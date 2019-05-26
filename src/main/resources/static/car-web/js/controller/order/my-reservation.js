/**
 *
 *  我的预约
 *   
 */
$(function() {

    var id = $.getQueryString('id'),
        crr = [],
        param = {},
        TS;
    var nw = localStorage.nTime;
    var param = {
        riAuditStatus: -1,
        riUseStart: -1,
        riUserName: ''
    } 
        // 获取列表
    function getList(pageNum, isFirst, param) {
        $.getData({
            url: '/carUserRelationInfo/getPageListByApplyId',
            query: {
                pageNum:pageNum,
                pageSize:10
            },
            body:param
        }, function(data) {
            //console.log(data)

            isFirst && $.renderPage({
                count: data.count,
                limit: 10,
                jump: function(num) {
                    getList(num, false, param);
                }
            })
            if (data.list && data.list.length) {
                data.list.forEach(function(item, index) {
                    item.num = index + 1 > 9 ? index + 1 : '0' + (index + 1);
                    crr = $.formatTime(item.riUseStart).split(' ')
                    item.riUseStart = crr[0]
                    item.createdDt = crr[1]
                });
                renderList(data.list);
            } else {
                renderEmpty();
            }

        })
    }

      //获取车辆公里数
  function getMileage(carId, id) {
    $.getData({
        url: '/carInfo/getCarJourneyAmount',
        query: {
            carId
        }  
    }, function(data) {
                var mileage = data || 0;

                $.pop({
                    size: 'sm',
                    noIcon: true,
                    content: `<div class="input-group">
                <input type="text" class="form-control" placeholder="请选择归还时间" aria-describedby="basic-addon1" id="start_time_picker" readonly>
                <span class="input-group-addon" id="basic-addon1">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
                                <div class="form-groupn" style="margin-top:10px">
                <input type="number" name="tel" id="tel" class="form-control" min="0" placeholder="请输入公里数">
            </div>
                               </div> `,

                    success: function() {
                        $('#start_time_picker').datetimepicker({
                            format: 'yyyy-mm-dd hh:ii',
                            language: 'zh-CN',
                            autoclose: true,
                            minView: 0,
                            minuteStep: 5,
                            startDate: new Date(),
                        });
                    },
                    yes: function() {
                        if ($('#start_time_picker').val() === '') {
                            $.toast('请选择归还时间!',{type: 'error'});
                            return false
                        } else if($('#tel').val() === ''){
                            $.toast('请输入公里数!',{type: 'error'})
                            return false
                            //结束行程应大于开始行程！！！！
                        } else if($('#tel').val().indexOf("-") != -1 || $('#tel').val() < mileage){
                            $.toast('请输入正确的公里数!',{type: 'error'});
                            return false
                        } else {
                            var TS = $.getTimeStamp($('#start_time_picker').val());
                            var endJourney = $('#tel').val();
                            $.getData({
                                    url: '/carUserRelationInfo/returnCar',
                                    query: {
                                        id: id,
                                        riUseEnd: TS,
                                        endJourney: endJourney
                                    }
                                },
                                function() {
                                    getList(1, true, param);
                                })
                        }

                    }
                })
    })
}
    // 渲染列表
    function renderList(data) {
        $('.js-list').html(template('order/my-reservation', { items: data }))
    }

    // 渲染无数据
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 12
        }))
    }

    function init() {
        getList(1, true, param)

        //取消预约
        $(document).on('click', '.js-edit1', function() {
            var id = $(this).data('id')
            $.bubble({
                el: $(this),
                msg:'确认取消预约此车辆？',
                ok: function () {
                    $.getData({
                        url: '/carUserRelationInfo/cancelCarUserRelationInfoById',
                        query: {
                            id: id,
                        }
                    },
                    function(data) {
                        getList(1, true, param)
                    })
                }
            });



        });

        //归还车辆
        $(document).on('click', '.js-edit2', function(e) {
            var id = $(this).data('id'),
                riId = $(this).data('ricarid');
            getMileage(riId,id);
        })

        //返回
        $(document).on('click', '#fh', function(){
            window.history.back()
        })
    }

    init();

})
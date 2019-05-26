/**
 * 预约管理
 */

$(function() {

    var id = $.getQueryString('id'),
        crr = [],
        param = {},
        TS;
    var nw = localStorage.nTime
  
    param.riUseStart = $('#startDate').val() === '' ? -1 : $.getTimeStamp($('#startDate').val());
    param.riAuditStatus = $.trim($('#state').val()) === '0' ? -1 : ($('#state').val()) * 1
    param.riUserName = $('#itemName').val() === '' ? '' : $.trim($('#itemName').val());
      // 获取列表
  function getList(pageNum, isFirst, param) {
      $.getData({
          url: '/carUserRelationInfo/getPageListByApplyId',
          body:param,
          query: {
            pageNum:pageNum,
            pageSize:10
          }  
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
              //console.log(data)
              data.list.forEach(function(item, index) {
                  item.num = index + 1 > 9 ? index +1 : '0' + (index + 1);
                  crr = $.formatTime(item.riUseStart).split(' ')
                  item.riUseStart = crr[0]
                  item.remarks = crr[1]
              });
              renderList(data.list);

          } else {
              renderEmpty();
          }

      })
  }

  //获取车辆公里数
  function getNum(id) {
    $.getData({
        url: '/carInfo/getCarJourneyAmount',
        query: {
            carId:id
        }  
    }, function(data) {
        var num = window.sessionStorage.setItem('num',data)
    })
}

  // 渲染列表
  function renderList(data) {
      $('.js-list').html(template('order/booking-management', { items: data }))
  }

  // 渲染无数据
  function renderEmpty() {
      $('.js-list').html(template('common/record-empty', {
          colspan: 14
      }))
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
                        title: '提示',
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
                                $('.modal-footer').css({"marginTop":"-5px","position":"relative"})
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



//初始
  function init() {
     getList(1, true, param)

      //取消预约
      $(document).on('click', '.js-edit1', function() {
        let marsk = '<div class="orderMarsk" style="z-index:9;width:100%;height:100%;background:#000;position:fixed;top:0;left:0;opacity:0.2;"></div>';
        $('body').append(marsk)
        $('.orderMarsk').click(function() {
            $(this).remove()
        })
          var id = $(this).data('id')
          $.bubble({
            el: $(this),
            msg:'确认取消预约此车辆？',
            ok: function () {
                $('.orderMarsk').remove()
                $.toast('取消成功', {
                    type: 'success'
                });
                $.getData({
                    url: '/carUserRelationInfo/cancelCarUserRelationInfoById',
                    query: {
                        id: id,
                    }
                },
                function(data) {
                    getList(1, true, param)
                })
            },
            cancel: function () {
                $('.orderMarsk').remove()
                // $.toast('您已取消');
            }
        });


      })

      $('#startDate').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        minView: 'year',
        autoclose: true
    });
    $(document).on('click', '.js-date-clean',function(){
        $(this).prev().val('');
    })

    //搜索
    $(document).on('click','.js-search',function(){
        param.riUseStart = $('#startDate').val() === '' ? -1 : $.getTimeStamp($('#startDate').val());
        param.riAuditStatus = $.trim($('#state').val()) === '0' ? -1 : ($('#state').val()) * 1
        param.riUserName = $('#itemName').val() === '' ? '' : $.trim($('#itemName').val());
        getList(1, true, param);
    });



        //归还车辆
        $(document).on('click', '.js-edit2', function(e) {
            var id = $(this).data('id'),
                riId = $(this).data('ricarid');
            getMileage(riId,id);
        })
  }

  init();

})
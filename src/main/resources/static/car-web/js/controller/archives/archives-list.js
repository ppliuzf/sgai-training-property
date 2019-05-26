$(function() {
    // 获取列表

    function getList(pageNum,isFirst) {
        var ciNumber = $.trim($('#serNumber').val()) === '' ? '' : $.trim($('#serNumber').val()),
        ciTypeId = $('#cllx').val() === '0' ? '' : $('#cllx').val(),
        ciStatus = $('#clzt').val() === '2' ? -1 : ($('#clzt').val()) * 1;
        $.getData({
            url: '/carInfo/getCarInfoPageList',
            query: {
                ciNumber: ciNumber,
                ciTypeId:ciTypeId,
                ciStatus:ciStatus,
                pageNum: pageNum,
                pageSize: 10
            },
        }, function(data) {
            if (data.list && data.list.length) {
                data.list.forEach(function (item, index) {
                   item.num = index + 1 > 9 ? index + 1 : '0' + (index+1);
                   if (item.ciImage) {
                       item.imgUrl = item.ciImage.split(',')[0];
                   }
                });
                renderList(data.list);
                isFirst && $.renderPage({
                    count: data.count,
                    jump: function(num) {
                        getList(num,false)
                    }
                });
            } else {
                renderEmpty();
            }
        });
    }

    //修改车辆状态
    function updateStatus(id, isOn) {
        $.getData({
            url: '/carInfo/updateCarStatus',
            query: {
                id: id,
                ciStatus: isOn ? 0 : 1
            },
        }, function(data) {
            $.toast('操作成功',{type: 'success'});
            //getList(1, true);
        });
    }


    // 渲染列表
    function renderList(data) {
        $('.js-list').html(template('archives/archives-list', {
            items: data
        })).find('.js-status').bootstrapSwitch({
            onText: "启用",
            offText: "禁用",
            size: "small",
            onSwitchChange: function() {
                updateStatus($(this).data('id'), $(this).is(':checked'));
            }
        });
    }

    // 渲染空
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 11
        }));
    }

    function init() {
        $('#serNumber').val('')
        $('#cllx').val('0') 
        $('#clzt').val('2') 
        getList(1,true);
        // 搜索
        $(document).on('click', '.js-search', function() {
                ciNumber = $.trim($('#serNumber').val()) === '' ? '' : $.trim($('#serNumber').val()),
                ciTypeId = $('#cllx').val() === '0' ? '' : $('#cllx').val(),
                ciStatus = $('#clzt').val() === '2' ? -1 : ($('#clzt').val()) * 1;
                getList(1,true);
        });
        // 删除
        $(document).on('click', '.js-del', function() {
            let marsk = '<div class="orderMarsk" style="width:100%;height:100%;background:#000;position:fixed;top:0;left:0;opacity:0.2;"></div>';
            $('body').append(marsk)
            $('.orderMarsk').click(function() {
                $(this).remove()
            })
            var id = $(this).data('id');
            $.bubble({
                el: $(this),
                msg:"确认删除此车辆？",
                ok: function () {
                    $('.orderMarsk').remove()
                    $.getData({
                        url: '/carInfo/deleteCarInfoById',
                        query: {
                            id: id
                        },
                    }, function(data) {
                            if(data.code === "20"){
                                $.alert('已经被预约中的车辆无法删除')
                                return false;
                            }else{
                                $.toast('删除成功', {
                                    type: 'success'
                                });
                                getList(1,true); 
                            } 
                    });
                },
                cancel: function () {
                    $('.orderMarsk').remove()
                    // $.toast('您已取消删除');
                }
            });
        });

        //新建跳转
        $(document).on('click', '#xj', function(){
            window.location.href = 'pages/archives/archives-add.html';
        })
    }

    init();
});
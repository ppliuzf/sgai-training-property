/**
 *
 *  新的预约
 *   
 */
$(function() {

    var id = $.getQueryString('id'),
        selectedTime = sessionStorage.getItem('selectedTime').split(' '),
        nt = sessionStorage.getItem('selectedTime');
    // 获取车辆信息
    function getList() {
        $.getData({
            url: '/carInfo/getCarInfoById',
            type: 'get',
            query: {
                id: id
            }
        }, function(data) {
            data.remarks = selectedTime[0];
            data.createdDt = selectedTime[2];
            renderMain(data)
        })
    }

    // 渲染内容
    function renderMain(data) {
        $('.js-mian').html(template('order/new-reservation', data))
    }

    //获取并判断数据
    function collectData() {
        var param = {}
        var mobilevalid = /^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$/;
        var time = new Date(nt);
        param.riCarId = id
        param.riUserName = $.trim($('#reser-name').val())
        param.riUserPhone = $.trim($('#tel').val())
        param.riUsePurpose = $.trim($('#textarea').val())
        param.riUseStart = $.getTimeStamp(nt)

        if (!mobilevalid.test(param.riUserPhone)) {
            $.alert('请输入正确手机号');
            return false;
        }

        return param;
    }

    //保存数据
    function saveData(param) {
        $.getData({
            url: '/carUserRelationInfo/addCarUserRelationInfo',
            body: param
        }, function(data) {
            $.toast('操作成功',{type: 'success'}, function() {
                 history.back();
            });
        });
    }

    //计算字符
    $.counter()
    function init() {
        getList();
        $.counter()
        // 取消
        $(document).on('click', '.js-cancel', function() {
            $.pop({
                content: '您确定取消此预约？',
                size: 'sm',
                yes: function() {
                    window.history.back();
                }
            })

        });

        //保存
        $(document).on('click', '.js-save', function() {

            if ($.trim($('#reser-name').val()) == '' || $.trim($('#tel').val()) == '' || $.trim($('#textarea').val()) == '') {
                $.alert('*为必填内容。')
                return false
            } else {
                $.pop({
                    content: '确定提交此预约？',
                    size: 'sm',
                    yes: function() {
                        var data = collectData();
                        if (data) {
                            saveData(data);
                        }
                    }
                })
            }

        });

        $('#backs').on('click', function() {
            window.history.back()
        })
    }
    init();
});
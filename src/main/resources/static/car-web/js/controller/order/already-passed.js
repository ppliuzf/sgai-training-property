/**
 *
 *  审核详情 
 *   
 */

$(function() {
    // 查询参数
    var arr = [],
        params = {},
        id = $.getQueryString('id'),
        riAuditStatus = $.getQueryString('riAuditStatus');
    var gy = $.getQueryString('createdDtLong');
    // 获取车辆信息

    function getList(params) {
        $.getData({
            url: '/carUserRelationInfo/getCarUserRelationInfoById',
            type: 'get',
            query: {
                id: id
            }
        }, function(data) {
            data.carUserRelationInfoList.createdDtLong = $.formatTime(data.carUserRelationInfoList.createdDtLong)
            data.carUserRelationInfoList.riUseStart = $.formatTime(data.carUserRelationInfoList.riUseStart)
            var brr = $.formatTime(new Date().valueOf()).split(' ')
            data.carUserRelationInfoList.condition = brr[0]
            renderMain(data.carUserRelationInfoList)
        })
    }

    // 渲染内容
    function renderMain(data) {
        $('.js-main').html(template('order/already-passed', data))
    }

    function renderContent(data) {
        $('.js-content').html(template('plan/mail', data))
    }

    function init() {
        getList();

        //返回
        $(document).on('click', '#fh', function(){
            window.history.back();
        })
    }
    init();

});
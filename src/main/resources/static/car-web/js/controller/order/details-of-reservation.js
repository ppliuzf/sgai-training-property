/**
 *
 *  预约详情
 *   
 */
$(function() {
    // 查询参数
    var id = $.getQueryString('id'),
        riAuditStatus = $.getQueryString('status')

    // 获取列表
    function getList() {
        $.getData({
            url: '/carUserRelationInfo/getCarUserRelationInfoById',
            type: 'get',
            query: {
                id: id,
                riAuditStatus: riAuditStatus
            }
        }, function(data) {       
            var res = data.carUserRelationInfoList,
                riUseStart = $.formatTime(res.riUseStart, 'yyyy-MM-dd hh:mm'),
                arr = riUseStart.split(' ');
                res.startJourney = res.endJourney  - res.lastJourney
            sTime = arr[0];
            sMinute = arr[1];
            res.sTime = sTime;
            res.sMinute = sMinute;
            renderInfor(res)
        });

    }

    // 渲染列表
    function renderInfor(data) {
        $('.js-main').html(template('order/details-of-reservation', { item: data }));
    }

    // 渲染无数据
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 5
        }));
    }

    function init() {
        getList();
        $(document).on('click', '#fh', function(){
            window.history.back();
        })


    }

    init();
});
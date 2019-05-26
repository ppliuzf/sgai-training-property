$(function () {

    var currPage = 1;
    // 收集数据
    function collectData(pageNum) {
        var rs = {};
        rs.pageNum = pageNum || 1;
        rs.pageSize = 10;
        rs.orderNo = $.trim($('#keyWords').val());
        var type = $.trim($('#type').val());
        if (type !== '3') {
            rs.orderType = Number($.trim($('#type').val()));
        }
        var status = $.trim($('#status').val());
        if (status !== '7') {
            rs.orderStat = Number($.trim($('#status').val()));
        }
        return rs;
    }
    // 获取列表
    function getList(pageNum) {
        // 收集数据
        var data = collectData();
        if (data) {
            $.getData({
                url: '/order/searchApproveList',
                query: collectData(pageNum)
            }, function (data) {
                $.renderPage({
                    count: data.count,
                    curr: currPage,
                    jump: function (n) {
                        currPage = n;
                        getList(n);
                    }
                });
                if (data.list && data.list.length) {
                    $('.pages').css('display', 'block');
                    renderList(data.list);
                } else {
                    $('.pages').css('display', 'none');
                    renderEmpty();
                }
            });
        }
    }
    // 渲染列表
    function renderList(data) {
        $('.js-list').html(template('purchase/purchase-approval-list', {
            items: data
        }));
    }
    // 渲染无数据
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 7
        }));
    }
    function init() {
        // 搜索
        $(document).on('click', '.js-search', function () {
            currPage = 1;
            getList();
        });
        // 点击订单详情
        $(document).on('click', '.js-detail', function(){
            var ss = $(this).attr('orderstat');
            var id = $(this).attr('id-main');
            if (ss == 1) {
                window.location.href = './examine-details.html?status=wcl&orderId=' + id;
            } else if (ss == 2) {
                window.location.href = './examine-details.html?status=yjj&orderId=' + id;
            } else if (ss == 3 || ss == 4 || ss == 5) {
                window.location.href = './examine-details.html?status=ytg&orderId=' + id;
            }
        });
        $.renderPage({ count: 100 });
        getList();
    }
    init();
});

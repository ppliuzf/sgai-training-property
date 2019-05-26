$(function () {
    var currPage = 1;
    // 空列表
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 16
        }));
    }
    // 渲染列表
    function renderList(data) {
        $('.js-list').html(template('check/inventory-management-list', {
            items: data
        }));
    }
    // 收集搜索数据
    function collectSearchData() {
        var rs = {};
        rs.whName = $.trim($('#keyWords').val());
        rs.matName = $.trim($('#keyWords1').val());
        return rs;
    }
    
    // 获取列表
    function getList(pageNumber, isFirst) {
        var data1 = collectSearchData();
        $.getData({
            url: '/depotManage/inventoryManages',
            query: {
                pageNo: pageNumber || 1,
                pageSize: 10
            },
            body: data1
        }, function (data) {
            if (data && data.list && data.list.length) {
                $('.pages').css('display', 'block');
                total = data.count;
                isFirst && $.renderPage({
                    count: total,
                    jump: function (n) {
                        getList(n);
                    }
                });
                renderList(data.list);
            } else {
                $('.pages').css('display', 'none');
                renderEmpty();
            }
        });
    }
    function init() {
        // 搜索
        $(document).on('click', '.js-search', function () {
            getList(1,true);
        });
        getList(1,true);
        $.renderPage({ count: 100 });
    }
    init();
});

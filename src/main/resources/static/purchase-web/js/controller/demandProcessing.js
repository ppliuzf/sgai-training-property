$(function () {
    var currPage = 1;
    // 获取列表
    function getList(pageNum) {
        $.getData({
            url: '/order/demondList',
            query: {
                pageNum: pageNum || 1,
                empName: $.trim($('#keyWords').val()),
                pageSize: 10
            }
        }, function (data) {
            for (var i = 0; i < data.list.length; i++) {
                data.list[i].number1 = i + 1;
            }
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
    // 渲染列表
    function renderList(data) {
        $('.js-list').html(template('purchase/demandProcessing', {
            items: data
        }));
    }
    // 渲染无数据
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 10
        }));
    }
    function init() {
        // 搜索
        $(document).on('click', '.js-search', function () {
            currPage = 1; // 搜索的时候记得重置当前页号为１
            getList();
        });
        // 点击处理
        $(document).on('click', '.js-chuli', function(){
            var orderId = $(this).parents('tr').attr('data-id');
            location.href = './purchase-demand-detail.html?status=edit&orderId=' + orderId;
        });
        // 点击返回
        $(document).on('click', '.js-back', function(){
            window.history.go(-1);
        });
        // 点击编号
        $(document).on('click', '.js-detail', function(){
            var id = $(this).attr('data-id');
            location.href = './purchase-demand-detail.html?status=show&orderId='+id;
        });
        $.renderPage({ count: 100 });
        getList();
    }
    init();
});

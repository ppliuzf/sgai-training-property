$(function () {
    var currPage = 1;
    // 收集数据
    function collectData(pageNum) {
        var rs = {};
        rs.pageNum = pageNum || 1;
        rs.pageSize = 10;
        // 申请人姓名
        rs.taskEmpName = $.trim($('#keyWords').val());
        return rs;
    }
    // 获取列表
    function getList(pageNum) {
        var data = collectData(pageNum);
        if (data) {
            $.getData({
                url: '/listOrDetail/getApplyList',
                query: data
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
                    var arr = [];
                    for(var i = 0;i<data.list.length;i++){
                        if(data.list[i].taskDept){
                            arr[i] = JSON.parse(data.list[i].taskDept)[0].title;
                        }
                    }
                    for(var m = 0;m<arr.length;m++){
                        data.list[m].department = arr[m];
                    }
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
        $('.js-list').html(template('purchase-applyT/purchase-apply-list', {
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
        // 处理
        $(document).on('click', '.js-chuli', function(){
            window.location.href = './purchase-apply-deal.html?id=' + $(this).attr('data-id');
        });
        // 操作详情
        $(document).on('click', '.js-xq', function () {
            window.location.href = './purchase-apply-aldeal.html?id=' + $(this).attr('data-id') + "&status=" + $(this).data('status');
        });
        // 搜索
        $(document).on('click', '.js-search', function () {
            currPage = 1;
            getList();
        });
        getList();
        $.renderPage({ count: 100 });
    }
    init();
});


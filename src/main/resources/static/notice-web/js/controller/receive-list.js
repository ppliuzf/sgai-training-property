$(function () {
    $(document).off('click');
    $('body').removeClass('loaded').addClass('loaded');
    // 渲染空列表
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 12
        }));
    }
    // 渲染列表
    function renderList (data) {
        $('.js-list').html(template('list/receive-list', {
            items: data
        })); 
    }
    // 收集搜索数据
    function collectSearchData() {
        var rs = {};
        rs.keyword = $.trim($('#keyWords').val());
        rs.infoUrgency = Number($('#type').val());
        return rs;
    }
    // 获取列表
    function getList(pageNumber, isFirst){
        var data = collectSearchData();
        if(!data){
            data = null;
        }
        $.getData({
            url: '/noticeInfoIReceive/cminfoPageList',
            query: {
                pageNum: pageNumber || 1,
                pageSize: 10
            },
            body: collectSearchData()
        }, function (data) {
            if (data && data.list && data.list.length) {
                $('.pages').css('display','block');
                total = data.count;
                isFirst && $.renderPage({
                    count: total,
                    jump: function(n) {
                        getList(n);
                    }
                });
                for (var i = 0; i < data.list.length; i++) {
                    data.list[i].number1 = i + 1;
                }
                renderList(data.list);
            } else{
                $('.pages').css('display', 'none');
                renderEmpty();
            }
        });
    }
    // 跳转到新增
    $(document).on('click', '.addnotice', function () {
        location.href = 'add-notice.html';
    });
    function init() {
        // 搜索
        $(document).on('click', '.js-search', function () {
            getList(1, true);
        });
        getList(1, true);
    }
    init();
});

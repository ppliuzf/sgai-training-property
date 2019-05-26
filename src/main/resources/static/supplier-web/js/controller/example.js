$(function () {
    var currPage = 1;
    // 获取列表
    function getList() {
        $.getData({
            url: '',
            query: {
                pageNo: currPage
            }
        }, function(data) {
            $.renderPage({
                count: data.count,
                curr: currPage,
                jump: function(n) {
                    currPage = n;
                    getList();
                }
            });
            if (data.list && data.list.length) {
                renderList(data);
            } else {
                renderEmpty();
            }
        });
    }
    // 渲染列表
    function renderList(data) {
        
    }
    // 渲染无数据
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 3
        }));
    }
    // 删除
    function delItem(el, id) {
        $.getData({
            url: '',
            query: {
                id: id
            }
        }, function(data) {
            if (data) {
                $.msg('操作成功');
                el.parent().parent().remove();
                if (currPage !== 1 && !$('.js-list tr').length) {
                    currPage -= 1;
                }
                getList();
            }
        });
    }
    function init() {
        // getList();
        // 搜索
        $(document).on('click', '.js-search', function() {
            currPage = 1; // 搜索的时候记得重置当前页号为１
        });
        // 删除
        $(document).on('click', '.js-del', function() {
            delItem($(this), $(this).data('id'));
        });
    }
    init();
});
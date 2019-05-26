$(function () {
    var currPage = 1;
    // 获取列表
    function getList() {
        $.getData({
            url: '/level/findGysLevelList',
            query: {
                pageNum: currPage,
                pageSize: 10
            },
        }, function (data) {
            $.renderPage({
                count: data.count,
                curr: currPage,
                jump: function (n) {
                    currPage = n;
                    getList();
                }
            });
            if (data.list && data.list.length) {
                for ( var i = 0; i < data.list.length; i++) {
                    data.list[i].index = i + 1;
                }
                renderList(data.list);
            } else {
                renderEmpty();
            }
        });
    }
    // 渲染数据为空
    function renderEmpty(text) {
        $('.js-list').html(template('common/record-empty', {
            colspan: 5,
            text: '暂无数据' || text
        }));
    }
    // 渲染列表
    function renderList(data) {
        $('.js-list').html(template('grade-list', {
            items: data
        }));
    }
    // 删除
    function delItem(el, id) {
        $.getData({
            url: '/level/deleteGysLevelById',
            query: {
                id: id
            }
        }, function (data) {
            if (data.code === '0') {
                // $.msg('操作成功');
                el.parent().parent().remove();
                if (currPage !== 1 && !$('.js-list tr').length) {
                    currPage-=1;
                }
                getList();
            } else {
                $.alert('删除失败');
            }
        });
    }

    function init() {
        getList();
        $(document).on('click', '.btn-add', function () {
            window.location.href = "./grade-add.html"
        });
        // 删除
        $(document).on('click', '.js-del', function () {
            var $this = $(this),
                id = $(this).data('id'),
                count = $(this).data('count');
            if(count > 0){
                // $.alert('已关联供应商,不能删除!')
                $.bubble({
                    el: $(this),
                    msg: '已关联供应商,不能删除!',
                });
            }else{
                $.bubble({
                    el: $(this),
                    msg: '确定删除当前供应商等级？',
                    ok: function () {
                        $.toast('删除成功', {
                            type: 'success'
                        });
                        delItem($this, id);
                    },
                    cancel: function () {
                        // $.toast('您已取消删除');
                    }
                });
            }
            return false;
        });
    }
    init();
});

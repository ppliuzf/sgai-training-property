$(function () {
    var currPage = 1;
    // 获取列表
    function getList() {
        $.getData({
            url: '/type/findTypePageList',
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
                renderList(data.list);
            } else {
                renderEmpty();
            }
        });
    }
    // 渲染数据为空
    function renderEmpty(text) {
        $('.js-list').html(template('common/record-empty', {
            colspan: 6,
            text: '暂无数据' || text
        }));
    }

    // 渲染列表
    function renderList(data) {
        $('.js-list').html(template('type-list', {
            items: data
        }));
    }

    // 删除
    function delItem(el, id) {
        $.getData({
            url: '/type/deleteById',
            query: {
                id: id
            }
        }, function (data) {
            var codeFun = {
                '0': function () {
                    // $.msg('操作成功');
                    el.parent().parent().remove();
                    if (currPage !== 1 && !$('.js-list tr').length) {
                        currPage -= 1;
                    }
                    getList();
                },
                '-1': function () {
                    $.alert('删除失败');
                },
                '3': function () {
                    $.alert(data.message);
                }
            }
            codeFun[data.code]()
        });
    }

    function init() {
        getList();
        $(document).on('click', '.btn-add', function () {
            window.location.href="./type-add.html"
        });
        // 删除
        $(document).on('click', '.js-del', function () {
            var $this = $(this),
                id = $(this).data('id');
            // $.confirm('确定删除当前合同类型？', function () {
            //     setTimeout(function () {
            //         delItem($this, id);
            //     }, 500);
            // });
            $.bubble({
                el: $(this),
                msg: '确定删除当前合同类型？',
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
            return false;
        });
    }init();
});
$(function () {
    var currPage = 1;
    // 点击批量新建出库单
    $(document).on('click', '.addDepot', function () {
        window.location.href = "./archives-add.html";
    });
    // 获取列表
    function getList(isSearch) {
        $.getData({
            url: 'depotManage/depotLists',
            query: {
                pageNo: currPage,
                pageSize: 10,
                whName: $('#name').val() ? $('#name').val() : '',
                whAddress: $('#address').val() ? $('#address').val() : '',
                whType: Number($('#type').val()) ? Number($('#type').val()) : ''
            }
        }, function (data) {
            $.renderPage({
                count: data.count,
                curr: currPage,
                jump: function (n) {
                    currPage = n;
                    getList(isSearch);
                }
            });
            if (data.list && data.list.length) {
                renderList(data.list);
            } else {
                renderEmpty();
            }
        });

    }
    // 渲染列表
    function renderList(data) {
        $('.js-list').html(template('list/archives-list', {
            items: data
        }));
    }
    // 渲染无数据
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 7
        }));
    }
    // 删除
    function delItem(el, id) {
        $.getData({
            url: 'depotManage/deleteDepot',
            query: {
                id: id
            }
        }, function (data) {
            if (data) {
                $.toast('操作成功', { type: "success" });
                el.parents('[data-id]').remove();
                if (currPage !== 1 && !$('.js-list tr').length) {
                    currPage -= 1;
                }
                getList();
            }
        });
    }

    function init() {
        getList();
        // 搜索
        $(document).on('click', '.js-search', function () {
            currPage = 1;
            getList();
        });
        // 点击删除
        $(document).on('click', '.js-tb-ch', function () {
            var $this = $(this);
            $.bubble({
                el: $this,
                msg: '确认删除当前仓库？',
                ok: function () {
                    setTimeout(function () {
                        delItem($this, $this.parents('[data-id]').attr('data-id'))
                    }, 300);

                }
            });
        });
        // 分页
        $.renderPage({ count: 100 })
    }
    init();
});

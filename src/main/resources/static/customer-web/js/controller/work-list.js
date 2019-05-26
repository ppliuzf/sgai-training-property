$(function () {
    var currPage = 1;
    // 获取列表
    function getList(pageNum = 1, isFirst = true) {
        $.getData({
            url: '/workorderCost/workorderCostList',
            query: {
                pageNum: pageNum,
                pageSize: 10
            }
        }, function (data) {
            total = data.count;
            if (data && data.list.length) {
                renderList(data.list);
                isFirst &&
                    $.renderPage({
                        count: total,
                        curr: pageNum,
                        jump: function (n) {
                            currPage = n;
                            getList(n, true);
                        }
                    });
            } else {
                renderEmpty();
            }
        });
    }// 删除
    function delItem(el, id) {
        $.getData({
            url: '/workorderCost/deleteWorkorderCostById',
            query: {
                id: id
            }
        }, function (data) {
            if (data) {
                // $.msg('操作成功', function () {
                //     getList(1, true)
                // });
                $.toast('操作成功', {
                    type: 'success',
                }, function(){
                    getList(1, true)
                });
            }
        });
    }
    // 渲染列表
    function renderList(data) {
        $('.js-show').html(template('work-list', {
            items: data
        }));
    }
    // 渲染空
    function renderEmpty() {
        $('.js-show').html(template('common/work-empty', {
            colspan: 5
        }));
    }
    function init() {
        getList();
        $(document).on('click', '.js-del', function () {
            var $this = $(this),
                id = $(this).data('id');
            // $.confirm('确定删除当前工单？', function () {
            //     delItem($this, id);
            // }
            $.bubble({
                el: $(this),
                msg: '确定删除当前工单？',
                ok: function () {
                    delItem($this, id);
                },
                cancel: function () {
                    $.toast('您已取消删除');
                }
            });
            return false;
        });
    }
    init();
});

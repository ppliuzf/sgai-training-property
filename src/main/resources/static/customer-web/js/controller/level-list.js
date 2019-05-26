$(function () {
    // 获取列表
    function getList() {
        $.getData({
            url: '/customer/customLevelInfoList'
        }, function(data) {
            if (data && data.length) {
                renderList(data);
            } else {
                renderEmpty();
            }
        });
    }// 删除
    function delItem(el, id) {
        $.getData({
            url: '/customer/deleteCustomLevelInfoById',
            query: {
                clId: id
            }
        }, function(data) {
            if (data) {
                // $.msg('操作成功', function() {
                //     el.parent().parent().parent().parent().parent().remove();
                // });
                $.toast('操作成功', {
                    type: 'success',
                }, function(){
                    // el.parent().parent().parent().parent().parent().remove();
                    getList();
                });
            }
        });
    }
    // 渲染列表
    function renderList(data) {
        $('.js-list').html(template('level-list', {
            items: data
        }));
    }
    // 渲染空
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 3
        }));
    }
    function init() {
        getList();
        $(document).on('click', '.js-del', function() {
            var $this = $(this),
                id = $(this).data('id');
            // $.confirm('确定删除当前级别？', function () {
            //     delItem($this, id);
            // });
            $.bubble({
                el: $(this),
                msg: '确定删除当前级别？',
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

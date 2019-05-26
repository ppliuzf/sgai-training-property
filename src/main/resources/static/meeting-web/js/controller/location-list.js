$(function () {
    var currPage = 1;
    // 获取列表
    function getList() {
        $.getData({
            url: '/roomResource/roomPositionPageList',
            query: {
                pageNo: currPage,
                pageSize: 10
            }
        }, function (data) {
            $.renderPage({
                count: data.count,
                curr: currPage,
                jump: function (num) {
                    currPage = num;
                    getList(num);
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
        $('.js-list').html(template('./plan/location-list', {
            items: data
        }));
        // var selectAll = $.selectAll();
        // selectAll.reset();
    }
    // 渲染无数据
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 4
        }));
    }
    // 删除
    function delItem(el, id) {
        $.getData({
            url: '/roomResource/deleteRoomPositionById',
            query: {
                rpId: id
            }
        }, function (data) {
            if (data) {
                // $.msg('操作成功');
                $.toast("操作成功",{
                    type: 'success'
                });
                el.parent().parent().remove();
                if (currPage !== 1 && !$('.js-list tr').length) {
                    currPage -= 1;
                }
                getList();
            }
        });
    }
    function init() {
        getList();
        // 删除
        $(document).on('click', '.js-del', function () {
            var $this = $(this);
            $.bubble({
                el: $(this),
                msg: '您确定删除当前会议室位置？',
                ok: function () {
                    delItem($this, $this.data('id'));
                },
                cancel: function () {
                    $.toast('您已取消');
                }
            });
            // $.confirm('确定删除当前会议室位置？', function () {
            //     delItem($this, $this.data('id'));
            // });
        });
        // 批量删除
        // $(document).on('click', '.js-del-multi', function () {
        //     $.confirm('确定批量删除会议室位置？', function () {
                
        //     });
        // });
    }
    init();
});
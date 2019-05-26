$(function () {
    var currPage = 1;
    // 获取列表
    function getList() {
        $.getData({
            url: '/htTemplet/findHtTempletList',
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
            colspan: 8,
            text: '暂无数据' || text
        }));
    }

    // 渲染列表
    function renderList(data) {
        $('.js-list').html(template('tpl-list', {
            items: data
        }));
    }

    // 删除
    function delItem(el, id) {
        $.getData({
            url: '/htTemplet/deleteHtTempletById',
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
        // 删除
        $(document).on('click', '.btn-add', function () {
            window.location.href="./tpl-add.html"
        });
        $(document).on('click', '.js-del', function () {
            var $this = $(this),
                id = $(this).data('id');
            // $.confirm('确定删除当前合同模板？', function () {
            //     setTimeout(function () {
            //         delItem($this, id);
            //     }, 500);
            // });
            $.bubble({
                el: $(this),
                msg: '确定删除当前合同模板？',
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
        $(document).on('click', '.js-download', function () {
            var url = $(this).data('url');
            var fileName = $(this).data('name');
            window.location.href = serverUrl+'/uploadDown/downLoadFile?filePath=http://192.168.144.246:20001'+url+'&filename='+fileName+'';
        })
    } init();
});
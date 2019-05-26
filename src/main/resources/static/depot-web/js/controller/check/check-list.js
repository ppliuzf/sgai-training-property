$(function () {
    var currPage = 1, mainID;
    $('.form_date5').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: true,
        todayHighlight: 1,
        startView: 3,
        minView: 3,
        forceParse: 0
    });
    $(document).on('click', '.js-date-clean', function () {
        $(this).prev().val('');
    });
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 16
        }));
    }
    // 渲染列表
    function renderList(data) {
        $('.js-list').html(template('check/check-list', {
            items: data
        }));
    }
    // 收集搜索数据
    function collectSearchData(pageNumber) {
        var rs = {};
        if ($.trim($('#keyWords').val())) {
            rs.ivtTitle = $.trim($('#keyWords').val());
        }
        if ($.trim($('#time').val())) {
            rs.dt = $.trim($('#time').val()) + '-02 00:00:00';
        }
        if (Number($('#type').val()) !== 4) {
            rs.matStat = Number($('#type').val());
        }
        rs.pageNum = pageNumber || 1;
        rs.pageSize = 10;
        return rs;
    }
    // 获取列表
    function getList(pageNumber) {
        var data1 = collectSearchData(pageNumber);
        console.log(data1);
        if (data1) {
            $.getData({
                url: 'Inventories/searchPage',
                query: data1
            }, function (data) {
                if (data && data.list && data.list.length) {
                    $('.pages').css('display', 'block');
                    total = data.count;
                    $.renderPage({
                        count: data.count,
                        curr: currPage,
                        jump: function (n) {
                            currPage = n;
                            getList(n);
                        }
                    });
                    for (var i = 0; i < data.list.length; i++) {
                        if (data.list[i].ivtPiJson) {
                            data.list[i].gg = JSON.parse(data.list[i].ivtPiJson).title;
                        }
                    }
                    renderList(data.list);
                } else {
                    $('.pages').css('display', 'none');
                    renderEmpty();
                }
            });
        }
    }
    // 删除
    function delItem(el, id) {
        console.log(id);
        $.getData({
            url: '/Inventories/del',
            query: {
                ivtNo: id
            }
        }, function (data) {
            if (data) {
                $.toast('操作成功');
                el.parents('[data-id]').remove();
                if (currPage !== 1 && !$('.js-list tr').length) {
                    currPage -= 1;
                }
            }
        });
    }
    function sd(data) {
        var rs = {};
        rs.ivtNo = data;
        return rs;
    }
    // 点击执行前先执行这个
    function actMain(data) {
        var data1 = sd(data);
        if (!data1) {
            return false;
        }
        console.log(JSON.stringify(data1));
        $.getData({
            url: '/Inventories/begin',
            query: data1
        }, function () {
            console.log('@@@@');
        });
    }
    function init() {
        // 详情
        $(document).on('click', '.detailMy', function () {
            window.location.href = './check-detail.html?infoId=' + $(this).attr('data-id') + '&state=' + $(this).attr('data-state');
        });
        // 搜索
        $(document).on('click', '.js-search', function () {
            getList();
        });
        // 新建
        $(document).on('click', '.js-add', function () {
            window.location.href = './check-add.html';
        });
        // 删除
        $(document).on('click', '.js-del', function () {

            var $this = $(this);
            $.bubble({
                el: $this,
                // title: '提示',
                msg: '确认要删除该盘点吗？',
                // btn: ['确认', '取消'],
                // size: 'sm',
                ok: function () {
                    delItem($this, $this.attr('data-id'));
                }
            });
        });
        // 编辑

        $(document).on('click', '.js-edit', function () {
            var dataId = $(this).attr('data-id');
            window.location.href = './check-add.html?infoId=' + dataId;
        });

        // 执行
        $(document).on('click', '.js-act1', function () {
            mainID = $(this).attr('data-id');
            actMain(mainID);
            window.location.href = './check-act.html?infoId=' + mainID + '&state=' + $(this).attr('data-state');
        });
        getList();
        $.renderPage({ count: 100 });
    }
    init();
});

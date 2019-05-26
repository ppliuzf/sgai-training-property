$(function () {
    var currPage = 1;
    function init() {
        
        // 点击新建出库单
        $(document).on('click', '#addOrder', function () {
            window.location.href = "./add-in.html";
        });
        // 点击批量新建出库单
        $(document).on('click', '#addAll', function () {
            window.location.href = "./in-addAll.html";
        });
        // 搜索
        $(document).on('click', '.js-search', function () {
            currPage = 1;
            getList();
        });
        // 点击撤消
        $(document).on('click', '.undoBtn', function(){
            var self = $(this);
            $.bubble({
                // title: '提示',
                el:self,
                msg: '确定撤销当前入库单？',
                // btn: ['确认', '取消'],
                // size:'sm',
                ok: function () {
                    $.getData({
                        url: 'depotInManage/deleteDepot',
                        query: {
                            id: self.attr('ids')
                        }
                    }, function (data) {
                        window.location.reload();
                    });
                }
            });

        });
        getList();
    }
    init();
    // 收集数据
    function collectData(pageNum) {
        var rs = {};
        rs.pageNo = pageNum || 1;
        rs.pageSize = 10;
        
        return rs;
    }
    // 获取列表
    function getList(pageNum) {
        // 收集数据
        var data = collectData();
        var body = {};
        body.whInNo = $.trim($('#keyWords').val());
        if ($.trim($('#type').val()) != 0) {
            body.whInType = Number($.trim($('#type').val()));
        }
        if ($.trim($('#status').val()) != 0) {
            body.whInStat = Number($.trim($('#status').val()));
        }
        
        if (data) {
            $.getData({
                url: 'depotInManage/depotInLists',
                query: collectData(pageNum),
                body: body
            }, function (data) {
                $.renderPage({
                    count: data.count,
                    curr: currPage,
                    jump: function (n) {
                        currPage = n;
                        getList(n);
                    }
                });
                if (data.list && data.list.length) {
                    $('.pages').css('display', 'block');
                    renderList(data.list);
                } else {
                    $('.pages').css('display', 'none');
                    renderEmpty();
                }
            });
        }
        // renderList({});
    }
    // 渲染列表
    function renderList(data) {
        $('.js-list').html(template('in/in-list', {
            items: data
        }));
    }
    // 渲染无数据
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 10
        }));
    }
    // 搜索
    $(document).on('click', '#searchBtn', function () {
        currPage = 1;
        getList();
    });
});

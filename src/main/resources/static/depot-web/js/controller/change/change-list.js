$(function () {
    function init() {
        getList();
    }
    init();
    var currPage = 1;
    // 收集数据
    function collectData(pageNum) {
        var rs = {};
        rs.pageNum = pageNum || 1;
        rs.pageSize = 10;
        rs.allotNo = $.trim($('#allotNo').val());
        return rs;
    }
    // 获取列表
    function getList(pageNum) {
        // 收集数据
        var data = collectData();
        if (data) {
            $.getData({
                url: 'allot/searchList',
                query: collectData(pageNum)
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
    }
    // 渲染列表
    function renderList(data) {
        $('.js-list').html(template('change/change-list', {
            items: data
        }));
    }
    // 渲染无数据
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 9
        }));
    }
    
    // 点击新建出库单
    $(document).on('click', '#addOrder', function () {
        window.location.href = "./add-change.html";
    });
    // 搜索
    $(document).on('click', '#searchBtn', function () {
        currPage = 1;
        getList();
    });
    // 点击撤消
    $(document).on('click', '.undoBtn', function(){
        var self = $(this);
        $.bubble({
            el:self,
            msg: '确定撤回此申请？',
            ok: function () {
                $.getData({
                    url: '/allot/del',
                    query: {
                        id: self.attr('ids')
                    }
                }, function (data) {
                    window.location.reload();
                });
            }
        });

    });
    // 点击订单详情
    $(document).on('click', '.js-detail', function(){
        
    });
});

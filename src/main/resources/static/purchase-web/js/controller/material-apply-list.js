$(function () {
    var currPage = 1;
    // 点击新建申请
    $(document).on('click', '.addnotice', function () {
        window.location.href = "./material-apply-add.html";
    });
    // 获取列表
    function getList(isSearch) {
        var query={};
            query = {
                pageNum: currPage,
                pageSize:10,
                applyEmpName: $('#keyWords').val()
            }
        $.getData({
            url: '/suppliesOperation/suppliesList',
            query: query
        }, function(data) { 
            $.renderPage({
                count: data.count,
                curr: currPage,
                jump: function(n) {
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
        $('.js-list').html(template('order/material-apply-list', {
            items: data
        }));
    }
    // 渲染无数据
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 11
        }));
    }
    // 删除
    function delItem(el, id) {
        $.getData({
            url: '',
            query: {
                id: id
            }
        }, function(data) {
            if (data) {
                $.toast('操作成功',{type:"success",timer:2000});
                el.parent().parent().remove();
                if (currPage !== 1 && !$('.js-list tr').length) {
                    currPage -= 1;
                }
                getList();
            }
        });
    }
    // 撤回
    function revoke(el, id){
        $.getData({
            url: 'suppliesOperation/suppliesRetract',
            query: {
                id: id
            }
        }, function (data) {
            if (data) {
                $.toast('操作成功', {
                    type:"success",
                    timer: 2000
                });
                // el.parents("td").html('<td></td>').parents("tr").find('.js-status').text('已撤回');
                getList();
            } else {
                $.alert('操作失败');
            }
        });
    }
    // 领取
    function getMat(el ,id){
        $.getData({
            url: 'suppliesOperation/getSupplies',
            query: {
                id: id
            }
        }, function (data) {
            if (data) {
                $.toast('操作成功', {
                    type:"success",
                    timer: 2000
                });
                // el.parents("td").html('<td></td>').parents("tr").find('.js-status').text('已领取');
                getList();
            } else {
                $.alert('操作失败');
            }
        });
    }
    function init() {
        getList();
        // 搜索
        $(document).on('click', '.js-search', function() {
            getList();
            currPage = 1; // 搜索的时候记得重置当前页号为１
        });
        // 重置
        $(document).on('click', '.js-reset', function() {
            getList();
            $("#keyWords").val("");
            currPage = 1; 
        });
        // 删除
        $(document).on('click', '.js-del', function() {
            delItem($(this), $(this).data('id'));
        });
        // 点击撤回
        $(document).on('click', '.js-tb-ch', function() {
            var that = $(this);
            $.bubble({
                el:that,
                // title: '提示',
                msg: '确认要撤回该申请吗？',
                ok: function () {
                    revoke($(that), $(that).parents("tr").attr('data-id'));
                }
            });
        });
        // 点击领取物料
        $(document).on('click', '.js-tb-lq', function() {
            var that = $(this);
            $.bubble({
                el:that,
                // title: '提示',
                msg: '确认要领取用料吗？',
                // btn: ['确认', '取消'],
                // size:'sm',
                ok: function () {
                    getMat($(that), $(that).parents("tr").attr('data-id'));
                }
            });
        });
        // 分页
        $.renderPage({count:100})
    }
    init();
});

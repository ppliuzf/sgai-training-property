$(function () {
    var currPage = 1;
    // 点击新建计划
    $(document).on('click', '.addplan', function () {
        window.location.href = "./add-purchase-plan.html";
    });
    // 获取列表
    function getList(isSearch) {
        var query={};
        var body = {};
        query = {
            pageNum: currPage,
            pageSize:10
        }

        body = {
            planName: $('#planName').val(),
            planStat: $('#planState option:selected').val(),
            approveIsYup: ''
        }
        $.getData({
            url: '/listOrDetail/getPlanList',
            query: query,
            body: body,
        }, function(data) { 
            console.log(123456);
            $.renderPage({
                count: data.count,
                curr: currPage,
                jump: function(n) {
                    currPage = n;
                    getList(isSearch);
                }
            });
            if (data.list && data.list.length) {
                data.list.forEach(function(item) {
                    if (item.planDept){
                        item.dept = JSON.parse(item.planDept)[0].title;
                        // var deptList = JSON.parse(item.planDept);
                        // var deptNames = '';
                        // deptList.forEach(function(item){
                        //     deptNames = deptNames + item.title + ' ';
                        // });
                        // item.dept = deptNames;
                    } else {
                        item.dept = '';
                    }
                    
                });
                renderList(data.list);
            } else {
                renderEmpty();
            }
        });
    }
    // 渲染列表
    function renderList(data) {
        $('.js-list').html(template('plan/purchase-plan-list', {
            items: data
        }));
    }
    // 渲染无数据
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 10
        }));
    }
    
    function init() {
        getList();
        // 搜索
        $(document).on('click', '#searchBtn', function() {
            getList();
            currPage = 1; // 搜索的时候记得重置当前页号为１
        });
        // 分页
        $.renderPage({count:100})
    }
    
    init();

    // 跳转详情
    $(document).on('click', '.jumpDetail', function(e){
        var self = $(this);
        var thisStatus = self.attr('status');
        var thisId = self.attr('id');
        window.location.href = 'purchase-paln-detail.html?id='+thisId+'&status='+thisStatus;
    });

    // 点击提交
    $(document).on('click', '.sutmbitBtn', function(e){
        var self = $(this);
        var selfId = $(this).parents("tr").attr('ids');
        popMsg(self,'确定提交当前计划？','/listOrDetail/planSubmit', selfId);
    });
    // 点击删除
    $(document).on('click', '.delBtn', function(e){
        var self = $(this);
        var selfId = $(this).parents("tr").attr('ids');
        popMsg(self,'确定删除当前计划？', '/listOrDetail/planDelete', selfId);
    });
    // 点击撤消
    $(document).on('click', '.cancelBtn', function(e){
        var self = $(this);
        var selfId = $(this).parents("tr").attr('ids');
        popMsg(self,'确定撤销当前计划？', '/listOrDetail/planRevocation', selfId);
    });
    // 公共弹框方法
    function popMsg (obj,content, ajaxUrl, selfId) {
        $.bubble({
            el:obj,
            // title: '提示',
            msg: content,
            // btn: ['确认', '取消'],
            // size: 'sm',
            ok: function () {
                $.getData({
                    url: ajaxUrl,
                    query: {'id': selfId}
                }, function(data) { 
                    // alert(123);
                    init();
                });
            }
        });
    }
});

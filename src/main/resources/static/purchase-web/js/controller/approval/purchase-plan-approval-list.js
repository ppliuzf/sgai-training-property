$(function () {
    var currPage = 1;
    // 获取列表
    function getList(isSearch) {
        var query={};
        var body = {};
        query = {
            pageNum: currPage,
            pageSize:10

            // planDept:"",
            // planDeptId:"",
            // planDescribe:"",
            // planType:0, 
        }

        body = {
            planName: $('#planName').val(),
            planStat: $('#planState option:selected').val(),
            approveIsYup: 'Y'
        }
        $.getData({
            url: '/listOrDetail/getPlanList',
            query: query,
            body: body,
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
                data.list.forEach(function(item) {
                    item.dept = JSON.parse(item.planDept)[0].title;
                });
                renderList(data.list);
            } else {
                renderEmpty();
            }
        });
    }
    // 渲染列表
    function renderList(data) {
        $('.js-list').html(template('approval/purchase-plan-approval-list', {
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
        window.location.href = 'purchase-plan-approval-detail.html?id='+thisId+'&status='+thisStatus;
    });

    // 点击提交
    $(document).on('click', '.sutmbitBtn', function(e){
        popMsg('确认要提交吗？', '/suppliesOperation/suppliesList');
    });
    // 点击删除
    $(document).on('click', '.delBtn', function(e){
        popMsg('确认要删除吗？', '/suppliesOperation/suppliesList');
    });
    // 点击撤消
    $(document).on('click', '.cancelBtn', function(e){
        popMsg('确认要撤消吗？', '/suppliesOperation/suppliesList');
    });
    // 公共弹框方法
    function popMsg (content, ajaxUrl) {
        $.pop({
            title: '提示',
            content: content,
            btn: ['确认', '取消'],
            size: 'sm',
            yes: function () {
                // $.getData({
                //     url: ajaxUrl,
                //     query: {}
                // }, function(data) { 
                //     alert(123);
                // });
            }
        });
    }
});
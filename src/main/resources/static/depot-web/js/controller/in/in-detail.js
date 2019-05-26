
$(function () {
    var currPage = 1;
    var currPageRKXQ = 1;
    // 页面加载时的公共方法
    function init() {
        getContent();
        getContentList();
        // 获取入库单详情
    
        getInList();
    }
    init();

    // 获取页面所需数据
    function getContent() {
        var types = $.getQueryString('type');
        if (types == 1) { // 未入库
            $('.storageBtn').removeClass('hide');
            $('.undoBtn').removeClass('hide');
            $('.notStorage').removeClass('hide');
        } else if (types == 2) { // 部分入库
            $('.storageBtn').removeClass('hide');
            $('.partStorage').removeClass('hide');
        } else if (types == 3) { // 全部入库
            $('.allStorage').removeClass('hide');
        }

        // 获取详情数据
        $.getData({
            url: 'depotInManage/depotDetil',
            query: {
                id: $.getQueryString('id')
            }
        }, function (data) {
            renderCont(data);
        });
    }

    // 获取用料明细列表数据
    function getContentList() {
        $.getData({
            url: 'depotInManage/depotDetilMat',
            query: {
                id: $.getQueryString('id'),
                pageNum:currPage,
                pageSize:10
            }
        }, function (data) {
            renderContList(data);
            $.renderPage({
                count: data.count,
                curr: currPage,
                elem: '.pages1',
                jump: function(n) {
                    currPage = n;
                    getContent();
                }
            });
        });
    }
    // 渲染数据
    function renderCont(data) {
        data.type = $.getQueryString('type');
        $('#detailCont').html(template('in/in-detail', {
            items: data
        }));
        
    }

    // 渲染列表数据
    function renderContList(data){
        $("#materielList").html(template('in/in-detail-list', {
            items: data.list
        }));
    }

    // 获取入库单记录列表
    function getInList() {
        var query = {},body = {};
            query = {
                id: $.getQueryString('id'),
                pageNum: currPage,
                pageSize: 10
            }
        $.getData({
            url: 'depotInManage/depotDetilOperation',
            query: query
        }, function(data) { 
            $.renderPage({
                count: data.count,
                curr: currPage,
                elem: '.pages2',
                jump: function(n) {
                    currPage = n;
                    getInList();
                }
            });
            if (data.list && data.list.length) {
                renderList(data.list);
            } else {
                renderOutEmpty();
            }
        });
    }
    // 渲染列表
    function renderList(data) {
        $('#ingoingList').html(template('in/in-warehouse-list', {
            items: data
        }));
    }
    // 渲染无数据
    function renderOutEmpty() {
        $('#ingoingList').html(template('common/record-empty', {
            colspan: 10
        }));
    }
    // 入库记录详情
    function getIngoingList(obj) {
        var param = {};
        param.id = obj.selfId;
        param.pageNum = currPageRKXQ;
        param.pageSize = 10;
        $.getData({
            url: 'depotInManage/depotDetilOperationMatPag',
            query: param
        }, function(data) { 
            
            if (data.list && data.list.length) {
                renderIngoing(obj, data.list);
                $.renderPage({
                    count: data.count,
                    curr: currPageRKXQ,
                    jump: function(n) {
                        currPageRKXQ = n;
                        getIngoingList(obj, data.list);
                    }
                });
            } else {
                renderIngoing(obj, data.list);
            }
        });
    }
    function renderIngoing(obj, list) {
        $.pop({
            content: template('in/in-going', {obj: obj, items: list}),
            title: '操作详情',
            isCancel: false,
            sureText: '关闭',
            noIcon:true,
            yes: function() {
                
            }
        });
    }
    
    // 点击取消
    $(document).on('click', '#cancel', function(e){
        // window.history.go(-1);
        window.location.href = "./in-list.html";
    });

    // 点击入库
    $(document).on('click', '.storageBtn', function(e){
        window.location.href = "./edit-in.html?id="+$.getQueryString('id');
    });

    // 点击入库记录的查看详情
    $(document).on('click', '.checkDetail', function(e){  
        var self = $(this);
        var obj = {};
        obj.selfId = self.attr("ids");
        obj.operationName = self.attr('names');
        obj.operationTime = self.attr('times');
        obj. operationType = self.attr('types');
        getIngoingList(obj);
    });
    
    // 点击撤消
    $(document).on('click', '.undoBtn', function(){
        var self = $(this);
        $.pop({
            title: '提示',
            content: '确定撤销当前入库单？',
            btn: ['确认', '取消'],
            size:'sm',
            yes: function () {
                $.getData({
                    url: 'depotInManage/deleteDepot',
                    query: {
                        id: $.getQueryString('id')
                    }
                }, function (data) {
                    setTimeout(function(){
                        window.location.href = "./in-list.html";
                    },100);
                });
            }
        });

    });

    // 用料明细和出库记录切换
    $(document).on('click', '.title-list li', function(e){
        var self = $(this);
        var selfIndex = self.index();
        self.addClass('curr').siblings().removeClass('curr');
        $('.contentList').find('.list').eq(selfIndex).show().siblings().hide();
        // if (selfIndex == 0) {
        //     $('.contentList').find('.list').eq(1).find('.pages').remove();
        // } else {
        //     $('.contentList').find('.list').eq(0).find('.pages').remove();
        // }
        // $('.contentList').find('.list').eq(index).appendTo('<nav class="text-right pages"></nav>');
        // $('.contentList').find('.list').eq(selfIndex).find('.pages').show();
    }); 
});

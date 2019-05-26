
$(function () {
    var currPage = 1;
    var currPageYL = 1;
    var currPageCKXQ = 1;
    var infoId = $.getQueryString('infoId');
    function init() {
        getContent();
        changeTitle();
        // 获取出库单说情
        getOutList();
        // 点击返回
        $(document).on('click', '.js-back', function(e){
            history.go(-1);
        }); 
        // 点击出库
        $(document).on('click', '.js-ck', function(e){
            location.href = "./out-edit.html?infoId=" + infoId
        }); 
        // 用料明细和出库记录切换
        $(document).on('click', '.title-list li', function(e){
            var self = $(this);
            var selfIndex = self.index();
            self.addClass('curr').siblings().removeClass('curr');
            $('.contentList').find('.list').eq(selfIndex).show().siblings().hide();
        }); 
        // 点击选择采购单
        $(document).on('click', '.checkDetail', function(e){  
            var self = $(this);
            var obj = {};
            obj.selfId = self.attr("ids");
            obj.operationName = self.attr('names');
            obj.operationTime = self.attr('times');
            obj. operationType = self.attr('types');
            getOutgoingList(obj);
        });
        // 点击撤销
        $(document).on('click', '.js-cx', function() {
            $.pop({
                title: '提示',
                content: '确认撤销当前出库单？',
                btn: ['确认', '取消'],
                size:'sm',
                yes: function () {
                    revoke();
                }
            });
         }); 
    }
    init();
     //撤销
     function revoke(){
        $.getData({
            url: 'depotOutManage/deleteDepot',
            query: {
                id: infoId
            }
        }, function (data) {
            if (data) {
                $.msg('操作成功', {
                    time: 2000
                });
                // $(".js-title h4").html("用料申请-已撤回");
                // $(".js-btn3").removeClass("hide");$(".js-btn1,.js-btn2").addClass("hide");  
                location.href = "./out-list.html"
            } else {
                $.alert('操作失败');
            }
        });
    }
    // 获取页面所需数据
    function getContent() {
        $.getData({
            url: 'depotOutManage/depotDetil',
            query: {
                id:infoId,
                pageNo:currPageYL,
                pageSize:10
            }
        }, function (data) {
            // 引入模板
            renderCont(data);
            $.renderPage({
                count: data.warehouseOutMats.count,
                curr: currPageYL,
                elem: '.pages1',
                jump: function(n) {
                    currPageYL = n;
                    getContent();
                }
            });

        });
        
    }
    // 渲染数据
    function renderCont(data) {
        $('#detailCont').html(template('detail/out-detail', {
            items: data
        }));
        if(data.warehouseOutMats && data.warehouseOutMats.list.length > 0){
            $("#materielList").html(template('detail/out-detail-list', {
                items: data.warehouseOutMats.list
            }));
        } else {
            renderEmpty()
        }
       
    }
      // 渲染无数据
      function renderEmpty() {
        $('#materielList').html(template('common/record-empty', {
            colspan: 5
        }));
    }
    // 根据url参数修改页面标题及其他内容
    function changeTitle(){
        var status = $.getQueryString('status');
        if(status == 1){
            $(".js-title h4").html("出库单详情-未出库");
            $(".js-btn1").removeClass("hide");$(".js-btn2,.js-btn3").addClass("hide");
        } else if(status == 2){
            $(".js-title h4").html("出库单详情-部分出库");
            $(".js-btn2").removeClass("hide");$(".js-btn1,.js-btn3").addClass("hide");
        } 
        else {
            $(".js-title h4").html("出库单详情-全部出库");
            $(".js-btn3").removeClass("hide");$(".js-btn1,.js-btn2").addClass("hide");
        }
    }

    
    // 获取出库单记录列表
    function getOutList() {
        var query = {},body = {};
            query = {
                id: infoId,
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
                    getOutList();
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
        $('#outgoingList').html(template('out/out-list', {
            items: data
        }));
    }
    // 渲染无数据
    function renderOutEmpty() {
        $('#outgoingList').html(template('common/record-empty', {
            colspan: 10
        }));
    }
    // 出库记录详情
    function getOutgoingList(obj) {
        console.log(obj);
        var param = {};
        param.id = obj.selfId;
        param.pageNum = currPageCKXQ;
        param.pageSize = 10;
        $.getData({
            url: 'depotInManage/depotDetilOperationMatPag',
            query: param
        }, function(data) { 
            
            if (data.list && data.list.length) {
                renderOutgoing(obj, data.list);
                $.renderPage({
                    count: data.count,
                    curr: currPageCKXQ,
                    jump: function(n) {
                        currPageCKXQ = n;
                        getOutgoingList(obj, data.list);
                    }
                });
            } else {
                renderOutgoing(obj, data.list);
            }
        });
    }
    function renderOutgoing(obj, list) {
        $.pop({
            content: template('out/out-going', {obj: obj, items: list}),
            title: '操作详情',
            isCancel: false,
            sureText: '关闭',
            noIcon:true,
            yes: function() {
                
            }
        });
    }
});

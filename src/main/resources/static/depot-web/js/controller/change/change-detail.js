$(function () {

    // 页面加载时的公共方法
    function init() {
        getContent();
    }
    init();
    
    // 定义公共的对象，渲染页面使用
    var tableObj = {};
    var detailObj = {};

    // 获取页面所需数据
    function getContent() {

        var types = $.getQueryString('type');
        if (types == 1) { // 未调拨
            $('.allocationBtn').removeClass('hide');
            $('.withdrawBtn').removeClass('hide');
            $('.notAllocation').removeClass('hide');
        } else if (types == 2) { // 部分调拨
            $('.allocationBtn').removeClass('hide');
            $('.partAllocation').removeClass('hide');
        } else if (types == 3) { // 全部调拨
            $('.allAllocation').removeClass('hide');
        }
        // 获取详情数据
        $.getData({
            url: 'allot/detail',
            query: {
                id: $.getQueryString('id')
            }
        }, function (data) {
            console.log(data);
            renderCont(data);
        });
        
    }
    // 渲染数据
    function renderCont(data) {
        $('#detailCont').html(template('change/change-detail', {
            items: data
        }));
        if (data.matVoList.length>0) {
            $("#materielList").html(template('change/change-detail-list', {
                // items: data.matVoList
                items: data.matVoList
            }));
        } else {
            $("#materielList").html(template('common/record-empty', {
                colspan: 6
            }));
        }
        
        // if (data.suppliesDetails == null){
        //     $("#materielList").html(template('common/record-empty', {
        //         colspan: 6
        //     }));
        // } else {
        //     $("#materielList").html(template('in/materiel-list', {
        //         items: data
        //     }));
        // }
        
    }
    
    // 点击取消
    $(document).on('click', '#cancelBtn', function(e){
        window.history.go(-1);
    });

    // 点击取消
    $(document).on('click', '.img', function(e){
        var self = $(this);
        window.open(self.prop('src'));
    });

    // 点击调拨
    $(document).on('click', '.allocationBtn', function(e){
        window.location.href = "./edit-change.html?id="+$.getQueryString('id');
    });
    
    // 点击撤消
    $(document).on('click', '.withdrawBtn', function(){
        var self = $(this);
        $.pop({
            title: '提示',
            content: '确定撤回此申请？',
            btn: ['确认', '取消'],
            size:'sm',
            yes: function () {
                $.getData({
                    url: '/allot/del',
                    query: {
                        id: $.getQueryString('id')
                    }
                }, function (data) {
                    setTimeout(function(){
                        window.location.href = "./change-list.html";
                    },100);
                });
            }
        });

    });
    
});

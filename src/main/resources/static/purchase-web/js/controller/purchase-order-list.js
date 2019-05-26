$(function () {
    var currPage = 1;
    // 收集数据
    function collectData(pageNum) {
        var rs = {};
        rs.pageNum = pageNum || 1;
        rs.pageSize = 10;
        rs.orderNo = $.trim($('#keyWords').val());
        var type = $.trim($('#type').val());
        if (type !== '3'){
            rs.orderType = Number($.trim($('#type').val()));
        }
        var status = $.trim($('#status').val());
        if (status !== '7') {
            rs.orderStat = Number($.trim($('#status').val()));
        }
        return rs;
    }
    // 获取列表
    function getList(pageNum) {
        // 收集数据
        var data = collectData(pageNum);
        if (data) {
            $.getData({
                url: '/order/searchList',
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
                    for (var i=0;i<data.list.length;i++){
                        data.list[i].number1 = i + 1;
                    }
                    renderList(data.list);
                } else {
                    $('.pages').css('display', 'none');
                    renderEmpty();
                }
            });
        }
    }
    // 冒泡个数
    function getCount(){
        $.getData({
            url: '/order/demondCount'
        }, function (data) {
            numCount = data;
            if (numCount == 0){
                $('.sign').css('display', 'none');
            } else {
                $('.sign').css('display', 'inline-block');
                $('.sign').html(data);
            }
        });
    }
    // 渲染列表
    function renderList(data) {
        $('.js-list').html(template('purchase/purchase-order-list', {
            items: data
        }));
    }
    // 渲染无数据
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 10
        }));
    }
    // 撤销
    function revoke(id){
        $.getData({
            url: '/order/revocation',
            query: {
                id: id
            }
        }, function (data) {
            if (data) {
                $.toast('操作成功', {
                    timer: 2000
                });
                getList(currPage);
            } else {
                $.alert('操作失败');
            }
        });
    }
    // 开始采购
    function start(el, id) {
        $.getData({
            url: '/order/beginPurchase',
            query: {
                id: id
            }
        }, function (data) {
            if (data) {
                $.toast('操作成功', {
                    timer: 2000
                });
                el.parent().html('<a class = "underline">完成采购</a>');
                getList(currPage);
            } else {
                $.alert('操作失败');
            }
        });
    }
    // 完成采购
    function complete(id) {
        $.getData({
            url: '/order/complatePurchase',
            query: {
                id: id
            }
        }, function (data) {
            if (data) {
                $.toast('操作成功', {
                    timer: 2000
                });
                getList(currPage);
            } else {
                $.alert('操作失败');
            }
        });
    }
    function init() {
        getCount();
        // 点击跳转详情
        $(document).on('click', '.js-detail', function(){
            var ss = $(this).attr('orderstat');
            var id = $(this).attr('id-main');
            var orderType = Number($(this).attr('ordertype'));
            if(orderType == 1){ //需求订单
                if(ss == 1){
                    window.location.href = './purchase-order-details.html?status=dsh&orderId='+id;
                } else if (ss == 2){
                    window.location.href = './purchase-order-details.html?status=yjj&orderId=' + id;
                } else if(ss == 3){
                    window.location.href = './purchase-order-details.html?status=ytg&orderId=' + id;
                } else if (ss == 4){
                    window.location.href = './purchase-order-details.html?status=cgz&orderId=' + id;
                } else if (ss == 5) {
                    window.location.href = './purchase-order-details.html?status=ywc&orderId=' + id;
                } else if (ss == 6) {
                    window.location.href = './purchase-order-details.html?status=ych&orderId=' + id;
                }
            } else if(orderType == 2) { //主动采购
                if(ss == 1){
                    window.location.href = './purchase-details.html?status=dsh&orderId='+id;
                } else if (ss == 2){
                    window.location.href = './purchase-details.html?status=yjj&orderId=' + id;
                } else if(ss == 3){
                    window.location.href = './purchase-details.html?status=ytg&orderId=' + id;
                } else if (ss == 4){
                    window.location.href = './purchase-details.html?status=cgz&orderId=' + id;
                } else if (ss == 5) {
                    window.location.href = './purchase-details.html?status=ywc&orderId=' + id;
                } else if (ss == 6) {
                    window.location.href = './purchase-details.html?status=ych&orderId=' + id;
                }
            }
        });
        // 搜索
        $(document).on('click', '.js-search', function () {
            currPage = 1;
            getList();
        });
        // 点击撤销
        $(document).on('click', '.js-cancle', function (){
            var that = $(this);
            $.pop({
                size: 'sm',
                title: '提示',
                content: '确认要撤回吗？',
                btn: ['确认', '取消'],
                yes: function () {
                    revoke(that.attr('data-id'));
                }
            });
        });
        // 点击开始采购
        $(document).on('click', '.js-start', function () {
            var that = $(this);
            $.pop({
                size: 'sm',
                title: '提示',
                content: '确认开始采购吗？',
                btn: ['确认', '取消'],
                yes: function () {
                    start(that, that.attr('data-id'));
                }
            });
        });
        // 点击完成采购
        $(document).on('click', '.js-compelet', function () {
            var that = $(this);
            $.pop({
                size: 'sm',
                title: '提示',
                content: '确认完成采购吗？',
                btn: ['确认', '取消'],
                yes: function () {
                    complete(that.attr('data-id'));
                }
            });
        });
        // 点击新建订单
        $(document).on('click', '.js-add', function (){
            location.href = './purchase-add.html';
        });
        // 点击需求处理
        $(document).on('click', '.js-xuqiuchuli', function(){
            location.href = './demand-process.html';
        });
        // 点击编辑
        $(document).on('click', '.js-edit', function(){
            var id = $(this).attr('data-id');
            var status = $(this).attr('ordertype1');
            if(status == 1) {
                location.href = './purchase-order-details.html?status=edit&orderId='+id;
            } else{
                location.href = './purchase-add.html?infoId='+id;
            }
        });
        getList();
        $.renderPage({ count: 100 });
    }
    init();
});

$(function () {

    // 页面加载时的公共方法
    function init() {
        getContent();
        getStatus($.getQueryString('status'));
    }
    init();

    // 判断不同的参数显示不同的操作按钮
    function getStatus (status) {
        switch(status)
        {
            case 'dsh':
            $("#activeOperation").removeClass("hide");
            break;

            case 'ytg':
            $("#adoptOperation").removeClass("hide");
            break;

            case 'yjj':
            $("#refuseOperation").removeClass("hide");
            break;

            case 'cgz':
            $("#procurementOperation").removeClass("hide");
            break;

            case 'ywc':
            $("#completeOperation").removeClass("hide");
            break;

            case 'ych':
            $("#completeOperation").removeClass("hide");
            break;
        }
    }

    // 获取页面所需数据
    function getContent() {
        // 获取详情数据
        $.getData({
            url: '/order/orderDetail',
            query: {
                id: $.getQueryString('orderId')
            }
        }, function(data) {
            if (data) {
                console.log('返回值');
                console.log(data);
                data.dept = JSON.parse(data.deptJson)[0].title;
                data.status = $.getQueryString('status');
                renderCont(data);
            }
        });
    }
    
    // 渲染数据
    function renderCont(data) {
        $('#order').html(template('order/detail-purchase', {
            items: data
        }));
        if (data.matDetailVoList == null){
            $("#materielList").html(template('common/record-empty', {
                colspan: 6
            }));
        } else {
            $("#materielList").html(template('order/materiel-list-two', {
                items: data.matDetailVoList
            }));
        }
        
    }
    // 点击取消
    $(document).on('click', '#cancel', function(e){
        window.history.go(-1);
    });
    // 跳转至编辑页面
    $(document).on('click', '#jumpEdit', function(e){
        window.location.href = 'purchase-add.html?infoId='+ $.getQueryString('orderId');
    });

    // 点击撤回按钮
    $(document).on('click', '#withdrawOrder', function(e){
        postMethod('/order/revocation', '撤销成功','确认要撤回吗？','purchase-order-list.html');
    });

    // 点击开始采购按钮
    $(document).on('click', '#startPurchase', function(e){
        postMethod('/order/beginPurchase', '开始采购成功','确认开始采购吗？','purchase-order-list.html');
    });

    // 点击采购完成按钮
    $(document).on('click', '#completePurchase', function(e){
        postMethod('/order/complatePurchase', '采购完成','确认完成采购吗？','purchase-order-list.html');
    });
    
    // 请求的公共方法
    function postMethod(postUrl, title, cont, url) {
        $.pop({
            content: cont,
            isCancel: true,
            size: 'sm',
            yes: function() {
                $.getData({
                    url: postUrl,
                    query: {
                        id: $.getQueryString('orderId')
                    }
                }, function(data) {
                    if (data) {
                        popupMsg(title,url);
                    }
                });
            }
        });
    }

    //弹框公共方法
    function popupMsg(title, url) {
        $.toast(title, {
            timer: 2000
        }, function () {
            window.location.href = url;
        });
    }
});

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
            case 'edit':
            $("#editPurchase, #editTitle, #purchase").removeClass("hide");
            document.title = '编辑采购-需求';
            break;

            case 'dsh':
            $("#activeOperation, #detailTitle").removeClass("hide");
            document.title = '采购详情-待审核';
            break;

            case 'ytg':
            $("#adoptOperation, #detailTitle").removeClass("hide");
            document.title = '采购详情-已通过';
            break;

            case 'yjj':
            $("#refuseOperation, #detailTitle").removeClass("hide");
            document.title = '采购详情-已拒绝';
            break;

            case 'cgz':
            $("#procurementOperation, #detailTitle").removeClass("hide");
            document.title = '采购详情-采购中';
            break;

            case 'ywc':
            $("#completeOperation, #detailTitle").removeClass("hide");
            document.title = '采购详情-已完成';
            break;

            case 'ych':
            $("#completeOperation, #detailTitle").removeClass("hide");
            document.title = '采购详情-已撤回';
            break;
        }
    }
    // 定义公共的对象，渲染页面使用
    var tableObj = {};
    var detailObj = {};

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
                data.orderDate = data.orderDate;
                tableObj.matDetailVoList = data.matDetailVoList;
                detailObj = data;
                var obj = {};
                obj.detail = data;
                // 获取供应商
                $.getData({
                    url: '/common/getComList'
                }, function(data) {
                    if (data) {
                        console.log('返回值');
                        console.log(data);
                        tableObj.comList = data;
                        // 引入模板
                        obj.detail.comList = data;
                        renderCont(obj.detail);
                    }
                });
            }
        });
    }
    // 渲染数据
    function renderCont(data) {
        $('#order').html(template('order/order-information-two', {
            items: data
        }));
        if (data.matDetailVoList == null){
            $("#materielList").html(template('common/record-empty', {
                colspan: 6
            }));
        } else {
            $("#materielList").html(template('order/materiel-list-three', {
                items: data
            }));
        }
        
    }
    // 点击采购材料
    $(document).on('click', '#purchase', function(e){
        $.pop({
            title: '采购物料',
            content: template('order/updat-material-two', {
                items: tableObj
            }),
            size: 'lg',
            isCancel: true,
            yes: function() {
                // 点击确实后，重新渲染物料明细表格的数据
                $('#tableList tbody tr').each(function(index, item){
                    var self = $(this);
                    var thisNum = self.find('.numbers');
                    var thisCom = self.find(".comList  option:selected");
                    tableObj.matDetailVoList[index].buyCount = thisNum.val();
                    tableObj.matDetailVoList[index].supplyComId = thisCom.prop('id');
                    tableObj.matDetailVoList[index].supplyComName = thisCom.text();
                });
                // 修改数据后，重新渲染表格数据
                console.log('修改数据后，重新渲染表格数据');
                console.log(tableObj);
                $("#materielList").html(template('order/materiel-list-three', {
                    items: tableObj
                }));
            }
        });
        
    });
    // 修改个数
    $(document).on('keyup', '.numbers', function(){
        var self = $(this);
        var thisLargeNumber = self.attr('applyCount');
        if (parseInt(self.val()) > thisLargeNumber ) {
            self.val(thisLargeNumber); 
        }
        if (parseInt(self.val()) < 0 ) {
            self.val("0");
        }
         if (!checkNumber(self.val())) {
            $.toast("请输入正整数",{timer:2000});
            self.val(0);
            return false;
        }
    });
    // 正则验证是否数字
    function checkNumber(theObj) {
        var reg = /^[0-9]+$/;
        if (reg.test(theObj)) {
            return true;
        }
        return false;
    }
    // 点击取消
    $(document).on('click', '#cancel', function(e){
        // window.history.go(-1);
        window.location.href= "./purchase-order-list.html";
    });
    // 点击保存
    $(document).on('click', '#saveOrder', function(e){
        var submitObj = {};
        submitObj.applyNo = detailObj.applyNo;
        submitObj.approveDate = detailObj.approveDate;
        submitObj.approveOption = detailObj.approveOption;
        submitObj.buyerEmpId = detailObj.buyerEmpId;
        submitObj.buyerEmpName = detailObj.buyerEmpName;
        submitObj.id = detailObj.id;
        submitObj.orderDate = detailObj.orderDate;
        submitObj.orderEmpId = detailObj.orderEmpId;
        submitObj.orderEmpName = detailObj.orderEmpName;
        submitObj.orderNo = detailObj.orderNo;
        submitObj.orderStat = detailObj.orderStat;
        submitObj.orderType = detailObj.orderType;
        submitObj.matDetailParamList = tableObj.matDetailVoList;
        
        $.getData({
            url: '/order/addOrder',
            body: submitObj
        }, function(data) {
            if (data) {
                console.log('提交值');
                console.log(data);
                window.location.href = "../purchase-order-list.html";
            }
        });
    });
     // 点击取消
     $(document).on('click', '#cancel', function(e){
        window.history.go(-1);
    });
    // 跳转至编辑页面
    $(document).on('click', '#jumpEdit', function(e){
        window.location.href = 'purchase-order-details.html?status=edit&orderId='+ $.getQueryString('orderId');
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
            size:'sm',
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

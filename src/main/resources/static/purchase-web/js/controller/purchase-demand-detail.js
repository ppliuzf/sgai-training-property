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
            $('#editBtn, #editStatus').removeClass('hide');
            break;

            case 'show':
            $('#showBtn').removeClass('hide');
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
            url: 'suppliesOperation/suppliesDetil',
            query: {
                id: $.getQueryString('orderId')
            }
        }, function(data) {
            if (data) {
                console.log('返回值');
                console.log(data);
                tableObj.suppliesDetails = data.suppliesDetails;
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
        $('#order').html(template('order/order-information', {
            items: data
        }));
        if (data.suppliesDetails == null){
            $("#materielList").html(template('common/record-empty', {
                colspan: 6
            }));
        } else {
            $("#materielList").html(template('order/materiel-list', {
                items: data
            }));
        }
        
    }
    // 点击采购材料
    $(document).on('click', '#purchase', function(e){
        $.pop({
            title: '采购物料',
            content: template('order/updat-material', {
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
                    tableObj.suppliesDetails[index].buyCount = parseInt(thisNum.val());
                    tableObj.suppliesDetails[index].supplyComId = thisCom.prop('id');
                    tableObj.suppliesDetails[index].supplyComName = thisCom.text();
                });
                // 修改数据后，重新渲染表格数据
                console.log('修改数据后，重新渲染表格数据');
                console.log(tableObj);
                $("#materielList").html(template('order/materiel-list', {
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
        // if (isNaN(parseInt(self.val()))) {
        //     $.msg("请输入正整数");
        //     self.val(0);
        // }
        // self.val(parseInt(self.val()));
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
    $(document).on('click', '#cancel1, #cancel2', function(e){
        window.history.go(-1);
    });
    // 点击处理
    $(document).on('click', '#handleOrder', function(e){
        window.location.href="purchase-demand-detail.html?status=edit&orderId="+$.getQueryString('orderId'); 
    });
    // 点击保存
    $(document).on('click', '#saveOrder', function(e){
        var submitObj = {};
        submitObj.applyNo = detailObj.applyNo;
        submitObj.orderType = 1;
        submitObj.matDetailParamList = tableObj.suppliesDetails;
        $.getData({
            url: 'order/addOrder',
            body: submitObj
        }, function(data) {
            if (data) {
                console.log('提交值');
                console.log(data);
                window.location.href = 'purchase-order-list.html';
            }
        });
    });
});

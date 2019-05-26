$(function () {
    var currPage = 1;
    var orderId = $.getQueryString('orderId');
    var inTypes = 0;  // 出库类型（0：调拨, 1:采购）
    var oddNumbers; //单号
    var ylArr = []; //用料明细初始值
    function init() {
        // 页面加载时，移除session中的采购单数组
        window.sessionStorage.removeItem('purchaseOrder');
        // 移除调拨单数组
        window.sessionStorage.removeItem('allocationList');
        // 移除实仓的类型数组
        window.sessionStorage.removeItem('entityWarehouse');
        // 移除虚仓的类型数组
        window.sessionStorage.removeItem('fictitiousWarehouse');
        
        getContent();
    };
    init();
    // 获取页面加载所需数据
    function getContent() {
        var sessionEntityWarehouse = JSON.parse(window.sessionStorage.getItem('entityWarehouse'));
        if (sessionEntityWarehouse) {
            // 引入模板
            renderCont(sessionEntityWarehouse);
        } else {
            $.getData({
                url: 'common/byWhType',
                query: {
                    whType: 1
                }
            }, function(data) {
                if (data) {
                    console.log('仓库内容');
                    console.log(data);
                    // 引入模板
                    renderCont(data);
                    window.sessionStorage.setItem('entityWarehouse', JSON.stringify(data));
                }
            });
        }
    }
    // 调用模板
    function renderCont(data) {
        $('#addIn').html(template('in/add-in', {
            items: data
        }));
    }

    // 点击选择调拨入库单
    var allocationList = [];
    $(document).on('click', '#choiseAllocation', function(e){
        var sessionAllocationList = JSON.parse(window.sessionStorage.getItem('allocationList'));
        if (sessionAllocationList) {
            renderOrderList(sessionAllocationList,'选择调拨单','dbd');
        } else {
            $.getData({
                url: 'allot/searchList4InOut',
                query:{
                    hasOrder:1
                }
            }, function(data) {
                if (data) {
                    data.forEach(function(item){
                        item.isChecked = false;
                    });
                    allocationList = data;
                    console.log('调拨单内容');
                    console.log(allocationList);
                    renderOrderList(allocationList,'选择调拨单','dbd');
                    window.sessionStorage.setItem('allocationList', JSON.stringify(allocationList));
                }
            });
        } 
    });

    // 点击选择采购单
    var purchaseOrder = [];
    $(document).on('click', '#choiseOrder', function(e){
        var sessionPurchaseOrder = JSON.parse(window.sessionStorage.getItem('purchaseOrder'));
        if (sessionPurchaseOrder) {
            renderOrderList(sessionPurchaseOrder,'选择采购单', 'cgd');
        } else {
            $.getData({
                url: 'order/searchList',
                query: {
                    pageNum: 1,
                    pageSize: 10000,
                    outOrIn: 0 //入库传0，出库传1
                }
            }, function(data) {
                if (data) {
                    data.list.forEach(function(item){
                        item.isChecked = false;
                    });
                    purchaseOrder = data.list;
                    console.log('采购单内容');
                    console.log(purchaseOrder);
                    renderOrderList(purchaseOrder,'选择采购单', 'cgd');
                    window.sessionStorage.setItem('purchaseOrder', JSON.stringify(purchaseOrder));
                }
            });
        } 
    });

    // 采购或调拨单弹窗
    function renderOrderList(orderList, title, type) {
        var templateList = ''
        if (type == 'dbd') { // 调拨单
            templateList = 'in/allocation-list';
        } else if (type == 'cgd') { // 采购单
            templateList = 'in/order-list';
        }
        if (orderList.length) {
            $.pop({
                content: template(templateList, {items: orderList}),
                title: title,
                noIcon:true,
                yes: function() {
                    var selectedLen = 0;
                    var selectedName = '';
                    var selectedIndex = '';
                    var selectedId = '';
                    $('.warn-btn').each(function(index, item) {
                        var self = $(this);
                        // alert($this.prop('checked'));
                        if (self.prop('checked')==true) {
                            selectedLen++;
                            selectedName = self.parent('.form-group').find('span').text();
                            selectedId = self.attr('ids');
                    
                            selectedIndex = index;
                            if (type == 'dbd') { // 调拨单
                                allocationList[index].isChecked = true;
                            } else if (type == 'cgd') { // 采购单
                                purchaseOrder[index].isChecked = true;
                            }
                        }
                    });
                    
                    if (selectedLen) {
                        if (type == 'dbd') { // 调拨单
                            window.sessionStorage.setItem('allocationList', JSON.stringify(allocationList));
                            $('#transferOrder').val(selectedName);
                            $('#transferOrder').attr('ids',selectedId);
                            
                            oddNumbers = $('.js-materiel-list input:checked').attr('ids');
                            renderList(oddNumbers, 0);
                        } else if (type == 'cgd') { // 采购单
                            window.sessionStorage.setItem('purchaseOrder', JSON.stringify(purchaseOrder));
                            $('#warehouseReceipt').val(selectedName);
                            $('#warehouseReceipt').attr('ids',selectedId);
                            oddNumbers = $('.js-materiel-list input:checked').attr('data-typeid');
                            renderList(oddNumbers, 1);
                        }
                        $('#addInList').removeClass('hide');
                    } else {
                        if (type == 'dbd') { // 调拨单
                            $.toast('请选择采购单',{timer:2000});
                        } else if (type == 'cgd') { // 采购单
                            $.toast('请选择调拨单',{timer:2000});
                        } 
                        $('#addInList').addClass('hide');
                        return false;
                    }
                }
            });
        }
    }

    // 渲染无数据
    function renderEmpty(col) {
        $('.js-wuliao-list').html(template('common/record-empty', {
            colspan: col
        }));
    }

     // 获取用料明细的列表数据
     function renderList(orderNumber, list) { // list:0代表调拨，1代表用料
        var query = {};
        query = {
            pageNum: currPage,
            pageSize: 10,
            isAllot: list,
            orderNo: orderNumber
        }
        
        $.getData({
            url: '/order/searchWarehousMatList',
            query: query

        }, function(data) {
            ylArr = data.list;
            var listHtml;
            listHtml = 'in/add-allocation-list';
            $('#addInList').html(template(listHtml, {
                items: data.list,
                type: list
            }));
            

            $.renderPage({
                count: data.count,
                curr: currPage,
                jump: function(n) {
                    currPage = n;
                    renderList(orderNumber, list);
                }
            });
        });
    }
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
    // 出库数量聚焦时如果为0清空内容
    $(document).on('focus','.numbers', function() {
        if ($(this).val() == 0) {
            $(this).val('')
        }
    });
    // 出库数量失去焦点保存数据
    $(document).on('blur', '.numbers', function () {
        var self = $(this);
        var selfVal = self.val();
        if (selfVal == '') {
            self.val(0)
        };
        $.getData({
            url: 'common/instantSaving',
            body: {
                id: self.parents("tr").find('td').eq(0).attr('id'),
                matRealNum: self.val()
            }
        }, function (data) {
            if (data) {
                // $.msg("出库数量已保存");        
            }
        });
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
        window.location.href = "./in-list.html";
    });
    // 点击保存
    $(document).on('click', '#save', function(e){
        var isSubmit = false;
        if ($('#warehouseReceipt').val() == '' && $('#transferOrder').val() == '') {
            $.toast('选择调拨单或采购单后保存',{timer:2000});
            isSubmit = false;
            return false;
        } else {
            isSubmit = true;
        }
        
        if (isSubmit) {
            var body= {};

            body.allotId = $('#transferOrder').attr('ids'); // 调拨单id
            body.allotName = $('#transferOrder').val();  // 调拨单名称

            body.orderId = $('#warehouseReceipt').val(); // 采购单id
            body.orderName = $('#warehouseReceipt').val();  // 采购单名称

            body.whId = $("#warehouse option:selected").attr('id'); // 仓库id
            // body.whInNo = ''; // 入库单号
            
            body.whInType = $("#storageType option:selected").val(); // 入库类型？1 调拨入库；2 采购入库
            body.whName  = $("#warehouse option:selected").text(); // 仓库名称
            body.whType = $("#warehouseType option:selected").val(); // 仓库类型
            
            var suppliesDetails = [];
            var isNonTransfer; // 未调拨
            var isAllTransfer; // 全部调拨
            $('.js-wuliao-list tr').each(function(index, item){
                var self = $(this);
                var objList = {};
                
                objList.matName = self.find(".matName").html();
                objList.matNeetNum = self.find(".buyCount").html(); // 所需数量
                objList.matRealNum = self.find('.numbers').val(); // 实际数量
                objList.matSpec = self.find('.orderNumber').attr("matSpec"); // 物料规格
                objList.matTypeCode = self.find('.orderNumber').attr("matTypeCode"); // 物料型号
                objList.matTypeId = self.find('.orderNumber').attr("matTypeId"); // 物料型号


                suppliesDetails.push(objList);
                
                // 未调拨判断
                if (isNonTransfer == false){
                    isNonTransfer = false;
                    // return false;
                } else {
                    if (objList.matRealNum == 0) {
                        isNonTransfer = true;
                    } else {
                        isNonTransfer = false;
                    }
                }

                // 全部调拨判断
                if (isAllTransfer == false) {
                    isAllTransfer = false;
                    // return false;
                } else {
                    if (parseInt(objList.matNeetNum) == parseInt(objList.matRealNum)) {
                        isAllTransfer = true;
                    } else {
                        isAllTransfer = false;
                    }
                }
            });

            if (ylArr.length ==0) {
                $.toast('请选择有用料明细的数据进行提交', { timer: 2000 });
                return false;
            }

            // 入库状态
            if (isNonTransfer) {
                body.whInStat = 1;
            } else if (isAllTransfer) {
                body.whInStat = 3;
            } else {
                body.whInStat = 2;
            }
            body.suppliesDetailsId = $('.js-wuliao-list').find('.numbers').eq(0).attr('orderid');
            
            // body.suppliesDetails = suppliesDetails; // 物料
            console.log(body);
            // return false;
            $.getData({
                url: 'depotInManage/addDepot',
                body: body
            }, function(data) {
                if (data) {
                    console.log('提交后返回');
                    console.log(data);
                    window.location.href= './in-list.html'
                }
            });
        }
        
    });
    // 入库类型选择
    $(document).on('change', '#storageType', function(e){
        var self = $(this);
        $('#addInList').addClass('hide');
        $('#warehouseReceipt').val('');
        $('#transferOrder').val('');

        if(self.val() == 1){
            $("#allocationList").removeClass("hide");
            $("#purchaseList").addClass("hide");
        } else {
            $("#purchaseList").removeClass("hide");
            $("#allocationList").addClass("hide");
        }
        // 切换入库单类型时，把session中的采购单数组的选中项去除
        var sessionPurchaseOrders =  JSON.parse(window.sessionStorage.getItem('purchaseOrder'));
        if (sessionPurchaseOrders) {
            sessionPurchaseOrders.forEach(function(item){
                item.isChecked = false;
            }); 
        }
        window.sessionStorage.setItem('purchaseOrder', JSON.stringify(sessionPurchaseOrders));
        // 切换入库单类型时，把session中的调拨单数组的选中项去除
        var sessionAllocationLists =  JSON.parse(window.sessionStorage.getItem('allocationList'));
        if (sessionAllocationLists) {
            sessionAllocationLists.forEach(function(item){
                item.isChecked = false;
            });
        }
        window.sessionStorage.setItem('allocationList', JSON.stringify(sessionAllocationLists));
        $('.js-wuliao-list').html('');
    });
    // 仓库类型选择
    $(document).on('change', '#warehouseType', function(e){
        var self = $(this);
        var selfVal = self.val();
        if (selfVal == 1) { // 实仓
            var sessionEntityWarehouse = JSON.parse(window.sessionStorage.getItem('entityWarehouse'));
                // 修改实仓模板的内容
                renderCont(sessionEntityWarehouse);
        } else if (selfVal == 2) { // 虚仓
            // fictitiousWarehouse
            var sessionFictitiousWarehouse = JSON.parse(window.sessionStorage.getItem('fictitiousWarehouse'));
            if (sessionFictitiousWarehouse) {
                // 修改虚仓模板的内容
                renderCont(sessionFictitiousWarehouse);
            } else {
                $.getData({
                    url: 'common/byWhType',
                    query: {
                        whType: 2
                    }
                }, function(data) {
                    if (data) {
                        console.log('采购单内容111');
                        console.log(data);
                        renderCont(data);
                        window.sessionStorage.setItem('fictitiousWarehouse', JSON.stringify(data));
                    }
                });
            }
        }
    });
    
});

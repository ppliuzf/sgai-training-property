
$(function () {
    var currPage = 1;
    var suppliesDetails = [];
    // 页面加载时的公共方法
    function init() {
        getContent();
        getContentList();
    }
    init();
    
    // 定义公共的对象，渲染页面使用
    var detailCont;
    // 获取页面所需数据
    function getContent() {
        // 获取详情数据
        $.getData({
            url: 'depotInManage/depotDetil',
            query: {
                id: $.getQueryString('id')
            }
        }, function (data) {
            renderCont(data);
            detailCont = data;
        });
    }
    // 获取列表数据
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
        
        $('#detailCont').html(template('in/edit-detail', {
            items: data
        }));
    }
    // 渲染列表数据
    function renderContList(data){
        $("#materielList").html(template('in/edit-in-list', {
            items: data.list
        }));
    }
    
    // 点击取消
    $(document).on('click', '#cancel', function(e){
        // window.history.go(-1);
        window.location.href = './in-list.html';
    });
    // 入库数量聚焦时如果为0清空内容
    $(document).on('focus','.numbers', function() {
        if ($(this).val() == 0) {
            $(this).val('')
        }
    });
    // 入库数量失焦时如果为空赋值为0
    $(document).on('blur','.numbers', function() {
        console.log($(this).val())
        if ($(this).val() == '') {
            $(this).val(0)
        };
    });
    // 修改个数
    $(document).on('keyup', '.numbers', function(){
        var self = $(this);
        var thisLargeNumber = self.attr('max');
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

    // 改变数量
    $(document).on('change', '.numbers', function(e){
        var self = $(this);
        var obj = {};
        var thisId = self.attr('ids');
        obj.id = self.attr('ids');
        obj.matRealNum = self.val();
        // 判断出库数量是否多次修改过同一条数据，如果不是同一条数据，就向数组中添加，如果是同一条数据，就不添加了，直接修改数据
        if (suppliesDetails.length > 0){
            for(var i=0; i<suppliesDetails.length; i++) {
                if (suppliesDetails[i].id == thisId) {
                    suppliesDetails.splice(i, 1);
                }
                // suppliesDetails.push(obj);
                
            }
        }
        suppliesDetails.push(obj);
        console.log('修改后的数据');
        console.log(suppliesDetails);
    }); 
    // 正则验证是否数字
    function checkNumber(theObj) {
        var reg = /^[0-9]+$/;
        if (reg.test(theObj)) {
            return true;
        }
        return false;
    }


    // 点击保存
    $(document).on('click', '#save', function(e){
        
        var body= {};
        body.id = $.getQueryString('id');

        // body.allotId = $('.allocation-list').attr('id'); // 调拨单id
        // body.allotName = $('.allocation-list').html(); // 用料申请单名称

        // body.orderId = $('.purchase-order').attr('id'); // 采购单id
        // body.orderName = $('.purchase-order').html(); // 采购单名称

        // body.whId = $('.warehouse-name').attr('id'); //仓库ID
        // body.whInNo = $('.warehouse-name').attr("whInNo"); // 入库单号
        // body.whInStat = $('.warehouse-name').attr('whInStat');
        if (detailCont == undefined) {
            $.toast('获取数据成功后提交',{timer:2000});
        } else {
            body.allotId = detailCont.allotId; // 调拨单id
            body.allotName = detailCont.allotName; // 用料申请单名称

            body.orderId = detailCont.orderId; // 采购单id
            body.orderName = detailCont.orderName; // 采购单名称

            body.whId = detailCont.whId; //仓库ID
            body.whInNo = detailCont.whInNo; // 入库单号

            
            body.whInType = detailCont.whInType; // 入库类型 

            body.whName = detailCont.whName // 仓库名称
            body.whType = detailCont.whType // 仓库类型

            var isNonTransfer; // 未调拨
            var isAllTransfer; // 全部调拨
            $('.js-list tr').each(function(index, item){
                var self = $(this);
                var objList = {};
                objList.matName = self.find(".matName").html();
                objList.matNeetNum = self.find(".buyCount").html(); // 所需数量
                objList.matRealNum = self.find('.numbers').val(); // 实际数量
                objList.matSpec = self.find('.orderNumber').attr("matSpec"); // 物料规格
                objList.matTypeCode = self.find('.orderNumber').attr("matTypeCode"); // 物料型号
                objList.matTypeId = self.find('.orderNumber').attr("matTypeId"); // 物料型号
                // suppliesDetails.push(objList);
                
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
            if (isNonTransfer) {
                body.whInStat = 1;
            } else if (isAllTransfer) {
                body.whInStat = 3;
            } else {
                body.whInStat = 2;
            }
            body.suppliesDetails = suppliesDetails; // 物料
            if (suppliesDetails.length ==0) { //代表数据没有修改，不让提交
                $.toast('请修改入库数量后再提交',{timer:2000});
                return false;
            }
            console.log(body);
            $.getData({
                url: 'depotInManage/updateDepot',
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
    
});

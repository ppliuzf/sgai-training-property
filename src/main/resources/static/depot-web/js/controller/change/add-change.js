$(function () {
    var orderId = $.getQueryString('orderId');
    function init() {
        // 页面加载时，移除session中的采购单数组
        window.sessionStorage.removeItem('materialList');
        getContent();
        // 上传图片
        $.uploader({
            el: '.shangchuan',
            url: '/uploadDown/uploadImages',
            maxLength: 3,
            imageGroup: 'mm',
            tips: '注 最多上传3张图片，<br />推荐尺寸1080*1080，图片大小不超过10M'
        });
        // 调拨理由字数
        $.counter({
            el:'#reason',
            count: '.conttask'
        });
    };
    init();
    // 获取列表
    function getContent() {
        renderCont({});
    }
    // 调用模板
    function renderCont(data) {
        $('#addIn').html(template('change/add-change', {
            items: data
        }));
        $('#addInList').html(template('change/add-list', {
            items: data
        }));
    }
    // 点击选择用料申请单
    var materialList = [];
    $(document).on('click', '#choiseOrder', function(e){
        var sessionMaterialList = JSON.parse(window.sessionStorage.getItem('materialList'));
        if (sessionMaterialList) {
            renderOrderList(sessionMaterialList);
        } else {
            $.getData({
                url: 'common/suppliesList',
                query: {
                    allotNo: '',
                    pageNum: 1,
                    pageSize: 10000
                }
            }, function(data) {
                if (data) {
                    console.log('用料申请单内容');
                    console.log(data);
                    materialList = data.list;
                    renderOrderList(materialList);
                    window.sessionStorage.setItem('materialList', JSON.stringify(materialList));
                }
            });
        }
    });
    // 调拨单弹窗
    function renderOrderList(orderList) {
        if (orderList.length) {
            $.pop({
                content: template('change/order-list', {items: orderList}),
                title: '选择用料申请单',
                noIcon:true,
                yes: function() {
                    var selectedLen = 0;
                    var selectedName = '';
                    var selectedIndex = '';
                    $('.warn-btn').each(function(index, item) {
                        var self = $(this);
                        // alert($this.prop('checked'));
                        if (self.prop('checked')==true) {
                            selectedLen++;
                            selectedName = self.parent('.form-group').find('span').text();
                            selectedId = self.prop('data-typeid');
                            selectedIndex = index;
                            materialList[index].isChecked = true;
                        }

                    });
                    if (selectedLen) {
                        window.sessionStorage.setItem('materialList', JSON.stringify(materialList));
                        $('#allocationList').val(selectedName);
                        $('#addInList').html(template('change/add-list', {
                            items: materialList[selectedIndex].matDetail
                        }));
                        $('#addInList').removeClass('hide');
                    } else {
                        $.toast('请选择采购单');
                        $('#addInList').addClass('hide');
                        return false;
                    }
                }
            });
        }
    }
    // 出库数量聚焦时如果为0清空内容
    $(document).on('focus','.numbers', function() {
        if ($(this).val() == 0) {
            $(this).val('')
        }
    });
    // 调拨数量失焦时如果为空赋值为0
    $(document).on('blur','.numbers', function() {
        console.log($(this).val())
        if ($(this).val() == '') {
            $(this).val(0)
        };
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
        window.location.href = "./change-list.html";
    });
    // 点击保存
    $(document).on('click', '#save', function(e){
        var isSubmit = false;
        if ($('#allocationList').val() == '') {
            $.toast('选择调拨单后保存',{timer:2000});
            isSubmit = false;
            return false;
        } else {
            isSubmit = true;
        }
        if (isSubmit) {
            var body= {};
            // body.allotDatetime = '' ; // 调拨时间
            // body.allotDeptName = '';  // 调拨人部门名称
            // body.allotEmpId = ''; // 调拨人id
            // body.allotEmpName = ''; // 调拨人名称
            body.allotName = $('#allocationList').val(); // 调拨单名称
            // body.allotNo = ''; // 调拨单编号
            body.allotReason = $('#reason').val(); // 调拨理由
            // body.id = '' // id
            var imgList = [];
            $('.upload-item-inner').each(function(){
                var self = $(this);
                var thisUrl = self.prop('href');
                var thisObj = {};
                thisObj.allotId = $('#allocationList').val();
                thisObj.allotImgUrl = thisUrl;
                imgList.push(thisObj);

            });
            var matList = [];
            var isNonTransfer; // 未调拨
            var isAllTransfer; // 全部调拨
            $('.js-wuliao-list tr').each(function(index, item){
                var self = $(this);
                var objList = {};
                objList.id = self.find('.orderNumber').attr("id");;
                objList.matName = self.find(".matName").html();
                objList.matNeetNum = self.find(".buyCount").html(); // 所需数量
                objList.matRealNum = self.find('.numbers').val(); // 实际数量
                objList.matSpec = self.find('.orderNumber').attr("matSpec"); // 物料规格
                objList.matTypeCode = self.find('.orderNumber').attr("matTypeCode"); // 物料型号
                objList.matTypeId = self.find('.orderNumber').attr("matTypeId"); // 物料型号
                matList.push(objList);

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
                    if (objList.matNeetNum == objList.matRealNum) {
                        isAllTransfer = true;
                    } else {
                        isAllTransfer = false;
                    }
                }
            });
            if (isNonTransfer) {
                body.whAllotStat = 1;
            } else if (isAllTransfer) {
                body.whAllotStat = 3;
            } else {
                body.whAllotStat = 2;
            }
            body.imgList = imgList; // 图片
            body.matList = matList; // 物料
            console.log(body);
            $.getData({
                url: 'allot/saveOrUpdate',
                body: body
            }, function(data) {
                if (data) {
                    console.log('提交后返回');
                    console.log(data);
                    window.location.href= './change-list.html'
                }
            });
        }
    });

    // 判断调拨状态
    // function allocationState () {

    // }
});

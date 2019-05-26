$(function () {

    // 页面加载时的公共方法
    function init() {
        getContent();
    }
    init();

    // 获取页面所需数据
    var detailCont;
    function getContent() {
        // 获取详情数据
        $.getData({
            url: 'allot/detail',
            query: {
                id: $.getQueryString('id')
            }
        }, function (data) {
            renderCont(data);
            detailCont = data;
        });
    }
    // 渲染数据
    function renderCont(data) {
        $('#detailCont').html(template('change/edit-change', {
            items: data
        }));
        data.matVoList.forEach(function(item){
            item.addNum = parseInt(item.matNeetNum - item.matRealNum);
        });
        console.log(data.matVoList);
        $("#materielList").html(template('change/edit-detail-list', {
            items: data.matVoList
        }));
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
    $(document).on('click', '#cancel', function(e){
        window.history.go(-1);
    });

    // 点击图片放大
    $(document).on('click', '.img', function(e){
        var self = $(this);
        window.open(self.prop('src'));
    });
    // 调拨数量聚焦时如果为0清空内容
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
            $.msg("请输入正整数");
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

    // 点击保存
    $(document).on('click', '#save', function(e){
        if (detailCont == undefined) {
            $.msg('获取数据成功后提交');
        } else {
            var body= {};
            // body.allotNo = $('#allotNo').html(); // 调拨单编号
            // body.allotName = $('#allotName').html(); // 用料申请单名称
            // body.allotReason = $('#reason').html(); // 调拨理由

            body.id = $.getQueryString('id'); // id
            body.allotNo = detailCont.allotNo; // 调拨单编号
            body.allotName = detailCont.allotName; // 用料申请单名称
            body.allotReason = detailCont.allotReason; // 调拨理由
            body.imgList = detailCont.imgVoList; // 图片

            // var imgList = [];
            // $('.upload-item-inner').each(function(){
            //     var self = $(this);
            //     var thisUrl = self.prop('src');
            //     var thisObj = {};
            //     thisObj.allotId = $('#allotName').html();
            //     thisObj.allotImgUrl = thisUrl;
            //     imgList.push(thisObj);
                
            // });
            var matList = [];
            var isNonTransfer; // 未调拨
            var isAllTransfer; // 全部调拨
            $('.js-list tr').each(function(index, item){
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
});
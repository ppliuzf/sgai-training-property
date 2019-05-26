$(function () {
    var infoId = $.getQueryString('infoId'),
    orderList = [],
    selectDept=[],
    param = {},
    orderId,
    outTypes = 0,  // 出库类型（0：调拨出库）
    oddNumbers, //单号
    whTypes = 1, // 仓库类型（1：实仓）
    whId  //仓库ID


    function init() {
        getContent();
        // 点击保存
        $(document).on('click', '#save', function (e) {
            submit(infoId)
        });
        // 点击取消
        $(document).on('click', '#cancle', function (e) {
            history.go(-1);
        });
        // 点击选择采购单
        $(document).on('click', '#choiseOrder', function (e) {
            getOrderList();
        });
        // 更改仓库类型
        $(document).on('change', '#whType', function (e) {
            selectDept = [];
            whTypes = $("#whType option:selected").val();
            getSelectDept();

        });
        //   更改申请单类型
        $(document).on('change', '#whOutType', function (e) {
            orderList = [];
            $('#warehouseReceipt').val("");
            $('#stepTwo').html('');
            if (e.currentTarget.value == 2) {
                $('#choiseOrder b').text("选择用料申请单");
                outTypes = 1;
            } else if (e.currentTarget.value == 1) {
                $('#choiseOrder b').text("选择调拨申请单");
                outTypes = 0;
            } else {
                $('#choiseOrder b').text("选择采购单");
                outTypes = 6;
            }
        });
    }
    init();
    // 获取申请单数据
    var currPage = 1;
    function getOrderList(){
        if(orderList && orderList.length > 0){
            renderOrderList(orderList, $('#whOutType').val());

        } else {
            // for(var i=0; i < orderList.length; i++){
            //     if(i != currPage) orderList[i] = [];
            // }
            // 判断申请单类型
            var url;
            var query={};
            if ($('#whOutType').val() == 2) {
                url = 'common/suppliesList'
                query = {pageNum:1,pageSize:10000}
            } else if ($('#whOutType').val() == 1) {
                url = 'allot/searchList4InOut',
                query = { hasOrder:0 };
            } else if ($('#whOutType').val() == 6) {
                url = 'order/searchListForPlan',
                query = {
                    pageNum: 1,
                    pageSize: 10000,
                    outOrIn: 1 //入库传0，出库传1
                }
            }
            $.getData({
                url: url,
                query: query

            }, function (data) {
                console.log('返回数据');
                console.log(data);
                orderList = data.list || data;
                orderList[0].status = $('#whOutType').val();
                console.log(orderList)
                renderOrderList(orderList, $('#whOutType').val())
            })
        }
    }
    // 获取列表
    function getContent() {
        // 如果是二次编辑的数据
        if (infoId) {
            $.getData({
                url: 'suppliesOperation/suppliesDetil',
                query: {
                    id: infoId
                }
            }, function (data) {
                if (data) {
                    // 引入模板
                    renderCont1(data);
                }
            });

        } else {
            // 引入模板
            renderCont1({});
        }

    }
     // 出库数量修改个数
     $(document).on('keyup', '.numbers', function(){
        var self = $(this);
        var matNum = parseInt(self.attr('matNum')); // 库存数量
        var matNeetNum = parseInt(self.attr('matNeetNum')); // 所需数量
        var thisLargeNumber;
        console.log(matNum);
        console.log(matNeetNum);
        if (matNum < matNeetNum) {
            thisLargeNumber = matNum;
        } else {
            thisLargeNumber = matNeetNum;
        }
        console.log('最大值'+thisLargeNumber);
        // var thisLargeNumber = self.attr('applyCount');
        if (parseInt(self.val()) > thisLargeNumber ) {
            self.val(thisLargeNumber);
        }
        if (parseInt(self.val()) < 0) {
            self.val("0");
        }
        if (!checkNumber(self.val())) {
            $.toast("请输入正整数", { timer: 2000 });
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

    // 切换仓库
    $(document).on('change', '#selectDept', function(){
        whId = $("#selectDept option:selected").val();
        if (oddNumbers) {
            renderList(whId, oddNumbers, outTypes); // 重新获取用料数据（仓库单号，调拨编号，出库类型）
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

    // 申请单弹窗
    function renderOrderList(orderList, orderType) {
        for (var i = 0; i < orderList.length; i++) {
            orderList[i].isChecked = '0';
            // if ((orderList[i].applyNo || orderList[i].allotNo) === $('#warehouseReceipt').val()) {
            //     console.log($('#warehouseReceipt').val())
            //     orderList[i].isChecked = '1'
            // }
            if ($('#whOutType').val() == 1) {
                if (orderList[i].allotNo === $('#warehouseReceipt').val()) {
                    console.log($('#warehouseReceipt').val())
                    orderList[i].isChecked = '1'
                }
            } else if ($('#whOutType').val() == 2) {
                if (orderList[i].applyNo === $('#warehouseReceipt').val()) {
                    console.log($('#warehouseReceipt').val())
                    orderList[i].isChecked = '1'
                }
            } else if ($('#whOutType').val() == 6) {
                if (orderList[i].orderNo === $('#warehouseReceipt').val()) {
                    console.log($('#warehouseReceipt').val())
                    orderList[i].isChecked = '1'
                }
            }
        }
        if (orderList.length) {
            $.pop({
                content: template('add/chooseOrder', { items: orderList,type: orderType }),
                title: '选择申请单',
                noIcon:true,
                yes: function() {
                    if($('.js-order-list input:checked').length){
                        // renderCont2(orderList[$('.js-order-list input:checked').parent().index()].matDetail || orderList[$('.js-order-list input:checked').parent().index()].matVoList)


                        if($('#whOutType').val() == 1 ){  // 调拨出库
                            oddNumbers = $('.js-order-list input:checked').attr('id');

                            renderList(whId, oddNumbers, 0);
                        } else if ($('#whOutType').val() == 2) { // 用料出库
                            oddNumbers = $('.js-order-list input:checked').attr('names');
                            renderList(whId, oddNumbers, 1);
                        } else if ($('#whOutType').val() == 6) { //计划出库
                            oddNumbers = $('.js-order-list input:checked').attr('names');
                            renderList(whId, oddNumbers, 6);
                        }
                        $('#warehouseReceipt').val($('.js-order-list input:checked').siblings('span').text());
                        $('#warehouseReceipt').attr('ids', $('.js-order-list input:checked').attr('id'));
                        $('#addInList').removeClass('hide');


                    } else {
                        $.toast('请选择申请单', { timer: 2000 });
                        $('#addInList').addClass('hide');
                        return false;
                    }
                    var curr = orderList[$('.js-order-list input:checked').parent().index()]

                    param.matApplyName  = curr.applyNo;
                    param.allotName  = curr.allotNo;
                    param.suppliesDetails = collectMaterielVos(curr.matDetail || curr.matVoList || curr.matDetailVoList)

                    var orderNumber = $('.js-order-list input:checked').attr('id');
                }
            });
        }
    }
    function renderCont1(data) {
        $('.stepOne').html(template('add/out-add', {
            items: data
        }));
        getSelectDept()
    }
    // function renderCont2(data) {
    //     console.log('选择调拨单点击确定时');
    //     console.log(data);
    //     $('.stepTwo').html(template('add/wuliao-list2', {
    //         items: data
    //     }));
    // }


    // 渲染无数据
    function renderEmpty(col) {
        $('.stepTwo .js-wuliao-list').html(template('common/record-empty', {
            colspan: col
        }));
    }

    // 获取用料明细的列表数据
    function renderList(selectWhId, orderNumber, list) { // list:0代表调拨，1代表用料，6代表计划

        var query = {};
        query = {
            pageNo: currPage,
            pageSize: 10
        }
        var ListParam = {};
        ListParam.isAllot = list;
        ListParam.orderId = ''; //虚拟Id
        ListParam.orderNumber = orderNumber; //订单号
        ListParam.whId = selectWhId;
        // console.log('列表数据');
        // console.log(ListParam);

        $.getData({
            url: 'depotOutManage/detilList',
            body: ListParam,
            query: query

        }, function(data) {
            if (data.list.length>0 ) {
                orderId = data.list[0].orderId;
            } else {
                orderId = '';
            }

            data.list.forEach(function(item){
                if (item.matNum < item.matNeetNum) {
                    item.outNumber = item.matNum;
                } else {
                    item.outNumber = item.matNeetNum;
                }
            });
            $('.stepTwo').html(template('add/wuliao-list2', {
                items: data.list
            }));

            $.renderPage({
                count: data.count,
                curr: currPage,
                jump: function(n) {
                    currPage = n;
                    renderList(selectWhId, orderNumber, list);
                }
            });
        });
    }
    function renderselectDept(data) {
        $('#selectDept').html(template('add/selectDept', {
            items: data
        }));
    }
    // 获取仓库列表
    function getSelectDept() {
        if (selectDept && selectDept.length) {
            renderselectDept(selectDept)
        } else {
            $.getData({
                url: 'common/byWhType',
                query: {
                    whType: $('#whType').val()
                }
            }, function(data) {
                selectDept = data;
                whId = selectDept[0].id; // 仓库ID
                renderselectDept(selectDept);
                if (oddNumbers){
                    renderList(whId, oddNumbers, outTypes); // 重新获取用料数据（仓库单号，调拨编号，出库类型）
                }
            });
        }

    }
    // 物料数据
    function collectMaterielVos(matDetail) {
        console.log(matDetail)
        var suppliesDetails = [];
        for (var i = 0; i < matDetail.length; i++) {
            suppliesDetails[i] = {};
            suppliesDetails[i].matName = matDetail[i].matName
            suppliesDetails[i].matNeetNum = matDetail[i].applyCount || matDetail[i].matNeetNum
            suppliesDetails[i].matSpec = matDetail[i].matSpec
            suppliesDetails[i].matTypeId = matDetail[i].matTypeId
            suppliesDetails[i].matRealNum = ''
        }
        return suppliesDetails;

    }
    // 收集新建出库单数据
    function getData() {
        // param.whId = $("#selectDept").val(); // 仓库id
        // param.whName = $("#selectDept option:selected").text(); // 仓库名称
        // param.whOutType  = $("#whOutType").val(); //出库类型
        // param.whType = $("#whType").val(); //仓库类型

        if(!oddNumbers){
            $.toast('请选择申请单',{timer:2000});
            return false;
        }

        if($('#whOutType').val() == 1 ){  // 调拨出库
            param.allotId = $('#warehouseReceipt').attr('ids');
            param.allotName = $('#warehouseReceipt').val();
            param.matApplyId = '';
            param.matApplyName = '';
        } else if ($('#whOutType').val() == 2) {
            param.matApplyId = $('#warehouseReceipt').attr('ids');
            param.matApplyName = $('#warehouseReceipt').val();
        } else if ($('#whOutType').val() == 6) {
            param.matApplyId = '';
            param.matApplyName = $('#warehouseReceipt').val();
            param.orderId = $('#warehouseReceipt').attr('ids');
            param.orderNumber = $('#warehouseReceipt').val();
        }
        param.orderId = orderId;
        param.whId = $("#selectDept").val(); // 仓库id
        param.whName = $("#selectDept option:selected").text(); // 仓库名称
        param.whOutType = $("#whOutType").val(); //出库类型
        param.whType = $("#whType").val(); //仓库类型

        var reg=/\D/,flag1=0,flag2=1;
        for(var i=0; i<$('.matRealNum').length; i++){
            param.suppliesDetails[i].matRealNum = $('.matRealNum').eq(i).val();
            if (reg.test(param.suppliesDetails[i].matRealNum) || (param.suppliesDetails[i].matRealNum < 0)) {
                $.toast('出库数量只能为自然数', { timer: 2000 });
                return false;
            }
            if (param.suppliesDetails[i].matRealNum > 0) flag1++;
            if (param.suppliesDetails[i].matRealNum > param.suppliesDetails[i].matNeetNum) {
                // $.toast('出库数量不能大于所需数量', { timer: 2000 });
                // return false;
            } else if (param.suppliesDetails[i].matRealNum < param.suppliesDetails[i].matNeetNum) {
                flag2 = flag2 * 0;
            }
        }

        if (flag1 == 0) {
            param.whStat = 1;
        } else if (flag2 == 0) {
            param.whStat = 2;
        } else {
            param.whStat = 3;
        }

        if (param.suppliesDetails.length==0){
            $.toast('请选择有用料明细的数据进行提交', { timer: 2000 });
            return false;
        }
        return param;
    }
    // 点击保存
    function submit(infoId) {
        var param = getData();

        if (param === false) {
            return false;
        } else {
            if (infoId) {
                param.id = infoId;
                console.log(param);
                $.getData({
                    url: 'depotOutManage/addDepot',
                    query: {},
                    body: param
                }, function (data) {
                    window.history.go(-1);

                });
            }
            else {
                console.log(param)
                $.getData({
                    url: 'depotOutManage/addDepot',
                    query: {},
                    body: param
                }, function (data) {
                    window.history.go(-1);
                    // if (data) {
                    //     window.history.go(-1);
                    // }
                });
            }

        }
    }
});

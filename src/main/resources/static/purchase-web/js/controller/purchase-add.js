$(function () {
    var infoId = $.getQueryString('infoId');
    var selectMateriel, bossArr, MaterielData, listArr = [], dataList = [];
    var listAry = [], dataList1=[];
    $('.form_date2').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
    // 清空日期选择
    $(document).on('click', '.js-date-clean', function () {
        $(this).prev().val('');
    });
    function supplier() {
        $.getData({
            url: '/common/getComList'
        }, function (data) {
            bossArr = data;
            for (var m = 0; m < bossArr.length; m++) {
                $('#supplier').append('<option data-id=' + bossArr[m].id+'>' + bossArr[m].comName + '</option>');
            }
        });
    }
    function collectMateriel() {
        $.getData({
            url: '/common/listMdmMatInfo',
            query: {
                pageNo: 1,
                pageSize: 10000
            }
        }, function (data) {
            MaterielData = data.list;
            for (var j = 0; j < MaterielData.length; j++) {
                $('#materiel').append('<option id=' + MaterielData[j].id+' matName=' + MaterielData[j].matName + ' spec=' + MaterielData[j].spec + ' matTypeCode=' + MaterielData[j].matTypeCode+'>' + MaterielData[j].matName + '</option>');
            }
        });
    }
    // 渲染已选物料
    function renderWuLiao(data) {
        if (data) {
            $('.js-wuliao-list').html(template('purchase/wuLiaoMingXi', { items: data }));
        }else{
            $('.js-wuliao-list').html(template('purchase/wuLiaoMingXi', { items: listArr }));
        }
    }
    // 渲染物料弹窗
    function renderMaterielList() {
        listArr = [];
        supplier();
        collectMateriel();
        setTimeout(function(){
            renderTc();
        },300);
        $.pop({
            title: '采购物料',
            content: template('purchase/addLngredient'),
            isCancel: true,
            size: 'lg',
            yes: function () {
                if (listArr.length) {
                    renderWuLiao(listArr);
                } else {
                    renderEmpty();
                }
            }
        });
    }
    function getLocalTime(ns) {
        return new Date(parseInt(ns)).toLocaleString().replace(/:\d{1,2}$/, ' ');
    }   
    // 获取列表
    function getContent() {
        // 编辑页面
        if (infoId) {
            $("#edit").removeClass("hidden");
            document.title = '编辑订单';
            $.getData({
                url: 'order/orderDetail',
                query: {
                    id: infoId
                }
            }, function (data) {
                if (data) {
                    if (data.buyerTime){
                        var time = getLocalTime(data.buyerTime).substr(0, 10).replace(/\//ig,'-');
                        $('#fqTimeStart').val(time);
                    }
                    dataList1 = data.matDetailVoList;
                    if (data.matDetailVoList) {
                        for (var i = 0; i < data.matDetailVoList.length;i++) {
                            var obj1 = {};
                            obj1.materiel = data.matDetailVoList[i].matName;
                            obj1.spec = data.matDetailVoList[i].matSpec;
                            obj1.matTypeCode = data.matDetailVoList[i].matTypeCode;
                            obj1.number = data.matDetailVoList[i].buyCount;
                            obj1.supplier = data.matDetailVoList[i].supplyComName;
                            // 全局变量保存
                            listAry.push(obj1);
                        }
                        // 渲染主页面的列表了
                        renderWuLiao(listAry);
                    } else{
                        renderEmpty();
                    }
                    var obj = JSON.parse(data.deptJson);
                    // 组织树
                    $.dept({
                        el: '.js-dept-selector',
                        type: 'dept',
                        default: obj
                    });
                    data.deptSearch = {
                        approvalEmpName:data.buyerEmpName,
                        approvalEmpId: data.buyerEmpId
                    };
                    $.instantSearch({
                        default : data.deptSearch,
                        // url: '/orgTree/searchEmpInfoByName'
                    });
                }
            });
        } else {
            // 新增页面
            $("#creat").removeClass("hidden");
            document.title = '新建订单';
            // 下拉联想
            $.instantSearch({
                // url: '/orgTree/searchEmpInfoByName',
                placeholder:'请输入采购人姓名'
            });
            // 组织树
            $.dept({
                el: '.js-dept-selector',
                type: 'dept'
            });
        }
    }
    // 收集数据
    function getData() {
        var name = null;
        var param = {}, obj = {};
        if ($.trim($('#instant-selected').val())){
            name = JSON.parse($.trim($('#instant-selected').val()));
            console.log(name);
            param.buyerEmpName = name.title;
            param.buyerEmpId = name.id;
        }
        param.orderType = 2;// 订单来源
        param.deptJson = $('.js--all').attr('value');// 部门对象
        param.matDetailParamList = dataList;  //物料明细
        console.log('********', JSON.stringify(param.matDetailParamList));
        param.buyerTime = $.trim($("#fqTimeStart").val()) + 'T00:00:00'; // 采购时间
        if ($('#instant-selected').val() === '') {
            $.alert('请输入采购人');
            return false;
        }
        if ($('.js--selector').find('span').length > 1) {
            $.alert('只能选择一个部门');
            return false;
        }
        if ($('.js--selector').children().length < 1) {
            $.alert('请选择部门');
            return false;
        }
        if ($.trim($("#fqTimeStart").val()) === '') {
            $.alert('请选择时间');
            return false;
        }
        if ($('.js-wuliao-list').find('.no-record').html() == '暂无记录') {
            $.alert('请采购物料');
            return false;
        }
        return param;
    }
    // 点击保存
    function submit(infoId) {
        var dataMain = getData();
        if (!dataMain) {
            return false;
        }
        if (infoId) {
            // 编辑页面
            dataMain.id = infoId;
            dataMain.matDetailParamList = dataList1;
            dataMain.buyerEmpName = $.trim($('#instant-keywords').val());
            dataMain.buyerEmpId = $.trim($('#instant-selected').val());
            $.getData({
                url: '/order/addOrder',
                body: dataMain
            }, function (data) {
                if (data) {
                    window.location.href = './purchase-order-list.html';
                }
            });
        } else {
            dataMain.deptJson = dataMain.deptJson;
            $.getData({
                url: '/order/addOrder',
                query: {},
                body: dataMain
            }, function (data) {
                if (data) {
                    window.location.href = './purchase-order-list.html';
                }
            });
        }
    }
    // 渲染无数据
    function renderEmpty() {
        $('.js-wuliao-list').html(template('common/record-empty', {
            colspan: 10
        }));
    }
    function renderTc(data) {
        $('.js-editWuLiao').html(template('purchase/tancengDetail', { items: listArr }));
    }
    function init() {
        if (listArr.length) {
            renderWuLiao();
        } else {
            renderEmpty();
        }
        getContent();
        // 点击采购材料
        $(document).on('click', '.js-select-materiel', function () {
            renderMaterielList();
        });
        // 点击添加物料
        $(document).on('click', '.js-popAdd', function () {
            var paramObj = {}, objLast={};
            paramObj.id = $.trim($('#materiel').find("option:selected").attr('id')); 
            paramObj.supplier = $.trim($('#supplier').val());
            paramObj.materiel = $.trim($('#materiel').val());
            paramObj.number = parseInt($.trim($('#number').val()));
            if (isNaN(paramObj.number)) {
                $.toast('请输入正整数', {
                    timer: 1000
                });
                $('#number').val('1');
                return false;
            }
            paramObj.spec = $(this).parents().find("#materiel").find("option:selected").attr('spec');
            paramObj.matTypeCode = $(this).parents().find("#materiel").find("option:selected").attr('matTypeCode');
            objLast.supplyComId = $.trim($('#supplier').find("option:selected").attr('data-id'));
            objLast.matTypeId = $.trim($('#materiel').find("option:selected").attr('id'));
            objLast.supplyComName = $.trim($('#supplier').val());
            objLast.matName = $.trim($('#materiel').val());
            objLast.buyCount = parseInt($.trim($('#number').val()));
            if (isNaN(objLast.buyCount)) {
                $.toast('请输入正整数', {
                    timer: 1000
                });
                $('#number').val('1');
                return false;
            }
            objLast.matSpec = $(this).parents().find("#materiel").find("option:selected").attr('spec');
            objLast.matTypeCode = $(this).parents().find("#materiel").find("option:selected").attr('matTypeCode');
            // 渲染列表的是 listArr 提交的是 dataList
            listArr[listArr.length] = paramObj;
            dataList[dataList.length] = objLast;
            dataList1 = dataList;
            if (listArr){
                renderTc();
            }
        });
        // 点击删除
        $(document).on('click', '.js-del1', function () {
            var that = $(this);
            $.popnomask({
                title: '提示',
                content: '确认删除当前物料？',
                isCancel: true,
                size: 'sm',
                yes: function () {
                    for (var n = 0; n < listArr.length;n++){
                        if (listArr[n].id == that.parents('.materiel-item').attr('data-id')) {
                            listArr.splice(n,1);
                            dataList.splice(n, 1);
                        }
                    }
                    that.parents('.materiel-item').remove();
                }
            });
        });
        // 点击保存
        $(document).on('click', '#save', function (e) {
            submit(infoId);
        });
        // 点击取消
        $(document).on('click', '#cancle', function(){
            history.go(-1);
        });
        // 数量
        $(document).on('keyup', '#number', function(){
            if ($('#number').val()!=='' && $('#number').val() < 1){
                $('#number').val('1');
            }
        });
    }
    init();
});

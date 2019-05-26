$(function () {
    var infoId = $.getQueryString('id'),rwId = $.getQueryString('rwId');
    var selectMateriel, bossArr, MaterielData, listArr = [], dataList = [];
    var listAry = [];
    var paramObj = {}, objLast={};
    function init() {
        getContent();
        changeTitle();
         // 数量
         $(document).on('keyup', '#number', function(){
            if ($('#number').val()!=='' && $('#number').val() < 1){
                $('#number').val('1');
            }
        });
        // 点击采购物料
        $(document).on('click', '#phaseWuliao', function(e){
            renderMaterielList();
        }); 
         // 点击添加
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
                            break;
                        }
                    }
                    console.log(listArr,dataList)
                    that.parents('.materiel-item').remove();
                }
            });
        }); 
        // 点击保存
        $(document).on('click', '#save', function(e){
            submit();
        });
        // 点击取消
        $(document).on('click', '#cancle', function(e){
            history.go(-1);
        });
    }
    init();
    // 清空日期选择
    $(document).on('click', '.js-date-clean', function () {
        $(this).prev().val('');
    });
     // 获取列表
     function getContent() {
        // 如果是二次编辑的数据
        if (rwId) {
            $.getData({
                url: 'listOrDetail/planTaskDetail',
                query: {
                    id: rwId
                }
            }, function(data) {
                if (data) {              
                    // 引入模板
                    renderCont(data);  
                    renderWuLiao(data.planDetailList);
                    listArr = data.planDetailList;        
                }
                 // 时间插件
                 $('.form_date1').datetimepicker({
                    language:  'zh-CN',
                    weekStart: 1,
                    todayBtn:  1,
                    autoclose: 1,
                    todayHighlight: 1,
                    startView: 2,
                    minView: 2,
                    forceParse: 0,
                    format:"yyyy-mm-dd"
                });
                 $('.form_date2').datetimepicker({
                    language:  'zh-CN',
                    weekStart: 1,
                    todayBtn:  1,
                    autoclose: 1,
                    todayHighlight: 1,
                    startView: 2,
                    minView: 2,
                    forceParse: 0,
                    format:"yyyy-mm-dd"
                });
                $.counter({
                    el: '#taskOpinion '
                });
                $.getData({
                    url:'listOrDetail/getStageList',
                    query:{
                        id:infoId
                    }
                },function(select){
                    for(var i=0; i<select.length ; i++){
                        if(select[i].id == data.stageId){
                            select[i].selected = true;
                            break;
                        }
                    }
                    $('#select').html(template('plan-detail/apply-add-select', {
                        items: select
                    }));
                });
            });
        } else {
            // 引入模板
            renderCont({});
             $.counter({
            el: '#taskOpinion '
        });
        $.getData({
            url:'listOrDetail/getStageList',
            query:{
                id:infoId
            }
        },function(data){
            $('#select').html(template('plan-detail/apply-add-select', {
                items: data
            }));
        });
        }
    // 时间插件
     $('.form_date1').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
        forceParse: 0,
        format:"yyyy-mm-dd"
    });
    $('.form_date2').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
        forceParse: 0,
        format:"yyyy-mm-dd"
    });
        
    }
    function renderCont(data) {
        $('#add').html(template('plan-detail/purchase-plan-apply-add', {
            items: data
        }));
        renderEmpty()
    }
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
            MaterielData = data;
            for (var j = 0; j < MaterielData.length; j++) {
                $('#materiel').append('<option id=' + MaterielData[j].id+' matName=' + MaterielData[j].matName + ' spec=' + MaterielData[j].spec + ' matTypeCode=' + MaterielData[j].matTypeCode+'>' + MaterielData[j].matName + '</option>');
            }
        });
    }
    // 渲染已选物料
    function renderWuLiao(data) {
        if (data) {
            $('#wuliaoList').html(template('plan-detail/wuliaoList', { items: data }));
        }else{
            $('#wuliaoList').html(template('plan-detail/wuliaoList', { items: listArr }));
        }
    }
    function renderTc() {
        console.log(JSON.stringify(listArr));
        $('.js-editWuLiao').html(template('plan-detail/wuliaoDetail', { items: listArr }));
    }
    // 渲染物料弹窗
    function renderMaterielList() {
        supplier();
        collectMateriel();
        setTimeout(function(){
            renderTc();
        },300);
        $.pop({
            title: '采购物料',
            content: template('plan-detail/addWuliao'),
            isCancel: true,
            size: 'md',
            noIcon:true,
            yes: function () {
                if (listArr.length) {
                    renderWuLiao(listArr);
                } else {
                    renderEmpty();
                }
            }
        });
    }
     // 渲染无数据
     function renderEmpty() {
        $('#wuliaoList').html(template('common/record-empty', {
            colspan: 10
        }));
    }
    // 根据url参数修改页面标题
    function changeTitle(){
        if(rwId){
            $(".js-title h4").html("编辑采购申请");$('title').html("编辑采购申请");
        } else{
            $(".js-title h4").html("新建采购申请");$('title').html("新建采购申请");
        }
    }
    // 收集新建申请数据
    function getData() {
       var param = {};
       param.planId = infoId;
       param.planDetailList = [];
       param.id = rwId;
       param.stageId = $('#phase').val();
       param.taskPuchaseName =  $('#purchaseName').val();
       param.taskPurchaseDate  = $('#taskPurchaseDate').val();
       param.taskEndDate = $('#taskEndDate').val();
       param.taskOpinion = $('#taskOpinion ').val();
       for(var i=0 ; i < listArr.length ; i++){
            param.planDetailList[i] = {};
            param.planDetailList[i].applyCount = listArr[i].number || listArr[i].applyCount;
            param.planDetailList[i].matName = listArr[i].materiel || listArr[i].matName;
            param.planDetailList[i].matSpec = listArr[i].spec || listArr[i].matSpec;
            param.planDetailList[i].matTypeCode = listArr[i].matTypeCode;
            param.planDetailList[i].matTypeId = listArr[i].id || listArr[i].matTypeId;
       }
       window.sessionStorage.setItem('detailMy', JSON.stringify(param.planDetailList));
       if(param.taskPuchaseName == ""){
           $.toast("请输入采购名称",{timer:2000});
           return false;
       }
       if(param.taskPurchaseDate == ""){
            $.toast("请选择采购日期",{timer:2000});
            return false;
        }
        if(param.taskEndDate == ""){
            $.toast("请选择结束日期",{timer:2000});
            return false;
        }
        aa = $.getTimeStamp($('#taskPurchaseDate').val());
        bb = $.getTimeStamp($('#taskEndDate').val(), 'end');
        if (aa && bb && aa > bb) {
            $.toast('采购日期要小于结束日期', {
                timer: 2000
            });
            return false;
        }
        if(param.planDetailList.length == 0){
            $.toast("请添加物料",{timer:2000});
            return false;
        }   
        console.log(param);
        return param;
    }
    // 点击保存
    function submit() {
        var param = getData();
        if (param === false) {
            return false;
        } else {
            $.getData({
                url: 'order/saveOrUpdateTask',
                query: {},
                body: param
            }, function (data) {
                if (data) {
                    window.history.go(-1);
                }
            });     
        }
    }
});

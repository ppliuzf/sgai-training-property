$(function () {
    var re = /^[1-9]+[0-9]*]*$/;
    var reId = $.getQueryString('id');
    var type = Number($.getQueryString('type'));
    function setCurTime(times) {
        var curT = $.formatTime(new Date().getTime()).split(" ")[1];
        var curH = curT.split(":")[0];
        var curM = curT.split(":")[1];
        if (curM < 30) {
            curT = curH + ":" + 30;
        } else {
            curT = parseInt(curH) + 1 + ":" + "00";
        }
        for (var i in times) {
            if (times[i].title == curT) {
                return times[i].value;
            }
        }
    }
    var weekFlag = false; // 是否选中每周
    var  arr = [],
        set1 = [],
        set2 = [],
        set3 = [],
        set4 = [],
        arr1 = [],
        arr2 = [],
        arr3 = [],
        arr4 = [];
    function getDeploy(){
        arr = []
        if($('.set-icon1')){
            arr1= []
            for(var i= 0;i<set1.length;i++){
                for(var j= 0;j<$('.set-icon1 li').length;j++){
                    if($('.set-icon1 li')[j].dataset.id===set1[i].value){
                          arr1.push(set1[i])
                    }
                }
            }
            if(arr1.length){
                arr.push(1)
            }
        }
        if($('.set-icon2')){
            arr2= []
            for(var i= 0;i<set2.length;i++){
                for(var j= 0;j<$('.set-icon2 li').length;j++){
                    if($('.set-icon2 li')[j].dataset.id===set2[i].id){
                        arr2.push(set2[i]);
                    }
                }
            }
            if(arr2.length){
                arr.push(2)
            }
        }
        if($('.set-icon3')){
            arr3=[]
            for(var i= 0;i<set3.length;i++){
                for(var j= 0;j<$('.set-icon3 li').length;j++){
                    if($('.set-icon3 li')[j].dataset.id===set3[i].id){
                    //    arr3.push(set3[i])
                       arr3.push({"id":set3[i].id,"supplierName":set3[i].supplierName})
                       console.log(set3[i]);
                       
                    }
                }
            }
            if(arr3.length){
                arr.push(4)
            }
        }
        if($('.set-icon4')){
            arr4=[]
            for(var i= 0;i<set4.length;i++){
                for(var j= 0;j<$('.set-icon4 li').length;j++){
                    if($('.set-icon4 li')[j].dataset.id===set4[i].id){
                        arr4.push(set4[i])
                    }
                }
            }
            if(arr4.length){
                arr.push(3)
            }
        }
    }
    var curYear = new Date().getFullYear(); //获取当前年
    // 时间插件
    function dateShow() {
        $('.js-date').datetimepicker({
            language: 'zh-CN',
            autoclose: true,
            startView: 2,
            minView: 0
        });
        // 清空日期选择
        $(document).on('click', '.js-date-clean', function () {
            $(this).prev().val('');
        });

    }
    //重复执行操作
    $(document).on('click', '.inputCheck', function () {
        if ($('.inputCheck').is(':checked') == true) {
            $('#dateStart,#dateEnd').val('');
            $('#dateStart,#dateEnd').attr('disabled', true)
            $('.disMethod').show();
            $('.radio-inline input:first').attr('checked', 'checked');//默认每日
            $('.dayBox').show();
            $('group-time').show();
            $('.group-time').show();
            $('.weekBox').hide();
        } else {
            $('#dateStart,#dateEnd').attr('disabled', false)
            $('.disMethod').hide();
            $('.group-time').hide();
        }
    })
    //每日的操作
    $(document).on('click', '.dayInput', function () {
        if ($('.dayInput').is(':checked') == true) {
            $('.dayBox').show();
            $('group-time').show();
            $('.monthWrap').show();
            $('.weekBox').hide();
            $('.monthBox').hide();
        }
    })
    //每周的操作
    $(document).on('click', '.weekInput', function () {
        // console.log($('.inputCheck').is(':checked'),'this');
        if ($('.inputCheck').is(':checked') == true) {
            $('.weekBox').show();
            $('.monthWrap').show();
            $('.monthBox').hide();
        }
    })
    //每月的操作
    $(document).on('click', '.monthInput', function () {
        if ($('.monthInput').is(':checked') == true) {
            $('.weekBox').hide();
            $('.monthWrap').hide();
            $('.monthBox').show();
            commonNum();
        }
    })
    // 收集数据
    function collectData() {
        var params = {};
        params.optionFlag = '';
        params.taskType= $(".js-type").find("option:selected").val();
        if(!$('.js-range').find("option:selected").val()){
            $.alert('请选择任务专业！');
            return
        }
        if(!$('.js-plan').find("option:selected").val()){
            $.alert('请选择任务模板！');
            return
        }
        params.templateId= $(".js-plan").find("option:selected").val();
        params.templateName= $(".js-plan").find("option:selected").text();
        getDeploy();
        if(!arr.length){
            $.alert('请选择任务范围！');
            return
        }
        params.spaceData  = arr1.length?JSON.stringify(arr1):'';
        params.materielData  = arr2.length?JSON.stringify(arr2):'';
        params.supplierData  = arr3.length?JSON.stringify(arr3):'';
        params.equipmentData  = arr4.length?JSON.stringify(arr4):'';
        params.taskScopeType  = arr.join(',')
        params.recordId = reId;
        params.dayVo = {};
        params.weekVo = {};
        params.monthVo = {};
        params.taskDesc = $.trim($('#taskDesc').val());
        if ($('.inputCheck').is(':checked') == true) {//是否是重复执行
            params.taskFlag = 1;
            if ($('.dayInput').is(':checked') == true || $('.weekInput').is(':checked') == true) { //每日的数据
                params.optionFlag = 1;
                if ($('.weekInput').is(':checked') == true) {
                    weekFlag = true;
                    params.optionFlag = 2;
                }
                params.taskdayStart = $.getTimeStamp($('#dayStart').val());//年月日
                params.taskdayEnd = $.getTimeStamp($('#dayEnd').val());
                if (!params.taskdayStart) {
                    $.alert('请选择开始日期！');
                    return;
                } else if (!params.taskdayEnd) {
                    $.alert('请选择截止日期！');
                    return;
                } else if (params.taskdayStart > params.taskdayEnd) {
                    $.alert('截止日期不能小于开始日期！');
                    return;
                }else{
                    if (weekFlag){
                        params.weekVo.beginDate = params.taskdayStart;
                        params.weekVo.endDate = params.taskdayEnd;

                        
                    }else{
                        params.dayVo.beginDate = params.taskdayStart;
                        params.dayVo.endDate = params.taskdayEnd;
                    }
                    
                }
                // $("#start-time").find("option:selected").html();
                startTime = parseInt($("#start-time").val());//时分索引
                endTime = parseInt($("#end-time").val());
                if (startTime > endTime) {
                    $.alert("开始时间必须早于结束时间");
                    return false;
                } else {
                    if (weekFlag){
                        params.weekVo.beginTime = $("#start-time").find("option:selected").html();
                        params.weekVo.endTime = $("#end-time").find("option:selected").html();
                    }else{
                        params.dayVo.beginTime = $("#start-time").find("option:selected").html();
                        params.dayVo.endTime = $("#end-time").find("option:selected").html();
                    }
                }
                var ends = $("#end-time").find("option:selected").html();
                if ($("#end-time").find("option:selected").html() == '24:00') {
                    ends = '23:59';
                }
                // debugger;
                params.taskBeginTime = $.getTimeStamp($('#dayStart').val() + ' ' + $("#start-time").find("option:selected").html());
                params.taskEndTime = $.getTimeStamp($('#dayEnd').val() + ' ' + ends);
                //判断是否选择日期    params.weekVo.day
                params.weekAry = [];
                if (weekFlag) {
                    if ($(".weekBox input:checked").length <= 0) {
                        $.alert("请选择日期");
                    }else{
                      var weekLen =$(".weekBox input:checked").length;
                        for (var i = 0; i < weekLen; i++){
                            let cur = parseInt($(".weekBox input:checked").eq(i).val())+1;
                                if(cur ==7){
                                    cur = 0;
                                }
                                params.weekAry.push(cur);                               
                        }
                    }
                    // debugger
                    console.log(params.weekAry,'params.weekAryparams.weekAry');
                    params.weekVo.JhDay = params.weekAry.join(',');
                    params.taskDay = params.weekVo.JhDay;
                }
            } else if ($('.monthInput').is(':checked') == true) { //月份 endMtime !re.test($.trim($('.endMtime').val())) 
                params.optionFlag = 3;
                var $begMtime = $.trim($('.begMtime').val())-0;
                var $endMtime = $.trim($('.endMtime').val())-0;
                if (!$begMtime){
                    $.alert("请输入开始时间");
                    $('.begMtime').focus();
                    return false;
                } else if (!$endMtime){
                    $.alert("请输入结束时间");
                    $('.endMtime').focus();
                    return false;
                }
               
                if (!re.test($begMtime)) {
                    $.alert("请输入正整数");
                    $('.begMtime').focus();
                    return false;
                }
                if (!re.test($endMtime)){
                    $.alert("请输入正整数");
                    $('.endMtime').focus();
                    return false;
                } else if ($begMtime > $endMtime){
                    $.alert('开始日期不能大于结束日期');
                    return false;
                }else{
                    //获取当前月
                    var d = new Date();
                    var curMonth = d.getMonth()+1;
                    params.monthVo.beginDate =  $begMtime;
                    params.monthVo.endDate = $endMtime;
                    params.monthVo.beginMonth = curMonth;
                    params.monthVo.endMonth = '12';
                }
                params.monthVo.jhYear = $('#selectYear').find('option:selected').html();
                startTime = parseInt($("#start-time").val());//时分索引
                endTime = parseInt($("#end-time").val());
                if (startTime > endTime) {
                    $.alert("开始时间必须早于结束时间");
                    return false;
                } else {
                    params.monthVo.beginTime = $("#start-time").find("option:selected").html();
                    params.monthVo.endTime = $("#end-time").find("option:selected").html();
                }
                var ends = $("#end-time").find("option:selected").html();
                if ($("#end-time").find("option:selected").html() == '24:00') {
                    ends = '23:59';
                }
                params.taskBeginTime = $.getTimeStamp(params.monthVo.jhYear + '-' + params.monthVo.beginMonth + '-' + $begMtime + ' ' + $("#start-time").find("option:selected").html());
                params.taskEndTime = $.getTimeStamp(params.monthVo.jhYear + '-' + params.monthVo.endMonth + '-' + $endMtime + ' ' + ends);
            }

        } else {
            params.taskFlag = 0;
            // 执行时间
            params.taskBeginTime = $.getTimeStamp($('#dateStart').val());
            params.taskEndTime = $.getTimeStamp($('#dateEnd').val());
            if (!params.taskBeginTime) {
                $.alert('请选择开始时间！');
                return;
            } else if (!params.taskEndTime) {
                $.alert('请选择截止时间！');
                return;
            } else if (params.taskBeginTime > params.taskEndTime) {
                $.alert('截止时间不能小于开始时间！');
                return;
            }
        }
        params.dutyPersonIdList = [];
        if ($('.js--all').val()) {
            var participantMember = $.parseJSON($('.js--all').val());
            for (let p = 0; p < participantMember.length; p++) {
                params.dutyPersonIdList.push(participantMember[p].id);
            }
        }else{
            $.alert('请选择执行人！');
            return;
        }
        
        return params;
    }
    //取消 btn-cancel
    $(document).on('click', '.btn-cancel', function () {
        window.history.back();
    });
    // 保存
    $(document).on('click', '.btn-submit', function () {
        var body = collectData();
        debugger;
        console.log(body, 'daaaa');
        if (type) {
            body.type = 1;
        }
        if (body) {
            $.getData({
                url: '/task/taskQualitySave',
                // url: 'http://172.31.64.27:20032/task/taskQualitySave',
                body
            }, function (data) {
                $.msg('提交成功', 2000);
                setTimeout(() => {
                    window.history.back();
                }, 2000);
            });
        }
    });
    //获取当前时间
    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = year + seperator1 + month + seperator1 + strDate;
        return currentdate;
    }
    //
    function commonNum(name) {
        $.kmNumber({
            el: name ? name : '.js-num',
            max: 31,
            onblur: function (e, el) {
                if (e < 0) {
                    el.val(0);
                } else {
                    el.val(e);
                }
                if (e > 31) {
                    el.val(31);
                } else {
                    el.val(e);
                }
            }
        });
    };
    //树
    function tree(config){
        $('body').off('click')
        var settings = {
                numid:'',
                name:'',
                url:'',
               flag:false,
                query:'',
                arr :[],
                odd: false,
                nr:[],
                xzn:{},
                sob:{},
                convertList:function () {}//数据属性转换
            },
            opt = $.extend(settings, config);
        //数据结构转换
        function convert(rows){
            function exists(rows, parentId){
                for(var i=0; i<rows.length; i++){
                    if (rows[i].id == parentId) {
                        return true;
                    }
                }
                return false;
            }
            var nodes = [];
            for(var i=0; i<rows.length; i++){
                var row = rows[i];
                if (!exists(rows, row.parentId)){
                    nodes.push({
                        id:row.id,
                        text:row.name,
                        url:row.url,
                        checked:row.checked,
                        index:nodes.length+1
                    });
                }
            }
            var toDo = [];
            for(var i=0; i<nodes.length; i++){
                toDo.push(nodes[i]);
            }
            while(toDo.length){
                var node = toDo.shift();
                for(var i=0; i<rows.length; i++){
                    var row = rows[i];
                    if (row.parentId == node.id){
                        var child = {id:row.id,text:row.name,url:row.url,checked:row.checked};
                        if (node.children){
                            node.children.push(child);
                        } else {
                            node.children = [child];
                        }
                        child.index = node.children.length
                        toDo.push(child);
                    }
                }
            }
            return nodes;
        };
        //接收数据
        function getList() {
            $.getData({
                url: opt.url,
                query:opt.query
            }, function(data) {
                if (data) {
                    var row = []
                    var rows;
                    if(data.list && data.list.length){
                        rows = data.list;
                    }else{
                        rows = data
                    }
                    opt.convertList(rows,row)
                    var xxx = convert(row);
                    getSpace(xxx)
                    showBack()
                }
            });
        };
        //弹窗操作
        function getSpace(data){
            $.pop({
                title: '请选择-'+opt.name,
                size: 'sm',
                noIcon: true,
                content: template('plan/classification', {
                    items:data
                }),
                yes: function () {
                    savaChecked()
                    if( $('.set-icon'+opt.numid)[0]){
                        if( opt.nr.length===0){
                            opt.name = ''
                        }
                        $('.set-icon'+opt.numid).html(template('plan/setting-sj',{
                            name: opt.name,
                            items: opt.nr
                        }))
                    }else{
                        var div = document.createElement('div')
                        div.className = 'set-icon'+opt.numid;
                        $('.link-set-word').append(div);
                        if(opt.nr.length) {
                            $('.set-icon' + opt.numid).html(template('plan/setting-sj', {
                                name: opt.name,
                                items: opt.nr
                            }))
                        }
                    }
                }
            });
            for(var i = 0;i<data.length;i++){
                if(data[i].children && data[i].children.length){
                    rendercc(data[i].children,data[i].id)
                }
            }
            var cz = $('.modal-footer .btn-default');
            $('<a class="btn btn-default js-modal-cz">重置</a>').insertAfter(cz);
            opt.nr.splice(0,opt.nr.length)
        };
        //子集渲染
        function rendercc(data,id){
            $('.tree-box-'+id+'').html(template('plan/classification', {
                items: data
            }))
            for(var i = 0;i<data.length;i++){
                if(data[i].children && data[i].children.length){
                    rendercc(data[i].children,data[i].id)
                }
            }
        };
        //倒序操作
        function resevsePT(id){
            var idt = $('.tree-'+id).parent()[0].className;
            var idT = idt.substring(9,idt.length-10);
            var lens = $('.tree-box-'+idT).children('ul').get().length;
            var checkNum = 0;
            for (var i = 0; i < lens; i++) {
                //同级classNAME
                var idC
                var lit = $('.tree-box-' + idT ).children('ul').get(i).className;
                if(lit.indexOf('tree-line') > -1){
                    idC = lit.substring(27, lit.length)
                }else{
                    idC = lit.substring(19, lit.length)
                }
                if($('#'+idC)[0].checked){
                    checkNum++
                }
            }
            if($('#'+idT)[0]){
                if(checkNum === lens){
                    $('#'+idT)[0].checked = true
                    $('#'+idT)[0].parentNode.style['background']='url("images/tree_icons.png") no-repeat -224px -18px'
                    resevsePT(idT)
                }else{
                    $('#'+idT)[0].checked = false
                    $('#'+idT)[0].parentNode.style['background']='url("images/tree_icons.png") no-repeat -208px -18px'
                    resevsePT(idT)
                }
            }
        };
        //数据暴露
        function SJBL(id){
            var lens = $('.tree-box-' + id).children('ul').get().length;
            for(var i = 0;i<lens;i++){
                var lit =  $('.tree-box-' + id).children('ul').get(i).className;
                var idC
                if(lit.indexOf('tree-line') > -1){
                    idC = lit.substring(27, lit.length)
                }else{
                    idC = lit.substring(19, lit.length)
                }
                var fh = $('span[data-id='+idC+']').text()
                if($('#'+idC)[0].checked){
                    if(fh !== '-'){
                        opt.xzn ={
                            id:idC,
                            text: $('#'+idC)[0].getAttribute('text')
                        }
                        opt.nr.push(opt.xzn)
                    }else{
                        SJBL(idC)
                    }
                }else{
                    SJBL(idC)
                }
            }
        }
        //保存
        function savaChecked(){
            var lens = $('.modal-body ').children('ul').get().length;
            for(var i = 0;i<lens;i++){
                var lit =  $('.modal-body').children('ul').get(i).className;
                var idC
                if(lit.indexOf('tree-line') > -1){
                    idC = lit.substring(27, lit.length)
                }else{
                    idC = lit.substring(19, lit.length)
                }
                var fh = $('span[data-id='+idC+']').text()
                if($('#'+idC)[0].checked){
                    if(fh !== '-'){
                        opt.xzn ={
                            id:idC,
                            text: $('#'+idC)[0].getAttribute('text')
                        }
                        opt.nr.push(opt.xzn)
                    }else{
                        SJBL(idC)
                    }
                }else{
                    SJBL(idC)
                }
            }
        }
        //回显
        function showBack(){
            if( $('.set-icon'+opt.numid)){
                var lenb = $('.set-icon'+opt.numid+' ul li').length;
                for(var i =0;i<lenb;i++){
                    $('#'+$('.set-icon'+opt.numid+' ul li')[i].dataset.id)[0].checked =true
                    $('#'+$('.set-icon'+opt.numid+' ul li')[i].dataset.id).parent().css('background','url("images/tree_icons.png") no-repeat -224px -18px')
                    resevsePT($('.set-icon'+opt.numid+' ul li')[i].dataset.id)
                    if( $('#'+$('.set-icon'+opt.numid+' ul li')[i].dataset.id)[0].getAttribute('child')){
                        var lenss = $('.tree-box-'+$('.set-icon'+opt.numid+' ul li')[i].dataset.id +' [name=choose]').length
                        for (var j= 0; j < lenss; j++) {
                            $('.tree-box-'+$('.set-icon'+opt.numid+' ul li')[i].dataset.id+' [name=choose]')[j].checked = true
                            $('.tree-box-'+$('.set-icon'+opt.numid+' ul li')[i].dataset.id+' [name=choose]')[j].parentNode.style['background']='url("images/tree_icons.png") no-repeat -224px -18px'
                        }
                    }
                }
            }
        }
        getList()
        //点击操作
        $('body').on('click', '[name=choose]', function()  {
            if($(this)[0].checked){
                $(this).parent().css('background','url("images/tree_icons.png") no-repeat -224px -18px')
            }else{
                $(this).parent().css('background','url("images/tree_icons.png") no-repeat -208px -18px')
            }
            var id = $(this)[0].id
            var childs = $(this)[0].getAttribute('child')
            if(childs) {
                var len = $('.tree-box-'+id +' [name=choose]').length
                if ($(this)[0].checked) {
                    for (var i = 0; i < len; i++) {
                        $('.tree-box-'+id+' [name=choose]')[i].checked = true;
                        $('.tree-box-'+id+' [name=choose]')[i].parentNode.style['background']='url("images/tree_icons.png") no-repeat -224px -18px'
                    }
                }else{
                    for (var i = 0; i < len; i++) {
                        $('.tree-box-'+id+' [name=choose]')[i].checked = false;
                        $('.tree-box-'+id+' [name=choose]')[i].parentNode.style['background']='url("images/tree_icons.png") no-repeat -208px -18px'
                    }
                }
            }
            resevsePT(id)
            if(opt.odd ){
                var lenss = $('[name=choose]').length
                for (var i = 0; i < lenss; i++) {
                    if ($('[name=choose]')[i] !== $(this)[0] && $(this)[0].checked) {
                        $('[name=choose]')[i].disabled = true
                        $('[name=choose]')[i].parentNode.style['background']='url("images/tree_icons.png") no-repeat -224px -18px'
                    } else {
                        $('[name=choose]')[i].disabled = false
                        $('[name=choose]')[i].parentNode.style['background']='url("images/tree_icons.png") no-repeat -208px -18px'
                    }
                }
            }
        });
        //子集显示
        var timeopen;
        $('body').on('click','.open-tree', function () {
            var id = $(this).data('id');
            var $el = $('.tree-box-'+id),
                _height = 0;
            clearTimeout(timeopen);
            if($(this)[0].innerText === '+'){
                $(this).css('background','url("images/zTreeStandard.png") no-repeat -92px -54px')
                $(this).next().css('background','url("images/zTreeStandard.png") no-repeat -110px -15px')
                $(this)[0].innerText = '-'
                $el.children('ul').each(function () {
                    _height += $(this).height();
                })
                $el.css({
                    height: _height,
                })
                timeopen = setTimeout(function () {
                    $el.height('auto');

                }, 500)
            }else{
                $(this).css('background','url("images/zTreeStandard.png") no-repeat -74px -54px')//1111111
                $(this).next().css('background','url("images/zTreeStandard.png") no-repeat -110px 0')//1111
                $(this)[0].innerText = '+'
                $el.children('ul').each(function () {
                    _height += $(this).height();
                })
                $el.css({
                    height: _height,
                })
                $el.height(0);
            }
        });
        //重置
        $('body').on('click', '.js-modal-cz', function () {
            var lena = $('[name=choose]').length
            for (var i = 0; i < lena; i++) {
                $('[name=choose]')[i].checked = false
                $('[name=choose]')[i].parentNode.style['background']='url("images/tree_icons.png") no-repeat -208px -18px'
            }
        });
    };
    // 获取配置项
    function getTaskSet() {
        var rangId = $('.js-range').find("option:selected").val();
        $.getDataPlan({
            url: '/category/getCategoryTypeByCategoryId',
            query: {
                categoryId: rangId
            }
        }, function (data) {
            var _data = [];
            if (data && data.length) {
                for (var i in data) {
                    _data.push({
                        title: data[i].asName,
                        value: data[i].asId
                    });
                }
            }
            renderTaskSet(_data);
        });
    };
    // 获取专业
    function getTaskRang() {
        $.getDataPlan({
            url: '/category/getCategoryByType',
            query: {
                typeName: type ? '安保类': '检验类',
                type: type ? 1 : 0
            }
        }, function (data) {
            if (data && data.length) {
                var _data = [];
                var defaultData = {
                    title: '请选择',
                    value: ''
                };
                _data.push(defaultData);
                for (var i in data) {
                    _data.push({
                        title: data[i].pcName,
                        value: data[i].id
                    });
                }
                renderTaskRang(_data);
            }
        });
    }//1111111111111111
    // 获取模板
    function getTaskPlan() {
        var rangId = $('.js-range').find("option:selected").val();
        $.getDataPlan({
            url: '/category/getTempletes',
            query: {
                id: rangId
            }
        }, function (data) {
            if (data && data.length) {
                var _data = [];
                for (var i in data) {
                    _data.push({
                        title: data[i].tpName,
                        value: data[i].id
                    });
                }
                renderTaskPlan(_data);
            }
        });
    }
    // 渲染模板
    function renderTaskPlan(data) {
        $('.js-plan').html(template('plan/select', {
            id: 'plan',
            items: data
        }));
    }
    //渲染专业
    function renderTaskRang(data) {
        $('.js-range').html(template('plan/select', {
            id: 'rang',
            items: data
        }));
    }//11111111111111111111
    // 渲染配置对象
    function renderTaskSet(data) {
        $('.link-set').html(template('plan/listcom', {
            items: data
        }));
    }
    function init() {
        getTaskRang(); // 获取专业范畴
        $('.js-addtask-btmcom').html(template('plan/addtask-btm'));
        $('.js-addtask-left').html(template('plan/addtask-left', {
            type: type ? type : 0
        }));
        $('.js-addtask-topcom').html(template('plan/addtask-top'));
        $('.js-addtask-btmcom').html(template('plan/addtask-btm'));
        commonNum();
        $.dept({
            el: '.js-dept-selector',
            type: 'emp'
        });
        // 日历
        $('#dateStart,#dateEnd').datetimepicker({
            language: 'zh-CN',
            // format: 'yyyy-mm-dd hh:ii',
            // minView: 'year',
            autoclose: true,
            startView: 2,
            minView: 0
        });
        // 日期
        $('#dayStart,#dayEnd').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            // minView: 'year',
            autoclose: true,
            startView: 2,
            minView: 2
        });
        // ('#dayStart').datetimepicker();
        $('#dayStart,#dayEnd').val(getNowFormatDate());
        dateShow();
        //选择日期
        var times = $.initTimeList();
        var sTiems = [],
            eTimes = [];
        for (var i in times) {
            sTiems.push({
                title: times[i].startTime,
                value: i
            });
            eTimes.push({
                title: times[i].endTime,
                value: i
            });
        }
        // 初始化开始时间和结束时间
        $("#start-time").html(template("common/select", {
            items: sTiems
        })
        ).val(setCurTime(sTiems));
        $("#end-time").html(
            template("common/select", {
                items: eTimes,
                value: i
            })
        );
        var dataYear = [];
        dataYear.push(curYear, curYear + 1, curYear + 2);
        var curData = [];
        for (var i in dataYear) {
            curData.push({
                title: dataYear[i],
                value: i
            });
        }
        // 初始化当前年
        $('#selectYear').html(template("common/select", {
            items: curData
        }))
        //初始化星期
        var daysShort = ["周一", "周二", "周三", "周四", "周五", "周六", "周日"];
        var weekData = [];
        for (var i in daysShort) {
            weekData.push({
                title: daysShort[i],
                value: i
            });
        }
        $('.weekBox').html(template("common/label", {
            items: weekData
        }));
        // 配置框显示隐藏
        $(document).on('click', '.js-set', function (e) {
            e.stopPropagation();
            $('.link-set').toggle('display');
            getTaskSet();
        });
        $(document).on('click', '.type-item', function () {
            if ($(this).hasClass('type-selected')) {
                $(this).removeClass('type-selected');
            } else {
                $(this).addClass('type-selected');
            }
            var selectedArr = $('.type-selected');
            typeSelect = [];
            for (var i = 0; i < selectedArr.length; i++) {
                var item = {
                    asId: selectedArr[i].dataset.id,
                    asName: selectedArr[i].dataset.name
                };
                typeSelect.push(item);
            }
        });
        // 确定关联对象
        $(document).on('click', '.js-sure', function () {
            // 渲染选中的对象
            $('.link-set-word').html(template('plan/setting-word', {
                items: typeSelect
            }));
            $('#setItem').modal('hide');
        });
        // 专业范畴切换
        $(document).on('change', '.js-range', function () {
            if ($('#range').val()) {
                $('.type-plan').show(); // 显示方案
                $('.link-con').show(); // 显示关联对象
                getTaskPlan();
                getTaskSet();
            }else{
                // 隐藏方案和关联对象
                $('.type-plan').hide();
                $('.link-con').hide();
            }
        });
        // 计划切换
        $(document).on('change', '.js-step', function () { // 任务阶段
            getTaskPlan();
        });
        // 隐藏配置框
        $(document).on('click', 'body', function () {
            $('.link-set').css('display', 'none');
        });
        // 配置类型
        $(document).on('click', '.link-set', function (e) {
            e.stopPropagation();
            // 选中的关联对象
            var currentSelect = e.target.className;
            var nameSelect = e.target.innerText;
            switch (nameSelect) {
                case '空间主数据':
                    tree({
                        numid:1,
                        name: nameSelect,
                        el: currentSelect,
                        url:'/space/getSpaceTree',
                        convertList:function(data,row){
                            set1 = data
                            if(data.length){
                                for(var i=0; i<data.length; i++){
                                    var str={"id":""+data[i].value+"","parentId":""+data[i].parent+"","checked":false,"name":""+data[i].name +"","children":""}
                                    row.push(str);
                                }
                            }
                        }
                    });
                    break;
                case '物料主数据':
                    tree({
                        numid:2,
                        name: nameSelect,
                        el: currentSelect,
                        url:'/task/getMaterielData',
                        convertList:function(data,row){
                            set2 = data
                            if(data.length){
                                for(var i=0; i<data.length; i++){
                                    var str={"id":""+data[i].id+"","parentId":""+data[i].parent+"","checked":false,"name":""+data[i].name +"","children":""}
                                    row.push(str);
                                }
                            }
                        }
                    });
                    break;
                case '设备主数据':
                    tree({
                        numid:4,
                        name: nameSelect,
                        el: currentSelect,
                        url:'/task/getDevicesData',
                        convertList:function(data,row){
                            set4 = data
                            if(data.length){
                                for(var i=0; i<data.length; i++){
                                    var str={"id":""+data[i].id+"","parentId":""+data[i].parent+"","checked":false,"name":""+data[i].name +"","children":""}
                                    row.push(str);
                                }
                            }
                        }
                    });
                    break;
                case '供应商主数据':
                    tree({
                        numid:3,
                        name: nameSelect,
                        el: currentSelect,
                        url:'/admin/mdmSupplierInfo/getListSupplierInfo',
                        query:{
                            pageNo:1,
                            pageSize:100000000
                        },
                        convertList:function(data,row){
                            set3 = data
                            if(data.length){
                                for(var i=0; i<data.length; i++){
                                    var str={"id":""+data[i].id+"","parentId":""+data[i].parent+"","checked":false,"name":""+data[i].supplierName +"","children":""}
                                    row.push(str);
                                }
                            }
                        }
                    })
                    break;
            }
        });
    }

    init();
});
$(function(){
    // 收集数据
    function collectData( periodValue ) {
        // console.log(periodValue);
        var rs = {};
        if($.trim($('#tplName').val()) === ''){
            $.alert('请输入模板名称');
            return false;
        }
        if (periodValue == 1 && $.trim($('#yearName').val()) === '') {
            $.alert('请输入年度名称');
            return false;
        }
        
        // 校验半年 季度 月 名称
        if($('.halfYearName')){
            for(var i = 0; i < $('.halfYearName').length; i++){
                if($.trim($('.halfYearName')[i].value) === ''){
                    $.alert('请输入半年名称');
                    return false;
                }
            }
        }
        if($('.qutName')){
            for(var i = 0; i < $('.qutName').length; i++){
                if($.trim($('.qutName')[i].value) === ''){
                    $.alert('请输入季度名称');
                    return false;
                }
            }
        }
        for(var i = 0; i < $('.month-name').length; i++){
            if($.trim($('.month-name')[i].value) === ''){
                $.alert('请输入月份名称');
                return false;
            }
        }
        for(var i = 0; i < $('.start-input').length; i++){
            if($.trim($('.start-input')[i].value) === ''){
                $.alert('请输入开始时间');
                return false;
            }
        }
        for(var i = 0; i < $('.end-input').length; i++){
            if($.trim($('.end-input')[i].value) === ''){
                $.alert('请输入结束时间');
                return false;
            }
        }
        for(var i = 0; i < $('.start-input').length; i++){
            var start = new Date($('.start-input')[i].value).getTime();
            var end = new Date($('.end-input')[i].value).getTime();
            if(start > end){
                $.alert('开始时间不能大于结束时间');
                return false;
            }
        }
        var startDate = [];
        var endDate = [];
        var startNumberDate = [];
        var endNumberDate = [];
        $('.start-input').each(function(){
            startDate.push($.trim(this.value));
        })
        $('.end-input').each(function(){
            endDate.push($.trim(this.value));
        })
        startDate.forEach(function(item){
            if(item){
                startNumberDate.push(new Date(item).getTime());
            }
        })
        endDate.forEach(function(item){
            if(item){
                endNumberDate.push(new Date(item).getTime());
            }
        })
        var startMin = startNumberDate[0];
        var endMax = endNumberDate[0];
        for(var i = 0; i < startNumberDate.length; i++){
            if(startMin > startNumberDate[i]){
                startMin = startNumberDate[i];
            }
        }
        for(var i = 0; i < endNumberDate.length; i++){
            if(endMax < endNumberDate[i]){
                endMax = endNumberDate[i];
            }
        }
        var time = (endMax - startMin)/(24 * 60 * 60 * 1000);
        if(periodValue == 1 || periodValue == 2){
            if(periodValue == 2 && time > 366){
                $.alert('最早开始时间和最晚结束时间不能超过366天');
                return false;
            }
            if(periodValue == 1 && time > 366){
                $.alert('最早开始时间和最晚结束时间不能超过366天');
                return false;
            }
            var halfYearList = [];
            var halfYearListData = [];
            for(var i = 0; i < $('.halfYearName').length; i++){
                halfYearList[i] = {};
                // if(periodValue == 1){
                //     halfYearList[i].yearName = $('#yearName').val();
                // }
                halfYearList[i].halfYearName = $('.halfYearName')[i].value;
                halfYearList[i].qut = [];
                var qutListData = [];
                var monthListData = [];
                for(var j = 0; j < 2; j++){
                    halfYearList[i].qut[j] = {};
                    halfYearList[i].qut[j].qutName = $('.qutName')[i * 2 + j].value; 
                    halfYearList[i].qut[j].month = [];
                    for(var k = 0; k < 3; k++){
                        halfYearList[i].qut[j].month[k] = {};
                        halfYearList[i].qut[j].month[k].monthName = $('.month-name')[(i * 2 + j) * 3 + k].value;
                        halfYearList[i].qut[j].month[k].startDate = $('.start-input')[(i * 2 + j) * 3 + k].value;
                        halfYearList[i].qut[j].month[k].endDate = $('.end-input')[(i * 2 + j) * 3 + k].value;
                    }
                }
                halfYearListData.push($('.halfYearName')[i].value);
            }
            for(var i = 0; i < $('.qutName').length; i++){
                qutListData.push($('.qutName')[i].value)
            }
            for(var i = 0; i < $('.month-name').length; i++){
                monthListData.push($('.month-name')[i].value);
            }
            if(periodValue == 1){
                var result = {};
                result.yearName = $('#yearName').val();
                result.halfYearList = halfYearList;
                var resultData = {};
                resultData.list = result;
                resultData.dataList = {};
                resultData.dataList.year = $('#yearName').val();
                resultData.dataList.halfYear = halfYearListData;
                resultData.dataList.quarter = qutListData;
                resultData.dataList.month = monthListData;
                rs.content = JSON.stringify(resultData);
            }else if(periodValue == 2){
                var resultData = {};
                resultData.list = halfYearList;
                resultData.dataList = {};
                resultData.dataList.halfYear = halfYearListData;
                resultData.dataList.quarter = qutListData;
                resultData.dataList.month = monthListData;
                rs.content = JSON.stringify(resultData);
            }
            rs.templateName = $('#tplName').val();
            rs.cycle = Number($('#period').val());
            rs.description = $('#confereDesc').val();
        }
        if(periodValue == 3){
            if(time > 366){
                $.alert('最早开始时间和最晚结束时间不能超过366天');
                return false;
            }
            var qutList = [];
            var qutListData = [];
            var monthListData = [];
            for(var i = 0; i < $('.qutName').length; i++){
                qutList[i] = {};
                qutList[i].qutName = $('.qutName')[i].value;
                qutList[i].month = [];
                for(var j = 0; j < 3; j++){
                    qutList[i].month[j] = {};
                    qutList[i].month[j].monthName = $('.month-name')[i * 3 + j].value;
                    qutList[i].month[j].startDate = $('.start-input')[i * 3 + j].value;
                    qutList[i].month[j].endDate = $('.end-input')[i * 3 + j].value;
                }
                qutListData.push($('.qutName')[i].value);
            }
            for(var i = 0; i < $('.month-name').length; i++){
                monthListData.push($('.month-name')[i].value);
            }
            var resultData = {};
            resultData.list = qutList;
            resultData.dataList = {};
            resultData.dataList.quarter = qutListData;
            resultData.dataList.month = monthListData;
            rs.content = JSON.stringify(resultData);
            rs.templateName = $('#tplName').val();
            rs.cycle = Number($('#period').val());
            rs.description = $('#confereDesc').val();
        }
        if(periodValue == 4){
            if(time > 366){
                $.alert('最早开始时间和最晚结束时间不能超过366天');
                return false;
            }
            var monthList = [];
            var monthListData = [];
            for(var i = 0; i < $('.month-name').length; i++){
                monthList[i] = {};
                monthList[i].monthName = $('.month-name')[i].value;
                monthList[i].startDate = $('.start-input')[i].value;
                monthList[i].endDate = $('.end-input')[i].value;
                monthListData.push($('.month-name')[i].value);
            }
            var resultData = {};
            resultData.list = monthList;
            resultData.dataList = {};
            resultData.dataList.month = monthListData;
            rs.content = JSON.stringify(resultData);
            rs.templateName = $('#tplName').val();
            rs.cycle = Number($('#period').val());
            rs.description = $('#confereDesc').val();
        }
        console.log(rs.content);
        return rs;
        
    }
    // 保存数据
    function saveData (data,type) {
        console.log(data);
        $.getData({
            url: '/template/save',
            body: data
        }, function (data) {
            if (data && type===1) {
                location.href = './tpl-relation.html?id='+ data.id;
            }else{
                window.history.back();
            }
        });
    }
    // 时间插件
	function dateShow () {
		$('.js-date').datetimepicker({
            language: 'zh-CN',
            autoclose: true,
            startView: 2,
            minView: 2
        });
	}
    function init(){
        
        $('.js-add-top').html(template('common/tpl-add-top', {
            item: {},
        }));
        
        $('.js-add-btm').html(template('common/tpl-add-btm', {
            item: {},
        }));
        // 渲染周期下拉
        var periodList = [{"value": 1, "title": "全年"}, {"value": 2, "title": "半年"}, {"value": 3, "title": "季度"}, {"value": 4, "title": "月"}];
        $('#period').html(template('common/select-type', {
			items: periodList
        }));
        var list = [
            {"qut": [ {"month": [{},{}, {}]}, {"month": [{}, {},{}]}], "allYear": true},
            {"qut": [ {"month": [{},{}, {}]}, {"month": [{}, {},{}]}], "allYear": true},
        ];
        $('.js-add-mid').html(template('common/tpl-add-mid', {
            items: list,
        }));
        dateShow();
        var periodValue = $('#period').val();
        var _periodValue = 1;
        var _list =[];
        // 周期下拉change
        $(document).on('change', '#period', function () {
            var periodValue = $('#period').val();
            var list = [];
            if(periodValue == 1){
                var list = [
                    {"qut": [ {"month": [{},{}, {}]}, {"month": [{}, {},{}]}], "allYear": true},
                    {"qut": [ {"month": [{},{}, {}]}, {"month": [{}, {},{}]}], "allYear": true},
                 ] 
            }else if(periodValue == 2){
                   list =[ {"qut": [ {"month": [{},{}, {}]}, {"month": [{}, {},{}]}], "allHalfYear": true}];
            }else if(periodValue == 3){
                list =[ {"qut": [ {"month": [{},{}, {}]}], "allqut": true}];
            }else if(periodValue == 4){
                list =[ {"qut": [ {"month": [{}]}], "allMonth": true}];
            }
            $('.js-add-mid').html(template('common/tpl-add-mid', {
                items: list,
            }));
            dateShow();
            _list =list;
            _periodValue =periodValue;
        });
        var monthHtml ='<div class="form-inline mb26">\
        <div class="form-group">\
            <label for="text">月份名称：</label>\
            <input type="text" class="form-control month-name" placeholder="" >\
        </div>\
        <div class="form-group">\
            <label for="type">开始日期</label>\
            <div class="input-group start-time-date">\
                <input type="text" class="form-control js-date start-input" readonly data-date-format="yyyy-mm-dd">\
                <span class="input-group-addon js-date-clean">\
                    <span class="glyphicon glyphicon-remove"></span>\
                </span>\
            </div>\
        </div>\
        <div class="form-group">\
            <label for="type">结束日期</label>\
            <div class="input-group end-time-date">\
                <input type="text" class="form-control js-date end-input" readonly data-date-format="yyyy-mm-dd">\
                <span class="input-group-addon js-date-clean">\
                    <span class="glyphicon glyphicon-remove"></span>\
                </span>\
            </div>\
        </div>';
        
        // 月 添加
        $(document).on('click', '.js-add-month', function(){
            if($('.month-name').length < 12){
                $('.middle-p').append($(monthHtml + '<div class="form-group">\
                  &nbsp;<button type="button" class="btn btn-default js-del-month" del-index={{monthItemIndex}}>删除</button>\
               </div>\
               </div>'));
                dateShow();
            }
        })
        // 月 删除
        $(document).on('click', '.js-del-month', function(e){
            var monIndex = $(this).attr("del-index");
            if(_list[0].qut[0].month.length < 12){
                $(this).parent().parent().remove();
            }
            // console.log(_list);
        })
        // 季度 添加
        $(document).on('click', '.js-add-qut', function(){
            // var monIndex = $(this).attr("del-index");
            var newMonthHtml = '';
            for(var i = 0; i < 3; i++){
                newMonthHtml = newMonthHtml + monthHtml+'</div>';
            }
            if($('.qutName').length < 4){
                $('.middle-p').append($(
                    '<div class="form-inline mb26">\
                        <div class="form-group">\
                            <label for="text">季度名称：</label>\
                            <input type="text" class="form-control qutName"  placeholder="">\
                        </div>\
                        <div class="form-group">\
                            <button type="button" class="btn btn-default js-del-qut" del-index={{qutItemIndex}}>删除</button>\
                        </div>\
                    </div>\
                    <div>'+
                    newMonthHtml +
                    '</div>'));
                dateShow();
            }
        })
        // 季度 删除
        $(document).on('click', '.js-del-qut', function(e){
                $(this).parent().parent().next().remove();
                $(this).parent().parent().remove();
        })
        // 半年 添加
        $(document).on('click', '.js-add-halfYear', function(){
            var newMonthHtml = '';
            var newHalfYearHtml = '';
            for(var i = 0; i < 3; i++){
                newMonthHtml = newMonthHtml + monthHtml+'</div>';
            }
            var newQut = '<div class="form-inline mb26">\
                    <div class="form-group">\
                        <label for="text">季度名称：</label>\
                        <input type="text" class="form-control qutName"  placeholder="">\
                    </div>\
                </div>\
                <div>'+
                newMonthHtml +
                '</div>';
            for(var i = 0; i < 2; i++){
                newHalfYearHtml = newHalfYearHtml + newQut;
            }
            if($('.halfYearName').length < 2){
                $('.middle-p').append($(
                    '<div class="form-inline mb26">\
                        <div class="form-group">\
                            <label for="text">半年名称：</label>\
                            <input type="text" class="form-control halfYearName" placeholder="">\
                        </div>\
                        <div class="form-group">\
                            <button type="button" class="btn btn-default js-del-halfYear" del-index={{itemIndex}}>删除</button>\
                        </div>\
                    </div>\
                    <div>'+
                    newHalfYearHtml +
                    '</div>'));
                dateShow();
            }
        })
        // 半年 删除
        $(document).on('click', '.js-del-halfYear', function(e){
            $(this).parent().parent().next().remove();
            $(this).parent().parent().remove();
        })
        $.counter({
            el: '#confereDesc', // 文本框 id，默认 #textarea
            count: '.counttask', // 计数器 id，默认 .js-count
            max: 200 // 输入最大长度值，默认 200
        });
        // 保存
        $(document).on('click', '.js-save', function () {
            var data = collectData( _periodValue );
            if (data) {
                saveData(data,0);
            }
        });
        //保存并关联任务
        $(document).on('click', '.js-save-detail', function () {
            var data = collectData(_periodValue);
            if (data) {
                saveData(data,1);
            }
        });
        // 取消
        $(document).on('click', '.js-cancel', function () {
            history.back();
        });
        
        // 清空日期选择
        $(document).on('click', '.js-date-clean', function () {
            $(this).prev().val('');
        });
    }
    init();
});
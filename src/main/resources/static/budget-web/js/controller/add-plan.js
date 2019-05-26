$(function () {
    // var searchParams = {};
    // 收集数据
    function collectData() {
        var rs = {};
        if ($.trim($('#name').val()) === '') {
            $.alert('请输入计划名称');
            return false;
        }
        if(!$('.js-budget1-dept').val()){
            $.alert('请选择计划部门');
            return false;
        }else {
            var depArray = $('.js-budget1-dept').val().split(",");
            if(depArray.length > 1){
                $.alert('只允许选择一个部门');
                return false;
            }
        }
        if(!$('#template').val()){
            $.alert('请在模板管理中创建预算模板并关联数据');
            return false;
        }
        
        rs.recordName = $.trim($('#name').val());
        rs.cycle = $.trim($('#period').val());
        rs.deptId = $.trim($('.js-budget1-dept').val());
        rs.deptName  = eval("("+ $('.js-budget1-all').val() +")")[0].title;
        rs.typeName = $.trim($('#type option:selected').text());  
        rs.year = $.trim($('#year').val());
        rs.cycle = $.trim($('#period').val());
        rs.templateId = $.trim($('#template').val());
        rs.templateName = $.trim($('#template option:selected').text());
        return rs;
    }
    // 保存数据
    function saveData (data,type) {
        $.getData({
            url: '/budget/recordAdd',
            body: data
        }, function (data) {
            // console.log(data);
            if (data && type===1) {
                console.log(data);
                location.href = './input-budget.html?id='+ data + '&type=1';
            }else{
                window.history.back();
            }
        });
    }
    function getPullMenus(){
		// 渲染预算周期下拉
		var periodList = [{"value": 1, "title": "全年"}, 
			{"value": 2, "title": "半年"}, 
			{"value": 3, "title": "季度"}, 
			{"value": 4, "title": "月"}];
        $('#period').html(template('common/select-type', {
			items: periodList
        }));
        // 预算类型渲染
        var typeList = [{"value": 1, "title": "财务支出"}, 
			{"value": 2, "title": "采购支出"}];
        $('#type').html(template('common/select-type', {
			items: typeList
        }));
        // console.log($('#type option:selected').text());
		// 预算年份
		var yearDate = new Date();
		yearDate = yearDate.getFullYear();
		var dateList = [];
		for(var i = 0; i <= 10; i++){
			dateList[i] = {};
			dateList[i].value = yearDate + i;
			dateList[i].title = yearDate + i;
		}
		$('#year').html(template('common/select-type', {
			items: dateList
        }));
        // 计划部门渲染
        $.dept({
            name:'budget1',
            el: '.js-dept-selector',
            type: 'dept',
        });
        // 选择模板渲染
        getTpl(1);
        $('#name').val('');
        
    }
    // 获取模板
    function getTpl(num){
        $.getData({
			url: '/budget/getTemplateByCycle',
			query: {
				cycle: num
			}
		}, function(data) {
            var tplDataList = [];
            for(var i = 0; i < data.length; i++){
                tplDataList[i] = {};
                tplDataList[i].value = data[i].id;
                tplDataList[i].title = data[i].templateName;
            }
            $('#template').html(template('common/select-type', {
                items: tplDataList
            }));
		});
    }
    function init() {
        getPullMenus();
		// 保存
        $(document).on('click', '.js-save', function () {
            var data = collectData();
            if (data) {
                saveData(data,0);
            }
        });
        //保存并关联任务
        $(document).on('click', '.js-save-detail', function () {
            var data = collectData();
            if (data) {
                saveData(data,1);
            }
        });
        // 取消
        $(document).on('click', '.js-cancel', function () {
            history.back();
        });
        // 根据周期获取模板
        $(document).on('change', '#period', function () {
            console.log($(this));
            getTpl($(this).val());
        });
	}
	init();
});
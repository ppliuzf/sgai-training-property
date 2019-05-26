$(function () {
     var fromPage = $.getQueryString('type'),
     tpId = $.getQueryString('tpId'),
     // tpId = '0aa4a1edd5d4408fb0f11f2ef9be8ae5',
     recordId = $.getQueryString('record_id'),
     dt = $.getQueryString('dt'),
	 flag = $.getQueryString('flag'),
     // recordId = '0fb9e53cdbde43a89aca43c37cf6c492';
     maxNum = '',
     minNum = '',
     numError = '', // 是否发起了整改
     // currentItemOver = '',
     checkedId = '';
	function renderAside(data) {
        $('.js-side').html(template('task/detail-side', {
            items: data
        }));
    }
    //
    	function getDetail(checkedId) {
		$.getData({
			// url: '/testitem/detailTestItem',
			url: '/taskresult/detailTestItem',
			query: {
				itemId: checkedId,
				recordId: recordId,
				dateTime:dt,
				tpId: tpId
			}
		}, function (data) {
			//如果已发起整改，显示出来
			maxNum = parseInt(data.tiMax);
			minNum = parseInt(data.tiMin);
			isNumType =data.tiIsInput;//0数值型，1单选型
			var enclosures=data.enclosures;
			data.enclosures=eval("("+enclosures+")"); //转换为数组
			//单选类型转换为数组
			if(data.tiOptions && data.tiStandardOption){
				var newArr=arrData(data.tiOptions,data.tiStandardOption);
				data.newOptions=newArr;
			}
			renderDetail(data);
		});
	}
	function arrData(tiOptions,tiStandardOption){
		var arr=tiOptions.split('|'),
			newArr=[]
		arr.map(function(item){
			if($.trim(item)===$.trim(tiStandardOption)){
				newArr.push({value:item,isOk:1});
			}else{
				newArr.push({value:item,isOk:0});
			}
		});
		return newArr;
		
	}
	// 渲染详情
	function renderDetail(data) {
		var tiOptions = data.tiOptions ? data.tiOptions.split(' | ') :[];
		$('.js-task-detail').html(template('task/detail-task',{items:data, dataTiOptions:tiOptions,fromPage:fromPage}));
		// if(data.tiStatus!==0 && data.tiHasDefect){ // 发起整改时，显示出整改信息
		
		if (data.tiStatus ==2 && data.tiHasDefect) {
			$(".change-list").removeClass('hidden');
		}
		
		if(data.tiStatus==1 ||data.tiStatus==2 ){ // 数据来了后更改
			$('.task-btn').addClass('hidden');
			$('.task-goback').removeClass('hidden');
		}
	}
	function getItemDatas() {
		$.getDataPlan({
			url: '/taskresult/taskItemStatusList',
			query: {
				tpId: tpId,
				recordId:recordId,
				// dt:dt.toString()
				// dt:parseInt(dt)
				dateTime:dt
			}
		}, function(data) {
			if (data.uncheckedTaskId==''){
				checkedId = data.lists.length ? data.lists[0].taskId : 0;
			}else{
				checkedId = data.uncheckedTaskId;
			}
			// currentItemOver= data.uncheckedTaskId =='';
			renderAside(data);
			getDetail(checkedId);
		});
	}
		$(document).on('click', '.r-checked', function () {
			var index = $(this).parent().index();
			var objPrs = $(this).parents('.task-result');
			var  checkedParent = $('.r-checked:checked').parent();
			objPrs.find('p').find('.icon').removeClass('icon0').removeClass('icon1').removeClass('icon2');
			if($(this).is(':checked')){
				$('.change-list').addClass('hidden');//隐藏缺陷
				$('.change-checked').attr('checked',false);//隐藏缺陷
				if(checkedParent.find('.icon').text()=='√') {
					objPrs.find('p').eq(index).find('.icon').addClass('icon0');
				}else{
					objPrs.find('p').eq(index).find('.icon').addClass('icon1');
				}
				// objPrs.find('p').eq(index).find('.icon').addClass('icon'+index);
				if (checkedParent.find('.icon').text()=='√') {
					objPrs.find('.error').addClass('hidden');
				} else {
					objPrs.find('.error').removeClass('hidden');
				}
			}else{
				objPrs.find('p').eq(index).find('.icon').removeClass('icon'+index);
			}
		});
		//整改
		$(document).on('click', '.change-checked', function () {
			if($(this).is(':checked')){
				$('.change-list').removeClass('hidden');
			}else{
				$('.change-list').addClass('hidden');
			}
		});
		$(document).on('click', '.js-opt', function () {
			var hasType = $(this).find('span').text();
			// currentItemOver= (hasType =='合格') || (hasType ='缺陷');
			checkedId = $(this).data('id');
			// checkedId= tpId
			$('.task-side').find('dl').removeClass('checked');
			$(this).parent('dl').addClass('checked');
			if(hasType =='未检查'){// 未检查时，显示保存，取消按钮
				$('.task-btn').removeClass('hidden');
				$('.task-goback').addClass('hidden');
			}
			getDetail(checkedId);
		});
		$(document).on('click', '.js-save', function () {
			// if (isHasChecked || isInputText){
			var isHasChecked = $('.change-checked').is(':checked');
				if (isNumType ==0 && $('.ipt-number').val() !==''){ // 数值型
					var testItemDto = {
						id:checkedId,
						dateTime:dt,
						recordId:recordId,
						tpId:tpId,
						tiHasDefect: isHasChecked? 1: 0,
						tiInputResult:$('.ipt-number').val(),
						tiIsSubmit: 1,
						tiStatus:numError==1 ? 2 :1
					}
				} else if(isNumType==1 && $('.r-checked').is(':checked')){ // 单选型
					var  checkedParent = $('.r-checked:checked').parent();
					var testItemDto = {
						recordId:recordId,
						dateTime:dt,
						id:checkedId,
						tpId:tpId,
						tiHasDefect: isHasChecked? 1: 0,
						tiInputResult:checkedParent.find('b').text(),
						tiIsSubmit: 1,
						tiStatus:checkedParent.find('.icon').text()=='√' ? 1 :2
					}
				} else {
					$.alert('请填写执行结果');
					return false;
				}
				$.getDataPlan({
					url: '/testitem/saveExecuteTestItem',
					body: testItemDto
				}, function(data) {
					init();
				});
		});
		
		$(document).on('blur', '.ipt-number', function () {
			var val = parseInt($(this).val());
			if (val > maxNum || val < minNum){
				$(this).addClass('error-border');
				numError = 1; //
				$(this).parents('.task-result').find('.error').removeClass('hidden');
			}else{
				$('.change-list').addClass('hidden');//隐藏缺陷
				$('.change-checked').attr('checked',false);//隐藏缺陷
				$(this).removeClass('error-border');
				$(this).parents('.task-result').find('.error').addClass('hidden');
			}
		});
		$(document).on('click', '.js-cancel', function () {
			// $.pop({
			// title: '取消',
			// type: 1,
			// content: '确认离开此页么？',
			// btn: ['确定', '取消'],
			// area: ['400px', '150px'],
			// yes: function(index, layero){
			// 	alert('0');
			// }
		});
		$(document).on('click', '.js-btn-back,.js-cancel,.task-goback', function () {
			if(flag == 0){
                window.location.href = 'task-management.html';
			}else if(flag == 1){
                window.location.href = 'task-per.html';
			}else{
                history.back();
			}
		});
	function init() {
		getItemDatas();
		// getDetail();
	}
	init();
})
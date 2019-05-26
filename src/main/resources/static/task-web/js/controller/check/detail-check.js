$(function () {
	var  id = $.getQueryString('id');
	var  sts = $.getQueryString('status');
	var recordId = $.getQueryString('recordId');
	if (id === undefined || id === 'undefined') {
		$.alert('检验项不存在！', function () {
			history.back();
		});
		return false;
	}
	// 获取详情数据
	function getDetail() {
		$.getData({
			url: '/taskresult/detailTestItem',
			query: {
				itemId: id,
				recordId: recordId
			}
		}, function (data) {
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
				newArr.push({value:$.trim(item),isOk:1});
			}else{
				newArr.push({value:$.trim(item),isOk:0});
			}
		});
		return newArr;
		
	}
	// 渲染详情
	function renderDetail(data) {
		console.log(data);
		
		$('.main').html(template('check/detail-check',{items:data, sts:sts}));
	}
	function evalArr (fn) {
		var Fn = Function; // 一个变量指向Function，防止有些前端编译工具报错this.$route.query.taskId
		return new Fn('return ' + fn)();
	}
	function init() {
		getDetail();
		// renderDetail({})
		// 返回
		$(document).on('click', '.js-check-back', function () {
			history.back();
		});
	}
	init();
});
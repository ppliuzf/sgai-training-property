$(function () {
	var  id = $.getQueryString('id');
	if (id === undefined || id === 'undefined') {
		$.alert('检验项不存在！', function () {
			history.back();
		});
		return false;
	}
	// 获取详情数据
	function getDetail() {
		$.getData({
			url: '/testitem/detailTestItem',
			query: {
				itemId: id
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
				newArr.push({value:item,isOk:1});
			}else{
				newArr.push({value:item,isOk:0});
			}
		});
		return newArr;
		
	}
	// 渲染详情
	function renderDetail(data) {
		$('.main').html(template('check/detail-check',{items:data}));
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
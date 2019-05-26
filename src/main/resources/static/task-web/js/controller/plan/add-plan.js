/**
 * Created by 137376 on 2017/12/22.
 */
var type = $.getQueryString('type');
// 收集图片地址
function collectImage() {
	var rs = [];
	$('.upload-item').each(function () {
		//var obj = {
		//	riImgUrl: $(this).data('url'),
		//	riIsDefault: 1,
		//	riSort: 0
		//}
		rs.push($(this).data('url'));
	});
	return rs;
}
// 收集数据
function collectData() {
	var rs = {};
	if ($.trim($('#name').val()) === '') {
		$.alert('请输入计划名称！');
		return false;
	}
	if (!$('.js--all').val()) {
		$.alert('请选择责任人！');
		return false;
	}
	// if ($('#instant-selected').val() === '') {
	// 	$.alert('请选择责任人！', '#instant-keywords');
	// 	return false;
	// }
	rs.recordName  = $.trim($('#name').val()); // 计划名称
	rs.recordDesc = $.trim($('#confereDesc').val()); // 描述
	if ($('.js--all').val()) {
		var participantMember = $.parseJSON($('.js--all').val());
	}
	rs.imgList = [];
	rs.idList = [];
	if (participantMember) {
		for (let p = 0;p < participantMember.length;p++) {
			rs.idList.push(participantMember[p].id);
		}
	}
	rs.typeId = '0';
	rs.typeName ='0';
	// rs.recordManager = $('#task-manager').is(':checked') ? 1 : 2; // 允许添加
	rs.recordManager = 0; // 允许添加
	return rs;
}

// 保存数据
function saveData (data,type) {
	$.getData({
		url: '/pc/plan/insert',
		body: data
	}, function (data) {
		if (data && type===1) {
			location.href = './pages/plan/detail.html?id='+ data.id;
		}else{
			window.history.back();
		}
	});
}
function init() {
	$('#name').val('');
	$('#confereDesc').val('');
	$('.js--all').val('');
	// getType(); //   获取类型
	$.counter({
		el: '#confereDesc', // 文本框 id，默认 #textarea
		count: '.counttask', // 计数器 id，默认 .js-count
		max: 100 // 输入最大长度值，默认 200
	});
	$.dept({
		el: '.js-dept-selector',
		type: 'emp'
	});
	// $.instantSearch({
	// 	url: '/orgTree/searchEmpInfoByName',
	// });
	// $.uploader({
	// 	url: '/uploadDown/uploadImage',
	// 	imageGroup: 'mm',
	// });
	//$.uploader();
	// 保存
	$(document).on('click', '.js-save', function () {
		var data = collectData();
		if (data) {
			if (type){
				data.type = 1;
			}
			saveData(data,0);
		}
	});
	//保存并关联任务
	$(document).on('click', '.js-save-detail', function () {
		var data = collectData();
		if (data) {
			if (type){
				data.type = 1;
			}
			saveData(data,1);
		}
	});
	// 取消
	$(document).on('click', '.js-cancel', function () {
		history.back();
	});
}
init();
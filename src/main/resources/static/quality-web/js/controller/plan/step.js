///**
// * Created by 137376 on 2017/12/25.
// */
(function() {
// 阶段管理开关
	$(document).on('click', '.js-checkbox', function () {
		if ($(this).is(':checked')){
			$('.approve-con').show();
		} else {
			$('.approve-con').hide();
		}
	});
	// 创建项
	function createItems(id) {
		var itemCreated = [{
			id: id ? id : 'time' + Date.now(),
			periodName: '',
			periodSort: 0,
			recordId: $.getQueryString('id'),
			recordName: $.getQueryString('name'),
			hasTask: 0
		}];
		console.log('*********', itemCreated);
		$('.items').append(template('plan/step-item', {
			items: itemCreated
		}));
		$('.items .disabled').removeClass('disabled');
		setTimeout(function () {
			$('.items .item').addClass('show');
		}, 20);
		$.kmSort({
			sortArea: '.items',
			sortItem: '.item',
			upEl: '.js-up',
			downEl: '.js-down'
		});
	}
	function getList () {
		$.getDataPlan({
			url: '/pc/period/periodList',
			query: {
				recordId: $.getQueryString('id')
			}
		}, function (data) {
			if (data.length > 1) {
				$('.items .disabled').removeClass('disabled');
				$('.approve-con').show();
				$('.js-checkbox').is(':checked');
				$('.js-checkbox').attr("checked","true");
				setTimeout(function () {
					$('.items .item').addClass('show');
				}, 20);
				renderList(data);
				$.kmSort({
					sortArea: '.items',
					sortItem: '.item',
					upEl: '.js-up',
					downEl: '.js-down'
				});
			}
			console.log('data', data);
		});
	}
	// 渲染列表
	function renderList(params) {
		$('.js-list').html(template('plan/step-item', {
			items: params
		}));
	}
	function collectData () {
		var paramsArr = [];
		var pass = false;
		$('.item').each(function (index) {
			var $txtInput = $(this).find('input');
			if ($txtInput.length && $.trim($txtInput.val()) !== '') {
				var sPaid;
				if ($(this).attr('data-id').indexOf('time') >= 0) {
					sPaid = '';
				} else {
					sPaid = $(this).attr('data-id');
				}
				paramsArr.push({
					id: sPaid,
					periodName: $.trim($txtInput.val()),
					periodSort: index,
					recordId: $.getQueryString('id'),
					recordName: $.getQueryString('name')
				});
			} else {
				$.alert('请输入阶段名称！');
				pass = true;
			}
		});
		if (pass) {
			pass = false;
			return false;
		} else {
			return paramsArr;
		}
	}
	function saveData (data) {
		$.getDataPlan({
			url: '/pc/period/save',
			body: data
		}, function (data) {
			if (data) {
				window.history.back();
			}
		});
	}
	function removePeriod (paId) {
		$.getDataPlan({
			url: '/pc/period/deleteById',
			query: {
				id: paId
			}
		}, function (data) {
			if (data) {
				getList();
			}
		});
	}
	function init() {
		getList();
		// 排序
		$.kmSort({
			sortArea: '.items',
			sortItem: '.item',
			upEl: '.js-up',
			downEl: '.js-down'
		});
		// 添加
		$(document).on('click', '.js-add', function () {
			createItems();
		});
		// 删除
		$(document).on('click', '.js-remove', function () {
			if ($(this).attr('data-pid').indexOf('time') >= 0) {
				$(this).parents('.item').remove();
			} else {
				removePeriod($(this).attr('data-pId'));
				//$(this).parents('.item').remove();
			}
		});
		// 保存
		$(document).on('click', '.js-save', function () {
			var data = collectData();
			if (data == '') {
				$.alert('请先添加阶段！');
			}else{
				saveData(data);
			}
		});
		// 取消
		$(document).on('click', '.js-cancel', function () {
			history.back();
		});
	}
	init();
})();

/**
 * Created by 137376 on 2017/12/22.
 */

(function () {
	var id = $.getQueryString('id');
	var recordName = $.getQueryString('name');
	var type = Number($.getQueryString('type'));
	var paId, paName, paData;
	if (!id) {
		$.alert('计划不存在！', function () {
			history.back();
		});
	}
	// 渲染空
	function renderEmpty() {
		$('.js-detail-right').html(template('common/record-empty', {
			colspan: 9,
			text: '还没有相关任务，请添加任务！'
		}));
	}
	// 获取列表
	function getList() {
		var emptyFlag = false;
		$.getData({
			url: '/pc/plan/getById',
			query: {
				id: id
			}
		}, function (data) {
			if (data) {
				renderAside(data);
	
				if (data.taskTemplateVos && data.taskTemplateVos.length > 0) {
					renderMain(data.taskTemplateVos);
				} else {
					renderEmpty();
				}
			}
			
			if (emptyFlag) {
				$.alert('还没有相关任务，请添加任务！');
				$('.js-add-task').show();
				renderEmpty();
			} else {
				// renderMain(data.periodTaskList);
			}


		});
	}
	// 渲染左侧
	function renderAside(params) {
		$('.js-plan-name').text(params.raName);
		$('.js-detail-left').html(template('plan/detail-left', {
			recordName: params.recordName,
			typeName: params.typeName,
			recordDesc: params.recordDesc,
			partner: params.actorNames,
			manager: params.raDutyEiEmpName
		}));
	}
	// 渲染右侧
	function renderMain(params) {
		console.log(params, '111111');
		$('.js-detail-right').html(template('plan/detail-right', {
			items: params
		}));
		// 排序
		// for (var p = 0;p < params.length;p++) {
		// 	$.kmSort({
		// 		sortArea: '.js-sort',
		// 		sortItem: '.sort-'+ params[p].periodId,
		// 		upEl: '.js-up',
		// 		downEl: '.js-down',
		// 		afterSort: function (before, after) {
		// 			var bef = before.substring(1);
		// 			var aft = after.substring(1);
		// 			taskMove(bef, aft);
		// 		}
		// 	});
		// }
		console.log(params, 'params22222222');
		// if (params.length == 1) {
		// 	$('.js-moveto').addClass('disabled');
		// }
	}
	// 删除任务
	function deleTask(obj) {
		$.getData({
			url: '/pc/plan/deletePlanById',
			query: {
				recordId: id,
				templateId: obj.taId
			}
		}, function (data) {
			if (data) {
				getList();
			}
		});
	}
	// 任务从当前阶段上下移动
	function taskMove(before, after) {
		$.getData({
			url: '/task/taskMove',
			query: {
				sId: before,
				gId: after
			}
		}, function (data) {
			if (data) {
				getList();
			}
		});
	}
	// 任务从一个阶段移动到另一个阶段
	function moveToPeriod(taId) {
		$.getData({
			url: '/task/taskMoveTo',
			query: {
				taskId: taId,
				periodId: paId,
				periodName: paName
			}
		}, function (data) {
			if (data) {
				getList();
			}
		});
	}
	// 获取任务阶段
	// function getTaskPeriod() {
	// 	$.getData({
	// 		url: '/pc/period/periodList',
	// 		query: {
	// 			recordId: id
	// 		}
	// 	}, function (data) {
	// 		if (data && data.length) {
	// 			var _data = [];
	// 			paData = data; // JIEDUAN
	// 			console.log(data, 'data-paData');
	// 			for (var i in data) {
	// 				_data.push({
	// 					title: data[i].periodName,
	// 					value: data[i].id
	// 				});
	// 			}
	// 			paId = _data[0].value;
	// 			paName = _data[0].title;
	// 			renderTaskPeriod(_data);
	// 		}
	// 	});
	// }
	// 渲染任务阶段
	function renderTaskPeriod(data) {
		$('.js-step').html(template('plan/select', {
			id: 'period',
			items: data
		}));
	}
	function init() {
		getList();
		// getTaskPeriod();
		//返回
		$(document).on('click', '.js-btn-back', function () {
			// window.history.go(-1);
			location.href = '../../pro-list.html';
		})
		// 添加任务
		$(document).on('click', '.js-add-task', function () {
			window.sessionStorage.removeItem('currentStep');
			location.href = '../../add-task.html?id=' + id + '&name=' + recordName + (type ? '&type=1' : '');
		});
		// 阶段管理
		$(document).on('click', '.js-manage-step', function () {
			location.href = './step.html?id=' + id + '&name=' + recordName;
		});
		// 删除
		// 删除
		$(document).on('click', '.js-del', function () {
			var delParam = {
				taId: $(this).data('taid'),
				//apprStat: $(this).attr('data-state')
			};
			$.bubble({
				el: $(this),
				msg: '确定删除当前任务模板？',
				ok: function () {
					$.toast('删除成功', {
						type: 'success'
					});
					deleTask(delParam)
					// delItem(id);
				},
				cancel: function () {
					$.toast('您已取消删除');
				}
			});
			// $.confirm('确定删除此任务？', function() {
			// 	deleTask(delParam);
			// });
			return false;
		});
		// 移动
		$(document).on('click', '.js-moveto', function () {
			if ($(this).hasClass('disabled')) {
				return false;
			} else {
				$('#selectPer').show();
				var taId = $(this).attr('data-id');
				//layer.open({
				$.pop({
					title: '任务移动至',
					type: 1,
					btn: ['确定', '取消'],
					area: ['400px', '250px'],
					yes: function (index, layero) {
						paName = $("#period").find("option:selected").val();
						paId = $("#period").find("option:selected").attr('data-paId');
						moveToPeriod(taId);
						getList();
						$('#modal-body').css('display', 'none');
						$('.modal-backdrop').css('display', 'none');
					}
				});
				$('.modal-body').html(template('plan/paSelect', {
					id: 'period',
					items: paData
				}));
			}
		});
		//暂时跳转
		$(document).on('click', '.go-task-detail', function () {
			console.log('暂时跳转');
		});
	}
	init();

})();

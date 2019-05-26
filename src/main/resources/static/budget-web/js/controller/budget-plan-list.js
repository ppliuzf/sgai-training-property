$(function () {
    // var bodyData = {};
	// var total = 0,
	// 	currPage = 1;
	var searchParams = {};
	// 获取列表数据
	function getList(pageNum, isFirst) {
		$.getData({
			url:  '/budget/budgetRecordList',
			query: {
				pageNum: pageNum,
				pageSize: 10,
			},
		}, function (data) {
			if (data.list && data.list.length) {
				renderList(data.list);
				isFirst && $.renderPage({
					count: data.count,
					jump: function (num) {
						getList(num);
					}
				});
			} else {
				renderEmpty();
			}
		});
    }
    // 渲染列表
	function renderList(params) {
		for(var i = 0; i < params.length; i++){
			var createTime = new Date(params[i].createTime);
			var approvalTime = new Date(params[i].approvalTime);
			params[i].createTime = createTime.toLocaleDateString().replace(/\//g, "-");
			params[i].approvalTime = approvalTime.toLocaleDateString().replace(/\//g, "-");
		}
		$('.js-list').html(template('common/budget-list', {
			items: params
		}));
	}

	// 渲染无数据
	function renderEmpty() {
		$('.js-list').html(template('common/noRecord', {
			colspan: 11,
			text: '还没有相关计划，请添加计划'
		}));
	}
	// 撤销操作
    function delItem(el, id) {
		$.getData({
			url: '/approval/revoke', 
			query: {
				id: id
			}
		}, function(data) {
			if (data) {
				$.toast('删除成功', {
					type: 'success'
				});
				getList(1,true);
				// $.msg('操作成功', function() {
				// 	getList(1,true);
				// });
			}
		});
	}

	// 收集搜索条件
	function collectFilters() {
		if($.trim($('#planName').val())){
			searchParams.recordName = $.trim($('#planName').val());
		}
		if($.trim($('#declarant').val())){
			searchParams.creatorEiEmpName = $.trim($('#declarant').val());
		}
		if($('#predictYear').val()){
			searchParams.year = $('#predictYear').val();
		}
		if($('#predictPeriod').val()){
			searchParams.cycle = $('#predictPeriod').val();
		}
		if($('#planStatus').val()){
			// console.log($('#planStatus').val())
			searchParams.state = $('#planStatus').val();
		}
		console.log('查找', searchParams);
		getConditionList(1, true);
		
	}

	// 根据搜索条件-获取列表
	function getConditionList(pageNum, isFirst) {
		$.getData({
			url:  '/budget/budgetRecordSearchList',
			query: {
				pageNum: pageNum,
				pageSize: 10,
			},
			body: searchParams
		}, function (data) {
			if (data.list && data.list.length) {
				renderList(data.list);
				isFirst && $.renderPage({
					count: data.count,
					jump: function (num) {
						getList(num);
					}
				});
			} else {
				renderEmpty();
			}
			searchParams = {}
		});
	}

    function getPullMenus(){
		// 渲染周期下拉
		var periodList = [{"value": '', "title": "全部"}, 
			{"value": 1, "title": "全年"}, 
			{"value": 2, "title": "半年"}, 
			{"value": 3, "title": "季度"}, 
			{"value": 4, "title": "月"}];
        $('#predictPeriod').html(template('common/select-type', {
			items: periodList
		}));
		// 渲染状态下拉
        var statusList = [
			{"value": '', "title": "全部"}, 
			{"value": 0, "title": "未提交"}, 
			{"value": 1, "title": "已提交"}, 
			{"value": 2, "title": "已通过"}, 
			{"value": 3, "title": "已拒绝"}, 
			{"value": 4, "title": "已撤销"}];
        $('#planStatus').html(template('common/select-type', {
			items: statusList
		}));
		var yearDate = new Date();
		yearDate = yearDate.getFullYear();
		var dateList = [];
		for(var i = 0; i <= 10; i++){
			dateList[i] = {};
			dateList[i].value = yearDate - i;
			dateList[i].title = yearDate - i;
		}
		dateList = dateList.reverse();
		for(var i = 0; i <= 10; i++){
			dateList[10 + i] = {};
			dateList[10 + i].value = yearDate + i;
			dateList[10 + i].title = yearDate + i;
		}
		dateList.unshift({
			value: '',
			title: '全部'
		});
		$('#predictYear').html(template('common/select-type', {
			items: dateList
		}));
		$('#planName').val('');
		$('#declarant').val('');
	}

    function init() {
		getList(1,true);
		getPullMenus();
		// getPrePeriod();
		// getStatus();
		//点击撤销
		$(document).on('click', '.js-del', function() {
			var $this = $(this),
				id = $(this).attr('data-id');
			// $.confirm('确定撤销当前计划？', function () {
			// 	delItem($this, id);

			// });
			// return false;

			$.bubble({
				el: $(this),
				msg: '确定撤销当前计划？',
                ok: function () {
                    
                    delItem($this, id);
                },
                cancel: function () {
                    $.toast('您已取消撤销');
                }
            });
		});
		// 搜索查找
		$('.js-determine').click(function(){
			collectFilters();
		});
		// 跳转到新增计划
		$(document).on('click', '.start-plan', function () {
			location.href = './budget-add-plan.html';
		})
    }
    init();
});
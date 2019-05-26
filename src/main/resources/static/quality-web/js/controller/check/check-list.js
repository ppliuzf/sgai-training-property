$(function () {
	// 获取列表
	var params={};
	function getList(pageCurr, isFirst,param) {
		var query=$.extend({pageNum: pageCurr||1, pageSize:10},param||{})
		$.getData({
			url: '/testitem/listTestItem',
			query: query
		}, function(data) {
            isFirst && $.renderPage({
                count: data.count,
                limit: 10,
                jump: function (num) {
                    getList(num, false, params);
                }
            });
            if (data.list && data.list.length) {
                data.list.forEach(function (item, index) {
					item.num = index + 1;
                });
				renderList(data.list);
			} else {
				renderEmpty();
			}
		});
	}

	// 获取专业范畴列表
	function getCategoryList() {
		$.getData({
			url: '/category/getAllCategory'
		}, function (data) {
			if (data && data.length) {
                var _data = [
                    {title: '全部', value: ''}
                ];
				for (var i in data) {
					_data.push({
						title: data[i].pcName,
						value: data[i].id
					});
				}
				renderCategory(_data);
			}
		});
	}
	// 重置搜索
	function resetSearch() {
        $('#itemName').val('');
        $('#startDate').val('');
        $('#endDate').val('');
				$('#pcId').val('');
				console.log($('#pcId').val(''))
				params = {};
				getList(1, true);
	}
	// 删除
	function delItem(id) {
		$.getData({
			url: '/testitem/delTestItem',
			query: {
				itemId: id
			}
		}, function (data) {
			if (data) {
				// $.alert("删除成功!")
				$.msg('操作成功！',{time:1000})
				getList(1, true);
			} else {
				$.msg('操作失败！',{time:1000})
			}
		});
	}
	// 渲染专业范畴
	function renderCategory(data) {
		$('.select-pc').html(template('common/select', {items: data}));
	}
	// 渲染列表
	function renderList(data) {
		$('.js-list').html(template('check/check-list',{items: data}))
	}
	// 渲染无数据
	function renderEmpty() {
		$('.js-list').html(template('common/record-empty', {
			colspan: 8
		}));
	}
	function init() {
		getList(1, true);
		getCategoryList();
		// 日历
		$('#startDate,#endDate').datetimepicker({
			language: 'zh-CN',
			format: 'yyyy-mm-dd',
			minView: 'year',
			endDate: new Date(),
			autoclose: true
		});
		// 搜索
		$(document).on('click','.js-search',function(){
			params = {};
			params.startDate=$.getTimeStamp($('#startDate').val());
			params.endDate=$.getTimeStamp($('#endDate').val());
			if (params.endDate && params.endDate < params.startDate) {
				$.alert('结束时间不能小于创建时间！');
				return false;
			}
			params.itemName=$.trim($('#itemName').val());
			params.pcId=$.trim($('#pcId').val());
			getList(1, true, params);
		});
		// 重置
		$(document).on('click', '.js-reset', resetSearch);
		// 删除
		$(document).on('click', '.js-del', function () {
			var id = $(this).data('id');
			$.confirm('确定删除当前检验项？',function(){
				delItem(id);
			})
		});
		$(document).on('click', '.js-date-clean',function(){
			$(this).prev().val('');
		})
	}
	init();
});
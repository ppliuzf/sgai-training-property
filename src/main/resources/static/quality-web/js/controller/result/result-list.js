$(function () {
	// 查询参数
    var params = {};
    // 获取列表
	function getList(pageCurr, isFirst, param) {
		var query = $.extend({pageNum: pageCurr||1, pageSize:10}, param||{});
		$.getData({
			url: '/taskresult/listTaskResult',
			query: query
		}, function(data) {
			if (data.list&& data.list.length) {
                data.list.forEach(function (item, index) {
                	item.num = index + 1;
                    if (item.ttStatus === 0) {
                        item.ttStatus = '未开始'
                    } else if (item.ttStatus === 1) {
                        item.ttStatus = '进行中'
                    } else if (item.ttStatus === 2) {
                        item.ttStatus = '审核中'
                    } else if (item.ttStatus === 3) {
                        item.ttStatus = '已完结'
                    }
                })
			}
            isFirst && $.renderPage({
                count: data.count,
                limit: 10,
                jump: function (num) {
                    getList(num, false, params);
                }
            });
			if (data.list && data.list.length) {
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
        $('#pcId').val('');
        $('#startDate').val('');
        $('#endDate').val('');
		params = {};
		getList(1,true);
	}
	// 渲染专业范畴
	function renderCategory(data) {
		$('.select-pc').html(template('common/select', {items: data}));
	}
	// 渲染列表
	function renderList(data) {
		$('.js-list').html(template('result/result-list',{items: data}))
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
		$(document).on('click','.js-search', function(){
				params.startDate = $.getTimeStamp($('#startDate').val());
				params.endDate = $.getTimeStamp($('#endDate').val());
                if (params.endDate && params.endDate < params.startDate) {
                    $.alert('结束时间不能小于创建日期！');
                    return false;
                }
				params.ttName = $.trim($('#itemName').val());
				params.pcId = $.trim($('#pcId').val());
				getList(1, true, params);
		});

		// 重置
		$(document).on('click', '.js-reset', resetSearch);
		// 日期清空
		$(document).on('click', '.js-date-clean',function(){
			$(this).prev().val('');
		})
	}
	init();
});
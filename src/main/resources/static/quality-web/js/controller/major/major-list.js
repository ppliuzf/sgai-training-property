$(function () {
	// 获取列表
	function getList(pageCurr, isFirst) {
		$.getData({
			url: '/category/listCategory',
			query: {
				pageNum: pageCurr,
				pageSize:10
			}
		}, function(data) {
			if (data.list && data.list.length) {
				data.list.forEach(function (item, index) {
					item.num = index + 1;
                });
				renderList(data.list);
				isFirst && $.renderPage({
					count: data.count,
					limit: 10,
					jump: function (num) {
						getList(num);
					}
				});
			} else {
				renderEmpty();
			}
		});
	}
	// 删除
	function delItem(id) {
		$.getData({
			url: '/category/delCategory',
			query: {
				pcId: id
			}
		}, function (data) {
			if (data) {
				// $.alert("删除成功!")
				$.msg('操作成功！',{time:2000})
				getList(1, true);
			} else {
				$.msg('操作失败！',{time:2000})
			}
		});
	}
	// 渲染列表
	function renderList(data) {
		$('.js-list').html(template('major/major-list',{items: data}))
	}
	// 渲染无数据
	function renderEmpty() {
		$('.js-list').html(template('common/record-empty', {
			colspan: 5
		}));
	}
	function init() {
		 getList(1, true);
		// 删除
		$(document).on('click', '.js-del', function () {
			var id = $(this).data('id');
			$.confirm('确定删除当前专业范畴？', function () {
				delItem(id);
			})
		});
	}
	init();
});
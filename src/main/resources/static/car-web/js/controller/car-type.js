$(function () {
	// 获取列表
	function getList(isFirst) {
		$.getData({
			url: '/typeInfo/typeInfoList',
			query:''
		}, function(data) {
			if (data.length > 0) {
                data.forEach(function (item, index) {
                	item.num = index + 1 > 9 ? index + 1 : '0' + (index + 1);
                });
				renderList(data);
				// isFirst && $.renderPage({
				// 	count: data.count,
				// 	jump: function (num) {
				// 		getList(num);
				// 	}
				// });
			} else {
				renderEmpty();
			}
		});
	}
	// 渲染列表
	function renderList(data) {
		$('.main').html(template('car-type', {
			items: data
		}));
	}
	// 渲染空
	function renderEmpty() {
		$('.main').html(template('common/record-empty', {
			colspan: 5
		}));
	}
	function init() {
		getList(false);
		// $(document).on('click', '.js-del', function() {
		// 	$.confirm('确定');
		// });
	}
	init();
});

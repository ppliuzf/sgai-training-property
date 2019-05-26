$(function () {
	// 获取列表
	function getList(isFirst) {
		$.getData({
			url: '/gearBoxType/gearBoxTypeList',
			query:''
		}, function(data) {
			//debugger
			if (data.length) {
				data.forEach(function (item, index) {
					item.num = 1 + index > 9 ? 1+index : '0' + (index + 1);
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
		$('.main').html(template('box-type', {
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
	}
	init();
});

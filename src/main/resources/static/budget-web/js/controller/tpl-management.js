$(function () {
    // 获取列表数据
    function getList(pageNum, isFirst) {
		$.getData({
			url:  '/template/getPageList',
			query: {
				pageNum: pageNum,
				pageSize: 10,
			},
			// body: bodyData
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
			params[i].createTime = createTime.toLocaleDateString().replace(/\//g, "-");
		}
		$('.js-list-data').html(template('common/tpl-list', {
			items: params
		})).find('.js-status').bootstrapSwitch({
			onText: "启用",
			offText:"禁用",
			size:"mini",
			onSwitchChange: function () {
				updateStatus($(this).data('id'), $(this).is(':checked'));
			}
		});
		// console.log(params);
	}
	//修改启用禁用状态
	function updateStatus(id,isOn){
		$.getData({
			url: '/template/enableOrDisable',
			query: {
				id: id,
				state:isOn ? 0 : 1
			},
		}, function (data) {
			// $.msg('操作成功');
			$.toast('操作成功', {
				type: 'success'
			});
		});
		
	}

	// 渲染无数据
	function renderEmpty() {
		$('.js-list-data').html(template('common/noRecord', {
			colspan: 8,
			text: '还没有相关模板，请添加模板'
		}));
    }
    function init () {
		getList(1,true);
		$(document).on('click', '.js-relation-data', function(){
			var id = $(this).attr('item-id');
			console.log(id)
			location.href = './tpl-relation.html?id='+ id;
		})
		// 跳转到新增
		$(document).on('click', '.js-add-tpl', function () {
			location.href = './tpl-add.html';
		})
    }
    init();
});
$(function(){
	var total = 0,
		currPage = 1;
    // 获取列表数据
    function getList() {
		$.getData({
			url:  '/expensesItem/getPageList',
			query: {
				pageNum: currPage,
				pageSize: 10,
			},
		}, function (data) {
			if (data.list && data.list.length) {
				renderList(data.list);
				$.renderPage({
					count: data.count,
					curr: currPage,
					jump: function (num) {
						currPage = num;
						getList(num);
					}
				});
			} else {
				renderEmpty();
			}
		});
    }
    // 渲染无数据
	function renderEmpty() {
		$('.js-list-data').html(template('common/noRecord', {
			colspan: 5,
			text: '还没有相关费项，请添加费项'
		}));
    }
    // 删除
	function delItem(el, id) {
		$.getData({
			url: '/expensesItem/deleteById',
			query: {
				id: id
			}
		}, function(data) {
			// debugger;
			if (data) {
				// $.msg('操作成功', function() {
				// 	el.parent().parent().parent().parent().parent().remove();
				// 	if (currPage !== 1 && !$('.js-list-data tr').length) {
				// 		currPage -= 1;
				// 	}
				// 	getList();
				// });
				$.toast('操作成功', {
					type: 'success'
				});
				// el.parent().parent().parent().parent().parent().remove();
				if (currPage !== 1 && !$('.js-list-data tr').length) {
					currPage -= 1;
				}
				getList();
			} else {
				// $.msg('费项已经关联数据不能删除！', function() {
				// 	el.parent().parent().parent().parent().parent().remove();
				// });
				// $.toast('费项已经关联数据不能删除！');
				$.alert('费项已经关联数据不能删除！');
			}
		}, function () {
			// $.msg('费项已经关联数据不能删除！', function() {
			// });
			// $.toast('费项已经关联数据不能删除！', {
			// 	timer: 5000
			// });
			$.alert('费项已经关联数据不能删除！');
		});
	}
    // 渲染列表
	function renderList(params) {
		$('.js-list-data').html(template('common/cost-list', {
			items: params
		}));
	}
    function init(){
		// getList(1,true);
		getList();
        //点击删除
		$(document).on('click', '.js-del', function() {
			var $this = $(this),
				id = $(this).attr('data-id');
			// $.confirm('确定删除当前费项？', function () {
			// 	delItem($this, id);

			// });
			// return false;

			$.bubble({
				el: $(this),
				msg: '确定删除当前费项？',
                ok: function () {
                    
                    delItem($this, id);
                },
                cancel: function () {
                    $.toast('您已取消删除');
                }
            });
		});
		// 跳转到新增
		$(document).on('click', '.js-add-cost', function () {
			location.href = './cost-add.html';
		})
    }
    init();
});
$(function () {
	var currPage = 1;
	function getList() {
		$.getData({
			url: '/subject/subjectList',
			query: {
				pageNum: currPage,
				pageSize: 10
			}
		}, function (data) {
			if (data && data.length) {
				renderList(data);
			} else {
				renderEmpty();
			}
		});
	}
    // 渲染列表
	function renderList(params) {
		$('.js-list-data').html(template('common/sub-list', {
			items: params
		}));
	}

	// 渲染无数据
	function renderEmpty() {
		$('.js-list-data').html(template('common/noRecord', {
			colspan: 5,
			text: '还没有相关科目，请添加科目'
		}));
    }
    // 删除
	function delItem(el, id) {
		 $.getData({
		 	url: '/subject/subjectDelete',
		 	query: {
			    subjectId: id
		 	}
		 }, function(data) {
		 	if (data) {
		 		// $.msg('操作成功', function() {
		 		// 	el.parent().parent().remove();
				//     if(el.parent().parent().length <= 1){
				// 		var str = '.'+ el.parent().parent().attr('data-attr');
				// 	    $(str).hide;
				// 	    $(str).css('display', 'none');
				//     }
				//  });
				 $.toast('删除成功', {
					type: 'success'
				});
				el.remove();
				if(el.parent().parent().length <= 1){
					var str = '.'+ el.parent().parent().attr('data-attr');
					$(str).hide;
					$(str).css('display', 'none');
				}
		 	}
			 //getList();
		 },function(data){
			//  $.msg(data,5000);
			$.alert(data);
			// $.toast(data, {
			// 	timer: 5000
			// });
		 });
	}
    function init () {
        getList();
	    $(document).on('click', '.identify', function (){
		  act = '.' + $(this).attr('data-attr'); // 取一级dom class
		  actIndex = $(this).attr('data-index');
		  actAttr = '.' + 'group-hide-' + actIndex;   //此一级下所有的二三级元素
		  nextSecondDom = '.' + 'group-next-' + actIndex;  //此一级下所有的二级元素
		    if($(this).hasClass('glyphicon-plus')){
			    $(this).removeClass('glyphicon-plus');
			    $(this).addClass('glyphicon-minus');
				$(nextSecondDom).show();
				if($(nextSecondDom+'  .identifyTwo').hasClass('glyphicon-plus')){
					console.log('+');
				}else {
					$(actAttr).show();
				}
			    
		    }else {
			    $(this).removeClass('glyphicon-minus');
			    $(this).addClass('glyphicon-plus');
			    $(actAttr).hide();
			    console.log(act,'11111111111111111');

		    }
	    });
	    $(document).on('click', '.identifyTwo', function (){
		   actLast = '.' + $(this).attr('data-attr'); // 取二级dom class
		    console.log(actLast, 'actLastactLastactLastactLastactLast');
		   cancelDom = '.' + $(this).attr('data-show'); // 取二级dom class
		   //styleAtr = '.' + $(this).attr('data-style'); // 取二级dom class
		    if($(this).hasClass('glyphicon-plus')){
			    $(this).removeClass('glyphicon-plus');
			    $(this).addClass('glyphicon-minus');
			    $(actLast).show();
		    }else {
			    $(this).removeClass('glyphicon-minus');
			    $(this).addClass('glyphicon-plus');
			    $(actLast).hide();
		    }
	    });
        //点击删除
		$(document).on('click', '.js-del', function() {
			var $this = $(this),
				id = $(this).attr('data-id');
			// $.confirm('确定删除当前科目？', function () {
			// 	var delDom = $this.parent().parent().parent();
			// 	delItem(delDom, id);

			// });
			// return false;
			$.bubble({
				el: $(this),
				msg: '确定删除当前科目？',
                ok: function () {
                    var delDom = $this.parent().parent();
                    delItem(delDom, id);
                },
                cancel: function () {
                    $.toast('您已取消删除');
                }
            });
		});
		// 跳转到新增
		$(document).on('click', '.js-add-sub', function () {
			location.href = './sub-add.html';
		})
    }
    init();
});
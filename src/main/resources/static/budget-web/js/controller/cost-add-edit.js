$(function(){
	var costName = $.getQueryString('name');
	console.log(costName);
	var id = $.getQueryString('id');
	console.log(id);
    //获取类型详情
	function getEditData() {
		$.getData({
			url: '/expensesItem/getById',
			query: {
				id: id
			}
		}, function (data) {
			if(data){
				document.title = '编辑费项';
				$('.costTitle').html('编辑费项-'+ data.itemName);
			}
			showList(data);
        });
	}
	// 编辑-渲染页面数据
	function showList(data){
        $('.js-add-edit').html(template('common/cost-add-edit', {
            item: data,
        }));
		$('#costName').val(data.itemName);
		$('#confereDesc').val(data.description);
		$.counter({
			el: '#confereDesc', // 文本框 id，默认 #textarea
			count: '.counttask', // 计数器 id，默认 .js-count
			max: 300 // 输入最大长度值，默认 200
		});
		
	}
    // 收集数据
	function collectData() {
		var rs = {};
		if ($.trim($('#costName').val()) === '') {
			$.alert('请输入费项名称');
			return false;
		}
		rs.itemName = $.trim($('#costName').val()); // 计划名称
		rs.description = $.trim($('#confereDesc').val()); // 描述
		if(id){
			rs.id = id;
		}
		return rs;
    }
    // 新增-保存
	function saveData (data) {
		$.getData({
			url: '/expensesItem/save',
			body: data
		}, function (data) {
				window.history.back();
		});
	}
    function init(){
        if(id){
           
			getEditData();
        }else {
            $('.js-add-edit').html(template('common/cost-add-edit', {
                item: {},
            }));
		}
		// console.log($('#confereDesc').val());
		if(!id){
			$.counter({
				el: '#confereDesc', // 文本框 id，默认 #textarea
				count: '.counttask', // 计数器 id，默认 .js-count
				max: 300 // 输入最大长度值，默认 200
			});
		}
        // 保存
		$(document).on('click', '.js-save', function () {
			var data = collectData();
			if(data){
				saveData(data);
			}
		});
		// 取消
		$(document).on('click', '.js-cancel', function () {
			history.back();
		});
    }
    init();
});
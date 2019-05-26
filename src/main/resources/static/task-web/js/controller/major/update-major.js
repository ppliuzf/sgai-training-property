$(function () {
	var typeSelect = [], // 选择的专业范围
		detailInfo = {}, // 获取的详情信息
		id = $.getQueryString('id'),
		type = $.getQueryString('type');
	// 获取专业范围
	function getType() {
		$.getData({
			url: '/category/getCategoryTypes'
		}, function (data) {
			console.log(data)
			if (data) {
			// if (data && data.length) {
				renderUl(data);
			}
		});
	}
	// 获取任务类型
	function getCategoryList() {
		$.getData({
			url: '/category/getAllCategory'
		}, function (data) {
			if (data && data.length) {
				var _data = [];
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
	// 渲染任务类型
	function renderCategory(data) {
		var data = [
			{
				title: '公共区域',
				value: '1'
			},
			{
				title: '投诉管理',
				value: '2'
			},
			{
				title: '协调类',
				value: '3'
			},
			{
				title: '咨询类',
				value: '4'
			},
			{
				title: '一般报事',
				value: '5'
			}
		]
		$('#pcId').html(template('common/select', {items: data}));
	}
	// 获取Icons
	// function getIcons() {
	// 	$.getData({
	// 		url: '/category/getCategoryIcons'
	// 	}, function (data) {
	// 		if (data && data.length) {
	// 			renderIcon(data);
	// 		}
	// 	})
	// }

	//获取详情
	function getInfo() {
		$.getData({
			url: '/category/detailCategory',
			query: { pcId: id }
		}, function (data) {
			if (data) {
				if (id) {
					data.title = '编辑任务专业';
				}
				renderList(data);
				$.counter({ el: '#pcDesc', max: 200 });
				detailInfo = data;
			}
		})
	}

	// 收集数据
	function collectData() {
		var body = {};
		if ($.trim($('#pcName').val())) {
			body.pcName = $.trim($('#pcName').val());
		} else {
			$.alert('请输入任务专业名称！');
			return false;
		}
		if (typeSelect.length > 0) {
			body.asType = JSON.stringify(typeSelect);
		} else {
			$.alert('请选择专业范围！');
			return false;
		}
		body.taskType  = $('#pcId option:selected').text();//------------>新增任务字段类型
		if (type) {
			body.taskType = '安保类';
		}
		body.pcId = id ? id : 0;
		body.pcDesc = $.trim($('#pcDesc').val());
		return body;
	}

	//保存 新增
	//新增
	function add(data) {
		$.getData({
			url: '/category/createCategory',
			body: data,
			query: data
		}, function (data) {
			if (data) {
				$.msg("保存成功", function () {
					history.back();
				})
			} else {
				$.alert("保存失败!")
			}
		});
	}
	//编辑
	function update(data) {
		$.getData({
			url: '/category/updateCategory',
			body: data,
			query: data
		}, function (data) {
			if (data) {
				$.msg("保存成功", function () {
					history.back();
				})
			} else {
				$.alert("保存失败!")
			}
		})
	}

	//渲染关联类型
	function renderUl(data) {
		var data = [
			{
				typeId: 1,
				typeName: '空间主数据'
			},
			{
				typeId: 2,
				typeName: '物料主数据'
			},
			{
				typeId: 3,
				typeName: '供应商主数据'
			},
			{
				typeId: 4,
				typeName: '设备主数据'
			},
		]
		$('#ul-type').html(template('common/ul', { items: data }));
		if (detailInfo.asType) {
			typeSelect = JSON.parse(detailInfo.asType);
			var typeItemArr = $('.type-item');
			for (var i = 0; i < typeItemArr.length; i++) {
				typeSelect.forEach(function (item) {
					if (typeItemArr[i].dataset.id === item.asId) {
						$(typeItemArr[i]).addClass('type-selected');
					}
				});
			}
		}
	}

	//渲染图片
	function renderIcon(data) {
		$('#ul-box').html(template('major/img-list', { items: data }));
		$('#ul-box li:first').find('.icon-right').css({ 'display': 'block' }); // 默认
		if (detailInfo.pcIcon) { // 渲染数据中选中的icon
			$('#ul-box li').each(function () {
				var _this = $(this),
					_src = _this.find('img').attr('src');
				if (_src === detailInfo.pcIcon) {
					_this.find('.icon-right').css("display", "block");
					_this.siblings().find('.icon-right').css("display", "none");
					return false;
				}
			})
		} else {
			// 新增
			detailInfo.pcIcon = data[0].url;
		}
	}

	// 渲染表单
	function renderList(data) {
		$('.js-main').html(template('major/add-major', { items: data }));
		$.counter({ el: '#pcDesc', max: 200 });
		// 渲染列表，有ul元素后再去渲染type,icon;
		getType();
		//获取任务类型
		getCategoryList();
		renderCategory();
		// getIcons();
	}

	function init() {
		if (!id) {
			// 新增
			renderList({ title: '新增任务专业' });
		} else {
			// 编辑
			getInfo();
		}
	
		// 选择关联类型
		$(document).on('click', '.type-item', function () {
			console.log($(this));
			if ($(this).hasClass('type-selected')) {
				$(this).removeClass('type-selected');
			} else {
				$(this).addClass('type-selected');
			}
			var selectedArr = $('.type-selected');
			typeSelect = [];
			for (var i = 0; i < selectedArr.length; i++) {
				var item = {
					asId: selectedArr[i].dataset.id,
					asName: selectedArr[i].dataset.name
				};
				typeSelect.push(item);
			}
		});
		// 选择icon
		// $(document).on('click', '#ul-box li', function () {
		// 	$(this).siblings().find('.icon-right').css({'display': 'none'});
		// 	$(this).find('.icon-right').css({'display': 'block'});
		//     detailInfo.pcIcon = $(this).find('img').attr('src');
		// });
		//保存
		$(document).on('click', '.js-save', function () {
			var body = collectData();
			if (body) {
				if (id) {
					update(body)
				} else {
					//新增
					if (type){
						body.type = 1;
					}
					add(body);
				}
			}
		});
		// 取消
		$(document).on('click', '.js-cancel', function () {
			history.back();
		});

	}

	init();
});
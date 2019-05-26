$(function () {
	var id = $.getQueryString('id'),
		isEdit = id ? true : false;
	//获取详情
	function getInfo(id) {
		$.getData({
			url: '/carInfo/getCarInfoById',
			type: 'get',
			query: {
				id: id
			},
		}, function (data) {
			if (data) {
				renderInfo(data);
			} else {
				$.alert("获取数据失败!")
			}
		});
	}

	// 获取变速箱类型
	function getciBoxType(el) {
		$.getData({
			url: '/gearBoxType/gearBoxTypeList',
		}, function (data) {
			if (data && data.length) {
				// var _data = [{
				//  value: '',
				//  title: ''
				// }];
				var _data = [];
				for (var i in data) {
					_data.push({
						value: data[i].id,
						title: data[i].btName
					});
				}
				renderSelect(el, _data);
			}
		});
	}

	// 获取车辆类型
	function typeInfoList(el) {
		$.getData({
			url: '/typeInfo/typeInfoList'
		}, function (data) {
			if (data && data.length) {
				// var _data = [{
				//  value: '',
				//  title: ''
				// }];
				var _data = [];
				for (var i in data) {
					_data.push({
						value: data[i].id,
						title: data[i].ctName
					});
				}
				renderSelect(el, _data);
			}
		});
	}

	//获取所属部门
	function getDepartment(el) {
		$.getData({
			url: '/common/getOrgTreeById',
			query: {
				deptId: 0,
				isIncludeEmp: 0
			}
		}, function (data) {
			if (data && data.length) {
				var _data = [];
				for (var i in data) {
					_data.push({
						value: data[i].nodeId,
						title: data[i].nodeName
					});
				}
				renderSelect(el, _data);
			}
		});
	}

	// 收集表单信息
	function collectRData() {
		var rs = {
			ciNumber: '',
			ciDepartment: '',
			ciDepartmentId: '',
			ciBuyDate: '',
			ciEngineNumber: '',
			ciBrand: '',
			ciModel: '',
			ciColor: '',
			ciDisplacement: '',
			ciBoxTypeId: '',
			ciBoxTypeName: '',
			ciLoadNumber: '',
			ciTypeName: '',
			ciTypeId: '',
			ciOwnerName: '',
			ciOwnerPhone: '',
			ciRemark: '',
			ciImage: '',
			journeyAmount:''
		};
		rs.ciNumber = $.trim($('#ciNumber').val());
		var elDept = $('.js-list-all');
		if (elDept.val()) {
			var arr = JSON.parse(elDept.val());
			rs.ciDepartment = arr[0].title;
			rs.ciDepartmentId = arr[0].id;

		}
		rs.ciBuyDate = $.getTimeStamp($.trim($("#ciBuyDate").val()));
		rs.ciEngineNumber = $.trim($('#e-num').val());
		rs.journeyAmount = $.trim($('#ciNum').val());
		rs.ciBrand = $.trim($('#ciBrand').val());
		rs.ciModel = $.trim($('#ciModel').val());
		rs.ciColor = $.trim($('#ciColor').val());
		rs.ciDisplacement = $.trim($('#ciDisplacement').val());
		rs.ciBoxTypeId = $('#ciBoxType').val();
		rs.ciBoxTypeName = $('#ciBoxType').find('option:selected').text();
		rs.ciLoadNumber = $.trim($('#ciLoadNumber').val());
		rs.ciTypeName = $('#ciTypeName').find('option:selected').text();
		rs.ciTypeId = $('#ciTypeName').val();
		rs.ciOwnerName = $.trim($('#ciOwnerName').val());
		rs.ciOwnerPhone = $.trim($('#ciOwnerPhone').val());
		rs.ciRemark = $.trim($('#ciRemark').val());
		rs.ciImage = '';
		return rs;
	}

	//获取图片url
	function getImgsUrl() {
		var s_urls = '',
			el_item = $('.js-uploader').find('.upload-item'),
			urls = [];
		if (el_item) {
			el_item.each(function () {
				urls.push($(this).data('url'));
			});
			s_urls = urls.join(',');
		}
		return s_urls;
	}

	// 表单校验
	function valitData() {
		var bool = true;
		var reg = /^[1-9]\d*$/;
		var ciNumber = $('#ciNumber'),
			jsDept = $('.js-list-dept'),
			jsAll = $('.js-list-all'),
			ciLoadNumber = $('#ciLoadNumber'),
			ck = $('#ciNum').val()
			phone = $('#ciOwnerPhone'),
			regPhone = /^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$/,
			regLoad = /(^[1-9]\d*$)/;
		if ($.trim(ciNumber.val()) === '') {
			$.alert('请输入车牌号码！', function () {
				ciNumber.select();
				$('html, body').stop().animate({
					scrollTop: ciNumber.parent().offset().top
				});
			});
			bool = false;
		} else if (jsDept.val() === '') {
			$.alert('请输入所属部门！', function () {
				//ciNumber.select();
				$('html, body').stop().animate({
					scrollTop: jsDept.parents('#partment-box').offset().top
				});
			});
			bool = false;
		} else if (JSON.parse(jsAll.val()).length >= 2) {
			$.alert('所属部门只能输入一项！', function () {
				//ciNumber.select();
				$('html, body').stop().animate({
					scrollTop: jsDept.parents('#partment-box').offset().top
				});
			});
			bool = false;
		}  else if (ck === '' || !reg.test($.trim(ck))) {
			$.alert('请正确输入行驶里程数！', function () {
				//ciNumber.select();
				$('html, body').stop().animate({
					scrollTop: jsDept.parents('#partment-box').offset().top
				});
			});
			bool = false;
		}else if ($.trim(ciLoadNumber.val()) && !regLoad.test($.trim(ciLoadNumber.val()))) {
			$.alert('荷载人数请输入数字！', function () {
				//ciNumber.select();
				$('html, body').stop().animate({
					scrollTop: ciLoadNumber.parents('#ciLoad-box').offset().top
				});
			})
			bool = false;
		} else if ($.trim(phone.val()) && !regPhone.test($.trim(phone.val()))) {
			$.alert('请输入正确的手机号格式！', function () {
				//ciNumber.select();
				$('html, body').stop().animate({
					scrollTop: phone.parents('#phone-box').offset().top
				});
			});
			bool = false;
		}
		return bool;
	}

	// 渲染select
	function renderSelect(el, data) {
		$(el).html(template('common/select', {
			items: data
		}));
		if ($(el).data('id')) {
			$(el).val($(el).data('id'));
		}
	}

	//新增
	function addCarInfo(carInfo) {
		$.getData({
			url: '/carInfo/addCarInfo',
			body: carInfo
		}, function (data) {
			if (data) {
				// $.alert("操作成功", function () {
				//  window.location.back();
				// })
				$.toast("操作成功!",{type: 'success'}, function () {
					window.history.back();
				})
			} else {
				$.alert("操作失败!");
			}
		});
	}

	//update
	function updateInfo(carInfo) {
		$.getData({
			url: '/carInfo/updateCarInfoById',
			body: carInfo
		}, function (data) {
			if (data) {
				$.toast("操作成功!",{type: 'success'}, function () {
					window.history.back();
				})
			} else {
				$.alert("操作失败!");
			}
		});
	}

	function renderInfo(data) {  //渲染详情
		$('.js-main').html(template('archives/archives-edit', {item: data}));
		var res = {};
		//多选组织树选中项内容
		res.scopeObject = [
			{"isDept": true, "id": data.ciDepartmentId, "title": data.ciDepartment, "avatar": "", "empNum": 1}
		];
		$.dept({
			el: '.js-dept-selector',
			type: 'dept',
			name: 'list',
			default: res.scopeObject // 默认选中值（新增时不需要，用于二次编辑回显）
		});
		domOpetate();
		if (data.ciImage) {
			var urls = data.ciImage.split(",");
			$.uploader({
				el: '.js-uploader',
				default: urls,
				url: '/uploadDown/uploadImages',
				maxLength: 3
			});
		} else {
			$.uploader({
				el: '.js-uploader',
				url: '/uploadDown/uploadImages'
			});
		}
	}

	// 保存数据
	function saveData() {
		var carInfo = collectRData();
		var ciImage = getImgsUrl();
		carInfo.ciImage = ciImage;
		if (isEdit) {
			carInfo.id = id;
			updateInfo(carInfo);
		} else {
			addCarInfo(carInfo);
		}
	}

	//页面渲染后 操作
	function domOpetate() {
		$.counter({el: '#ciRemark'});
		getciBoxType('#ciBoxType'); //变速车辆类型
		typeInfoList('#ciTypeName'); // 车辆类型
		$('#ciBuyDate').datetimepicker({
			language: 'zh-CN',
			format: 'yyyy-mm-dd ',
			minView: 'year',
			autoclose: true
		});
	}

	function init() {
		if (isEdit) {
			getInfo(id);
		} else {
			$('.js-main').html(template('archives/archives-add'));
			domOpetate();
			$.dept({
				el: '.js-dept-selector',
				type: 'dept',
				name: 'list'
			});
			$.uploader({
				url: '/uploadDown/uploadImages',
				imageGroup: 'mm'
			});
		}
		// 保存
		$(document).on('click', '.js-save', function () {
		 var data = valitData();
		 //data = true;
			if (data) {
				saveData(data);
			}
		});
		// 取消
		$(document).on('click', '.js-cancel', function () {
			window.history.back();
		});
	}

	init();
});

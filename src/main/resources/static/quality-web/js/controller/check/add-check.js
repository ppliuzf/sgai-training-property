$(function () {
	var isSaving = true,
		isDefultHg = false, // 待选序列第一个默认为合格，当第二个为合格时，第一个应为不合格，单击合格才为合格
		answerData = [
			{value: 1, title: '单选'},
			{value: 0, title: '数值'}
		],
		qxzrgwData = [
			{value: 1, title: '受险机构-财务收费'},
			{value: 2, title: '受险机构-客服服务'},
			{value: 3, title: '受险机构-品质管控'},
			{value: 4, title: '受险机构-安全防护'},
			{value: 5, title: '受险机构-环境保洁'},
			{value: 6, title: '受险机构-采购仓存'},
			{value: 7, title: '受险机构-工程维修专业线'},
			{value: 8, title: '受险机构-管理处领导'},
			{value: 9, title: '受险机构-管理处前台'}
		],
		dxSelData = [
			{value: '', id: 1, isHg: 1},
			{value: '', id: 2, isHg: 0}
		];
	// 获取专业范畴
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

	// 待选序列
	function renderDXSel(data) {
		$('.select-div').html(template('check/add-radio-list', {
			items: data
		}));
	}

	// 缺陷责任岗位
	function renderPostList(data) {
		var postList = $('#postList');
		postList.html(template('common/select', {items: data}));
		postList.val('2');
	}

	// 表单校验
	function tipFn(content, _this, $par) {
		$.alert(content, function () {
			_this.select();
			$('html, body').stop().animate({
				scrollTop: $par.offset().top
			});
		});
	}

	//判断数组重复方法
	function isRepeat(arr) {
		var hash = {};
		for (var i in arr) {
			if (hash[arr[i]]) {
				return true;
			}
			// 不存在该元素，则赋值为true，可以赋任意值，相应的修改if判断条件即可
			hash[arr[i]] = true;
		}
		return false;
	}

	// 收集图片地址
	function collectImage() {
		var rs = [];
		$('.upload-item').each(function () {
			var obj = {
				riImgUrl: $(this).data('url'),
				riIsDefault: 1,
				riSort: 0
			}
			rs.push(obj);
		});
		return rs;
	}

	function collectData() {
		var rs = {},
			tiName = $('#tiName'),
			pcId = $('#pcId'),
			tiStandard = $('#tiStandard'),
			typeAnswer = $('#type-answer'),
			tiMin = $('#tiMin'),
			tiMax = $('#tiMax'),
			tiLimitTime = $('#tiLimitTime');

		if ($.trim(tiName.val()) === '') {
			isSaving = false;
			tipFn('请输入检验项名称', tiName, tiName.parent());
			return false;
		}
		if (typeAnswer.val() === "1") {
			//待选序列
			var tOptionVal = '';
			var hgOptionVal = '';
			var dxValArr = [];
			var arrVal = [];
			$('.input-div input').each(function () {
				var _this = $(this);
				if ($.trim(_this.val()) === '') {
					tipFn('请输入待选序列', _this, $('.select-list'));
					isSaving = false;
					return false;
				} else {
					var sel1 = $.trim(_this.val());
					if (sel1.length > 0) {
						dxValArr.push(sel1);
						tOptionVal += sel1 + ' | ';
					}
					//var hgSel = $(v).find('[isHg=1]').parent().find('[name=cre-select]').val();
					var isHg = _this.parent('.input-div').siblings().attr('isHg');
					var s = $.trim(_this.val());
					if (isHg === '1') {
						hgOptionVal += s.length > 0 ? s + ' | ' : '';
					}
				}
				arrVal.push($.trim(_this.val()));
			});
            if(tOptionVal.length > 0 && tOptionVal.substring(tOptionVal.length - 3, tOptionVal.length) === ' | '){
                tOptionVal = tOptionVal.substring(0, tOptionVal.length - 3);
            }
            if(hgOptionVal.length > 0 && hgOptionVal.substring(hgOptionVal.length - 3, hgOptionVal.length) === ' | '){
                hgOptionVal = hgOptionVal.substring(0, hgOptionVal.length - 3);
            }
			if (isRepeat(arrVal)) {
				tipFn('待选序列不能重复', $('.input-div input:first'), $('.dyxl'));
				isSaving = false;
				return false;
			}
		}
		if (typeAnswer.val() === "0") {
			if ($.trim(tiMin.val()) === '') {
				isSaving = false;
				tipFn('请输入正常值下限', tiMin, tiMin.parent());
				return false;
			} else {
				var numExec = /^\d*(\.?)(\d{1,2})?$/;
				if (!numExec.test($.trim(tiMin.val()))) {
					isSaving = false;
					tipFn('输入格式有误，只支持数字且最多只能输入两位小数', tiMin, tiMin.parent());
					return false;
				}
			}
			if ($.trim(tiMax.val()) === '') {
				isSaving = false;
				tipFn('请输入正常值上限', tiMax, tiMax.parent());
				return false;
			} else {
				var numExec = /^\d*(\.?)(\d{1,2})?$/;
				if (!numExec.test($.trim(tiMax.val()))) {
					isSaving = false;
					tipFn('输入格式有误，只支持数字且最多只能输入两位小数', tiMax, tiMax.parent());
					return false;
				}
			}
			if ($.trim(tiMax.val()) !== '' && $.trim(tiMin.val()) !== '') {
				var maxNum = $.trim(tiMax.val()) * 100;
				var minNum = $.trim(tiMin.val()) * 100;
				if (maxNum < minNum) {
					isSaving = false;
					tipFn('正常值上限不能小于正常值下限', tiMin, tiMin.parent());
					return false;
				}
			}
		}
		if ($.trim($('#tiLimitTime').val()) !== '') {
			var numExec = /^[1-9]\d{0,4}$/;
			if (!numExec.test($.trim(tiLimitTime.val()))) {
				isSaving = false;
				tipFn('输入格式有误，只支持小于5位的整数', tiLimitTime, tiLimitTime.parent());
				return false;
			}

		}
		rs.tiName = $.trim(tiName.val());
		if (!pcId.val()) {
			$.alert('请选择专业范畴')
			return false;
		}
		rs.pcId = pcId.val();
		rs.pcName = pcId.find("option:selected").text(); // 专业范畴名称
		rs.tiStandard = $.trim(tiStandard.val()); // 标准
		var imgStr = collectImage();
		rs.urls = imgStr.length === 0 ? '' : JSON.stringify(imgStr); // 上传图片
		rs.tiIsInput = typeAnswer.val(); // 答案类型
		rs.tiOptions = tOptionVal;  // 待选序列文本
		rs.tiStandardOption = hgOptionVal;
		rs.tiMax = $.trim(tiMax.val()) === '' ? 0 : tiMax.val(); // 正常值上限
		rs.tiMin = $.trim(tiMin.val()) === '' ? 0 : tiMin.val(); // 正常值下限
		rs.tiUnit = $.trim($('#tiUnit').val()); // 数值单位
		rs.tiPostId = parseInt($('#postList').val()); // 缺陷责任岗位
		rs.tiPostName = $('#postList').find('option:selected').text();
		rs.tiLimitTime = $.trim(tiLimitTime.val()) === '' ? 0 : $.trim(tiLimitTime.val()); // 完成期限
		rs.tiFinshUnit = $('#finishUnit').val(); // 完成期限单位
		rs.tiRectificationRequirements = $.trim($('#zgyq').val());
		return rs;
	}

	// 新增数据
	function addData(data) {
		$.getData({
			url: '/testitem/createTestItem',
			body: data
		}, function (data) {
			if (data) {
				$.msg('保存成功！', {time: 2000}, function () {
					history.back();
				});
			} else {
				$.alert('保存失败！');
			}
		});
	}

	// 渲染专业范畴
	function renderCategory(data) {
		$('.select-pc').html(template('common/select', {items: data}));
	}

	function init() {
		getCategoryList();
		$.counter({el: '#tiStandard', max: 500});
		$.uploader({
			url: '/uploadDown/uploadImages',
			imageGroup: 'mm',
			isInsertImage: true,
			onError: function (data) {
				$.alert(data);
			}
		});
		//默认展示单选
		renderDXSel(dxSelData);
		//渲染责任岗位列表
		renderPostList(qxzrgwData);
		//整改要求
		$.counter2({el: '#zgyq', max: 500});
		//	选择答案类型
		$('#type-answer').change(function (el) {
			var selATVal = $(this).val();
			if (selATVal === 0 || selATVal === '0') {
				$('.dyxl').hide();
				$('.num-div').show();
			} else if (selATVal === 1 || selATVal === '1') {
				$('#tiMax').val('');
				$('#tiMin').val('');
				$('#tiUnit').val('');
				$('.num-div').hide();
				isDefultHg = false;
				renderDXSel(dxSelData);
				$('.dyxl').show();
			}
		})
		// 设为合格选项
		$(document).on('click', '.js-hege', function () {
			var $this = $(this);
			var inputSel = $this.closest('li').find('[name=cre-select]');
			if ($.trim(inputSel.val()) === '') {
				$.alert('请输入待选项', function () {
					inputSel.select();
					$('html, body').stop().animate({
						scrollTop: inputSel.offset().top
					});
				});
				return false;
			} else {
				// 把之前合格的都设置为不合格，确保当前合格
				$('.select-list li').find('[ishg=1] .remove a').hide();
				$('.select-list li').find('[ishg=1]').attr('isHg', '0');
				$this.closest('li').find('.result-div').attr('isHg', '1');
				$this.closest('li').find('.result-div .remove a').hide();
				// 只有超过2项时，才显示移除
				var ulLength = $this.closest('ul').find('li').length;
				if (ulLength > 2) {
					$('.select-list li').find('[ishg=0] .remove a').show();
				}
			}
		});
		// 移除选项
		$(document).on('click', '.js-remove', function () {
			var $this = $(this);
			var ulLength = $this.closest('ul').find('li').length;
			if (ulLength > 2) {
				$this.closest('li').remove();
				$('.select-list li').find('.result-div .remove a').hide();
				$(".create-select").show();
			} else {
				$.alert('待选项至少2个！');
			}
		});
		// 添加选项
		$(document).on('click', '.js-create-select', function () {
			var $this = $(this);
			var date = (new Date()).valueOf();
			// var newDXJson = {
			//    value: '',
			//    id: date,
			//    isHg: 0
			// };
			// dxSelData.push(newDXJson);
			var temp = '<li data-Id="' + date + '">' +
				'<div class="input-div">' +
				'<input type="text" name="cre-select" id="sel' + date + '" placeholder="请输入" class="form-control" maxlength="10">' +
				'</div>' +
				'<div class="result-div" isHg="0">' +
				'<span class="remove"><a class="js-remove underline" data-Id="' + date + '" href="javascript:;">移除</a></span>' +
				'<span class="hg"><a class="js-del underline" data-Id="' + date + '"><i class="icon-ok"></i>合格</a></span>' +
				'<span class="bhg"><a class="js-hege underline" data-Id="' + date + '">设为合格</a></span>' +
				'</div>' +
				'</li>';
			$(".select-list").append(temp);
			// 当超过2项时，才显示移除
			var ulLength = $(".select-list").find('li').length;
			if (ulLength > 2) {
				$(".select-list li").find('[ishg=0]').find('.remove a').show();
			}
			$(".create-select").hide();
		});
		// 保存
		$(document).on('click', '.js-save', function () {
			var data = collectData();
			if(!data){
				return;
			}
			if (!isSaving) {
				isSaving = true; // 加锁
				return;
			}
			// var data=
			// {
			// 	"pcId": "3",
			// 	"pcName": "默认专业范畴",
			// 	"tiFinshUnit": 1,
			// 	"tiIsInput": 1,
			// 	"tiLimitTime": 1,
			// 	"tiMax": 0,
			// 	"tiMin": 0,
			// 	"tiName": "测试l",
			// 	"tiOptions": "撒旦法撒旦 | 水电费",
			// 	"tiPostId": 2,
			// 	"tiPostName": "受险机构-客服服务",
			// 	"tiRectificationRequirements": "",
			// 	"tiStandard": "水电费水电费",
			// 	"tiStandardOption": "撒旦法撒旦",
			// 	"tiUnit": "string",
			// 	"urls": ""
			// }

			addData(data);
		});
		// 取消
		$(document).on('click', '.js-cancel', function () {
			history.back();
		});
	}

	init();
})

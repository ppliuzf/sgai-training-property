$(function () {
	var id = $.getQueryString('id');
	//获取详情
	function getInfo() {
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

	function getOne (pageNum,isFirst) {
		$.getData({
			url: '/carUserRelationInfo/getMaintainDetail',
			query:{
				carId: id,
				pageNum: pageNum,
				pageSize: 10
			}
	}, function(data) {
			isFirst && $.renderPage({
			    count: data.count,
			    limit: 10,
			    jump: function(num) {
						getOne(num, false);
			    }
			})
			if (data.list.length && data.list) {
				data.list.forEach((val) => {
					val.maintainTime =  $.formatTime(val.maintainTime)
				});
				renderOne(data.list)
			} else {
			    renderEmpty1();
			}
	});
	}

	function getTwo (pageNum,isFirst) {
		$.getData({
			url: '/carRepairRecode/getCarRepairRecodePageList',
			query:{
				carId: id,
				pageNum: pageNum,
				pageSize: 10
			}
	}, function(data) {
			isFirst && $.renderPage({
			    count: data.count,
			    limit: 10,
			    jump: function(num) {
						getTwo(num, false);
			    }
			})
			if (data.list.length && data.list) {
				data.list.forEach((val) => {
					val.rrDate =  $.formatTime(val.rrDate)
				});
				renderTwo(data.list)
			} else {
			    renderEmpty2();
			}
	});
	}


	function getTher (pageNum,isFirst) {
		$.getData({
			url: '/carUserRelationInfo/getPunishPageInfo',
			query:{
				carId: id,
				pageNum: pageNum,
				pageSize: 10
			}
	}, function(data) {
			isFirst && $.renderPage({
			    count: data.count,
			    limit: 10,
			    jump: function(num) {
						getTher(num, false);
			    }
			})
			if (data.list.length && data.list) {
				data.list.forEach((val) => {
					val.punishTime =  $.formatTime(val.punishTime)
				});
				renderTher(data.list)
			} else {
			    renderEmpty3();
			}
	});
	}

	function renderOne(data) {
		$('.js-list1').html(template('archives/one', {items: data}));
	}
	function renderTwo(data) {
		$('.js-list2').html(template('archives/two', {items: data}));
	}

	function renderTher(data) {
		$('.js-list3').html(template('archives/ther', {items: data}));
	}
	function renderInfo(data) {
		$('.js-main').html(template('archives/archives-detail', {item: data}));
	}

	// <li class="active" id='appointment'><a href="#appointment" data-toggle="tab">预约记录</a></li>
	// <li id='maintain'><a href="#maintain" data-toggle="tab">保养记录</a></li>
	// <li id='repair'><a href="#repair" data-toggle="tab">维修记录</a></li>
	// <li id='regulations'><a href="#regulations" data-toggle="tab">违章记录</a></li>
	$(document).on('click', '#one', function(){
		getUserList(true, 1)
	});
	$(document).on('click', '#two', function(){
		getOne(1,true)
	})
	$(document).on('click', '#ther', function(){
		getTwo(1,true)
	})
	$(document).on('click', '#for', function(){
		getTher(1,true)
	})

	//点击新增保养
		$(document).on('click', '#by', function(){
			setTimeout(function(){
				$('.modal-content').css({"width":"105%"})
				$('.modal-content').css({"height":"500px"})
				$('.modal-footer').css({"position":"relative","top":"-30px"})		
				 var font = '保存'
				 $('.js-modal-sure').text(font)
		},0)
		$.pop({
				title: '新增保养记录',
				size: 'md',
				noIcon: true,
				content: `
				<form class="form-horizontal">
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><span class='require'></span>保养名称</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="name" placeholder="请输入保养名称" maxlength="20">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label"><span class='require'></span>保养类型</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="type" placeholder="请输入保养类型" maxlength="20">
					</div>
				</div>
				<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label"><span class='require'></span>保养时间</label>
				<div class="col-sm-10">
				<div class="input-group">
				<input type="text" class="form-control js-date" placeholder="请选择保养时间" aria-describedby="basic-addon1" id="time" readonly>
				<span class="input-group-addon" id="basic-addon1">
						<span class="glyphicon glyphicon-remove"></span>
				</span>
				</div>
				</div>
			

			</div>
			<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">		<span class='require'></span>保养单位</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="danwei" placeholder="请输入保养单位" maxlength="20">
			</div>
		</div>
		<div class="form-group">

		<label for="inputPassword3" class="col-sm-2 control-label">		<span class='require'></span>保养人</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="people" placeholder="请输入保养人" maxlength="20">
		</div>
	</div>
	<div class="form-group">
	
	<label for="inputPassword3" class="col-sm-2 control-label"><span class='require'></span>备注</label>
	<div class="col-sm-10" >
	<textarea class="form-control js-textarea" id='remker' rows="7" maxlength="200"></textarea>
	<div class="text-right js-number zishu">0/200</div>
	</div>
</div>
	
			</form>
								 `,
				success: function() {
						$('#time').datetimepicker({
								format: 'yyyy-mm-dd hh:ii',
								language: 'zh-CN',
								autoclose: true,
								minView: 0,
								minuteStep: 5,
								endDate:new Date()
						});
						$.kmCount();
						$(document).on('click', '#basic-addon1', function(){
							$(this).prev().val('')
						})
						$(document).on('click', '#tiem', function(){
								$(this).blur()
						})
				},
				
				yes: function() {
					var params = {};
					params.maintainName = $.trim($('#name').val());
					params.maintainType = $.trim($('#type').val());
					params.maintainTime = $.getTimeStamp($('#time').val());
					params.maintainComName = $.trim($('#danwei').val());
					params.maintainUserName = $.trim($('#people').val());
					params.maintainDesc = $.trim($('#remker').val());
					params.carId = id;
					if (params.maintainName === '') {
							$.msg('请输入保养名称');
							return false;
					} else if (params.maintainType === '') {
							$.msg('请输入保养类型');
							return false;
					} else if (params.maintainTime === '') {
							$.msg('请选择保养时间');
							return false;
					} else if (params.maintainComName === '') {
							$.msg('请输入保养单位');
							return false;
					} else if (params.maintainUserName === '') {
							$.msg('请输入保养人');
							return false;
					} else if (params.maintainDesc === '') {
							$.msg('请输入保养备注');
							return false;
					} else{
						$.getData({
							url: '/carUserRelationInfo/addMaintain',
							body: params,
					}, function(data) {
							getOne(1,true)
							if(data == true){
								$.msg('保存成功');
							}
					});
					}
				}
		})
		})


	//点击新增维修
	$(document).on('click', '#wx', function(){
		setTimeout(function(){
			$('.modal-content').css({"width":"105%"})
			$('.modal-content').css({"height":"600px"})
			$('.modal-footer').css({"position":"relative","top":"-30px"})
			 var font = '保存'
			 $('.js-modal-sure').text(font)
	},0)
	$.pop({
			title: '新增维修记录',
			size: 'md',
			noIcon: true,
			content: `
			<form class="form-horizontal">
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label"><span class='require'></span>维修日期</label>
				<div class="col-sm-10">
				<div class="input-group">
				<input type="text" class="form-control" placeholder="请选择维修时间" aria-describedby="basic-addon1" readonly id="time">
				<span class="input-group-addon" id="basic-addon1">
						<span class="glyphicon glyphicon-remove"></span>
				</span>
				</div>
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label"><span class='require'></span>维修人</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="people" placeholder="请输入维修人" maxlength="20">
				</div>
			</div>
			<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">	<span class='require'></span>手机号码</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="tel" placeholder="请输入手机号码">
			</div>
		</div>
		<div class="form-group">
		<label for="inputPassword3" class="col-sm-2 control-label"><span class='require'></span>维修单位</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="danwei" placeholder="请输入维修单位" maxlength="20">
		</div>
	</div>
	<div class="form-group">
	<label for="inputPassword3" class="col-sm-2 control-label"><span class='require'></span>维修内容</label>
	<div class="col-sm-10" >
	<textarea class="form-control js-textarea" id='neirong' rows="7" maxlength="200"></textarea>
	<div class="text-right js-number zishu">0/200</div>
	</div>
	</div>
<div class="form-group">
<label for="inputPassword3" class="col-sm-2 control-label"><span class='require'></span>维修原因</label>
<div class="col-sm-10" >
<textarea class="form-control js-textarea" id='yuanyin' rows="7" maxlength="200" style='margin-top:-20px'></textarea>
<div class="text-right js-number zishu">0/200</div>
</div>
</div>

		</form>
							 `,
			success: function() {
					$('#time').datetimepicker({
							format: 'yyyy-mm-dd hh:ii',
							language: 'zh-CN',
							autoclose: true,
							minView: 0,
							minuteStep: 5,
							endDate:new Date()
					});
					$.kmCount()
					$(document).on('click', '#basic-addon1', function(){
						$(this).prev().val('')
					})
			},

			yes: function() {
				var params = {};
				params.rrPhoneNum  =  $.trim($('#tel').val());
				params.rrContent = $.trim($('#neirong').val());
				params.rrDate  =  $.getTimeStamp($('#time').val());
				params.rrAddress  =  $.trim($('#danwei').val());
				params.rrName =  $.trim($('#people').val());
				params.rrRemark =  $.trim($('#yuanyin').val());
				params.carId = id;
				var mobilevalid = /^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$/;
				if (params.rrDate  === '') {
						$.msg('请选择维修日期');
						return false;
				} else if (params.rrName === '') {
						$.msg('请输入维修人');
						return false;
				} else if (params.rrPhoneNum  === '') {
						$.msg('请输入手机号');
						return false;
				} else if (params.rrAddress  === '') {
						$.msg('请输入维修单位');
						return false;
				} else if (params.rrContent === '') {
						$.msg('请输入维修内容');
						return false;
				} else if (params.rrRemark === '') {
						$.msg('请输入维修原因');
						return false;
				} else if (!mobilevalid.test(params.rrPhoneNum )) {
					$.msg('请输入正确手机号');
					return false;
			  } else{
					$.getData({
						url: '/carRepairRecode/addCarRepairRecode',
						body: params,
				}, function(data) {
						getTwo(1,true)
						if(data == true){
							$.msg('保存成功');
						}
				});
				}
			}
	})
	})

	//点击新增违章
	$(document).on('click', '#wz', function(){
		setTimeout(function(){
			$('.modal-content').css({"width":"105%"})
			$('.modal-content').css({"height":"600px"})
			$('.modal-footer').css({"position":"relative","top":"-30px"})			
			 var font = '保存'
			 $('.js-modal-sure').text(font)
	},0)
	$.pop({
			title: '新增违章记录',
			size: 'md',
			noIcon: true,
			content: `
			<form class="form-horizontal">
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label"><span class='require'></span>违章日期</label>
				<div class="col-sm-10">
				<div class="input-group">
				<input type="text" class="form-control" placeholder="请选择违章时间" aria-describedby="basic-addon1" id="time" readonly>
				<span class="input-group-addon" id="basic-addon1">
						<span class="glyphicon glyphicon-remove"></span>
				</span>
				</div>
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label"><span class='require'></span>违章人</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="people" placeholder="请输入违章人" maxlength="20">
				</div>
			</div>
			<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">		<span class='require'></span>手机号码</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="tel" placeholder="请输入手机号码">
			</div>
		</div>
		<div class="form-group">
		<label for="inputPassword3" class="col-sm-2 control-label">		<span class='require'></span>违章类型</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="danwei" placeholder="请输入违章类型" maxlength="10">
		</div>
	</div>
	<div class="form-group">
	<label for="inputPassword3" class="col-sm-2 control-label"><span class='require'></span>违章内容</label>
	<div class="col-sm-10" >
	<textarea class="form-control js-textarea" id='neirong' rows="7" maxlength="200"></textarea>
	<div class="text-right js-number zishu">0/200</div>
	</div>
	</div>


<div class="form-group">
<label for="inputPassword3" class="col-sm-2 control-label"><span class='require'></span>违章原因</label>
<div class="col-sm-10" >
<textarea class="form-control js-textarea" id='yuanyin' rows="7" maxlength="200" style='margin-top:-20px'></textarea>
<div class="text-right js-number zishu">0/200</div>
</div>
</div>

		</form>
							 `,
			success: function() {
					$('#time').datetimepicker({
							format: 'yyyy-mm-dd hh:ii',
							language: 'zh-CN',
							autoclose: true,
							minView: 0,
							minuteStep: 5,
							endDate:new Date()
					});
					$.kmCount()
					$(document).on('click', '#basic-addon1', function(){
						$(this).prev().val('')
					})
			},

			yes: function() {
				var params = {};
				params.punishUserPhoneNum  =  $.trim($('#tel').val());
				params.punishContent  =  $.trim($('#neirong').val());
				params.punishTime  =  $.getTimeStamp($('#time').val());
				params.punishType  =  $.trim($('#danwei').val());
				params.punishUserName  =  $.trim($('#people').val());
				params.punishDesc  =  $.trim($('#yuanyin').val());
				params.carId = id;
				var mobilevalid = /^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$/;
				if (params.punishTime  === '') {
						$.msg('请选择违章日期');
						return false;
				} else if (params.punishUserName  === '') {
						$.msg('请输入违章人');
						return false;
				} else if (params.punishUserPhoneNum  === '') {
						$.msg('请输入手机号');
						return false;
				} else if (params.punishType  === '') {
						$.msg('请输入违章类型');
						return false;
				} else if (params.punishContent  === '') {
						$.msg('请输入违章内容');
						return false;
				} else if (params.punishDesc  === '') {
						$.msg('请输入违章原因');
						return false;
				} else if (!mobilevalid.test(params.punishUserPhoneNum)) {
					$.msg('请输入正确手机号');
					return false;
				} else{
					$.getData({
						url: '/carUserRelationInfo/addPunish',
						body: params,
				}, function(data) {
						getTher(1,true)
						if(data == true){
							$.msg('保存成功');
						}
				});
				}
			}
	})
	})
	
	//获取预约人信息
	function getUserList(isFirst, pageNum) {
		$.getData({
			url: '/carInfo/getCarUserRelationByCardId',
			query: {
				cardId: id,
				pageNum: pageNum,
				pageSize: 10
			},
		}, function (data) {
			//console.log(data)
			isFirst && $.renderPage({
				count: data.count,
				jump: function (num) {
					getUserList(true,num);
				}
			});
			if (data.list && data.list.length) {
				renderList(data.list);
			} else {
				renderEmpty();
			}
		});
	}
	
	function renderInfo(data) {
		$('.js-main').html(template('archives/archives-detail', {item: data}));
	}


	
	// 渲染列表
	function renderList(data) {
		$('.js-list').html(template('archives/archives-user-list', {
			items: data
		}));
	}
	
	// 渲染空
	function renderEmpty() {
		$('.js-list').html(template('common/record-empty', {
			colspan: 6
		}));
	}
	function renderEmpty1() {
		$('.js-list1').html(template('common/record-empty', {
			colspan: 7
		}));
	}
	function renderEmpty2() {
		$('.js-list2').html(template('common/record-empty', {
			colspan: 7
		}));
	}
	function renderEmpty3() {
		$('.js-list3').html(template('common/record-empty', {
			colspan: 7
		}));
	}
	
	function init() {
		getInfo(); //车辆详情
		getUserList(true,1); //获取预约人信息;
	
		$(document).on('click', '.btn-back', function () {
			window.history.back();
		})




































	}
	
	init();
});
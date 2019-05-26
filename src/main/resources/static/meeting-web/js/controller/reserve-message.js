$(function () {
	var ids = $.getQueryString('id');
	var newDate = new Date();
	var currPage = 1;
	var todayT = $.getTimeStamp(
		newDate.getFullYear() +
		"-" +
		(newDate.getMonth() + 1) +
		"-" +
		newDate.getDate()
	);
	var dateT = $.getTimeStamp(
		newDate.getFullYear() +
		"-" +
		(newDate.getMonth() + 1) +
		"-" +
		newDate.getDate()
	);
	// 收集搜索数据
	function collectSearch() {
		var rs = {};
		rs.startTime  = dateT;
		rs.rrId = ids;
		return rs;
	}
	// 获取会议室列表
	function getRoomList(data) {
		$.getData({
				url: "/roomResource/getReserveDetail",
				query: {
					falg: 1
				},
				body:data
			},
			function (data) {
				$(".meeting-title").html("预定信息-"+data.roomName)
				if (data.reserveRoomVoList && data.reserveRoomVoList.length) {
					
					renderRoomList(data.reserveRoomVoList);
				} else {
					renderEmpty();
				}
			}
		);
	}
	// 渲染会议室列表
	function renderRoomList(data) {
		$(".js-list").html(
			template("plan/reserve-message", {
				items: data
			})
		);
	}
	// 渲染空
	function renderEmpty() {
		$(".js-list").html(
			template("common/record-empty", {
				text: "暂无会议",
				colspan: 4
			})
		);
	}
	function init() {
		// 初始化日期列表
		$.weekSelector({
			onselect: function (val) {
				var curD = new Date(val);
				dateT = $.getTimeStamp(
					curD.getFullYear() +
					"-" +
					(curD.getMonth() + 1) +
					"-" +
					curD.getDate()
				);
				var data = collectSearch();
				if (data) {
					getRoomList(data);
				}
			}
		});
		getRoomList(collectSearch());
	}
	init();
});
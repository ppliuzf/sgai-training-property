$(function () {
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

    function comparisonTime(time) {
        var curDate = $.formatTime(new Date().getTime()).split(" ");
        if (todayT > dateT) {
            $.alert("开始日期必须晚于当前日期");
            return false;
        } else if (todayT == dateT) {
            if (time < curDate[1]) {
                $.alert("开始时间必须晚于当前时间");
                return false;
            }
        }
        return true;
    }
    // 收集搜索数据
    function collectSearch() {
        var rs = {},
            startTime = parseInt($("#start-time").val()),
            endTime = parseInt($("#end-time").val()),
            timeStr = startTime;
        rs.currtDate = dateT;

        if (!comparisonTime($("#start-time option:selected").text())) {
            return false;
        }
        if (startTime > endTime) {
            $.alert("开始时间必须早于结束时间");
            return false;
        }
        if (startTime == endTime) {
            timeStr = startTime + "," + endTime;
        } else {
            for (var j = startTime; j < endTime; j++) {
                timeStr = timeStr + "," + (j + 1);
            }
        }
        rs.timeSeg = timeStr;
        // rs.timeSeg = startTime + ',' + endTime;
        return rs;
    }
    // 获取会议室列表
    function getRoomList(params) {
        params.pageSize = 10;
        params.pageNo = currPage;
        $.getData({
                url: "/meetings/selectMeetingRoomPC",
                query: params
            },
            function (data) {
                if (data.list && data.list.length) {
                    for (var i in data.list) {
                        data.list[i].roomImg = data.list[i].rrRoomPicMain ?
                            data.list[i].rrRoomPicMain.split(" | ")[0] :
                            "";
                    }
                    renderRoomList(data.list);
                } else {
                    renderEmpty();
                }
                let total = data.count;
                $.renderPage({
                    count: total, //数据总条数
                    curr: currPage, //展示的是第几页
                    pageSize: 10,
                    jump: function (num) {
                        currPage = num;
                        getRoomList(params)
                        // renderRoomList(data.list);
                    }
                });
            }
        );
    }
    // 渲染会议室列表
    function renderRoomList(data) {
        $(".js-list").html(
            template("plan/order", {
                items: data
            })
        );
    }
    // 渲染空
    function renderEmpty() {
        $(".js-list").html(
            template("common/record-empty", {
                text: "还没有会议室，请创建会议室",
                colspan: 6
            })
        );
    }
    // function fillZero(n) {
    //     return n < 10 ? '0' + n : n;
    // }
    function setCurTime(times) {
        var curT = $.formatTime(new Date().getTime()).split(" ")[1];
        var curH = curT.split(":")[0];
        var curM = curT.split(":")[1];
        if (curM < 30) {
            curT = curH + ":" + 30;
        } else {
            curT = parseInt(curH) + 1 + ":" + "00";
        }
        for (var i in times) {
            if (times[i].title == curT) {
                return times[i].value;
            }
        }
    }
    //修改设备状态
    function updateDeviceStatus(obj) {
        var rdsId = obj.attr("data-rdsId"),
            rdsState = obj.attr("data-rdsState");
        rdsState = rdsState == 0 ? 1 : 0;
        $.getData({
                url: "/roomResource/updateDeviceStatus;",
                query: {
                    rdsId: rdsId,
                    status: rdsState
                }
            },
            function (data) {
                console.log(data);
                if (data) {
                    var data = collectSearch();
                    // $.msg("操作成功");
                    $.toast("操作成功", {
                        type: 'success'
                    });
                    getRoomList(data);
                }
            }
        );
    }

    function orderRoom(id) {
        var startT = parseInt($("#start-time").val()),
            endT = parseInt($("#end-time").val());
        var timeP = startT;
        if (!comparisonTime($("#start-time option:selected").text())) {
            return false;
        }
        if (startT == endT) {
            timeP = startT + "," + endT;
        } else {
            for (var j = startT; j < endT; j++) {
                timeP = timeP + "," + (j + 1);
            }
        }
        $.getData({
                url: "/meetings/confirmTimeSeg",
                query: {
                    currtDate: dateT,
                    rrId: id,
                    timeSeg: timeP
                }
            },
            function (data) {
                console.log(data);
                if (data) {
                    location.href =
                        "./order-add.html?roomid=" +
                        id +
                        "&date=" +
                        dateT +
                        "&time=" +
                        $("#start-time option:selected").text() +
                        "," +
                        $("#end-time option:selected").text()

                } else {
                    $.alert("该会议室已被占用");
                }
            }
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
        var times = $.initTimeList();
        var sTiems = [],
            eTimes = [];
        for (var i in times) {
            sTiems.push({
                title: times[i].startTime,
                value: i
            });
            eTimes.push({
                title: times[i].endTime,
                value: i
            });
        }
        // var times = [],
        //     j = 0;
        // for (var i = 7; i <= 22; i += .5) {
        //     times.push({
        //         title: fillZero(Math.floor(i)) + ':' + (i % 1 === .5 ? '30' : '00'),
        //         value: j++
        //     });
        // }
        // 初始化开始时间和结束时间
        $("#start-time")
            .html(
                template("common/select", {
                    items: sTiems
                })
            )
            .val(setCurTime(sTiems));
        $("#end-time").html(
            template("common/select", {
                items: eTimes,
                value: "29"
            })
        );

        //修改设备状态
        // $(document).on("click", ".device-txt", function () {
        //     var that = $(this);
        //     updateDeviceStatus(that);
        // });
        // 搜索
        $(document).on("click", ".js-search", function () {
            var data = collectSearch();
            if (data) {
                getRoomList(data);
            }
        });
        // 预订
        $(document).on("click", ".js-order", function () {
            var id = $(this).data("id");
            orderRoom(id);
        });
        getRoomList(collectSearch());
        //我的预定跳转
        // $(document).on("click","MyOrder",function(){

        // })
    }
    init();
});
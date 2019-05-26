$(function () {
    var param = {
        startTime: "",
        endTime: "",
        miStatus: "",
        rrRoomName: ""
    };
    var currPage = 1;
    // 获取列表
    function getList() {
        $.getData({
                url: "/roomResource/getMyReserve",
                query: {
                    pageNo: currPage,
                    pageSize: 10
                },
                body: param
            },
            function (data) {
                $.renderPage({
                    curr: currPage,
                    count: data.count,
                    jump: function (num) {
                        currPage = num;
                        getList(num);
                    }
                });
                if (data.list && data.list.length) {
                    for (var i in data.list) {
                        var dataL = data.list[i];
                        dataL.time = dataL.miMtTimeSeg.split(" ")[1].replace("-", ",");
                        dataL.roomImg = dataL.rrRoomPicMain ?
                            dataL.rrRoomPicMain.split(" | ")[0] :
                            "";
                    }
                    renderList(data.list);
                } else {
                    renderEmpty();
                }
            }
        );
    }
    // 渲染列表
    function renderList(data) {
        $(".js-list").html(
            template("./plan/order-list", {
                items: data
            })
        );
    }
    // 渲染无数据
    function renderEmpty() {
        $(".js-list").html(
            template("common/record-empty", {
                colspan: 8
            })
        );
    }
    // 取消
    function cancel(id) {
        $.getData({
                url: "/meetings/cancelMeeting",
                query: {
                    id: id
                }
            },
            function (data) {
                if (data) {
                    // $.msg("操作成功");
                    $.toast("操作成功", {
                        type: 'success'
                    });
                    getList();
                } else {
                    $.msg(data.message);
                }
            }
        );
    }
    // 结束
    function end(id) {
        $.getData({
                url: "/meetings/finishMeeting",
                query: {
                    id: id
                }
            },
            function (data) {
                if (data) {
                    // $.msg("操作成功");
                    $.toast("操作成功", {
                        type: 'success'
                    });
                    getList();
                } else {
                    $.toast(data.message, {
                        type: 'success'
                    });
                    // $.msg(data.message);
                }
            }
        );
    }

    function init() {
        $("#name").val("");
        $("#type option:first").attr("selected",true).siblings("option").attr("selected",false);

        $("#start-time").datetimepicker({
            format: "yyyy-mm-dd hh:ii",
            autoclose: true,
            language: "zh-CN",
            minuteStep: 30
        });
        $("#end-time").datetimepicker({
            format: "yyyy-mm-dd hh:ii",
            autoclose: true,
            language: "zh-CN",
            minuteStep: 30
        });
        getList();
        $(document).on("click", ".search-btn", function () {
            // param.startTime = $.getTimeStamp($('#start-time').val());
            // param.endTime = $.getTimeStamp($('#end-time').val());
            // if (param.startTime && param.endTime && param.startTime >= param.endTime) {
            //     $.alert('时间范围不正确');
            //     return;
            // }
            param.miStatus = $("#type").val();
            param.rrRoomName = $.trim($("#name").val());
            getList();
        });
        // 编辑、、
        $(document).on("click", ".js-edit", function () {
            location.href =
                "./order-add.html?id=" +
                $(this).data("id") +
                "&date=" +
                $(this).data("date") +
                "&time=" +
                $(this).data("time") +
                "&roomid=" +
                $(this).data("roomid");
        });

        // 取消
        $(document).on("click", ".js-cancel", function () {
            var id = $(this).data("id");
            $.bubble({
                el: $(".js-cancel"),
                msg: '您确定要取消当前预约？',
                ok: function () {
                        cancel(id);
                },
                cancel: function () {
                    $.toast('您已取消');
                }
            });

            // $.confirm("确定取消当前预约？", function () {
            //     cancel(id);
            // });
            return false;
        });
        // 结束
        $(document).on("click", ".js-end", function () {
            var id = $(this).data("id");
            $.bubble({
                el: $(this),
                msg: '您确定结束当前预约？',
                ok: function () {      
                        end(id);
                },
                cancel: function () {
                    $.toast('已取消');
                }
            });

            // $.confirm("您确定结束当前预约？", function () {
            //     end(id);
            // });
            return false;
        });
        // 清空时间选择
        $(document).on("click", ".js-clean", function () {
            $(this)
                .prev()
                .val("");
        });
    }
    init();
});
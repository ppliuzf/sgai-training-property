$(function () {
    var param = {
        startTime: "",
        endTime: "",
        miStatus: "",
        rrRoomName: "",
        createEiName: ""
    };
    var currPage = 1;
    // 获取列表
    function getList() {
        $.getData({
                url: "/roomResource/getMyReserve",
                query: {
                    pageNo: currPage,
                    pageSize: 10,
                    falg:1
                },
                body: param
            },
            function (data) {
                console.log(data)
                $.renderPage({
                    curr: currPage,
                    count: data.count,
                    jump: function (num) {
                        currPage = num;
                        getList();
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
            template("plan/reserve-list", {
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
    function init() {
        $("#person").val("");
        $("#name").val("");
        $("#start-time").val("");
        $("#end-time").val("");
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
        //搜索
        $(document).on("click", ".search-btn", function () {
            param.createEiName = $.trim($("#person").val());
            param.miStatus = $("#type").val();
            param.rrRoomName = $.trim($("#name").val());
            param.startTime = new Date($("#start-time").val()).getTime();
            param.endTime = new Date($("#end-time").val()).getTime();
            if(param.endTime < new Date()){
                $.alert("结束时间必须晚于当前时间");
                return
            }
            if(param.startTime >= param.endTime){
                $.alert("结束时间必须晚于开始时间")
            }else {
                getList();
            }
            
        });
        //重置
        $(document).on("click", ".reset-btn", function () {
            $("#person").val("");
            $("#name").val("");
            $("#start-time").val("");
            $("#end-time").val("");
            param.startTime = "";
            param.endTime= "";
            param.miStatus= "";
            param.rrRoomName= "";
            param.createEiName= "";

        });
    }
    init();
});
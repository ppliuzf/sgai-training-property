$(function() {
    var currPage = 1,
        params = {
            keyWord: "",
            pageSize: 10,
            rrRoomState: ""
        };
    // 渲染列表
    function renderList(data) {
        $(".js-list")
            .html(
                template("plan/room-list", {
                    items: data
                })
            )
            .find(".js-status")
            $('.js-status').bootstrapSwitch({
                size: 'mini',
                onText: '启用',
                offText: "禁用",
                onSwitchChange: function() {
                    changeStatus($(this).data("id"), $(this).is(":checked"));
                }
            });
            // .bootstrapSwitch({
            //     onText: "启用",
            //     offText: "停用",
            //     size: "small",
            //     onSwitchChange: function() {
            //         changeStatus($(this).data("id"), $(this).is(":checked"));
            //     }
            // });
        // var a = $.selectAll();
        // a.reset();
    }
    // 渲染无数据
    function renderEmpty() {
        $(".js-list").html(
            template("common/record-empty", {
                colspan: 8
            })
        );
    }
    // 获取列表
    function getList() {
        params.pageNo = currPage;
        $.getData({
                url: "/roomResource/searchRoom",
                query: params
            },
            function(data) {
                $.renderPage({
                    count: data.count,
                    curr: currPage,
                    jump: function(num) {
                        currPage = num;
                        getList();
                    }
                });
                if (data.list && data.list.length) {
                    for (var i in data.list) {
                        data.list[i].roomImg = data.list[i].rrRoomPicMain ?
                            data.list[i].rrRoomPicMain.split(" | ")[0] :
                            "";
                    }
                    renderList(data.list);
                } else {
                    renderEmpty();
                }
            }
        );
    }
    // 状态开关
    function changeStatus(id, isOn) {
        $.getData({
                url: "/roomResource/modifyRoomStatusById",
                body: {
                    rrId: id,
                    rrRoomState: isOn ? 1 : 0
                }
            },
            function(data) {
                if (data) {
                    $.toast("操作成功",{
                        type: 'success'
                    });
                    getList();
                }
            }
        );
    }
    // 删除
    function delItem(el, id) {
        $.getData({
                url: "/roomResource/modifyRoomStatusById",
                body: {
                    rrId: id,
                    isDelete: 1
                }
            },
            function(data) {
                // $.msg("操作成功");
                $.toast("操作成功",{
                    type: 'success'
                });
                el
                    .parent()
                    .parent()
                    .remove();
                if (currPage !== 1 && !$(".js-list tr").length) {
                    currPage -= 1;
                }
                getList();
            }
        );
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
            function(data) {
                console.log(data);
                if (data) {
                    // $.msg("操作成功");
                    $.toast("操作成功",{
                        type: 'success'
                    });
                    getList();
                }
            }
        );
    }

    //初始化
    function init() {
        getList();
        // 点击搜索
        $(document).on("click", ".search-btn", function() {
            currPage = 1;
            params.keyWord = $.trim($("#name").val());
            params.rrRoomState = $("#meeting-status").val();
            getList();
        });
        // 跳转新建会议室页面
        $(document).on("click", ".add-meeting-room", function() {
            window.location.href = "./room-add.html";
        });
        // 删除
        $(document).on("click", ".js-del", function() {
            var $this = $(this);
            $.bubble({
                el: $(this),
                msg: '您确定删除当前会议室？',
                ok: function () {
                        delItem($this, $this.attr("data-id"));
                },
                cancel: function () {
                    $.toast('您已取消');
                }
            });
            // $.confirm("确定删除当前会议室？", function() {
            //     delItem($this, $this.attr("data-id"));
            // });
        });

        //修改设备状态
        $(document).on("click", ".device-txt", function() {
            var that = $(this);
            updateDeviceStatus(that);
        });
        // 批量删除
        // $(document).on('click', '.js-del-multi', function () {
        //     if ($(this).hasClass('disabled')) {
        //         return;
        //     }
        //     $.confirm('确定批量删除会议室？', function () {

        //     });
        // });
    }
    init();
});
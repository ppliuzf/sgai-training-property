$(function () {
    var devicesId = [],
        devicesName = [],
        devicesList = [],
        devicesStatus = [],
        rtId = '',
        ItemId = [],
        PopId = [];
    var id = $.getQueryString('id');

    function collectImage() {
        var rs = [];
        $(".upload-item").each(function () {
            rs.push($(this).data("url"));
        });
        rs = rs.join(" | ");
        return rs;
    }

    function collectData() {
        var param = {},
            devicesList = [];
        (dId = []), (dName = []),
        $(".inner span").each(function () {
            var deviceObj = {};
            dId.push($(this).data("id"));
            dName.push($(this).text());
            deviceObj["rdId"] = $(this).data("id");
            deviceObj["rdRoomDevice"] = $(this).text();
            deviceObj["rdsState"] = $(this).data("status");
            devicesList.push(deviceObj);
        });
        param.deviceDetailParamList = devicesList;
        param.rrRoomName = $.trim($("#room-name").val());
        param.rrRoomPosition = $("#room-location").val(); // 位置名称
        param.rpId = $("#room-location").data("parid"); // 位置id
        param.rrRoomType = $("#room-type").find("option:selected").val(); // 类型名称
        param.rtId = $("#room-type").find("option:selected").data("id"); // 类型id
        param.rrRoomPeoples = $("#num").val();
        param.rrRoomDesc = $("#room-text").val();
        param.rrRoomPicMain = collectImage();
        param.rrAdminName = $("#instant-selected").val() ?
            JSON.parse($("#instant-selected").val()).title :
            "";
        param.rrAdminId = $("#instant-selected").val() ?
            JSON.parse($("#instant-selected").val()).id :
            "";
        param.rrAdminType = 0;
        param.rrId = id || "";
        if (param.rrRoomName == "") {
            $.alert('请输入会议室名称');
            return false;
        }
        if (param.rrRoomPosition == "") {
            // $.msg("请选择会议室位置");
            $.alert('请选择会议室位置');
            return false;
        }
        if (param.rrRoomType == "") {
            $.alert('请选择会议室类型');
            // $.msg("请选择会议室类型");
            return false;
        }
        if (param.rrRoomPeoples == "") {
            $.alert('请输入可容纳人数');
            // $.msg("");
            return false;
        }
        if (dName.length > 10) {
            // $.msg("");
            $.alert('选择设备不能大于10个');
            return false;
        }
        if (param.rrAdminName == "") {
            $.alert('请选择会议室管理员');
            // $.msg("请选择会议室管理员");
            return false;
        }
        return param;
    }
    //渲染类型
    function renderType(data) {
        if (id) {
            var item = {};
            item.data = data;
            item.rtId = rtId;
            // console.log(item)
            $(".js-room-type").html(
                template("plan/select-op", {
                    items: item
                })
            );
        } else {
            // console.log(data)
            $(".js-room-type").html(
                template("common/select-op", {
                    items: data
                })
            );
        }

    }
    // 获取设备列表localeCompare
    function getDeviceList() {
        ItemId = [];
        $.getData({
                url: "/roomResource/roomDeviceList"
            },
            function (data) {
                // console.log(data);
                for (let i in data) {
                    PopId.push(data[i].rdRoomDevice)
                }
                console.log(data)
                // if (data && data.length) {
                if (data) {
                    $.pop({
                        title: "设备",
                        noIcon: true,
                        content: template("plan/device-list", {
                            items: data,
                            // ids: devicesId.join(",")
                        }),
                        yes: function () {
                            var data = collectPopDevice();
                            setTimeout(function () {
                                if (data) {
                                    savePopDevice(data);
                                } else {
                                    createDevice();
                                }
                            }, 300);
                        }
                    });
                }
                $(".js-room-device span").each(function () {  
                    ItemId.push($.trim($(this).text()));
                })
                $(".check-device").each(function () {  
                    let text = $.trim($(this).parent().text());
                    for (let i = 0; i < ItemId.length; i++) {
                        let x = ItemId[i].localeCompare(text);
                        // console.log(x)
                        if(x == 0){
                            $(this).attr("checked", "checked")
                        }
                    }
                })
            }
        );
    }
    // 状态开关
    function changeStatus(isOn, obj) {
        var status = isOn ? 0 : 1;
        obj
            .parents(".form-inline")
            .children()
            .attr("data-status", status);
    }
    // 收集弹窗设备选中值
    function collectPopDevice() {
        devicesId = [];
        devicesName = [];
        devicesStatus = [];
        // devicesList = [];
        $(".check-device").each(function () {
            if ($(this).is(":checked")) {
                var thObj = $(this).parent();
                devicesId.push(thObj.data("id"));
                devicesName.push(thObj.data("name"));
                devicesStatus.push(thObj.data("status"));
            }
        });
        // console.log(devicesStatus);
        var rs = [];
        $(".js-device-user input").each(function () {
            if ($.trim($(this).val()) !== "") {
                rs.push($.trim($(this).val()));
            }
        });
        return rs.join("@@");
    }
    // 保存弹窗设备新增值
    function savePopDevice(data) {
        $.getData({
                url: "/roomResource/saveRoomDevice",
                query: {
                    roomDeviceDetails: data
                }
            },
            function (data) {
                if (data) {
                    // console.log(data);
                    for (var i = 0; i < data.length; i++) {
                        devicesId.push(data[i].rdId);
                        devicesName.push(data[i].rdRoomDevice);
                        devicesStatus.push(0);
                    }
                    createDevice();
                } else {
                    // $.msg("新增设备失败");
                    $.toast("新增设备失败", {
                        type: 'error'
                    })
                }
            }
        );
    }
    // 创建设备显示
    function createDevice() {
        var out = '<div class="inner">';
        for (var i = 0; i < devicesId.length; i++) {
            out +=
                '<span data-id="' +
                devicesId[i] +
                '" data-status="' +
                devicesStatus[i] +
                '">' +
                devicesName[i] +
                '<i class="del"></i></span>';
        }
        out = out + "</div>";
        $(".js-room-device .multi-selector").html(out);
    }
    // 获取类型
    function getType() {
        $.getData({
                url: "/roomResource/getRoomTypeList",
                query: {
                    pageNo: 1,
                    pageSize: 1000,
                }
            },
            function (data) {
                // console.log(data);
                if (data && data.list) {
                    renderType(data.list);
                }
            }
        );
    }
    // 获取位置//
    function getLocation() {
        $.getData({
                url: "/meetings/spaceTree"
            },
            function (data) {
                // console.log(data)
                turnDate(data) //渲染树
            },
            function () {
                $.alert("会议室位置获取失败")
            }
        );
    }
    //渲染树

    function turnDate(data) {
        var rows = data,
            arr = new Array();
        for (var i = 0; i < rows.length; i++) {
            var str = {
                "id": "" + rows[i].id + "",
                "parentId": "" + rows[i].pId + "",
                "checked": false,
                "name": "" + rows[i].name + "",
                "children": ""
            };
            arr.push(str);
        }
        // console.log(arr)
        $('#tt').tree({
            data: arr,
            cascadeCheck: false,
            checkbox: true,
            animate: true,
            idField: 'passId',
            loadFilter: function (rows) { //转换数据格式
                return convert(rows);
            },
            onSelect: function (node) {
                let cknodes = $('#tt').tree("getChecked"),
                    selTree = "",
                    parId;
                for (var i = 0; i < cknodes.length; i++) {
                    if (cknodes[i].id != node.id) {
                        $('#tt').tree("uncheck", cknodes[i].target);
                    }
                }
                if (node.checked) {
                    $('#tt').tree('uncheck', node.target);
                    selTree = "";
                } else {
                    selTree = getFathers();
                    // console.log(selTree)
                    $('#tt').tree('check', node.target);
                }
                parId = node.id;
                $(".js-room-location").val(selTree);
                $(".js-room-location").attr("data-parid", parId);
                $("#tt").hide();
            },
            onLoadSuccess: function (node, data) {
                $(this).find('span.tree-checkbox').unbind().click(function () {
                    $('#tt').tree('select', $(this).parent());
                    return false;
                });
            }
        });
        $('#tt').hide();
    }

    function getFathers() { //找所有上级父节点
        let node = $('#tt').tree('getSelected'),
            parent = $('#tt').tree('getParent', node.target),
            str = [],
            sty;
        str.unshift(node.text);
        if (parent != null) {
            str.unshift(parent.text);
            while (1) {
                parent = $('#tt').tree('getParent', parent.target);
                if (parent) {
                    str.unshift(parent.text)
                } else break;
            }
            sty = str.join("-");
        } else {
            sty = str.join("")
        }

        return sty;
    }
    //转换数据格式
    function convert(rows) {
        function exists(rows, parentId) {
            for (var i = 0; i < rows.length; i++) {
                if (rows[i].id == parentId) return true;
            }
            return false;
        }
        var nodes = [];
        // get the top level nodes
        for (var i = 0; i < rows.length; i++) {
            var row = rows[i];
            if (!exists(rows, row.parentId)) {
                nodes.push({
                    id: row.id,
                    text: row.name,
                    url: row.url,
                    parId: row.parentId,
                    hierarchy: 1
                    //checked:row.checked
                });
            }
        }
        // console.log(nodes)
        var toDo = [];
        for (var i = 0; i < nodes.length; i++) {
            toDo.push(nodes[i]);
        }
        while (toDo.length) {
            var node = toDo.shift(); // the parent node
            // get the children nodes
            for (var i = 0; i < rows.length; i++) {
                var row = rows[i];
                if (row.parentId == node.id) {
                    var child = {
                        id: row.id,
                        text: row.name,
                        url: row.url,
                        checked: row.checked,
                        parId: row.parentId
                    };
                    if (node.children) {
                        node.children.push(child);
                    } else {
                        node.children = [child];
                    }
                    toDo.push(child);
                }
            }
        }
        // console.log(nodes);
        return nodes;
    }

    // 保存数据
    function saveData(data) {
        $.getData({
                url: "/roomResource/saveRoom",
                body: data
            },
            function (data) {
                $.toast("操作成功", {
                    type: 'success'
                }, function () {
                    history.back();
                });
            }
        );
    }
    // 删除已有设备
    function removeDevice(id) {
        $.getData({
                url: "/roomResource/deleteRoomDeviceById",
                query: {
                    rdId: id
                }
            },
            function (data) {
                if (data) {
                    $.toast("操作成功", {
                        type: 'success'
                    })
                    $(".pop-device-list [data-id=" + id + "]").remove();
                }
            }
        );
    }

    //渲染列表
    function renderResult(obj, temp, data) {
        $(obj).html(template(temp, data));
    };

    //获取会议详情roomResource/getRoomDetailById
    function ObtainMting() {
        $.getData({
            url: '/roomResource/getRoomDetailById',
            query: {
                id: id
            }
        }, function (data) {
            // for (let i in data.deviceDetailVoList) {
            //     // rdsId
            //     ItemId.push(data.deviceDetailVoList[i].rdRoomDevice)
            // }
            rtId = data.rtId || "";
            if (data) {
                $('.js-editor').html(template('plan/editor', data));
                $('#instant-keywords').val(data.rrAdminName);

                $.instantSearch({
                    default: {
                        approvalEmpName: data.rrAdminName
                    },
                    // searchInput:"#instant-keyword",
                    url: "/meetings/searchEmpInfoByName"
                });
                $("#instant-selected").val(JSON.stringify({
                    id: rtId || "",
                    title: data.rrAdminName
                }));
                $.counter({
                    el: "#room-text"
                });
                $.uploader({
                    el: "#js-uploader",
                    maxLength: 1,
                    default: [data.rrRoomPicMain]
                });
            }

        });
    }
    //弹出设备默认选款
    function DefaultaItem() {

    }
    //初始化
    function init() {
        if (id) {
            ObtainMting();
        }
        getLocation();
        getType();
        $.uploader({
            maxLength: 1,
        });
        if (!id) {
            $.counter({
                el: "#room-text"
            });
            $.instantSearch({
                url: "/meetings/searchEmpInfoByName"
            });
        }
        //最大人数lengthinp
        $(document).on("keyup", ".lengthinp", function () {
            let lengthinp = $(".lengthinp").val();
            console.log(lengthinp.length);
            if (lengthinp.length >= 10) {
                $.alert("已超最多人数，请重新输入！")
                return;
            }
        });
        // 弹出设备选择框
        $(document).on("click", ".js-room-device", function () {
            getDeviceList();
        });
        //选取位置
        $(document).on("click", ".js-room-location", function () {
            $('#tt').show();
            let id = $(this).data('parid');

        })
        // 已选设备删除
        $(document).on("click", ".multi-selector .del", function () {
            $(this)
                .parent()
                .remove();
            devicesId.splice(devicesId.indexOf($(this).data("id")), 1);
            devicesName.splice(devicesId.indexOf($(this).text("")), 1);
            devicesStatus.splice(devicesId.indexOf($(this).data("status")), 1);
            return false;
        });
        $(document).on("click", ".multi-selector span", function () {
            return false;
        });
        // 取消
        $(document).on("click", ".js-cancel", function () {
            window.history.back();
        });
        // 保存
        $(document).on("click", ".js-save", function () {
            var data = collectData();
            if (data) {
                saveData(data);
            }
        });
        // 设备已有删除
        $(document).on("click", ".pop-device-list .js-device-remove", function () {
            removeDevice(
                $(this)
                .parents(".form-inline")
                .data("id")
            );
        });
        // 设备自定义删除
        $(document).on("click", ".pop-device-custom .js-device-remove", function () {
            $(this)
                .parent()
                .parent()
                .remove();
        });
        // 设备自定义添加
        $(document).on("click", ".js-device-add", function () {
            $(".js-device-user")
                .append(template("plan/device-custom"))
                .find("input:last")
                .focus();
        });
    }
    init();
});
$(function () {
    var roomParams = {};
    var times = $.initTimeList();
    var MaterielData, selectMateriel;
    // 重组物料选择模板数据
    function setMaterielParams() {
        for (var i in MaterielData) {
            MaterielData[i].isSelect = false;
            for (var j in selectMateriel) {
                if (MaterielData[i].maName == selectMateriel[j].maName) {
                    MaterielData[i].isSelect = true;
                    MaterielData[i].maCount = selectMateriel[j].maCount;
                }
            }
        }
        return MaterielData;
    }
    // 时间转换为数字 请求接口需要
    function collectTime(tParams) {
        var timeNumS, timeNumE, timeStr;
        var len;
        for (var i in times) {
            if (tParams[0] == times[i].startTime) {
                timeNumS = i;
            }
            if (tParams[1] == times[i].endTime) {
                timeNumE = i;
            }
        }
        len = timeNumE - timeNumS;
        if (len <= 1) {
            return timeNumS + "," + timeNumE;
        }
        timeNumS = parseInt(timeNumS);
        timeNumE = parseInt(timeNumE);
        timeStr = timeNumS;
        for (var j = timeNumS; j < timeNumE; j++) {
            timeStr = timeStr + "," + (j + 1);
        }
        return timeStr;
    }
    // 收集物料选择模板中已选物料
    function collectSelectMa() {
        var materielArr = [];
        $(".js-materiel-list input").each(function () {
            var $this = $(this);
            if ($this.is(":checked")) {
                materielArr.push({
                    maId: $this.attr("data-id"),
                    maCount: $this.attr("data-count"),
                    maName: $this.attr("data-name"),
                    maTypeName: $this.attr("data-type"),
                    maTypeId: $this.attr("data-typeId"),
                    maCode: $this.attr("data-code")
                });
            }
        });
        // materielArr = collectMaterielVos().concat(materielArr);
        return materielArr;
    }
    // 收集物料列表
    function collectMateriel() {
        $.getData({
            url: "/roomResource/getMaterielList"
        },
            function (data) {
                console.log(data)
                MaterielData = data;
            }
        );
    }
    // 收集会议室详情
    function collectRoom() {
        $.getData({
            url: "/roomResource/getRoomDetailById",
            query: { id: $.getQueryString("roomid") }
        },
            function (data) {
                console.log(data);
                roomParams = data;
                roomParams.meetingTime = $.formatTime(
                    $.getQueryString("date") * 1,
                    "yyyy-MM-dd"
                );
                roomParams.time = $.getQueryString("time").replace(",", "-");
                renderDetail(roomParams);
            }
        );
    }
    // 收集已选物料数据
    function collectMaterielVos() {
        var materielArr = [];
        console.log($(".materiel-item"));
        $(".materiel-item").each(function () {
            var $this = $(this);
            materielArr.push({
                maCount: $this.data("num"), // 物料数
                maName: $this.data("name"), // 物料名
                maId: $this.data("id"), // 物料id
                maTypeId: $this.data("typeId"), // 物料类型id
                maTypeName: $this.data("type"), // 物料类型名称
                maCode: $this.data("code")
            });
        });
        return materielArr;
    }
    // 收集渲染已选物料数据过滤
    function collectRenderMa() {
        var tableMa = collectMaterielVos() ? collectMaterielVos() : [];
        var modelMa = collectSelectMa();
        var arr2 = [];
        var arr1 = collectMaterielVos() ? collectMaterielVos() : [];
        if (tableMa.length <= 0) {
            arr2 = arr2.concat(modelMa);
        } else {
            for (var i in tableMa) {
                arr1[i].flag = 0;
                for (var j in modelMa) {
                    if (tableMa[i].maName == modelMa[j].maName) {
                        arr1[i].flag = 1;
                        modelMa.splice(j, 1);
                    }
                }
            }
            tableMa = [];
            for (var k in arr1) {
                if (arr1[k].flag == 1) {
                    tableMa.push(arr1[k]);
                }
            }
            arr2 = tableMa.concat(modelMa);
        }
        return arr2;
    }
    // 收集参会人、参会部门数据
    function collectInvite() {
        var str = $(".js--all").val();
        var arr;
        if (!str) {
            arr = [];
        } else {
            arr = JSON.parse(str);
        }
        var inviteObj = {
            inviters: [],
            inviteDeptVos: []
        };
        for (var i in arr) {
            var obj = {};
            if (!arr[i].isDept) {
                obj.inviterEiId = arr[i].id;
                obj.inviterEiName = arr[i].title;
                obj.isCompere = "";
                inviteObj.inviters.push(obj);
            } else {
                obj.inviterDeptId = arr[i].id; // 参会部门id
                obj.inviterDeptName = arr[i].title; // 参会部门名称
                inviteObj.inviteDeptVos.push(obj);
            }
        }
        return inviteObj;
    }
    // 编辑时获取会议详情
    function getMainData() {
        $.getData({
            url: "/meetings/getMeetingDetail",
            query: { id: $.getQueryString("id") }
        },
            function (data) {
                renderMain(data);
                if (data.materielDtoList.length) {
                    selectMateriel = data.materielDtoList;
                    $(".wuliao .form-inline")
                        .addClass("show")
                        .removeClass("hidden");
                    renderWuLiao(data.materielDtoList);
                } else {
                    $(".wuliao .form-inline")
                        .addClass("hidden")
                        .removeClass("show");
                }
            }
        );
    }
    // 收集发布会议数据
    function getData() {
        var param = {};
        var invite = collectInvite();
        var materielVos = collectMaterielVos();
        var timeA = $.getQueryString("time").split(",");
        param.id = $.getQueryString("id") || ""; //主键
        param.rrId = $.getQueryString("roomid"); //会议室id
        if (parseInt(timeA[0]) > parseInt(timeA[1])) {
            // $.msg("会议结束时间不能小于开始时间", 2000);
            $.toast("会议结束时间不能小于开始时间",{
                type: 'error'
            });
            return false;
        }
        param.miMtTimeSeg = collectTime(timeA); //会议时间段
        param.miMtDate = $.getQueryString("date"); //会议日期
        param.miMtSubject = $(".meeting-title input").val();
        param.miMtContent = $(".meeting-text textarea").val(); // 会议简要
        param.miIsRemind = $(".warn-btn").is(":checked") ? 1 : 0; //是否开启提醒
        param.inviteDeptVos = invite.inviteDeptVos; // 参会部门
        param.inviters = invite.inviters; // 参会人
        param.materielVos = materielVos; //物料
        if (!param.miMtSubject || param.miMtSubject == "") {
            // $.msg("会议主题不能为空", 2000);
            $.toast("会议主题不能为空",{
                type: 'error'
            });
            return false;
        }
        if (!param.inviteDeptVos.length && !param.inviters.length) {
            // $.msg("参会人不能为空", 2000);
            $.toast("参会人不能为空",{
                type: 'error'
            });
            return false;
        }
        if (param.miIsRemind) {
            param.miRemindMin = $(".warn select").val();
        }
        return param;
    }
    // 渲染物料选择模板
    function renderMaterielList() {
        MaterielData = setMaterielParams();
        if (MaterielData.length) {
            $.pop({
                noIcon: true,
                title: "物料选择",
                content: template("plan/materielList", { items: MaterielData }),
                yes: function () {
                    var selectedMaArr = collectRenderMa();
                    // var selectedMaArr = collectSelectMa();
                    selectMateriel = selectedMaArr;
                    if (selectedMaArr.length) {
                        $(".wuliao .form-inline")
                            .addClass("show")
                            .removeClass("hidden");
                        renderWuLiao(selectedMaArr);
                    } else {
                        $(".wuliao .form-inline")
                            .addClass("show")
                            .removeClass("hidden");
                    }
                }
            });
        }
    }
    // 渲染已选物料
    function renderWuLiao(param) {
        $(".js-wuliao-list").html(template("plan/wuliao", { items: param }));
    }
    // 渲染会议室信息
    function renderDetail(param) {
        $(".js-meeting-main").html(template("plan/add-meeting-main", param));
    }
    // 渲染编辑页面
    function renderMain(data) {
        data = data ? data : {};
        $("#meeting-title").val(data.miMtSubject);
        $("#meeting-text").val(data.miMtContent);
        var inviters = [];
        for (var i in data.inviters) {
            inviters.push({
                title: data.inviters[i].inviterEiName,
                id: data.inviters[i].inviterEiId,
                isDept: data.inviters[i].isInvite
            });
        }
        if (data.miIsRemind) {
            $(".checkbox input").attr("checked", true);
            $("#warn-time").val(data.miRemindMin || 5);
        }
        $.dept({
            el: ".add-pop",
            deptUrl: "/orgTree/getSgaiOrgTreeById",
            searchUrl: "/meetings/searchSgaiOrgTree",
            default: inviters || [],
        });
    }
    // 发布会议
    function saveData(data) {
        $.getData({
            url: "/meetings/saveMeetingPC",
            body: data
        },
            function (data) {
                // $.msg("保存成功", 2000);
                $.toast("保存成功",{
                    type: 'success'
                });
                setTimeout(function () {
                    window.location.replace("./order-list.html");
                }, 2000);
            },
            function (data) {  
                $.alert(data, function () {  
                    history.go(-1)
                })
                console.log(data)
            }
        );
    }
    // 初始化数据
    function init() {
        if ($.getQueryString("id")) {
            getMainData();
        } else {
            $.dept({
                el: ".add-pop",
                deptUrl: "/orgTree/getSgaiOrgTreeById",
                searchUrl: "/meetings/searchSgaiOrgTree",
                type:"emp"
            });
        }
        collectRoom();
        collectMateriel();
        // 取消发布会议
        $(document).on("click", ".js-cancel", function () {
            if($.getQueryString("id")){
                window.location.replace("./order-list.html");
            }else {
                window.location.replace("./order.html");
            }
            
        });
        // 跳转添加会议室页面
        $(document).on("click", ".js-add-room", function () {
            window.location.href = "./room-add.html";
        });
        // 保存会议
        $(document).on("click", ".js-save", function () {
            var data = getData();
            if (data) {
                saveData(data);
            }
        });

        // 选择物料
        $(document).on("click", ".js-select-materiel", function () {
            console.log("弹框GG");
            selectMateriel = collectMaterielVos();
            renderMaterielList();
        });
        // 删除物料
        $(document).on("click", ".js-del", function () {
            var $this = $(this);
            $.pop({
                content: "确认删除当前物料？",
                yes: function () {
                    $this.parents(".materiel-item").remove();
                    selectMateriel = collectMaterielVos();
                    if ($(".materiel-item").length <= 0) {
                        $(".form-inline")
                            .removeClass("show")
                            .addClass("hidden");
                    }
                }
            });
        });
        // 输入物料数量时校验
        // $(document).on('input', '.materiel-item input', function () {
        //    var $this = $(this);
        //    var curV = $this.val();
        //    var haveNum = $this.parents('materiel-item').data('num');
        //    if (!/^[0-9]+$/.test(curV)) {
        //        $this.val(haveNum);
        //        return false;
        //    }
        //    if (curV > haveNum) {
        //        $.msg('物料数不能超过' + haveNum, 2000);
        //        $this.val(haveNum);
        //    }
        // });
    }
    init();
});
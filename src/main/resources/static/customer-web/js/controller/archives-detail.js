$(function () {
    var id = $.getQueryString("id"),
        cardList = [],
        eventList = [],
        carList = [],
        currPage = 1,
        detailData = {},
        eventDepartment = {},
        arrStates2 = ["待核实", "待指派", "待处理", "已完成", "已终止"],
        arrStates1 = ["待指派", "待处理", "待回访", "已完成", "已终止"],
        complaintNode = ["ZP", "CL", "HF"],
        maintainNode = ["HS", "ZP", "CL"],
        configs = {};

    if (!id) {
        $.alert("记录不存在", function () {
            history.back();
        });
        return false;
    }
    // 获取详情
    function getDetail() {
        $.getData({
            url: "/customer/getPersonalRecordInfoById",
            query: {
                prId: id
            }
        },
            function (data) {
                if (data) {
                    detailData = data;
                    getCarList(1, true, detailData);
                    getEventList();
                    // getEventList(detailData);
                    renderDetail(data);
                    renderCards(data.customCardInfoVos);//渲染证件
                    // renderEvents(data.customCardInfoVos);
                    // renderCars(data.customCardInfoVos);
                }
            }
        );
    }
    // 渲染证件
    function renderCards(data) {
        $(".js-card-add")[data.length >= 5 ? "addClass" : "removeClass"]("hide");
        if (data.length) {
            $(".js-cards").html(
                template("archives-detail-cards", {
                    items: data
                })
            );
        } else {
            $(".js-cards").html(
                template("common/record-empty", {
                    colspan: 4
                })
            );
        }
    }
    // 渲染详情
    function renderDetail(data) {
        $(".js-detail").html(template("archives-detail", data));
    }
    // 渲染空
    function renderEmpty(el, colspan) {
        $(el).html(
            template("common/record-empty", {
                colspan: colspan
            })
        );
    }
    // 获取证件列表
    function getCardList() {
        $.getData({
            url: "/customer/certificateList",
            query: {
                type: 1
            }
        },
            function (data) {
                if (data && data.length) {
                    for (var i in data) {
                        cardList.push({
                            value: data[i].ccnId,
                            title: data[i].ccCertificateName
                        });
                    }
                }
            }
        );
    }
    // 删除证件
    function delCard(el, ids) {
        $.getData({
            url: "/customer/updateCardInfo",
            body: {
                id: ids,
                ccIsDelete: 1
            }
        },
            function (data) {
                if (data) {
                    // $.msg("操作成功", getDetail);
                    $.toast('操作成功', {
                        type: 'success',
                    }, getDetail);
                }
            }
        );
    }
    // 编辑证件
    function editCard(el, ids, value, title, number) {
        $.getData({
            url: "/customer/updateCardInfo",
            body: {
                id: ids,
                ccCertificateNo: number,
                ccnId: value,
                ccCertificateName: title
            }
        },
            function (data) {
                if (data) {
                    // $.msg("操作成功", getDetail);
                    $.toast('操作成功', {
                        type: 'success',
                    }, getDetail);
                }
            }
        );
    }
    // 新增证件
    function addCard() {
        if ($.trim($("#card-number").val()) === "") {
            return;
        }
        $.getData({
            url: "/customer/saveCardInfo",
            body: {
                ccCertificateNo: $.trim($("#card-number").val()),
                ccCertificateName: $("#card-type option:selected").text(),
                ccnId: $("#card-type").val(),
                prId: id
            }
        },
            function (data) {
                if (data) {
                    // $.msg("操作成功", getDetail);
                    $.toast('操作成功', {
                        type: 'success',
                    }, getDetail);
                }
            }
        );
    }
    //循环替换事件类型
    function replaceType(data) {
        data.forEach(function (item) {
            switch (item.eventType) {
                case "XL":
                    item.eventType = "修理事件";
                    break;
                case "YJ":
                    item.eventType = "应急事件";
                    break;
                case "AB":
                    item.eventType = "安保事件";
                    break;
                case "TS":
                    item.eventType = "投诉事件";
                    break;
                default:
                    break;
            }
        });
    }
    // 渲染工单
    function renderEvents(data) {
        // $('.js-car-add')[data.length >= 5 ? 'addClass' : 'removeClass']('hide');
        if (data.length) {
            $(".js-events").html(
                template("archives-detail-events", {
                    items: data
                })
            );
        } else {
            $(".js-events").html(
                template("common/record-empty", {
                    colspan: 10
                })
            );
        }
    }
    // 获取车辆列表
    function getCarList(pageNum, isFirst, detailData) {
        let data = {};
        data.ciOwnerName = detailData.prName;
        data.ciOwnerPhone1 = detailData.prPhoneFirst;
        if (detailData.prPhoneSecond) {
            data.ciOwnerPhone2 = detailData.prPhoneSecond;
        }
        $.getData({
            url: "/customer/getCarInfoList",
            query: {
                pageNum: pageNum,
                pageSize: 3
            },
            body: data
        },
            function (data) {
                total = data.count;
                if (data.list && data.list.length) {
                    renderCars(data.list);
                    isFirst &&
                        $.renderPage({
                            count: total,
                            limit: 3,
                            curr: currPage,
                            jump: function (n) {
                                currPage = n;
                                getCarList(n, false, detailData);
                            }
                        });
                } else {
                    renderEmpty(".js-cars", 10);
                    $(".pages").hide();
                }
            }
        );
    }
    // 渲染车辆
    function renderCars(data) {
        // $('.js-car-add')[data.length >= 5 ? 'addClass' : 'removeClass']('hide');
        if (data.length) {
            $(".js-cars").html(
                template("archives-detail-cars", {
                    items: data
                })
            );
        } else {
            $(".js-cars").html(
                template("common/record-empty", {
                    colspan: 4
                })
            );
        }
    }
    // 删除车辆
    function delCar(el, ids) {
        $.getData({
            url: "/customer/deleteCarInfoById",
            query: {
                id: ids
            }
        },
            function (data) {
                if (data) {
                    // $.msg("操作成功", getCarList(currPage, true, detailData));
                    $.toast('操作成功', {
                        type: 'success',
                    }, getCarList(currPage, true, detailData));
                }
            }
        );
    }

    //设置投诉流程事件

    function setComplaintEvent() {
        var index = $('.order_item.selected').data('index');
        $('.order_item').on('click', function () {
            var item = $(this).data('flow');
            $('.flow-' + item).show().siblings().hide();
        })
        $('#birth').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            minView: 'year',
            endDate: new Date(),
            autoclose: true
        });
        $('#assign_person').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            minView: 'year',
            endDate: new Date(),
            autoclose: true
        });
    }

    //获取事件列表

    function getEventList() {
        $.getData({
            url: '/event/getEventLists',
            query: {
                personId: id
            }
        }, function (data) {
            if (data) {
                renderEvents(data)
            } else {
                // renderEmpty(".js-events", 10);
                $(".js-events").html(
                    template("common/record-empty", {
                        colspan: 10
                    })
                );
            }

        })
    }
    //客户事件获取投诉类型
    function getCardType() {
        var code_Type = $('.js-event-type option:selected').data('codetype');
            if(code_Type == 'TsCategory'){
                // $("#category").text("投诉类别");//类型
                $("#theName").text("投诉人");//人
                $("#theAddress").text("投诉地址");//地址
                $("#theContent").text("投诉内容");//内容
            }else if(code_Type == 'WxCategory'){
                // $("#category").text("事件类别");//类型
                $("#theName").text("报修人");//人
                $("#theAddress").text("报修地址");//地址
                $("#theContent").text("报修内容");//内容
            }else if(code_Type == 'AbCategory'){
                // $("#category").text("安保类别");//类型
                $("#theName").text("报案人");//人
                $("#theAddress").text("报案地址");//地址
                $("#theContent").text("报案内容");//内容
            }else{
                return
            }

        $.getData({
            url: '/event/getListType',
            query: {
                code_type: code_Type,
            }
        }, function (data) {
            $('.js-card').html(template('archives-event-type', {
                items: data
            }))
        })
    }

    //新增事件

    function addEvent(url, dom) {
        var code_Type = $('.js-event-type option:selected').data('codetype'),
            card_Type = $('.js-card-type option:selected').data('type'),
            prName = $('#firstname').val(),
            prPhone = $('#telephone').val(),
            prAdress = $.trim($('#adress').val()),
            prContent = $.trim($('#content').val()),
            prDesc = $.trim($('#desc').val());
            console.log(prAdress,prContent)
            if (prAdress == "") {
                $.alert("请填写"+$("#theAddress").text())
            }else if (prContent == "") {
                $.alert("请填写"+$("#theContent").text())
            }else {
                $.getData({
                    url: '/event/saveEvent',
                    body: {
                        address: prAdress,
                        complContent: prContent,
                        contactPerson: prName,
                        desc: prDesc,
                        eventType: code_Type,
                        eventClass: card_Type,
                        telephone: prPhone,
                        repairPhoto: url,
                        prId: id
                    }
                }, function (data) {
                    $(dom).modal('hide');
                    $.toast('操作成功', {
                        type: 'success',
                    }, getEventList);
                })
            }

    }
    //时间处理
    function amendTime(data) {
        var setTime = {};
        setTime.date = data.split(" ")[0];
        setTime.time = data.split(" ")[1];
        return setTime;
    }
    //格式化当前时间
    Date.prototype.format = function (fmt) { //author: meizz
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }
    // 查看进度
    function getEventProgress(data) {
        $.getData({
            url: '/event/getEventInfoByCode',
            query: data,
        }, function (res) {
            res.emEventCreateVo.emTime = amendTime(res.emEventCreateVo.emTime);
            if (res.emAssign) {
                res.emAssign.assignDatetime = amendTime(res.emAssign.assignDatetime);
            }
            if (res.emComplaintsReturn) {
                res.emComplaintsReturn.returnTime = amendTime(res.emComplaintsReturn.returnTime)
            }
            if (res.emConfirm) {
                res.emConfirm.confirmDate = amendTime(res.emConfirm.confirmDate)
            }
            if (res.emEnded) {
                res.emEnded.endTime = res.emEnded.endTime ? amendTime(res.emEnded.endTime) : amendTime(new Date().format("yyyy-MM-dd HH:mm:ss"))
            }
            if (res.emProcesses.length > 0) {
                for (let i = 0; i < res.emProcesses.length; i++) {
                    res.emProcesses[i].repairDatetime = res.emProcesses[i].repairDatetime ? amendTime(res.emProcesses[i].repairDatetime) : amendTime(new Date().format("yyyy-MM-dd HH:mm:ss"))
                }
            }
            if (res.emConfirm && res.emProcesses.length > 0 && res.emEnded) {
                res.emProcesses = [];
            }
            if (res.emConfirm && res.emProcesses.length > 0 || res.emComplaintsReturn) {
                res.emEnded = null;
            }

            $.pop({
                noIcon: true,
                title: "查看进度",
                isCancel: false,
                content: template("archives-detail-flow", {
                    items: res
                }),
                size: "md",
                yes: function () { }
            });
        })

    }
    // 事件操作
    function setEventVisit(obj) {
        var eventType = obj.data("type");
        var eventStates = obj.data("states");
        if (eventType == "TS") {
            //指派
            if (eventStates == "0") {
                var type = obj.data("type");
                var step = type == "WX" ? "D" : "C";
                eventDepartment.flowCode = type;
                eventDepartment.stepPos = step;
                $.pop({
                    el: 'designate',
                    ide: "#designate",
                    yesModel: false,
                    noIcon: true,
                    title: "指派",
                    content: template("archives-event-assign", {}),
                    size: "md",
                    yes: function () {
                        setDesignatePerson(obj, "#designate")
                    }
                });
                $.counter({
                    el: '#assign-remark'
                });
                $(document).on("click", "#assign-person", function () {
                    $.alert("请先选择部门");
                })
            } else if (eventStates == "1") { //处理
                $.pop({
                    el: 'designate',
                    ide: "#designate",
                    noIcon: true,
                    title: "处理",
                    sureText: '处理完成',
                    cancel: '保存',
                    cancelModel: false,
                    yesModel: false,
                    content: template("model/archives-detail-processing", {}),
                    size: "md",
                    yes: function () {
                        setDispose(obj, "#designate")
                    },
                    cancels: function () {
                        setDisposeSave(obj, "#designate");
                    }
                });
                calendar("#js-h-processingTime", "startDate");
                $.counter({
                    el: '#js-h-processing'
                });
            } else if (eventStates == "2") { //回访
                var progressCode = obj.data('code')+'';
                var endTime = '';
                $.getData({
                    url: '/event/getEventInfoByCodes',
                    query: {
                        code: progressCode,
                        codeType: "TsCategory"
                    },
                }, function (data) {
                    returnModel(obj, data.repairDatetime)
                })
            }
        } else {
            if (eventStates == "0") { //核实
                $.getData({
                    url: '/event/getLoginUserInfo'
                }, function (data) {
                    $.pop({
                        el: 'designate',
                        ide: "#designate",
                        yesModel: false,
                        noIcon: true,
                        title: "核实",
                        content: template("model/archives-detail-verify", {
                            item: data
                        }),
                        size: "md",
                        yes: function () {
                            setVerify(obj, "#designate")
                        }
                    });
                    calendar1("#js-h-processingTime");
                    $.counter({
                        el: '#js-h-verify'
                    });
                })

            } else if (eventStates == "1") { //指派
                var type = obj.data("type");
                var step = type == "WX" ? "D" : "C";
                eventDepartment.flowCode = type;
                eventDepartment.stepPos = step;
                $.pop({
                    el: 'designate',
                    ide: "#designate",
                    yesModel: false,
                    noIcon: true,
                    title: "指派",
                    content: template("archives-event-assign", {}),
                    size: "md",
                    yes: function () {
                        setDesignatePerson(obj, "#designate")
                    }
                });
                $.counter({
                    el: '#assign-remark'
                });
            } else if (eventStates == "2") { //处理
                $.pop({
                    el: 'designate',
                    ide: "#designate",
                    cancelModel: false,
                    yesModel: false,
                    noIcon: true,
                    title: "处理",
                    sureText: '处理完成',
                    cancel: '保存',
                    content: template("model/archives-detail-processing", {}),
                    size: "md",
                    yes: function () {
                        setDispose(obj, "#designate")
                    },
                    cancels: function () {
                        setDisposeSave(obj, "#designate");
                    }
                });
                calendar("#js-h-processingTime", "startDate");
                $.counter({
                    el: '#js-h-processing'
                });
            }
        }
    }
    //回访弹窗
    function returnModel(obj, data) {
        $.pop({
            el: 'designate',
            ide: "#designate",
            yesModel: false,
            noIcon: true,
            title: "回访",
            content: template("model/archives-detail-return", {}),
            size: "md",
            yes: function () {
                setReturn(obj, "#designate")
            }
        });
        let times = null;
        if(new Date(data) < new Date()) {
            times = {
                format: "yyyy-mm-dd hh:ii:ss",
                language: "zh-CN",
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 0,
                forceParse: 0,
                startDate: new Date(data),
                endDate: new Date()
            }
        }else {
            times = {
                format: "yyyy-mm-dd hh:ii:ss",
                language: "zh-CN",
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 1,
                forceParse: 0,
                startDate: new Date(data),
                endDate: new Date(data)
            }
        }
        console.log(times)
        $("#js-h-processingTime").datetimepicker(times);
        $.counter({
            el: '#js-h-return'
        });
    }
    //获取部门
    function getReach(value) {
        $.getData({
            url: '/customer/getSgaiDept'
        }, function (data) {
            turnDate(data, value) //渲染树
        },function () {
            $.alert("部门获取失败")
        })
    }
    //获取指派人
    function getDesignatedPerson(res) {
        eventDepartment.deptCode = res;
        $.getData({
            url: '/event/findNextNodeUserList',
            query: eventDepartment
        }, function (data) {
            console.log(data)
            if (data.length == 0) {
                data.push({
                    userCode:"0",
                    userName:"未找到结果"
                })
            }
            $('.assign-person').html(template('archives-assign-person', {
                items: data
            }))
        });
    }
    //设置指派人
    function setDesignatePerson(res, dom) {
        var section = $('#js-reach').val(),
            code = $('.js-card-type option:selected').data('code'),
            content = $("#assign-remark").val(),
            type = res.data("type"),
            eventCode = res.data("code"),
            states = res.data("states"),
            node = type == "TS" ? complaintNode[states] : maintainNode[states];
            console.log(eventCode,type)
        if (!section) {
            $.alert("请选择部门");
        } else if (!code) {
            $.alert("请选择指派人");
        } else if (!content) {
            $.alert("请填写备注");
        } else {
            $.getData({
                url: '/event/proceEventInfo',
                query: {
                    node: node
                },
                body: {
                    content: content,
                    person: code,
                    emCode: eventCode,
                    instanceId: eventCode,
                    emType: type
                }
            }, function (data) {
                $(dom).modal('hide');
                $.toast('操作成功', {
                    type: 'success',
                }, getEventList);
            });
        }

    }
    //设置处理完成
    function setDispose(res, dom) {
        var time = $('#js-h-processingTime').val(),
            content = $("#js-h-processing").val(),
            type = res.data("type"),
            eventCode = res.data("code"),
            states = res.data("states"),
            node = type == "TS" ? complaintNode[states] : maintainNode[states];
        if (!time) {
            $.alert("请选择处理时间");
        } else if (!content) {
            $.alert("请填写处理说明");
        } else {
            $.getData({
                url: '/event/proceEventInfo',
                query: {
                    node: node
                },
                body: {
                    content: content,
                    date: time,
                    emCode: eventCode,
                    instanceId: eventCode,
                    emType: type
                }
            }, function (data) {
                $(dom).modal('hide');
                $.toast('操作成功', {
                    type: 'success',
                }, getEventList);
            });
        }

    }
    //设置处理保存
    function setDisposeSave(res, dom) {
        var time = $('#js-h-processingTime').val(),
            content = $("#js-h-processing").val(),
            type = res.data("type"),
            eventCode = res.data("code"),
            states = res.data("states"),
            node = type == "TS" ? complaintNode[states] : maintainNode[states];
        if (!time) {
            $.alert("请选择处理时间");
        } else if (!content) {
            $.alert("请填写处理说明");
        } else {
            $.getData({
                url: '/event/saveProcessInfo',
                body: {
                    content: content,
                    date: time,
                    emCode: eventCode,
                    instanceId: eventCode,
                    emType: type
                }
            }, function (data) {
                $(dom).modal('hide');
                $.toast('操作成功', {
                    type: 'success',
                }, getEventList);
            });
        }
    }
    //设置回访
    function setReturn(res, dom) {
        var time = $('#js-h-processingTime').val(),
            content = $("#js-h-return").val(),
            type = res.data("type"),
            eventCode = res.data("code"),
            states = res.data("states"),
            node = type == "TS" ? complaintNode[states] : maintainNode[states];
        if (!time) {
            $.alert("请选择回访时间");
        } else if (!content) {
            $.alert("请填写回访内容");
        } else {
            $.getData({
                url: '/event/proceEventInfo',
                query: {
                    node: node
                },
                body: {
                    content: content,
                    date: time,
                    emCode: eventCode,
                    instanceId: eventCode,
                    emType: type
                }
            }, function (data) {
                $(dom).modal('hide');
                $.toast('操作成功', {
                    type: 'success',
                }, getEventList);
            });
        }
    }
    //设置核实
    function setVerify(res, dom) {
        var time = $("#js-h-processingTime").val(),
            content = $("#js-h-verify").val(),
            userId = $("#js-h-user").data('id'),
            type = res.data("type"),
            eventCode = res.data("code"),
            states = res.data("states"),
            node = type == "TS" ? complaintNode[states] : maintainNode[states];
        if (!time) {
            $.alert("请选择核实时间");
        } else if (!content) {
            $.alert("请填写备注");
        } else {
            $.getData({
                url: '/event/proceEventInfo',
                query: {
                    node: node
                },
                body: {
                    content: content,
                    date: time,
                    person: userId,
                    emCode: eventCode,
                    instanceId: eventCode,
                    emType: type
                }
            }, function (data) {
                $(dom).modal('hide');
                $.toast('操作成功', {
                    type: 'success',
                }, getEventList);
            });
        }
    }
    //终止操作
    function eventTermination(obj) {
        $.pop({
            el: 'designate',
            ide: "#designate",
            yesModel: false,
            noIcon: true,
            title: "终止",
            content: template("model/archives-detail-termination", {}),
            size: "md",
            yes: function () {
                setTermination(obj, "#designate")
            }
        });
        calendar("#js-h-processingTime", "startDate");
        $.counter({
            el: '#js-h-termination'
        });
    }
    //设置终止
    function setTermination(res, dom) {
        var time = $('#js-h-processingTime').val(),
            content = $("#js-h-termination").val(),
            type = res.data("type"),
            eventCode = res.data("code"),
            states = res.data("states");
        if (!time) {
            $.alert("请选择终止时间");
        } else if (!content) {
            $.alert("请填写终止原因");
        } else {
            $.getData({
                url: '/event/proceEventInfo',
                query: {
                    node: 'ZZ'
                },
                body: {
                    content: content,
                    date: time,
                    emCode: eventCode,
                    instanceId: eventCode,
                    emType: type
                }
            }, function (data) {
                $(dom).modal('hide');
                $.toast('操作成功', {
                    type: 'success',
                }, getEventList);
            });
        }
    }

    //渲染树
    function turnDate(data, value) {
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
        $('#tt').tree({
            data: arr,
            cascadeCheck: false,
            checkbox: true,
            animate: true,
            idField: 'passId',
            loadFilter: function (rows) { //转换数据格式
                return convert(rows);
            },
            // onCheck: function (node, checked) { //选中回调  //复选
            //     if (checked) {
            //         // document.getElementsByName("allChecked")[0].checked = false;
            //         selTree.push(node.text);
            //         console.log(selTree)
            //     } else {
            //         selTree.splice(selTree.indexOf(node.text), 1);
            //     }
            //     $(".js-room-location").val(selTree);
            // }
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
                    $('#tt').tree('check', node.target);
                }
                parId = node.id;
                configs = {
                    selTree,
                    parId
                }
            },
            onLoadSuccess: function (node, data) {
                if (value != null) {
                    var node = $('#tt').tree('find', value);
                    $('#tt').tree('check', node.target);
                }
                $(this).find('span.tree-checkbox').unbind().click(function () {
                    $('#tt').tree('select', $(this).parent());
                    return false;
                });
            }
        });
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
        return nodes;
    }
    // 获取级别，
    function getLevel(value) {
        $.getData({
            url: '/customer/customLevelInfoList'
        }, function (data) {
            if (data && data.length) {
                var _data = [];
                for (var i in data) {
                    _data.push({
                        value: data[i].clId,
                        title: data[i].levelName
                    });
                }
                renderSelect('#level', _data, value);
            }
        });
    }
    // function returnCalendar(time, _startDate) {
    //     let now = new Date();
    //     let times = {
    //         format: "yyyy-mm-dd hh:ii:ss",
    //         language: "zh-CN",
    //         weekStart: 1,
    //         todayBtn: 1,
    //         autoclose: 1,
    //         todayHighlight: 1,
    //         startView: 2,
    //         minView: 0,
    //         forceParse: 0,
    //         startDate: _startDate,
    //         endDate: new Date()
    //     }
    //     $(time).datetimepicker(times);
    // }
    function calendar1(time) {
        let now = new Date();
        let times = {
            format: "yyyy-mm-dd hh:ii:ss",
            language: "zh-CN",
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 0,
            forceParse: 0,
            startDate: new Date(now.getTime() - 4 * 24 * 3600 * 1000),
            endDate: new Date()
        }
        $(time).datetimepicker(times);
    }
    //日历
    function calendar(Time, _startDate) {
        let times = {
            format: "yyyy-mm-dd hh:ii:ss",
            language: "zh-CN",
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 0,
            forceParse: 0,
            // startDate:new Date()
        }
        times[_startDate] = new Date();
        $(Time).datetimepicker(times);
    }

    function init() {
        getDetail();
        getCardList();
        // renderEmpty(".js-cards", 10);
        // renderEmpty(".js-events", 10);
        // renderEmpty(".js-cars", 10);
        // getEventList();
        $(".nav-tabs").on("click", "li", function () {
            $(this)
                .addClass("active")
                .siblings()
                .removeClass("active");
            $(".js-tab-item")
                .eq($(this).index())
                .removeClass("hide")
                .siblings()
                .addClass("hide");
            return false;
        });
        //获取组织树
        $(document).on("click", "#js-reach", function () {
            $.pop({
                title: "选择部门",
                el: 'branch',
                ide: "#branch",
                noIcon: true,
                content: template("tree-branch"),
                size: "sm",
                yes: function () {
                    $("#js-reach").val(configs.selTree);
                    $("#js-reach").attr("data-parid", configs.parId);
                    getDesignatedPerson(configs.parId);
                }
            })
            let val = $(this).val();
            // val != undefined || val != "" ? getReach(configs.parId) : getReach();
            getReach();
        })
        $(document).on("click", ".js-card-edit", function () {
            var $this = $(this);
            $.pop({
                title: "编辑证件",
                noIcon: true,
                content: template("archives-detail-card-edit", {
                    value: $this.data("ccnid"),
                    number: $this.data("number"),
                    items: cardList
                }),
                size: "sm",
                yes: function () {
                    if ($.trim($("#card-number").val()) === "") {
                        return;
                    }
                    editCard(
                        $this,
                        $this.data("ccid"),
                        $("#card-type").val(),
                        $("#card-type option:selected").text(),
                        $.trim($("#card-number").val())
                    );
                }
            });
            return false;
        });
        $(document).on("click", ".js-card-del", function () {
            var ids = $(this).data("ccid"),
                $this = $(this);

            // $.confirm("确定删除当前证件？", function () {
            //     delCard($this, ids);
            // });
            $.bubble({
                el: $(this),
                msg: '确定删除当前客户？',
                ok: function () {
                    delCard($this, ids);
                },
                cancel: function () {
                    $.toast('您已取消删除');
                }
            });
            return false;
        });
        $(document).on("click", ".js-card-add", function () {
            $.pop({
                noIcon: true,
                title: "新增证件",
                content: template("archives-detail-card-edit", {
                    items: cardList
                }),
                size: "sm",
                yes: function () {
                    addCard();
                }
            });
        });
        //添加事件
        $(document).on("click", ".js-event-add", function () {
            var prData = {},
                prUrl = '';
            prData.name = detailData.prName;
            prData.phone = detailData.prPhoneFirst;
            $.pop({
                el: 'designate',
                ide: "#designate",
                yesModel: false,
                noIcon: true,
                title: "事件增加",
                content: template("archives-detail-event", {
                    items: prData
                }),
                height: "300px",
                size: "md",
                yes: function () {
                    addEvent(prUrl, "#designate");
                }
            });
            $.uploader({
                el: '.shangchuan',
                url: '/uploadDown/uploadImages',
                maxLength: 5,
                tips: '注：最多上传5张图片，<br />推荐尺寸1080*1080，图片大小不超过10M',
                afterUpload: function (data) {
                    prUrl = data
                }
            });
            $.counter({
                el: '#desc'
            });
            getCardType();
        });
        //获取投诉类型
        $(document).on("change", ".js-event-type", function () {
            getCardType();
        });
        //查看进度
        $(document).on("click", ".js-event-check", function () {
            var $this = $(this)
            var data = {};
            data.code = $this.data("code");
            data.codeType = $this.data("codetype")
            getEventProgress(data)
        })
        //指派
        $(document).on("click", ".js-event-operate", function () {
            var $this = $(this);
            setEventVisit($this);
        })
        //终止事件
        $(document).on("click", ".js-event-del", function () {
            var $this = $(this);
            eventTermination($this);
        })
        $(document).on("click", ".js-car-del", function () {
            var ids = $(this).data("ccid"),
                $this = $(this);
            $.confirm("确定删除当前车辆？", function () {
                delCar($this, ids);
            });
            return false;
        });
    }
    init();
});

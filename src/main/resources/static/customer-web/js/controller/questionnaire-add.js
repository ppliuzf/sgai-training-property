$(function () {
    var id = $.getQueryString("id");
    // 获取数据
    function getDetail(id) {
        var query = {
            id: id
        };
        $.getData({
                url: "/survey/getSurveyDetail",
                query: query
            },
            function (data) {
                if (data) {
                    $(".container-fluid").html(
                        template("questionnaire-add", {
                            type: "编辑",
                            item: data
                        })
                    );
                    var surveyQuestionVOs = data.surveyQuestionVOs;
                    for (var i = 0; i < surveyQuestionVOs.length; i++) {
                        var str = surveyQuestionVOs[i].soContent;
                        if (str) {
                            var arr = str.split("@@");
                            arr.length -= 1;
                            surveyQuestionVOs[i].arrContent = arr;
                        } else {
                            surveyQuestionVOs[i].arrContent = [];
                        }
                    }
                    $(".js-content").html(
                        template("questionnaire-add-content", {
                            items: surveyQuestionVOs,
                            id: new Date().getTime()
                        })
                    );
                    $("#smEndTime").datetimepicker({
                        format: "yyyy-mm-dd hh:ii",
                        language: "zh-CN",
                        weekStart: 1,
                        todayBtn: 1,
                        autoclose: 1,
                        todayHighlight: 1,
                        startView: 2,
                        minView: 0,
                        forceParse: 0,
                        // startDate:new Date()
                    });
                    $(".js-questionnaire-box-add")
                        .parent()[$(".js-content .box").length >= 30 ? "addClass" : "removeClass"](
                            "hide"
                        );
                    $(".js-card-remove")[
                        $(".js-content .box").length <= 1 ? "addClass" : "removeClass"
                    ]("hide");
                    //设置内容类型值
                    $(".js-content-type").each(function () {
                        var _this = $(this);
                        if (_this.attr("value") === "0") {
                            _this.val("0");
                        } else if (_this.attr("value") === "1") {
                            _this.val("1");
                        } else {
                            _this.val("2");
                        }
                    });
                    //选项内容
                    $(".box").each(function (i) {
                        _this = $(this);
                        if (surveyQuestionVOs[i].arrContent.length > 0) {
                            _this.find(".js-items").append(
                                template("questionnaire-add-item", {
                                    items: surveyQuestionVOs[i].arrContent
                                })
                            );
                        } else {
                            _this.find(".js-items").addClass("hide");
                        }
                        $(".js-questionnaire-item-add")[
                            _this.find(".js-item").length >= 10 ? "addClass" : "remove"
                        ]("hide");
                        setGroupItemNum(_this);
                        setGroupItemBtn(_this.find(".js-items"));
                    });
                } else {
                    $.alert("数据不存在", function () {
                        history.back();
                    });
                }
            }
        );
    }

    //校验标题，选项内容
    function checkContent() {
        var bool = false;
        $(".js-content .box").each(function (i) {
            var _this = $(this);
            if ($.trim(_this.find(".js-title").val()) === "") {
                console.log("biaoti");
                $.alert("请输入标题", function () {
                    _this.find(".js-title").select();
                });
                bool = true;
                return false;
            }
            if (!_this.find(".js-items").hasClass("hide")) {
                var arrStr = [];
                _this.find(".js-item").each(function (i) {
                    var $this = $(this);
                    if ($.trim($this.val()) === "") {
                        console.log("neirontg");
                        $.alert("请输入选项内容", function () {
                            $this.select();
                        });
                        bool = true;
                        return false;
                    } else if ($.trim($(this).val())) {
                        arrStr.push($.trim($(this).val()));
                        if (isRepeat(arrStr)) {
                            $.alert("选项内容不能重复！", function () {
                                _this.select();
                            });
                            bool = true;
                        }
                    }
                });
            }
            if (bool) return false;
        });

        return bool;
    }

    function isRepeat(arr) {
        var hash = {};
        for (var i in arr) {
            if (hash[arr[i]]) return true;
            hash[arr[i]] = true;
        }
        return false;
    }
    //时间提示
    // $('#smEndTime').datetimepicker()
    //     .on('changeDate', function (ev) {
    //         if ($.trim($("#smEndTime").val())) {
    //             var time1 = $.getTimeStamp($.trim($("#smEndTime").val())); //设置的时间
    //             var time2 = new Date().valueOf(); //获取的时间
    //             if (time1 < time2) {
    //                 $.alert("结束时间要大于当前时间！", function () {
    //                     $("#smEndTime").select();
    //                 });
    //                 return false;
    //             }
    //         }
    //     });
    // 收集数据
    function collectData() {
        var rs = {},
            arrContent = [];
        if ($.trim($("#name").val()) === "") {
            $.alert("请输入问卷名称", function () {
                $("#name").select();
            });
            return false;
        }
        if ($.trim($("#smEndTime").val()) === "") {
            $.alert("请选择结束时间", function () {
                $("#smEndTime").select();
            });
            return false;
        }

        if (checkContent()) {
            return false;
        }
        $(".js-content .box").each(function (i) {
            _this = $(this);
            var sqTopic = $.trim(_this.find(".js-title").val()),
                sqNo = i + 1,
                sqType = _this.find(".js-content-type").val(),
                soContent = "",
                oContent = {};
            if (!_this.find(".js-items").hasClass("hide")) {
                _this.find(".js-item").each(function () {
                    soContent += $.trim($(this).val()) + "@@";
                });
                console.log(soContent);
            }
            oContent = {
                soContent: soContent,
                sqNo: sqNo,
                sqTopic: sqTopic,
                sqType: sqType
            };
            arrContent.push(oContent);
        });
        rs.surveyQuestionParamList = arrContent;
        console.log($("#smEndTime").val());
        rs.surveyMainParam = {
            smCount: $(".js-content .box").length,
            smDesc: "",
            smEndTime: $.getTimeStamp($("#smEndTime").val()),
            smId: "",
            smName: $.trim($("#name").val())
        };
        if (id) {
            rs.surveyMainParam.smId = id;
        }
        return rs;
    }

    // 保存数据
    function saveData(params) {
        var url = "/survey/createSurvey";
        $.getData({
                url: url,
                body: params
            },
            function (data) {
                if (data) {
                    $.toast('操作成功', {
                        type: 'success',
                    }, function () {
                        history.back();
                    });
                }
            }
        );
    }

    // 编辑update保存
    function updateData(params) {
        var url = "/survey/updateSurvey";
        $.getData({
                url: url,
                body: params
            },
            function (data) {
                if (data) {
                    $.toast('操作成功', {
                        type: 'success',
                    }, function () {
                        history.back();
                    });
                }
            },function(data){
                $.alert(data,function(){
                    history.go(-1);
                })
            }
        );
    }

    // 设置每道题删除按钮样式
    function setCardRemoveBtn() {
        $(".js-card-remove")[
            $(".js-content .box").length > 1 ? "removeClass" : "addClass"
        ]("hide");
    }

    //设置每道题序号
    function setContentNum() {
        $(".js-content .box").each(function (i) {
            $(this)
                .find(".js-num")
                .text(i + 1);
        });
    }

    //设置选项内容的序号
    function setGroupItemNum(el) {
        el.find(".js-group").each(function (i) {
            $(this)
                .find(".js-group-num")
                .text(i + 1);
        });
    }

    //设置选项内容 删除按钮样式
    function setGroupItemBtn(_this) {
        _this
            .find(".js-questionnaire-item-del")[_this.find(".js-group").length <= 2 ? "addClass" : "removeClass"](
                "disabled"
            );
    }

    function init() {
        if (id) {
            getDetail(id);
        } else {
            $(".container-fluid").html(
                template("questionnaire-add", {
                    type: "新建",
                    item: {}
                })
            );
            $(".js-content").html(
                template("questionnaire-add-content", {
                    items: [1],
                    id: new Date().getTime()
                })
            );
            $(".js-items").append(
                template("questionnaire-add-item", {
                    items: ["", ""]
                })
            );
            $(".js-num").text("1");
            setGroupItemNum($(".js-items"));
            setGroupItemBtn($(".js-items"));
            $("#smEndTime").datetimepicker({
                format: "yyyy-mm-dd hh:ii",
                language: "zh-CN",
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 0,
                forceParse: 0,
                // startDate:new Date()
            });
        }
        // 添加多个题目
        $(document).on("click", ".js-questionnaire-box-add", function () {
            $(".js-content").append(
                template("questionnaire-add-content", {
                    items: [1],
                    id: new Date().getTime()
                })
            );
            $(".js-questionnaire-box-add")
                .parent()[$(".js-content .box").length >= 20 ? "addClass" : "removeClass"](
                    "hide"
                );
            setContentNum();
            setCardRemoveBtn();
            var lastChildBox = $(".js-content .box:last-child");
            lastChildBox.find(".js-items").append(
                template("questionnaire-add-item", {
                    items: ["", ""]
                })
            );
            setGroupItemNum(lastChildBox);
            setGroupItemBtn(lastChildBox);
        });
        // 删除题目
        $(document).on("click", ".js-card-remove", function () {
            $(this)
                .parent()
                .remove();
            setContentNum();
            setCardRemoveBtn();
            $(".js-questionnaire-box-add")
                .parent()[$(".js-content .box").length >= 20 ? "addClass" : "removeClass"](
                    "hide"
                );
        })
        //选项为文本时，隐藏
        $(document).on("change", ".js-content-type", function () {
            $(this)
                .parents()
                .siblings(".js-items")[$(this).val() === "2" ? "addClass" : "removeClass"]("hide");
        });
        //添加选项内容
        $(document).on("click", ".js-questionnaire-item-add", function () {
            var itemsBox = $(this).parents(".js-items");
            itemsBox.append(template("questionnaire-add-item", {
                items: [""]
            }));
            $(this)[
                itemsBox.find(".js-group").length >= 10 ? "addClass" : "removeClass"
            ]("hide");
            setGroupItemNum(itemsBox);
            setGroupItemBtn(itemsBox);
        });
        //删除选项内容
        $(document).on("click", ".js-questionnaire-item-del", function () {
            var _this = $(this),
                itemsBox = _this.parents(".js-items");
            _this.parents(".js-group").remove();
            itemsBox
                .find(".js-questionnaire-item-add")[itemsBox.find(".js-group").length >= 10 ? "addClass" : "removeClass"](
                    "hide"
                );
            setGroupItemBtn(itemsBox);
            setGroupItemNum(itemsBox);
        });
        // 保存
        $(document).on("click", ".js-save", function () {
            var data = collectData();
            if (data) {
                if (id) {
                    updateData(data);
                } else {
                    saveData(data);
                }
            }
        });
        // 取消
        $(document).on("click", ".js-cancel", function () {
            history.back();
        });
    }

    init();
});
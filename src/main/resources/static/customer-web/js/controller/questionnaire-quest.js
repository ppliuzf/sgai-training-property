$(function () {
    var smId = $.getQueryString("id");
    var ary = [], //单选答案
        oba = "",
        sqNo = [],
        arrys = [];

    //获取题目列表
    function getResult() {
        $.getData({
                url: "/survey/getDetailById",
                query: {
                    smId: smId
                }
            },
            function (data) {
                //改变数据结构
                console.log(data)
                let curItem = data["surveyQuestionDetailVoList"];
                arrys = curItem;
                for (let item of curItem) {
                    let saContent = item.soContent;
                    if (item.sqType != 2) {
                        item["saContentList"] = saContent.indexOf("@@") >= 0 ? saContent.split("@@") : saContent;
                        item["saContentList"].pop()
                    }
                };
                //加载数据，渲染页面    
                if (data) {
                    renderResult(".js-result", "questionnaire-quest", data);
                    let flag = true;
                    for (let item of data.surveyQuestionDetailVoList) {
                        if (item.sqType == 2) {
                            flag = false;
                        }
                    }
                    if (!flag) {
                        $.counterG({
                            el: '.answerText'
                        });
                    }

                };
            }
        );
    }

    //获取答案
    function loadEvent() {
        //获取单选题
        $(document).on("change", ".detail-box .answerZ", function () {
            let answers = $(this).val(),
                sqId = $(this).data('sqid'),
                saType = $(this).data('sqtype');
            sqNo.push($(this).data('name'));
            console.log(sqNo)
            obj = {
                saContent: answers,
                sqId,
                saType,
                smId
            };
            let flag = true; //标识
            if (ary.length > 0) {
                for (let item of ary) {
                    if (item.sqId === obj.sqId) {
                        item.saContent = obj.saContent;
                        flag = false;
                        break;
                    }
                }
                flag ? ary.push(obj) : null; //判断数组里是是否有重负的答案
            } else {
                ary.push(obj); //获取第一个答案
            }
        });
        //获取多选题答案 
        $(document).on("change", ".answerO", function () {

            let answers = $(this).val(), //获取答案
                sqId = $(this).data('sqid'), //获取id
                saType = $(this).data('sqtype'); //获取题型
            obj = {
                saContent: answers, //保存答案
                sqId, //保存id
                saType,
                smId
            };
            let flag = true,
                fblg = true;
            if (ary.length > 0) {
                for (let item of ary) {
                    if (item.sqId === obj.sqId) {

                        let ary = item.saContent.split("@@");
                        for (let i = 0; i < ary.length; i++) {
                            let idem = ary[i];
                            if (idem == obj.saContent) {
                                ary.splice(i, 1, "");
                                sqNo.splice(sqNo.indexOf($(this).data('name')), 1);
                                for (let iaem of ary) {
                                    if (iaem != "") {
                                        iaem += "@@";
                                        oba += iaem
                                    }
                                }
                                item.saContent = oba;
                                oba = "";
                                fblg = false;
                                break;
                            }
                        }
                        if (fblg) {
                            item.saContent = item.saContent + "@@" + obj.saContent;
                            sqNo.push($(this).data('name'));
                        }
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    ary.push(obj);
                    sqNo.push($(this).data('name'));
                }
            } else {
                ary.push(obj); //获取第一个答案
                sqNo.push($(this).data('name'));
            }
        })
        //获取文本答案
        $(document).on("keyup", ".answerText", function () {
            let answers = $.trim($(this).val()),
                sqId = $(this).data('sqid'),
                saType = $(this).data('sqtype');
            sqNo.push($(this).data('name'));
            obj = {
                    saContent: answers,
                    sqId,
                    saType,
                    smId
                },
                flag = true;
            if (ary.length > 0) {
                for (let item of ary) {
                    if (item.sqId === obj.sqId) {
                        item.saContent = obj.saContent;
                        flag = false;
                        break;
                    }
                }
                flag ? ary.push(obj) : null; //判断数组里是是否有重负的答案
            } else {
                ary.push(obj); //获取第一个答案
            }
            // console.log(obj)
        })

    }
    //匹配空题
    function trimanswer() {
        for(let i = 0; i < $(".answerText").length; i++){
            let answerText = $.trim($(".answerText").eq(i).val());
            if ( answerText == '') {
                $.alert('答案不能为空,请回答完毕在提交！', function () {
                    $(this).focus();
                });
                return;
            }
        }    
        let num = [];
        for (let item of arrys) {
            num.push(item.sqNo);
        }
        set = new Set(sqNo);
        sqNo = Array.from(set);
        for (let i = 0; i < num.length; i++) {
            let item = num[i];
            for (let l = 0; l < sqNo.length; l++) {
                let idem = sqNo[l];
                if (idem == item) {
                    num.splice(i, 1);
                    i = -1;
                }
            }
        }
        if (num.length > 0) {
            $.alert(`您第${num}题没有回答，请回答完毕再提交！`)
        } else {
            $(".show-quest").hide().siblings('.hide-query').show()
        }
    };
    //匹配空user
    function trimuser() {
        let userName = $.trim($("#userName").val()),
            userPhone = $.trim($("#userPhone").val());
        console.log(userName.length);

        if (userName == "") {
            $.alert('姓名不能为空或者格式错误！', function () {
                $("#userName").focus();
            });
            return false;
        } else if (!userPhone.match(/^1[0-9]{10}.*/ig) || userPhone == '') {
            $.alert('请您输入正确的手机号！', function () {
                $("#userPhone").focus();
            });
            return false;
        } else {
            return true;
        }
    }
    //保存答案
    function Saveanswer({
        focu = true
    }) {
        let userName = $.trim($("#userName").val()),
            userPhone = $.trim($("#userPhone").val());
        let obj = {
            "surveyUserAnswerVOs": ary, //答案的集合
            userName,
            userPhone
        };
        $.getData({
                url: "/survey/submitSurvey",
                body: obj,
            },
            function (data) {
                if (data) {
                    console.log(11111)
                    if (!focu) {
                        $.toast('保存成功', {
                            type: 'success',
                        }, function(){location.reload()});
                        // setTimeout("location.reload()", 2000);
                    }else {
                        $.toast('保存成功', {
                            type: 'success',
                        }, function(){
                            history.go(-1)
                        });
                        // setTimeout("history.go(-1)", 2000);
                    }
                }else {
                    $.toast('该手机号已参与过答题', {
                        type: 'error',
                    }, function(){});
                }
                return true;
            },function (data) {
                $.toast(data, {
                    type: 'error',
                },function(){
                    setTimeout(" history.go(-1)", 2000);
                });
            });
    }
    //返回列表
    function goBack() {
        $(document).on("click", ".js-save", function () { //下一步
            if (trimanswer()) {
                $(".show-quest").hide().siblings('.hide-query').show()
            }
        });
        $(document).on("click", ".btn-go", function () {
            backTo();
        });
        $(document).on("click", ".js-cancel", function () { //上一步
            $(".show-quest").show().siblings('.hide-query').hide();
        })
        $(document).on('click', '.js-close', function () { //取消

            $.pop({
                content: "确认取消填写此问卷？",
                size: "sm",
                yes: function () {
                    backTo();
                }
            })
        });
        $(document).on("click", ".js-saves", function () { //保存
            if (trimuser()) {
                $.pop({
                    content: "保存此问卷？",
                    size: "sm",
                    yes: function () {
                        Saveanswer({
                            focu: true
                        });
                        // $.msg("保存成功");
                        // $.toast('保存成功', {
                        //     type: 'success',
                        // }, function(){});
                        // setTimeout("history.go(-1)", 2000);
                    },
                })
            } else {
                return;
            }

        });
        $(document).on("click", ".js-Entry", function () {

            if (trimuser()) {
                $.pop({
                    content: "保存并录入下一条？",
                    size: "sm",
                    yes: function () {
                        Saveanswer({
                            focu: false
                        });
                    },
                })
            } else {
                return;
            }
        });
    }
    //返回上一页
    function backTo() {
        history.go(-1);
        // return false;
    }
    // 渲染结果页面
    function renderResult(obj, temp, data) {
        $(obj).html(template(temp, data));
    }
    //初始化所有函数
    function init() {
        //渲染第一题
        getResult();
        //返回
        goBack();
        //加载所有事件
        loadEvent();
    }
    init();
});
$(function () {
    var id = $.getQueryString("id"),
        sqId,
        detaInfo,
        tIdx;
    //查看详情
    function getDetail(pageId, isFirst, detail) {
        detaInfo = detail;
        var pageId = pageId || 1,
            isFirst = isFirst || true;
        $.getData({
            url: "/survey/getResultDetail",//
            query: {
                PageInfoNum: pageId,
                PageInfoSize: 10
            },
            body: {
                saContent: detail.saContent,
                smId: id,
                sqId: sqId
            }
        },
            function (data) {
                if (data) {
                    var total = data.total,
                        currPage = data.pageNum,
                        data = $.extend(data, detail);
                    console.log(data);
                    renderModal("questionnaire-modal", data);
                    if (data.list.length > 0) {
                        if (total > 0) {
                            isFirst &&
                                $.renderPage({
                                    count: total,
                                    limit: 10,
                                    curr: currPage,
                                    jump: function (n) {
                                        currPage = n;
                                        getDetail(currPage, true, detaInfo);
                                    }
                                });
                        }
                    } else {
                        $("#select-detail").html(
                            template("common/record-empty", {
                                colspan: 6
                            })
                        );
                    }
                }
            }
        );
    }
    //获取题目列表
    function getResult(idx = 1, pageIdx = 1, isFirst = true) {
        $.getData({
            url: "/survey/getSurveyResult",
            query: {
                smId: id,
                sqNo: idx,
                PageInfoSize: 10,
                PageInfoNum: pageIdx
            }
        },
            function (data) {
                console.log(data);
                if (data) {
                    var csColor = ["success", "info", "striped", "danger", "warning"],
                        arr = [];
                    sqId = data.sqId;
                    data.arrayNo.forEach((item, index) => {
                        let obj = {};
                        // console.log(item);
                        // console.log(index);
                        obj["index"] = item;
                        arr.push(obj);
                    });
                    data.arrayNo = arr;
                    if (data.optionInfoPageInfo.list.length > 0) {
                        var l = -1;
                        for (var i = 0; i < data.optionInfoPageInfo.list.length; i++) {
                            var item = data.optionInfoPageInfo.list[i];
                            l++;
                            if (l > 5) l = -1;
                            item.scale =
                                parseFloat(item.scale * 100).toFixed(2) + "%";
                            item.color = csColor[l];
                            item.idx = l;
                        }
                    } else if (data.textAnswerVoPageInfo.list) {
                        tIdx = idx;
                        if (data.textAnswerVoPageInfo.list.length > 0) {
                            var total = data.textAnswerVoPageInfo.total,
                                currPage = data.textAnswerVoPageInfo.pageNum;
                            renderResult(".js-result", "questionnaire-result", data);
                            if (total > 0) {
                                isFirst &&
                                    $.renderPage({
                                        count: total,
                                        limit: 10,
                                        curr: currPage,
                                        jump: function (n) {
                                            console.log(n);
                                            currPage = n;
                                            getResult(tIdx, currPage);
                                        }
                                    });
                            }
                        } else {
                            renderResult(".js-result", "questionnaire-result", data);
                            renderResult("#text-detail", "common/record-empty", {
                                colspan: 8
                            });
                        }
                        return;
                    }
                    renderResult(".js-result", "questionnaire-result", data);
                }
            }
        );
    }
    //页面跳转函数
    function jumpPage() {
        var jump = $(this).attr("data-cla"), //获取题本本身Class，用于识别是谁
            sqNo = $("#wrap-word").attr("data-sq"), //获取本题数字
            smCount = $("#wrap-word").attr("data-cou"); //获取题目总数

        switch (jump) {
            case "result-page":
                var idx = $(this).attr("data-page");
                getResult(idx);
                break;
            case "result-up":
                if (parseFloat(sqNo) > 1) {
                    getResult(parseFloat(sqNo) - 1);
                }
                break;
            case "result-next":
                if (parseFloat(sqNo) < smCount) {
                    getResult(parseFloat(sqNo) + 1);
                }
                break;
            default:
        }
    }
    // 渲染结果页面
    function renderResult(obj, temp, data) {
        $(obj).html(template(temp, data));
    }
    function renderModal(temp, data) {
        $.pop({
            noIcon: true,
            title: "调查详情",
            isCancel: false,
            content: template(temp, data),
            size: "md",
            yes: function () { }
        });
        // $(obj).html(template(temp, data));
    }

    function init() {
        //渲染第一题
        getResult();
        //返回
        $(document).on("click", ".btn-go", function () {
            window.history.back();
        });
        //打开详情页面
        $(document).on("click", ".res-modal", function () {
            var data = {
                saContent: $(this).attr("data-saContent"),
                count: $(this).attr("data-count"),
                scale: $(this).attr("data-scale"),
                color: $(this).attr("data-color")
            };
            getDetail(1, true, data);
        });
        //跳转题目
        $(document).on("click", ".result-page,.result-up,.result-next", jumpPage);
        //上一步，下一步
        //查看文本详情
        $(document).on("click", ".text-detail", function () {
            var text = $(this).attr("data-txt"),
                data = { boxTxt: text };
                renderModal("questionnaire-modal", data);
        });
    }
    init();
});
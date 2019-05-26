$(function () {
    var currPage = 1,
        total = 0,
        searchParams = {
            smName: $.trim($("#smName").val()),
            smStatus: $("#smStatus").val()
        };
    // 获取列表
    function getList(pageNum = 1, isFirst = true) {
        $.getData({
            url: "/survey/getSurveyPage",
            query: {
                pageNum: pageNum,
                pageSize: 10
            },
            body: {
                smName: $.trim($("#smName").val()),
                smStatus: $("#smStatus").val()
            }
        },
            function (data) {
                console.log(data);
                if (data) {
                    renderList(data.list);
                    total = data.count;
                    isFirst &&
                        $.renderPage({
                            count: total,
                            curr: pageNum,
                            jump: function (n) {
                                currPage = n;
                                getList(n);
                            }
                        });
                }
            }
        );
    }

    //开始调查
    function startSurvey(id) {
        $.getData({
            url: "/survey/startSurvey",
            query: {
                id: id
            }
        },
            function (data) {
                // if (data) {
                    // $.msg("操作成功", function () {
                    //     getList(currPage, true);
                    // });
                    $.toast('操作成功', {
                        type: 'success',
                    }, function(){
                        getList(currPage, true);
                    });
                // }
            },
            function(data){
                $.toast("调查已结束不可修改", {
                    type: 'error',
                }, function(){
                    history.go(0);
                });
            }
        );
    }

    //结束
    function stopSurvey(id) {
        $.getData({
            url: "/survey/stopSurvey",
            query: {
                smId: id
            }
        },
            function (data) {
                if (data) {
                    // $.msg("操作成功", function () {
                    //     getList(currPage, true);
                    // });
                    $.toast('操作成功', {
                        type: 'success',
                    }, function(){
                        getList(currPage, true);
                    });
                }
            }
        );
    }

    // 删除
    function delItem(el, id) {
        $.getData({
            url: "/survey/deleteSurvey",
            query: {
                smId: id
            }
        },
            function (data) {
                if (data) {
                    // $.msg("操作成功", getList(1, true));
                    $.toast('操作成功', {
                        type: 'success',
                    }, function(){
                        getList(1, true);
                    });
                }
            }
        );
    }

    // 渲染列表
    function renderList(data) {
        $(".js-list").html(
            template("questionnaire-list", {
                items: data
            })
        );
    }

    // 渲染空
    function renderEmpty() {
        $(".js-list").html(
            template("common/record-emptys", {
                colspan: 8
            })
        );
    }

    function init() {
        var authCode = $.getQueryString("authCode");
        if (authCode === undefined || authCode === "undefined") {
                $("body").html(template("common/403"));
        }
        getList(1, true);
        //搜索
        $(document).on("click", ".js-search", function () {
            getList(1, true);
        });
        //开始调查
        $(document).on("click", ".js-start", function () {
            var id = $(this).data("id");
            $.confirm("确认开始当前问卷?", function () {
                startSurvey(id);
            });
        });
        // 删除
        $(document).on("click", ".js-del", function () {
            var $this = $(this),
                id = $this.data("id");
            // $.confirm("确定删除当前问卷？", function () {
            //     delItem($this, id);
            // });
            $.bubble({
                el: $(this),
                msg: '确认删除当前问卷？',
                ok: function () {
                    delItem($this, id);
                },
                cancel: function () {
                    $.toast('您已取消删除');
                }
            });
        });
        //结束
        $(document).on("click", ".js-end", function () {
            var id = $(this).data("id");
            $.confirm("确认结束当前问卷?", function () {
                stopSurvey(id);
            });
        });
    }

    init();
});
$(function () {
    // 获取列表
    function getList(pageNum = 1, isFirst = true) {
        $.getData({
            url: "/customer/uploadRecordInfoPageList",
            query: {
                pageNum: pageNum,
                pageSize: 10
            },
            body: {
                
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
    //渲染列表
    function renderList(data) {
        $(".js-list").html(
            template("archives-import-record", {
                items: data
            })
        );
    }
    function init() {
        // getList(1, true);
        $('.js-list').html(template('common/record-empty', {
            colspan: 7
        }));
        $.renderPage({
            count: 0
        });
    }
    init();
});
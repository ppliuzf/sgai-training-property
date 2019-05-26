$(function () {
    // 获取列表
    function getList(pageCurr, isFirst) {
        $.getData({
            url: '',
            query: {
                pageNum: pageCurr
            }
        }, function(data) {
            if (data.list && data.list.length) {
                renderList(data);
                isFirst && $.renderPage({
                    count: data.count,
                    jump: function (num) {
                        getList(num);
                    }
                });
            } else {
                renderEmpty();
            }
        });
    }
    // 渲染列表
    function renderList(data) {
        
    }
    // 渲染无数据
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 3
        }));
    }
    function init() {
        // getList(1, true);
    }
    init();
});
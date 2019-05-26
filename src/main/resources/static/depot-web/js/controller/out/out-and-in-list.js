$(function () {
    var currPage = 1;
    // 获取列表
    function getList() {
        var startTime = $.getTimeStamp($.trim($('#time1').val()));
        var endTime = $.getTimeStamp($.trim($('#time2').val()));
        if (startTime && endTime && startTime > endTime) {
            $.toast('开始时间应小于结束时间', {
                time: 3000
            });
            return false;
        } else {
            var query = {},
                body = {};
            query = {
                pageNum: currPage,
                pageSize: 10,
                dtBegin: $('#time1').val() ? $('#time1').val() + ':00' : '',
                dtEnd: $('#time2').val() ? $('#time2').val() + ':00' : ''
            }
            $.getData({
                url: 'depotInManage/searchInOutList',
                query: query
            }, function (data) {
                $.renderPage({
                    count: data.count,
                    curr: currPage,
                    jump: function (n) {
                        currPage = n;
                        getList();
                    }
                });
                if (data.list && data.list.length) {
                    renderList(data.list);
                } else {
                    renderEmpty();
                }
            });
        }


    }
    // 渲染列表
    function renderList(data) {
        $('.js-list').html(template('list/out-and-in-list', {
            items: data
        }));
    }
    // 渲染无数据
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 8
        }));
    }

    function init() {
        getList();
        // 搜索
        $(document).on('click', '.js-search', function () {
            getList();
            currPage = 1; // 搜索的时候记得重置当前页号为１
        });
        $('#time1').datetimepicker({
            format: 'yyyy-mm-dd hh:ii',
            language: 'zh-CN',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 0,
            forceParse: 0
        });
        $('#time2').datetimepicker({
            format: 'yyyy-mm-dd hh:ii',
            language: 'zh-CN',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            forceParse: 0
        });
        $(document).on('click', '.js-date-clean', function () {
            $(this).prev().val('');
        });
    }
    init();
});
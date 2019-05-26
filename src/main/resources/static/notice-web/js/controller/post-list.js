$(function () {
    var currPage = 1;
    // 渲染空列表
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 12
        }));
    }
    // 渲染列表
    function renderList (data) {
        $('.js-list').html(template('list/post-list', {
            items: data
        })); 
    }
    // 收集搜索数据
    function collectSearchData() {
        var rs = {};
        rs.infoUrgency = '';
        rs.flag = '';
        rs.keyword = $.trim($('#keyWords').val());
        rs.status = $('#type').val();
        rs.startCreateTime = $.getTimeStamp($('#fqTimeStart').val());
        rs.endCreateTime = $.getTimeStamp($('#fqTimeEnd').val(), 'end');
        rs.startPublishTime = $.getTimeStamp($('#fbTimeStart').val());
        rs.endPublishTime = $.getTimeStamp($('#fbTimeEnd').val(), 'end');
        if (rs.endCreateTime && rs.startCreateTime && rs.startCreateTime > rs.endCreateTime) {
            $.toast('发起审核结束时间不能小于开始时间', {
                time: 2000
            });
            return;
        }
        if (rs.endPublishTime && rs.startPublishTime && rs.startPublishTime > rs.endPublishTime) {
            $.toast('发布的结束时间不能小于开始时间', {
                time: 2000
            });
            return;
        }
        return rs;
    }
    // 获取列表
    function getList(pageNumber, isFirst){
        var data = collectSearchData();
        if (data) {
            $.getData({
                url: '/noticeInfoISend/cminfoPageList',
                query: {
                    pageNum: pageNumber || 1,
                    pageSize: 10
                },
                body: data
            }, function (data) {
                if (data && data.list && data.list.length) {
                    $('.pages').css('display', 'block');
                    total = data.count;
                    isFirst && $.renderPage({
                        count: total,
                        jump: function (n) {
                            getList(n);
                        }
                    });
                    renderList(data.list);
                } else {
                    $('.pages').css('display', 'none');
                    renderEmpty();
                }
            });
        }
    }
    // 撤回的接口
    function revoke(el, id) {
        $.getData({
            url: '/operation/infoRetract',
            query: {
                id: id
            }
        }, function (data) {
            if (data) {
                $.toast('操作成功', {
                    type:"success",
                    timer: 2000
                });
                el.parent().html('<a class="js-edit underline" data-id="'+ id +'">编辑</a>').parent().find('.js-status').text('已撤回');
                getList(1, true);
            } else {
                $.alert('操作失败');
            }
        });
    }
    // 发布的接口
    function publish(el, id) {
        $.getData({
            url: '/operation/infoPublish',
            query: {
                id: id
            }
        }, function (data) {
            if (data !== 'false') {
                $.toast('操作成功', {
                    type:"success",
                    timer: 2000
                });
                // 注意顺序，如果放在后面，没有el了，就会出问题
                el.parent().parent().find('.js-publish-time').text(data);
                el.parent().html('<a class="js-revoke underline" data-id="'+ id +'">撤回</a>').parent().find('.js-status').text('已发布');
                getList(1, true);
            } else {
                $.alert('操作失败');
            }
        });
    }
    // 置顶/取消置顶
    function top(status, id) {
        $.getData({
            url: '/operation/infoIsTop',
            body: {
                id: id,
                approvalStatus: status
            }
        }, function (data) {
            if (data !== 'false') {
                getList(1, true);
            } else {
                $.alert('操作失败');
            }
        });
    }
    // 时间插件
    $('.form_date2').datetimepicker({
        language: 'zh-CN',
        autoclose: true,
        startView: 2,
        minView: 2
        
        // format: 'yyyy-mm-dd hh:ii',
        // format: 'yyyy-mm-dd',
        // language: 'zh-CN',
        // weekStart: 1,
        // todayBtn:  1,
        // autoclose: 1,
        // todayHighlight: 1,
        // startView: 2,
        // forceParse: 0
    });
    $('#datetimepicker').datetimepicker({
        language: 'zh-CN',
        autoclose: true,
        startView: 2,
        minView: 2
        
        // language: 'zh-CN',
        // weekStart: 1,
        // todayBtn:  1,
		// autoclose: 1,
		// todayHighlight: 1,
		// startView: 2,
		// minView: 2,
        // forceParse: 0

        // format: 'yyyy-mm-dd',
        // language: 'zh-CN',
        // weekStart: 1,
        // todayBtn:  1,
        // autoclose: 1,
        // todayHighlight: 1,
        // startView: 2,
        // forceParse: 0
    });
    $('.form_date3').datetimepicker({
        language: 'zh-CN',
        autoclose: true,
        startView: 2,
        minView: 2
        // language:  'zh-CN',
        // weekStart: 1,
        // todayBtn:  1,
		// autoclose: 1,
		// todayHighlight: 1,
		// startView: 2,
		// minView: 2,
        // forceParse: 0

        // format: 'yyyy-mm-dd',
        // language: 'zh-CN',
        // weekStart: 1,
        // todayBtn:  1,
        // autoclose: 1,
        // todayHighlight: 1,
        // startView: 2,
        // forceParse: 0
    });
    $('.form_date4').datetimepicker({
        // language:  'zh-CN',
        // weekStart: 1,
        // todayBtn:  1,
		// autoclose: 1,
		// todayHighlight: 1,
		// startView: 2,
		// minView: 2,
        // forceParse: 0

        language: 'zh-CN',
        autoclose: true,
        startView: 2,
        minView: 2


        // format: 'yyyy-mm-dd',
        // language: 'zh-CN',
        // weekStart: 1,
        // todayBtn:  1,
        // autoclose: 1,
        // todayHighlight: 1,
        // startView: 2,
        // forceParse: 0
    });
    $('.form_date5').datetimepicker({
        language: 'zh-CN',
        autoclose: true,
        startView: 2,
        minView: 2

        // language:  'zh-CN',
        // weekStart: 1,
        // todayBtn:  1,
		// autoclose: 1,
		// todayHighlight: 1,
		// startView: 2,
		// minView: 2,
        // forceParse: 0

        // format: 'yyyy-mm-dd',
        // language: 'zh-CN',
        // weekStart: 1,
        // todayBtn:  1,
        // autoclose: 1,
        // todayHighlight: 1,
        // startView: 2,
        // forceParse: 0
    });

    // 清空日期选择
    $(document).on('click', '.js-date-clean', function () {
        $(this).prev().val('');
    });
    // 跳转到新增
    $(document).on('click', '.addnotice', function () {
        location.href = './add-notice.html';
    });
    function init() {
        // 搜索、查找
        $(document).on('click', '.js-search', function () {
            getList(1, true);
        });
        // 撤回
        $(document).on('click', '.js-revoke', function () {
            var that = $(this);
            $.bubble({
                el:that,
                // title: '提示',
                msg: '确认要撤回该公告吗？',
                // btn: ['确认', '取消'],
                // size:'sm',
                ok: function () {
                    revoke($(that), $(that).parents('tr').attr('data-id'));
                }
            });
        });
        // 发布
        $(document).on('click', '.js-publish', function () {
            var that = $(this);
            $.bubble({
                el:that,
                // title: '提示',
                msg: '确认要发布该公告吗？',
                // btn: ['确认', '取消'],
                // size:'sm',
                ok: function () {
                    publish($(that), $(that).parents('tr').attr('data-id'));
                }
            });
        });
        // 点击取消置顶
        $(document).on('click', '.js-top', function () {
            top('N', $(this).parents('tr').attr('data-id'));
        });
        // 点击置顶
        $(document).on('click', '.js-cancleTop', function () {
            top('Y', $(this).parents('tr').attr('data-id'));
        });
        getList(1, true);
    }
    init();
});

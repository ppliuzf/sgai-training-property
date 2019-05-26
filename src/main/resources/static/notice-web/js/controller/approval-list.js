$(function () {
    // 渲染空列表
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 8
        }));
    }
    // 渲染列表
    function renderList (data) {
        $('.js-list').html(template('list/approval-list', {
            items: data
        }));
    }
    // 收集搜索数据
    function collectSearchData() {
        var rs = {};
        rs.keyword = $.trim($('#keyWords').val());
        rs.status = $('#type').val();
        rs.startCreateTime = $.getTimeStamp($('#fqTimeStart').val());
        rs.endCreateTime = $.getTimeStamp($('#fqTimeEnd').val(), 'end');
        rs.startPublishTime = $.getTimeStamp($('#fbTimeStart').val());
        rs.endPublishTime = $.getTimeStamp($('#fbTimeEnd').val(), 'end');
        if (rs.endCreateTime && rs.startCreateTime && rs.startCreateTime > rs.endCreateTime) {
            $.toast('发起审核结束时间不能小于开始时间', {
                type:"error",
                timer: 2000
            });
            return;
        }
        if (rs.endPublishTime && rs.startPublishTime && rs.startPublishTime > rs.endPublishTime) {
            $.toast('发布的结束时间不能小于开始时间', {
                type:"error",
                timer: 2000
            });
            return;
        }
        return rs;
    }                          
    // 获取列表
    function getList(pageNum, isFirst) {
        var data = collectSearchData();
        if (data) {
            $.getData({
                url: '/noticeInfoIApprove/cminfoPageList',
                query: {
                    pageNum: pageNum || 1,
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
                    for (var i=0;i<data.list.length;i++){
                        data.list[i].number1 = i + 1;
                    }
                    renderList(data.list);
                } else {
                    $('.pages').css('display', 'none');
                    renderEmpty();
                }
            });
        }
    }
    // 首次点击同意、拒绝的弹窗
    function popMsg(type, id) {
        if (type === 'agree') {
            setTimeout(function(){
                $('.modal-body').css({"height":"200px"})
            },0)
            $.pop({
                title: '同意意见',
                content: "<textarea maxlength='50' placeholder='请输入同意意见（非必填）' id='msg1'></textarea>",
                btn: ['确认', '取消'],
                noIcon:true,
                yes: function () {
                    $('#msg1').hide();
                    approval(type, id);
                }
            });
        } else {
            setTimeout(function(){
                $('.modal-body').css({"height":"200px"})
            },0)
            $.pop({
                title: '拒绝意见',
                content: "<textarea maxlength='50' placeholder='请输入拒绝理由（必填）' id='msg1'></textarea>",
                btn: ['确认', '取消'],
                noIcon:true,
                yes: function () {
                    $('#msg1').hide();
                    approval(type, id);
                }
            });
        }
    }
    // 二次确认同意、拒绝
    function approval(type, id) {
        if (type === 'refuse' && $.trim($('#msg1').val()) === '') {
            $.toast('拒绝理由未填写', {
                timer: 2000
            });
            return;
        }
        $.getData({
            url: '/operation/infoApproval',
            body: {
                approvalOpinition: $.trim($('#msg1').val()),
                approvalStatus: type === 'agree' ? 'Y' : 'N',
                id: id
            }
        }, function (data) {
            if (data) {
                $.toast('操作成功', {
                    type:"success",
                    timer: 2000
                });
                $('.js-list tr[data-id='+ id +'] .js-act').html('已审核'+ (type === 'agree' ? '通过' : '拒绝'));
                $('.js-list tr[data-id='+ id +'] .js-status').text('已审核');
            } else {
                $.toast('操作成功', {
                    type:"false",
                    timer: 2000
                });
            }
        });
    }
    // 时间插件
    $('.form_date2').datetimepicker({
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

        // format: 'yyyy-mm-dd hh:ii',
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

        // language: 'zh-CN',
        // weekStart: 1,
        // todayBtn:  1,
        // autoclose: 1,
        // todayHighlight: 1,
        // startView: 2,
        // minView: 2,
        // forceParse: 0

        // format: 'yyyy-mm-dd hh:ii',
        // language: 'zh-CN',
        // weekStart: 1,
        // todayBtn:  1,
        // autoclose: 1,
        // todayHighlight: 1,
        // startView: 2,
        // forceParse: 0
    });
    $('.form_date4').datetimepicker({
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
        
        // format: 'yyyy-mm-dd hh:ii',
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

        // language: 'zh-CN',
        // weekStart: 1,
        // todayBtn:  1,
        // autoclose: 1,
        // todayHighlight: 1,
        // startView: 2,
        // minView: 2,
        // forceParse: 0

        // format: 'yyyy-mm-dd hh:ii',
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
        location.href = 'add-notice.html';
    });
    function init() {
        // 搜索、查找
        $(document).on('click', '.js-search', function () {
            getList(1, true);
        });
        // 同意、拒绝
        $(document).on('click', '.js-agree', function () {
            console.log($(this).attr('data-id'));
            popMsg('agree', $(this).attr('data-id'));
        });
        $(document).on('click', '.js-refuse', function () {
            popMsg('refuse', $(this).attr('data-id'));
        });
        getList(1, true);
    }
    init();
});

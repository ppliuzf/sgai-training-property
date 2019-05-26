$(function () {
    $(document).off('click');
    $('body').removeClass('loaded').addClass('loaded');
    var id = $.getQueryString('id'), datas = {};
    if (id === undefined || id === 'undefined') {
        layer.alert('信息不存在', function () {
            history.back();
        });
        return false;
    }
    // 获取详情数据
    function getDetail() {
        $.getData({
            url: '/noticeInfoIApprove/cminfoDetail',
            query: {
                id: id
            }
        }, function (data) {
            datas = data;
            renderDetail(data);
        });
    }
    // 渲染详情
    function renderDetail(params) {
        var data = params;
        data.infoContent = params.infoContent.replace(/\r|\n|\r\n|\\r|\\n|\\r\\n/ig, '<br>').replace(/ /ig, '&nbsp;').replace(/^\"|\"$/g, '').replace(/\\|&nbsp;|\\t|<br>/gi, ' ').replace(/ t/gi, '');
        data.infoSummary = data.infoSummary.replace(/</g, '&lt;').replace(/\r|\n|\r\n|\\r|\\n|\\r\\n/ig, '<br>').replace(/\s/g, '&nbsp;');
        $('#approvalListDetail').html(template('list/approval-list-detail', data));
        $('.js-article').html(data.infoContent);
        setTimeout(function () {
            $('.header').addClass('status');
        }, 800);
        setDot();
    }
    // 超过2行...
    function setDot() {
        if (!datas.infoScopeIsAll) {
            var $fuck = $('.fuck-inner'), txt = datas.infoScope.split('、');
            $fuck.text(datas.infoScope);
            if($fuck.length>0){
                if ($fuck[0].scrollHeight > $fuck.height()) {
                    $('.js-show-more').show();
                }
                while ($fuck[0].scrollHeight > $fuck.height()) {
                    txt.pop();
                    $fuck.text(txt.join('、') + '...');
                }
            }
        }
    }
    // 同意、拒绝二次确认
    function popMsg(types, id) {
        if (types === 'agree') {
            setTimeout(function(){
                $('.modal-body').css({"height":"200px"})
            },0)

            $.pop({
                title: '同意意见',
                noIcon: true,
                content: "<textarea maxlength='50' placeholder='请输入同意意见（非必填）' id='msg2'></textarea>",
                btn: ['确认', '取消'],
                yes: function () {
                    $('#msg2').hide();
                    approval(types);
                }
            });
        } else {
            setTimeout(function(){
                $('.modal-body').css({"height":"200px"})
            },0)
            $.pop({
                title: '拒绝意见',
                noIcon: true,
                content: "<textarea maxlength='50' placeholder='请输入拒绝理由（必填）' id='msg2'></textarea>",
                btn: ['确认', '取消'],
                yes: function () {
                    $('#msg2').hide();
                    approval(types);
                }
            });
        }
    }
    // 同意、拒绝
    function approval(types) {
        if (types === 'refuse' && $.trim($('#msg2').val()) === '') {
            $.msg('拒绝理由未填写', {
                time: 2000
            });
            return;
        }
        $.getData({
            url: '/operation/infoApproval',
            body: {
                approvalOpinition: $.trim($('#msg2').val()),
                approvalStatus: types === 'agree' ? 'Y' : 'N',
                id: id
            }
        }, function (data) {
            if (data) {
                $.msg('操作成功', {
                    time: 2000
                }, function () {
                    location.href = './approval-list.html';
                    // getDetail();
                });
            } else {
                $.alert('操作失败');
            }
        });
    }
    function init() {
        getDetail();
        // 同意、拒绝
        $(document).on('click', '.js-agree', function () {

            popMsg('agree', id);
        });
        $(document).on('click', '.js-refuse', function () {
            popMsg('refuse', id);
        });
        // 预览
        $(document).on('click', '.js-preview', function () {
            $.pop({
                title: '预览',
                content: template('common/preview', {
                    items: datas
                }),
                isCancel: false,
                noIcon:true
            });
            $('.js-article').html(datas.infoContent);
        });
        // 人员展开、收缩
        $(document).on('click', '.js-show-more', function () {
            $('.fuck').toggleClass('open');
            setDot();
        });
        var resizeTimer;
        $(window).off('resize').on('resize', function() {
            clearTimeout(resizeTimer);
            resizeTimer = setTimeout(function () {
                setDot();
            }, 100);
        });
    }
    init();
});

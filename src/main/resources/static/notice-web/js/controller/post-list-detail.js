$(function () {
    $(document).off('click');
    $('body').removeClass('loaded').addClass('loaded');
    var id = $.getQueryString('id'), datas = {};
    if (id === undefined || id === 'undefined') {
        $.alert('信息不存在', function () {
            history.back();
        });
        return false;
    }
    // 获取详情数据
    function getDetail() {
        $.getData({
            url: '/noticeInfoISend/cminfoDetail',
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
        $('#postListDetail').html(template('list/post-list-detail', data));
        $('.js-article').html(data.infoContent);
        //setDot();
    }
    // 超过2行...
    // function setDot() {
    //     if (!datas.infoScopeIsAll) {
    //         var $fuck = $('.fuck-inner'), txt = datas.infoScope.split('、');
    //         $fuck.text(datas.infoScope);
    //         if ($fuck[0].scrollHeight > $fuck.height()) {
    //             $('.js-show-more').show();
    //         }
    //         while ($fuck[0].scrollHeight > $fuck.height()) {
    //             txt.pop();
    //             $fuck.text(txt.join('、') + '...');
    //         }
    //     }
    // }
    // 撤回
    function revoke(el) {
        $.getData({
            url: '/operation/infoRetract',
            query: {
                id: id
            }
        }, function (data) {
            if (data) {
                $.msg('操作成功', {
                    time: 2000
                }, function () {
                    el.parent().html('<a class="layui-btn js-edit km-ripple btn btn-primary bianjiMy">编辑</a>&nbsp;&nbsp;<a class="btn btn-default js-preview">预览</a>');
                    el.parent().find('.js-publish').remove();
                    el.remove();
                });
            } else {
                $.alert('操作失败');
            }
        });
    }
    $(document).on('click', '.bianjiMy', function(){
        location.href = './add-notice.html?infoId='+id;
    });
    // 发布
    function publish(el) {
        $.getData({
            url: '/operation/infoPublish',
            query: {
                id: id
            }
        }, function (data) {
            if (data) {
                $.msg('操作成功', {
                    time: 2000
                }, function () {
                    el.parent().html('<a class="layui-btn js-revoke km-ripple btn btn-primary">撤回</a>&nbsp;&nbsp;<a class="btn btn-default js-preview">预览</a>');
                    el.parent().find('.js-revoke').remove();
                    el.remove();
                });
            } else {
                $.alert('操作失败');
            }
        });
    }
    function init() {
        getDetail();
        // 撤回
        $(document).on('click', '.js-revoke', function () {
            var $this = $(this);
            $.pop({
                title: '提示',
                content: '确认要撤回该公告吗？',
                btn: ['确认', '取消'],
                size: 'sm',
                yes: function () {
                    revoke($this);
                }
            });
        });
        // 发布
        $(document).on('click', '.js-publish', function () {
            var $this = $(this);
            $.pop({
                title: '提示',
                content: '确认要发布该公告吗？',
                btn: ['确认', '取消'],
                yes: function () {
                    publish($this);
                }
            });
        });
        // 预览
        $(document).on('click', '.js-preview', function () {
            $.pop({
                title: '预览',
                noIcon:true,
                content: template('common/preview', {
                    items: datas
                }),
                isCancel: false
            });
            $('.js-article').html(datas.infoContent);
        });
        // 人员展开、收缩
        $(document).on('click', '.js-show-more', function () {
            $('.fuck').toggleClass('open');
           // setDot();
        });
        var resizeTimer;
        $(window).off('resize').on('resize', function() {
            clearTimeout(resizeTimer);
            resizeTimer = setTimeout(function () {
             //   setDot();
            }, 100);
        });
    }
    init();
});

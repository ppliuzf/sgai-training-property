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
            url: '/noticeInfoIReceive/cminfoDetail',
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
        data.infoContent = params.infoContent.replace(/\r|\n|\r\n|\\r|\\n|\\r\\n/ig, '<br>').replace(/ /ig, '&nbsp;').replace(/^\"|\"$/g, '').replace(/\\|&nbsp;|\\t|<br>/gi, ' ').replace(/ t/gi,'');
        console.log('2222', data.infoContent);
        data.infoSummary = data.infoSummary.replace(/</g, '&lt;').replace(/\r|\n|\r\n|\\r|\\n|\\r\\n/ig, '<br>').replace(/\s/g, '&nbsp;');
        $('#receiveListDetail').html(template('list/receive-list-detail', data));
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
            // if ($fuck[0].scrollHeight > $fuck.height()) {
            //     $('.js-show-more').show();
            // }
            // while ($fuck[0].scrollHeight > $fuck.height()) {
            //     txt.pop();
            //     $fuck.text(txt.join('、') + '...');
            // }
        }
    }
    function init() {
        getDetail();
        // 返回
        $(document).on('click', '.js-preview', function () {
            window.history.go(-1);
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

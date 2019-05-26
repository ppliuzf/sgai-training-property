$(function () {
    var flag = false;
    var rightDis = 0;
    function init() {
        $('.matter-container').html(template('common/matter-calendar'))  
    };
    // 获取详情
    function getMatterDetail(this_) {
        $('.matter-detail').remove();
        $('.matter-item li').removeClass('hoverColor');
        $(this_).addClass('hoverColor').siblings().removeClass('hoverColor');
        if(flag){
            // $(this_).css({'background':'rgba(211, 235, 199, 1)'}).siblings().css({'background':'#fff'})
            console.log($(this_))
            var title = $(this_).children().eq(0).text(),
                state = $(this_).children().eq(1).text();
            $(this_).parent().parent().parent().append(template('common/matter-detail',{title,state}))
            var width = $('.matter-item').width()+ $('.js-list').width()+3;
            $('.matter-detail').css({'position':'absolute','left':rightDis <= 440 ? -440 : width});
        }else{
            $('.js-list').remove();
            // $(this_).css({'background':'rgba(211, 235, 199, 1)'}).siblings().css({'background':'rgba(237, 249, 234, 1)'});
            console.log($(this_))
            var title = $(this_).find('span').text(),
                state = $(this_).find('a').text();
                $(this_).parent().parent().append(template('common/matter-detail',{title,state}))
                var width = $('.matter-item').width();
                rightDis = document.documentElement.clientWidth - $(this_).parent().offset().left - width;
                $(this_).parent().parent().find('.matter-detail').css('left', rightDis <= 220 ? -220 : width);
                console.log(width);
                
        }
        
        console.log(flag)
        flag = false;
    };
    //展开列表
    function getMatterList(this_) {
       
        var index = $(this_).parent().parent().parent().parent().parent().data('index');
        $('.js-list').remove();
        $(this_).parent().parent().parent().append(template('common/matter-list'))
        var width = $('.matter-item').width();
        rightDis = document.documentElement.clientWidth - $(this_).parent().parent().offset().left - width;
        $('.matter-case').css('left', rightDis <= 440 ? -220 : width);
        $('.matter-detail').remove();
        // debugger;
        console.log(document.documentElement.clientWidth - $(this_).parent().parent().offset().left - width);
    };
    $(document).on('click', '.matter-item li', function () {
        getMatterDetail(this)  
    });
    $(document).on('click', '.matter-detail .delete', function () {
        $('.matter-detail').remove();
    });
    $(document).on('click', '.js-list .delete', function () {
        $('.matter-detail').remove();
        $(this).parent().parent().parent().parent().remove();
    });
    $(document).on('click', '.matter-all a', function () {
        getMatterList(this)
    });
    $(document).on('click', '.js-list tbody tr', function () {
        flag = true;
        getMatterDetail(this)
    });
    init();
});
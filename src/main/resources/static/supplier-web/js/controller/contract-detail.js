$(function () {
    var id = $.getQueryString('id');
    if (!id) {
        $.alert('记录不存在', function () {
            history.back();
        });
        return false;
    } else {
        contractDetail(id);
    }
    // 获取编辑数据
    function contractDetail(id) {
        $.getData({
            url: '/htContract/getById',
            query: { id: id }
        }, function (data) {
            if (data) {
                data.status !== 1 ? $('.status-text').text('合同详情-已签约') : $('.status-text').text('合同详情-未签约');
                $('.js-detail').html(template('contract-detail', data))
            }
        })
    }
    function init(){
        $(document).on('click', '.js-ToFile', function () {
            var fileUrl = $(this).data('url')
            var fileName = $(this).data('name')
            window.location.href = serverUrl+'/uploadDown/downLoadFile?filePath=http://220.194.33.252:20001'+fileUrl+'&filename='+fileName+'';
        })
        $(document).on('click', '.btn-default', function () {
            history.back()
        })
    }
    init()
});


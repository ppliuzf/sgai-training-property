$(function () {
    function upload() {
        var ld = $.loading();
        var file = $('#upload')[0].files[0],
            fd = new FormData();
        fd.append('file', file);
        $.ajax({
            url: serverUrl + '/common/uploadCustomList',
            type: 'post',
            headers: {
                // accessToken: $.getCookie('token')
                'accessToken': 'PC_' + localStorage.getItem('token') || $.getCookie($.getCookie(projectName + '-code'))
            },
            data: fd,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                if (data.code === '0') {
                    if (data.data) {
                        $.toast('导入成功',function () {  
                            history.go(-1)
                        })
                        $('#filename').val('');
                        ld.hide();
                    }else {
                        ld.hide();
                        $('#filename').val('');
                        $('.js-save').addClass('disabled');
                        $.alert("导入失败，请导入正确文件");
                    }
                } else {
                    $('#filename').val('');
                    $('.js-save').addClass('disabled');
                    ld.hide();
                    $.alert(data.message);
                }
            },
            error: function () {
                $.alert('服务器连接失败，请稍候再试');
            },
            complete: function () {
                
            }
        });
    }
    function init() {
        $(document).on('click', '.js-file-picker', function () {
            $('#upload').click();
            return false;
        });
        $('#upload').change(function() {
            var file = $('#upload')[0].files[0];
            $('#filename').val(file.name);
            $('.js-save').removeClass('disabled');
        });
        $(document).on('click', '.js-save', function () {
            if ($(this).hasClass('disabled')) {
                return false;
            }
            
            upload();
            // setTimeout(function () {
            //     ld.hide();
            //     $.alert('导入失败');
            // }, Math.random() * 7000 + 3000);
        });
        $(document).on('click', '.js-cancel', function() {
            location.href = './archives-list.html';
        });
        $(document).on('click', '.js-download', function() {
            location.href = serverUrl + '/uploadDown/downloadTemplate';
        });
    }
    init();
});
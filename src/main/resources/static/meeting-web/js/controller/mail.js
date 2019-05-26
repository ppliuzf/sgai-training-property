$(function () {
    function changeStatus(param) {
        $.getData({
            url: '/common/openOrCloseEmail',
            body: param
        }, function(data) {
            $('.bottom-btn').removeClass('show').addClass('hidden');
        });
    }
    //测试邮件
    function testData(param) {
        $.getData({
            url: '/common/testSendEmail',
            body: param
        }, function(data) {
            $('.bottom-btn').removeClass('show').addClass('hidden');
            if (data) {
                $('.form-group input').each(function () {
                    $(this).attr('disabled', '');
                });
                // $.msg('测试成功',{time: 2000});
                $.toast("测试成功",{
                    type: 'success'
                });
            } else {
                $.toast("测试失败",{
                    type: 'error'
                });
                // $.msg('测试失败',{time: 2000});
            }
            // renderContent(data);
        });
    }
    //保存数据
    function saveData(param) {
        $.getData({
            url: '/common/updateEmailConfigInfo',
            body: param
        }, function(data) {
            if (data) {
                // $.msg('保存成功',{time: 2000});
                $.toast("保存成功",{
                    type: 'success'
                });
                getData();
                $('.bottom-btn').removeClass('show').addClass('hidden');
                $('.form-group input').each(function () {
                    $(this).attr('disabled', false);
                });
            }
        });
    }
    // 渲染页面
    function renderContent(data) {
        $('.js-content').html(template('plan/mail',data));
        $('input[type=checkbox]').bootstrapSwitch();
    }
    // 获取数据
    function getData() {
        $.getData({
            url: '/common/getEmailConfigInfo'
        }, function(data) {
            // console.log(data);
            renderContent(data);
        });
    }
    function collectData() {
        var param = {};
        param.isSend = $('.switch input').is(':checked') ? 1 : 0;
        param.mcAccount = $('.mail-name input').val();
        param.mcPassword = $('.password input').val();
        param.mcIp = $('.server input').val();
        param.mcPort = $('.port input').val();
        param.mcEmailName = $('.send-name input').val();
        param.mcId = "";
        if (!param.mcAccount || param.mcAccount == '') {
            // $.msg('邮箱账号不能为空',{time: 2000});
            $.toast("邮箱账号不能为空",{
                type: 'error'
            });
            return false;
        }
        if (!param.mcPassword || param.mcPassword == '') {
            // $.msg('密码不能为空',{time: 2000});
            $.toast("密码不能为空",{
                type: 'error'
            });
            return false;
        }
        if (!param.mcIp || param.mcIp == '') {
            // $.msg('SMTP服务器不能为空',{time: 2000});
            $.toast("SMTP服务器不能为空",{
                type: 'error'
            });
            return false;
        }
        if (!param.mcPort || param.mcPort == '') {
            // $.msg('端口不能为空',{time: 2000});
            $.toast("端口不能为空",{
                type: 'error'
            });
            return false;
        }
        if (!param.mcEmailName || param.mcEmailName == '') {
            // $.msg('发信名称',{time: 2000});
            $.toast("发信名称",{
                type: 'error'
            });
            return false;
        }
        return param;
    }
    function init() {
        getData();
        $(document).on('click', '.js-edit', function () {
            $('.form-group input').attr('disabled', false);
            $('.bottom-btn').removeClass('hidden').addClass('show');
        });
        $(document).on('click', '.js-cancel', function () {
            getData();
        });
        $('.setting-mail-page .header input[type=checkbox]').bootstrapSwitch({
            onSwitchChange: function () {
                var data = collectData();
                if (data) {
                    changeStatus(data);
                }
            }
        });
        $(document).on('click', '.js-test', function () {
            var data = collectData();
            if (data) {
                testData(data);
            }
        });
        $(document).on('click', '.js-save', function () {
            var data = collectData();
            saveData(data);
        });
    }
    init();
});
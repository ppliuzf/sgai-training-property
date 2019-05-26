$(function () {
    function saveData(param) {
        $.getData({
            url: '/roomResource/saveRoomPosition',
            body: param
        }, function(data) {
            $.toast("操作成功",{
                type: 'success'
            });
            setTimeout(" history.back()", 2000);
            // $.msg('操作成功', function() {
            //     history.back();
            // });
        });
    }
    function collectData() {
        var param ={};
        param.rpPositionName = $.trim($('#meeting-location').val());
        param.rpPositionDesc = $.trim($('#meeting-text').val());
        if (param.rpPositionName === '') {
            $.alert('位置名称不能为空');
            return false;
        }
        return param;
    }
    function init() {
        $(document).on('click', '.js-save', function () {
            var data = collectData();
            if (data) {
                saveData(data);
            }
        });
        $(document).on('click', '.js-cancel', function () {
            window.history.back();
        });
        $.counter({
            el: '#meeting-text'
        });
    }
    init();
});
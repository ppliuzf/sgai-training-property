$(function () {
    let id = $.getQueryString("id");
    function saveData(param) {
        let url = id ? '/roomResource/updateRoomType' : '/roomResource/saveRoomType';
        $.getData({
            url,
            body: param
        }, function (data) {
            if (data) {
                $.toast("操作成功",{
                    type: 'success'
                }, function () {
                    history.back()
                });
            }else {
                $.toast("该类型名称已存在",{
                    type: 'error'
                }, function () {
                    
                });
                
            }
            
            // setTimeout(" history.back()", 2000);
            // $.msg('操作成功', function () {
            //     history.back();
            // });
        });
    }
    function collectData() {
        let param = {};
        param.id = id ? $.trim($('#meeting-name').data("id")) : "";
        param.rtName = $.trim($('#meeting-name').val()); //类型主题名称
        param.rtType = $.trim($("#meeting-type").find("option:selected").data("id"));//类型
        param.rtTypeName = $.trim($("#meeting-type").find("option:selected").val());//选择类型名称
        param.rtTypeDesc = $.trim($('#meeting-text').val()); //类型描述
        if (param.rtName === '') {
            $.alert('类型名称不能为空');
            return false;
        }
        return param;
    }
    //获取类型详情
    function getType() {
        $.getData({
            url: '/roomResource/getRoomTypeDetail',
            query: {
                id
            }
        }, function (data) {
            console.log(data)
            if (data) {
                $('#meeting-name').attr("data-id", data.id);
                $('#meeting-name').val(data.rtName);
                $('#meeting-text').val(data.rtTypeDesc);
                $.counter({
                    el: '#meeting-text'
                });
                $("#meeting-type").find("option[data-id='" + data.rtType + "']").attr("selected", true);
            }
        });
    }

    function init() {
        if (id) {
            getType();
            $(".js-name").html("编辑类型")
        } else {
            $(".js-name").html("新建类型")
        }
        //点击保存
        $(document).on('click', '.js-save', function () {
            var data = collectData();
            if (data) {
                saveData(data);
            }
        });
        //点击返回
        $(document).on('click', '.js-cancel', function () {
            window.history.back();
        });
        //渲染文本框
        $.counter({
            el: '#meeting-text'
        });
    }
    init();
});
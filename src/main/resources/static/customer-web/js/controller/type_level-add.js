$(function () {
    var id = $.getQueryString('id'),
        type = location.pathname.indexOf('type-add') > -1 ? '类型' : '级别';
    // 获取数据
    function getDetail() {
        var url = type === '类型' ? '/customer/getCustomTypeInfoById' : '/customer/getCustomLevelInfoById',
            query = type === '类型' ? {ctId: id} : {clId: id};
        $.getData({
            url: type === '类型' ? '/customer/getCustomTypeInfoById' : '/customer/getCustomLevelInfoById',
            query: query
        }, function(data) {
            if (data) {
                $('.container-fluid').html(template('type_level-add',{
                    type: '编辑',
                    title: type,
                    item: data
                }));
                $.counter({
                    el: '#desc'
                });
            } else {
                $.alert('数据不存在', function() {
                    history.back();
                });
            }
        });
    }
    // 收集数据
    function collectData() {
        if ($.trim($('#name').val()) === '') {
            $.alert('请输入'+ type +'名称', function() {
                $('#name').select();
            });
            return false;
        }
        var rs = {}, _type;
        if (type === '类型') {
            id && (rs.ctId = id);
            rs.typeClass = 1;
            _type = 'type';
        } else {
            id && (rs.clId = id);
            _type = 'level';
        }
        rs[_type +'Name'] = $.trim($('#name').val());
        rs[_type +'Desc'] = $.trim($('#desc').val());
        console.log(rs);
        return rs;
    }
    // 保存数据
    function saveData(params) {
        var url = '/customer/saveCustomTypeInfo';
        if (type === '级别') {
            url = '/customer/saveCustomLevelInfo';
        }
        if (id) {
            url = url.replace('save', 'update') + 'ById';
        }
        $.getData({
            url: url,
            body: params
        }, function(data) {
            if (data) {
                // $.msg('操作成功', function() {
                //     history.back();
                //     // location.href = type === '类型' ? './type-list.html' : './level-list.html';
                // });
                $.toast('操作成功', {
                    type: 'success',
                }, function(){
                    history.back();
                    // location.href = type === '类型' ? './type-list.html' : './level-list.html';
                });
            }
        });
    }
    function init() {
        if (id) {
            getDetail();
        } else {
            $('.container-fluid').html(template('type_level-add',{
                type: '新建',
                title: type
            }));
            $.counter({
                el: '#desc'
            });
        }
        // 保存
        $(document).on('click', '.js-save', function () {
            var data = collectData();
            if (data) {
                saveData(data);
            }
        });
        // 取消
        $(document).on('click', '.js-cancel', function () {
            location.href = './'+ (type === '类型' ? 'type' : 'level') +'-list.html';
        });
    }
    init();
});
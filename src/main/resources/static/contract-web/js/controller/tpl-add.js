$(function () {
    var id = $.getQueryString('id');
    id ? $('#headTxt').text('编辑模板') : $('#Headtxt').text('上传模板');

    // 获取类型
    function getType(eid) {
        $.getData({
            url: '/type/findList'
        }, function (data) {
            if (data && data.length) {
                var _data = [];
                for (var i in data) {
                    _data.push({
                        value: data[i].id,
                        title: data[i].typeName
                    });
                }
                $('#type').html(template('common/select', {
                    items: _data,
                    eid: eid
                }));
            }
        });
    }
    // 获取数据
    function getDetail(id) {
        $.getData({
            url: '/htTemplet/getHtTempletById',
            query: {
                id: id
            }
        }, function (data) {
            if (data) {
                $('.js-main').html(template('tpl-add', {
                    item: data
                }));
                $.counter({
                    el: '#desc'
                });
                getType(data.typeId);
                var files = [{
                    fileUrl: data.url,
                    fileName: data.fileName
                }]
                $.uploadFile({
                    el: '.js-uploader-file',
                    isRequire: true,
                    maxLength: 1,
                    btnTips: '请上传合同模板',
                    default: data.url ? files : []
                });
            } else {
                $.alert('数据不存在', function () {
                    history.back();
                });
            }
        });
    }
    // 收集数据
    function collectData() {
        var rs = {};
        if ($.trim($('#name').val()) === '') {
            $.alert('请输入模板名称', function () {
                $('#name').select();
            });
            return false;
        }
        rs.id = id ? id : '';
        rs.name = $.trim($('#name').val());
        rs.typeId = $.trim($('#type').val());
        // rs.typeName = $('#type').find('option:selected').text();
        rs.description = $.trim($('#desc').val());
        rs.url = $('.js-uploader-file .upload-file-items .upload-item').data('url');
        console.log(rs.url);
        if (rs.url === undefined) {
            $.alert('请上传合同模板', function () {
                $('#name').select();
            });
            return false;
        }
        rs.fileName = $('.js-uploader-file .upload-file-items .upload-item').data('name');
        return rs;
    }
    // 保存数据
    function saveData(params) {
        var url = '/htTemplet/saveTemplet';
        if (id) {
            url = url.replace('save', 'updateHt') + 'ById';
        }
        $.getData({
            url: url,
            body: params
        }, function (res) {
            var codeFun = {
                '0': function () {
                    $.toast(id ? '编辑成功' : '保存成功');
                    setTimeout(function(){
                        location.href = './tpl-list.html';
                    },2000)
                },
                '-1': function () {
                    $.alert('保存失败')
                },
                '3': function () {
                    $.alert(res.message);
                }
            }
            codeFun[res.code]();
        });
    }
    function init() {
        if (id) {
            getDetail(id);
        } else {
            $('.js-main').html(template('tpl-add'));
            getType();
            $.counter({
                el: '#desc'
            });
            $.uploadFile({
                el: '.js-uploader-file',
                isRequire: true,
                maxLength: 1,
                btnTips: '请上传合同模板',
            });
        }
        // 保存
        $(document).on('click', '.js-save', function () {
            var data = collectData();
            if (data) {
                saveData(data);
            }
        });
        $(document).on('click', '.js-cancel', function () {
            history.back();
        });
    }
    init();
});
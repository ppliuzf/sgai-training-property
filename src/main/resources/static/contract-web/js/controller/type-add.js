$(function () {
    var id = $.getQueryString('id');
    id ? $('#headTxt').text('编辑类型') : $('#Headtxt').text('新增类型');
    // 获取数据
    function getDetail(id) {
        $.getData({
            url: '/type/getById',
            query: {
                id: id
            }
        }, function (data) {
            if (data) {
                $('.js-main').html(template('type-add', {
                    item: data
                }));
                $.counter({
                    el: '#desc'
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
            $.alert('请输入类型名称', function () {
                $('#name').select();
            });
            return false;
        }
        if ($.trim($('#protocol').val()) === '') {
            $.alert('请输入合同规约', function () {
                $('#protocol').select();
            });
            return false;
        }
        var reg = /(^[1-9]{1}[0-9]*$)|(^[0-9]*\.[0-9]{2}$)/;
        if (!reg.test($.trim($('#protocol').val()))) {
            $.alert('合同规约请输入两位小数', function () {
                $('#protocol').select();
            });
            return false;
        }
        if ($.trim($('#protocol').val()) < 1 || $.trim($('#protocol').val()) > 99999.99) {
            $.alert('请输入1~99999.99之间的数字', function () {
                $('#name').select();
            });
            return false;
        }
        rs.id = id ? id : '';
        var protocol = +$.trim($('#protocol').val());
        rs.limitValue = protocol.toFixed(2);
        rs.typeName = $.trim($('#name').val());
        rs.typeDescription = $.trim($('#desc').val());
        return rs;
    }
    // 保存数据
    function saveData(params) {
        var url = '/type/save';
        if (id) {
            url = url.replace('save', 'update') + 'ById';
        }
        $.getData({
            url: url,
            body: params
        }, function (res) {
            var codeFun = {
                '0': function () {
                    $.toast(id ? '编辑成功' : '保存成功');
                    setTimeout(function(){
                        location.href = './type-list.html';
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
    function init () {
        if (id) {
            getDetail(id);
        } else {
            $('.js-main').html(template('type-add'));
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
        $(document).on('click', '.js-cancel', function () {
            history.back();
        });
    }
    init();
});
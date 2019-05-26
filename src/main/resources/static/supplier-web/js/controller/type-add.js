$(function () {
    var id = $.getQueryString('id');
    // 获取数据
    function getDetail(id) {
        $.getData({
            url: '/type/getGysTypeById',
            query: {
                id: id
            },
        }, function (data) {
            if (data) {
                 $('#HeadTxt').text('编辑类型'+'-'+data.name)
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
        rs.id = id ? id : '';
        rs.typeName = $.trim($('#name').val());
        rs.typeDescription = $.trim($('#desc').val());
        return rs;
    }
    // 保存数据
    function saveData(data) {
        if (id) {
            $.getData({
                url: '/type/updateGysType',
                query:{
                    id:id
                },
                body: {
                    description:data.typeDescription,
                    name:data.typeName
                }
            }, function (res) {
                if (res) {
                    $.toast(res.message);
                    if(res.code === '0'){
                        setTimeout(function(){
                            location.href = './type-list.html';
                        },2000)
                    }
                }
            });
        }else{
            $.getData({
                url:'/type/saveGysType',
                body: {
                    description:data.typeDescription,
                    name:data.typeName
                }
            }, function (res) {
                if (res) {
                    $.toast(res.message);
                    if(res.code === '0'){
                        setTimeout(function(){
                            location.href = './type-list.html';
                        },2000)
                    }
                }
            });
        }
    }
    function init () {
        if (id) {
            getDetail(id);
        } else {
            $('#headTxt').text('新建类型');
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

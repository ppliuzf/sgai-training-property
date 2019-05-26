$(function () {
    var id = $.getQueryString('id');
    // 获取数据
    function getDetail(id) {
        $.getData({
            url: '/level/getGysLevelById',
            query: {
                id: id
            },
        }, function (data) {
            if (data) {
                $('#HeadTxt').text('编辑等级'+'-'+data.name)
                $('.js-main').html(template('grade-add', {
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
            $.alert('请输入等级名称', function () {
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
                url: '/level/updateGysLevel',
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
                            location.href = './grade-list.html';
                        },2000)
                    }
                }
            });
        }else{
            $.getData({
                url:'/level/saveGysLevel',
                body: {
                    description:data.typeDescription,
                    name:data.typeName
                }
            }, function (res) {
                if (res) {
                    $.toast(res.message);
                    if(res.code === '0'){
                        setTimeout(function(){
                            location.href = './grade-list.html';
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
            $('#headTxt').text('新建等级');
            $('.js-main').html(template('grade-add'));
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

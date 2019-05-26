$(function () {
    var id = $.getQueryString('id');
    // 获取数据
    function getDetail(id) {
        $.getData({
            url: '/content/getGysContentById',
            query: {
                id: id
            },
        }, function (data) {
            if (data) {
                $('#HeadTxt').text('编辑供应商服务内容'+'-'+data.name)
                $('.js-main').html(template('service-add', {
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
            $.alert('请输入供应商服务内容名称', function () {
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
                url: '/content/updateGysContent',
                query:{
                    id:id
                },
                body: {
                    description:data.typeDescription,
                    name:data.typeName
                }
            }, function (res) {
                if (res) {
                    $.toast(res.message, function () {
                        if(res.code === '0'){
                            // location.href = './service-list.html';
                        }
                    });
                    setTimeout(function(){
                        window.location.replace('./service-list.html');
                    },2000)
                }
            });
        }else{
            $.getData({
                url:'/content/saveGysContent',
                body: {
                    description:data.typeDescription,
                    name:data.typeName
                }
            }, function (res) {
                if (res) {
                    $.toast(res.message);
                    if(res.code === '0' ){
                        // location.href = './service-list.html';
                        setTimeout(function(){
                            window.location.replace('./service-list.html');
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
            $('#headTxt').text('新建供应商服务内容');
            $('.js-main').html(template('service-add'));
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


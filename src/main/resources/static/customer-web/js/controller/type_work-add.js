$(function () {

    var id = $.getQueryString('id');

    // 获取数据
    function getDetail() {
        $.getData({
            url: "/workorderCost/getWorkorderCostById",
            query: {
                id: id
            }
        }, function (data) {
            if (data) {
                $('.container-fluid').html(template('type_work-add', {
                    type: '编辑',
                    title: '工单',
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
        let val = $('#name').val(),
            money = $('#money').val(),
            adress = $('#adress').val(),
            texts = $("#desc").val();

        if ($.trim(val) === '') {
            $.alert('请输入费用名称', function () {
                $('#name').select();
            });
            return false;
        } else if ($.trim(money) === '') {
            $.alert('请输入单价', function () {
                $('#money').select();
            });
            return false;
        } else if ($.trim(adress) === '') {
            $.alert('请输入单位', function () {
                $('#adress').select();
            });
            return false;
        }
        money = Number($('#money').val());
        return {
            "wdCostName": val,
            "wdDesc": texts,
            "wdPrice": money,
            "wdUnit": adress
        };
    }
    // 保存数据
    function saveData(params) {
        var url = '/workorderCost/saveWorkorderCost';
        if (id) {
            url = url.replace('save', 'update') + 'ById';
            params["id"] = id;
        }
        $.getData({
            url: url,
            body: params
        }, function (data) {
            if (data) {
                // $.msg('操作成功', function () {
                //     history.back();
                // });
                $.toast('操作成功', {
                    type: 'success',
                }, function(){
                    history.back();
                });
            }
        });
    }
    function init() {
        if (id) {
            getDetail();
        } else {
            $('.container-fluid').html(template('type_work-add', {
                type: '新建',
                title: '工单'
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
            location.href = './work-list.html';
        });
    }
    init();
});
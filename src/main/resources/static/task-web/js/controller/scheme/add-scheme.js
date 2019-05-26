$(function () {
    var type = $.getQueryString('type');
    // 获取专业范畴
    function getCategoryList() {
        $.getData({
            url: '/category/getAllCategory',
            query: {
				type: type ? type : 0
			}
        }, function (data) {
            if (data && data.length) {
                var _data = [];
                for (var i in data) {
                    _data.push({
                        title: data[i].pcName,
                        value: data[i].id
                    });
                }
                renderCategory(_data);
            }
        });
    }

    // 渲染专业范畴
    function renderCategory(data) {
        $('.select-pc').html(template('common/select', {items: data}));
    }
    // 渲染表单
    function renderList(data) {
        $.counter({el: '#description', max: 100});
    }

    // 收集数据
    function collectData() {
        var rs = {};
        if ($.trim($('#name').val()) === '') {
            $.alert('请输入任务模板名称', {time: 3000});
            return false;
        }
        rs.name = $.trim($('#name').val()); // 名称
        // 获取专业范畴
        if ($.trim($('#category').val())) {
            rs.pcId = $('#category').val();
        } else {
            $.alert('请选择专业范畴', {time: 3000});
            return false;
        }
        rs.description = $.trim($('#description').val()) ? $.trim($('#description').val()) : '无备注'; // 描述
        return rs;
    }

    function init() {
        getCategoryList();
        renderList();
        //保存
        $(document).on('click', '.js-save', function () {
        	var params = collectData();
        	if (!params) {
        		return
            }
            if (type) {
				params.type = type;
			}
            $.getData({
                url: '/planPc/addPlan',
                body: params,
				query: params
            }, function (data) {
                if (data) {
                    // $.msg("保存成功", {time: 2000}, function () {
                    //     history.back();
                    // })
                    $.toast('保存成功', {
                        type: 'success'
                    });
                    setTimeout(() => {
                        history.back();
                    }, 2000);
                } else {
                    $.alert("保存失败!")
                }
            });
        });
        // 保存并配置
        $(document).on('click', '.js-save-relate', function () {
        	var params = collectData();
        	if (!params) {
        		return
			}
            $.getData({
                url: '/planPc/addPlan',
                body: params,
				query: params
            }, function (data) {
                if (data) {
                    // window.location.href = window.location.href.replace(/add-scheme.html/, 'set-scheme.html?id=') + data;
                    window.location.href = 'set-scheme.html?id=' + data;
                } else {
                    $.alert("保存失败!")
                }
            });
        });
        // 取消
        $(document).on('click', '.js-cancel', function () {
            history.back();
        });
    }
    init();
});
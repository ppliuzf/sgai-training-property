
$(function () {
    var currPage = 1;
    // var selectAll = $.selectAll();
    // var a = $.selectAll();
    // 获取类型
    function getType() {
        $.getData({
            url: '/type/findAllTypeList'
        }, function (data) {
            if (data&& data.length) {
                var _data = [{
                    value: '0',
                    title: '全部'
                }];
                for (var i in data) {
                    _data.push({
                        value: data[i].id,
                        title: data[i].name
                    });
                }
                $('#CL-type').html(template('common/select', {
                    items: _data,
                }));
            }
        });
    }
    //获取内容类型
    function getService() {
        $.getData({
            url: '/content/findAllContentList'
        }, function (data) {
            if (data && data.length) {
                var _data = [{
                    value: '0',
                    title: '全部'
                }];
                for (var i in data) {
                    _data.push({
                        value: data[i].id,
                        title: data[i].name
                    });
                };
                $('#FW-type').html(template('common/select', {
                    items: _data,
                }))
            }
        });
    }
    //获取等级
    function getGrade() {
        $.getData({
            url: '/level/findAllLevelList'
        }, function (data) {
            if (data && data.length) {
                var _data = [
                    {
                        value: '0',
                        title: '全部'
                    },{
                        value: '1',
                        title: '未评级'
                }];
                for (var i in data) {
                    _data.push({
                        value: data[i].id,
                        title: data[i].name
                    });
                };
                $('#grade').html(template('common/select', {
                    items: _data,
                }))
            }
        });
    }
    //获取数据
    function collectData(){
        var rs = {};
        rs.CL_type = $('#CL-type').val();
        rs.FW_type = $('#FW-type').val();
        rs.keywords = $.trim($('#keywords').val());
        rs.grade =$('#grade').val();
        return rs;
    }
    // 获取列表
    function getList() {
        var data = collectData()
        $.getData({
            url: '/supplier/supplierPageList',
            query: {
                pageNum: currPage,
                pageSize: 10
            },
            body:{
                levelId:data.grade,
                contentId: data.FW_type,
                keyWord: data.keywords,
                typeId: data.CL_type
            }
        }, function (data) {
            $.renderPage({
                count: data.count,
                curr: currPage,
                jump: function (n) {
                    currPage = n;
                    getList(n);
                }
            });
            if (data.list && data.list.length) {
                for ( var i = 0; i < data.list.length; i++) {
                    data.list[i].index = i + 1;
                }
                renderList(data.list);
            } else {
                renderEmpty();
            }
        });
    }
    // 渲染数据为空
    function renderEmpty(text) {
        $('.js-list').html(template('common/record-empty', {
            colspan: 12,
            text: '暂无数据' || text
        }));
    }
    // 渲染列表
    function renderList(data) {
        $('.js-list').html(template('archive-list', {
            items: data
        }));
        var a = $.selectAll();
        a.reset();
        //切换
        $('[name="status"]').bootstrapSwitch({
            // onText:"启动",
            // offText:"停止",
            // onColor:"primary",
            // offColor:"default",
            // size:"mini",
            size: 'mini',
            onText: '启用',
            offText: "禁用",
            onSwitchChange:function(){
                isEnabled($(this).data('id'), $(this).is(':checked'));
            }
        })
    }
    //是否启用
    function isEnabled(id,isOn){
        $.getData({
            url: '/supplier/isEnabled',
            query: {
                id: id,
                statusId:isOn ? 1 : 0
            },
        }, function () {
        });
    }
    // 删除
    function delItem(el, id) {
        $.getData({
            url: '/supplier/deleteById',
            query: {
                id: id
            }
        }, function (data) {
            if (data.code === '0') {
                // $.msg('操作成功');
                 el.parent().parent().remove();
                if (currPage !== 1 && !$('.js-list tr').length) {
                    currPage -= 1;
                }
                getList();
            } else {
                $.alert('删除失败');
            }
        });
    }
    //全部删除
    function delItemS(idS) {
        $.getData({
            url: '/supplier/deleteByIds',
            query: {
                ids:idS
            }
        }, function (data) {
            if (data.code === '0') {
                // $.msg('操作成功');
                if (currPage !== 1 && !$('.js-list tr').length) {
                    currPage -= 1;
                }
                getList();
            } else {
                $.alert('删除失败');
            }
        });
    }
    function init() {
        getType()
        getList();
        getService()
        getGrade()
        // 搜索
        $(document).on('click', '.js-search', function () {
            currPage = 1;
            // var a = $.selectAll();
            // a.reset();
            getList();
        });
        // 删除
        $(document).on('click', '.js-del', function () {
            var $this = $(this),
                id = $(this).data('id');
            $.bubble({
                el: $(this),
                msg: '确定删除当前供应商？',
                ok: function () {
                    $.toast('删除成功', {
                        type: 'success'
                    });
                    delItem($this, id);
                },
                cancel: function () {
                    // $.toast('您已取消删除');
                }
            });
        });
        $(document).on('click', '.btn-add', function () {
            window.location.href = "./archive-add.html"
        });
        //全部删除
        $(document).on('click', '.js-del-multi', function () {
            if ($(this).hasClass('disabled')) {
                return;
            }
            var Arrid = [];
            var ch = document.getElementsByName("choose");
            for(var i = 0; i < ch.length; i++){
                if(ch[i].checked) {
                    Arrid.push(ch[i].getAttribute('id'))
                }
            }
            // $.confirm('确定删除当前所有选中供应商？', function () {
            //     setTimeout(function () {
            //         delItemS(Arrid.join(','))
            //         document.getElementsByName("allChecked")[0].checked =false
            //     }, 500);
            // });
            $.bubble({
                el: $(this),
                msg: '确定删除当前所有选中供应商？',
                ok: function () {
                    $.toast('删除成功', {
                        type: 'success'
                    });
                    delItemS(Arrid.join(','));
                    document.getElementsByName("allChecked")[0].checked =false
                },
                cancel: function () {
                    // $.toast('您已取消删除');
                }
            });
        });
    }
    init();
});
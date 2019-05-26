$(function () {
    var schemeId = $.getQueryString('id'); // 方案ID
    var selGroupId, selGroupName, defaultSelGroup; // 当下拉框不切换时，存第一个组
    if (schemeId === undefined || schemeId === 'undefined') {
        $.alert('任务专业不存在！', function () {
            history.back();
        });
        return false;
    }
    // 获取数据
    function getList() {
        $.getData({
            query: {
                planId: schemeId
            },
            url: '/planPc/grouping'
        }, function (data) {
            for (var i = 0; i<data.length; i++) {
                data[i].index = i + 1;
                data[i].nofz = data.length - 1;
                if (data[i].list&& data[i].list.length > 0) {
                    data[i].list.forEach(function (item, index) {
                        item.num = index + 1;
                    })
                }
            }
            $renderList(data);
            $.kmSort({
                sortArea: '.p-sort',
                sortItem: '.p-item', // '.sort-'+ data[p].id,
                upEl: '.js-up',
                downEl: '.js-down',
                afterSort: function (before, after) {
                    var bef = before.substring(1, before.length);
                    var aft = after.substring(1, after.length);
                    groupUpOrDown(bef, aft);
                }
            });
            $.kmSort({
                sortArea: '.s-sort',
                sortItem: '.s-item', // '.sort-'+ data[p].id,
                upEl: '.js-s-up',
                downEl: '.js-s-down',
                afterSort: function (before, after) {
                    var bef = before.substring(1, before.length);
                    var aft = after.substring(1, after.length);
                    schemeItemMove(bef, aft);
                }
            });
        });
    }
    // 渲染数据
    function $renderList(data) {
        $('.det-group-list').html(template('scheme/det-group-list', {items: data}));
    }
    // 新建组
    function addGroup() {
        var groupName = $.trim($('#groupName').val());
        if (groupName === '') {
            $.alert('请输入检验方案名称', {time: 3000});
            return false;
        }
        $.getData({
            url: '/planPc/addGroup',
            query: {
                planId: schemeId,
                name: groupName
            }
        }, function (data) {
            if (data) {
                $.msg('操作成功！', {
                    time: 2000
                });
                getList();
                // 隐藏弹框
                $('#createGroup').modal('hide');
                $('#groupName').val('');
            } else {
                $.alert('操作失败！');
            }
        });
    }
    // 删除组
    function deleteGroup(id) {
        $.getData({
            url: '/planPc/deleteGroup',
            query: {
                groupId: id
            }
        }, function (data) {
            if (data) {
                $.msg('操作成功！', {
                    time: 2000
                });
                location.reload()
                getList();
            } else {
                $.alert('操作失败！');
            }
        });
    }
    // 从当前组上下移动
    function groupUpOrDown (before, after) {
        $.getData({
            url: '/planPc/groupUpOrDown',
            query: {
                upId: after,
                downId: before
            }
        }, function (data) {
            if (data) {
                $.msg('操作成功！', {
                    time: 2000
                });
                getList();
            } else {
                $.alert('操作失败！');
            }
        });
    }
    // 从当前检验项上下移动
    function schemeItemMove (before, after) {
        $.getData({
            url: '/planPc/itemUpOrDown',
            query: {
                upId: after,
                downId: before
            }
        }, function (data) {
            if (data) {
                getList();
            }
        });
    }
    // 检验项移动至其他组
    function moveToOtherGroup (itemId) {
        selGroupId = $.trim($('#moveGroup').val());

        if (selGroupId === undefined || selGroupId === 'undefined' || selGroupId === '') {
            selGroupId = defaultSelGroup.id;
            selGroupName = defaultSelGroup.name;
        }
        $.getData({
            url: '/planPc/moveToOtherGroup',
            query: {
                itemId: itemId,
                groupId: selGroupId
            }
        }, function (data) {
            if (data) {
                $.msg('操作成功！', {
                    time: 2000
                });
                getList();
            } else {
                $.alert('操作失败！');
            }
        });
    }
    // 获取可移到的组列表
    function getMoveGroupList(itemId) {
        $.getData({
            url: '/planPc/groupList',
            query: {
                id: itemId
            }
        }, function (data) {
            if (data && data.length) {
                var _data = [],
                latsIndex = data.length - 1;
                defaultSelGroup = data[latsIndex]; // 默认组未分组
                for (var i in data) {
                    _data.push({
                        title: data[i].name,
                        value: data[i].id
                    });
                }
                renderMoveGroup(_data);
            }
        });
    }
    // 渲染可移到的组列表
    function renderMoveGroup(data) {
        $('.js-move-group').html(template('common/select', {
            items: data
        }));
    }

    function init() {
        $(document).on('click', '.js-btn-back', function () {
           window.history.back();
        });
        // 获取数据
        getList();
        // 新建组
        $(document).on('click', '.js-add-group', addGroup);
        // 删除组
        $(document).on('click', '.js-delete-group', function () {
            var id = $(this).data('id');
            // $.confirm('确定删除当前分组？', function(){
                deleteGroup(id);
            // })
        });
        // 移动至
        var itemId = '';
        var tabLen = '';
        $(document).on('click', '.js-move-to', function () {
            itemId = $(this).attr('data-id');
            tabLen = $('table').length;
            if(tabLen == 1){
                $.msg('请先新建分组！',{
                    time:2000
                })
            } else {
                $('#moveToGroup').modal('show');
                getMoveGroupList(itemId);
            }
        });
        // 确定移动
        $(document).on('click', '.js-move-det', function () {
            if (!itemId) return;
            moveToOtherGroup(itemId);
            itemId = '';
            $('#moveToGroup').modal('hide');
            getList();
        })
    }
    init();
});

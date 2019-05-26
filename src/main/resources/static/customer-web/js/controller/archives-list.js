$(function () {
    var searchParams = {
        prName: '',
        clId: '',
        ctId: ''
    },
        total = 0,
        currPage = 1;
    // 获取类型
    function getType() {
        $.getData({
            url: '/customer/customTypeInfoList',
            query: {
                typeClass: 1
            }
        }, function (data) {
            if (data && data.length) {
                var _data = [];
                for (var i in data) {
                    _data.push({
                        value: data[i].ctId,
                        title: data[i].typeName
                    });
                }
                renderSelect('#type', _data);
            }
        });
    }
    // 获取部门
    function getDepartment() {
        $.getData({
            url: '/customer/getSgaiDept'
        }, function (data) {
            console.log(data)
            if (data && data.length) {
                var _data = [];
                for (var i in data) {
                    _data.push({
                        value: data[i].id,
                        title: data[i].name
                    });
                }
                renderSelect('#department', _data);
            }
        },function () {
            $.alert("部门获取失败")
        })
    }
    // 获取级别
    function getLevel() {
        $.getData({
            url: '/customer/customLevelInfoList'
        }, function (data) {
            if (data && data.length) {
                var _data = [];
                for (var i in data) {
                    _data.push({
                        value: data[i].clId,
                        title: data[i].levelName
                    });
                }
                renderSelect('#level', _data);
            }
        });
    }
    // 获取列表
    function getList(pageNum, isFirst) {
        $.getData({
            url: '/customer/customInfoList',
            query: {
                pageNum: pageNum,
                pageSize: 10
            },
            body: searchParams
        }, function (data) {
            total = data.count;
            isFirst && $.renderPage({
                count: total,
                curr: currPage,
                jump: function (n) {
                    currPage = n;
                    getList(n);
                }
            });
            if (data.list && data.list.length) {
                renderList(data.list);
            } else {
                renderEmpty();
            }
        });
    }
    // 删除
    function delItem(el, id) {
        $.getData({
            url: '/customer/deletePersonalRecordInfoById',
            query: {
                prId: id
            }
        }, function (data) {
            if (data) {
                // $.msg('操作成功', getList(currPage, true));
                $.toast('操作成功', {
                    type: 'success',
                }, function(){
                    getList(currPage, true);
                });
            }
        });
    }
    // 渲染级别、类型
    function renderSelect(id, data) {
        var _data = data;
        _data.unshift({
            value: '0',
            title: '全部'
        });
        $(id).html(template('common/select', {
            items: _data
        }));
    }
    // 渲染列表
    function renderList(data) {
        $('.js-list').html(template('archives-list', {
            items: data
        }));
    }
    // 渲染空
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 6
        }));
    }
    // 收集搜索条件
    function collectFilters() {
        searchParams.clId = $('#level').val();
        searchParams.ctId = $('#type').val();
        searchParams.deptId = $('#department').val();
        searchParams.prName = $.trim($('#name').val());
        
    }

    function init() {
        getType();
        getLevel();
        getDepartment();
        getList(1, true);
        // 搜索
        $(document).on('click', '.js-search', function () {
            collectFilters();
            getList(1, true);
        });
        // 删除
        $(document).on('click', '.js-del', function () {
            var $this = $(this),
                id = $this.data('id');
            // $.confirm('确定删除当前客户？', function () {
            //     delItem($this, id);
            // });
            // return false;
            $.bubble({
                el: $(this),
                msg: '确定删除当前客户？',
                ok: function () {
                    delItem($this, id);
                },
                cancel: function () {
                    $.toast('您已取消删除');
                }
            });
            return false;
        });
    }
    init();
});
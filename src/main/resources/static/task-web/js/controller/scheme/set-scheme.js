(function () {
    var schemeId = $.getQueryString('id');
    var taskName = $.getQueryString('taskName');
    var type = $.getQueryString('type');
    var isSearch = false;
    if (!schemeId) {
        $.alert('任务方案不存在！', function () {
            history.back();
        });
    }


    // 获取方案详情
    function getList() {
        $.getData({
            url: '/planPc/planDetail',
            query: {
                id: schemeId
            }
        }, function (data) {
            if (data) {
                renderAside(data);
                if (data.list && data.list.length) {
                    data.list.forEach(function (item, index) {
                        item.num = index + 1;
                    });
                    renderMain(data.list);
                } else {
                    renderEmpty();
                }
            }
        });
    }

    // 渲染左侧
    function renderAside(data) {
        $('.aside').html(template('scheme/aside-info', {
            name: data.name,
            pcName: data.pcName,
            description: data.description
        }));
    }

    // 渲染右侧
    function renderMain(data) {
        if (taskName) {
            data.taskName = taskName;
            $('.has-opt').hide();
        }
        $('.js-list').html(template('scheme/main-scheme', {
            items: data
        }));
    }

    // 添加未关联的任务项
    function addRelateItem(el, id) {
        $.getData({
            url: '/planPc/link',
            query: {
                id: id,
                planId: schemeId
            }
        }, function (data) {
            if (data) {
                unlinkList(1, true);
                getList();
            } else {
                $.alert('操作失败！');
            }
        });
    }
    function addRelateItems(el, id) {
        $.getData({
            url: '/planPc/link',
            query: {
                id: id,
                planId: schemeId
            }
        }, function (data) {
            if (data) {
                unlinkList(1, true);
                getList();
            } else {
                $.alert('操作失败！');
            }
        });
    }

    // 取消关联
    function cancelRealte(id) {
        $.getData({
            url: '/planPc/unlink',
            query: {
                id: id
            }
        }, function (data) {
            if (data) {
                // $.msg('操作成功！', {
                //     time: 2000
                // });
                $.toast('操作成功', {
                    type: 'success'
                });
                getList();
            } else {
                $.alert('操作失败！');
            }
        });
    }

    // 收集搜索数据,引用，未关联任务项
    function collectSearchData() {
        var rs = {
            name: '',
            planId: schemeId
        };
        if (isSearch) {
            rs.name = $.trim($('#keyword').val());
        }
        return rs;
    }

    // 渲染引用，未关联任务项列表
    function renderULList(data) {
        $('.check-all').prop('checked', false);
        // $('#keyword').val('');
        $('.js-det-list').html(template('scheme/det-list', {
            items: data
        }));
    }

    // 渲染空列表
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 8
        }));
    }

    // 渲染未关联任务项空列表
    function renderUNEmpty() {
        $('.check-all').prop('checked', false);
        // $('#keyword').val('');
        $('.js-det-list').html(template('common/record-empty', {
            colspan: 7
        }));
    }

    // 引用，未关联任务项列表
    function unlinkList(pageNumber, isFirst) {
        var data = collectSearchData();
        if (!data) {
            return;
        }
        $.getData({
            url: '/planPc/unlinkList',
            query: {
                pageNum: pageNumber || 1,
                pageSize: 5,
                name: data.name,
                planId: schemeId,
                type: type ? type : 0
            }
        }, function (data) {
            isFirst && $.renderPage({
                count: data.count,
                limit: 5,
                jump: function (num) {
                    unlinkList(num);
                }
            });
            if (data && data.list && data.list.length) {
                data.list.forEach(function (item, index) {
                    item.num = index + 1;
                });
                renderULList(data.list);
            } else {
                renderUNEmpty();
            }
        });
    }
    // 保存关联项
    function saveItem(itemsId) {
        console.log(itemsId.join(','));
        
        $.getData({
            url: '/planPc/links',
            query: {
                planId: schemeId,
                ids: itemsId.join(',')
            }
        }, function (data) {
            getList();
        });
    }
    function init() {
        //判断是否是任务详情跳转的
        if (taskName) {
            $('.js-get-det').css('display', 'none');
        }
        //返回按钮
        $(document).on('click', '.js-btn-back', function () {
            if (taskName){
                  window.history.go(-1);
            }else{
                window.location.href = '../../project-list.html';
            }
        })
        // 任务项分组
        $(document).on('click', '.js-detect-group', function () {
            window.location.href = 'det-group-list.html?id=' + schemeId;
        });

        // 引用任务项
        $(document).on('click', '.js-get-det', function () {
            $('#keyword').val('');
            unlinkList(1, true);
        });

        // 搜素任务项
        $(document).on('click', '.js-search', function () {
            isSearch = true;
            unlinkList(1, true);
        });

        // 添加未关联的任务项
        $(document).on('click', '.js-add-item', function () {
            var $this = $(this);
            addRelateItem($this, $this.data('id'));
        });

        // 点击弹窗全选复选框
        $(document).on('click', '.check-all', function () {
            if ($(this).prop('checked')) {
                $('.check-item').prop('checked', true);
            } else {
                $('.check-item').prop('checked', false);
            }
        });
        $(document).on('click', '.check-item', function () {
            if ($(this).prop('checked')) {
                var flag = true;
                for (var i = 0; i < $('.check-item').length; i++) {
                    if (!$('.check-item').eq(i).prop('checked')) {
                        flag = false;
                    }
                }
                if (flag) {
                    $('.check-all').prop('checked', true);
                }
            } else {
                $('.check-all').prop('checked', false);
            }
        });

        $(document).on('click', '.js-save-item', function () {
            var itemList = [];
            for (var j = 0; j < $('.check-item').length; j++) {
                if ($('.check-item').eq(j).prop('checked')) {
                    itemList.push($('.check-item').eq(j).data('id'));
                }
            }
            console.log(itemList);
            if (!itemList.length&&$('.check-item').length) {
                $.alert('请选择任务项');
                $('.modal-dialog.modal-sm').css('margin-top', '100px');
                return false;
            } else if (itemList.length){
                saveItem(itemList);
                $('#myModal').modal('hide');
            } else {
                $('#myModal').modal('hide');
            }
        });

        // 取消关联
        $(document).on('click', '.js-cancel-relate', function () {
            var $this = $(this);
            // $.confirm('确定取消关联当前任务项？', function(){
            //     cancelRealte($this.data('id'));
            // })
            $.bubble({
                el: $(this),
                msg: '确定取消关联当前任务项？',
                ok: function () {
                    cancelRealte($this.data('id'));
                },
                cancel: function () {
                    $.toast('您已取消操作');
                }
            });
        });
        getList();
    }
    init();
})();

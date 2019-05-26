(function() {
    var schemeId = $.getQueryString('id');
    var isSearch = false;
    if (!schemeId) {
        $.alert('检验方案不存在！', function () {
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
        $('.js-list').html(template('scheme/main-scheme', {
            items: data
        }));
    }

    // 添加未关联的检验项
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

    // 取消关联
    function cancelRealte (id) {
        $.getData({
            url: '/planPc/unlink',
            query: {
                id: id
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

    // 收集搜索数据,引用，未关联检验项
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

    // 渲染引用，未关联检验项列表
    function renderULList(data) {
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

    // 渲染未关联检验项空列表
    function renderUNEmpty() {
        $('.js-det-list').html(template('common/record-empty', {
            colspan: 7
        }));
    }

    // 引用，未关联检验项列表
    function unlinkList (pageNumber, isFirst) {
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
                planId: schemeId
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
            } else{
                renderUNEmpty();
            }
        });
    }
    function init() {
        // 检验项分组
        $(document).on('click', '.js-detect-group', function () {
            window.location.href = 'det-group-list.html?id=' + schemeId;
        });

        // 引用检验项
        $(document).on('click', '.js-get-det', function () {
            unlinkList(1, true);
        });

        // 搜素检验项
        $(document).on('click', '.js-search', function () {
            isSearch = true;
            unlinkList(1, true);
        });

        // 添加未关联的检验项
        $(document).on('click', '.js-add-item', function () {
            var $this = $(this);
            addRelateItem($this, $this.data('id'));
        });

        // 取消关联
        $(document).on('click', '.js-cancel-relate', function () {
            var $this = $(this);
            $.confirm('确定取消关联当前检验项？', function(){
                cancelRealte($this.data('id'));
            })
        });
        getList();
    }
    init();
})();

(function() {
    var  templateId = $.getQueryString('tpId');
    var  recordId = $.getQueryString('record_id');
    var  status = $.getQueryString('state');
    var  dt = $.getQueryString('dt');
    var isSearch = false;
    if (! templateId) {
        $.alert('任务不存在！', function () {
            history.back();
        });
    }

    // 获取方案详情
    function getList() {
        $.getData({
            url: '/taskresult/executeTaskDetail',
            query: {
                templateId: templateId,
                recordId: recordId,
                dateTime: dt
            }
        }, function (data) {
            if (data) {
                if (data.taskScopeType) {
                    var dataType = data.taskScopeType.split(',');
                    var treeData = {};
                    console.log(dataType);
                    for (var i = 0; i < dataType.length; i++) {
                        if (dataType[i] == 1) {
                            if (Array.isArray(JSON.parse(data.spaceData))){
                                treeData.spaceData = JSON.parse(data.spaceData);
                            }
                        }else if (dataType[i] == 2) {
                            // materielData 
                            if (Array.isArray(JSON.parse(data.materielData))){
                                treeData.materielData = JSON.parse(data.materielData);
                            }
                        }else if (dataType[i] == 3) {
                            // equipmentData 
                            if (Array.isArray(JSON.parse(data.equipmentData))){
                                treeData.equipmentData = JSON.parse(data.equipmentData);
                            }
                        }else if (dataType[i] == 4) {
                            // supplierData 
                            if (Array.isArray(JSON.parse(data.supplierData))){
                                treeData.supplierData = JSON.parse(data.supplierData);
                            }
                        }
                    }
                }

                console.log(treeData);
                renderAside(data, treeData);
                if (data.exPlanItemls && data.exPlanItemls.length) {
                    data.exPlanItemls.forEach(function (item, index) {
                       item.num = index + 1;
                    });
                    renderMain(data.exPlanItemls);
                } else {
                    renderEmpty();
                }
            }
        });
    }

    // 渲染左侧
    function renderAside(data, treeData) {
        $('.js-side').html(template('result/detail-side', {
            items: data,
            status: status,
            treeData: treeData
        }));
    }

    // 渲染右侧
    function renderMain(data) {
        $('.js-list').html(template('result/detail-list', {
            items: data,
            status: status,
            recordId: recordId
        }));
    }

    // 添加未关联的检验项
    function addRelateItem(el, id) {
        $.getData({
            url: '/planPc/link',
            query: {
                id: id,
                planId:  templateId
            }
        }, function (data) {
            if (data) {
                unlinkList(1, true);
                getList();
            } else {
                layer.alert('操作失败！');
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
            planId:  templateId
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
            colspan: 6
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
                planId:  templateId
            }
        }, function (data) {
            isFirst && $.renderPage({
                count: data.count,
                limit: 5,
                jump: function (num) {
                    unlinkList(num);
                }
            });
            // if (isFirst) {
            //     debugger;
            //     if (data && data.count > 5) {
            //         $.renderPage({
            //             count: data.count,
            //             limit: 5
            //         }, function (obj, first) {
            //             first || unlinkList(obj.curr);
            //         });
            //     } else {
            //         $('.det-pages').hide();
            //     }
            // }
            if (data && data.list && data.list.length) {
                renderULList(data.list);
            } else{
                renderUNEmpty();
            }
        });
    }
    function init() {
        // 返回
        $(document).on('click', '.js-btn-back', function () {
            window.history.back();
        });
        // 检验项分组
        $(document).on('click', '.js-detect-group', function () {
            var url = 'det-group-list.html?id=' +  templateId;
            window.location.href = url;
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

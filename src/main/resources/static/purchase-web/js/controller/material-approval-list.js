$(function () {
    var currPage = 1;
    // 获取列表
    function getList(isSearch) {
        var queryBody={};
            queryBody = {
                "applyEmpName": $('#keyWords').val(),
                "id": "",
                "matStat": $('#status').val()
            };
        console.log(queryBody);
        $.getData({
            url: 'suppliesOperation/suppliesApproves',
            query: {
                pageNum: currPage,
                pageSize:10           
            },
            body:queryBody
        }, function(data) {
            $.renderPage({
                count: data.count,
                curr: currPage,
                jump: function(n) {
                    currPage = n;
                    getList();
                }
            });
            if (data.list && data.list.length) {
                renderList(data.list);
            } else {
                renderEmpty();
            }
        });     
    }
    // 渲染列表
    function renderList(data) {
        $('.js-list').html(template('order/material-approval-list', {
            items: data
        }));
    }
    // 渲染无数据
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 7
        }));
    }
    // 删除
    function delItem(el, id) {
        $.getData({
            url: '',
            query: {
                id: id
            }
        }, function(data) {
            if (data) {
                $.toast('操作成功',{timer:2000});
                el.parent().parent().remove();
                if (currPage !== 1 && !$('.js-list tr').length) {
                    currPage -= 1;
                }
                getList();
            }
        });
    }
    function init() {
        getList(true);
        // 搜索
        $(document).on('click', '.js-search', function() {
            getList(true);
            currPage = 1; // 搜索的时候记得重置当前页号为１
        });
        // 删除
        $(document).on('click', '.js-del', function() {
            delItem($(this), $(this).data('id'));
        });
        // 重置
        $(document).on('click', '.js-reset', function() {
            getList();
            $("#keyWords").val("");
            currPage = 1; 
        });
        // 分页
        $.renderPage({count:100});
    }
    init();
});

$(function () {
    var currPage = 1;
    // 点击批量新建出库单
    $(document).on('click', '#addAll', function () {
        window.location.href = "./out-addAll.html";
    });
    // 点击新建出库单
    $(document).on('click', '#addDepot', function () {
        window.location.href = "./out-add.html";
    });
    // 获取列表    
    function getList() {
        var query = {},body = {};
            query = {
                pageNo: currPage,
                pageSize: 10
            }
            // body = {
            //     whOutNo: $('#whOutNo').val()?$('#whOutNo').val():null,
            //     // whOutType: $('#whOutType').val() == '0'? 0 : $('#whOutType').val(),
            //     // whStat: $('#whStat').val() == '0'? 0 :$('#whStat').val()
            //   }
              console.log(body)
              body.whOutNo = $.trim($('#whOutNo').val());
        if ($.trim($('#whOutType').val()) != 0) {
            body.whOutType = Number($.trim($('#whOutType').val()));
        }
        if ($.trim($('#whStat').val()) != 0) {
            body.whStat = Number($.trim($('#whStat').val()));
        }
        $.getData({
            url: 'depotOutManage/depotOutLists',
            query: query,
            body:body
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
        $('.js-list').html(template('list/out-list', {
            items: data
        }));
    }
    // 渲染无数据
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 10
        }));
    }

    // 撤回
    function revoke(id){
        $.getData({
            url: 'depotOutManage/deleteDepot',
            query: {
                id: id
            }
        }, function (data) {
            if (data) {
                $.toast('操作成功', {
                    time: 2000
                });
                // el.parents("td").html('<td></td>').parents("tr").find('.js-status').text('已撤回');
                getList();
            } else {
                $.alert('操作失败');
            }
        });
    }
    function init() {
        getList();
        // 搜索
        $(document).on('click', '.js-search', function() {
            getList();
            currPage = 1; // 搜索的时候记得重置当前页号为１
        });
        // 点击出库
        $(document).on('click', '.js-tb-ck', function() {
            location.href = './out-edit.html?infoId=' + $(this).parents('[data-id]').attr('data-id');
        });
        // 点击撤回
        $(document).on('click', '.js-tb-cx', function() {
            var that = $(this);
            $.bubble({
                // title: '提示',
                el: that,
                msg: '确认撤销当前出库单？',
                // btn: ['确认', '取消'],
                // size:'sm',
                ok: function () {
                    revoke($(that).parents('[data-id]').attr('data-id'))
                }
            });
        });
    }
    init();
});

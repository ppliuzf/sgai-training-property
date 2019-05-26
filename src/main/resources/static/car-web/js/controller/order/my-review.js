/**
 *
 *  我的审核
 *   
 */
$(function() {
    // 查询参数
    var params = {},
        id = '',
        pageCurr
        // 获取列表
    function getList(pageCurr, isFirst, param) {
        var query = $.extend({ pageNum: pageCurr || 1, pageSize: 10 }, param || {});
        $.getData({
                url: '/carUserRelationInfo/findListPageByAuditStatus',
                query: query
            },
            function(data) {
                isFirst && $.renderPage({
                    count: data.count,
                    limit: 10,
                    jump: function(num) {
                        pageCurr = num;
                        getList(num, false, params);
                    }
                });
                if (data.list && data.list.length) {
                    //console.log(data)
                    data.list.forEach(function(item, index) {
                        item.riUseStart = $.formatTime(item.riUseStart)
                        var nowD = new Date()
                        var ss = $.formatTime(nowD)
                        var kk = ss.split(' ')
                        var ff = kk[0]
                        item.updatedDt = ff
                        item.num = index + 1 > 9 ? index + 1 : '0' + (index + 1);
                    });
                    renderList(data.list);
                } else {
                    renderEmpty();
                }
            })
    }

    // 重置搜索
    function resetSearch() {
        $('#ciNumber').val('');
        params = {};
        getList(1, true)
    }

    // 渲染列表
    function renderList(data) {
        $('.js-list').html(template('order/my-review', { items: data }))
    }
    // 渲染无数据
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 9
        }));
    }
    //四
    function init() {
        getList(1, true, params);

        // 搜索
        $(document).on('click', '.js-search', function() {
            params = {};
            params.ciNumber = $.trim($('#ciNumber').val());
            getList(1, true, params);
        });

        //重置
        $(document).on('click', '.js-reset', resetSearch)


        //通过
        $(document).on('click', '.js-edit1', function() {
            let marsk = '<div class="orderMarsk" style="width:100%;height:100%;background:#000;position:fixed;top:0;left:0;opacity:0.2;"></div>';
            $('body').append(marsk)
            $('.orderMarsk').click(function() {
                $(this).remove()
            })
            var id = $(this).data('id')
            var arr = id.split('-')
            var pam = {}
            for (var i = 0; i < arr.length; i++) {
                pam.id = arr[0]
                pam.riAuditStatus = 2
            }
            $.bubble({
                el: $(this),
                msg:"确认通过此次车辆预约？",
                ok: function () {
                    $('.orderMarsk').remove()
                    $.getData({
                        url: '/carUserRelationInfo/auditCarUserRelationInfoById',
                        query: pam
                    },
                    function(data) {
                        getList(pageCurr, true, params);
                    })
                },
                cancel: function () {
                    $('.orderMarsk').remove()
                    // $.toast('您已取消');
                }
            });

        })

        //拒绝
        $(document).on('click', '.js-edit2', function() {
            let marsk = '<div class="orderMarsk" style="width:100%;height:100%;background:#000;position:fixed;top:0;left:0;opacity:0.2;"></div>';
            $('body').append(marsk)
            $('.orderMarsk').click(function() {
                $(this).remove()
            })
            var id = $(this).data('id');
            $.bubble({
                el: $(this),
                msg:"确认拒绝此次车辆预约？",
                ok: function () {
                    $('.orderMarsk').remove()
                    var arr = id.split('-'),
                        pam = {};
                    for (var i = 0; i < arr.length; i++) {
                        pam.id = arr[0]
                        pam.riAuditStatus = 3
                    }
                    $.getData({
                            url: '/carUserRelationInfo/auditCarUserRelationInfoById',
                            query: pam
                        },
                        function(data) {
                            getList(pageCurr, true, params);
                        })
                },
                cancel: function () {
                    $('.orderMarsk').remove()
                    // $.toast('您已取消');
                }
            });
        });

        //返回
        $(document).on('click', '#fh', function(){
            window.history.back();
        })
    }
    init();
});
$(function () {
    var currPage = 1;
    // 仓库ID
    var ckId = [], ckName = [], ckList = [];
    // 收集数据
    function collectData(pageNum) {
        var rs = {};
        rs.pageNum = pageNum || 1;
        rs.pageSize = 10;
        // 订单号
        rs.orderNo = $.trim($('#keyWords').val());
        // 订单类型
        var type = $.trim($('#type').val());
        if (type !== '3') {
            rs.orderType = Number($.trim($('#type').val()));
        }
        // 采购状态
        var status = $.trim($('#status').val());
        if (status !== '4') {
            rs.orderStat = Number($.trim($('#status').val()));
        }
        // 发票状态
        var statusFp = $.trim($('#statusFp').val());
        if (statusFp !== '3') {
            rs.invoiceState = Number($.trim($('#statusFp').val()));
        }
        return rs;
    }
    // 获取仓库
    function getckList() {
        $.getData({
            url: '/listOrDetail/getWhList'
        }, function (data) {
            ckList = data;
        });
    }
    // 获取列表
    function getList(pageNum) {
        // 收集数据
        var data = collectData(pageNum);
        if (data) {
            $.getData({
                url: '/order/searchList',
                query: data
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
                    $('.pages').css('display', 'block');
                    renderList(data.list);
                } else {
                    $('.pages').css('display', 'none');
                    renderEmpty();
                }
            });
        }
    }

    // 渲染列表
    function renderList(data) {
        $('.js-list').html(template('purchase-orderT/purchase-order-list', {
            items: data
        }));
    }
    // 渲染无数据
    function renderEmpty() {
        $('.js-list').html(template('common/record-empty', {
            colspan: 10
        }));
    }
    // 开始采购
    function start(id) {
        $.getData({
            url: '/order/beginPurchase',
            query: {
                id: id
            }
        }, function (data) {
            if (data) {
                $.toast('操作成功', {
                    timer: 2000
                });
                getList(currPage);
            } else {
                $.alert('操作失败');
            }
        });
    }
    // 确认收货
    function complete(id) {
        if ($('#instant-selected').val() == '') {
            $.toast('请选择正确的收货人后提交', {
                timer: 2000
            });
            return false;
        } else {
            $.getData({
                url: '/listOrDetail/confirmReceipt',
                body: {
                    id: id,
                    takeCargoId: JSON.parse($('#instant-selected').val()).id,
                    takeCargoName: JSON.parse($('#instant-selected').val()).title,
                    warName: $('#address').val(),
                    whId: $('#address>option:selected').attr('id')
                }
            }, function (data) {
                if (data) {
                    $.toast('操作成功', {
                        timer: 2000
                    });
                    getList(currPage);
                } else {
                    $.alert('操作失败');
                }
            });
        }
    }
    // 开具发票
    function drawBills(id) {
        $.getData({
            url: '/listOrDetail/invoice',
            body: {
                orderId: id,
                invoiceName: $('#name').val(),
                tallageNumber: $('#number').val(),
                invoiceSite: $('#address').val(),
                invoicePhone: $('#phone').val(),
                invoiceMoney: $('#money').val()
            }
        }, function (data) {
            if (data) {
                $.toast('操作成功', {
                    timer: 2000
                });
                getList(currPage);
            } else {
                $.alert('操作失败');
            }
        });
    }
    function init() {
        // 点击跳转详情
        $(document).on('click', '.js-detail', function () {
            var id = $(this).attr('data-id');
            var status = $(this).attr('status');
            var statusF = $(this).attr('status-f');
            window.location.href = './purchase-order-del.html?id=' + id + '&status=' + status + '&status-f=' + statusF;
        });

        // 搜索
        $(document).on('click', '.js-search', function () {
            currPage = 1;
            getList();
        });
        // 点击开始采购
        $(document).on('click', '.js-start', function () {
            var that = $(this);
            $.pop({
                size: 'sm',
                title: '提示',
                content: '确认开始采购吗？',
                btn: ['确认', '取消'],
                yes: function () {
                    start(that.attr('data-id'));
                }
            });
        });
        // 点击确认收货
        $(document).on('click', '.js-compelet', function () {
            var that = $(this);
            $.pop({
                noIcon: true,
                title: '确认收货',
                size: 'md',
                content: template('purchase-orderT/confirm'),
                btn: ['确认', '取消'],
                yes: function () {
                    complete(that.attr('data-id'));
                }
            });
            // 下拉联想
            $.instantSearch({
                // url: '/orgTree/searchEmpInfoByName',
                placeholder: '请输入采购人姓名'
            });
            for (var i = 0; i < ckList.length; i++) {
                $('#address').append('<option value=' + ckList[i].whName + ' id=' + ckList[i].id + '>' + ckList[i].whName + '</option>')
            }
        });
        // 点击开具发票
        $(document).on('click', '.js-bills', function () {
            var that = $(this);
            $.pop({
                noIcon: true,
                title: '开具发票',
                size: 'md',
                content: template('purchase-orderT/makeBill'),
                btn: ['确认', '取消'],
                yes: function () {
                    if ($.trim($('#name').val()) == '') {
                        $.alertc('请输入名称');
                        return false;
                    }
                    if ($.trim($('#number').val()) == '') {
                        $.alertc('请输入纳税人识别号');
                        return false;
                    }
                    if ($.trim($('#money').val()) == '') {
                        $.alertc('请输入发票金额');
                        return false;
                    }
                    drawBills(that.attr('data-id'));
                }
            });
        });
        getckList();
        getList();
        $.renderPage({ count: 100 });
    }
    init();
});

$(function () {
    var currPage = 1;
    var infoId = $.getQueryString('id');
    var status = $.getQueryString('status');
    var statusF = $.getQueryString('status-f');
    var ckList = [];
    // status-f=1
    if(statusF == 1){
        $('#billsPur').css('display', 'inline-block');
    } else{
        $('#billsPur').css('display', 'none');
    }
    // 获取仓库
    function getckList(){
        $.getData({
            url: '/listOrDetail/getWhList'
        }, function (data) {
            ckList = data;
        });
    }
    getckList();
    console.log('仓库内容');
    console.log(ckList);
    // 获取列表
    function getList(pageNum) {
        $.getData({
            url: '/listOrDetail/orderDetail',
            query: {
                id: infoId
            }
        }, function (data) {
            if (data) {
                console.log(data);
                if(data.deptJson){
                    data.dept = JSON.parse(data.deptJson);
                    console.log(data.dept[0].title);
                }
                renderDetailUp(data);
                if (data.matDetailVoList && data.matDetailVoList.length) {
                    renderDetailDown(data.matDetailVoList);
                } else {
                    renderEmpty();
                }
            }
        });
    }
    // 收货单信息
    function getMertail(){
        $.getData({
            url: '/listOrDetail/reivgDetail',
            query: {
                id: infoId
            }
        }, function (data) {
            if (data) {
                $('#sPeo').text(data.takeCargoName);
                $('#sTime').text(data.createdTime);
                $('#sRk').text(data.whName );
            }
        });
    }
    // 发票信息
    function getFapiao(){
        $.getData({
            url: '/listOrDetail/invoiceDetail',
            query: {
                id: infoId
            }
        }, function (data) {
            console.log('####', data);
            if (data) {
                $('#nameC').text(data.invoiceName);
                $('#numberC').text(data.tallageNumber);
                if (data.invoiceSite) {
                    $('#addressC').text(data.invoiceSite);
                } else {
                    $('#addressC').text('--');
                }
                if (data.invoicePhone) {
                    $('#phoneC').text(data.invoicePhone);
                } else {
                    $('#phoneC').text('--');
                }
                $('#moneyC').text(data.invoiceMoney);
            }
        });
    }
    // 渲染up
    function renderDetailUp(data) {
        $('.js-main-up').html(template('purchase-orderT/purchase-order-detail-up', {
            items: data
        }));
    }
    // 渲染down
    function renderDetailDown(data) {
        $('.js-main-down').html(template('purchase-orderT/purchase-order-detail-down', {
            items: data
        }));
    }
    function renderEmpty() {
        $('.js-main-down').html(template('common/record-empty', {
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
                    type: 'success',
                    time: 2000
                });
                $('#startPur').addClass('hidden');
                $('#confirmPur').removeClass('hidden');
            } else {
                $.alert('操作失败');
            }
        });
    }
    // 确认收货
    function complete(id) {
        if ($('#instant-selected').val() == '') {
            $.toast('请选择正确的收货人后提交', {
                // type: 'error',
                time: 2000
            });
            return false;
        } else {
            $.getData({
                url: '/listOrDetail/confirmReceipt',
                body: {
                    id: infoId,
                    takeCargoId: JSON.parse($('#instant-selected').val()).id,
                    takeCargoName: JSON.parse($('#instant-selected').val()).title,
                    warName: $('#address').val(),
                    whId: $('#address>option:selected').attr('id')
                }
            }, function (data) {
                if (data) {
                    $.toast('操作成功', {
                        type: 'success',
                        time: 2000
                    });
                    $('#confirmPur').addClass('hidden');
                    $('#billsPur').removeClass('hidden');
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
                $('#billsPur').addClass('hidden');
            } else {
                $.alert('操作失败');
            }
        });
    }
    function tabChange(){
        $('#tableChange span').each(function(index, item){
            $(this).on('click', function(){
                var idMain = $(this).attr('id');
                if (idMain == 'wlmx'){
                    $('#wlmxtable').removeClass('hidden');
                    $('#shdxxtablt').addClass('hidden');
                    $('#fpxxtable').addClass('hidden');
                    $('#wlmx').addClass('cur').siblings().removeClass('cur')
                }
                if (idMain == 'shdxx') {
                    $('#wlmxtable').addClass('hidden');
                    $('#shdxxtablt').removeClass('hidden');
                    $('#fpxxtable').addClass('hidden');
                    $('#shdxx').addClass('cur').siblings().removeClass('cur')
                    getMertail();
                }
                if (idMain == 'fpxx') {
                    $('#wlmxtable').addClass('hidden');
                    $('#shdxxtablt').addClass('hidden');
                    $('#fpxxtable').removeClass('hidden');
                    $('#fpxx').addClass('cur').siblings().removeClass('cur')
                    getFapiao();
                }
                $(this).addClass('selectMain').siblings().removeClass('selectMain');
            });
        });
    }
    function init() {
        $('#wlmx').addClass('cur')
        if(status == 1){
            $('.nodeal').removeClass('hidden');
            $('#startPur').removeClass('hidden');
        } else if (status == 2) {
            $('.dealing').removeClass('hidden');
            $('#confirmPur').removeClass('hidden');
        } else if (status == 3){
            $('.deal').removeClass('hidden');
            $('#billsPur').removeClass('hidden');
        } else if (status == 3 && statusF == 2){
            $('.deal').removeClass('hidden');
        }
        // 点击开始采购
        $(document).on('click', '#startPur', function () {
            var that = $(this);
            $.pop({
                
                size: 'sm',
                title: '提示',
                content: '确认开始采购吗？',
                btn: ['确认', '取消'],
                yes: function () {
                    start(infoId);
                }
            });
        });
        // 点击确认收货
        $(document).on('click', '#confirmPur', function () {
            var that = $(this);
            $.pop({
                title: '确认收货',
                size: 'md',
                noIcon:true,
                content: template('purchase-orderT/confirm'),
                btn: ['确认', '取消'],
                yes: function () {
                    complete(infoId);
                }
            });

            // 下拉联想
            $.instantSearch();
            
            for (var i = 0; i < ckList.length; i++) {
                $('#address').append('<option value=' + ckList[i].whName + ' id=' + ckList[i].id + '>' + ckList[i].whName + '</option>');
            }
        });
        // 点击开具发票
        $(document).on('click', '#billsPur', function () {
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
                    drawBills(infoId);
                }
            });
        });
        // 点击返回
        $(document).on('click', '#back', function(){
            window.history.go(-1);
        });
        tabChange();
        getList();
    }
    init();
});

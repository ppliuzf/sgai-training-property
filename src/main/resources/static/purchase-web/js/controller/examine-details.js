$(function () {

    // 页面加载时的公共方法（如果是编辑页面，获取请求数据的内容）
    function init() {
        getContent();
        getStatus($.getQueryString('status'));
    }
    init();

    // 判断不同的参数显示不同的操作按钮
    function getStatus (status) {
        switch(status)
        {
            case 'wcl':
            $("#untreated, #untreatedTitle").removeClass("hide");
            break;

            case 'ytg':
            $("#alreadyPassed, #passedTitle").removeClass("hide");
            break;

            case 'yjj':
            $("#haveRefused, #refusedTitle").removeClass("hide");
            break;
        }
    }
    
    // 获取页面所需数据
    function getContent() {
        // 获取详情数据
        $.getData({
            url: '/order/orderDetail',
            query: {
                id: $.getQueryString('orderId')
            }
        }, function(data) {
            if (data) {
                console.log('返回值');
                console.log(data);
                if (data.deptJson) {
                    data.dept = JSON.parse(data.deptJson)[0].title;
                } else {
                    data.dept = '';
                }
                
                renderCont(data);
            }
        });
    }

    // 渲染数据
    function renderCont(data) {
        $('#order').html(template('order/detail-purchase', {
            items: data
        }));
        if (data.matDetailVoList == null){
            $("#materielList").html(template('common/record-empty', {
                colspan: 6
            }));
        } else {
            $("#materielList").html(template('order/materiel-list-four', {
                items: data.matDetailVoList
            }));
        }
    }

    // 点击处理
    $(document).on('click', '#handleOrder', function(e){
        window.location.href="purchaseDemand.html";
    });

    // 点击取消
    $(document).on('click', '#cancel', function(e){
        // window.history.go(-1);
        window.location.href = "./purchase-approval-list.html";
    });
    // 点击通过
    $(document).on('click', '#examineAdopt', function(e){
        // popMsg('1');
        approval("1");
    });
    // 点击拒绝
    $(document).on('click', '#examineRefuse', function(e){
        popMsg('0');
    });
    
    // 同意，拒绝弹框公共方法
    function popMsg(types) {
        var postContent = '';
        var postTitle = '';
        if (types === '1') {
            postContent = "<textarea maxlength='50' placeholder='请输入同意意见（非必填）' class='text-msg'></textarea>";
            postTitle = '同意意见';
            
        } else {
            postContent = "<textarea maxlength='50' placeholder='请输入拒绝理由（必填）' class='text-msg'></textarea>";
            postTitle = '拒绝意见';
        }
        $.pop({
            title: postTitle,
            content: postContent,
            btn: ['确认', '取消'],
            yes: function () {
                if (types === '0' && $.trim($('.text-msg').val()) === '') {
                    $.msg('拒绝理由未填写', {
                        time: 2000
                    });
                    return false;
                } else {
                    $('.text-msg').hide();
                    approval(types);
                }
            }
        });
    }
    // 同意，拒绝请求公共方法
    function approval(types) {
        $.getData({
            url: '/order/approve',
            query: {
                id: $.getQueryString('orderId'),
                option: types,
                reason: $.trim($('.text-msg').val())
            }
        }, function (data) {
            if (data) {
                $.msg('操作成功', {
                    time: 2000
                }, function () {
                    window.location.href = "purchase-approval-list.html";
                });
            } else {
                $.alert('操作失败');
            }
        });
    }
});
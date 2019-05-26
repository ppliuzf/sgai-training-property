
$(function () {
    var infoId = $.getQueryString('infoId');
    function init() {
        changeTitle();
        getContent();
        // 点击返回
        $(document).on('click', '.js-back', function() {
           history.back();
        });
        // 点击编辑
        $(document).on('click', '.js-bj', function() {
            location.href = "./material-apply-add.html?infoId=" + infoId;
         });
        //  点击撤回
        $(document).on('click', '.js-bt-ch', function() {
            $.pop({
                title: '提示',
                content: '确认要撤回该申请吗？',
                btn: ['确认', '取消'],
                size:'sm',
                yes: function () {
                    revoke();
                }
            });
         }); 
         // 点击领取物料
        $(document).on('click', '.js-lq', function() {
            var that = this;
            $.pop({
                title: '提示',
                content: '确认要领取该物料吗？',
                btn: ['确认', '取消'],
                size:'sm',
                yes: function () {
                    getMat();
                }
            });
        }); 
    }
    init();
    // 领取
    function getMat(){
        $.getData({
            url: 'suppliesOperation/getSupplies',
            query: {
                id: infoId
            }
        }, function (data) {
            if (data) {
                $.toast('操作成功', {
                    timer: 2000
                });
                // el.parents("td").html('<td></td>').parents("tr").find('.js-status').text('已领取');
                location.href = "material-apply-list.html"
            } else {
                $.alert('操作失败');
            }
        });
    }
     // 撤回
     function revoke(){
        $.getData({
            url: 'suppliesOperation/suppliesRetract',
            query: {
                id: infoId
            }
        }, function (data) {
            if (data) {
                $.msg('操作成功', {
                    time: 2000
                });
                // $(".js-title h4").html("用料申请-已撤回");
                // $(".js-btn3").removeClass("hide");$(".js-btn1,.js-btn2").addClass("hide");  
                location.href = "material-apply-list.html"
            } else {
                $.alert('操作失败');
            }
        });
    }
    // 获取页面所需数据
    function getContent() {
        $.getData({
            url: 'suppliesOperation/suppliesDetil',
            query: {
                id:infoId
            }
        }, function (data) {
            console.log('3333', JSON.stringify(data));
            // 引入模板
            if (data.imgUrl) {
                data.imgUrl = data.imgUrl.split(',');
            }
            renderCont(data);
        });
        
        
    }
    // 渲染数据
    function renderCont(data) {
        $('#order').html(template('order/material-apply-detail', {
            items: data
        }));
        if(data.suppliesDetails){
            $("#materielList").html(template('order/material-approval-detail-wuliao', {
                items: data.suppliesDetails
            }));
        }   else{
            renderEmpty();
        }
        if (data.imgUrl){
            $('.js-imgs').parent().parent().css('display', 'block');
            $('.js-imgs').html(template('order/imgList', {
                items: data.imgUrl
            }));
        }else{
            $('.js-imgs').parent().parent().css('display', 'none');
        }
    }
      // 渲染无数据
      function renderEmpty() {
        $('#materielList').html(template('common/record-empty', {
            colspan: 4
        }));
    }
     // 根据url参数修改页面标题及其他内容
     function changeTitle(){
        var status = $.getQueryString('status');
        if(status == 1){
            $(".js-title h4").html("用料申请-已提交");
            $(".js-btn1").removeClass("hide");$(".js-btn2,.js-btn3").addClass("hide");
        } else if(status == 2){
            $(".js-title h4").html("用料申请-已通过");
            $(".js-btn2").removeClass("hide");$(".js-btn1,.js-btn3").addClass("hide");
        } else if(status == 3){
            $(".js-title h4").html("用料申请-已拒绝");
            $(".js-btn3").removeClass("hide");$(".js-btn1,.js-btn2").addClass("hide");
        } 
        else if(status == 4){
            $(".js-title h4").html("用料申请-已撤回");
            $(".js-btn3").removeClass("hide");$(".js-btn1,.js-btn2").addClass("hide");
        }
        else{
            $(".js-title h4").html("用料申请-已领取");
            $(".js-btn3").removeClass("hide");$(".js-btn1,.js-btn2").addClass("hide");
        }
    }
});

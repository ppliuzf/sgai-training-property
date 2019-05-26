
$(function () {
    var infoId = $.getQueryString('infoId');
    function init() {
        changeTitle();
        getContent();
        // 点击返回
        $(document).on('click', '.js-back', function() {
           history.back();
        });
          // 点击通过
        $(document).on('click', '.js-agree', function(e){
            popMsg('Y');
        });
        // 点击拒绝
        $(document).on('click', '.js-refuse', function(e){
            popMsg('N');
        });
    }
    init();
  // 同意，拒绝弹框公共方法
  function popMsg(types) {
        var postContent = '';
        var postTitle = '';
        if (types === 'Y') {
            approval(types);
        } else {
            postContent = "<textarea maxlength='50' placeholder='请输入拒绝理由（必填）' class='text-msg' style='height:142px; border-radius:5px; width:100%;'></textarea>";
            postTitle = '拒绝意见';
            setTimeout(() => {
                $('.modal-body').css({'height':'200px'})
            }, 0);
            $.pop({
                title: postTitle,
                content: postContent,
                noIcon:true,
                btn: ['确认', '取消'],
                yes: function () {
                    if (types === 'N' && $.trim($('.text-msg').val()) === '') {
                        $.toast('拒绝理由未填写', {
                            type:'error',
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
        
    }
    // 同意，拒绝请求公共方法
    function approval(types) {
        $.getData({
            url: 'suppliesOperation/suppliesApproval',
            query:{},
            body: {
                id: infoId,
                approvalStatus: types,
                approvalOpinition: $.trim($('.text-msg').val())
            }
        }, function (data) {

            if (data) {
                $.toast('操作成功', {
                    type:'success',
                    time: 2000
                });
                setTimeout(function(){
                    window.location.href = "material-approval-list.html";
                },2000);
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
           // 引入模板
           if(data.imgUrl){
            data.imgUrl = data.imgUrl.split(',');
           }
           console.log(data.imgUrl)
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
            renderEmpty()
        }
        
        $(".js-imgs").html(template('order/imgList', {
            items: data.imgUrl
        }));
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
            $(".js-title h4").html("用料审批-待审核");
            $(".js-btn1").removeClass("hide");$(".js-btn2").addClass("hide");
           
        } else if(status == 2){
            $(".js-title h4").html("用料审批-已通过");
            $(".js-btn2").removeClass("hide");$(".js-btn1").addClass("hide");
        }
        else if(status == 3){
            $(".js-title h4").html("用料审批-已拒绝");
            $(".js-btn2").removeClass("hide");$(".js-btn1").addClass("hide");
        }
        else if(status == 4){
            $(".js-title h4").html("用料审批-已撤销");
            $(".js-btn2").removeClass("hide");$(".js-btn1").addClass("hide");
        } else {
            $(".js-title h4").html("用料审批-已通过");
            $(".js-btn2").removeClass("hide");$(".js-btn1").addClass("hide");
        }
    }
});

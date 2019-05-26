$(function () {

    // 页面加载时的公共方法
    function init() {
        getContent();
        getStatus($.getQueryString('status'));
    }
    init();

    // 判断不同的参数显示不同的操作按钮
    function getStatus (status) {
        switch(status)
        {
            case 'dsh':
            $(".dsh").removeClass("hide");
            // document.title = '采购计划详情-待审核';
            document.title = '采购计划详情'
            break;

            case 'ytg':
            $(".ytg").removeClass("hide");
            // document.title = '采购计划详情-已通过';
            document.title = '采购计划详情'
            break;

            case 'yjj':
            $(".yjj").removeClass("hide");
            // document.title = '采购计划详情-已通过';
            document.title = '采购计划详情'
            break;

            case 'yjj':
            $("#refuseOperation, #detailTitle").removeClass("hide");
            // document.title = '采购详情-已拒绝';
            document.title = '采购计划详情'
            break;

            case 'cgz':
            $("#procurementOperation, #detailTitle").removeClass("hide");
            // document.title = '采购详情-采购中';
            document.title = '采购计划详情'
            break;

            case 'ywc':
            $("#completeOperation, #detailTitle").removeClass("hide");
            // document.title = '采购详情-已完成';
            document.title = '采购计划详情'
            break;

            case 'ych':
            $("#completeOperation, #detailTitle").removeClass("hide");
            // document.title = '采购详情-已撤回';
            document.title = '采购计划详情'
            break;
        }
    }
    // 定义公共的对象，渲染页面使用
    var tableObj = {};
    var detailObj = {};

    // 获取页面所需数据
    function getContent() {
        
        // 获取详情数据
        $.getData({
            url: '/listOrDetail/getPlanDetail',
            query: {
                id: $.getQueryString('id')
            }
        }, function(data) {
            if (data) {
                console.log('返回值');
                console.log(data);
                data.dept =JSON.parse(data.planDept)[0].title;
                renderCont(data);

                // 获取供应商
                // $.getData({
                //     url: '/common/getComList'
                // }, function(data) {
                //     if (data) {
                //         console.log('返回值');
                //         console.log(data);
                //         tableObj.comList = data;
                //         // 引入模板
                //         obj.detail.comList = data;
                //         renderCont(obj.detail);
                //     }
                // });
            }
        });
    }
    
    // 渲染数据
    function renderCont(data) {
        $('#order').html(template('approval/approval-detail', {
            items: data
        }));
        if (data.planStageVoList.length>0) {
            // if (data.planStageVoList.length ==1 && !data.planStageVoList.planTaskVoList){
                // $("#materielList").html(template('common/record-empty', {
                //     colspan: 8
                // }));
            // } else {
                $("#materielList").html(template('approval/approval-detail-list', {
                    items: data
                }));
            // }
        } else {
            $("#materielList").html(template('common/record-empty', {
                colspan: 8
            }));
        }
        
        // if (data.matDetailVoList == null){
        //     $("#materielList").html(template('common/record-empty', {
        //         colspan: 6
        //     }));
        // } else {
        //     $("#materielList").html(template('approval/approval-detail-list', {
        //         items: data
        //     }));
        // }
    }
    
    // 点击取消
    $(document).on('click', '.cancelBtn', function(e){
        window.history.go(-1);
        // window.location.href= "./purchase-plan-approval-list.html";
    });

    
    // 点击通过按钮
    $(document).on('click', '#adoptBtn', function(e){
        popMsg('Y');
    });

    // 点击拒绝按钮
    $(document).on('click', '#refuseBtn', function(e){
        popMsg('N');
    });
    
    // 同意，拒绝弹框公共方法
    function popMsg(types) {
        if (types === 'Y') {
            approval(types);
        } else {
            $.popnomaskmy({
                title: "拒绝意见",
                noIcon: true,
                size: 'md',
                isCancel: false,
                content: "<textarea id='reson' maxlength='50' placeholder='请输入拒绝理由（必填）' class='text-msg' style='height:142px;width:100%; border-radius:5px;'></textarea>",
                btn: ['确认', '取消'],
                noIcon:true,
                yes: function () {
                    if ($.trim($('.text-msg').val()) === '') {
                        $.toast('拒绝理由未填写', {
                            timer: 2000
                        });
                        return false;
                    } else {
                        approval(types);
                        $('#modal-pop').css('display', 'none');
                    }
                }
            });
        }
        
    }
    // 同意，拒绝请求公共方法
    function approval(types) {
        $.getData({
            url: '/listOrDetail/planApproval',
            query:{},
            body: {
                id: $.getQueryString('id'),
                approvalStatus: types,
                approvalOpinition: $.trim($('#reson').val())
            }
        }, function (data) {
            if (data) {
                $.toast('操作成功', {
                    type:"success",
                    timer: 2000
                });
                setTimeout(function(){
                    window.location.href = "purchase-plan-approval-list.html";
                },200);
            } else {
                $.alert('操作失败');
            }
        });
    }

    // 点击查看明细
    $(document).on('click', '.checkList', function(e){
        var self= $(this);
        var selfId = self.attr('id');
        $.getData({
            url: '/listOrDetail/planTaskDetail',
            query:{id: selfId}
        }, function (data) {
            if (data) {
                $.pop({
                    title: '物料明细',
                    content: template('approval/material-list', {
                        items: data.planDetailList
                    }),
                    size: 'lg',
                    isCancel: false,
                    noIcon:true,
                    yes: function() {
        
                    }
                });
            } else {
                $.alert('操作失败');
            }
        });
        
        
    });
    
    // 请求的公共方法
    // function postMethod(postUrl, title, cont, url) {
    //     $.pop({
    //         content: cont,
    //         isCancel: true,
    //         size:'sm',
    //         yes: function() {
    //             $.getData({
    //                 url: postUrl,
    //                 query: {
    //                     id: $.getQueryString('orderId')
    //                 }
    //             }, function(data) {
    //                 if (data) {
    //                     popupMsg(title,url);
    //                 }
    //             });
    //         }
    //     });
    // }

    // //弹框公共方法
    function popupMsg(title, url) {
        $.msg(title, {
            time: 2000
        }, function () {
            window.location.href = url;
        });
    }
});

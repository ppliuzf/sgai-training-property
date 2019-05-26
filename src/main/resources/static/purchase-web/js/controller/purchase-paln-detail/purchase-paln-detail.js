
$(function () {
    var infoId = $.getQueryString('id'),dataList,status = $.getQueryString('status');
    var dataListDown = [];
    function init() {
        changeTitle();
        getContent();
        // 点击返回
        $(document).on('click', '#cancel1', function() {
           window.location.href = './purchase-plan-list.html';
        });
          // 点击提交审核
        $(document).on('click', '#submit', function(e){
            $.pop({
                title: '提示',
                content: '确定提交当前计划？',
                btn: ['确认', '取消'],
                size:'sm',
                yes: function () {
                    submit();
                }
            });
        });
        // 点击撤销
        $(document).on('click', '#recall', function(e){
            $.pop({
                title: '提示',
                content: '确定撤销当前计划？',
                btn: ['确认', '取消'],
                size:'sm',
                yes: function () {
                    revoke();
                }
            });
        });
        // 点击编辑
        $(document).on('click', '#edit', function() {
            location.href = './add-purchase-plan.html?id=' + infoId;
         });
        //  点击阶段管理
        $(document).on('click', '#js-phase', function() {
            location.href = './purchase-phase-adm.html?id=' + infoId;
         });
        //  点击新增任务
        $(document).on('click', '#add', function() {
            location.href = './purchase-plan-apply-add.html?id=' + infoId;
         });
         // 移动至
		$(document).on('click', '.js-moveto', function (e) {
            
			if ($(this).hasClass('disabled')) {
				return false;
			} else {
				$('#selectPer').show();
				var taId = $(this).attr('data-id');
				//layer.open({
                    console.log(dataList.planStageVoList)
				$.pop({
					title: '任务移动至',
					type: 1,
					btn: ['确定', '取消'],
                    area: ['500px', '250px'],
                    size:'sm',
					content: template('plan-detail/apply-add-select', {
                        items: dataList.planStageVoList
                    }),
					//content: '111',
					yes: function(){       
						stId = $("#phase").val();
						taId =$(e.target).data('id');
						$('#modal-body').css('display', 'none');
                        $('.modal-backdrop').css('display', 'none');
                        setTimeout(function(){moveToPeriod(taId,stId)},300)
                        
					}
				});
			}
        });
        // 移除
        $(document).on('click', '.js-del', function (e) {
            
			if ($(this).hasClass('disabled')) {
				return false;
			} else {
				var taId = $(this).attr('data-taid');
				$.pop({
					title: '提示',
					type: 1,
					btn: ['确定', '取消'],
                    size:'sm',
					content:"确认删除该任务？",
					//content: '111',
					yes: function(){       
                        delTask(taId);
					}
				});
			}
        });
         
    }
    init();

    // 删除任务
    function delTask(taId){ 
        $.getData({
            url: 'order/taskDelete',
            query: {
                taskId:taId
            }
        }, function (data) {
            getContent()
        });
    }   
    // 提交
    function submit(){
        $.getData({
            url: 'listOrDetail/planSubmit',
            query: {
                id:infoId
            }
        }, function (data) {
            if(data){
                location.href = './purchase-paln-detail.html?id=' + infoId + '&status=' + 'dsh';
            }
        });
        
    }
    // 撤销
    function revoke(){
        $.getData({
            url: 'listOrDetail/planRevocation',
            query: {
                id:infoId
            }
        }, function (data) {
            if(data){
                location.href = './purchase-paln-detail.html?id=' + infoId + '&status=' + 'dtj';
            } 
        });     
    }
    // 获取页面所需数据
    function getContent() {
        $.getData({
            url: 'listOrDetail/getPlanDetail',
            query: {
                id:infoId
            }
        }, function (data) {
            $('#modal-body').css('display', 'none');
            $('.modal-backdrop').css('display', 'none');
            dataList = data;
            data.id = infoId;
            console.log(data.id)
            if(data.planDept){
                data.planDept = JSON.parse(data.planDept)[0].title;
            }
            data.status = status;
           // 引入模板
          renderCont(data);
        });
    }
    // 任务从一个阶段移动到另一个阶段
	function moveToPeriod (taId,stId) {
		$.getData({
			url: 'order/taskMoveTo',
			query: {
				taskId: taId,
				stageId: stId,
			}
		}, function (data) {
			if (data) {
                $('#modal-body').css('display', 'none');
                $('.modal-backdrop').css('display', 'none');
				setTimeout(function(){getContent()},100);
			}
		});
	}
    // 渲染数据
    function renderCont(data) {
        $('#order').html(template('plan-detail/plan-detail', {
            items: data
        }));
        if(data.planStageVoList && data.planStageVoList.length > 0){
            console.log(data.planStageVoList)
            if (data.planStageVoList.length == 1){
                if (data.planStageVoList[0].planTaskVoList){
                    $('#planList').html(template('plan-detail/plan-detail-list',{items:data} ));
                } else {
                    $('#planList').html(template('common/record-empty', {
                        colspan: 7
                    }));
                }
            } else {
                $('#planList').html(template('plan-detail/plan-detail-list',{items:data} ));
            }
            // 排序   
            for (var p = 0;p < data.planStageVoList.length;p++) {
                $.kmSort({
                    sortArea: '.js-sort',
                    sortItem: '.sort-'+ data.planStageVoList[p].id,
                    upEl: '.js-up',
                    downEl: '.js-down',
                    afterSort: function (id,flag) {
                        console.log(id,flag)
                        $.getData({
                            url: 'order/taskMove',
                            query: {
                                taskId:id,
                                upDown:flag
                            }
                        }, function (data) {
                            
                           if(data){
                            console.log(data)
                               setTimeout(function(){getContent()},100);
                           }
                        }); 
                    }
                });
            }
            if (data.planStageVoList.length == 1) {
                $('.js-moveto').addClass('disabled');
            }
        } else {
            renderEmpty();
        }
       
    }
    var detailId = '';
    // 获取详情
    function detailMain(){
        $.getData({
            url: '/listOrDetail/planTaskDetail',
            query: {
                id:detailId
            }
        }, function (data) {
            dataListDown = data.planDetailList;
        });
    }
    // 采购详情
    $(document).on('click', '.js-detail', function(){
        detailId = $(this).attr('data-id');
        detailMain();
        setTimeout(function(){
            $.pop({
                title: '采购详情',
                content: template('plan/detail', {
                    items: dataListDown
                }),
                noIcon:true,
                size: 'lg',
                isCancel: false,
                yes: function () {
                    
                }
            });
        },200);
    })
     // 渲染无数据
     function renderEmpty() {
        $('#planList').html(template('common/record-empty', {
            colspan: 8
        }));
    }
     // 根据url参数修改页面标题及其他内容
     function changeTitle(){
        
        if(status == 'dtj'){
            $(".js-title h4").html("采购计划详情-待提交");
            $("#dtjBtn").removeClass("hide");$("#dshBtn").addClass("hide");$("#yjjBtn").addClass("hide");     
        } else if(status == 'dsh'){
            $(".js-title h4").html("采购计划详情-待审核");
            $("#dshBtn").removeClass("hide");$("#dtjBtn").addClass("hide");$("#yjjBtn").addClass("hide");
            $("#editBtn").hide();
        }
        else if(status == 'ytg'){
            $(".js-title h4").html("采购计划详情-已通过");
            $("#yjjBtn").removeClass("hide");$("#dtjBtn").addClass("hide");$("#dshBtn").addClass("hide");
            $("#editBtn").hide();
        }
         else {
            $(".js-title h4").html("采购计划详情-已拒绝");
            $("#yjjBtn").removeClass("hide");$("#dtjBtn").addClass("hide");$("#dshBtn").addClass("hide");
            $("#editBtn").hide();
        }
    }
});

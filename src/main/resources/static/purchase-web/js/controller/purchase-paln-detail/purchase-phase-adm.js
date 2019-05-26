$(function () {
    var infoId = $.getQueryString('id'),dataList = [];
    function init() {
        getContent();
        // 点击取消
        $(document).on('click', '#cancle', function(e){
            history.go(-1);
        });   
        // 点击添加
        $(document).on('click', '#add', function(e){
            
            createItems();
        }); 
        // 点击保存
        $(document).on('click', '#save', function(e){
            save(infoId);
        }); 
        // 点击删除
		$(document).on('click', '.js-remove', function () {
            $(this).parents('.item').remove();
            dataList.pop();
            $.kmSort({
                sortArea: '#adm',
                sortItem: '.item',
                upEl: '.js-up',
                downEl: '.js-down'
            });
		});
    }
    init();
    // 创建项
	function createItems(id) {
        dataList.push({});
		var itemCreated = [{
			dataId:  'time' + new Date().getTime(),
        }];
        console.log(itemCreated)
		$('#adm').append(template('plan-detail/purchase-phase-adm', {
			items: itemCreated
		}));
		$('#adm .disabled').removeClass('disabled');
		setTimeout(function () {
			$('#adm .item').addClass('show');
		}, 20);
		$.kmSort({
			sortArea: '#adm',
			sortItem: '.item',
			upEl: '.js-up',
			downEl: '.js-down'
		});
	}
    // 处理阶段数据
    function getList(){
        dataList.shift();
        for( var i=dataList.length-1 ;i >=0 ; i--){
            dataList[i]={};
            dataList[i].stageName = $('#adm>div').eq(i).find('input').val();
            dataList[i].id = $('#adm>div').eq(i).data('id');
            if(dataList[i].id.slice(0,4)=='time'){
                dataList[i].id="";
            }
            dataList[i].planId = infoId;
            console.log(dataList[i].stageName)
            // if(!dataList[i].stageName){
            //     dataList.splice(i, 1);
            // }
        }
        console.log(dataList);
        return dataList;
    }
    // 清空日期选择
    $(document).on('click', '.js-date-clean', function () {
        $(this).prev().val('');
    });
     // 获取列表
     function getContent() {
        if (infoId) {
            $.getData({
                url: 'listOrDetail/getStageList',
                query: {
                    id: infoId
                }
            }, function(data) {
                if (data) {     
                    dataList = data;         
                    // 引入模板
                    renderCont(dataList);
                    if(dataList.length ==1){
                        createItems();
                    }            
                }
            });
        }
    }
    function renderCont(dataList) {
        dataList.id = infoId;
        for(var i=0 ; i < dataList.length ; i++){
            dataList[i].dataId = dataList[i].id || new Date().getTime();
        }
        $('#adm').html(template('plan-detail/purchase-phase-adm', {
            items: dataList
        }));
        $.kmSort({
            sortArea: '#adm',
            sortItem: '.item',
            upEl: '.js-up',
            downEl: '.js-down'
        });
        
    }
    // 点击保存
    function save(infoId) {
        var param = getList();
        if(param && param.length>0){
            $.getData({
                url: 'order/saveOrUpdateStage',
                query: {},
                body: param
            }, function (data) {
                if (data) {
                        window.history.go(-1);
                        // console.log(data);
                }
            });
        } else{
            console.log(param)
            $.getData({
                url: 'order/clearStage',
                query: {
                    planId:infoId
                }
            }, function (data) {
                if (data) {
                        window.history.go(-1);
                        // console.log(data);
                }
            });
        }
       
    }
});

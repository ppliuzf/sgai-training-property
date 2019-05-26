$(function () {
    var infoId = $.getQueryString('infoId'),
    orderList = [],
    selectDept=[],
    param = {},
    orderDetail = {},
    current = 0,
    whInParamList = [],
    currPage = 1,
    whMainName = "";
    // diaoArr=[]; 
    function init() {
        // getContent();
        getOrderList();
       // 点击保存
        $(document).on('click', '#save', function(e){
            console.log(whInParamList);        
            console.log($("#stepOne").find("input:checked").length);    
            var checkedNum = $("#stepOne").find("input:checked").length;
            var saveNum = whInParamList.length;
            if (saveNum == 0 && checkedNum == 0){
                $.alert("请至少勾选一个带有物料信息的入库单");
            }else if(checkedNum > saveNum ){
                $.alert("已勾选单子中存在没有物料或未点击入库的入库单");
            }else{
                submit(whInParamList);
            }
           
        });
        // 点击取消
        $(document).on('click', '#cancle', function(e){
            window.location.href="in-list.html"
        });
       // 更改仓库类型 
       $(document).on('change', '#whType', function(e){ 
        whMainName = ""; 
        selectDept=[];
        getSelectDept();
        toBackDepot($("#whType").val(),"",getMetarialList);
        //getMetarialList();//为了保存功能获取仓库和仓库类型的信息
        // toBackDepot($(this).val(),$("#selectDept").val());

      });
      // 更改仓库 
      $(document).on('change', '#selectDept', function(e){
        whMainName = "";
        toBackDepot("",$("#selectDept").val(),getMetarialList);
        // getMetarialList();
    });
    // 勾选或取消入库单
    $(document).on('change', '.stepOne input', function(e){  
        var $this = $(this);
        whMainName = "";
        $this.parents("li").attr("isClick",true);
        if($this.prop('checked')) {//勾选
            $this.parent('label').next('a').addClass('checked');
            // for(var i=0;i<diaoArr.length;i++){
            //     if(diaoArr[i]==$this.next().text()) return;
            // }
            // diaoArr.push($this.next().text());
        } else {//取消
            $this.parent('label').next('a').removeClass('checked');
            $this.parents('li').removeAttr("random");
            if($this.parents('li').index() == current) {
                $('.js-right').hide();
                current = '';
            }
            // for(var i=0;i<diaoArr.length;i++){
            //     if(diaoArr[i]==$this.next().text()) {
            //         diaoArr.splice(i,1);
            //     }
            // }
            for(var i=0;i<whInParamList.length;i++){
                if(($("#whOutType").val() == 1 && whInParamList[i].allotId == $this.parents("li").attr("data-id")) || ($("#whOutType").val() == 2 && whInParamList[i].orderId == $this.next().text())) {
                    whInParamList.splice(i,1);
                }
            }
        }
        // console.log(diaoArr);

    });
    // 点击入库
    $(document).on('click', '.stepOne a', function(e){
        whMainName="";
        var statu = $("#whOutType").val() == 1 ? 1 : 2;
        currPage = 1;
        var $this = $(this),
        index = $this.parent('li').index();
        var oId = $this.parents("li").attr("data-id");
        var random = $this.parents("li").attr("random");
        console.log(index,current)
        if($this.hasClass('checked') && index !== current) {
            $('.js-right').show(); 
            current = index;
            selectDept = []
            orderDetail = orderList[current]
            console.log(orderDetail);
            renderOrderDetail(statu,orderDetail);
            getSelectDept(getMetarialList);
        }
        console.log(whInParamList);
    });
    //   更改申请单类型
    $(document).on('change', '#whOutType', function(e){  
        orderList = [];
        $('#warehouseReceipt').val("");
        $('#stepTwo').html('');
        current = 0;
        whInParamList = [],
        getOrderList();
      });
    }
    init();
    // 获取申请单数据
    function getOrderList(){
        // 判断申请单类型
        var url,statu,query;
        if($('#whOutType').val() == 1){
            url = 'allot/searchList4InOut';
            statu = 1;
            query = { hasOrder:1 };
        } else{
            url = 'order/searchList';
            statu = 2;
            query = {
                pageNum:1,
                pageSize:10000,
                outOrIn: 0 //入库传0，出库传1
            }
        }
        $.getData({
            url:url,
            query:query

        },function(data){
            var dataJudge = statu == 1 ? data.length : data.list.length;
            if(dataJudge>0){
                orderList = statu == 1 ? data : data.list;
                //orderList[0].status = $('#whOutType').val();
                orderDetail = orderList[0]
                console.log(orderList)
                renderOrderList(statu,orderList)
                renderOrderDetail(statu,orderDetail);
                getSelectDept(getMetarialList);
                $(".js-right").show();
            // if(statu==1){
            //     diaoArr.push(orderList[0].allotNo);
            // }else{
            //     diaoArr.push(orderList[0].orderNo);
            // }
            // var aLi = $("#stepOne").find("span");
            // for(var i=0 ; i< aLi.length ; i++){
            //     for(var j=0;j<diaoArr.length;j++){
            //         console.log(aLi.eq(i).text(),diaoArr[j]);
            //         if (aLi.eq(i).text() == diaoArr[j]){
            //             aLi.eq(i).prev().prop("checked",true);
            //             aLi.eq(i).parent().next().addClass("checked");
            //         }
            //     }
            // }
            // for(var i=0 ;i<$("#stepOne").find("span").length;i++){

            // }
        }else{
            renderOrderList();
            $(".js-right").hide();
        }
            
        })   
    }  
    //改变仓库或类型向后台传值
    function toBackDepot(whType,whId,fn){
        var orderId = "",query={};
        $("#stepOne").find("span").each(function(){
            var _this=$(this);
            var aText=_this.text();
            var sText=$(".allotNo").text();
            var aRandom = $("#getRandom").attr("random");
            if(aText == sText){
                orderId = _this.parents("li").attr("random");
             }
        })
        if(whType == ""){
            query={
                orderId :orderId,
                whId:whId 
            }
        }else if(whId == ""){
            query={
                orderId :orderId,
                whType:whType 
            }
        }
        $.getData({
            url:"/depotInManage/updateWhInfo",
            query:query
        },function(data){
            if(data == null){
                fn&&fn();
            }
        })
    }
    // 入库数量聚焦时如果为0清空内容
    $(document).on('focus','.numbers', function() {
        if ($(this).val() == 0) {
            $(this).val('')
        }
    });
    // 入库数量失焦时如果为空赋值为0
    $(document).on('blur','.numbers', function() {
        console.log($(this).val())
        if ($(this).val() == '') {
            $(this).val(0)
        };
    });
     // 修改个数
     $(document).on('keyup', '.numbers', function(){
        var self = $(this);
        var thisLargeNumber = self.attr('applyCount');
        if (parseInt(self.val()) > thisLargeNumber ) {
            self.val(thisLargeNumber); 
        }
        if (parseInt(self.val()) < 0 ) {
            self.val("0");
        }
        if (!checkNumber(self.val())) {
            $.toast("请输入正整数");
            self.val(0);
            return false;
        }
    });
    // 正则验证是否数字
    function checkNumber(theObj) {
        var reg = /^[0-9]+$/;
        if (reg.test(theObj)) {
            return true;
        }
        return false;
    }
     
    //  渲染申请单列表
    function renderOrderList(statu,orderList) {
        $('.stepOne').html(template('add/in-addAll-left', {
            statu:statu,
            items: orderList
        }));
        $('.stepOne').find('input').eq(0).prop('checked', true);
        $('.stepOne').find('li').eq(0).attr('isClick', true);
        $('.stepOne').find('a').eq(0).addClass('checked');
    }
    // 渲染申请单详情
    function renderOrderDetail(statu,orderDetail) {
        console.log(orderList);
        $('.stepTwo').html(template('add/in-addAll-two', {
            statu:statu,
            items: orderDetail
        }));
    }
    // 渲染仓库下拉
    function renderselectDept(data){
        $('#selectDept').html(template('add/selectDept', {
            items: data
        }));
        // if(whMainName.length>0){
        //      $("#selectDept").val(whMainName);
        // }
        
         
    }
    // 渲染物料列表
    function renderWhList(data,statu) {
        $('.stepThree').html(template('add/in-addAll-three', {
            items: data,
            statu:statu
        }));
    }
    // 获取仓库列表
    function getSelectDept(fn){
        $.getData({
            url: 'common/byWhType',
            query: {
                whType : $('#whType').val()
            }
        }, function(data) { 
            console.log(data);
            selectDept = data;
            renderselectDept(selectDept);
            for(var i=0;i<selectDept.length;i++){
                if(selectDept[i].id == whMainName){
                    $("#selectDept").val(whMainName);
                }
            }
            fn&&fn();
        });
    }
    //获取物料列表
    function getMetarialList(isClick,n){
        var isAllot = $("#whOutType").val() == 1 ? 0 : 1;
        var orderNo = $("#whOutType").val() == 1 ? $(".allotNo").attr("data-id") : $(".allotNo").text();
        var orderId = "";
        var backStr = {
            pageNum:currPage,
            pageSize:10,
            isAllot:isAllot,
            orderNo:orderNo
        }
        //判断是否存在虚拟值
        $("#stepOne").find("span").each(function(){
            var _this=$(this);
            var aText=_this.text();
            var sText=$(".allotNo").text();
            var aRandom = $("#getRandom").attr("random");
            if(aText == sText && _this.parents("li").attr("random")){
                delete backStr["orderNo"];
                backStr.orderId = _this.parents("li").attr("random");
             }
        })
        $.getData({
            url:"/order/searchWarehousMatList",
            query:backStr
        },function(data){
            console.log(data);
            renderWhList(data.list,isAllot);
            $.renderPage({
                count: data.count,
                curr: currPage,
                jump: function (n) {
                    currPage = n;
                    getMetarialList(n);
                }
            });
            $("#stepOne").find("span").each(function(){
                var _this=$(this);
                var aText=_this.text();
                var sText=$(".allotNo").text();
                var aRandom = $("#getRandom").attr("random");
                if(aText == sText && !_this.parents("li").attr("random")){
                     _this.parents("li").attr("random",aRandom);
                }
            })
            if(data.list.length>0){
                getSaveData();
            }
            if(data.list.length>0&&data.list[0].whType){
                $("#whType").val(data.list[0].whType);
            }
            if(data.list.length>0 && data.list[0].whId){
                whMainName = data.list[0].whId;
                getSelectDept(getSaveData);
            }
        })
    }
    //收集保存信息
    function getSaveData(){
        $("#stepOne").find("span").each(function(){
            var _this=$(this);
            var aText=_this.text();
            var sText=$(".allotNo").text(); 
            if(aText == sText){
                if($("#whOutType").val() == 1){
                    for(var i = 0 ; i < whInParamList.length ; i++){
                        if(whInParamList[i].allotId == _this.parents("li").attr("data-id")){
                            whInParamList.splice(i,1);
                        }
                    }
                    whInParamList.push({
                        allotId:_this.parents("li").attr("data-id"),
                        allotName:sText,
                        suppliesDetailsId:_this.parents("li").attr("random"),
                        whId:$("#selectDept").val(),
                        whInType:$("#whOutType").val(),
                        whName:$("#selectDept option:selected").text(),
                        whType:$("#whType").val()
                    })
                }else{
                    for(var i = 0 ; i < whInParamList.length ; i++){
                        if(whInParamList[i].orderId == _this.text()){
                            whInParamList.splice(i,1);
                        }
                    }
                    whInParamList.push({
                        orderId:sText,
                        orderName:sText,
                        suppliesDetailsId:_this.parents("li").attr("random"),
                        whId:$("#selectDept").val(),
                        whInType:$("#whOutType").val(),
                        whName:$("#selectDept option:selected").text(),
                        whType:$("#whType").val()
                    })
                }
                console.log(whInParamList);
            }
            
        })
    }
    //改变物料数量给后台传值事件
    $(document).on("blur",".numbers",function(){
        var self = $(this);
        var oId = self.parents("tr").find("td:eq(0)").attr("id");
        if(self.val()>Number(self.parent("td").prev().text())){
            if($("#whOutType").val() == 1){
                $.alert("请输入不超过调拨数量的数值");
            }else{    
                $.alert("请输入不超过采购数量的数值");
            }
            self.val(0);
        }else(
            getMaterielNum(oId,self.val())
        )
        
    })
    //改变物料数量给后台传值
    function getMaterielNum(id,num){
        $.getData({
            url: '/depotInManage/addDepotMatInfo',
            body: {
                id:id,
                matRealNum:num
            }
        }, function(data) { 
            // selectDept = data
            // renderWhList(selectDept);
        });
    }
    // 点击保存
    function submit(infoId) {
        $.getData({
            url: '/depotInManage/addDepotBatch',
            body: 
                infoId 
        }, function(data) { 
            if(data){
                $.toast("操作成功",{timer:2000});
                setTimeout(function(){
                    window.location.href="in-list.html"
                },2500);
            }
        });
    };
});

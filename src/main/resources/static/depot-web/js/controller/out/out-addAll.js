$(function () {
    var infoId = $.getQueryString('infoId'),
    orderList = [],
    selectDept=[],
    param = {},
    orderDetail = {},
    current = 0,
    whInParamList = [],
    checkedOrder = [],
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
                $.alert("请至少勾选一个带有物料信息的出库单");
            }else if(checkedNum > saveNum ){
                $.alert("已勾选单子中存在没有物料或未点击出库的出库单");
            }else{
                submit(whInParamList);
            }
        });

        // 点击取消
        $(document).on('click', '#cancle', function(e){
            window.location.href="out-list.html"
        });
        // 更改仓库类型 
        $(document).on('change', '#whType', function(e){ 
            whMainName = "";  
            selectDept=[];
            getSelectDept();
            toBackDepot($("#whType").val(),"",getMetarialList);
            // getSelectDept(toBackDepot)
            // toBackDepot($(this).val(),$("#selectDept").val());
        });
        // 更改仓库 
      $(document).on('change', '#selectDept', function(e){
        whMainName = ""; 
        toBackDepot("",$("#selectDept").val(),getMetarialList);
        //getMetarialList();
    });
        // 勾选或取消出库单
        $(document).on('change', '.stepOne input', function(e){ 
            var $this = $(this);
            whMainName = "";
            $this.parents("li").attr("isClick",true);
            if($this.prop('checked')) {
                $this.parent('label').next('a').addClass('checked');
                // for(var i=0;i<diaoArr.length;i++){
                //     if(diaoArr[i]==$this.next().text()) return;
                // }
                // diaoArr.push($this.next().text());
            } else {
                $this.parent('label').next('a').removeClass('checked');
                $this.parents('li').removeAttr("random");
                console.log($this.parents('li'),current)
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
                    if(($("#whOutType").val() == 1 && whInParamList[i].allotId == $this.parents("li").attr("data-id")) || ($("#whOutType").val() == 2 && whInParamList[i].matApplyId == $this.parents("li").attr("data-id"))) {
                        whInParamList.splice(i,1);
                    }
                }
            }
            // console.log(diaoArr);
        });
        // 点击出库
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
        });
        //   更改申请单类型
        $(document).on('change', '#whOutType', function(e){  
            // 更改申请单类型后需要重置数据
            orderList = [];
            current = 0;
            checkedOrder = [];
            selectDept=[];
            orderDetail = {};
            whInParamList = []
            getOrderList();
        });
    }
    init();
    // 出库数量聚焦时如果为0清空内容
    $(document).on('focus','.numbers', function() {
        if ($(this).val() == 0) {
            $(this).val('')
        }
    });
    //改变物料数量给后台传值事件
    $(document).on("blur",".numbers",function(){
        if ($(this).val() == '') {
            $(this).val(0)
        };
        var self = $(this);
        var oId = self.parents("tr").find("td:eq(0)").attr("id");
        if(self.val()>Number(self.parent("td").prev().text())){
            $.alert("请输入小于库存数量的数值");
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
    
    // 获取申请单数据
    function getOrderList(){
        // 判断申请单类型
        var url,statu;
        var query={};
        if($('#whOutType').val() == 2){
            url = 'common/suppliesList';
            statu = 2;
            query = {pageNum:1,pageSize:10000}
        } else{
            url = 'allot/searchList4InOut';
            statu = 1;
            query = { hasOrder:0 };
        }
        $.getData({
            url:url,
            query:query
        },function(data){
            console.log(data);
            var dataJudge = statu == 1 ? data.length : data.list.length;
            if(dataJudge>0){
            orderList = statu == 1 ? data : data.list;
            //orderList[0].status = $('#whOutType').val();
            orderDetail = orderList[0]
            checkedOrder.push(orderList[0].allotNo);
            renderOrderList(statu,orderList)
            renderOrderDetail(statu,orderDetail);
            getSelectDept(getMetarialList);
            $(".js-right").show();
            // if(statu==1){
            //     diaoArr.push(orderList[0].allotNo);
            // }else{
            //     diaoArr.push(orderList[0].applyNo);
            // }
            // var aLi = $("#stepOne").find("span");
            // for(var i=0 ; i< aLi.length ; i++){
            //     for(var j=0;j<diaoArr.length;j++){
            //         if (aLi.eq(i).text() == diaoArr[j]){
            //             aLi.eq(i).prev().prop("checked",true);
            //             aLi.eq(i).parent().next().addClass("checked");
            //         }
            //     }
            // }
            // $("#stepOne").find("span").each(function(){
            //     var self=$(this);
            //     for(var i=0;i<diaoArr.length;i++){
            //         if(diaoArr[i]==self.text()) return;
            //     }
            //     if(self.prev().prop("checked")==true){
            //         diaoArr.push(self.text());
            //     }
            // })
            
            // console.log(diaoArr);
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
                // debugger;
                fn&&fn();
            }
        })
    }
    
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
            $.toast("请输入正整数",{timer:2000});
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
        console.log(orderList);
        $('.stepOne').html(template('add/out-addAll-left', {
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
        $('.stepTwo').html(template('add/out-addAll-two', {
            items: orderDetail,
            statu:statu
        }));
    }
    // 渲染仓库下拉
    function renderselectDept(data){
        $('#selectDept').html(template('add/selectDept', {
            items: data
        }));
    //     if(whMainName.length>0){
    //         $("#selectDept").val(whMainName);
    //    }
    }
    // 渲染物料列表
    function renderWhList(data) {
        $('.stepThree').html(template('add/out-addAll-three', {
            items: data
        }));
    }
    // 获取仓库列表
    function getSelectDept(fn) {
        $.getData({
            url: 'common/byWhType',
            query: {
                whType : $('#whType').val()
            }
        }, function(data) {
            selectDept = data
            renderselectDept(selectDept)
            for(var i=0;i<selectDept.length;i++){
                if(selectDept[i].id == whMainName){
                    $("#selectDept").val(whMainName);
                }
            }
            // debugger
            fn&&fn();
        });
    }
    //获取物料列表
    function getMetarialList(n){
        // console.log($("#selectDept").val());
        var isAllot = $("#whOutType").val() == 1 ? 0 : 1;
        var orderNo = $("#whOutType").val() == 1 ? $(".allotNo").attr("data-id") : $(".allotNo").text();
        var orderId = "";
        var backStr = {
            isAllot:isAllot,
            orderNumber:orderNo,
            whId:$("#selectDept").val()
        }
        //判断是否存在虚拟值
        $("#stepOne").find("span").each(function(){
            var _this=$(this);
            var aText=_this.text();
            var sText=$(".allotNo").text();
            var aRandom = $("#getRandom").attr("random");
            if(aText == sText && _this.parents("li").attr("random")){
                backStr.orderId = _this.parents("li").attr("random");
             }
        })
        $.getData({
            url:"/depotOutManage/detilList",
            query:{
                pageNo:currPage,
                pageSize:10
            },
            body:backStr
        },function(data){
            console.log(data);

            renderWhList(data.list); 
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
                        orderId:_this.parents("li").attr("random"),
                        whId:$("#selectDept").val(),
                        whOutType:$("#whOutType").val(),
                        whName:$("#selectDept option:selected").text(),
                        whType:$("#whType").val()
                    })
                }else{
                    for(var i = 0 ; i < whInParamList.length ; i++){
                        if(whInParamList[i].matApplyId == _this.parents("li").attr("data-id")){
                            whInParamList.splice(i,1);
                        }
                    }
                    whInParamList.push({
                        matApplyId:_this.parents("li").attr("data-id"),
                        matApplyName:sText,
                        orderId:_this.parents("li").attr("random"),
                        whId:$("#selectDept").val(),
                        whOutType:$("#whOutType").val(),
                        whName:$("#selectDept option:selected").text(),
                        whType:$("#whType").val()
                    })
                }
                console.log(whInParamList);
            }
            
        })
    }
    // 点击保存
    function submit(infoId) {
        $.getData({
            url: '/depotOutManage/addListDepot',
            body: 
                infoId 
        }, function(data) { 
            if(data.length>0){
                $.pop({
                    content:data,
                    size:"lg",
                    yes:function(){
                        window.location.href="out-list.html";
                    }
                });
            }else{
                $.toast("操作成功",{timer:2000});
                setTimeout(function(){
                    window.location.href="out-list.html"
                },2500);
            }
        });
    }
});

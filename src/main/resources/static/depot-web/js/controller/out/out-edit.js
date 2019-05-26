
$(function () {
    var infoId = $.getQueryString('infoId'),
    param = {} ;
    param.suppliesDetails = [];
    param.id = infoId;
    param.whOutId = infoId;
    var currPageYL = 1;
    function init() {
        getContent();
        // 点击返回
        $(document).on('click', '#cancel1', function(e){
            location.href = './out-list.html'
        }); 
        // 改变数量
        $(document).on('change', '.matRealNum', function(e){
            var self = $(this);
            var obj = {};
            var thisId = self.attr('ids');
            obj.id = self.attr('ids');
            obj.matRealNum = self.val();
            obj.matTypeId = self.attr('matTypeId');
            // 判断出库数量是否多次修改过同一条数据，如果不是同一条数据，就向数组中添加，如果是同一条数据，就不添加了，直接修改数据
            if (param.suppliesDetails.length > 0){
                for(var i=0; i<param.suppliesDetails.length; i++) {
                    if (param.suppliesDetails[i].id == thisId) {
                        param.suppliesDetails.splice(i, 1);
                    }
                }
            }
            param.suppliesDetails.push(obj);
            console.log('修改后的数据');
            console.log(param.suppliesDetails);
        }); 
        // 点击保存
        $(document).on('click', '#save', function(e){
            save();
            
        });
    }
    init();
    // 获取页面所需数据
    function getContent() {
        $.getData({
            url: 'depotOutManage/depotDetil',
            query: {
                id:infoId,
                pageNo:currPageYL,
                pageSize:10
            }
        }, function (data) {
            param.whStat = data.whStat ;
            param.whId = data.whId ;
            // param.suppliesDetails = [];
            // console.log(data.warehouseOutMats)
            // for(var i = 0 ; i < data.warehouseOutMats.list.length; i++){
            //     param.suppliesDetails[i] = {};
            //     param.suppliesDetails[i].id = data.warehouseOutMats.list[i].id;
            //     param.suppliesDetails[i].matRealNum = data.warehouseOutMats.list[i].matRealNum;
            //     param.suppliesDetails[i].matTypeId  = data.warehouseOutMats.list[i].matTypeId ;
            // }
            // console.log('用料明细列表数据'+param.suppliesDetails)
            // 引入模板
            renderCont(data);
            $.renderPage({
                count: data.warehouseOutMats.count,
                curr: currPageYL,
                jump: function(n) {
                    currPageYL = n;
                    getContent();
                }
            });
        });
        
    }
    // 渲染数据
    function renderCont(data) {
        $('#detailCont').html(template('detail/out-edit-detail', {
            items: data
        }));

        
        
        if(data.warehouseOutMats && data.warehouseOutMats.list.length > 0){
            data.warehouseOutMats.list.forEach(function(item){
                console.log('数组中的值');
                console.log(item);
    
                var matNum = item.matNum; // 库存数量
                var matNeetNum = item.matNeetNum; // 所需数量
                var thisLargeNumber;
                var outNumber;
                if (matNum < matNeetNum) {
                    thisLargeNumber = matNum;
                } else {
                    thisLargeNumber = matNeetNum;
                }
                item.largeNumber = thisLargeNumber;
                item.outNumber = parseInt(thisLargeNumber) - parseInt(item.matRealNum);
                console.log('剩余出库量', item.outNumber);
            });
            console.log(data.warehouseOutMats.list);
            $("#materielList").html(template('add/wuliao-list2', {
                items: data.warehouseOutMats.list
            }));
        } else {
            renderEmpty()
        }
    }
      // 渲染无数据
      function renderEmpty() {
        $('#materielList').html(template('common/record-empty', {
            colspan: 5
        }));
    }
     // 收集出库单数据
     function selectData() {
        var reg=/\D/,flag1=0,flag2=1;
        for(var i=0; i< param.suppliesDetails.length; i++){
            // param.suppliesDetails[i].matRealNum = $('.matRealNum').eq(i).val();
            if(reg.test(param.suppliesDetails[i].matRealNum) || (param.suppliesDetails[i].matRealNum < 0)) {
                $.msg('出库数量只能为自然数', 2000);
                return false;
            }
            if(param.suppliesDetails[i].matRealNum > 0) flag1++;
            if(param.suppliesDetails[i].matRealNum > param.suppliesDetails[i].matNeetNum){
                $.msg('出库数量不能大于所需数量', 2000);
                return false;
            } else if(param.suppliesDetails[i].matRealNum < param.suppliesDetails[i].matNeetNum){
                flag2 = flag2*0;
            }
            console.log('提交的数组');
            console.log(param.suppliesDetails);
            // return false;
        }
        
        if(flag1 == 0) {
            param.whStat = 1;
        } else if(flag2 == 0){
            param.whStat = 2;
        } else{
            param.whStat = 3;
        }
        return param;
    }
     // 出库数量修改个数
     $(document).on('keyup', '.numbers', function(){
        var self = $(this);
        var thisLargeNumber = self.attr('max');
        // var matNum = parseInt(self.attr('matNum')); // 库存数量
        // var matNeetNum = parseInt(self.attr('matNeetNum')); // 所需数量
        // var thisLargeNumber;
        // console.log(matNum);
        // console.log(matNeetNum);
        // if (matNum < matNeetNum) {
        //     thisLargeNumber = matNum;
        // } else {
        //     thisLargeNumber = matNeetNum;
        // }
        console.log('最大值'+thisLargeNumber);
        // var thisLargeNumber = self.attr('applyCount');
        if (parseInt(self.val()) > thisLargeNumber ) {
            self.val(thisLargeNumber); 
        }
        if (parseInt(self.val()) < 0) {
            self.val("0");
        }
        if (!checkNumber(self.val()) || self.val() == 0) {
            $.toast("请输入正整数", { timer: 2000 });
            self.val(0);
            return false;
        }
    });
    // 出库数量聚焦时如果为0清空内容
    $(document).on('focus','.numbers', function() {
        if ($(this).val() == 0) {
            $(this).val('')
        }
    });
    // 出库数量失去焦点保存数据
    $(document).on('blur', '.numbers', function () {
        var self = $(this);
        var selfVal = self.val();
        if (selfVal == '') {
            self.val(0)
        };
        $.getData({
            url: 'common/instantSaving',
            body: {
                id: self.parents("tr").find('td').eq(0).attr('id'),
                matRealNum: self.val()
            }
        }, function (data) {
            if (data) {
                // $.msg("出库数量已保存");        
            }
        });
    });
    // 正则验证是否数字
    function checkNumber(theObj) {
        var reg = /^[0-9]+$/;
        if (reg.test(theObj)) {
            return true;
        }
        return false;
    }
    // 保存
    function save(){
        var param = selectData();
        if (param.suppliesDetails.length ==0) { //代表数据没有修改，不让提交
            $.toast('请修改出库数量后再提交',{timer:2000});
            return false;
        } else {
            if(param){
                console.log(param)
                $.getData({
                    url: 'depotOutManage/updateDepot',
                    body: param
                }, function (data) {
                    location.href='./out-list.html';
                });
            } else {
                return false;
            }
        }
       
    }
});

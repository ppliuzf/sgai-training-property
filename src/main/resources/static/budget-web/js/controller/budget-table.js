$(function() {
    var recordId = $.getQueryString('id');
    var tplType = $.getQueryString('type');
    var complete = true;
    var isSave = false;
    var monthNum = [0,1,2,3,4,5,6,7,8,9,10,11];
    //获取表头
    function getTableHeader() {
        $.getData({
            url: '/budget/budgetTableHeader',
            query: {
            recordId: recordId
            }
        }, function (data) {
            monthNum = [];
            monthNum = data.dataList.month;
            renderTableHeader(data.dataList);
        });
    }
    //获取表格内容
    function getTableContent() {
        $.getData({
            url: '/budget/budgetTableContent',
            query: {
            recordId: recordId
            }
        }, function (data) {
            renderTableContent(data);
        });
    }
    function renderTableHeader(params) {
        var data = {
            list: params,
            type: tplType
        }
        $('.js-header').html(template('common/budget-table-header', {
            items: data
        }));
    }
    function renderTableContent(params) {
        // debugger
        var data = {
            list: params,
            monthNum: monthNum,
            type: tplType //1录入预算，2录入支出，3录入详情
        }
        $('.js-body').html(template('common/budget-table-tbody', {
            items: data
        }));
    }
    
    // $(document).on('click', '.iptbudget', function () {

    // }
    function getBudgets(params) {
        var budgets = [];
        $('.iptbudget').each(function (datas) {
           var obj={
            rsiId: $(this).attr('dataId'),
            budget: $(this).val()? $.trim($(this).val()) : ''
           }
            budgets.push(obj);
        });
        return budgets
    }
    $(document).on('click', '.js-save', function () {
        if (!complete) {
            return false;
        }
        complete = false;
        var saveData = {};
        saveData.entryList = getBudgets();
        saveData.recordId = recordId;
        var $self = $(this);
        $.getData({
            url: '/budget/inputBudget',
            body: saveData
        }, function (data) {
            isSave = true;
            complete = true;
            $self.removeClass('btn-primary').addClass('btn-default');
            $('.js-submit').removeClass('btn-default').addClass('btn-primary');
            // $('.btn-operation').hide();
            // $('.btn-close').show();
            msgToast('保存成功', 'success', 2000);
        });
    });
    //点击取消
    
    $(document).on('click', '.js-expend-close,.js-input-cancel', function () {
        window.history.back();
    });
    // 点击提交
    $(document).on('click', '.js-submit', function () {
        if (!isSave){
            msgToast('请先保存', 'error', 2000);
            return false;
        }
        if (!complete) {
            return false;
        }
        complete = false;
        $.getData({
            url: '/approval/submit',
            query: {
              id: recordId
            }
        }, function (data) {
            isSave = true;
            complete = true;
            // $('.btn-operation').hide();
            // $('.btn-close').show();
            msgToast('提交成功', 'success', 2000);
            setTimeout(() => {
               window.location.href = 'plan-list.html';
                // window.history.back();
            }, 3000);
        });
    });
    //录入支出
    function getExpendsMonth() {
        var expends = [];
        $('.js-body tr').each(function (datas) {

            var inputExpends= [];
            var expendsItem = {};
            var expendsSum = 0;
            //预算金额budget
            var budgetNum = $.trim($(this).find('.budget-num').text());
            //console.log("打印预算---------");
            //console.log(budgetNum);
            $(this).find('input').each(function (datas) {

                inputExpends.push($(this).val());
                expendsSum = expendsSum +　Number($(this).val());
            });
            var plu = accSubtr(budgetNum,expendsSum);
            $(this).find('.expend-num').text(plu);

            if (budgetNum - expendsSum<0){
                $(this).find('.expend-num').addClass('redw');
            }
            var datasExpends = inputExpends.join(',');
            expendsItem = {
                budget: budgetNum,
                expend: datasExpends,
                rsiId: $(this).attr('dataid'),
                surplus: $(this).find('.expend-num').text()
            }
            expends.push(expendsItem);
        });
        return expends
    }
    //减法运算
    function accSubtr(arg1,arg2){
        var r1,r2,m,n;
        try{r1=arg1.toString().split(".")[1].length;}catch(e){r1=0;}
        try{r2=arg2.toString().split(".")[1].length;}catch(e){r2=0;}
        m=Math.pow(10,Math.max(r1,r2));
        //动态控制精度长度
        n=(r1>=r2)?r1:r2;
        return ((arg1*m-arg2*m)/m).toFixed(n);
    }

    // function getExpends(params) {
    //     getExpendsMonth ();
    //     var budgets = [];
    //     $('.iptbudget').each(function (datas) {
    //        var obj={
    //         rsiId: $(this).attr('dataId'),
    //         budget: $(this).val()? $(this).val() : ''
    //        }
    //         budgets.push(obj);
    //     });
    //     return budgets
    // }
    
    $(document).on('click', '.js-expend-cancel', function () {
        window.history.back();

    });
    $(document).on('click', '.js-expend-save', function () {
        if (!complete) {
            return false;
        }
        complete = false;
        var saveData = {};
        saveData.entryList = getExpendsMonth();
        //console.log(saveData.entryList);

        saveData.recordId = recordId
        $.getData({
            url: '/budget/inputExpend',
            body: saveData
        }, function (data) {
            complete = true;
            $('.btn-operation').hide();
            $('.btn-close').show();
            msgToast('提交成功', 'success', 2000);
            setTimeout(() => {
            // window.location.href(goUrl);
                window.history.back();
            }, 3000);
        });
    });
    
    function msgToast (val, type, time) {
        $.toast(val, {
            timer: time,
            type: type
        });
    }
    function init(){
        getTableHeader();
        getTableContent();
    }
    init();
});
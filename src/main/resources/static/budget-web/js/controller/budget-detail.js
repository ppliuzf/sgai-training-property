$(function() {
    var recordId = $.getQueryString('id');
    var tplType = 3;
    var parentEl = $('.js-information-con');
    var monthNum = [0,1,2,3,4,5,6,7,8,9,10,11];
    // 获取基础信息数据
    function getBasic () {
        $.getData({
			url: '/budget/budgetDetail',
			query: {
				recordId: recordId
			}
		}, function(data) {
            var textState = data.state == 2 ?'审核结果-已通过' : data.state == 3? '审核结果-已拒绝' :'预算详情';
            $('.budget-state').text(textState);
            renderSelectBasic('.js-detail-basic', data);
		});
    }
    // 渲染基础信息
	function renderSelectBasic(name, data) {
		$(name).html(template('common/budget-detail-basic', {
			item: data
		}));
    }
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
        parentEl.find('.js-header').html(template('common/budget-table-header', {
            items: data
        }));
    }
    function renderTableContent(params) {
        var data = {
            monthNum: monthNum,
            list: params,
            type: tplType //1录入预算，2录入支出，3录入详情
        }
        parentEl.find('.js-body').html(template('common/budget-table-tbody', {
            items: data
        }));
    }
    //点录入记录
    function recordDetail (inputId) {
        parentEl = $('.js-detail-con');
        getTableHeader();
        $.getData({
            url: '/budget/inputDetail',
            query: {
                recordId: recordId,
                inputId:inputId
            }
        }, function(data) {
            //
            var data = {
                monthNum: monthNum,
                list: data,
                type: tplType //1录入预算，2录入支出，3录入详情
            }
            parentEl.find('.js-body').html(template('common/budget-table-tbody', {
                items: data
            }));
            //
        });
    }
    // 渲染表格数据
    function renderSelectTable () {
        getTableHeader();
        getTableContent();
    }
    
    $(document).on('click', '.js-budget-tab', function () { // 点录入信息
        $(this).addClass('active');
        $(this).siblings().removeClass('active');
        $('.js-information-con').show();
        $('.js-table-list').hide();
        $('.js-detail-con').hide();
    });
    $(document).on('click', '.js-record-tab', function () { // 点录入记录
        $(this).addClass('active');
        $(this).siblings().removeClass('active');
        $('.js-information-con').hide();
        $('.js-table-list').show();
        $('.js-detail-con').hide();
    });
    $(document).on('click', '.js-link-detail', function () { // 点详情
        recordDetail($(this).attr('dataid'));
        $('.js-table-list').hide();
        $('.js-detail-con').show();
    });
    $(document).on('click', '.js-detail-close', function () { // 关闭当前，显示详情列表
        $('.js-table-list').show();
        $('.js-detail-con').hide();
    });
    $(document).on('click', '.js-back-return', function () { // 关闭当前，显示详情列表
        window.history.back();
    });
    function init(){
        getBasic();
        renderSelectTable();
    }
    init();
});
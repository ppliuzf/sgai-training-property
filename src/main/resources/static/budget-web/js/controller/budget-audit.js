$(function() {
    var recordId = $.getQueryString('id');
    var tplType = 4;
    var monthNum = [0,1,2,3,4,5,6,7,8,9,10,11];
    // 获取基础信息数据
    function getBasic () {
        $.getData({
			url: '/budget/budgetDetail',
			query: {
				recordId: recordId
			}
		}, function(data) {
            renderSelectBasic('.js-detail-basic', data);
		});
    }
    // 渲染基础信息
	function renderSelectBasic(name, data) {
        // var createTime = new Date(data.createTime);
        // data.createTime = createTime.toLocaleDateString().replace(/\//g, "-");
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
        $('.js-header').html(template('common/budget-table-header', {
            items: data
        }));
    }
    function renderTableContent(params) {
        // debugger
        var data = {
            monthNum: monthNum,
            list: params,
            type: tplType //1录入预算，2录入支出，3录入详情
        }
        $('.js-body').html(template('common/budget-table-tbody', {
            items: data
        }));
    }
    // 渲染表格数据
    function renderSelectTable () {
        getTableHeader();
        getTableContent();
    }

    //审核通过
    function pass (idPass, type) {
        var data = {};
        data.id = idPass;
        data.state = type
        $.getData({
            url: '/approval/check',
            body: data
        }, function () {
            location.href = './plan-list.html';
        });
    }
    // 审核拒绝
    function reject (data,type) {
        $.pop({
            title: '提示',
            btn: ['确定', '取消'],
            area: ['500px', '250px'],
            // content: '请输入拒绝理由：',
            yes: function(){
               saveReson();
            }
        });
        $('.modal-body').html('<textarea class="txt-col" placeholder="请输入拒绝的理由" name="" id="textarea" rows="7" style="width: 100%" maxlength="300"></textarea><div class="counttask text-right"></div>');
        $.counter({
			el: '#textarea', // 文本框 id，默认 #textarea
			count: '.counttask', // 计数器 id，默认 .js-count
			max: 300 // 输入最大长度值，默认 200
		});
    }
    // 审核拒绝时-拒绝原因保存
    function saveReson() {
        // console.log($("#textarea").val());
        var data = {};
        data.id = recordId;
        data.state = 3;
        data.approvalReason = $("#textarea").val()
        //调接口保存数据
        $.getData({
            url: '/approval/check',
            body: data
        }, function () {
            location.href = './plan-list.html';
        });
    }

    $(document).on('click', '.js-goback', function () {
        window.history.back();
    });
    function init(){
        getBasic();
        renderSelectTable();
        $(document).on('click', '.js-pass', function(){
            pass(recordId, 2);
        });
        $(document).on('click', '.js-reject', reject);
    }
    init();
});
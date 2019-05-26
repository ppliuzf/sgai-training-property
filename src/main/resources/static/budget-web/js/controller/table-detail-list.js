$(function (){
    var id = $.getQueryString('id');
    var total = 0,
        currPage = 1;
    // 获取录入记录列表
    function getInputList(){
        $.getData({
			url: '/recordInput/getPageList',
			query: {
                recordId: id,
                pageNum: currPage,
				pageSize: 10,
			}
		}, function(data) {
            // console.log(data);
            if (data.list && data.list.length) {
				renderList(data.list);
				$.renderPage({
					count: data.count,
					curr: currPage,
					jump: function (num) {
						currPage = num;
						getInputList(num);
					}
				});
			} else {
				renderEmpty();
			}
		});
    }
    function renderEmpty() {
		$('.js-list').html(template('common/noRecord', {
			colspan: 11,
			text: '还没有相关计划，请添加计划'
		}));
	}
     // 渲染列表
	function renderList(params) {
		for(var i = 0; i < params.length; i++){
			var createTime = new Date(params[i].createTime);
			params[i].createTime = createTime.toLocaleDateString().replace(/\//g, "-");
		}
		var createTime = new Date(params.createTime);
		params.createTime = createTime.toLocaleDateString().replace(/\//g, "-");
		$('.js-table-list').html(template('common/table-detail-list', {
			items: params
		}));
	}
    function init(){
        getInputList();
    }
    init();
})
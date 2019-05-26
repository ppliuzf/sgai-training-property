/**
 * Created by 137376 on 2017/12/22.
 */
$(function () {
	var params = {
		recordName: '',
		startTime: '',
		endTime: '',
		typeId: ''
		};

	// 渲染无数据
	function renderEmpty() {
		$('.js-list-data').html(template('common/record-empty', {
			colspan: 5,
			text: '还没有相关计划，请添加计划'
		}));
}
	// 获取列表
	function getList(pageNum, isFirst) {
		//var limit = 10;
		$.getDataPlan({
			url: '/pc/plan/planPageList',
			query: {
				pageNum: pageNum,
				pageSize: 10,
			},
			body: params
		}, function (data) {
			console.log(data, 'data');
			if (data.list && data.list.length) {
				data.list.forEach(function (item, index) {
					item.num = index + 1;
					if (item.num < 10) {
						item.num = '0' + item.num;
					}
                });
				renderList(data.list);
				isFirst && $.renderPage({
					count: data.count,
					jump: function (num) {
						getList(num);
					}
				});
			} else {
				renderEmpty();
			}
		});
	}
	// 获取类型
	function getType() {
		$.getDataPlan({
			url: '/pc/type/getList',
			query: {
				typeClass: 1
			}
		}, function(data) {
			if (data && data.length) {
				var _data = [];
				for (var i in data) {
					_data.push({
						value: data[i].id,
						title: data[i].typeName
					});
				}
				renderSelect('#typeId', _data);
			}
		});
	}
	// 渲染级别、类型
	function renderSelect(id, data) {
		var _data = data;
		_data.unshift({
			value: '',
			title: '全部'
		});
		$(id).html(template('plan/select-type', {
			items: _data
		}));
	}
	// 渲染列表
	function renderList(params) {
		$('.js-list-data').html(template('plan/list-data', {
			items: params
		}));
	}
    // 重置搜索
    function resetSearch() {
        $('#recordName').val('');
        $('#startTime').val('');
        $('#endTime').val('');
        $('#typeId').val('');
		params = {};
		getList(1, true);
    }
	function init() {
		getList(1, true);
		getType();
		// 日历插件
        $('#startTime,#endTime').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            minView: 'year',
            endTime: new Date(),
            autoclose: true
        });
        // 搜索
        $(document).on('click','.js-search',function(){
            params = {};
            params.startTime = $.getTimeStamp($('#startTime').val());
            params.endTime = $.getTimeStamp($('#endTime').val());
            // 如果开始时间为空 去1970-01-01
			if (!params.startTime) {
                params.startTime = $.getTimeStamp('1970-01-01');
			}
			if (!params.endTime) {
                params.endTime = new Date().getTime();
			}
			// 结束时间小于开始时间 开始时间和结束时间是同一天
            if (params.endTime && params.endTime < params.startTime) {
                $.alert('结束时间不能小于创建时间！');
                return false;
            }else if (params.endTime === params.startTime) {
                params.endTime = params.endTime + (24 * 60 * 60 * 60 * 1000);
			}
            params.recordName=$.trim($('#recordName').val());
            params.typeId=$.trim($('#typeId').val());
            getList(1, true);
        });
        // 重置
		$(document).on('click', '.js-reset', resetSearch);
		$(document).on('click', '.input-group-addon', function(){
            $(this).prev().val('');
        })
	}
	init();
});
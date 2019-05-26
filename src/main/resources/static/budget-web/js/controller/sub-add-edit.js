$(function(){
    var subName = $.getQueryString('name');
    var id = $.getQueryString('id');
    var levels = $.getQueryString('levels');
    var add = $.getQueryString('add');
    var edit = $.getQueryString('edit');
	if(!id){
		id = -1;
	}
    // 收集数据
	function collectData() {
        var rs = {};
        if(!$('#first-div').text()){                                               //一级科目的修改和新建
            if ($.trim($('#subName').val()) === '') {
                $.alert('请输入科目名称', '#subName');
                return false;
            }
            rs.shortName = $.trim($('#subName').val()); // 科目名称         subName更改
        }else if($('#first-div').text() && !$('#second-div').text()){          //二级科目修改和新建
            if ($.trim($('#subNameSecond').val()) === '') {
                $.alert('请输入二级科目名称', '#subName');
                return false;
            }
            rs.subName = $('#first-div').text(); // 科目名称         subName更改
            rs.shortName = $.trim($('#subNameSecond').val()); // 二级科目名称         subNameSecond更改
        }else if($('#first-div').text() && $('#second-div').text()){           //三级科目修改和新建
            if ($.trim($('#subNameThird').val()) === '') {
                $.alert('请输入三级科目名称', '#subName');
                return false;
            }
            rs.shortName = $('#first-div').text(); // 科目名称         subName更改
            rs.shortName = $('#second-div').text(); // 科目名称         subNameSecond更改
            rs.shortName = $.trim($('#subNameThird').val()); // 二级科目名称         subNameThird更改
        }
		rs.description = $.trim($('#confereDesc').val()); // 描述      subDesc更改
		rs.parentId = id;
		return rs;
	}
    // 编辑-获取数据
    function getDetail(){
         $.getData({
		 	url: '/subject/subjectEdit',
		 	query: {
		 		id: id
		 	}
		 }, function (data) {
         	showData(data);
         });
    }
	function getNext(){
		$.getData({
			url: '/subject/subjectDetail',
			query: {
				subjectId: id
			}
		}, function (data) {
			showData(data);
			$.counter({
				el: '#confereDesc', // 文本框 id，默认 #textarea
				count: '.counttask', // 计数器 id，默认 .js-count
				max: 200 // 输入最大长度值，默认 200
			});
		});
	}
    // 编辑-渲染页面数据
	function showData(data){
		if(edit){
			data.edit=edit;
		}
        $('.js-add-edit').html(template('common/sub-add-edit', {
            item: data,
        }));
        if(add && levels){//新增下一级科目
            if(levels == 1){
                console.log(data);
                $('#first-div').text(subName);
            }else if(levels == 2){
                $('#first-div').text(data.levelOneName);
	            $('#second-div').text(data.shortName);
            }

        }else if(edit && levels){  //编辑一 二 三级科目
	        if(levels==1){
		        $('#subName').val(data.shortName);
		        $('#confereDesc').val(data.description);
	        }else if(levels == 2){
		        $('#first-div').text(data.levelOneName);
		        $('#subNameSecond').val(data.shortName);
		        $('#confereDesc').val(data.description);
	        }else if(levels==3){
		        $('#first-div').text(data.levelOneName);
		        $('#second-div').text(data.levelTwoName);
		        $('#subNameThird').val(data.shortName);
		        $('#confereDesc').val(data.description);
	        }
            //if(!data.subNameSecond && !data.subNameThird){ //一级科目
            //    $('#subName').val(data.subName);
            //    $('#confereDesc').val(data.subDesc);
            //}
            //if(data.subNameSecond && !data.subNameThird){//二级科目
            //    $('#first-div').text(data.subName);
            //    $('#subNameSecond').val(data.subNameSecond);
            //    $('#confereDesc').val(data.subDesc);
            //}
            //if(data.subNameThird){                 //三级科目
            //    $('#first-div').text(data.subName);
            //    $('#second-div').text(data.subNameSecond);
            //    $('#subNameThird').val(data.subNameThird);
            //    $('#confereDesc').val(data.subDesc);
            //}
        }

    }
    //编辑-保存
    function upSaveData (data) {
        data.id =  $.getQueryString('id');
        console.log(data);
	    $.getData({
		    url: '/subject/subjectEdit',
		    body: data
	    }, function (data) {
		    window.history.back();
	    });
    }
    // 新建-保存
	function saveData (data) {
        console.log(data);
		 $.getData({
		 	url: '/subject/subjectAdd',
		 	body: data
		 }, function (data) {
		 		window.history.back();
		 });
	}
    function init(){
        //是否是编辑
		if(edit && subName){
			document.title = '编辑科目';
			$('.subTitle').html('编辑科目');
            //getDetail();
			getNext();
        }
        //新增下一级科目时地址栏有grade和id 无subName
        if(add && levels && subName){
            //getDetail();
	        getNext();
        }
        if(!subName && !levels){
            $('.js-add-edit').html(template('common/sub-add-edit', {
                item: {},
            }));
        }
	    if(id == -1){
		    $.counter({
		       el: '#confereDesc', // 文本框 id，默认 #textarea
		       count: '.counttask', // 计数器 id，默认 .js-count
		       max: 200 // 输入最大长度值，默认 200
		    });
	    }
        // 保存
		$(document).on('click', '.js-save', function () {
			var data = collectData();
			if (edit) { //判断是新增还是编辑 grade判断是否是新建下一级科目
				upSaveData(data);
			}else{
				saveData(data);
			}
		});
		// 取消
		$(document).on('click', '.js-cancel', function () {
			history.back();
		});
    }
    init();
});
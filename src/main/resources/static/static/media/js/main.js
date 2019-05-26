/**表头不动，tbody动
 * @wrap: 包裹table的元素
 * @table: 表格元素
 * @threshold: 滚动阈值
 */

function handleTableScroll(wrap, table, threshold) {
	var $wrap = $(wrap);
	var $table = $(table);
	var $div;
	threshold = threshold || 0;
	if ($wrap.attr('binded')) { return; }
	$wrap.attr('binded', true);
	$wrap.css('position', 'relative');
	$wrap.on('scroll', function() {
		if (!$div || !$div.length) {
			//console.log('create thead!');
			var tmp = $table.clone();
			tmp.find('tbody').remove();
			$div = $('<div id="_thead" style="position: absolute; top: 0; left: 0;"></div>');
			$div.html(tmp);
			$wrap.prepend($div);
			$div = $('#_thead');

		}
		var top = $wrap.scrollTop();
		//console.log(top);
		if (top >= threshold) {
			$div.css('top', top).show();
		} else {
			$div.css('top', 0).hide();
		}
	});
}
//操作按钮
function handelBtn(){
	$(".btn_more").unbind().on("click", function () {
		if ($(this).next().hasClass("pub-hide")) {
			$(this).next().removeClass("pub-hide").addClass("pub-show");
			$(this).parents("tr").siblings().find(".btn_list").removeClass("pub-show").addClass("pub-hide");
		} else {
			$(this).next().removeClass("pub-show").addClass("pub-hide");
		}
	});
	$(".btn_list button").click(function(){
		$(".btn_list").removeClass("pub-show").addClass("pub-hide");
	});
}
//点击操作框以外的位置，让下拉框关闭 现table列表无handleBtn
/*$(document).click(function(e){
	var _con = $('.btn-hover');
	if(!_con.is(e.target) && _con.has(e.target).length === 0){
		$(".btn_list").removeClass("pub-show").addClass("pub-hide");
	}
});*/
//textarea自动拉伸
function autoSize(){
	var $t;
	var border = 0;
	$('.textAreas').on('input propertychange', function() {
		var $this = $(this);
		var scrollHeight = this.scrollHeight;
		if (!border) {
			border = $this.outerHeight(true) - scrollHeight;
		}
		if (!$t) {
			$t = $('<textarea></textarea>').css({
				position:'absolute',
				top: -9999,
				left: -9999,
				overflow: 'hidden',
				width:$this.outerWidth(),
				height: scrollHeight + border
			}).appendTo('body');
		}
		$t.val($this.val());
		console.log($t);
		$(this).css('height', $t[0].scrollHeight);
	});
}

var pageSize;
var page;
//分页返回数据
function pageContent(data,num,size){
	$("#page_div").empty();
	var allPageNum = Math.ceil(data.count/data.pageSize);
	var page = data.pageNo;
	$("#pageNow").html(page);
	$("#allNum").html(data.count);
	$("#allPage").html(allPageNum);
	var options = {
		size:"normal",
		currentPage: page,
		totalPages:allPageNum,
		numberOfPages:10,
		onPageClicked: function(e,originalEvent,type,page){
			pageload(page,pageSize)
		}
	}
	if(num && !isNaN(Number(num))){
		options.numberOfPages = num;
		if(size){
			options.size = size;
		}
	}else if(num){
		options.size = num;
	}
	//默认应用mini分页时，去掉页数统计
	if(options.size == "mini"){
		$(".row-fluid .page-box").hide(0);
	}
	if(data.count){//否则返回0条时报错
		$("#page_div").bootstrapPaginator(options);
	}
}


//添加modal
//modalId 模态框Id
//url 请求地址
//iframeId 内容iframeID
function insertItem(modalId,url,iframeId,width) {
	$("#"+iframeId).removeAttr("height");
	$('#'+ modalId).modal({backdrop: 'static', keyboard: false});
	$('#'+ modalId).css("width",width+"px");
	$('#'+ modalId).modal('show');
	var url = APIHost + "/"+ url +".html";
	$("#" + iframeId).attr("src",url);
	$('#' + modalId).one('shown',function(){
		setModalIframeHeight(iframeId);
	});
}


//编辑modal
//index 当前项index(this)   //attr 自定义属性获取item id   //url 请求页面名   //modalId 模态框id  //iframeId 内容iframe id

function editItem(index,attr,url,modalId,iframeId,width){
	$("#"+iframeId).removeAttr("height");
	$('#' + modalId).modal({backdrop: 'static', keyboard: false});
	$('#' + modalId).css("width",width+"px");
	$('#' + modalId).modal('show');
	$(index).parents("tr").children().css("cssText","background:rgba(237,241,242,0.38) !important");
	$(index).parents("tr").siblings().children().css("cssText","background:#fff !important");
	$(index).parents("tr").find("[type='checkbox']").prop("checked",true);
	$(index).parents("tr").siblings().find("[type='checkbox']").prop("checked",false);
	var ids = $(index).attr(attr);
	var url = APIHost + "/"+url+".html?paramId=" + ids;
	$("#" + iframeId).attr("src",url);
	$('#' + modalId).one('shown',function(){
		setModalIframeHeight(iframeId);
	});
}

function setModalIframeHeight(iframe){
	if (iframe) {
		var height = $("#" + iframe).contents().find("body").height();
		if( height >= 444 ){
			height = 444;
		}
		$("#" + iframe).attr("height",height);
	}
}


// 表格inputcheck 选中高亮
function inputCheck(){
	$(".table").on("change","input",function(){
		if($(this).is(':checked')){
			$(this).parent().parent().find("td").css("cssText","background:rgba(237,241,242,0.38) !important");
		}else{
			$(this).parent().parent().find("td").css("cssText","background:#fff !important");
		}
	})
}


function pageRightContent(parentLabel){
	var pageRightContent='<div class="row-fluid">'
		+'<h3 class="page-null">暂无数据！</h3>'
		+'<div class="page-box span3" style="padding-top: 15px;">'
		+'<span id="pageNow"></span><span>-</span><span id="allPage"></span>页&nbsp;&nbsp;<span>共<span id="allNum"></span>项</span>&nbsp;&nbsp;'
		+'<select class="page-size">'
		+'<option value="10">10</option>'
		+'<option value="20">20</option>'
		+'<option value="50">50</option>'
		+'<select>&nbsp;项/页'
		+'</div>'
		+'<div id="page_div">'
		+'</div>'
		+'</div>';
	if(!parentLabel){
		$(".table_fluid").append(pageRightContent);
	}else{
		$(parentLabel).append(pageRightContent);
	}


	$(".page-size").on("change",function(){
		pageSize = this.value;
		$("#pageSize").val(pageSize);
		pageload(page,pageSize);
	});

}
function pageRightContentTable(){
	var pageRightContentTable='<div class="row-fluid">'
			/*+'<div style="padding-top: 15px" class="page-box span6">'
			 +'<span id="pageNow"></span><span>-</span><span id="allPage"></span>页&nbsp;&nbsp;<span>共<span id="allNum"></span>项</span>&nbsp;&nbsp;'
			 +'<select class="page-size">'
			 +'<option value="10">10</option>'
			 +'<option value="20">20</option>'
			 +'<option value="50">50</option>'
			 +'<select>&nbsp;项/页'
			 +'</div>'*/
		+'<div id="page_div">'
		+'</div>'
		+'</div>';
	$("#left_page_div").append(pageRightContentTable);

	$(".page-size").on("change",function(){
		pageSize = this.value;
		page = $("#pageNow").html();
		pageload(page,pageSize);
	});

}
function setIframeHeight(iframe) {
	if (iframe) {
		var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
		if (iframeWin.document.body) {
			iframe.height = 0;
			var height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
			iframe.height = height+2;
		}
	}
}

//筛选条件折叠收起
function searchFilter(obj,num) {
	var searchElement;
	if(num == 1){
		searchElement = $('.search_form_one tr');
	}else if(num == 2){
		searchElement = $('.search_form_two tr');
	}else{
		searchElement = $('.search_form_three tr');
	}
	var slideDown = $(obj).parent().parent().find("span");
	console.log(slideDown)
	if($(obj).children().hasClass("slide_up")) {
		searchElement.css("display","none");
		slideDown.removeClass("slide_up").addClass("slide_down");
		searchElement.fadeOut();
	} else {
		searchElement.css("display","table-row");
		slideDown.removeClass("slide_down").addClass("slide_up");
		searchElement.fadeIn();
	}
}

// 全选
function SelectAll(i,id){
	var flage = $(i).is(":checked");
	if(flage){
		$("#"+ id + " tr td input").prop("checked",true);
	}else{
		$("#"+ id + " tr td input").prop("checked",false);
	}
}

// 每一个checkbox 添加选中事件
function SelectOneByOne(i){
	var this_ipt = $(i).parents('tbody:first').find('input').length;
	var this_ipt_ckb = $(i).parents('tbody:first').find('input[type=checkbox]:checked').length;
	var selectAll_Btn = $(i).parents('tbody:first').siblings('thead').find('input');
	if(this_ipt == this_ipt_ckb){
		selectAll_Btn.prop("checked",true);
	}else{
		selectAll_Btn.prop("checked",false);
	}
}

// 弹框遮罩
function shadeShow(name){
	$("."+name).css("display","block");
}
function shadeHidden(name){
	$("."+name).css("display","none");
}

//面包屑
function makeBread() {
	var eatBread = window.localStorage.getItem("bigBread").split(',').reverse();
	var breadLength = eatBread.length - 1;
	var eatBreadStr = '';
	for (var i = 0; i < eatBread.length; i++) {
		if (i == breadLength) {
			eatBreadStr += '<span> ' + eatBread[i] + '</span>'
		} else {
			eatBreadStr += '<span> ' + eatBread[i] + ' \/</span>'
		}
	}
	$('.smart_bread').html(eatBreadStr);
}

//清空查询条件
function clearReseachData() {
	$("input[type='text']").val("");
	var select = document.getElementById("repairType");
	for (var i = 0; i < select.options.length; i++) {
		if (select.options[i].value == "") {
			select.options[i].selected = true;
			$("#select2-repairType-container").html(select.options[i].text);
			$("#select2-repairType-container").css("color", "#999");
			break;
		}
	}
}

function tip (imageUrl,descriptText) {
	var timer = null;
	$('#tipModal').fadeIn();
	var tipHtml = '<image class="jboxSelfImage" src="'+ imageUrl +'"></image><div class="jboxSelfContent">'+descriptText+'</div>';
	$('#tipContent').html(tipHtml);
	if(timer) {
		clearTimeout(timer);
	}
	timer = setTimeout(function () {
		$('#tipModal').fadeOut();
	}, 1800);
}

// 运行监控模块——专业视角——设备报警
//设备报警
function deviceMoreData(){
	$.ajax({
		headers    : {
			"token" : localStorage.getItem("token"),
		},
		url : APIHost+'/admin/alm/almRecordList/getAlarmList',
		type : 'POST',
		async : false, //或false,是否异步
		timeout : 5000,
		dataType : 'json',
		success : function(res) {
			console.log(res);
			var deviceLi='';
			for(var i=0;i<5;i++){
				deviceLi+='<li class="more-equip">'+res[i].devicesCode+res[i].alarmMsg+'</li>';
			}
			$("#deviceData").html(deviceLi);
		},
		error : function() {
			console.log('错误')
		}
	})
}
/*// 点击更多页面跳转
function deviceDumpClick(){
	$(parent.document).find(".menu_bg[href='/monitor/alarms/alarm.html']")[0].click();
	console.log($(parent.document).find(".menu_bg[href='/monitor/alarms/alarm.html']"))
}*/
//查看附件图片
function checkImage (elm) {
	$(".imageContent").fadeIn().find("img").attr("src",$(elm).attr("src"));
}
//关闭附件图片
function closeImage () {
	$(".imageContent").fadeOut();
}

//验证报警权限
function verifyAlarmUser(){
	var verify;
	$.ajax({
		headers    : {
			"token" : localStorage.getItem("token")
		},
		url : APIHost + '/admin/allocmenu/ctlAllocMenu/findMenuByUserCode',
		type : 'post',
		async : false, //或false,是否异步
		data : {
			menuCode:"bjgl"
		},
		timeout : 5000,
		dataType : 'json',
		success : function(res) {
			if(res.code === 1000){
				if(res.data === 1){
					verify = true;
				}else{
					verify = false;
				}
			}
		},
		error : function() {
			console.log('错误')
		}
	});
	return verify
}
// button 点击权限的设置
function btnDisabledOrAbled(arr,boolean) {
	arr.forEach(function (item, index) {
		$("#"+item).attr("disabled", boolean)
	})
}

//获取url参数
function getIframeParams(iframeId) {
    var regexpParam = /\??([\w\d%]+)=([\w\d%-]*)&?/g; //分离参数的正则表达式
    var targetEle = window.parent.document.getElementById(iframeId);
    var paramMap = null;
    if(!!targetEle) {
        var url = targetEle.src; //取得iframe的url
        var ret;
        paramMap = {};//初始化结果集
        //开始循环查找url中的参数，并以键值对形式放入结果集
        while((ret = regexpParam.exec(url)) != null) {
            //ret[1]是参数名，ret[2]是参数值
            paramMap[ret[1]] = ret[2];
        }
    }
    return paramMap; //返回结果集
}
javascript:window.history.forward(1); 
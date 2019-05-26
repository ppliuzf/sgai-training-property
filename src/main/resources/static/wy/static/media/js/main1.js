
//操作按钮
function handelBtn(){
		$(".btn_more").on("click",function(){
			if($(this).children().hasClass("caret")){
				$(this).children().removeClass("caret").addClass("caret_up");
				$(this).next().removeClass("pub-hide").addClass("pub-show");
				$(this).parents("tr").siblings().find(".btn_list").removeClass("pub-show").addClass("pub-hide");
				$(this).parents("tr").siblings().find(".caret_up").removeClass("caret_up").addClass("caret");
			}else{
				$(this).children().removeClass("caret_up").addClass("caret");
				$(this).next().removeClass("pub-show").addClass("pub-hide");
			}
	  });
}
//点击操作框以外的位置，让下拉框关闭
$(document).click(function(e){
	var _con = $('.btn-hover'); 
	if(!_con.is(e.target) && _con.has(e.target).length === 0){
		$(".btn_more").children().removeClass("caret_up").addClass("caret");
		$(".btn_list").removeClass("pub-show").addClass("pub-hide");
	}
});	

	var pageSize;
	var page;
//分页返回数据
	function pageContent1(data){
		var allPageNum = Math.ceil(data.count/data.pageSize);
		var page = data.pageNo;
		$("#pageNow1").html(page);
		console.log(data);
		$("#allNum1").html(data.count);
		$("#allPage1").html(allPageNum);
		var options = {
		    size:"normal",
		    currentPage: page,
		    totalPages:allPageNum,
		    numberOfPages:10,
		    onPageClicked: function(e,originalEvent,type,page){
		    	pageLoad2(page,pageSize)
		    }
		};
		if(data.count){//否则返回0条时报错
			$("#page_div1").bootstrapPaginator(options);
		}else{
			$("#page_div1").html("");
		}
	}


//添加modal
//modalId 模态框Id
//url 请求地址
//iframeId 内容iframeID
function insertItem(modalId,url,iframeId,height) {
	$('#'+ modalId).modal('show');
	var url = APIHost + "/"+ url +".html";
	$("#" + iframeId).attr("src",url);
	$("#" + iframeId).css("height",height+"px");
}

//编辑modal
//index 当前项index(this)   //attr 自定义属性获取item id   //url 请求页面名   //modalId 模态框id  //iframeId 内容iframe id

function editItem(index,attr,url,modalId,iframeId,height){
/*	$(index).parents("tr").children().css("cssText","background:#F9F9F9 !important");
	$(index).parents("tr").siblings().children().css("cssText","background:#fff !important");*/
	$(index).parents("tr").find("[type='checkbox']").prop("checked",true);
	$(index).parents("tr").siblings().find("[type='checkbox']").prop("checked",false);
	var ids = $(index).attr(attr);
	var url = APIHost + "/"+url+".html?paramId=" + ids;
	$('#' + modalId).modal('show');
	$("#" + iframeId).attr("src",url);
	$("#" + iframeId).css("height",height+"px");
}


// 表格inputcheck 选中高亮
function inputCheck(){
	$(".table").on("change","input",function(){
	    if($(this).is(':checked')){
			$(this).parent().parent().find("td").css("cssText","background:#F9F9F9 !important");
		}else{
			$(this).parent().parent().find("td").css("cssText","background:#fff !important");
		}
	})
}


function pageRightContent1(){
	var pageRightContent='<div class="row-fluid">'
	+'<div style="padding-top: 15px" class="span6">'
	+'<span id="pageNow1"></span><span>-</span><span id="allPage1"></span>页&nbsp;&nbsp;<span>共<span id="allNum1"></span>项</span>&nbsp;&nbsp;'
	+'<select class="page-size">'
		+'<option value="10">10</option>'
		+'<option value="20">20</option>'
		+'<option value="50">50</option>'
	+'<select>&nbsp;项/页'
	+'</div>'
	+'<div id="page_div1">'
	+'</div>'
	+'</div>';
	$("#Mat").append(pageRightContent);

	$(".page-size").on("change",function(){
		pageSize = this.value;
		pageload(page,pageSize,1);
	});
	
}
function pageRightContentTable(){
	var pageRightContentTable='<div class="row-fluid">'
	+'<div style="padding-top: 15px" class="span6">'
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
	$("#left_page_div").append(pageRightContentTable);

	$(".page-size").on("change",function(){
		pageSize = this.value;
		pageload2(page,pageSize);
	});
	
}
function setIframeHeight(iframe) {
	if (iframe) {
		var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
		if (iframeWin.document.body) {
			iframe.height = 0;
			iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
		}
	}
}

	
	

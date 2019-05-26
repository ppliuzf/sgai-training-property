    ///上传文件  
    var riskStr,str,url=""; var token="";
	function imageFinderOpen(str1,fileType){
		if($(fileType).html()==="选择"){
			url = $(fileType).prev().prev().val();
			fileType = "files";
			riskStr = str1;
		}
	    str=str1
	    /*token=localStorage.getItem("token");
		var date = new Date(), year = date.getFullYear(), month = (date.getMonth()+1)>9?date.getMonth()+1:"0"+(date.getMonth()+1);
		var url2 = "/static/ckfinder/ckfinder.html?type="+fileType+"&start=files:/annex/"+year+"/"+month+
			"/&action=js&func=imageSelectAction&cb=imageCallback&dts=0&sm=1&token="+token+"";
		windowOpen(url2,"文件管理",1000,700);*/
	    str=str1
	    token=localStorage.getItem("token");
		var date = new Date(), year = date.getFullYear(), month = (date.getMonth()+1)>9?date.getMonth()+1:"0"+(date.getMonth()+1);
		var url2 = "/zuul/static/ckfinder/ckfinder.html?type="+fileType+"&start=files:/annex/"+year+"/"+month+"-"+window.localStorage.getItem("userName")+
			"/&action=js&func=imageSelectAction&cb=imageCallback&dts=0&sm=1&token="+token+"";
		windowOpen(url2,"文件管理",1000,700);
	}
	function imageSelectAction(fileUrl, data, allFiles){
		files=ckfinderAPI.getSelectedFiles();
		for(var i=0; i<files.length; i++){
			if(url!=""){
				url+="|"
			}
			url += files[i].getUrl();
		}
		$("#"+str).val(url);
//		if($("#"+str+1).children().html()=="无"){
//			$("#"+str+1).children().remove();
//		}
		$("#"+str+1).children().remove();
		imagePreview(str,false);
	}
	function imageCallback(api){
		ckfinderAPI = api;
	}
	function imageDel(obj){
		if(riskStr!=""&&riskStr!=null){
			str = $(obj).parent().attr("id").split("|")[0];
			riskStr = str;
			url = $("#"+str).val();
		}
		var url1 = $(obj).prev().attr("href");
		if(!url1){
			url1 = $(obj).prev().attr("src");
		}
		$("#"+str).val($("#"+str).val().replace("|"+url1,"").replace(url1+"|","").replace(url1,""));
		url = url.replace("|"+url1,"").replace(url1+"|","").replace(url1,"");
		imagePreview(str,true);
	}
	function imageDelAll(){
		$("#"+str).val("");
		imagePreview(str,true);
		url = "";
	}
	function riskDelAll(risk){
		$("#"+risk).val("");
		imagePreview(risk,true);
		url = "";
	}
	function imagePreview(str,isClean){
		var li, urls = $("#"+str).val().split("|");
		//urls=decodeURIComponent(urls);
		if($("#"+str+1).children().html()!="无"&&isClean==true){
			$("#"+str+1).children().remove();
			$("#"+str+1).html("");
		}
		for (var i=0; i<urls.length; i++){
			if (urls[i]!=""&&urls[i]!="null"){
				var url=decodeURIComponent(urls[i]);
				var arr=url.split('/');
				var fileName=arr[arr.length-1];
				var fileArr= fileName.split(".");
				var fileType = fileArr[fileArr.length-1];
				if(fileType ==="jpg" || fileType=== "bmp" ||fileType==="jpeg" || fileType==="jpg" || fileType === "png" || fileType==="gif")
				{
					li = "<li id=\""+str+"|"+url+"\"><img src=\""+url+"\" url=\""+url+"\" style=\"max-width:100px;max-height:100px;_height:100px;border:0;padding:3px;\">";//
       				li += "&nbsp;&nbsp;<a href=\"javascript:\" onclick=\"imageDel(this);\">×</a></li>";
       				//<a href=\"javascript:\" onclick=\"imageDel(this);\">×</a>
				}else if(fileType==="docx"||fileType==="txt"){
					li = "<li id=\""+str+"|"+url+"\"><a href=\""+url+"\" style=\"max-width:100px;max-height:100px;_height:100px;border:0;padding:3px;\">"+fileName+"";//
					li += "&nbsp;&nbsp;<a href=\"javascript:\" onclick=\"imageDel(this);\">×</a></li>";
				}else{
					if(str=="photo"){
						$.jBox.alert("所选不是照片格式，请重新选择！","提示");
						$("#"+str).val("");
						url = "";
						continue;
					}
					li = "<li><a href=\""+url+"\" style=\"max-width:100px;max-height:100px;_height:100px;border:0;padding:3px;\">"+fileName+"";//
					li += "&nbsp;&nbsp;<a href=\"javascript:\" onclick=\"imageDel(this);\">×</a></li>";
				}
				$("#"+str+1).append(li);
			}
		}
		if ($("#"+str+1).text() == ""){
			$("#"+str+1).html("<li style='list-style:none;padding-top:5px;'>无</li>");
		}
	} 
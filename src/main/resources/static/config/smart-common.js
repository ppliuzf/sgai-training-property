APIHost = 'http://10.111.1.193:8087/';
function loadDictByType(sltId,dateType){
	var select = document.getElementById(sltId);
	$.ajax({
		headers : {"token" : localStorage.getItem("token")},
		url : APIHost + '/admin/ctlCodeDet/getTypeList',
		type : 'POST',
		async : false,
		data : {
			code_type:dateType
		},
		timeout : 5000,
		dataType : 'json',
		success : function(res) {
			var list = res.data;
			for(var i=0; i<list.length; i++){
				var option = document.createElement("option");
				option.value = list[i].codeCode;
				option.text = list[i].codeName;
				select.appendChild(option);
			}
		}
	});
}
//按钮权限
function btnPermit(progPath){

	$.ajax({
		  headers    : {
            "token" : localStorage.getItem("token"),
        },
		url : APIHost + '/admin/btnPermit',
		type : 'POST',
		async : false, //或false,是否异步
		data : {
			url : progPath  //参数是当前页名称
		},
		timeout : 5000,
		dataType : 'json',
		success : function(res) {
			console.log(res);
			if(res.data.length >0){
				for(var i = 0; i<res.data.length;i++){
		    		 $("#"+res.data[i].progCode).css('display','');
		    	 }
			}else{
				$("button").css('display','');
				$("input[type='button']").css('display','');
			}

		},
		error : function() {
			console.log('错误')
		}
	})
}




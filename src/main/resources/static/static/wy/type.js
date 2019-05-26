$(function (){
		var pCode = "0";
		var repairType1 = "";
		var repairType2 = "";
		$.ajax({
			headers    : {
	               "token" : localStorage.getItem("token"),
	         },
			url:APIHost+"/repairInformation/getTypeByCode",
			type:"POST",
			data:{pCode : pCode},
			success:function (obj){
				for(var i = 0;i<obj.length;i++){
					var province = obj[i];
					$("#repairType1").append("<option value="+province.typeId+">"+province.typeCode+"</option>");
				}
			}
		});
		
		$("#repairType1").change(function (){
			$("#repairType2").empty();
			repairTypeCode1 = $("#repairType1 option:selected").val();
			repairType1 = $("#repairType1 option:selected").text();
				$.ajax({
					headers    : {
			               "token" : localStorage.getItem("token"),
			         },
					url:APIHost+"/repairInformation/getTypeByCode",
					type:"POST",
					data:{pCode:repairTypeCode1},
					success:function(obj){
						console.log(obj);
						$("#repairType2").append("<option value=''></option>");
						for(var i = 0;i<obj.length;i++){
							var city = obj[i];
							$("#repairType2").append("<option value="+city.typeId+">"+city.typeCode+"</option>");
						}
						$("#repairType").val();
						$("#repairTypeCode").val();
						$("#repairTypeCode").val(repairTypeCode1);
						$("#repairType").val(repairType1);
					}
				});
		});
		$("#repairType2").change(function (){
			$("#repairTypeCode").val($("#repairType1 option:selected").val()+"-"+$("#repairType2 option:selected").val());
			$("#repairType").val($("#repairType1 option:selected").text()+"-"+$("#repairType2 option:selected").text());
		});
			
			
		
});
		
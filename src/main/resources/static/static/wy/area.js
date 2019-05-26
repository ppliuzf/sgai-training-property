$(function (){
		var pCode = "0";
		var repairAddress1 = "";
		var repairAddress2 = "";
		var repairAddress3 = "";
		var repairAddressCode1 = "";
		var repairAddressCode2 = "";
		var repairAddressCode3 = "";
		
		$.ajax({
			headers    : {
	               "token" : localStorage.getItem("token"),
	         },
			url:APIHost+"/repairInformation/getByParentCode",
			type:"POST",
			data:{pCode : pCode},
			success:function (obj){
				for(var i = 0;i<obj.length;i++){
					var province = obj[i];
					$("#repairAddress1").append("<option value="+province.spaceCode+">"+province.spaceName+"</option>");
				}
			}
		});
		$("#repairAddress1").change(function (){
			$("#repairAddress2").empty();
			$("#repairAddress3").empty();
			//  $("#repairAddress2").append("<option>"+"--请选择--"+"</option>");
			//2.$("#repairAddress2")[0].options.length = 1;
			//$("#repairAddress2").get(0).length = 1;
			//$("#repairAddress3").get(0).length = 1;
			repairAddressCode1 = $("#repairAddress1 option:selected").val();
			repairAddress1 = $("#repairAddress1 option:selected").text();
				$.ajax({
					headers    : {
			               "token" : localStorage.getItem("token"),
			         },
					url:APIHost+"/repairInformation/getByParentCode",
					type:"POST",
					data:{pCode:repairAddressCode1},
					success:function(obj){
						console.log(obj);
						$("#repairAddress2").append("<option value=''></option>");
						for(var i = 0;i<obj.length;i++){
							var city = obj[i];
							$("#repairAddress2").append("<option value="+city.spaceCode+">"+city.spaceName+"</option>");
						}
						$("#repairAddress").val();
						$("#repairAddressCode").val();
						$("#repairDetailAddress").val();
						$("#repairAddressCode").val(repairAddressCode1);
						$("#repairAddress").val(repairAddress1);
						$("#repairDetailAddress").val(repairAddress1);
					}
				});
			
			
		
		})
		$("#repairAddress2").change(function (){
			$("#repairAddress3").empty();
			//$("#repairAddress3").append("<option>"+"--请选择--"+"</option>");
			//$("#repairAddress3").get(0).length = 1;
			repairAddressCode2 = $("#repairAddress2 option:selected").val();
			repairAddress2 = $("#repairAddress2 option:selected").text();
				$.ajax({
					headers    : {
			               "token" : localStorage.getItem("token"),
			         },
					url:APIHost+"/repairInformation/getByParentCode",
					type:"POST",
					data:{pCode:repairAddressCode2},
					success:function(obj){
						$("#repairAddress3").append("<option value=''></option>");
						for(var i = 0;i<obj.length;i++){
							var country = obj[i];
							$("#repairAddress3").append("<option value="+country.spaceCode+">"+country.spaceName+"</option>");
						}
						$("#repairAddress").val();
						$("#repairAddressCode").val();
						$("#repairDetailAddress").val();
						$("#repairAddressCode").val($("#repairAddress1 option:selected").val()+"-"+repairAddressCode2);
						$("#repairAddress").val($("#repairAddress1 option:selected").text()+"-"+repairAddress2);
						$("#repairDetailAddress").val($("#repairAddress1 option:selected").text()+"-"+repairAddress2);
					}
				});
		})
		$("#repairAddress3").change(function (){
			repairAddressCode3= $("#repairAddress3 option:selected").val();
			repairAddress3 = $("#repairAddress3 option:selected").text();
			$("#repairAddress").val();
			$("#repairAddressCode").val();
			$("#repairDetailAddress").val();
			$("#repairAddressCode").val($("#repairAddress1 option:selected").val()+"-"+$("#repairAddress2 option:selected").val()+"-"+repairAddressCode3);
			$("#repairAddress").val($("#repairAddress1 option:selected").text()+"-"+$("#repairAddress2 option:selected").text()+"-"+repairAddress3);
			$("#repairDetailAddress").val($("#repairAddress1 option:selected").text()+"-"+$("#repairAddress2 option:selected").text()+"-"+repairAddress3+"-"+$("#detailAddress").val());
			
		}) 
	})
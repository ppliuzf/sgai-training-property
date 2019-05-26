$(function(){
	var home = document.getElementById("nav-item");
	var arrSiblings = $(".nav-item").nextAll();
	

	$.each(arrSiblings, function(index1,value1){	
		var arrLis1 = $(value1).children("ul").children("li");
		$.each(arrLis1, function(index2,value2){		
			var arrLis2 = $(value2).children("ul").children("li");
			$.each(arrLis2, function(index3,value3){
				$(value3).click(function(){
					var text = $(this).children("a").children(".title").html();
					$(".breadcrumb_C").html(text);
					//console.log(text);
				});
			});
		
			$(value2).click(function(){
				var text = $(this).children("a").children(".title").html();
				$(".breadcrumb_B").html(text);
				//console.log(text);
			});
		});
		
		$(value1).click(function(){
			var text = $(this).children("a").children(".title").html();
			$(".breadcrumb_A").html(text);
			//console.log(text);
		});
	});

	
	
})

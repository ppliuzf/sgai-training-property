var localObj = window.location;

var contextPath = localObj.pathname.split("/")[1];

var basePath = localObj.protocol+"//"+localObj.host;
var server_context=basePath;
(function($) {
	"use strict";	
	var events=new Array();
	var date=new Date().getTime();
	var oDate = getMyDate(new Date());
	var colorObj = {}; //保存班次显示颜色
	function getData(date){
		$.ajax({
		headers    : {
            "token" : localStorage.getItem("token"),
        },
	    url:basePath+"/emergency/emmCalendar/getMemberViewEmmCalendar",
     	type: "POST",
     	data: {queryStartDate:date},
     	dataType: "json",
     	cache: false,
     	async:false,
     	success: function(res){
     		if(res.code === 1000){
     			// var str = '<div>';
     			// res.data.forEach(function(item,index){
     			// 	var odata = item.title.split(":")[1].split("/");
     			// 	str += '<span>'+item.title.split(":")[0]+'</span>'
     			// 	odata.forEach(function(item2,index2){
     			// 		str += '<span>'+item2.split("|")[1]+'</span>'
     			// 	})
     			// 	str+='</div>'
     			// })
     			// console.log(str)
     			events = res.data;
     			calendar.options.events_source =events;
					calendar.view();
     		}
     	},
     	error: function(err){
     	}
     });
	}
	
	// $.ajax({
	// 	headers : {
 //            "token" : localStorage.getItem("token")
 //        },
	// 	url : APIHost + '/emergency/emmDuty/getShiftInfoSelect',
	// 	type : 'POST',
	// 	async : false, //或false,是否异步
	// 	data : {
	// 	},
	// 	timeout : 5000,
	// 	dataType : 'json',
	// 	success : function(data) {
	//     	for(var i=0;i<data.length;i++){
	//     		console.log(data[i].COLOR);
	//     		colorObj[data[i].shiftName] = data[i].COLOR;
	//     	}
	// 	},
	// 	error : function() {
	// 		console.log('错误')
	// 	}
 //    });

	var options = {
		events_source:events,
		view: 'month',
		tmpl_path: '/static/bootstrap_calendar/tmpls2/',
		tmpl_cache: false,
		day: getMyDate(date),
		onAfterEventsLoad: function(events) {
		},
		onAfterViewLoad: function(view) {
			/*dayDate=this.getTitle();*/
			$('.page-header h3').text(this.getTitle());
			$('.btn-group button').removeClass('active');
			$('button[data-calendar-view="' + view + '"]').addClass('active');
		},
		classes: {
			months: {
				general: 'label'
			}
		}
	};

	var calendar = $('#calendar').calendar(options);
	getData(oDate)
	calendar.setOptions({first_day:"1"});
	calendar.setLanguage("zh-CN");
	calendar.setOptions({modal: "#events-modal"});
	calendar.setOptions({display_week_numbers: true});
	calendar.setOptions({weekbox: true});
	calendar.view();
	var dateIndex = 0;
	$('.btn-group button[data-calendar-nav]').each(function() {
		var $this = $(this);
		$this.click(function() {
			if($this.data('calendar-nav') == "next"){
				dateIndex++;
			}else if($this.data('calendar-nav') == "prev"){
				dateIndex--;
			}else{
				dateIndex = 0;
			}
			oDate = getMyDate(new Date().setMonth(new Date().getMonth()+dateIndex));
			getData(oDate)
			calendar.navigate($this.data('calendar-nav'));
		});
	});

	$('.btn-group button[data-calendar-view]').each(function() {
		var $this = $(this);
		$this.click(function() {
			calendar.view($this.data('calendar-view'));
		});
	});
	
	$('#format-12-hours').change(function(){
		var val = $(this).is(':checked') ? true : false;
		calendar.setOptions({format12: val});
		calendar.view();
	});
	$('#events-modal .modal-header, #events-modal .modal-footer').click(function(e){
		//e.preventDefault();
		//e.stopPropagation();
	});
    //获得年月日      得到日期oTime  
    function getMyDate(str){  
        var oDate = new Date(str),  
        oYear = oDate.getFullYear(),  
        oMonth = oDate.getMonth()+1,  
        oDay = oDate.getDate(),  
        oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) ;//最后拼接时间  
        return oTime;  
    };  
    //补0操作  
    function getzf(num){  
        if(parseInt(num) < 10){  
            num = '0'+num;  
        }  
        return num;  
    }

}(jQuery));
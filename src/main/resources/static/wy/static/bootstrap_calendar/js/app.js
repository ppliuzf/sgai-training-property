var localObj = window.location;

var contextPath = localObj.pathname.split("/")[1];

var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;

var server_context=basePath;
(function($) {
	"use strict";
	var events=new Array();
	var date=new Date().getTime();
	$.ajax({
		headers    : {
            "token" : localStorage.getItem("token"),
        },
	    url:APIHost+"/admin/ruag/ruagModelCalendar/getList",
     	type: "POST",
     	data: {},
     	dataType: "json",
     	cache: false,
     	async:false,
     	success: function(res){
     		 if(res.code === 1000){
     			events = res.data;
     		 }
     		
     	},
     	error: function(err){
     	}
     });
	
	var options = {
		events_source:events,
		view: 'month',
		tmpl_path: '/static/bootstrap_calendar/tmpls/',
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
	calendar.setOptions({first_day:"1"});
	calendar.setLanguage("zh-CN");
	calendar.setOptions({modal: "#events-modal"});
	calendar.setOptions({display_week_numbers: true});
	calendar.setOptions({weekbox: true});
	calendar.view();
	$('.btn-group button[data-calendar-nav]').each(function() {
		var $this = $(this);
		$this.click(function() {
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
$(function () {
    var flag = false;
    var rightDis = 0;
    // debugger;
    var startDate = '';
    var endDate = '';
    var nstr = new Date(); // 获取当前日期
    var changedYear = nstr.getFullYear(); // 年份  
    var changedMonth = nstr.getMonth(); // 月份  
    var dnow = nstr.getDate(); // 今日日期  月份中的多少号
    var n1str = new Date(changedYear, changedMonth, 1); // 当月第一天Date  
    
    var initfirstday = n1str.getDay() != 0 ? n1str.getDay() : 7; // 当月第一天星期几  
    var daynumber = getMonthAllDay(changedMonth, changedYear);  //当月有多少天
    var type = Number($.getQueryString('type'));

    var dataList = [];
         
    // 是否为闰年  
    function is_leap(year) {  
        return (year % 100 == 0 ? res = (year % 400 == 0 ? 1 : 0)  : res = (year % 4 == 0 ? 1 : 0));  
    }  
       
    // 获取当月的天数  
    function getMonthAllDay(month, year) {  
        var m_days = new Array(31, 28 + is_leap(year), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
        return m_days[month];  
    }  
      
    // 获得某年某月某日是星期几  
    function getFirstWeekDay(year, month, day) {  
        var date = new Date();  
        date.setFullYear(year);  
        date.setMonth(month);  
        date.setDate(day);  
        return date.getDay() != 0 ? date.getDay() : 7;  
    }  
      
    // 获得表格行数  
    function requiredRows(allday, firstday) {
        var trstr = Math.ceil((allday + firstday) / 7);  
        // alert("trstr"+trstr);  
        return trstr;  
    }  
    /* 显示日历 */  
    function showCanledar(month, firstday, dnow, allday) {  
        
        var rows = requiredRows(allday, firstday);
        console.log(changedYear, changedMonth + 1);
        var preMonthAllDay =  getMonthAllDay(changedMonth == 0 ? 11 : changedMonth - 1, changedYear);  
        $(".calendarOuter").html('');

        var lis = `<ul class="weekTittle">
            <li>星期一</li>
            <li>星期二</li>
            <li>星期三</li>
            <li>星期四</li>
            <li>星期五</li>
            <li class="weekend">星期六</li>
            <li class="weekend">星期日</li>  
        </ul>`;
        var containt = `<div class="matter-container"></div>`;
        var over= 1;
        // var countIndex = 0;
        for (var i = 0; i < rows; i++) {  
            lis += "<ul>";  
            for (var k = 0; k < 7; k++) { // 表格每行的单元格
                var idx = i * 7 + k; // 单元格自然序列号  
                var date_str = idx - firstday + 2; // 计算日期  (date_str == dnow ? changedYear == nstr.getFullYear()&&changedMonth == nstr.getMonth() ? "nowDay" : "" : "")
                 
                (date_str <= 0 || date_str > allday) ?  lis+="<li class='parter other-month' data-month="+ (date_str > allday ? changedMonth + 2 : changedMonth) +" data-index = "+ k +" style='border-top: 1px solid #a1a1a1'><span class='daySpan'>" + (date_str > allday ? over++ : preMonthAllDay + date_str) + "</span>" + containt + "</li>" : lis += "<li class='parter "+ (date_str == dnow ? changedYear == nstr.getFullYear()&&changedMonth == nstr.getMonth() ? "nowDay" : "" : "") +" ' data-month="+ (changedMonth + 1) +" data-index = "+ k +" style=' border-top: 1px solid #a1a1a1'"+ "><span class='daySpan'>" +date_str + "</span>" + containt +"</li>"; 
                if (k === 0 && i === 0) {
                    if (date_str > 0) {

                        startDate = changedYear + '-' + (changedMonth + 1 >= 10 ? changedMonth + 1 : '0'+ (changedMonth + 1)) + '-01' + ' 00:00:00';
                    } else {
                        startDate = (changedMonth == 0 ? changedYear - 1 : changedYear ) + '-' + (changedMonth >= 10 ? changedMonth :  changedMonth == 0 ? '12' : '0' + changedMonth) + '-' +  (preMonthAllDay + date_str >= 10 ? preMonthAllDay + date_str : '0'+ (preMonthAllDay + date_str)) + ' 00:00:00';
                    }
                    console.log(startDate);
                    startDate = new Date(startDate).getTime();
                    console.log(startDate);
                } 
                
            }  
            lis += "</ul>";  
            // 表格的行结束  
        }
        if (over !== 1) {
            endDate = (changedMonth==11 ? changedYear + 1 :  changedYear) + '-' + (changedMonth==11 ? '01' : changedMonth + 2 >= 10 ? changedMonth + 2 : '0' + (changedMonth + 2)) + '-' + (over - 1 >= 10 ?over - 1 : '0' +( over - 1)) + ' 23:59:59';
            console.log(endDate);
            endDate = new Date(endDate).getTime();
            console.log(endDate);
        } else {
            endDate = changedYear + '-' + (changedMonth + 1 >= 10 ? changedMonth + 1 : '0' + (changedMonth + 1)) + '-' + allday + ' 23:59:59';
            console.log(endDate);
            endDate = new Date(endDate).getTime();
            console.log(endDate);
        }  
        $(".calendarOuter").html(lis);
        $('.nowDay').prepend('<span class="tody-span">今天</span>');
        getList();
    }  
    function commChanged() {  
        var firstweekday = getFirstWeekDay(changedYear, parseInt(changedMonth), 1);       
        var allday = getMonthAllDay(parseInt(changedMonth), changedYear);
        showCanledar(changedMonth, firstweekday, dnow, allday);   
    }
    function getList() {
        $.getData({
            url: '/task/taskStatMonth',
            query: {
                startTime: startDate,
                endTime: endDate,
                type: type ? type: 0
            }
        }, function (data) {
            if (data) {
                dataList = data;
                for (var i = 0; i < data.length; i++) {
                    var date = new Date(data[i].dt);
                    dataList[i].dt =  date.toLocaleDateString().replace(/\//g, "-") + " " + date.toTimeString().substr(0, 8)
                }
                console.log(dataList);
                renderDetail();
            }
        });
    }
    function renderDetail () {
        // dataList = [
        //     {"dt": "2018-05-28 00:54:12", "arr": [{"stat": "W", "taskName": "工程验收"}, {"stat": "J", "taskName": "仓库巡视"}, {"stat": "Y", "taskName": "仓库巡视"}, {"stat": "P", "taskName": "仓库巡视"},{"stat": "W", "taskName": "工程验收"}, {"stat": "W", "taskName": "仓库巡视"}, {"stat": "W", "taskName": "仓库巡视"}, {"stat": "W", "taskName": "仓库巡视"}]},
        //     {"dt": "2018-06-20 00:54:12", "arr": [{"stat": "W", "taskName": "工程验收"}, {"stat": "J", "taskName": "仓库巡视"}, {"stat": "Y", "taskName": "仓库巡视"}, {"stat": "P", "taskName": "仓库巡视"},{"stat": "W", "taskName": "工程验收"}, {"stat": "W", "taskName": "仓库巡视"}, {"stat": "W", "taskName": "仓库巡视"}, {"stat": "W", "taskName": "仓库巡视"}]},
        //     {"dt": "2018-06-05 00:54:12", "arr": [{"stat": "W", "taskName": "工程验收"}, {"stat": "J", "taskName": "仓库巡视"}, {"stat": "Y", "taskName": "仓库巡视"}, {"stat": "P", "taskName": "仓库巡视"},{"stat": "W", "taskName": "工程验收"}, {"stat": "W", "taskName": "仓库巡视"}, {"stat": "W", "taskName": "仓库巡视"}, {"stat": "W", "taskName": "仓库巡视"}]},
        //     {"dt": "2018-06-11 00:54:12", "arr": [{"stat": "W", "taskName": "工程验收"}, {"stat": "J", "taskName": "仓库巡视"}, {"stat": "Y", "taskName": "仓库巡视"}, {"stat": "P", "taskName": "仓库巡视"},{"stat": "W", "taskName": "工程验收"}, {"stat": "W", "taskName": "仓库巡视"}, {"stat": "W", "taskName": "仓库巡视"}, {"stat": "W", "taskName": "仓库巡视"}]},
        //     {"dt": "2018-06-13 00:54:12", "arr": [{"stat": "W", "taskName": "工程验收"}, {"stat": "J", "taskName": "仓库巡视"}, {"stat": "Y", "taskName": "仓库巡视"}, {"stat": "P", "taskName": "仓库巡视"},{"stat": "W", "taskName": "工程验收1111222"}]}
        // ];
        
        console.log($('.parter').eq(0).text());
        for (var i = 0; i < dataList.length; i++) {
            console.log(dataList[i].dt.split(' ')[0].split('-'));
            for (var j = 0; j < $('.parter').length; j++) {
                var month = $('.parter').eq(j).data('month');
                var curDay = $('.parter').eq(j).find('.daySpan').text();
                if (changedYear == dataList[i].dt.split(' ')[0].split('-')[0] && month == dataList[i].dt.split(' ')[0].split('-')[1] && curDay == dataList[i].dt.split(' ')[0].split('-')[2]){
                    if(status){
                        // getMatterList(_this,i,j)    
                    }else{
                        // 0 非本月 绿色  1 本月非本日 灰色 2 本月本日 蓝色
                        var colorStatus = $('.parter').eq(j).data('month') == (changedMonth + 1) ?  $('.parter').eq(j).find('.daySpan').text() == dnow ? 2 : 1 : 0;
                        if(colorStatus === 0){
                            // $('.parter').eq(j).css('background','rgba(237, 249, 234, 1)');
                        }else if(colorStatus === 1){
                            // $('.parter').eq(j).css('background','#eee');
                        }else if(colorStatus === 2){
                            // $('.parter').eq(j).css('background','rgba(229, 243, 251, 1)');
                        }
                        $('.parter').eq(j).find('.matter-container').html(template('common/matter-calendar', {
                            items: dataList[i].taskTPList,
                            date: new Date(dataList[i].dt).getTime(),
                            countI:i,
                            countJ:j,
                            colorStatus: colorStatus
                        })) 
                    }     
                }
                
            }
            
        }
        
        
    }
    
    //点击颜色
    function getColor(get, set, status) {
        // $('.matter-all').addClass('allDefault');
        // if(get.data('color') == 0) {            
        //     set.css('background','#BEDDF1');
        // }else if(get.data('color') == 1) {
        //     set.css('background','#BEDDF1');
        // }else if(get.data('color') == 2) {
        //     set.css('background','#BEDDF1');
        // }
        if(status === 1) {
            set.css('background','#BEDDF1');
            $('.parter').removeClass('blue');
            get.parent().parent().parent().addClass('blue');
            $('.checkAll').removeClass('checkAll');
            get.find('.matter-all').addClass('checkAll');
        }else if(status === 2){
            $('.parter').removeClass('blue');
            set.parent().parent().parent().parent().addClass('blue');
            $('.checkAll').removeClass('checkAll');
            set.addClass('checkAll');
        }else{
            set.css('background','#BEDDF1');
            // $('.checkAll').removeClass('checkAll');
            get.find('.matter-all').addClass('checkAll');
        }
    }
    //获取任务详情
    function getTaskDetail(this_) {
        // $.getData({
        //     url: '/taskresult/executeTaskDetail',
        //     query: {
        //         recordId: $(this_).data('record'),
        //         templateId: $(this_).data('id'),
        //     }
        // }, function (data) {
        //     if (data) {  
        //         getMatterDetail(this_,data.taskBeginTime,data.taskEndTime)
        //     }
            
        // });
        getMatterDetail(this_,data.taskBeginTime,data.taskEndTime)
    }
    // 获取详情
    function getMatterDetail(this_,begin,end) {
        var executor = $(this_).data('executor');
        var dt = $(this_).data('dt');
        $('.matter-detail').remove();
        $('.matter-posit li').css('background','none');
        if(flag){
            $('.js-list td').css('background','none');
            getColor($(this_),$(this_).children());
            var title = $(this_).children().eq(0).text(),
                state = $(this_).children().eq(1).text(),
                that = $(this_).parent().parent().parent();
            $(that).append(template('common/matter-detail',{
                executor,
                dt,
                begin,
                end,
                title,
                state,
                color: $(this_).data('color'),
                isMyTask: true,
                id: $(this_).data('id'),
                record_id: $(this_).data('record'),
            }))
            var width = $('.matter-item').width()+ $('.js-list').width()+3;
            $('.matter-detail').css({'position':'absolute','left':rightDis <= 440 ? -443 : width});
        }else{
            $('.js-list').remove();
            // $('.matter-all').css('background','none');
            getColor($(this_).parent(),$(this_),1);
            var title = $(this_).find('span').text(),
                state = $(this_).find('a').text(),
                that = $(this_).parent().parent();
            $(that).append(template('common/matter-detail',{
                executor,
                begin,
                dt,
                end,
                title,
                state,
                color:$(this_).parent().data('color'),
                isMyTask: true,
                id: $(this_).data('id'),
                record_id: $(this_).data('record'),
            }))
            var width = $('.matter-item').width();
            rightDis = document.documentElement.clientWidth - $(this_).parent().offset().left - width;
            $(that).find('.matter-detail').css('left', rightDis <= 220 ? -220 : width);
        }
        flag = false;
    }; 
    //展开列表
    function getMatterList(this_) {
        $('.matter-posit li').css('background','none');
        getColor($(this_), $(this_).parent(),2);
        var countI = $(this_).data('counti');
        var countJ = $(this_).data('countj');
        var weekDay = $('.parter').eq(countJ).data('index');
        var index = $(this_).parent().parent().parent().parent().parent().data('index');
        $('.js-list').remove();
        console.log($('.parter').eq(countJ).data('month')+'--'+$('.parter').eq(countJ).find('.daySpan').text()+'--'+ countI)
        $('.parter').eq(countJ).find('.matter-container .matter-posit').append(template('common/matter-list', {
            items: dataList[countI].taskTPList,
            date: new Date(dataList[countI].dt).getTime(),
            month: $('.parter').eq(countJ).data('month'),
            day: $('.parter').eq(countJ).find('.daySpan').text(),
            weekDay: weekDay,
            color: $(this_).data('color'),
        })) 
        console.log($(this_).data('record'))
        var width = $('.matter-item').width();
        rightDis = document.documentElement.clientWidth - $(this_).parent().parent().offset().left - width;
        $('.matter-case').css('left', rightDis <= 440 ? -220 : width);
        $('.matter-detail').remove();
        console.log(document.documentElement.clientWidth - $(this_).parent().parent().offset().left - width);
    };
    $(document).on('click', '.matter-item li', function () {
        // getTaskDetail(this) 
        var start = $(this).data('start');
        var end = $(this).data('end');
        console.log(start, end);
        getMatterDetail(this,start,end);  
    });
    $(document).on('click', '.matter-detail .delete', function () {
        $('.matter-detail').remove();
        $('.matter-posit li').css('background','none');
    });
    $(document).on('click', '.js-list .delete', function () {
        $('.matter-detail').remove();
        $('.matter-all').css('background','none');
        $(this).parent().parent().parent().parent().remove();
    });
    $(document).on('click', '.matter-all a', function () {
        // renderDetail(true, this)
        getMatterList(this)
    });
    $(document).on('click', '.taskBtn', function () {
        var id = $(this).parent().data('id');
        var record = $(this).data('record');
        var state = $(this).data('state');
        var dt = $(this).data('dt');
        var stateId;
        if(state === '未开始'){
            stateId = '1';
        }else if(state === '执行中'){
            stateId = '2';
        }else if(state === '已逾期'){
            stateId = '3';
        }else if(state === '已完成'){
            stateId = '4';
        }
        if($(this).text() == '任务详情'){
            window.location.href = 'pages/result/result-detail.html?tpId='+id+'&record_id='+record+'&state='+stateId+'&dt=' + dt;
        }else {
            window.location.href = 'task-detail.html?type=1&tpId=' + id + '&record_id=' + record + '&state=' + stateId + '&dt=' + dt + '&flag=0';
        }
    });
    $(document).on('click', '.js-list tbody tr', function () {
        flag = true;
        // getTaskDetail(this)
        var start = $(this).data('start');
        var end = $(this).data('end');
        console.log(start, end);
        getMatterDetail(this,start,end);
    });
    $(document).on('click', '.js-task-result', function () {
        window.location.href = 'task-per.html' + (type ? '?type=1' : '');
    });
    function init () {
        showCanledar(changedMonth, initfirstday, dnow, daynumber);  
        $('.js-top-date').html(template('result/top-date', {
            year: changedYear,
            month: changedMonth + 1
        }));
        $(document).on('click', '.js-pre-date', function () {
            var curPreMonth = changedMonth - 1;
            if (curPreMonth < 0) {
                changedYear = changedYear - 1;
                curPreMonth = 11;
            }
            changedMonth = curPreMonth;
            $('.js-top-date').html(template('result/top-date', {
                year: changedYear,
                month: changedMonth + 1
            }));
            commChanged();  

            $('.js-pre-date').css({'background-color': '#1785ca', 'color':'white'});
            $('.js-pre-date').siblings().css({'background-color': 'white', 'color':'black'});
            
        });
        $(document).on('click', '.js-next-date', function () {
            var curNextMonth = changedMonth + 1;
            if (curNextMonth > 11) {
                changedYear = changedYear + 1;
                curNextMonth = 0;
            }
            changedMonth = curNextMonth;
            $('.js-top-date').html(template('result/top-date', {
                year: changedYear,
                month: changedMonth + 1
            }));
            commChanged();  
            $('.js-next-date').css({'background-color': '#1785ca', 'color':'white'});
            $('.js-next-date').siblings().css({'background-color': 'white', 'color':'black'});
            console.log(changedYear, changedMonth + 1);
        });   
        $(document).on('click', '.js-cur-date', function () {
           
            changedYear = nstr.getFullYear(); // 当前年份  
            changedMonth = nstr.getMonth(); // 当前月份
            $('.js-top-date').html(template('result/top-date', {
                year: changedYear,
                month: changedMonth + 1
            }));
            commChanged();    
            $('.js-cur-date').css({'background-color': '#1785ca', 'color':'white'});
            $('.js-cur-date').siblings().css({'background-color': 'white', 'color':'black'});
        }); 
    }
    init();
});
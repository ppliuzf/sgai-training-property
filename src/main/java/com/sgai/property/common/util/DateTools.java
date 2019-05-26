package com.sgai.property.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateTools {

    /**
     * 日期转星期
     * 根式 2018-06-21
     *1：周一，2：周二:3：周三:4：周四:5：周五:6：周六:0：周日
     * @param datetime
     * @return
     */
    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"0", "1", "2", "3", "4", "5", "6"};
        // 获得一个日历
        Calendar cal = Calendar.getInstance();
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 指示一个星期中的某天。
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }


    /**
     * 获取时间区间的所有时间集合
     *
     * @param dBegin
     * @param dEnd
     * @return
     */
    public static List<Date> findDates(Date dBegin, Date dEnd) {

        List lDate = new ArrayList();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }

    /**
     * 获取时间区间的所有时间
     *
     * @param dBegin
     * @param dEnd
     * @return
     */
    public static List<Date> findDates(Long dBegin, Long dEnd) {
        List lDate = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date beginDate = new Date(dBegin);
        Date endDate = new Date(dEnd);
        String beginDate1 = sdf.format(beginDate);
        String endDate1 = sdf.format(endDate);
        try {
            Date dBegin1 = sdf.parse(beginDate1);
            Date dEnd1 = sdf.parse(endDate1);

            lDate.add(dBegin1);
            Calendar calBegin = Calendar.getInstance();
            // 使用给定的 Date 设置此 Calendar 的时间
            calBegin.setTime(dBegin1);
            Calendar calEnd = Calendar.getInstance();
            // 使用给定的 Date 设置此 Calendar 的时间
            calEnd.setTime(dEnd1);
            // 测试此日期是否在指定日期之后
            while (dEnd1.after(calBegin.getTime())) {
                // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
                calBegin.add(Calendar.DAY_OF_MONTH, 1);
                lDate.add(calBegin.getTime());
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return lDate;
    }
    /**
     * 获取时间区间的所有时间(包含开始时间+包含结束时间)
     *
     * @param dBegin
     * @param dEnd
     * @return
     */
    public static List<Date> getDates(Date dBegin, Date dEnd) {
    	List<Date> list_date = new ArrayList<Date>();
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(dBegin);
        
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(dEnd);
        list_date.add(calBegin.getTime());
    	while (calBegin.before(calEnd)&& !DateUtils.isSameDay(calBegin, calEnd)) {
    		  calBegin.add(Calendar.DAY_OF_MONTH, 1);
    		  list_date.add(calBegin.getTime());
		}
    	return list_date;
    }
    
    /**
     * 获取时间区间内所有时间 每周几的日期
     * @param dBegin
     * @param dEnd
     * @param weeks 一周内选择的天数，如可能是周1和周2 则查询这段时间内的 所有周1和周2的日期
     * @return
     */
    public static List<Date> getDatesOfWeek(Date dBegin, Date dEnd,int[] weeks) {
    	List<Date> list_date = new ArrayList<Date>();
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(dBegin);
        
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(dEnd);
        
    	while (calBegin.before(calEnd)&& !DateUtils.isSameDay(calBegin, calEnd)) {
    		  calBegin.add(Calendar.DAY_OF_MONTH, 1);
    		  list_date.add(calBegin.getTime());
//    		  list_Calendar.add(calBegin);
		}
    	List<Date> finalList = new ArrayList<Date>();
    	for(Date date :list_date){
    	    Calendar cal_temp = Calendar.getInstance();
    	    cal_temp.setTime(date);
    	    // 获得星期几（注意（这个与Date类是不同的）：1代表星期日、2代表星期1、3代表星期二，以此类推）
    	    int i  = cal_temp.get(Calendar.DAY_OF_WEEK);
    		if (Arrays.asList().contains(i)) {
    			finalList.add(cal_temp.getTime());
			}
    	}
    	return finalList;
    }
    
    /**
     * 获取时间区间内  包含一段月内日期 的所有日期 
     * @param dates 日期区间
     * @param beginDayofMonth 每月的开始日期
     * @param endDayofMonth 每月的结束日期
     * @return
     */
    public static List<Date> getDatesOfMonth(List<Date> dates,String beginDayofMonth, String endDayofMonth) {
    	List<Date>  list = new ArrayList<Date>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	for(Date date :dates){
    		Calendar cal = Calendar.getInstance();
    		cal.setTime(date);
    	 	int year  =  cal.get(Calendar.YEAR);
    		int month  =  cal.get(Calendar.MONTH);
    		Calendar cal_beginTemp = Calendar.getInstance();
    		cal_beginTemp.set(Calendar.YEAR, year);
    		cal_beginTemp.set(Calendar.MONTH, month);
    		cal_beginTemp.set(Calendar.DAY_OF_MONTH, new Integer(beginDayofMonth));
    		
    		Calendar cal_endTemp = Calendar.getInstance();
    		cal_endTemp.set(Calendar.YEAR, year);
    		cal_endTemp.set(Calendar.MONTH, month);
    		cal_endTemp.set(Calendar.DAY_OF_MONTH, new Integer(endDayofMonth));
    		
    		List<Date> list_date  =  getDates(cal_beginTemp.getTime(), cal_endTemp.getTime());
    		for (Date date_temp :list_date) {
    			if (DateUtils.isSameDay(date, date_temp)) {
    				list.add(date);
    				break;
				}
			}
    	}
    	return list;
    }
    /***
     * 把jh_task表TASK_DAY字段值转成日期函数中星期值
     * @param str
     * @return
     */
    public static int[] strToWeeks(String str){
//		 0：周一，1：周二:2：周三:3：周四:4：周五:5：周六:6：周日
//  现在改为  	1：周一，2：周二:3：周三:4：周四:5：周五:6：周六:7：周日
//		int  weeks[] = null;
    	int int_weeks[] = null;
		 if (!StringUtils.isBlank(str)) {
			 String weeksStr[]  = null;
			 if (str.indexOf(",")!= -1) {
				weeksStr  = str.split(",");
			 }else{
				 weeksStr = new String[1];
				 weeksStr[0] = str;
			 }
			 int_weeks = new int[weeksStr.length];
			 for(int i = 0 ;i<weeksStr.length;i++){
				 // 获得星期几   转成日期函数中的值（注意（这个与Date类是不同的）：1代表星期日、2代表星期1、3代表星期二，以此类推）
				 String strTemp = weeksStr[i];
				 if (strTemp.equals("7")) {
					 int_weeks[i] = 1;
				 }else{
					 int_weeks[i] = new Integer(strTemp)+1;
				 }
			 }
		 }
		return int_weeks;
    }
    /***
     *  从time1中获取小时和分钟，设置到time2
     * @param time1 带有小时和分钟的时间
     * @return
     */
    public static Long getHourMinuteFromTime1(Long time1,Long time){
	 	/**得到任务当天具体执行时间 的小时和分钟 */
        Calendar calbegin = Calendar.getInstance();
        calbegin.setTime(new Date(time1));
        int hour =   calbegin.get(Calendar.HOUR_OF_DAY);
        // 获得分钟
        int minute = calbegin.get(Calendar.MINUTE);
	 
        Calendar calbegin2 = Calendar.getInstance();
        calbegin2.setTime(new Date(time));
        calbegin2.set(Calendar.HOUR_OF_DAY,hour);
        calbegin2.set(Calendar.MINUTE,minute);
        return calbegin2.getTime().getTime();
    }
    
    public static void main(String args[]){
        Calendar calBegin = Calendar.getInstance();
        
//  		 Long taskBeginTime = spring.getTaskBeginTime();
//	     Long taskEndTime = spring.getTaskEndTime();
//        Calendar calbegin = Calendar.getInstance();
//        calbegin.setTime(new Date(date));
//        int year =   calbegin.get(Calendar.YEAR);
//        int month =   calbegin.get(Calendar.MONTH);
//        int day =   calbegin.get(Calendar.DAY_OF_MONTH);
//        int hour =   calbegin.get(Calendar.HOUR_OF_DAY);
//        // 获得分钟
//        int minute = calbegin.get(Calendar.MINUTE);
        
        calBegin.add(Calendar.YEAR, -1);
        calBegin.set(Calendar.DATE, 6);
        calBegin.set(Calendar.MONTH, 10);
//        calBegin.setTime(new Date());
        
        
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(new Date());

        int weeks[] = new int[]{2,3,4};
        List<Date> list_date = new ArrayList<Date>();
        list_date = getDatesOfWeek(calBegin.getTime(), calEnd.getTime(), weeks);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Date> lists  = getDates(calBegin.getTime(), calEnd.getTime());
        for(Date date :lists){
        	System.out.println(sdf.format(date));
        }
        List<Date> lists2 =  getDatesOfMonth(lists, "27", "28");
//        for(Date date :list_date){
//        	System.out.println(sdf.format(date));
//        }
        for(Date date :lists2){
        	System.out.println(":::::::"+sdf.format(date));
        }
        
//        String str = "0,1,2,3,4,5,6";
//         int []a = strToWeeks(str);
//         System.out.println(java.util.Arrays.toString(a));
//        System.out.println(list_date.size());
//        System.out.println(list_date);
    }
}


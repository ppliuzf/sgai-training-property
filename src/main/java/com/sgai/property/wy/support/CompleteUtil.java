package com.sgai.property.wy.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @Description:补齐两个时间段的日期
 * @Author: cuiwenjian
 */
public class CompleteUtil {

    public static Book complete(String begin, String end, String mod) {
    	Logger logger = LoggerFactory.getLogger(CompleteUtil.class);
    	Book book = new Book();
        List<Map<String,Object>> page = new ArrayList<Map<String,Object>>();
        Map<String,Integer> menu =  new HashMap<String,Integer>();
        book.setMenu(menu);
        book.setPage(page);
        int calMod=0;
        SimpleDateFormat modFormat = null;
        if("2".equals(mod)){
        	modFormat = new SimpleDateFormat("yyyy-MM-dd");
            calMod=Calendar.DATE;
        }else if("1".equals(mod)){
        	modFormat = new SimpleDateFormat("yyyy-MM");
            calMod=Calendar.MONTH;
        }else if("0".equals(mod)){
        	modFormat = new SimpleDateFormat("yyyy");
            calMod=Calendar.YEAR;
        }
        try {
        	Date parseBegin = modFormat.parse(begin);
            Date parseEnd = modFormat.parse(end);
            Calendar cal = Calendar.getInstance();
            // 补齐列表charts
            int menuCount=0;
            cal.setTime(parseBegin);
                while (cal.getTime().getTime()<=parseEnd.getTime()) {
                	Map<String,Object> pageDetail = new HashMap<String,Object>();
                	String menuDetail =  modFormat.format(cal.getTime());
                	pageDetail.put(menuDetail, 0l);
                	page.add(pageDetail);
                    menu.put(menuDetail,menuCount++);
                    cal.add(calMod, 1);
                }
        }catch (Throwable e){
        	logger.error("error:",e);
        }
      
        return book;
    }    

}

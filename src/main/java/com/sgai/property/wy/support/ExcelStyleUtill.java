  
    /**    
    * @Title: ExcelStyleUtill.java  
    * @Package com.sgai.property.wy.support
    * (用一句话描述该文件做什么)
    * @author cui  
    * @date 2018年3月19日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.support;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

    /**
        * @ClassName: ExcelStyleUtill
        * (设置表格头样式)
        * @author cui
        * @date 2018年3月19日
        * @Company 首自信--智慧城市创新中心
        */

    public class ExcelStyleUtill {
        public static void CellStyle(HSSFCellStyle style, HSSFWorkbook book){
            //style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
            //为了能够使用换行，设置单元格的样式 wrap=true
            style.setWrapText(true);
            HSSFFont contentFont = book.createFont(); // 定义字体
            contentFont.setFontName("宋体");//设置字体
            contentFont.setFontHeightInPoints((short) 14);//设置字号
            contentFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
            style.setFillPattern(HSSFCellStyle.LEAST_DOTS);
            style.setFillBackgroundColor(HSSFCellStyle.LEAST_DOTS);
            style.setFont(contentFont);
            //设置Excel中的背景
            style.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
            style.setFillBackgroundColor(HSSFColor.LIGHT_BLUE.index);
        }
    }

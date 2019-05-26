
package com.sgai.property.wy.service;

import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.wy.dao.MeetingOrderDao;
import com.sgai.property.wy.entity.MeetingOrder;
import com.sgai.property.wy.support.ExcelStyleUtill;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

/***
 *
 */

@Service
public class MeetingOrderService extends CrudServiceExt<MeetingOrderDao, MeetingOrder> {


    @Autowired
    private MeetingOrderDao meetingOrderDao;

    public void export(HttpServletResponse response, MeetingOrder meetingOrder) throws IOException {
        List<MeetingOrder> meetingOrders = meetingOrderDao.findList(meetingOrder);
        OutputStream os = response.getOutputStream();// 取得输出流
        response.reset();//清空输出流
        if ("meetingRoom".equals(meetingOrder.getSourceType())) {
            response.setHeader("Content-disposition", "attacment;filename=" + new String("会议服务.xls".getBytes("UTF-8"), "ISO-8859-1"));//通知浏览器要下载
            response.setContentType("application/msexcel");// 定义输出类型
            exportMeetingOrder_meetingRoom(meetingOrders, os);
        } else if ("fitness".equals(meetingOrder.getSourceType())) {
            response.setHeader("Content-disposition", "attacment;filename=" + new String("活动服务.xls".getBytes("UTF-8"), "ISO-8859-1"));//通知浏览器要下载
            response.setContentType("application/msexcel");// 定义输出类型
            exportMeetingOrder_fitness(meetingOrders, os);
        }
    }

    private void exportMeetingOrder_meetingRoom(List<MeetingOrder> meetingOrders, OutputStream os) throws IOException {
        // 创建工作簿
        HSSFWorkbook book = new HSSFWorkbook();
        // 创建Sheet对象
        HSSFSheet sheet = book.createSheet("会议服务");
		HSSFCellStyle style = book.createCellStyle();
		ExcelStyleUtill.CellStyle(style, book);
		HSSFRow row0 = sheet.createRow((short) 0);
		HSSFCell cell00 = row0.createCell(0);
		row0.setHeight((short) 1200);
		cell00.setCellStyle(style);
		// 增加单元格的高度
		row0.setHeightInPoints((short) 60);
        HSSFCell cell01 = row0.createCell(1);
        cell01.setCellStyle(style);
        HSSFCell cell02 = row0.createCell(2);
        cell02.setCellStyle(style);
        HSSFCell cell03 = row0.createCell(3);
        cell03.setCellStyle(style);
        HSSFCell cell04 = row0.createCell(4);
        cell04.setCellStyle(style);
        HSSFCell cell05 = row0.createCell(5);
        cell05.setCellStyle(style);
        HSSFCell cell06 = row0.createCell(6);
        cell06.setCellStyle(style);
        HSSFCell cell07 = row0.createCell(7);
        cell07.setCellStyle(style);
        HSSFCell cell08 = row0.createCell(8);
        cell08.setCellStyle(style);
        HSSFCell cell09 = row0.createCell(9);
        cell09.setCellStyle(style);
        HSSFCell cell10 = row0.createCell(10);
        cell10.setCellStyle(style);
        HSSFCell cell11 = row0.createCell(11);
        cell11.setCellStyle(style);
        HSSFCell cell12 = row0.createCell(12);
        cell12.setCellStyle(style);
        HSSFCell cell13 = row0.createCell(13);
        cell13.setCellStyle(style);
        HSSFCell cell14 = row0.createCell(14);
        cell14.setCellStyle(style);
        HSSFCell cell15 = row0.createCell(15);
        cell15.setCellStyle(style);
        HSSFCell cell16 = row0.createCell(16);
        cell16.setCellStyle(style);
        HSSFCell cell17 = row0.createCell(17);
        cell17.setCellStyle(style);
        HSSFCell cell18 = row0.createCell(18);
        cell18.setCellStyle(style);
        HSSFCell cell19 = row0.createCell(19);
        cell19.setCellStyle(style);
        HSSFCell cell20 = row0.createCell(20);
        cell20.setCellStyle(style);
        HSSFCell cell21 = row0.createCell(21);
        cell21.setCellStyle(style);
        HSSFCell cell22 = row0.createCell(22);
        cell22.setCellStyle(style);
        HSSFCell cell23 = row0.createCell(23);
        cell23.setCellStyle(style);
        HSSFCell cell24 = row0.createCell(24);
        cell24.setCellStyle(style);
        // 向单元格中设置值
        cell00.setCellValue("序号");
        cell01.setCellValue("开始时间");
        cell02.setCellValue("结束时间");
        cell03.setCellValue("预约部门");
        cell04.setCellValue("会议名称");
        cell05.setCellValue("人数");
        cell06.setCellValue("预约人");
        cell07.setCellValue("实际参会人数");
        cell08.setCellValue("联系电话");
        cell09.setCellValue("会议类型");
        cell10.setCellValue("会议规模");
        cell11.setCellValue("会议级别");
        cell12.setCellValue("记录人");
        cell13.setCellValue("服务状态");
        cell14.setCellValue("茶水");
        cell15.setCellValue("瓶装水");
        cell16.setCellValue("咖啡");
        cell17.setCellValue("热毛巾");
        cell18.setCellValue("玻璃杯");
        cell19.setCellValue("签字笔");
        cell20.setCellValue("A4纸");
        cell21.setCellValue("白板");
        cell22.setCellValue("红蓝铅笔");
        cell23.setCellValue("2B铅笔");
        cell24.setCellValue("备注");
        // 向excel中插入数据
        for (int i = 0; i < meetingOrders.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);
            HSSFCell cell30 = row.createCell(0);
            HSSFCell cell31 = row.createCell(1);
            HSSFCell cell32 = row.createCell(2);
            HSSFCell cell33 = row.createCell(3);
            HSSFCell cell34 = row.createCell(4);
            HSSFCell cell35 = row.createCell(5);
            HSSFCell cell36 = row.createCell(6);
            HSSFCell cell37 = row.createCell(7);
            HSSFCell cell38 = row.createCell(8);
            HSSFCell cell39 = row.createCell(9);
            HSSFCell cell40 = row.createCell(10);
            HSSFCell cell41 = row.createCell(11);
            HSSFCell cell42 = row.createCell(12);
            HSSFCell cell43 = row.createCell(13);
            HSSFCell cell44 = row.createCell(14);
            HSSFCell cell45 = row.createCell(15);
            HSSFCell cell46 = row.createCell(16);
            HSSFCell cell47 = row.createCell(17);
            HSSFCell cell48 = row.createCell(18);
            HSSFCell cell49 = row.createCell(19);
            HSSFCell cell50 = row.createCell(20);
            HSSFCell cell51 = row.createCell(21);
            HSSFCell cell52 = row.createCell(22);
            HSSFCell cell53 = row.createCell(23);
            HSSFCell cell54 = row.createCell(24);
            cell30.setCellValue(i + 1);
            MeetingOrder meetingOrder = meetingOrders.get(i);
            cell31.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(meetingOrder.getBeginTime()));
            cell32.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(meetingOrder.getEndTime()));
            cell33.setCellValue(meetingOrder.getOrderDepartment());
            cell34.setCellValue(meetingOrder.getMeetingName());
            cell35.setCellValue(meetingOrder.getOrderPeopleNum());
            cell36.setCellValue(meetingOrder.getContactPeople());
            cell37.setCellValue(meetingOrder.getRealPeopleNum() == null ? "" : meetingOrder.getRealPeopleNum().toString());
            cell38.setCellValue(meetingOrder.getPhone());
            cell39.setCellValue(meetingOrder.getMeetingType());
            cell40.setCellValue(meetingOrder.getMeetingScale());
            cell41.setCellValue(meetingOrder.getMeetingLeval());
            cell42.setCellValue(meetingOrder.getRecordPeople());
            cell43.setCellValue(meetingOrder.getServiceType());
            cell44.setCellValue(meetingOrder.getTeaNum() == null ? "" : meetingOrder.getTeaNum().toString());
            cell45.setCellValue(meetingOrder.getWaterBottleNum() == null ? "" : meetingOrder.getWaterBottleNum().toString());
            cell46.setCellValue(meetingOrder.getCoffeeNum() == null ? "" : meetingOrder.getCoffeeNum().toString());
            cell47.setCellValue(meetingOrder.getTowelNum() == null ? "" : meetingOrder.getTowelNum().toString());
            cell48.setCellValue(meetingOrder.getGlassNum() == null ? "" : meetingOrder.getGlassNum().toString());
            cell49.setCellValue(meetingOrder.getSignPenNum() == null ? "" : meetingOrder.getSignPenNum().toString());
            cell50.setCellValue(meetingOrder.getPaperNum() == null ? "" : meetingOrder.getPaperNum().toString());
            cell51.setCellValue(meetingOrder.getWhiteBoardNum() == null ? "" : meetingOrder.getWhiteBoardNum().toString());
            cell52.setCellValue(meetingOrder.getPencilsNum() == null ? "" : meetingOrder.getPencilsNum().toString());
            cell53.setCellValue(meetingOrder.getPencils2bNum() == null ? "" : meetingOrder.getPencils2bNum().toString());
            cell54.setCellValue(meetingOrder.getRemark() == null ? "" : meetingOrder.getRemark().toString());
    
            //book.close();
        }
        // 将数据写出
        book.write(os);
        os.close();
    }

    private void exportMeetingOrder_fitness(List<MeetingOrder> meetingOrders, OutputStream os) throws IOException {
        // 创建工作簿
        HSSFWorkbook book = new HSSFWorkbook();
        // 创建Sheet对象
        HSSFSheet sheet = book.createSheet("活动服务");
        // 创建行Row
        HSSFRow row0 = sheet.createRow(0);
        // 创建单元格（Cell）（共25列）
        HSSFCell cell00 = row0.createCell(0);
        HSSFCell cell01 = row0.createCell(1);
        HSSFCell cell02 = row0.createCell(2);
        HSSFCell cell03 = row0.createCell(3);
        HSSFCell cell04 = row0.createCell(4);
        HSSFCell cell05 = row0.createCell(5);
        HSSFCell cell06 = row0.createCell(6);
        HSSFCell cell07 = row0.createCell(7);
        HSSFCell cell08 = row0.createCell(8);
        HSSFCell cell09 = row0.createCell(9);
        HSSFCell cell10 = row0.createCell(10);
        HSSFCell cell11 = row0.createCell(11);
        HSSFCell cell12 = row0.createCell(12);
        HSSFCell cell13 = row0.createCell(13);
        HSSFCell cell14 = row0.createCell(14);
        HSSFCell cell15 = row0.createCell(15);
        HSSFCell cell16 = row0.createCell(16);
        HSSFCell cell17 = row0.createCell(17);
        HSSFCell cell18 = row0.createCell(18);
        HSSFCell cell19 = row0.createCell(19);
        HSSFCell cell20 = row0.createCell(20);
        HSSFCell cell21 = row0.createCell(21);
        HSSFCell cell22 = row0.createCell(22);
        HSSFCell cell23 = row0.createCell(23);
        HSSFCell cell24 = row0.createCell(24);
        HSSFCell cell25 = row0.createCell(25);
        HSSFCell cell26 = row0.createCell(26);
        // 向单元格中设置值
        cell00.setCellValue("序号");
        cell01.setCellValue("开始时间");
        cell02.setCellValue("结束时间");
        cell03.setCellValue("预约部门");
        cell04.setCellValue("活动名称");
        cell05.setCellValue("人数");
        cell06.setCellValue("预约人");
        cell07.setCellValue("实际参加人数");
        cell08.setCellValue("联系电话");
        cell09.setCellValue("活动类型");
        cell10.setCellValue("活动规模");
        cell11.setCellValue("记录人");
        cell12.setCellValue("茶水");
        cell13.setCellValue("瓶装水");
        cell14.setCellValue("桶装水");
        cell15.setCellValue("咖啡");
        cell16.setCellValue("热毛巾");
        cell17.setCellValue("纸巾");
        cell18.setCellValue("一次性纸杯");
        cell19.setCellValue("消耗品");
        cell20.setCellValue("玻璃杯");
        cell21.setCellValue("签字笔");
        cell22.setCellValue("A4纸");
        cell23.setCellValue("白板");
        cell24.setCellValue("红蓝铅笔");
        cell25.setCellValue("2B铅笔");
        cell26.setCellValue("备注");
        // 向excel中插入数据
        for (int i = 0; i < meetingOrders.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);
            HSSFCell cell30 = row.createCell(0);
            HSSFCell cell31 = row.createCell(1);
            HSSFCell cell32 = row.createCell(2);
            HSSFCell cell33 = row.createCell(3);
            HSSFCell cell34 = row.createCell(4);
            HSSFCell cell35 = row.createCell(5);
            HSSFCell cell36 = row.createCell(6);
            HSSFCell cell37 = row.createCell(7);
            HSSFCell cell38 = row.createCell(8);
            HSSFCell cell39 = row.createCell(9);
            HSSFCell cell40 = row.createCell(10);
            HSSFCell cell41 = row.createCell(11);
            HSSFCell cell42 = row.createCell(12);
            HSSFCell cell43 = row.createCell(13);
            HSSFCell cell44 = row.createCell(14);
            HSSFCell cell45 = row.createCell(15);
            HSSFCell cell46 = row.createCell(16);
            HSSFCell cell47 = row.createCell(17);
            HSSFCell cell48 = row.createCell(18);
            HSSFCell cell49 = row.createCell(19);
            HSSFCell cell50 = row.createCell(20);
            HSSFCell cell51 = row.createCell(21);
            HSSFCell cell52= row.createCell(22);
            HSSFCell cell53 = row.createCell(23);
            HSSFCell cell54 = row.createCell(24);
            HSSFCell cell55 = row.createCell(25);
            HSSFCell cell56 = row.createCell(26);
            cell30.setCellValue(i + 1);
            MeetingOrder meetingOrder = meetingOrders.get(i);
            cell31.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(meetingOrder.getBeginTime()));
            cell32.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(meetingOrder.getEndTime()));
            cell33.setCellValue(meetingOrder.getOrderDepartment());
            cell34.setCellValue(meetingOrder.getMeetingName());
            cell35.setCellValue(meetingOrder.getOrderPeopleNum()== null ? "" :meetingOrder.getOrderPeopleNum().toString());
            cell36.setCellValue(meetingOrder.getContactPeople());
            cell37.setCellValue(meetingOrder.getRealPeopleNum() == null ? "" : meetingOrder.getRealPeopleNum().toString());
            cell38.setCellValue(meetingOrder.getPhone());
            cell39.setCellValue(meetingOrder.getMeetingType());
            cell40.setCellValue(meetingOrder.getMeetingScale());
            cell41.setCellValue(meetingOrder.getRecordPeople());
            cell42.setCellValue(meetingOrder.getTeaNum() == null ? "" : meetingOrder.getTeaNum().toString());
            cell43.setCellValue(meetingOrder.getWaterBottleNum() == null ? "" : meetingOrder.getWaterBottleNum().toString());
            cell44.setCellValue(meetingOrder.getCoffeeNum() == null ? "" : meetingOrder.getCoffeeNum().toString());
            cell45.setCellValue(meetingOrder.getWaterBucketNum() == null ? "" : meetingOrder.getWaterBucketNum().toString());
            cell46.setCellValue(meetingOrder.getTowelNum() == null ? "" : meetingOrder.getTowelNum().toString());
            cell47.setCellValue(meetingOrder.getTissueNum() == null ? "" : meetingOrder.getTissueNum().toString());
            cell48.setCellValue(meetingOrder.getDixieCupNum() == null ? "" : meetingOrder.getDixieCupNum().toString());
            cell49.setCellValue(meetingOrder.getConsumableNum() == null ? "" : meetingOrder.getConsumableNum().toString());
            cell50.setCellValue(meetingOrder.getGlassNum() == null ? "" : meetingOrder.getGlassNum().toString());
            cell51.setCellValue(meetingOrder.getSignPenNum() == null ? "" : meetingOrder.getSignPenNum().toString());
            cell52.setCellValue(meetingOrder.getPaperNum() == null ? "" : meetingOrder.getPaperNum().toString());
            cell53.setCellValue(meetingOrder.getWhiteBoardNum() == null ? "" : meetingOrder.getWhiteBoardNum().toString());
            cell54.setCellValue(meetingOrder.getPencilsNum() == null ? "" : meetingOrder.getPencilsNum().toString());
            cell55.setCellValue(meetingOrder.getPencils2bNum() == null ? "" : meetingOrder.getPencils2bNum().toString());
            cell56.setCellValue(meetingOrder.getRemark()== null ? "" : meetingOrder.getRemark());
  
            //book.close();
        }
        // 将数据写出
        book.write(os);
        os.close();
    }
}

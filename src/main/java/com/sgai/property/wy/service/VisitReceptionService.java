package com.sgai.property.wy.service;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.wy.dao.VisitReceptionDao;
import com.sgai.property.wy.entity.VisitReception;
import com.sgai.property.wy.support.ExcelStyleUtill;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
    * @ClassName: VisitReceptionService  
    * (访客记录管理服务)
    * @author LiuXiaobing  
    * @date 2018年1月20日  
    * @Company 首自信--智慧城市创新中心
*/
@Service
public class VisitReceptionService extends CrudServiceExt<VisitReceptionDao,VisitReception> {
	
	//查询数据并分页显示
	public Page<VisitReception> findPage(Page<VisitReception> page, VisitReception visitReception) {
		return super.findPage(page, visitReception);
	}
	
	//批量删除数据
	@Transactional(readOnly = false)
	public void batchDeleteVisitReception(List<String> idList) {
		dao.batchDelete(idList);
	}
	
	//导出数据到Excel
	public void export(VisitReception visitReception,HttpServletResponse response) throws IOException {
		OutputStream os = response.getOutputStream();// 取得输出流 
		response.reset();//清空输出流
		response.setHeader("Content-disposition", "attacment;filename="+new String("参观接待.xls".getBytes("UTF-8"),"ISO-8859-1"));//通知浏览器要下载
		response.setContentType("application/msexcel");// 定义输出类型 
		if(visitReception.getAppVisitTime()!=null){
            Date time = visitReception.getAppVisitTime();
            Calendar c = Calendar.getInstance();
            c.setTime(time);
            c.set(Calendar.DAY_OF_YEAR,c.get(Calendar.DAY_OF_YEAR)+1);
            visitReception.setAppVisitTime(c.getTime());
        }
		//数据类型转换
		List<VisitReception> list = super.findList(visitReception);
		exportVisitReception(list, os);
	}
	
	private void exportVisitReception(List<VisitReception> list, OutputStream os) throws IOException {
		
		// 创建工作簿
		HSSFWorkbook book = new HSSFWorkbook();
		// 创建Sheet对象
		HSSFSheet sheet = book.createSheet("参观接待信息");
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
		HSSFCell cell010 = row0.createCell(10);
		cell010.setCellStyle(style);
		HSSFCell cell011 = row0.createCell(11);
		cell011.setCellStyle(style);
		HSSFCell cell012 = row0.createCell(12);
		cell012.setCellStyle(style);
		HSSFCell cell013 = row0.createCell(13);
		cell013.setCellStyle(style);
		HSSFCell cell014 = row0.createCell(14);
		cell014.setCellStyle(style);
		
		// 向单元格中设置值
		cell00.setCellValue("序号");
		cell01.setCellValue("预约时间");
		cell02.setCellValue("预约人姓名");
		cell03.setCellValue("预约人电话");
		cell04.setCellValue("接待时间");
		cell05.setCellValue("接待地点");
		cell06.setCellValue("参观单位");
		cell07.setCellValue("参观人数");
		cell08.setCellValue("领队电话");
		cell09.setCellValue("参观级别");
		cell010.setCellValue("预约参观时间");
		cell011.setCellValue("参观区域");
		cell012.setCellValue("结束时间");
		cell013.setCellValue("参观用时");
		cell014.setCellValue("备注");

		// 向excel中插入数据
		for (int i = 0; i < list.size(); i++) {
			HSSFRow row = sheet.createRow(i + 1);
			HSSFCell cell0 = row.createCell(0);
			HSSFCell cell1 = row.createCell(1);
			HSSFCell cell2 = row.createCell(2);
			HSSFCell cell3 = row.createCell(3);
			HSSFCell cell4 = row.createCell(4);
			HSSFCell cell5 = row.createCell(5);
			HSSFCell cell6 = row.createCell(6);
			HSSFCell cell7 = row.createCell(7);
			HSSFCell cell8 = row.createCell(8);
			HSSFCell cell9 = row.createCell(9);
			HSSFCell cell10 = row.createCell(10);
			HSSFCell cell11 = row.createCell(11);
			HSSFCell cell12 = row.createCell(12);
			HSSFCell cell13 = row.createCell(13);
			HSSFCell cell14 = row.createCell(14);
			VisitReception visitReception = list.get(i);
			cell0.setCellValue(i + 1);
			cell1.setCellValue(visitReception.getAppTime());
			cell2.setCellValue(visitReception.getAppPerson());
			cell3.setCellValue(visitReception.getAppPhone());
			cell4.setCellValue(visitReception.getReceptionTime());
			cell5.setCellValue(visitReception.getReceptionAddress());
			cell6.setCellValue(visitReception.getVisitUnit());
			cell7.setCellValue(visitReception.getVisitNumber());
			cell8.setCellValue(visitReception.getLeaderPhone());
			cell9.setCellValue(visitReception.getVisitLevel());
			cell10.setCellValue(visitReception.getAppVisitTime());
			cell11.setCellValue(visitReception.getVisitArea());
			cell12.setCellValue(visitReception.getOverTime());
			cell13.setCellValue(visitReception.getVisitingTime());
			cell14.setCellValue(visitReception.getRemarks());
		}
		
		// 将数据写出
		book.write(os);
		os.close();
	}
}

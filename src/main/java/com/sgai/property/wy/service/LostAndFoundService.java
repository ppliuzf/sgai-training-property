package com.sgai.property.wy.service;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.wy.dao.LostAndFoundDao;
import com.sgai.property.wy.entity.LostAndFound;
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
    * @ClassName: LostAndFound  
    * (失物招领服务)
    * @author LiuXiaobing  
    * @date 2018年1月29日  
    * @Company 首自信--智慧城市创新中心
*/
@Service
public class LostAndFoundService extends CrudServiceExt<LostAndFoundDao,LostAndFound> {
	
	//查询数据并分页显示
	public Page<LostAndFound> findPage(Page<LostAndFound> page, LostAndFound lostAndFound) {
		return super.findPage(page, lostAndFound);
	}
	
	//批量删除数据
	@Transactional(readOnly = false)
	public void batchDeleteLostAndFound(List<String> idList) {
		dao.batchDelete(idList);
	}
	
	//导出数据到Excel
	public void export(LostAndFound lostAndFound,HttpServletResponse response) throws IOException {
		OutputStream os = response.getOutputStream();// 取得输出流 
		response.reset();//清空输出流
		response.setHeader("Content-disposition", "attacment;filename="+new String("失物招领信息.xls".getBytes("UTF-8"),"ISO-8859-1"));//通知浏览器要下载
		response.setContentType("application/msexcel");// 定义输出类型 
		if(lostAndFound.getDrawTime()!=null){
            Date time = lostAndFound.getDrawTime();
            Calendar c = Calendar.getInstance();
            c.setTime(time);
            c.set(Calendar.DAY_OF_YEAR,c.get(Calendar.DAY_OF_YEAR)+1);
            lostAndFound.setDrawTime(c.getTime());
        }
		//数据类型转换
		List<LostAndFound> list = super.findList(lostAndFound);
		exportLostAndFound(list, os);
	}
	
	private void exportLostAndFound(List<LostAndFound> list, OutputStream os) throws IOException {
		
		// 创建工作簿
		HSSFWorkbook book = new HSSFWorkbook();
		// 创建Sheet对象
		HSSFSheet sheet = book.createSheet("失物招领信息");
		HSSFCellStyle style = book.createCellStyle();
		ExcelStyleUtill.CellStyle(style, book);
		HSSFRow row0 = sheet.createRow((short) 0);
		HSSFCell cell00 = row0.createCell(0);
		row0.setHeight((short) 1200);
		cell00.setCellStyle(style);
		// 增加单元格的高度
		row0.setHeightInPoints((short) 50);
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
		
		// 向单元格中设置值
		cell00.setCellValue("序号");
		cell01.setCellValue("拾取时间");
		cell02.setCellValue("拾取人");
		cell03.setCellValue("物品");
		cell04.setCellValue("领取时间");
		cell05.setCellValue("交接人");
		cell06.setCellValue("代领人");
		cell07.setCellValue("电话");
		cell08.setCellValue("失主姓名");
		cell09.setCellValue("备注");

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
			LostAndFound lostAndFound = list.get(i);
			cell0.setCellValue(i + 1);
			cell1.setCellValue(lostAndFound.getPickupTime());
			cell2.setCellValue(lostAndFound.getPickupPerson());
			cell3.setCellValue(lostAndFound.getArticels());
			cell4.setCellValue(lostAndFound.getDrawTime());
			cell5.setCellValue(lostAndFound.getHandOver());
			cell6.setCellValue(lostAndFound.getLeadPerson());
			cell7.setCellValue(lostAndFound.getPhone());
			cell8.setCellValue(lostAndFound.getLoserName());
			cell9.setCellValue(lostAndFound.getRemarks());
		}
		
		// 将数据写出
		book.write(os);
		os.close();
	}
}

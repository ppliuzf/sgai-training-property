package com.sgai.property.wy.service;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.wy.dao.VisitorDao;
import com.sgai.property.wy.entity.Visitor;
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
    * @ClassName: VisitorService  
    * (访客记录管理服务)
    * @author Liuxiaobing  
    * @date 2018年1月20日  
    * @Company 首自信--智慧城市创新中心
*/
@Service
public class VisitorService extends CrudServiceExt<VisitorDao,Visitor> {
	
	/*@Autowired
	private VisitorDao visitorDao;*/
	
	//查询数据并分页显示
	public Page<Visitor> findPage(Page<Visitor> page, Visitor visitor) {
		return super.findPage(page, visitor);
	}
	
	//批量删除数据
	@Transactional(readOnly = false)
	public void batchDeleteVisitor(List<String> idList) {
		dao.batchDelete(idList);
	}
	
	//导出数据到Excel
	public void export(Visitor visitor,HttpServletResponse response) throws IOException {
		OutputStream os = response.getOutputStream();// 取得输出流 
		response.reset();//清空输出流
		response.setHeader("Content-disposition", "attacment;filename="+new String("访客信息.xls".getBytes("UTF-8"),"ISO-8859-1"));//通知浏览器要下载
		response.setContentType("application/msexcel");// 定义输出类型 
		if(visitor.getEndTime()!=null){
            Date time = visitor.getEndTime();
            Calendar c = Calendar.getInstance();
            c.setTime(time);
            c.set(Calendar.DAY_OF_YEAR,c.get(Calendar.DAY_OF_YEAR)+1);
            visitor.setEndTime(c.getTime());
        }
		//数据类型转换
		List<Visitor> list = super.findList(visitor);
		exportVisitor(list, os);
	}
	
	private void exportVisitor(List<Visitor> list, OutputStream os) throws IOException {
		
		// 创建工作簿
		HSSFWorkbook book = new HSSFWorkbook();
		// 创建Sheet对象
		HSSFSheet sheet = book.createSheet("访客信息");
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
		HSSFCell cell010 = row0.createCell(10);
		cell010.setCellStyle(style);
		HSSFCell cell011 = row0.createCell(11);
		cell011.setCellStyle(style);
		HSSFCell cell012 = row0.createCell(12);
		cell012.setCellStyle(style);
		
		// 向单元格中设置值
		cell00.setCellValue("序号");
		cell01.setCellValue("姓名");
		cell02.setCellValue("证件号");
		cell03.setCellValue("分类");
		cell04.setCellValue("单位");
		cell05.setCellValue("电话");
		cell06.setCellValue("人数");
		cell07.setCellValue("是否预约");
		cell08.setCellValue("访问部门");
		cell09.setCellValue("被访问人");
		cell010.setCellValue("进入时间");
		cell011.setCellValue("离开时间");
		cell012.setCellValue("备注");

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
			Visitor visitor = list.get(i);
			cell0.setCellValue(i + 1);
			/*cell1.setCellValue(visitor.getVisitorDate()==null?"":new SimpleDateFormat("yyyy-MM-dd").format(visitor.getCreatedDt()));*/
			cell1.setCellValue(visitor.getVisitorName());
			cell2.setCellValue(visitor.getIdCard());
			cell3.setCellValue(visitor.getVisitorType());
			cell4.setCellValue(visitor.getComName());
			cell5.setCellValue(visitor.getPhone());
			cell6.setCellValue(visitor.getPersonNum());
			cell7.setCellValue(visitor.getVisitorMeet());
			cell8.setCellValue(visitor.getDeptName());
			cell9.setCellValue(visitor.getToerPersons());
			cell10.setCellValue(visitor.getBeginTime());
			cell11.setCellValue(visitor.getEndTime());
			cell12.setCellValue(visitor.getRemarks());
		}
		
		// 将数据写出
		book.write(os);
		os.close();
	}
}

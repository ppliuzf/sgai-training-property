package com.sgai.property.wy.service;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.wy.dao.MagazineInfoDao;
import com.sgai.property.wy.entity.MagazineInfo;
import com.sgai.property.wy.support.ExcelStyleUtill;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


/**
    * @ClassName: magazineInfoService  
    * (访客记录管理服务)
    * @author Liuxiaobing  
    * @date 2018年1月20日  
    * @Company 首自信--智慧城市创新中心
*/
@Service
public class MagazineInfoService extends CrudServiceExt<MagazineInfoDao,MagazineInfo> {
	
	/*@Autowired
	private MagazineInfoDao magazineInfoDao;*/
	
	//查询数据并分页显示
	public Page<MagazineInfo> findPage(Page<MagazineInfo> page, MagazineInfo magazineInfo) {
		return super.findPage(page, magazineInfo);
	}
	
	//批量删除数据
	@Transactional(readOnly = false)
	public void batchDeleteMagazineInfo(List<String> idList) {
		dao.batchDelete(idList);
	}
	
	//导出数据到Excel
	public void export(MagazineInfo magazineInfo,HttpServletResponse response) throws IOException {
		OutputStream os = response.getOutputStream();// 取得输出流 
		response.reset();//清空输出流
		response.setHeader("Content-disposition", "attacment;filename="+new String("报刊杂志信息.xls".getBytes("UTF-8"),"ISO-8859-1"));//通知浏览器要下载
		response.setContentType("application/msexcel");// 定义输出类型 
		//数据类型转换
		List<MagazineInfo> list = super.findList(magazineInfo);
		exportMagazineInfo(list, os);
	}
	
	private void exportMagazineInfo(List<MagazineInfo> list, OutputStream os) throws IOException {
		
		// 创建工作簿
		HSSFWorkbook book = new HSSFWorkbook();
		// 创建Sheet对象
		HSSFSheet sheet = book.createSheet("报刊杂志信息");
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
		
		// 向单元格中设置值
		cell00.setCellValue("序号");
		cell01.setCellValue("报刊名字");
		cell02.setCellValue("编号");
		cell03.setCellValue("添加时间");
		cell04.setCellValue("维护人");
		cell05.setCellValue("备注");

		// 向excel中插入数据
		for (int i = 0; i < list.size(); i++) {
			HSSFRow row = sheet.createRow(i + 1);
			HSSFCell cell0 = row.createCell(0);
			HSSFCell cell1 = row.createCell(1);
			HSSFCell cell2 = row.createCell(2);
			HSSFCell cell3 = row.createCell(3);
			HSSFCell cell4 = row.createCell(4);
			HSSFCell cell5 = row.createCell(5);
			MagazineInfo magazineInfo = list.get(i);
			cell0.setCellValue(i + 1);
			cell1.setCellValue(magazineInfo.getMagazineName());
			cell2.setCellValue(magazineInfo.getEncoder());
			cell3.setCellValue(magazineInfo.getCreatedDate());
			cell4.setCellValue(magazineInfo.getPersonName());
			cell5.setCellValue(magazineInfo.getRemarks());
		}
		// 将数据写出
		book.write(os);
		os.close();
	}
}

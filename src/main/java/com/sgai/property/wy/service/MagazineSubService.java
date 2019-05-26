
package com.sgai.property.wy.service;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.wy.dao.MagazineSubDao;
import com.sgai.property.wy.entity.MagazineInfo;
import com.sgai.property.wy.entity.MagazineSub;
import com.sgai.property.wy.support.ExcelStyleUtill;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.summingLong;

/**
 * @ClassName: MagazineSubService
 * ()
 * @author cui
 * @date 2018年2月1日
 * @Company 首自信--智慧城市创新中心
 */
@Service
public class MagazineSubService
		extends
        CrudServiceExt<MagazineSubDao, MagazineSub> {

	public Page<MagazineSub> findPage(Page<MagazineSub> page,
                                      MagazineSub magazine) {
		return super.findPage(page, magazine);
	}

	// 导出数据到Excel
	public void export(MagazineSub magazine, HttpServletResponse response)
			throws IOException {
		OutputStream os = response.getOutputStream();// 取得输出流
		response.reset();// 清空输出流
		response.setHeader("Content-disposition", "attacment;filename="
				+ new String("报刊杂志信息.xls".getBytes("UTF-8"), "ISO-8859-1"));// 通知浏览器要下载
		response.setContentType("application/msexcel");// 定义输出类型
		// 数据类型转换
		List<MagazineSub> list = super.findList(magazine);
		exportMagazineSub(list, os);
	}

	private void exportMagazineSub(List<MagazineSub> list, OutputStream os)
			throws IOException {

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

		// 向单元格中设置值
		cell00.setCellValue("序号");
		cell01.setCellValue("日期");
		cell02.setCellValue("报刊名称");
		cell03.setCellValue("份数");
		cell04.setCellValue("区域");
		cell05.setCellValue("签收人部门");
		cell06.setCellValue("经手人");
		cell07.setCellValue("备注");

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
			MagazineSub magazine = list.get(i);
			cell0.setCellValue(i + 1);
			if (magazine.getSignDate() != null) {
				cell1.setCellValue(new SimpleDateFormat("yyyy-MM-dd")
						.format(magazine.getSignDate()));
			}

			cell2.setCellValue(magazine.getMagazine());
			cell3.setCellValue(magazine.getPhr());
			cell4.setCellValue(magazine.getArea());
			cell5.setCellValue(magazine.getSignDept());
			cell6.setCellValue(magazine.getSignName());
			cell7.setCellValue(magazine.getRemarks());
		}
		// 将数据写出
		book.write(os);
		os.close();
	}

	// 批量删除,逻辑删除
	@Transactional(readOnly = false)
	public void batchDeleteMS(List<String> idList) {
		dao.batchDelete(idList);
	}

	/**
	 * @Title: findAllM (查询所有报刊杂志) @param @return 参数 @return
	 *         MagazineSub 返回类型 @throws
	 */

	public List<MagazineInfo> findAllM() {
		return dao.findAllM();
	}

	/**
	 * @Title: queryMCount (统计各个报刊订阅数)
	 * @param @param
	 *         magazine @param @return 参数 @return List<MagazineInfo>
	 *         返回类型 @throws
	 */
	public Map<String,Object> findPageCount(Page<MagazineSub> page,
			MagazineSub magazine) {
		
		Map<String,Object> map=new HashMap<>();
		magazine.setPage(page);
		List<MagazineSub> list = dao.queryMCount(magazine);
		Long sum=list.stream().collect(summingLong(MagazineSub::getPhr));
		page.setList(list);
		map.put("sum", sum);
		map.put("page", page);
		return map;
	}
	// 导出数据到Excel
	public void exportMCount(MagazineSub magazine, HttpServletResponse response)
			throws IOException {
		OutputStream os = response.getOutputStream();// 取得输出流
		response.reset();// 清空输出流
		response.setHeader("Content-disposition", "attacment;filename="
				+ new String("报刊杂志统计.xls".getBytes("UTF-8"), "ISO-8859-1"));// 通知浏览器要下载
		response.setContentType("application/msexcel");// 定义输出类型
		// 数据类型转换
		List<MagazineSub> list = dao.queryMCount(magazine);
		exportMagazineCount(list, os);
	}
	private void exportMagazineCount(List<MagazineSub> list, OutputStream os)
			throws IOException {
		// 创建工作簿
		HSSFWorkbook book = new HSSFWorkbook();
		// 创建Sheet对象
		HSSFSheet sheet = book.createSheet("报刊杂志信息统计");
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

		// 向单元格中设置值
		cell00.setCellValue("序号");
		cell01.setCellValue("报刊名称");
		cell02.setCellValue("份数");

		// 向excel中插入数据
		for (int i = 0; i < list.size(); i++) {
			HSSFRow row = sheet.createRow(i + 1);
			HSSFCell cell0 = row.createCell(0);
			HSSFCell cell1 = row.createCell(1);
			HSSFCell cell2 = row.createCell(2);
			MagazineSub magazine = list.get(i);
			cell0.setCellValue(i + 1);
			cell1.setCellValue(magazine.getMagazine());
			cell2.setCellValue(magazine.getPhr());
		}
		// 将数据写出
		book.write(os);
		os.close();
	}

}

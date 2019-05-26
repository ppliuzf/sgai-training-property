
/**    
* @Title: CallService.java  
* @Package com.sgai.property.wy.service
* (用一句话描述该文件做什么)
* @author cui  
* @date 2018年1月29日  
* @Company 首自信--智慧城市创新中心
* @version V1.0    
*/

package com.sgai.property.wy.service;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.wy.dao.CallDao;
import com.sgai.property.wy.entity.CallCommon;
import com.sgai.property.wy.entity.CallInformation;
import com.sgai.property.wy.entity.Member;
import com.sgai.property.wy.support.ExcelStyleUtill;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CallService
 * @Description:
 * @author cui
 * @date 2018年1月29日
 * @Company 首自信--智慧城市创新中心
 */

@Service
public class CallService extends CrudServiceExt<CallDao, CallInformation> {

	public Page<CallInformation> findPage(Page<CallInformation> page,
                                          CallInformation call) {
		return super.findPage(page, call);
	}

	// 导出数据到Excel
	public void export(CallInformation call, HttpServletResponse response)
			throws IOException {
		OutputStream os = response.getOutputStream();// 取得输出流
		response.reset();// 清空输出流
		response.setHeader("Content-disposition", "attacment;filename="
				+ new String("来电信息.xls".getBytes("UTF-8"), "ISO-8859-1"));// 通知浏览器要下载
		response.setContentType("application/msexcel");// 定义输出类型
		// 数据类型转换
		List<CallInformation> list = super.findList(call);
		exportcall(list, os);
	}

	private void exportcall(List<CallInformation> list, OutputStream os)
			throws IOException {

		// 创建工作簿
		HSSFWorkbook book = new HSSFWorkbook();
		// 创建Sheet对象
		HSSFSheet sheet = book.createSheet("来电记录");
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
		HSSFCell cell10 = row0.createCell(10);
		cell10.setCellStyle(style);

		// 向单元格中设置值
		cell00.setCellValue("序号");
		cell01.setCellValue("录入日期");
		cell02.setCellValue("姓名");
		cell03.setCellValue("地址");
		cell04.setCellValue("部门");
		cell05.setCellValue("电话");
		cell06.setCellValue("来电描述");
		cell07.setCellValue("处理人");
		cell08.setCellValue("处理描述");
		cell09.setCellValue("处理日期");
		cell10.setCellValue("备注");

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
			HSSFCell cell11 = row.createCell(10);
			CallInformation call = list.get(i);
			cell0.setCellValue(i + 1);
			/*
			 * cell1.setCellValue(call.getcallDate()==null?"":new
			 * SimpleDateFormat("yyyy-MM-dd").format(call.getCreatedDt()));
			 */
			cell1.setCellValue(new
					  SimpleDateFormat("yyyy-MM-dd").format(call.getCallDate()));
			cell2.setCellValue(call.getCaller());
			cell3.setCellValue(call.getCallAddress());
			cell4.setCellValue(call.getDepartment());
			cell5.setCellValue(call.getCallPhone());
			cell6.setCellValue(call.getCallDescription());
			cell7.setCellValue(call.getOperatorId());
			cell8.setCellValue(call.getDealDescription());
			if(call.getDealDate()!=null){
				cell9.setCellValue(new
						  SimpleDateFormat("yyyy-MM-dd").format(call.getDealDate()));
			}
			cell11.setCellValue(call.getRemarks());
		}

		// 将数据写出
		book.write(os);
		os.close();
	}

	/**
	 * @Title: batchDelete (这里用一句话描述这个方法的作用) @param @param
	 * idList 参数 @return void 返回类型 @throws
	 */
	// 批量删除数据
	@Transactional(readOnly = false)
	public void batchDeleteCall(List<String> idList) {
		dao.batchDelete(idList);
	}

	/**
	 * @Title: updateStatus 
	 * (更新指定状态)
	 * call 参数 @return void 返回类型 @throws
	 */
	@Transactional(readOnly = false)
	public void updateAppointStatus(CallInformation call) {
		call.setAppointDate(new Date());
		call.setAppointStatus("1"); //已指定
		call.setDealStatus("0");
		dao.update(call);
	}
	/**
	 * @Title: updateStatus 
	 * (更新处理状态)
	 * call 参数 @return void 返回类型 @throws
	 */
	@Transactional(readOnly = false)
	public void updateDealStatus(CallInformation call) {
		call.setDealDate(new Date());
		call.setDealStatus("1");
		dao.update(call);
	}

	  
	    /**  
	    * @Title: queryName  
	    * (查询会员名称)
	    * @param @return    参数  
	    * @return List<Member>    返回类型  
	    * @throws  
	    */  
	    
	public List<Member> queryName(String caller) {
		return dao.findName(caller);
	}
	 /**
	  * 
	     * @Title: findPage  
	     * (封装特定分页查询)
	     * @param @param page
	     * @param @param call
	     * @param @return    参数  
	     * @return Page<CallCommon>    返回类型  
	     * @throws
	  */
	public Page<CallCommon> findPage(Page<CallCommon> page,
                                     CallCommon call) {
		call.setPage(page);
		List<CallCommon> list = dao.findAppointList(call);
		page.setList(list);
		return page;
	}
	
	 /**
	  * 
	     * @Title: queryAddress  
	     * (查询区域地址)
	     * @param @param id
	     * @param @return    参数  
	     * @return Map<String,Object>    返回类型  
	     * @throws
	  */
	public Map<String,Object> queryAddress(String  id){
		Map<String,Object> map=new HashMap<>();
		List<Member> mList=dao.findAddressList(id);
		String address=dao.findAddress(id);
		map.put("mlist", mList);
		map.put("address", address);
		return map;
	}

	  
	    /**  
	    * @Title: queryRole  
	    * (判断当前用户角色)
	    * @param @param userId
	    * @param @return    参数  
	    * @return String    返回类型  
	    * @throws  
	    */  
	    
	public List<String> queryRole(String userId) {
		return dao.queryRole(userId);
	}
}

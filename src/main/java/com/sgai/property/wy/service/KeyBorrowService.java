  
    /**    
    * @Title: KeyBorrowService.java  
    * @Package com.sgai.modules.key.service  
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年1月18日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.service;

    import com.sgai.common.persistence.Page;
    import com.sgai.common.service.CrudServiceExt;
    import com.sgai.modules.login.annotation.DataAuthority;
    import com.sgai.property.wy.dao.KeyBorrowDao;
    import com.sgai.property.wy.entity.KeyBorrow;
    import com.sgai.property.wy.support.ExcelStyleUtill;
    import org.apache.poi.hssf.usermodel.*;
    import org.springframework.stereotype.Service;

    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    import java.io.OutputStream;
    import java.text.SimpleDateFormat;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;


    /**  
 * @ClassName: KeyBorrowService  
 * (这里用一句话描述这个类的作用)
 * @author XJ9001  
 * @date 2018年1月18日  
 * @Company 首自信--智慧城市创新中心  
 */
@Service
public class KeyBorrowService extends CrudServiceExt<KeyBorrowDao, KeyBorrow> {
	
	@DataAuthority(tableAlias = "a")
	public Page<KeyBorrow> findPage(Page<KeyBorrow> page, KeyBorrow keyBorrow) {
		return super.findPage(page, keyBorrow);
	}

	  
	    /**
	     * @throws IOException   
	    * @Title: export  
	    * (这里用一句话描述这个方法的作用)
	    * @param @param keyBorrow
	    * @param @param response    参数  
	    * @return void    返回类型  
	    * @throws  
	    */  
	    
	public void export(KeyBorrow keyBorrow, HttpServletResponse response) throws IOException {
		OutputStream os = response.getOutputStream();// 取得输出流 
		response.reset();//清空输出流
		response.setHeader("Content-disposition", "attacment;filename="+new String("门卡借用信息.xls".getBytes("UTF-8"),"ISO-8859-1"));//通知浏览器要下载
		response.setContentType("application/msexcel");// 定义输出类型 
		//数据类型转换
		List<KeyBorrow> list = super.findList(keyBorrow);
		exportKeyBorrow(list, os);
	}


		  
		    /**
		     * @throws IOException   
		    * @Title: exportKeyBorrow  
		    * (这里用一句话描述这个方法的作用)
		    * @param @param list
		    * @param @param os    参数  
		    * @return void    返回类型  
		    * @throws  
		    */  
		    
		private void exportKeyBorrow(List<KeyBorrow> list, OutputStream os) throws IOException {
			// 创建工作簿
			HSSFWorkbook book = new HSSFWorkbook();
			// 创建Sheet对象
			HSSFSheet sheet = book.createSheet("物品借出信息");
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
			cell01.setCellValue("日期");
			cell02.setCellValue("部位");			
			cell03.setCellValue("借用部门");
			cell04.setCellValue("借用人");
			cell05.setCellValue("数量");
			cell06.setCellValue("借出时间");
			cell07.setCellValue("收回时间");
			cell08.setCellValue("值班人");
			cell09.setCellValue("备注");
			cell010.setCellValue("联系电话");
			cell011.setCellValue("经办人");
			cell012.setCellValue("用途");

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
				KeyBorrow keyBorrow = list.get(i);
				cell0.setCellValue(i + 1);
				cell1.setCellValue(keyBorrow.getBorrowDate()==null?"":new SimpleDateFormat("yyyy-MM-dd").format(keyBorrow.getBorrowDate()));
				cell2.setCellValue(keyBorrow.getPosition());
				cell3.setCellValue(keyBorrow.getDeptName());
				cell4.setCellValue(keyBorrow.getBorrower());
				cell5.setCellValue(keyBorrow.getAmount());
				cell6.setCellValue(keyBorrow.getBorrowTime()==null?"":new SimpleDateFormat("yyyy-MM-dd").format(keyBorrow.getBorrowTime()));
				cell7.setCellValue(keyBorrow.getRegainTime()==null?"":new SimpleDateFormat("yyyy-MM-dd").format(keyBorrow.getRegainTime()));
				cell8.setCellValue(keyBorrow.getWatch());
				cell9.setCellValue(keyBorrow.getRemark());
				cell10.setCellValue(keyBorrow.getPhone());
				cell11.setCellValue(keyBorrow.getManager());
				cell12.setCellValue(keyBorrow.getPurpose());
			}
			// 将数据写出
			book.write(os);
			os.close();
			//book.close();
		}


		/**
		 * 
		 * deleteKey:(删除).
		 * @param ids
		 * @return :Map<String,Object> 
		 * @since JDK 1.8
		 * @author zxj
		 */
		public Map<String, Object> deleteKey(String ids){
			Map<String, Object> map = new HashMap<String, Object>(); 
			String idss[]=ids.split(",");
			List<KeyBorrow> list = new ArrayList<KeyBorrow>();
			List<String> newList = new ArrayList<String>();
			for(String id:idss){
				if(id!=null&&!id.equals("")){
					newList.add(id);
					KeyBorrow keyBorrow=super.get(id);
					list.add(keyBorrow);
				}
			}
			List<KeyBorrow> finalList = batchDelete(list);
			if (finalList.size() > 0) {
				map.put("msg", "删除成功!");
			}else {
				map.put("msg", "删除失败！");
			}
			return map;
		}	
}

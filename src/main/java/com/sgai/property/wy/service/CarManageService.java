  
    /**    
    * @Title: CarManageService.java  
    * @Package com.sgai.property.wy.service
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年2月1日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.service;

    import com.sgai.common.service.CrudServiceExt;
    import com.sgai.common.utils.DateUtils;
    import com.sgai.property.wy.dao.CarManageDao;
    import com.sgai.property.wy.entity.CarManage;
    import com.sgai.property.wy.entity.Exceerror;
    import org.apache.poi.hssf.usermodel.HSSFWorkbook;
    import org.apache.poi.ss.usermodel.Cell;
    import org.apache.poi.ss.usermodel.Row;
    import org.apache.poi.ss.usermodel.Sheet;
    import org.apache.poi.ss.usermodel.Workbook;
    import org.apache.poi.xssf.usermodel.XSSFWorkbook;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Propagation;
    import org.springframework.transaction.annotation.Transactional;
    import org.springframework.web.multipart.MultipartFile;

    import java.util.*;


    /**  
 * @ClassName: CarManageService  
 * (这里用一句话描述这个类的作用)
 * @author XJ9001  
 * @date 2018年2月1日  
 * @Company 首自信--智慧城市创新中心  
 */
@Service
public class CarManageService extends CrudServiceExt<CarManageDao, CarManage> {

		  
		    /**  
		    * @Title: deleteCars  
		    * (这里用一句话描述这个方法的作用)
		    * @param @param ids
		    * @param @return    参数  
		    * @return Map<String,Object>    返回类型  
		    * @throws  
		    */  
		    
		public Map<String, Object> deleteCars(String ids) {
				Map<String, Object> map = new HashMap<String, Object>(); 
				String idss[]=ids.split(",");
				List<CarManage> list = new ArrayList<CarManage>();
				List<String> newList = new ArrayList<String>();
				for(String id:idss){
					if(id!=null&&!id.equals("")){
						newList.add(id);
						CarManage c = new CarManage();
						c.setId(id);
						CarManage carManage = super.get(c);
						list.add(carManage);
					}
				}
				List<CarManage> finalList = batchDelete(list);
				if (finalList.size() > 0) {
					map.put("msg", "删除成功!");
				}else {
					map.put("msg", "删除失败！");
				}
				return map;
		}
		/**
		 * 批量导入表格
		 * @param file
		 * @return
		 * @throws Exception
		 */
		@Transactional(propagation=Propagation.REQUIRED)
		public List<Exceerror> uploadExcelFile(MultipartFile file, String userName) throws Exception  {

			//创建一个list集合，用来封装车辆对象
			List<CarManage> list = new ArrayList<CarManage>();
			//创建错误信息集合
			List<Exceerror> lists = new ArrayList<Exceerror>();
			String fileName = file.getOriginalFilename();
			String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

			if (!extension.equals("xls") && !extension.equals("xlsx")) {
				Exceerror exceerror = new Exceerror();
				exceerror.setColumn("格式不正确");
				lists.add(exceerror);
				return lists;	
			}

			Workbook workbook = null;
			if(extension.equals("xlsx")){
				workbook = new XSSFWorkbook(file.getInputStream());
			}else if(extension.equals("xls")){
				workbook = new HSSFWorkbook(file.getInputStream());
			}
			Sheet sheet = workbook.getSheetAt(0);
			//获得到第一行的行数
			int begin = sheet.getFirstRowNum();  
			//获得到最后一行的行数
			int end = sheet.getLastRowNum();
			if(end==0){
				Exceerror exceerror = new Exceerror();
				exceerror.setColumn("文件没有数据");
				lists.add(exceerror);
				return lists;	
			}
			int lie = sheet.getRow(0).getPhysicalNumberOfCells();
			
			//调出空行
			for (int i = 1; i <= end; i++) { 
				if (null == sheet.getRow(i)) {  
					continue;  
				}  
				Row row = sheet.getRow(i);
				
				String memberName = getStringCellValue(row.getCell(0)).trim();//客户名称
				String memberIdCard = getStringCellValue(row.getCell(1)).trim();//客户证件号
				String carNumber = getStringCellValue(row.getCell(2)).trim();//车牌号
				String carColor = getStringCellValue(row.getCell(3)).trim();//车辆颜色
				String carModel = getStringCellValue(row.getCell(4)).trim();//车辆型号 
				String loadNumber = getStringCellValue(row.getCell(5)).trim();//核载人数
				String remark = getStringCellValue(row.getCell(6)).trim();//备注
				
				//和下一行的某一列比较判断重复
	 			for(int j=i+1;j<=end;j++){
					if(null==sheet.getRow(j)){
						continue;
					}					
				
				}
				//校验车牌号
				if( "".equals(carNumber)){
					Exceerror exceerror = new Exceerror();
					exceerror.setRow(i+1);
					exceerror.setColumn("C列");
					exceerror.setError("车牌号不能为空");
					lists.add(exceerror);
				}else{
					CarManage cm=new CarManage();
					cm.setCarNumber(carNumber);
					CarManage c = get(cm);
					if(c != null){
						Exceerror exceerror = new Exceerror();
						exceerror.setRow(i+1);
						exceerror.setColumn("C列");
						exceerror.setError("该车牌号已存在");
						lists.add(exceerror);
					}else{
					//校验导入表格中的登录手机号是否重复
					for (int j = 0; j < list.size(); j++) {
						String p1 = list.get(j).getCarNumber();
						if( p1.equals(carNumber)){
							Exceerror exceerror = new Exceerror();
							exceerror.setRow(i+1);
							exceerror.setColumn("C列");
							exceerror.setError("车牌号与第"+(j+2)+"行车牌号重复");
							lists.add(exceerror);	
						}
					}
					}
				}
				//校验颜色
				if( "".equals(carColor)){
					Exceerror exceerror = new Exceerror();
					exceerror.setRow(i+1);
					exceerror.setColumn("D列");
					exceerror.setError("颜色不能为空");
					lists.add(exceerror);
				}
				//校验型号
				if( "".equals(carModel)){
					Exceerror exceerror = new Exceerror();
					exceerror.setRow(i+1);
					exceerror.setColumn("E列");
					exceerror.setError("型号不能为空");
					lists.add(exceerror);
				}
				//校验荷载人数
				if( "".equals(loadNumber)){
					Exceerror exceerror = new Exceerror();
					exceerror.setRow(i+1);
					exceerror.setColumn("F列");
					exceerror.setError("荷载人数不能为空");
					lists.add(exceerror);
				}
				CarManage car=new CarManage();
				car.setMemberName(memberName);
				car.setMemberIdCard(memberIdCard);
				car.setCarNumber(carNumber);
				car.setCarColor(carColor);
				car.setCarModel(carModel);
				car.setLoadNumber(loadNumber);
				car.setPreservePersonName(userName);
				car.setRemark(remark);	
				list.add(car);
			}  
			if(lists.size()>0){
				return lists;
			}
			//循环保存到数据库
			for (CarManage carManage : list) {
				save(carManage);
			}
			
			return  null;//上传成功
		}

		/**
		 * 批量导入表格-获取单元格值
		 * @param cell
		 * @return
		 */
		private String getStringCellValue(Cell cell) {
			if(cell == null)
				return "";
			String strCell = "";
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				strCell = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				strCell = String.valueOf((int)(cell.getNumericCellValue()));
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				strCell = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_BLANK:
				strCell = "";
				break;
			default:
				strCell = "";
				break;
			}
			return strCell;
		}
		private String creatUUID() {  
	        return UUID.randomUUID().toString().replace("-", "");  
	    }
		public static void main(String[]a){
			String enrollTime="2018-05-10";
			Date startTime = DateUtils.parseDate(enrollTime);
			System.out.print(startTime);;
		}

}

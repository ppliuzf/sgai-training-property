  
    /**    
    * @Title: MemberService.java  
    * @Package com.sgai.property.wy.service
    * (用一句话描述该文件做什么)
    * @author XJ9001  
    * @date 2018年1月26日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.wy.service;

    import com.sgai.common.service.CrudServiceExt;
    import com.sgai.common.utils.DateUtils;
    import com.sgai.property.wy.dao.MemberDao;
    import com.sgai.property.wy.entity.Exceerror;
    import com.sgai.property.wy.entity.Member;
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
    import java.util.regex.Pattern;


    /**  
 * @ClassName: MemberService  
 * (这里用一句话描述这个类的作用)
 * @author XJ9001  
 * @date 2018年1月26日  
 * @Company 首自信--智慧城市创新中心  
 */
@Service
public class MemberService extends CrudServiceExt<MemberDao, Member> {

	/**
	 * 
	 * deleteMember:(删除).
	 * @param ids
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author zxj
	 */
	@Transactional(readOnly = false)
	public Map<String, Object> deleteMember(String ids){
		Map<String, Object> map = new HashMap<String, Object>(); 
		String idss[]=ids.split(",");
		List<Member> list = new ArrayList<Member>();
		List<String> newList = new ArrayList<String>();
		for(String id:idss){
			if(id!=null&&!id.equals("")){
				newList.add(id);
				Member m = new Member();
				m.setId(id);
				Member member=super.get(m);
				list.add(member);
			}
		}
		List<Member> finalList = batchDelete(list);
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
	public List<Exceerror> uploadExcelFile(MultipartFile file) throws Exception  {

		//创建一个list集合，用来封装车辆对象
		List<Member> list = new ArrayList<Member>();
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
			
			String enrollTime = getStringCellValue(row.getCell(0)).trim();//注册时间
			String phoneNumber = getStringCellValue(row.getCell(1)).trim();//登录手机号
			String chineseName = getStringCellValue(row.getCell(2)).trim();//中文姓名
			String contactWay = getStringCellValue(row.getCell(3)).trim();//联系方式
			String status = getStringCellValue(row.getCell(4)).trim();//状态
			String memberRankName = getStringCellValue(row.getCell(5)).trim();//级别
			String deptName = getStringCellValue(row.getCell(6)).trim();//部门
			String email = getStringCellValue(row.getCell(7)).trim();//电子邮件
			String repairAddress = getStringCellValue(row.getCell(8)).trim();//地址
			String dateOfBirth = getStringCellValue(row.getCell(9)).trim();//出生年月
			String finishSchool = getStringCellValue(row.getCell(10)).trim();//毕业院校
			String position = getStringCellValue(row.getCell(11)).trim();//职位
			String remark = getStringCellValue(row.getCell(12)).trim();//备注
			
			//和下一行的某一列比较判断重复
 			for(int j=i+1;j<=end;j++){
				if(null==sheet.getRow(j)){
					continue;
				}					
			
			}
 			//校验注册时间
			if( !"".equals(enrollTime)){
				String pa = "^2\\d{3}-("
			      		+ "(0[13578]|1[02])-(0[1-9]|[1-2][0-9]|3[0-1])"
			      		+ "|(0[469]|11)-(0[1-9]|[1-2][0-9]|30)"
			      		+ "|(02)-(0[1-9]|[1-2][0-9]))"
			      		+ "$";
			   boolean isMatch1 = Pattern.matches(pa, enrollTime);
				if(!isMatch1){
					Exceerror exceerror = new Exceerror();
					exceerror.setRow(i+1);
					exceerror.setColumn("A列");
					exceerror.setError("注册时间格式不正确,应该为yyyy-MM-dd");
					lists.add(exceerror);
				}
			}
			//校验登录手机号
			Pattern  personPhoneReg = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
			if( "".equals(phoneNumber)){
				Exceerror exceerror = new Exceerror();
				exceerror.setRow(i+1);
				exceerror.setColumn("B列");
				exceerror.setError("登录手机号不能为空");
				lists.add(exceerror);
			}else if(!personPhoneReg.matcher(phoneNumber).matches()){
				Exceerror exceerror = new Exceerror();
				exceerror.setRow(i+1);
				exceerror.setColumn("B列");
				exceerror.setError("登录手机号格式错误");
				lists.add(exceerror);				
			}else{
				Member m = new Member();
				m.setPhoneNumber(phoneNumber);
				Member member = get(m);
				if(member != null){
					Exceerror exceerror = new Exceerror();
					exceerror.setRow(i+1);
					exceerror.setColumn("B列");
					exceerror.setError("该登录手机号已存在");
					lists.add(exceerror);
				}else{
				//校验导入表格中的登录手机号是否重复
				for (int j = 0; j < list.size(); j++) {
					String p1 = list.get(j).getPhoneNumber();
					if( p1.equals(phoneNumber)){
						Exceerror exceerror = new Exceerror();
						exceerror.setRow(i+1);
						exceerror.setColumn("B列");
						exceerror.setError("登录手机号与第"+(j+2)+"行登录手机号重复");
						lists.add(exceerror);	
					}
				}
				}
			}
			//校验中文姓名
			if( "".equals(chineseName)){
				Exceerror exceerror = new Exceerror();
				exceerror.setRow(i+1);
				exceerror.setColumn("C列");
				exceerror.setError("中文姓名不能为空");
				lists.add(exceerror);
			}else{
				String pat="[\u4e00-\u9fa5]{2,30}";
				 boolean isMatch2 = Pattern.matches(pat, chineseName);
					if(!isMatch2){
						Exceerror exceerror = new Exceerror();
						exceerror.setRow(i+1);
						exceerror.setColumn("C列");
						exceerror.setError("姓名只能为2-30个汉字");
						lists.add(exceerror);
					}
			}
			//校验联系方式:
			if( "".equals(contactWay)){
				Exceerror exceerror = new Exceerror();
				exceerror.setRow(i+1);
				exceerror.setColumn("D列");
				exceerror.setError("联系方式不能为空");
				lists.add(exceerror);
			}
		    //校验状态
			if( "".equals(status)){
				Exceerror exceerror = new Exceerror();
				exceerror.setRow(i+1);
				exceerror.setColumn("E列");
				exceerror.setError("状态不能为空");
				lists.add(exceerror);
			}else if( !status.equals("正常") && !status.equals("锁定")){
				Exceerror exceerror = new Exceerror();
				exceerror.setRow(i+1);
				exceerror.setColumn("E列");
				exceerror.setError("状态应填为'正常'或'锁定'");
				lists.add(exceerror);
			}
			//校验级别
			if( "".equals(memberRankName)){
				Exceerror exceerror = new Exceerror();
				exceerror.setRow(i+1);
				exceerror.setColumn("F列");
				exceerror.setError("级别不能为空");
				lists.add(exceerror);
			}else if( !memberRankName.equals("局级") && !memberRankName.equals("委领导")&& !memberRankName.equals("部长以下")){
				Exceerror exceerror = new Exceerror();
				exceerror.setRow(i+1);
				exceerror.setColumn("F列");
				exceerror.setError("级别应填为'局级'或'委领导'或'部长以下'");
				lists.add(exceerror);
			}
			//校验部门
			if( "".equals(deptName)){
				Exceerror exceerror = new Exceerror();
				exceerror.setRow(i+1);
				exceerror.setColumn("G列");
				exceerror.setError("部门不能为空");
				lists.add(exceerror);
			}
			//校验电子邮件
			if( "".equals(email)){
				Exceerror exceerror = new Exceerror();
				exceerror.setRow(i+1);
				exceerror.setColumn("H列");
				exceerror.setError("电子邮件不能为空");
				lists.add(exceerror);
			}else {
				String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"; 
				  boolean isMatch2 = Pattern.matches(regex, email);
					if(!isMatch2){
						Exceerror exceerror = new Exceerror();
						exceerror.setRow(i+1);
						exceerror.setColumn("H列");
						exceerror.setError("电子邮件格式错误");
						lists.add(exceerror);
					}
			}
			
			Member member  = new Member();
			Date startTime = null;
			Date endTime = null;
			//转换时间格式
			if(!"".equals(enrollTime)){
				startTime = DateUtils.parseDate(enrollTime);
				member.setEnrollTime(startTime);
			}else {
				member.setEnrollTime(new Date());
			}
			if(!"".equals(dateOfBirth)){
				endTime = DateUtils.parseDate(dateOfBirth);
			}
			
			member.setPhoneNumber(phoneNumber);
			member.setChineseName(chineseName);
			member.setContactWay(contactWay);
			member.setStatus(status);
			member.setMemberRankName(memberRankName);
			member.setDeptName(deptName);
			member.setEmail(email);
			member.setRepairAddress(repairAddress);
			member.setDateOfBirth(endTime);
			member.setFinishSchool(finishSchool);
			member.setPosition(position);
			member.setRemark(remark);	
			member.setMemberCode(creatUUID());
			member.setType("1");
			list.add(member);
		
		}  
		if(lists.size()>0){
			return lists;
		}
		//循环保存到数据库
		for (Member member : list) {
			save(member);
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

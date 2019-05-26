
/**
 * @Title: RepairInformationService.java
 * @Package com.sgai.modules.repair.service
 * (用一句话描述该文件做什么)
 * @author XJ9001
 * @date 2018年1月20日
 * @Company 首自信--智慧城市创新中心
 * @version V1.0
 */

package com.sgai.property.wy.service;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.property.wy.dao.RepairInformationDao;
import com.sgai.property.wy.dto.MaintainInformationDto;
import com.sgai.property.wy.dto.RepairConst;
import com.sgai.property.wy.entity.*;
import com.sgai.property.wy.support.ExcelStyleUtill;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName: RepairInformationService
 * (这里用一句话描述这个类的作用)
 * @author XJ9001
 * @date 2018年1月20日
 * @Company 首自信--智慧城市创新中心
 */
@Service
public class RepairInformationService
		extends
        CrudServiceExt<RepairInformationDao, RepairInformation> {

	@Autowired
	private RepairInformationDao repairInformationDao;
	@Autowired
	private RepairRecordService repairRecordService;
	@Autowired
	private MemberService memberService;

	@Transactional(readOnly = false)
	public void saveRepair(RepairInformation repairInformation) {

		RepairRecord repairRecord = new RepairRecord();
		if (!("".equals(repairInformation.getId()))) {
			repairRecord.setRepairLog(RepairConst.repairUpdate.getName());
			repairRecord.setRepairStatus(RepairConst.repairUpdate.getIndex());
		} else {
			repairRecord.setRepairLog(RepairConst.repairSubmit.getName());
			repairRecord.setRepairStatus(RepairConst.repairSubmit.getIndex());
		}
		repairInformation.setRepairStatus(RepairConst.repairSubmit.getIndex());
		repairInformation.setIncidentSource("1");
		if (!"".equals(repairInformation.getRepairPeopleId())) {
			// 判断repairPeopleId != null 直接添加
			super.save(repairInformation);
		} else {
			Member m = new Member();
			m.setChineseName(repairInformation.getRepairPeopleName());
			m.setPhoneNumber(repairInformation.getRepairPhone());
			Member m2 = memberService.get(m);
			if(m2 != null){
				super.save(repairInformation);
			}else{
				// 先添加会员 再添加报修
				Member mem = new Member();
				mem.setChineseName(repairInformation.getRepairPeopleName());
				mem.setPhoneNumber(repairInformation.getRepairPhone());
				mem.setRepairAddress(repairInformation.getRepairAddress());
				mem.setRepairAddressCode(repairInformation.getRepairAddressCode());
				mem.setEnrollTime(new Date());
				mem.setMemberRankName("无");
				mem.setType("1");
				memberService.save(mem);
				repairInformation.setRepairPeopleId(mem.getId());
				super.save(repairInformation);
			}
		}

		String[] eqIds = repairInformation.getRepairEquipmentIds().split(",");
		for (int i = 0; i < eqIds.length; i++) {
			RepairEquipment repairEquipment = new RepairEquipment();
			repairEquipment.setRepairId(repairInformation.getId());
			repairEquipment.setEquipmentId(eqIds[i]);
			repairInformationDao.saveEquip(repairEquipment);
		}
		repairRecord.setRepairId(repairInformation.getId());
		repairRecord.setPresentDate(new Date());

		repairRecordService.save(repairRecord);
	}

	/**
	 * @throws ParseException
	 * @Title: upRepairStatus (这里用一句话描述这个方法的作用) @param @param
	 *         id 参数 @return void 返回类型 @throws
	 */
	@Transactional(readOnly = false)
	public void upRepairStatus(RepairInformation repairInformation,
			LoginUser loginUser) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		RepairRecord repairRecord = new RepairRecord();
		repairRecord.setPresentDate(getTodays());
		repairRecord.setRepairId(repairInformation.getId());
		if (repairInformation.getCause() != null) {
			repairRecord.setCause(repairInformation.getCause());
		}
		if (repairInformation.getAppraiseNorm() != null) {
			repairRecord.setAppraiseNorm(repairInformation.getAppraiseNorm());
		}
		try {
			if (RepairConst.repairAccept.getIndex()
					.equals(repairInformation.getRepairStatus())) {
				repairInformation.setAcceptPerson(loginUser.getUserName());
			}
			repairInformation.setUpdatedDt(new Date());
			repairInformationDao.updateRepairStatus(repairInformation);
			repairRecord
					.setRepairLog(upRepairLog(repairInformation, loginUser));
			repairRecord.setRepairStatus(repairInformation.getRepairStatus());
			repairRecordService.save(repairRecord);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
	}

	private Date getTodays() throws ParseException {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = sdf.parse(sdf.format(d));
		return today;
	}

	/**
	 * @return @Title: upRepairLog @Description:
	 *          @param @param
	 *         repairInformation @param @param loginUser 参数 @return void
	 *         返回类型 @throws
	 */

	private String upRepairLog(RepairInformation repairInformation,
			LoginUser loginUser) {
		String ss = "";
		switch (repairInformation.getRepairStatus()) {
			case "3" :
				ss = loginUser.getUserName() + "已受理";
				break;
			case "4":
				ss = RepairConst.repairAppoint.getName()
						+ repairInformation.getMaintainPerson() + "为您维修";
				break;
			case "5" :
				// ss = repairInformation.getMaintainPerson()+"维修开始";
				ss = RepairConst.maintainBegin.getName();
				break;
			case "6" :
				ss = RepairConst.maintainWait.getName();
				break;
			case "7" :
				// ss = repairInformation.getMaintainPerson()+"维修完成";
				ss = RepairConst.maintainFinish.getName();
				break;
			default :
				ss =RepairConst.getName(repairInformation.getRepairStatus());
				break;
		}
		return ss;

	}

	/**
	 * @Title: updateRepair (这里用一句话描述这个方法的作用) @param @param
	 *         repairInformation 参数 @return void 返回类型 @throws
	 */
	@Transactional(readOnly = false)
	public void updateRepair(RepairInformation repairInformation) {
		RepairRecord repairRecord = new RepairRecord();
		repairRecord.setRepairLog(RepairConst.repairUpdate.getName());
		repairRecord.setRepairStatus(RepairConst.repairUpdate.getIndex());
		repairInformation.setRepairStatus(RepairConst.repairSubmit.getIndex());
		super.save(repairInformation);
		repairInformationDao.deleteEquipment(repairInformation);
		String[] eqIds = repairInformation.getRepairEquipmentIds().split(",");
		for (int i = 0; i < eqIds.length; i++) {
			RepairEquipment re = new RepairEquipment();
			re.setRepairId(repairInformation.getId());
			re.setEquipmentId(eqIds[i]);
			repairInformationDao.saveEquip(re);
		}
		repairRecord.setRepairId(repairInformation.getId());
		repairRecord.setPresentDate(new Date());

		repairRecordService.save(repairRecord);

	}

	/**
	 * @Title: selectWorkloadList @Description:
	 *          @param @param page @param @param
	 *         maintainInformation @param @return 参数 @return
	 *         Page<MaintainInformationDto> 返回类型 @throws
	 */

	public Page<MaintainInformationDto> findPage(
			Page<MaintainInformationDto> page,
			MaintainInformationDto maintainInformation) {
		maintainInformation.setPage(page);
		List<MaintainInformationDto> list = repairInformationDao
				.findWorkList(maintainInformation);
		page.setList(list);
		// page.setCount(list.size());
		// Page<MaintainInformationDto> pages = new
		// Page<MaintainInformationDto>(pageNo,pageSize,list.size(),list);
		return page;
	}

	/**
	 * @Title: showName (这里用一句话描述这个方法的作用) @param @param
	 *         repairPeopleName @param @return 参数 @return List<Member>
	 *         返回类型 @throws
	 */

	public List<Member> showName(Member m) {
		return repairInformationDao.findName(m);
	}

	/**
	 * @Title: manageChargeback (处理退单) @param @param
	 *         repairInformation @param @param repairRecord 参数 @return void
	 *         返回类型 @throws
	 */
	@Transactional(readOnly = false)
	public void manageChargeback(RepairInformation repairInformation,
			RepairRecord repairRecord) {
		repairInformationDao.manageChargeback(repairInformation);
		repairRecord.setRepairLog(RepairConst.chargeback.getName());
		repairRecord.setRepairStatus(RepairConst.chargeback.getIndex());
		repairRecordService.save(repairRecord);
	}

	/**
	 * @Title: findPageByParam (这里用一句话描述这个方法的作用) @param @param
	 *         page @param @param repairInformation @param @return 参数 @return
	 *         Page<RepairInformation> 返回类型 @throws
	 */

	public Page<RepairInformation> findPageByParam(Page<RepairInformation> page,
                                                   RepairInformation repairInformation) {
		repairInformation.setPage(page);
		List<RepairInformation> list = repairInformationDao
				.findList(repairInformation);
		for (RepairInformation repairIn : list) {
			RepairRecord repairR = new RepairRecord();
			repairR.setRepairId(repairIn.getId());
			List<RepairRecord> recordList = repairRecordService
					.findList(repairR);
			repairIn.setRepairRecords(recordList);
		}
		page.setList(list);
		return page;
	}

	/**
	 * @Title: findPageByToday (这里用一句话描述这个方法的作用) @param @param
	 *         page @param @param repairInformation @param @return 参数 @return
	 *         Page<RepairInformation> 返回类型 @throws
	 */

	public Page<RepairInformation> findPageByToday(Page<RepairInformation> page,
                                                   RepairInformation repairInformation) {
		repairInformation.setPage(page);
		List<RepairInformation> list = repairInformationDao
				.findPageByToday(repairInformation);
		for (RepairInformation repairIn : list) {
			RepairRecord repairR = new RepairRecord();
			repairR.setRepairId(repairIn.getId());
			List<RepairRecord> recordList = repairRecordService
					.findList(repairR);
			repairIn.setRepairRecords(recordList);
		}
		page.setList(list);
		return page;
	}
	@Transactional(readOnly = false)
	public void updateComplainId(String id, String complainId) {
		repairInformationDao.updateComplainId(id, complainId);
	}

	/**
	 * @Title: getTypeByCode (这里用一句话描述这个方法的作用) @param @param
	 *         pCode @param @return 参数 @return List<RepairInformationType>
	 *         返回类型 @throws
	 */

	public List<RepairInformationType> getTypeByCode(String pCode) {
		return repairInformationDao.getTypeByCode(pCode);
	}

	/**
	 * @throws Exception
	 * @throws ParseException
	 * @Title: selfMotionAccept (自动受理) @param 参数 @return void
	 *         返回类型 @throws
	 */
	@Transactional(readOnly = false)
	public void selfMotionAccept(LoginUser loginUser) throws Exception {
		RepairInformation repair = new RepairInformation();
		repair.setRepairStatus(RepairConst.repairSubmit.getIndex());
		List<RepairInformation> list = repairInformationDao
				.findRepairList(repair);
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				// 报修
				RepairInformation repairs = new RepairInformation();
				repairs.setRepairStatus(RepairConst.repairAccept.getIndex());
				repairs.setId(list.get(i).getId());
				repairs.setAcceptPerson(loginUser.getUserName());
				// 报修状态
				RepairRecord repairRecord = new RepairRecord();
				repairRecord.setPresentDate(getTodays());
				repairRecord.setRepairId(repairs.getId());
				repairRecord.setRepairStatus(repairs.getRepairStatus());
				repairInformationDao.updateRepairStatus(repairs);
				repairRecord.setRepairLog(upRepairLog(repairs, loginUser));
				repairRecordService.save(repairRecord);
			}
		}

	}

	/**
	 * @throws Exception
	 * @throws ParseException
	 * @Title: selfSendOrder (自动派单) @param 参数 @return void
	 *         返回类型 @throws
	 */
	@Transactional(readOnly = false)
	public void selfSendOrder(LoginUser loginUser) throws Exception {
		MaintainInformationDto maintainInformation = new MaintainInformationDto();
		// 查询工作量最少的员工
		List<MaintainInformationDto> list = repairInformationDao
				.findWorkList(maintainInformation);
		// 查询所有已受理的事件
		RepairInformation repair = new RepairInformation();
		repair.setRepairStatus(RepairConst.repairAccept.getIndex());
		List<RepairInformation> repairList = repairInformationDao
				.findRepairList(repair);
		if (repairList.size() != 0) {
			for (RepairInformation repairs : repairList) {
				repairs.setRepairStatus(RepairConst.repairAppoint.getIndex());
				repairs.setMaintainPerson(list.get(0).getPersonName());
				repairs.setMaintainPersonId(list.get(0).getAid());
				// 计划维修时间(获取预约时间期间任意时间)
				repairs.setPlanMaintainDate(
						getBetweenDates(repairs.getAppointmentTime(),
								repairs.getAppointmentTimeGo()));
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date today = sdf.parse(sdf.format(new Date()));
				repairs.setAppointDate(today);
				// 维修人电话
				repairs.setMaintainPersonTelepno(list.get(0).getTelepno());

				// 事件状态表添加
				RepairRecord repairRecord = new RepairRecord();
				repairRecord.setPresentDate(getTodays());
				repairRecord.setRepairId(repairs.getId());
				repairRecord.setRepairStatus(repairs.getRepairStatus());
				repairInformationDao.updateRepairStatus(repairs);
				repairRecord.setRepairLog(upRepairLog(repairs, loginUser));
				repairRecordService.save(repairRecord);

			}

		}

	}

	/**
	 * 获取两个日期之间的随机日期
	 * 
	 * @param start
	 *            开始日期
	 * @param end
	 *            结束日期
	 * @return 随机日期
	 */
	private Date getBetweenDates(Date start, Date end) {
		List<Date> result = new ArrayList<Date>();
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(start);
		tempStart.add(Calendar.DAY_OF_YEAR, 1);

		Calendar tempEnd = Calendar.getInstance();
		tempEnd.setTime(end);
		while (tempStart.before(tempEnd)) {
			result.add(tempStart.getTime());
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
		}

		int index = (int) (Math.random() * result.size());
		Date planMaintainDate = result.get(index);
		return planMaintainDate;
	}

	/**
	 * @Title: updateRepairShowUpDate @Description:
	 *         返回类型 @throws
	 */
	@Transactional(readOnly = false)
	public void updateRepairShowUpDate(RepairInformation repairInformation) {
		repairInformationDao.updateRepairStatus(repairInformation);
	}

	/**
	 * @Title: getTypeList (这里用一句话描述这个方法的作用) @param @param
	 *         page @param @param repairInformationType @param @return
	 *         参数 @return Page<RepairInformationType> 返回类型 @throws
	 */

	public Page<RepairInformationType> getTypeList(
			Page<RepairInformationType> page,
			RepairInformationType repairInformationType) {

		repairInformationType.setPage(page);
		List<RepairInformationType> list = repairInformationDao
				.getTypeList(repairInformationType);
		page.setList(list);
		return page;
	}

	/**
	 * @Title: saveType (这里用一句话描述这个方法的作用) @param @param
	 *         repairInformationType 参数 @return void 返回类型 @throws
	 */
	@Transactional(readOnly = false)
	public void saveType(RepairInformationType repairInformationType) {

		int total = repairInformationDao.getTypeTotal();
		repairInformationType.setTypeId((total + 1) + "");
		if (repairInformationType.getParentCode() == null
				|| "".equals(repairInformationType.getParentCode())) {
			repairInformationType.setParentCode("0");
		}
		repairInformationDao.saveType(repairInformationType);
	}

	/**
	 * @return @Title: deleteType @Description:
	 *          @param @param repairInformationType
	 *         参数 @return void 返回类型 @throws
	 */
	/*
	 * public Map<String, Object> deleteType( RepairInformationType
	 * repairInformationType) { Map<String, Object> map = new HashMap<String,
	 * Object>(); RepairInformationType repairInformation = repairInformationDao
	 * .findById(repairInformationType.getTypeId()); if
	 * ("0".equals(repairInformation.getParentCode())) {
	 * List<RepairInformationType> list = repairInformationDao
	 * .getTypeByCode(repairInformationType.getTypeId()); if (list.size() > 0) {
	 * map.put("data", "该一级类别下面有二级，不能删除！"); return map; } }
	 * 
	 * repairInformationDao.deleteType(repairInformationType); map.put("data",
	 * ""); return map; }
	 */

	/**
	 * @Title: findById (这里用一句话描述这个方法的作用) @param @param
	 *         typeId @param @return 参数 @return RepairInformationType
	 *         返回类型 @throws
	 */

	/**
	 * @return @Title: deleteType @Description:
	 *          @param @param repairInformationType
	 *         参数 @return void 返回类型 @throws
	 */
	@Transactional(readOnly = false)
	public Map<String, Object> deleteType(
			RepairInformationType repairInformationType) {
		Map<String, Object> map = new HashMap<String, Object>();
		RepairInformationType repairInformation = repairInformationDao
				.findById(repairInformationType.getTypeId());
		if ("0".equals(repairInformation.getParentCode())) {
			List<RepairInformationType> list = repairInformationDao
					.getTypeByCode(repairInformationType.getTypeId());
			if (list.size() > 0) {
				map.put("data", "该一级类别下面有二级，不能删除！");
				return map;
			}
		}
		RepairInformation repair = new RepairInformation();
		List<RepairInformation> findRepairList = repairInformationDao
				.findRepairList(repair);
		for (RepairInformation r : findRepairList) {
			String arr[] = r.getRepairTypeCode().split("-");
			if (repairInformationType.getTypeId().equals(arr[0])
					|| repairInformationType.getTypeId().equals(arr[1])) {
				map.put("data", "该类别已经被引用，不能删除！");
				return map;
			}
		}
		repairInformationDao.deleteType(repairInformationType);
		map.put("data", "");
		return map;
	}

	public RepairInformationType findById(String typeId) {
		return repairInformationDao.findById(typeId);
	}
	public RepairInformationType findByName(String typeCode) {
		return repairInformationDao.findByName(typeCode);
	}

	/**
	 * @Title: editType (这里用一句话描述这个方法的作用) @param @param
	 *         repairInformationType 参数 @return void 返回类型 @throws
	 */
	@Transactional(readOnly = false)
	public void editType(RepairInformationType repairInformationType) {
		repairInformationDao.editType(repairInformationType);
	}

	/**
	 * @Title: export  @param response @param @param
	 *         repairInformation 参数 @return void 返回类型 @throws
	 */

	public void export(HttpServletResponse response,
			RepairInformation repairInformation) throws IOException {
		List<RepairInformation> repairInformations = repairInformationDao
				.findList(repairInformation);
		OutputStream os = response.getOutputStream();// 取得输出流
		response.reset();// 清空输出流
		response.setHeader("Content-disposition", "attacment;filename="
				+ new String("报修事件信息.xls".getBytes("UTF-8"), "ISO-8859-1"));// 通知浏览器要下载
		response.setContentType("application/msexcel");// 定义输出类型
		// 数据类型转换
		export(repairInformations, os);
	}
	private void export(List<RepairInformation> repairInformations,
			OutputStream os) throws IOException {
		{
			// 创建工作簿
			HSSFWorkbook book = new HSSFWorkbook();
			// 创建Sheet对象
			HSSFSheet sheet = book.createSheet("报修事件信息");
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
			HSSFCell cell015 = row0.createCell(15);
			cell015.setCellStyle(style);
			// 向单元格中设置值
			cell00.setCellValue("序号");
			cell01.setCellValue(new HSSFRichTextString("订单号"));
			cell02.setCellValue("报修人");
			cell03.setCellValue("联系电话");
			cell04.setCellValue("事件级别");
			cell05.setCellValue("事件来源");
			cell06.setCellValue("报修类型");
			cell07.setCellValue("受理人员");
			cell08.setCellValue("维修人员");
			cell09.setCellValue("维修人电话");
			cell010.setCellValue("到场时间");
			cell011.setCellValue("预约时间从");
			cell015.setCellValue("预约时间至");
			cell012.setCellValue("报修地址");
			cell013.setCellValue("计划维修时间");
			cell014.setCellValue("故障描述");

			// 向excel中插入数据
			for (int i = 0; i < repairInformations.size(); i++) {
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
				HSSFCell cell15 = row.createCell(15);
				cell0.setCellValue(i + 1);
				RepairInformation repairInformation = repairInformations.get(i);
				cell1.setCellValue(repairInformation.getOrderNumber());
				cell2.setCellValue(repairInformation.getRepairPeopleName());
				cell3.setCellValue(repairInformation.getRepairPhone());
				// 事件级别
				cell4.setCellValue(repairInformation.getIncidentRank());
				String info = "";
				if ("1".equals(repairInformation.getIncidentSource())) {
					info = "pc端提报";
				} else if ("2".equals(repairInformation.getIncidentSource())) {
					info = "app端提报";
				} else if("4".equals(repairInformation.getIncidentSource())){
					info = "报警事件";
				}else {
					info = "检修转事件";
				}
				cell5.setCellValue(info);
				cell6.setCellValue(repairInformation.getRepairType());
				cell7.setCellValue(repairInformation.getAcceptPerson());
				cell8.setCellValue(repairInformation.getMaintainPerson());
				cell9.setCellValue(
						repairInformation.getMaintainPersonTelepno());
				cell10.setCellValue(
						repairInformation.getMaintainShowUpDate() == null
								? ""
								: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
										.format(repairInformation
												.getMaintainShowUpDate()));
				// 预约时间
				cell11.setCellValue(
						repairInformation.getAppointmentTime() == null
								? ""
								: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
										.format(repairInformation
												.getAppointmentTime()));
				cell15.setCellValue(
						repairInformation.getAppointmentTimeGo() == null
								? ""
								: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
										.format(repairInformation
												.getAppointmentTimeGo()));
				cell12.setCellValue(repairInformation.getRepairDetailAddress());
				cell13.setCellValue(
						repairInformation.getPlanMaintainDate() == null
								? ""
								: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
										.format(repairInformation
												.getPlanMaintainDate()));
				cell14.setCellValue(repairInformation.getFaultDescription());
			}
			// 将数据写出
			book.write(os);
			os.close();
			// book.close();
		}
	}

	  
	    /**  
	    * @Title: selectByGroup  
	    * (这里用一句话描述这个方法的作用)
	    * @param @param repairInformationDto
	    * @param @return    参数  
	    * @return List<RepairInformationDto>    返回类型  
	    * @throws  
	    */  
	    
	public List<RepairInformationDto> selectByGroup(RepairInformationDto repairInformationDto) {
		List<RepairInformationDto> list = repairInformationDao.selectByGroup(repairInformationDto);
		for (RepairInformationDto d: list) {
			d.setType(CalculateWeekDay(d.getCreateTime()));
		}
		return list;
	}

	private String CalculateWeekDay(Date createTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(createTime);
		int week = cal.get(Calendar.DAY_OF_WEEK);
		switch (week) {
			case 1:
				return "1";
			case 2:
				return "2";
			case 3:
				return "3";
			case 4:
				return "4";
			case 5:
				return "5";
			case 6:
				return "6";
			case 7:
				return "7";
			default:
				return "100";
		}
	}


	/**
		    * @Title: selectComplainList  
		    * (这里用一句话描述这个方法的作用)
		    * @param @param repairInformationDto
		    * @param @return    参数  
		    * @return List<RepairInformationDto>    返回类型  
		    * @throws  
		    */  
		    
		public List<RepairInformationDto> selectComplainList(RepairInformationDto repairInformationDto) {
			
			return repairInformationDao.selectComplainList(repairInformationDto);
		}

			  
			    /**  
			    * @Title: selectAgainList  
			    * (这里用一句话描述这个方法的作用)
			    * @param @param repairInformationDto
			    * @param @return    参数  
			    * @return List<RepairInformationDto>    返回类型  
			    * @throws  
			    */  
			    
			public List<RepairInformationDto> selectAgainList(RepairInformationDto repairInformationDto) {
				return repairInformationDao.selectAgainList(repairInformationDto);
			}

				  
				    /**  
				    * @Title: selectOnlyList  
				    * (这里用一句话描述这个方法的作用)
				    * @param @param repairInformationDto
				    * @param @return    参数  
				    * @return List<RepairInformationDto>    返回类型  
				    * @throws  
				    */  
				    
				public List<RepairInformationDto> selectOnlyList(RepairInformationDto repairInformationDto) {
					return repairInformationDao.selectOnlyList(repairInformationDto);
				}

					  
					    /**  
					    * @Title: getRepairTargetList  
					    * (这里用一句话描述这个方法的作用)
					    * @param @param repairTargetDto
					    * @param @return    参数  
					    * @return List<RepairTargetDto>    返回类型  
					    * @throws  
					    */  
					    
					public List<RepairTargetDto> getRepairTargetList(RepairTargetDto repairTargetDto) {
						return repairInformationDao.getRepairTargetList(repairTargetDto);
					}

						  
						    /**  
						    * @Title: getEveryBuildCount  
						    * (这里用一句话描述这个方法的作用)
						    * @param @param r
						    * @param @return    参数  
						    * @return int    返回类型  
						    * @throws  
						    */  
						    
						public int getEveryBuildCount(RepairTargetDto r) {
							return repairInformationDao.getEveryBuildCount(r);
						}

							  
							    /**  
							    * @Title: getEveryBuildName  
							    * (这里用一句话描述这个方法的作用)
							    * @param @param r
							    * @param @return    参数  
							    * @return RepairTargetDto    返回类型  
							    * @throws  
							    */  
							    
							public RepairTargetDto getEveryBuildName(RepairTargetDto r) {
								return repairInformationDao.getEveryBuildName(r);
							}
									  
									    /**  
									    * @Title: getNumList  
									    * (这里用一句话描述这个方法的作用)
									    * @param @param rdto
									    * @param @return    参数  
									    * @return List<RepairTargetDto>    返回类型  
									    * @throws  
									    */  
									    
									public List<RepairTargetDto> getNumList(RepairTargetDto rdto) {
										return repairInformationDao.getNumList(rdto);
									}

										  
										    /**  
										    * @Title: getComplainNum  
										    * (这里用一句话描述这个方法的作用)
										    * @param @param rdto
										    * @param @return    参数  
										    * @return int    返回类型  
										    * @throws  
										    */  
										    
										public int getComplainNum(RepairTargetDto rdto) {
											return repairInformationDao.getComplainNum(rdto);
										}

											  
											    /**  
											    * @Title: getRepairPartList  
											    * (这里用一句话描述这个方法的作用)
											    * @param @param repairCategoryDto
											    * @param @return    参数  
											    * @return List<RepairCategoryDto>    返回类型  
											    * @throws  
											    */  
											    
											public List<RepairCategoryDto> getRepairPartList(
													RepairCategoryDto repairCategoryDto) {
												return repairInformationDao.getRepairPartList(repairCategoryDto);
											}

												  
												    /**  
												    * @Title: getRepairClassList  
												    * (这里用一句话描述这个方法的作用)
												    * @param @param repairCategoryDto
												    * @param @return    参数  
												    * @return List<RepairCategoryDto>    返回类型  
												    * @throws  
												    */  
												    
												public List<RepairCategoryDto> getRepairClassList(
														RepairCategoryDto repairCategoryDto) {
													return repairInformationDao.getRepairClassList(repairCategoryDto);
												}

													  
													    /**  
													    * @Title: getEveryCategoryCount  
													    * (这里用一句话描述这个方法的作用)
													    * @param @param r1
													    * @param @return    参数  
													    * @return int    返回类型  
													    * @throws  
													    */  
													    
													public int getEveryCategoryCount(RepairTargetDto r1) {
														return repairInformationDao.getEveryCategoryCount(r1);
													}


	public Page<RepairInformation> findCheckPageByParam(Page<RepairInformation> page, RepairInformation repairInformation) {
		repairInformation.setPage(page);
		List<RepairInformation> list = repairInformationDao
				.findCheckList(repairInformation);
		for (RepairInformation repairIn : list) {
			RepairRecord repairR = new RepairRecord();
			repairR.setRepairId(repairIn.getId());
			List<RepairRecord> recordList = repairRecordService
					.findList(repairR);
			repairIn.setRepairRecords(recordList);
		}
		page.setList(list);
		return page;
	}
}

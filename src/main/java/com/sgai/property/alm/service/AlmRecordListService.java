package com.sgai.property.alm.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.alm.dao.AlmRecordListDao;
import com.sgai.property.alm.entity.AlmDeviceLevelRelation;
import com.sgai.property.alm.entity.AlmRecordHisList;
import com.sgai.property.alm.entity.AlmRecordList;
import com.sgai.property.ctl.entity.CtlUser;
import com.sgai.property.ctl.service.CtlComRuleService;
import com.sgai.property.ctl.service.CtlUserService;
import com.sgai.property.em.entity.EmRepairList;
import com.sgai.property.em.service.EmRepairListService;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.property.mdm.entity.MdmDevicesUseInfo;
import com.sgai.property.mdm.entity.MdmSpaceTree;
import com.sgai.property.mdm.service.MdmDevicesUseInfoService;
import com.sgai.property.mdm.service.MdmSpaceTreeService;

import net.sf.json.JSONObject;

/**
 *
 * ClassName: AlmRecordListService com.sgai.property.commonService.vo;(报警记录列表Service)
 *
 * @author 王天尧 Date 2017年11月24日 Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class AlmRecordListService extends CrudServiceExt<AlmRecordListDao, AlmRecordList> {

	@Autowired
	private MdmDevicesUseInfoService mdmDevicesUseInfoService;
	@Autowired
	private MdmSpaceTreeService mdmSpaceTreeService;
	@Autowired
	private AlmDeviceLevelRelationService almDeviceLevelRelationService;
	@Autowired
	private AlmRecordHisListService almRecordHisListService;
	@Autowired
	private CtlComRuleService ctlComRuleService;
	@Autowired
	private EmRepairListService emRepairListService;
	@Autowired
	private CtlUserService ctlUserService;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public Page<AlmRecordList> getAlarmListByUser(Page<AlmRecordList> page, AlmRecordList almRecordList) {
		List<AlmRecordList> aaa = dao.getAlarmListByUser(almRecordList);
		almRecordList.setPage(page);
		almRecordList.preGet();
		page.setList(dao.getAlarmListByUser(almRecordList));
		page.setOrderBy("updated_dt desc");
		return page;
	}

	public AlmRecordList get(String id) {
		return super.get(id);
	}

	public List<AlmRecordList> findList(AlmRecordList almRecordList) {
		return super.findList(almRecordList);
	}

	public Integer findCount(AlmRecordList almRecordList) {
		return dao.findCount(almRecordList);
	}

	public List<AlmRecordList> findListOrderByStates(AlmRecordList almRecordList) {
		return dao.findListOrderByStates(almRecordList);
	}

	public Page<AlmRecordList> findPage(Page<AlmRecordList> page, AlmRecordList almRecordList) {
		return super.findPage(page, almRecordList);
	}

	@Transactional(readOnly = false)
	public void save(AlmRecordList almRecordList) {
		super.save(almRecordList);
	}

	@Transactional(readOnly = false)
	public void delete(AlmRecordList almRecordList) {
		super.delete(almRecordList);
	}

	public Page<AlmRecordList> findIndexAlmByPage(Page<AlmRecordList> page, AlmRecordList almRecordList) {
		almRecordList.setPage(page);
		almRecordList.preGet();
		page.setList(dao.findIndexAlmForPage(almRecordList));
		return page;
	}

	/**
	 *
	 * saveAlmMsg:(保存报警信息).
	 *
	 * @param devicesCode
	 *            设备编码
	 * @param almMessage
	 *            :void 报警信息
	 * @since JDK 1.8
	 * @author 王天尧
	 * @throws ParseException
	 */
	@Transactional(readOnly = false)
	public void saveAlmMsg(String devicesCode, String almMessage, AlmRecordList almRecordList,
			AlmRecordHisList almRecordHisList) throws ParseException {
		if (!("").equals(devicesCode) && !("").equals(almMessage)) {
			// 通过设备编码找到设备
			MdmDevicesUseInfo mdmDevicesUseInfo = new MdmDevicesUseInfo();
			mdmDevicesUseInfo.setDevicesCode(devicesCode);
			List<MdmDevicesUseInfo> findList = mdmDevicesUseInfoService.findList(mdmDevicesUseInfo);
			MdmDevicesUseInfo mdmDevicesUseInfoNew = findList.get(0);
			// 通过设备类型获取报警等级，分类，专业等信息
			AlmDeviceLevelRelation almDeviceLevelRelation = new AlmDeviceLevelRelation();
			AlmDeviceLevelRelation almDeviceLevelRelationNew = new AlmDeviceLevelRelation();
			almDeviceLevelRelation.setClassCode(mdmDevicesUseInfoNew.getClassCode());
			List<AlmDeviceLevelRelation> list = almDeviceLevelRelationService
					.findList(almDeviceLevelRelation);
			if(list.size()>0) {
				almDeviceLevelRelationNew = list.get(0);
			}
			// 将报警信息保存到报警记录列表中
			String recordNum = ctlComRuleService.getNext(mdmDevicesUseInfoNew.getComCode(), "ALMCODE-RECORD");
			saveAlmRecordList(almRecordList, almMessage, recordNum, almDeviceLevelRelationNew, mdmDevicesUseInfoNew);
			// 同时将历史信息存储到报警记录历史表中
			saveAlmRecordHisList(almRecordHisList, almRecordList);
		}

	}

	/**
	 *
	 * saveAlmRecordList:(保存报警记录).
	 *
	 * @param almRecordList
	 *            报警记录entity
	 * @param almMessage
	 *            报警信息
	 * @param almDeviceLevelRelationNew
	 *            报警关系entity
	 * @param mdmDevicesUseInfoNew
	 *            :void 设备主数据entity
	 * @since JDK 1.8
	 * @author 王天尧
     */
	@Transactional(readOnly = false)
	public void saveAlmRecordList(AlmRecordList almRecordList, String almMessage, String recordNum,
			AlmDeviceLevelRelation almDeviceLevelRelationNew, MdmDevicesUseInfo mdmDevicesUseInfoNew) {

		almRecordList.setRecordNum(recordNum);
		almRecordList.setAlarmMsg(almMessage);
		almRecordList.setAlarmTime(new Date());
		almRecordList.setAlermTypeCode(almDeviceLevelRelationNew.getAlermTypeCode());
		almRecordList.setAlermTypeName(almDeviceLevelRelationNew.getAlermTypeName());
		almRecordList.setDevicesCode(mdmDevicesUseInfoNew.getDevicesCode());
		almRecordList.setDevicesName(mdmDevicesUseInfoNew.getDevicesName());
		almRecordList.setEnabledFlag("Y");
		almRecordList.setProfCode(almDeviceLevelRelationNew.getProfCode());
		almRecordList.setProfName(almDeviceLevelRelationNew.getProfName());
		almRecordList.setLevelCode(almDeviceLevelRelationNew.getLevelCode());
		almRecordList.setLevelName(almDeviceLevelRelationNew.getLevelName());
		almRecordList.setStates("10");
		almRecordList.setSpaceId(mdmDevicesUseInfoNew.getSpaceCode());
		almRecordList.setSpaceName(mdmDevicesUseInfoNew.getRemarks());
		almRecordList.setComCode(mdmDevicesUseInfoNew.getComCode());
		almRecordList.setModuCode(mdmDevicesUseInfoNew.getModuCode());
		save(almRecordList);
	}

	/**
	 *
	 * saveAlmRecordHisList:(保存报警历史记录表).
	 *
	 * @param almRecordHisList
	 *            历史记录entity
	 *            :void 报警记录entity
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	@Transactional(readOnly = false)
	public void saveAlmRecordHisList(AlmRecordHisList almRecordHisList, AlmRecordList almRecordList) {
		almRecordHisList.setAlarmMsg(almRecordList.getAlarmMsg());
		almRecordHisList.setAlarmTime(almRecordList.getAlarmTime());
		almRecordHisList.setAlermTypeCode(almRecordList.getAlermTypeCode());
		almRecordHisList.setAlermTypeName(almRecordList.getAlermTypeName());
		almRecordHisList.setDevicesCode(almRecordList.getDevicesCode());
		almRecordHisList.setDevicesName(almRecordList.getDevicesName());
		almRecordHisList.setEnabledFlag(almRecordList.getEnabledFlag());
		almRecordHisList.setProfCode(almRecordList.getProfCode());
		almRecordHisList.setProfName(almRecordList.getProfName());
		almRecordHisList.setLevelCode(almRecordList.getLevelCode());
		almRecordHisList.setLevelName(almRecordList.getLevelName());
		almRecordHisList.setSpaceId(almRecordList.getSpaceId());
		almRecordHisList.setSpaceName(almRecordList.getSpaceName());
		almRecordHisList.setRecordNum(almRecordList.getRecordNum());
		almRecordHisList.setMasterId(almRecordList.getId());
		almRecordHisList.setStates(almRecordList.getStates());
		almRecordHisList.setProcessTime(almRecordList.getProcessTime());
		almRecordHisList.setRemarks(almRecordList.getRemarks());
		almRecordHisList.setComCode(almRecordList.getComCode());
		almRecordHisList.setModuCode(almRecordList.getModuCode());
		almRecordHisListService.save(almRecordHisList);

	}

	/**
	 *
	 * @Title: generateEvents @com.sgai.property.commonService.vo;(生成维修事件) @param @param id
	 * 报警记录id @param @param user 当前登录用户 @param @throws Exception 参数 @return void
	 * 返回类型 @throws
	 */
	@Transactional
	public String generateEvents(String id, LoginUser user) throws Exception {
		AlmRecordList almRecordList = get(id);
		EmRepairList emRepairList = new EmRepairList();
		String emCode = ctlComRuleService.getNext(user.getComCode(), "EMCODE-WX");
		/* emRepairList.setComCode(user.getComCode()); */
		emRepairList.setFromNum(almRecordList.getRecordNum());
		emRepairList.setEmCode(emCode);
		emRepairList.setRepairType("ZDBJ");
		emRepairList.setRepairFrom("报警");
		emRepairList.setAddress(almRecordList.getSpaceName());
		emRepairList.setRepairContent(almRecordList.getAlarmMsg());
		emRepairList.setEnabledFlag("Y");
		emRepairListService.saveRepair(emRepairList, user);
		// 修改状态，处理中
		almRecordList.setStates("20");
		almRecordList.setProcessTime(emRepairList.getRepairDate());
		almRecordList.setEmCode(emCode);
		save(almRecordList);
		// 同步到报警历史记录中
		saveAlmRecordHisList(new AlmRecordHisList(), almRecordList);
		return emCode;
	}

	// 更新报警记录
	@Transactional
	public void updateAlmRecord(String emCode, String states) {
		EmRepairList emRepairList = new EmRepairList();
		AlmRecordList almRecordList = new AlmRecordList();
		emRepairList.setEmCode(emCode);
		List<EmRepairList> findList2 = emRepairListService.findList(emRepairList);
		if (findList2.get(0).getRepairType().equals("ZDBJ")) {
			almRecordList.setRecordNum(findList2.get(0).getFromNum());
			List<AlmRecordList> findList = findList(almRecordList);
			if (findList.size() > 0) {
				AlmRecordList almRecordList2 = findList.get(0);
				if ("4".equals(states)) {
					almRecordList2.setStates("30");
				} else if ("3".equals(states)) {
					almRecordList2.setStates("40");
				}
				almRecordList2.setProcessTime(new Date());
				save(almRecordList2);
				// 同步到报警历史记录中
				saveAlmRecordHisList(new AlmRecordHisList(), almRecordList2);
			}
		}

	}

	@SuppressWarnings("null")
	@Transactional
	public String getAllName(String spaceCode,String comCode,String moduCode) {
		List<String> nameList = new ArrayList<String>();
		MdmSpaceTree mdmSpaceTree = new MdmSpaceTree();
		mdmSpaceTree.setSpaceCode(spaceCode);
		mdmSpaceTree.setComCode(comCode);
		mdmSpaceTree.setModuCode(moduCode);
		MdmSpaceTree space = mdmSpaceTreeService.getByCode(mdmSpaceTree);
		String allName = "";
		nameList.add(space.getSpaceName());
		String parentCode = space.getParentCode();
		while (StringUtils.isNotBlank(parentCode)) {
			mdmSpaceTree.setSpaceCode(parentCode);
			mdmSpaceTree.setComCode(comCode);
			mdmSpaceTree.setModuCode(moduCode);
			MdmSpaceTree parentSpace = mdmSpaceTreeService.getByCode(mdmSpaceTree);
			parentCode = parentSpace.getParentCode();
			nameList.add(parentSpace.getSpaceName());
		}
		for (int i = nameList.size() - 1; i >= 0; i--) {
			allName += nameList.get(i) + "-";
		}
		return allName.substring(0, allName.length() - 1);
	}

	/**
	 *
	 * @Title: almConfirm @com.sgai.property.commonService.vo;(确认报警信息) @param @param id 参数 @return
	 * void 返回类型 @throws
	 */
	@Transactional
	public void almConfirm(String id) {
		AlmRecordList almRecordList = get(id);
		almRecordList.setStates("20");
		almRecordList.setProcessTime(new Date());
		save(almRecordList);
		// 同步到报警历史记录中
		saveAlmRecordHisList(new AlmRecordHisList(), almRecordList);
	}

	/**
	 *
	 * @Title: almProcess @com.sgai.property.commonService.vo;(处理报警信息) @param @param id @param @param
	 * remarks 处理信息 @return void 返回类型 @throws
	 */
	@Transactional
	public void almProcess(String id, String remarks, String states) {
		AlmRecordList almRecordList = get(id);
		almRecordList.setStates(states);
		almRecordList.setProcessTime(new Date());
		almRecordList.setRemarks(remarks);
		save(almRecordList);
		// 同步到报警历史记录中
		saveAlmRecordHisList(new AlmRecordHisList(), almRecordList);
	}

	/**
	 *
	 * @Title: getMsgById @com.sgai.property.commonService.vo;(获取处理信息详情) @param @param
	 * id @param @return 参数 @return Map<String,Object> 返回类型 @throws
	 */
	@Transactional
	public Map<String, Object> getMsgById(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		AlmRecordList almRecordList = get(id);
		result.put("date", almRecordList.getProcessTime());
		CtlUser user = new CtlUser();
		user.setUserCode(almRecordList.getUpdatedBy());
		List<CtlUser> findList = ctlUserService.findAllList(user);
		result.put("person", findList.get(0).getUserName());
		result.put("remarks", almRecordList.getRemarks());
		return result;

	}

	/**
	 * @Title: updateAlm @Description: 修改状态 @param @param id 参数 @return void
	 * 返回类型 @throws
	 */
	@Transactional
	public void updateAlm(String id, String states) {
		AlmRecordList almRecordList = get(id);
		almRecordList.setStates(states);
		almRecordList.setProcessTime(new Date());
		save(almRecordList);
	}
	/**
	 *
	 * get7gBjData:(7g报警数据处理).
	 *
	 * @param content
	 * @return :String
	 * @since JDK 1.8
	 * @author wangtianyao
	 * @throws ParseException
	 */
	@Transactional
	public void get7gBjData(String content) throws ParseException {
		JSONObject jasonObject = JSONObject.fromObject(content);
		Map map = jasonObject;
		String path = (String) map.get("NodeFullTag");
		String msg = map.get("ValueName") +"报警";
		saveAlmMsg(path,msg,new AlmRecordList(),new AlmRecordHisList());
	}
	/**
	 *
	    * @Title: almCountsByProf
	    * @com.sgai.property.commonService.vo;(根据位置查询报警条数)
	    * @param @return    参数
	    * @return List<Map<String,String>>    返回类型
	    * @throws
	 */
	@Transactional
	public List<Map<String,String>>  almCountsByProf(){
		return dao.almCountsByProf();
	}
}

package com.sgai.property.em.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.em.dao.EmRepairListDao;
import com.sgai.property.em.dto.EmRepairListVo;
import com.sgai.property.em.entity.EmRepairList;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.property.wf.service.WfInstanceRecordService;
import com.sgai.property.wf.service.WfUserRightService;

/**
 * 修理事件维护Service
 * @author guanze
 * @version 2017-12-05
 */
@Service
@Transactional
public class EmRepairListService extends CrudServiceExt<EmRepairListDao, EmRepairList> {

	@Autowired
	private EmRepairListDao emRepairListDao;
	@Autowired
	private WfInstanceRecordService wfInstanceRecordService;
	@Autowired
	private WfUserRightService wfUserRightService;
	public EmRepairList get(String id) {
		return super.get(id);
	}

	public List<EmRepairList> findList(EmRepairList emRepairList) {
		return super.findList(emRepairList);
	}

	public Page<EmRepairList> findPage(Page<EmRepairList> page, EmRepairList emRepairList) {
		page.setOrderBy("a.UPDATED_DT");
		return super.findPage(page, emRepairList);
	}

	public Page<EmRepairList> findRepairPage(Page<EmRepairList> page, EmRepairList emRepairList) {
		page.setOrderBy("a.UPDATED_DT");
		emRepairList.setPage(page);
		emRepairList.preGet();
		page.setList(dao.findRepairList(emRepairList));
		return page;
	}

	@Transactional(readOnly = false)
	public void save(EmRepairList emRepairList) {
		super.save(emRepairList);
	}

	@Transactional(readOnly = false)
	public void delete(EmRepairList emRepairList) {
		super.delete(emRepairList);
	}

	@Transactional(readOnly = false)
	public List<EmRepairList> batchDelete(List<EmRepairList> objs) {
		return super.batchDelete(objs);
	}

	@Transactional(readOnly = false)
	public EmRepairList findNextCodeEmRepairList() {
		return emRepairListDao.findNextCodeEmRepairList();
	}
	@Transactional(readOnly = false)
	public void saveRepair(EmRepairList emRepairList,LoginUser user) throws Exception {
		emRepairList.setRepairDate(new Date());
		emRepairList.setStates("0");
		emRepairList.setEmType("WX");
		super.save(emRepairList);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("flowCode", emRepairList.getEmType());
		params.put("type", "start");
		params.put("emCode", emRepairList.getEmCode());
		params.put("stepCode", "WX-A-001");
		wfInstanceRecordService.changeStatus(params,user);
	}
	/**
	 *
	 * updateComplaint:(更新修理事件状态).
	 * @param complCode
	 * @param status
	 * @param userCode
	 * @param userName :void
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	public void updateRepairList(String userCode,String emCode, String status ) {
		EmRepairList emRepairList = new EmRepairList();
		emRepairList.setEmCode(emCode);
		emRepairList.preGet();
		EmRepairList info = dao.getRepairDetail(emRepairList);
		info.setStates(status);
		if(status=="2") {
			info.setUserCode(userCode);
			info.setUserName(userCode);
		}
		super.save(info);
	}
	/**
	 *
	 * getByCode:(根据事件编号查找事件).
	 * @param emRepairList
	 * @return :EmRepairList
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	public EmRepairListVo getByCode(EmRepairListVo emRepairList) {
		emRepairList.preGet();
		return dao.getByCode(emRepairList);
	}
	/**
	 *
	    * @Title: getEmCodes
	    * @com.sgai.property.commonService.vo;(获得当前用户所能看到的维修事件事件编号)
	    * @param @param userInfo
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	public String getEmCodes(LoginUser userInfo) {
		EmRepairList emRepairList = new EmRepairList();
		Map<String,String> param = new HashMap<String,String>();
		param.put("flowCode", "WX");
		param.put("userCode", userInfo.getUserId());
		param.put("comCode", userInfo.getComCode());
		Map<String, String> userMap = wfUserRightService.getListsByUser(param);
		emRepairList.setSqlMap(userMap);
		emRepairList.setRepairType("ZDBJ");
		List<EmRepairList> findList = findList(emRepairList);
		StringBuilder emCodes = new StringBuilder();
		if(findList.size()>0) {
			findList.forEach((repairList)->emCodes.append("'").append(repairList.getEmCode()).append("',"));
		    emCodes.delete(emCodes.length()-1, emCodes.length());
		}
		return emCodes.toString();
	}
}

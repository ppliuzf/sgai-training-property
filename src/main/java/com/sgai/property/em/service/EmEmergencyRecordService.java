package com.sgai.property.em.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.em.dao.EmEmergencyRecordDao;
import com.sgai.property.em.entity.EmEmergencyRecord;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.property.wf.service.WfInstanceRecordService;

/**
 * 应急事件列表维护Service
 * @author guanze
 * @version 2017-12-05
 */
@Service
@Transactional
public class EmEmergencyRecordService extends CrudServiceExt<EmEmergencyRecordDao, EmEmergencyRecord> {

	@Autowired
	private EmEmergencyRecordDao emEmergencyRecordDao;
	@Autowired
	private WfInstanceRecordService wfInstanceRecordService;

	public EmEmergencyRecord get(String id) {
		return super.get(id);
	}

	public List<EmEmergencyRecord> findList(EmEmergencyRecord emEmergencyRecord) {
		return super.findList(emEmergencyRecord);
	}

	public Page<EmEmergencyRecord> findPage(Page<EmEmergencyRecord> page, EmEmergencyRecord emEmergencyRecord) {
		return super.findPage(page, emEmergencyRecord);
	}

	@Transactional(readOnly = false)
	public void save(EmEmergencyRecord emEmergencyRecord) {
		super.save(emEmergencyRecord);
	}

	@Transactional(readOnly = false)
	public void delete(EmEmergencyRecord emEmergencyRecord) {
		super.delete(emEmergencyRecord);
	}

	@Transactional(readOnly = false)
	public List<EmEmergencyRecord> batchDelete(List<EmEmergencyRecord> objs) {
		return super.batchDelete(objs);
	}

	@Transactional(readOnly = false)
	public EmEmergencyRecord findNextCodeEmEmergencyRecord() {
		return emEmergencyRecordDao.findNextCodeEmEmergencyRecord();
	}

	@Transactional(readOnly = false)
	public EmEmergencyRecord getEmEmergencyRecord(EmEmergencyRecord emEmergencyRecord) {
		return emEmergencyRecordDao.getEmEmergencyRecord(emEmergencyRecord);
	}

	@Transactional(readOnly = false)
	public void saveEmergencyRecord(EmEmergencyRecord emEmergencyRecord,LoginUser user) throws Exception {
		super.save(emEmergencyRecord);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("emCode", emEmergencyRecord.getEmCode());
		params.put("stepCode", "YJ-A-001");
		params.put("type", "start");
		params.put("flowCode", "YJ");
		wfInstanceRecordService.changeStatus(params,user);
	}


	/**
	 *
	    * @Title: updateEmergencyRecord
	    * @com.sgai.property.commonService.vo;(跟新应急事件状态)
	    * @param @param emCode
	    * @param @param status
	    * @param @param userCode
	    * @param @param userName    参数
	    * @return void    返回类型
	    * @throws
	 */
	public void updateEmergencyRecord(String emCode, String status ,String userCode, String userName) {
		EmEmergencyRecord emEmergencyRecord = new EmEmergencyRecord();
		emEmergencyRecord.setEmCode(emCode);
		EmEmergencyRecord info = dao.getEmEmergencyRecord(emEmergencyRecord);
		info.setStates(status);
		info.setOperator(userCode);
		super.save(info);
	}

	public Page<EmEmergencyRecord> findSkanPage(Page<EmEmergencyRecord> page, EmEmergencyRecord emEmergencyRecord) {
		emEmergencyRecord.setPage(page);
		page.setList(dao.findSkanList(emEmergencyRecord));
		return page;
	}

	public Page<EmEmergencyRecord> findMainPage(Page<EmEmergencyRecord> page, EmEmergencyRecord emEmergencyRecord) {
		emEmergencyRecord.setPage(page);
		page.setList(dao.findMainList(emEmergencyRecord));
		return page;
	}


	/**
	 * getEmergencyCountByTime:图表-- 获得按照分类获得各个分类的数量.
	 * @param emEmergencyRecord
	 * @return :List<Map<String,String>>
	 * @since JDK 1.8
	 * @author admin
	 */
	public  List<Map<String,String>> getEmergencyCountByTime(EmEmergencyRecord emEmergencyRecord){
		return dao.getEmergencyCountByTime(emEmergencyRecord);
	}


}

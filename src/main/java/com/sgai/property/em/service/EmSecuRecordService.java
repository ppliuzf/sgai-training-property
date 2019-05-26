package com.sgai.property.em.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.em.dao.EmSecuRecordDao;
import com.sgai.property.em.dto.EmSecuRecordVo;
import com.sgai.property.em.entity.EmSecuRecord;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.property.wf.service.WfInstanceRecordService;

/**
 * 安保事件维护Service
 * @author guanze
 * @version 2017-12-05
 */
@Service
@Transactional
public class EmSecuRecordService extends CrudServiceExt<EmSecuRecordDao, EmSecuRecord> {

	@Autowired
	private EmSecuRecordDao emSecuRecordDao;
	@Autowired
	private WfInstanceRecordService wfInstanceRecordService;

	public EmSecuRecord get(String id) {
		return super.get(id);
	}

	public List<EmSecuRecord> findList(EmSecuRecord emSecuRecord) {
		return super.findList(emSecuRecord);
	}

	public Page<EmSecuRecord> findPage(Page<EmSecuRecord> page, EmSecuRecord emSecuRecord) {
		page.setOrderBy("a.UPDATED_DT");

		return super.findPage(page, emSecuRecord);
	}

	public Page<EmSecuRecord> findSecuRecordPage(Page<EmSecuRecord> page, EmSecuRecord emSecuRecord) {
		page.setOrderBy("a.UPDATED_DT");
		emSecuRecord.setPage(page);
		emSecuRecord.preGet();
		page.setList(dao.findSecuRecordList(emSecuRecord));
		return page;
	}

	@Transactional(readOnly = false)
	public void save(EmSecuRecord emSecuRecord) {
		super.save(emSecuRecord);
	}

	@Transactional(readOnly = false)
	public void delete(EmSecuRecord emSecuRecord) {
		super.delete(emSecuRecord);
	}

	@Transactional(readOnly = false)
	public List<EmSecuRecord> batchDelete(List<EmSecuRecord> objs) {
		return super.batchDelete(objs);
	}

	@Transactional(readOnly = false)
	public EmSecuRecord findNextCodeEmSecuRecord() {
		return emSecuRecordDao.findNextCodeEmSecuRecord();
	}

	public void saveSecuRecord(EmSecuRecord emSecuRecord,LoginUser user) throws Exception {
		super.save(emSecuRecord);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("emCode", emSecuRecord.getEmCode());
		params.put("stepCode", "AB-A-001");
		params.put("type", "start");
		params.put("flowCode", "AB");
		wfInstanceRecordService.changeStatus(params,user);
	}

	/**
	 *
	 * updateSecuRecord:(更新安保事件状态).
	 * @param emSecuRecord :void
	 * @since JDK 1.8
	 * @author guanze
	 */
	public void updateSecuRecord(String emCode, String status ,String userCode, String userName) {
		EmSecuRecord emSecuRecord = new EmSecuRecord();
		emSecuRecord.setEmCode(emCode);
		emSecuRecord.preGet();
		EmSecuRecord info = dao.getEmSecuRecords(emSecuRecord);
		info.setStates(status);
		info.setProcPerson(userCode);
		info.setUserCode(userCode);
		info.setUserName(userName);
		super.save(info);
	}

	/**
	 *
	 * getEmSecuRecord:(查询投诉事件).
	 * @param emSecuRecord
	 * @return :EmSecuRecord
	 * @since JDK 1.8
	 * @author guanze
	 */
	public EmSecuRecordVo getEmSecuRecord(EmSecuRecordVo emSecuRecord) {
		emSecuRecord.preGet();
		return dao.getEmSecuRecord(emSecuRecord);
	}

	public void updateEmSecuRecord(String emCode, String status ,String userCode, String userName) {
		EmSecuRecord emSecuRecord = new EmSecuRecord();
		emSecuRecord.setEmCode(emCode);
		emSecuRecord.preGet();
		EmSecuRecord info = dao.getEmSecuRecords(emSecuRecord);
		info.setStates(status);
		info.setProcPerson(userCode);
		info.setUserCode(userCode);
		info.setUserName(userName);
		super.save(info);
	}
}

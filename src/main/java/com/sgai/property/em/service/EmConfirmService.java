package com.sgai.property.em.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.em.dao.EmConfirmDao;
import com.sgai.property.em.entity.EmConfirm;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.property.wf.service.WfInstanceRecordService;

/**
 *
    * @ClassName: EmConfirmService
    * @com.sgai.property.commonService.vo;(事件核实Service)
    * @author LiuYang
    * @date 2017年12月5日
    * @Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class EmConfirmService extends CrudServiceExt<EmConfirmDao, EmConfirm> {

	@Autowired
	private WfInstanceRecordService wfInstanceRecordService;
	@Autowired
	private EmEmergencyRecordService emEmergencyRecordService;

	@Autowired
	private EmRepairListService emRepairListService;
	@Autowired
	private EmSecuRecordService emSecuRecordService;

	public EmConfirm get(String id) {
		return super.get(id);
	}

	public List<EmConfirm> findList(EmConfirm emConfirm) {
		return super.findList(emConfirm);
	}

	public Page<EmConfirm> findPage(Page<EmConfirm> page, EmConfirm emConfirm) {
		return super.findPage(page, emConfirm);
	}

	@Transactional(readOnly = false)
	public void save(EmConfirm emConfirm) {
		super.save(emConfirm);
	}

	@Transactional(readOnly = false)
	public void delete(EmConfirm emConfirm) {
		super.delete(emConfirm);
	}

	@Transactional(readOnly = false)
	public void saveConfirm(EmConfirm emConfirm,LoginUser user) {
		super.save(emConfirm);
		//修改流程实例 并开始下一步
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("emCode", emConfirm.getEmCode());
		switch (emConfirm.getEmType()) {
			case "TS":
				params.put("stepCode", "TS-B-001");
				break;
			case "AB":
				params.put("stepCode", "AB-B-001");
				emSecuRecordService.updateSecuRecord(emConfirm.getEmCode(),"1", emConfirm.getConfirmPerson(),emConfirm.getConfirmPerson());
				break;
			case "WX":
				params.put("stepCode", "WX-B-001");
				emRepairListService.updateRepairList(emConfirm.getConfirmPerson(),emConfirm.getEmCode(), "1");
				break;
			default:
				//添加     应急跟新状态
				emEmergencyRecordService.updateEmergencyRecord(emConfirm.getEmCode(),"1",emConfirm.getConfirmPerson(),"");
				params.put("stepCode", "YJ-B-001");
				break;
		}
		params.put("type", "next");
		params.put("flowCode", emConfirm.getEmType());

		try {
			wfInstanceRecordService.changeStatus(params,user);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	/**
	 *
	 * getCode:(根据事件编码查找事件).
	 * @param emConfirm
	 * @return :EmConfirm
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	public EmConfirm getCode(EmConfirm emConfirm) {
		emConfirm.preGet();
		return dao.getByCode(emConfirm);
	}
}

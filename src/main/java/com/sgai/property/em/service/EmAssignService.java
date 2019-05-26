package com.sgai.property.em.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.em.dao.EmAssignDao;
import com.sgai.property.em.entity.EmAssign;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.property.wf.service.WfInstanceRecordService;

/**
 *
    * @ClassName: EmAssignService
    * @com.sgai.property.commonService.vo;(事件指派Service)
    * @author LiuYang
    * @date 2017年12月5日
    * @Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class EmAssignService extends CrudServiceExt<EmAssignDao, EmAssign> {

	@Autowired
	private WfInstanceRecordService wfInstanceRecordService;
	@Autowired
	private EmRepairListService emRepairListService;
	@Autowired
	private EmComplaintsService emComplaintsService;
	@Autowired
	private EmSecuRecordService emSecuRecordService;
	public EmAssign get(String id) {
		return super.get(id);
	}

	public List<EmAssign> findList(EmAssign emAssign) {
		return super.findList(emAssign);
	}

	public Page<EmAssign> findPage(Page<EmAssign> page, EmAssign emAssign) {
		return super.findPage(page, emAssign);
	}

	@Transactional(readOnly = false)
	public void save(EmAssign emAssign) {
		super.save(emAssign);
	}

	@Transactional(readOnly = false)
	public void delete(EmAssign emAssign) {
		super.delete(emAssign);
	}

	/**
	 *
	    * @Title: saveAssignAndProcess
	    * @com.sgai.property.commonService.vo;(保存指派人并创建事件处理)
	    * @param @param emAssign    参数
	    * @return void    返回类型
	    * @throws
	 */
	public void saveAssignAndProcess(EmAssign emAssign,LoginUser user) {
		super.save(emAssign);
		//创建事件处理对象
		/*EmProcess emProcess = new EmProcess();
		emProcess.setInstanceId(emAssign.getInstanceId());
		emProcess.setEmType(emAssign.getEmType());
		emProcess.setEmCode(emAssign.getEmCode());
		emProcess.setProcPerson(emAssign.getAssignPerson());
		emProcess.setEndFlag("N");
		emProcess.setEnabledFlag("Y");
		emProcessService.save(emProcess);*/
		//修改流程实例 并开始下一步
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("emCode", emAssign.getEmCode());
		switch (emAssign.getEmType()) {
			case "TS":
				emComplaintsService.updateComplaint(emAssign.getEmCode(),"1",emAssign.getAssignPerson(),emAssign.getAssignPerson());
				params.put("stepCode", "TS-B-001");
				break;
			case "AB":
				emSecuRecordService.updateEmSecuRecord(emAssign.getEmCode(), "2", emAssign.getAssignPerson(), emAssign.getAssignPerson());
				params.put("stepCode", "AB-C-001");
				break;
			case "WX":
				params.put("stepCode", "WX-C-001");
				emRepairListService.updateRepairList(emAssign.getAssignPerson(),emAssign.getEmCode(), "2");
				break;
			default:
				//
				break;
		}
		params.put("type", "next");
		params.put("flowCode", emAssign.getEmType());
		try {
			wfInstanceRecordService.changeStatus(params,user);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 *
	 * getAssignByCode:(查询指派数据).
	 * @param emAssign
	 * @return :EmAssign
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public EmAssign getAssignByCode(EmAssign emAssign) {
		emAssign.preGet();
		return dao.getAssignByCode(emAssign);
	}
}

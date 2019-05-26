package com.sgai.property.em.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.em.dao.EmResourceDispatchDao;
import com.sgai.property.em.entity.EmProcess;
import com.sgai.property.em.entity.EmResourceDispatch;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.property.wf.service.WfInstanceRecordService;


/**
 *
    * @ClassName: EmResourceDispatchService
    * @com.sgai.property.commonService.vo;( 应急资源调度Service)
    * @author LiuYang
    * @date 2017年12月5日
    * @Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class EmResourceDispatchService extends CrudServiceExt<EmResourceDispatchDao, EmResourceDispatch> {
	@Autowired
	private EmResourceDispatchDao dao;
	@Autowired
	private EmProcessService emProcessService;
	@Autowired
	private EmEmergencyRecordService emEmergencyRecordService;
	@Autowired
	private WfInstanceRecordService wfInstanceRecordService;

	public EmResourceDispatch get(String id) {
		return super.get(id);
	}

	public EmResourceDispatch getResourceDispatch(String emCode) {
		return dao.getResourceDispatch(emCode);
	}

	public List<EmResourceDispatch> findList(EmResourceDispatch emResourceDispatch) {
		return super.findList(emResourceDispatch);
	}

	public Page<EmResourceDispatch> findPage(Page<EmResourceDispatch> page, EmResourceDispatch emResourceDispatch) {
		return super.findPage(page, emResourceDispatch);
	}

	@Transactional(readOnly = false)
	public void save(EmResourceDispatch emResourceDispatch) {
		super.save(emResourceDispatch);
	}

	@Transactional(readOnly = false)
	public void delete(EmResourceDispatch emResourceDispatch) {
		super.delete(emResourceDispatch);
	}

	/**
	 *
	    * @Title: saveResource
	    * @com.sgai.property.commonService.vo;(应急事件处理保存调用方法)
	    * @param @param emResourceDispatch    参数
	    * @return void    返回类型
	    * @throws
	 */
	@Transactional(readOnly = false)
	public void saveResource(EmResourceDispatch emResourceDispatch,LoginUser user) {
		super.save(emResourceDispatch);
		//添加事件处理节点
		EmProcess emProcess = new EmProcess();
		emProcess.setInstanceId(emResourceDispatch.getInstanceId());
		emProcess.setEmCode(emResourceDispatch.getEmCode());
		emProcess.setEmType(emResourceDispatch.getEmType());
		emProcess.setEnabledFlag("Y");
		emProcess.setProcPerson(user.getUserId());
		emProcess.setRepairContent(emResourceDispatch.getRepairContent());
		emProcess.setRepairDatetime(emResourceDispatch.getRepairDatetime());
		emProcessService.save(emProcess);
		//应急事件修改状态
		emEmergencyRecordService.updateEmergencyRecord(emResourceDispatch.getEmCode(),"2",user.getUserId(),user.getUserName());

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("emCode", emResourceDispatch.getEmCode());
		params.put("stepCode", "YJ-C-001");
		params.put("type", "next");
		params.put("flowCode", emResourceDispatch.getEmType());
		try {
			wfInstanceRecordService.changeStatus(params,user);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}

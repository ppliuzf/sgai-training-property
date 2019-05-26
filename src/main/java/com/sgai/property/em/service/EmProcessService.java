package com.sgai.property.em.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.alm.service.AlmRecordListService;
import com.sgai.property.em.dao.EmProcessDao;
import com.sgai.property.em.entity.EmProcess;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.property.wf.service.WfInstanceRecordService;


/**
 *
    * @ClassName: EmProcessService
    * @com.sgai.property.commonService.vo;(事件处理(含更新)Service)
    * @author LiuYang
    * @date 2017年12月5日
    * @Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class EmProcessService extends CrudServiceExt<EmProcessDao, EmProcess> {

	@Autowired
	private WfInstanceRecordService wfInstanceRecordService;
	@Autowired
	private EmComplaintsService emComplaintsService;
	@Autowired
	private EmRepairListService emRepairListService;
	@Autowired
	private EmSecuRecordService emSecuRecordService;
	@Autowired
	private AlmRecordListService almRecordListService;
	public EmProcess get(String id) {
		return super.get(id);
	}

	public List<EmProcess> findList(EmProcess emProcess) {
		return super.findList(emProcess);
	}

	public Page<EmProcess> findPage(Page<EmProcess> page, EmProcess emProcess) {
		return super.findPage(page, emProcess);
	}

	@Transactional(readOnly = false)
	public void save(EmProcess emProcess) {
		super.save(emProcess);
	}

	@Transactional(readOnly = false)
	public void delete(EmProcess emProcess) {
		super.delete(emProcess);
	}

	/**
	 *
	    * @Title: saveComplete
	    * @com.sgai.property.commonService.vo;(处理完成保存并修改流程)
	    * @param @param emProcess
	    * @param @throws Exception    参数
	    * @return void    返回类型
	    * @throws
	 */
	public void saveComplete(EmProcess emProcess,LoginUser user) throws Exception {
		super.save(emProcess);
		//修改流程实例 并开始下一步
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("emCode", emProcess.getEmCode());
		params.put("type", "next");
		params.put("flowCode", emProcess.getEmType());
		switch (emProcess.getEmType()) {
		case "TS":
			emComplaintsService.updateComplaint(emProcess.getEmCode(),"2",emProcess.getProcPerson(),emProcess.getProcPerson());
			params.put("stepCode", "TS-C-001");
			break;
		case "AB":
			//添加   安保跟新状态
			emSecuRecordService.updateSecuRecord(emProcess.getEmCode(),"3", emProcess.getProcPerson(),emProcess.getProcPerson());
			params.put("stepCode", "AB-D-001");
			break;
		case "WX":
			//添加    维修跟新状态
			params.put("stepCode", "WX-D-001");
			emRepairListService.updateRepairList(emProcess.getProcPerson(),emProcess.getEmCode(), "3");
			//若是报警事件生成的同步报警事件的状态
			almRecordListService.updateAlmRecord(emProcess.getEmCode(), "3");
			break;
		default:
			//添加     应急跟新状态
			params.put("stepCode", "YJ-C-001");
			break;
		}
		wfInstanceRecordService.changeStatus(params,user);
	}
}

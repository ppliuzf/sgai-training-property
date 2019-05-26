package com.sgai.property.wf.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.property.wf.entity.WfFlowDefine;
import com.sgai.property.wf.entity.WfInstanceRecord;
import com.sgai.property.wf.manager.IwfProcess;
import com.sgai.property.wf.service.WfFlowDefineService;
import com.sgai.property.wf.service.WfInstanceRecordService;

/**
 *
    * ClassName: IwfProcessImpl
    * com.sgai.property.commonService.vo;(事件流程实例驱动接口)
    * @author lenovo
    * Date 2017年12月6日
    * Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class IwfProcessImpl implements IwfProcess {

	@Autowired
	private WfFlowDefineService wfFlowDefineService;
	@Autowired
	private WfInstanceRecordService wfInstanceRecordService;

	/**
	 */
	@Override
	public void startFlow(Map<String, Object> params, LoginUser user) {
		Integer stepSeq=0;
		Object flowCode = params.get("flowCode");
		List<WfFlowDefine> list = wfFlowDefineService.findFlowList(String.valueOf(flowCode));
		for (WfFlowDefine info : list) {
			stepSeq += 10;
			WfInstanceRecord record = new WfInstanceRecord();
			record.setInstanceId(String.valueOf(params.get("emCode")));//流程实例主键
			record.setEmCode(String.valueOf(params.get("emCode")));//业务事件的编码
			record.setEmType(String.valueOf(flowCode));
			record.setStepSeq(String.valueOf(stepSeq));//步骤流水号
			record.setStepUrl(info.getStepUrl());
			if(stepSeq==10) {
				record.setStepCode(String.valueOf(params.get("stepCode")));//步骤编码
				record.setStepStat("2");//状态
				if (user!=null) {
					record.setUserCode(user.getUserId());
					record.setUserName(user.getUserName());
				}
			}else if(stepSeq==20){
				record.setStepStat("1");//状态
			}else {
				record.setStepStat("0");//状态
			}
			record.setStepName(info.getStepName());//步骤名称
			record.setStepLevel("1");//步骤层级
			record.setStepType(info.getStepPos());//节点类型
			record.setEnabledFlag("Y");//是否可用
			wfInstanceRecordService.save(record);
		}
	}

	/**
	 事件流程实例化结束
	 * @see com.sgai.modules.wf.manager.IwfProcess#endFlow(String)
	 */
	@Override
	public void endFlow(Map<String, Object> params,LoginUser user) {
		String stepCode = String.valueOf(params.get("stepCode"));
		String emCode = String.valueOf(params.get("emCode"));
		WfInstanceRecord info = new WfInstanceRecord();
		info.setEmCode(emCode);
		List<WfInstanceRecord> list = wfInstanceRecordService.findWfInstanceRecordList(info);
		info = list.get(list.size()-1);
		info.setStepCode(stepCode);
		info.setStepStat("2");
		if (user != null) {
			info.setUserCode(user.getUserId());
			info.setUserName(user.getUserName());
		}
		wfInstanceRecordService.save(info);
	}

	/**
	 事件流程实例化下一步处理
	 * @see com.sgai.modules.wf.manager.IwfProcess#nextStep(Map)
	 */
	@Override
	public void nextStep(Map<String, Object> params,LoginUser user) {
		String emCode = String.valueOf(params.get("emCode"));
		String stepCode = String.valueOf(params.get("stepCode"));
		WfInstanceRecord info = new WfInstanceRecord();
		info.setEmCode(emCode);
		List<WfInstanceRecord> list = wfInstanceRecordService.findWfInstanceRecordList(info);
		for (int i = 0; i < list.size(); i++) {
			 if ("1".equals(list.get(i).getStepStat())) {
				info = list.get(i);
				info.setStepCode(stepCode);
				info.setStepStat("2");
				if (user != null) {
					info.setUserCode(user.getUserId());
					info.setUserName(user.getUserName());
				}
				if(i+1 < list.size()) {
					WfInstanceRecord wfInstanceRecord = list.get(i+1);
					if(wfInstanceRecord!=null) {
						wfInstanceRecord.setStepStat("1");
						wfInstanceRecordService.save(wfInstanceRecord);
					}
				}
				break;
			}
		}
		wfInstanceRecordService.save(info);
	}

	/**
	 事件流程实例化上一步处理
	 * @see com.sgai.modules.wf.manager.IwfProcess#preStep(Map)
	 */
	@Override
	public void preStep(Map<String, Object> params,LoginUser user) {
		String emCode = String.valueOf(params.get("emCode"));//事件编码
		WfInstanceRecord info = new WfInstanceRecord();
		WfInstanceRecord perInfo = new WfInstanceRecord();
		info.setEmCode(emCode);
		List<WfInstanceRecord> list = wfInstanceRecordService.findWfInstanceRecordList(info);
		Integer stepSeq=0;
		for (int i = 0; i < list.size(); i++) {
			//处理当前节点状态为0
			 if ("1".equals(list.get(i).getStepStat())) {
				stepSeq = Integer.parseInt(list.get(i).getStepSeq())-10;
				info = list.get(i);
				info.setStepCode("");
				info.setStepStat("0");

			}
			 //修改上一节点状态为1
			 if ((String.valueOf(stepSeq)).equals(list.get(i).getStepStat())) {
				perInfo = list.get(i);
				perInfo.setStepCode("");
				perInfo.setStepStat("1");
			}
		}
		wfInstanceRecordService.save(info);
		wfInstanceRecordService.save(perInfo);
	}

	/**
	 查询事件处理流程.
	 * @see com.sgai.modules.wf.manager.IwfProcess#findProcessList(String)
	 */
	@Override
	public List<WfInstanceRecord> findProcessList(String emCode) {
		WfInstanceRecord wfInstanceRecord = new WfInstanceRecord();
		wfInstanceRecord.setEmCode(emCode);
		List<WfInstanceRecord> list = wfInstanceRecordService.findWfInstanceRecordList(wfInstanceRecord);
		return list;
	}
}

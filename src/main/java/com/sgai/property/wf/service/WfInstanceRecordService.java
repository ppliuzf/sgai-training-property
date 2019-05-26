package com.sgai.property.wf.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.service.DeleteRulesUtils;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.property.wf.dao.WfInstanceRecordDao;
import com.sgai.property.wf.entity.WfFlowDefine;
import com.sgai.property.wf.entity.WfInstanceRecord;
import com.sgai.property.wf.manager.impl.IwfProcessImpl;

/**
 *
    * ClassName: WfInstanceRecordService
    * com.sgai.property.commonService.vo;(事件流程实例业务层)
    * @author yangyz
    * Date 2017年12月5日
    * Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class WfInstanceRecordService extends CrudServiceExt<WfInstanceRecordDao, WfInstanceRecord> {

	@Autowired
	private DeleteRulesUtils deleteRulesUtils;
	@Autowired
	private IwfProcessImpl iwfProcessImpl;

	public WfInstanceRecord get(String id) {
		return super.get(id);
	}

	public List<WfInstanceRecord> findList(WfInstanceRecord wfInstanceRecord) {
		return super.findList(wfInstanceRecord);
	}

	public Page<WfInstanceRecord> findPage(Page<WfInstanceRecord> page, WfInstanceRecord wfInstanceRecord) {
		return super.findPage(page, wfInstanceRecord);
	}

	@Transactional(readOnly = false)
	public void save(WfInstanceRecord wfInstanceRecord) {
		super.save(wfInstanceRecord);
	}

	@Transactional(readOnly = false)
	public void delete(WfInstanceRecord wfInstanceRecord) {
		super.delete(wfInstanceRecord);
	}

	/**
	 *
	 * deleteInstanceRecord:(批量删除事件流程实例).
	 * @param ids
	 * @return :Map<String,Object>
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public Map<String, Object> deleteInstanceRecord(String ids){
		Map<String, Object> map = new HashMap<String, Object>();
		String idss[]=ids.split(",");
		List<WfInstanceRecord> list = new ArrayList<WfInstanceRecord>();
		List<String> newList = new ArrayList<String>();
		for(String id:idss){
			if(id!=null&&!id.equals("")){
				newList.add(id);
				WfInstanceRecord info=super.get(id);
				list.add(info);
			}
		}
		Map<String,String> resultMap = deleteRulesUtils.checkBeforeDelete(WfInstanceRecord.class,newList);
		if("true".equals(resultMap.get("value"))) {
			List<WfInstanceRecord> finalList = batchDelete(list);
			if (finalList.size() > 0) {
				map.put("msg", "删除成功!");
			}else {
				map.put("msg", "删除失败！");
			}
			map.put("result", "success");
		}else {
			map.put("msg", resultMap.get("description"));
			map.put("result", "fail");
		}
		return map;
	}

	/**
	 *
	 * findProcessList:(查询事件实例流程数据).
	 * @param wfInstanceRecord
	 * @return :List<WfInstanceRecord>
	 * @since JDK 1.8
	 * @author lenovo
	 */
	public List<WfInstanceRecord> findWfInstanceRecordList(WfInstanceRecord wfInstanceRecord){

		return dao.findWfInstanceRecordList(wfInstanceRecord);
	}

	/**
	 *
	 * getInstanceRecord:(查询一个事件流程实例).
	 * @param wfInstanceRecord
	 * @return :WfInstanceRecord
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public WfInstanceRecord getInstanceRecord(WfInstanceRecord wfInstanceRecord) {
		return dao.getInstanceRecord(wfInstanceRecord);
	}

	/**
	 *
	 * changeStatus:(事件流程节点处理接口).
	 * @return
	 * @throws Exception :Map<String,String>
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public Map<String, String> changeStatus(Map<String, Object> params, LoginUser user) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		Object type = params.get("type");
		if ("start".equals(type)) {
			iwfProcessImpl.startFlow(params, user);
		}else if ("next".equals(type)) {
			iwfProcessImpl.nextStep(params, user);
		}else if ("pre".equals(type)) {
			iwfProcessImpl.preStep(params, user);
		}else if("end".equals(type)){
			iwfProcessImpl.endFlow(params, user);
		}
		return map;
	}
	/**
	 *
	 * getStepByCode:(根据用户获得其拥有的流程步骤).
	 * @param param
	 * @return :List<WfUserRight>
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public List<WfFlowDefine> getStepByCode(Map<String,String> param){
		return dao.getStepByUser(param);
	}

	/**
	 *
	 * getStepssByCode:(获取用户是否有创建权限).
	 * @param param
	 * @return :List<WfFlowDefine>
	 * @since JDK 1.8
	 * @author yangyz
	 */
	public List<WfFlowDefine> getStepssByCode(Map<String,String> param){
		return dao.getStepssByUser(param);
	}
}

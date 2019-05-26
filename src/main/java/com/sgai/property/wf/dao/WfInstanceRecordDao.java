package com.sgai.property.wf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.wf.entity.WfFlowDefine;
import com.sgai.property.wf.entity.WfInstanceRecord;
import com.sgai.property.wf.entity.WfUserRight;

/**
 * 
    * ClassName: WfInstanceRecordDao  
    * com.sgai.property.commonService.vo;(事件流程实例Dao)
    * @author yangyz  
    * Date 2017年12月5日  
    * Company 首自信--智慧城市创新中心
 */
@Mapper
public interface WfInstanceRecordDao extends CrudDao<WfInstanceRecord> {
	
	/**
	 * 
	 * findProcessList:(查询事件实例数据).
	 * @param wfInstanceRecord
	 * @return :List<WfInstanceRecord> 
	 * @since JDK 1.8
	 * @author yangyz
	 */
    List<WfInstanceRecord> findWfInstanceRecordList(WfInstanceRecord wfInstanceRecord);
	
	/**
	 * 查询一条事件实例数据
	 * getInstanceRecord:(这里用一句话描述这个方法的作用).
	 * @param wfInstanceRecord
	 * @return :WfInstanceRecord 
	 * @since JDK 1.8
	 * @author yangyz
	 */
    WfInstanceRecord getInstanceRecord(WfInstanceRecord wfInstanceRecord);
	/**
	 * 
	 * getStepByUser:(根据用户获取流程实例).
	 * @param param
	 * @return :List<WfInstanceRecord> 
	 * @since JDK 1.8
	 * @author 王天尧
	 */
    List<WfFlowDefine> getStepByUser(Map<String, String> param);
	
	List<WfFlowDefine> getStepssByUser(Map<String, String> param);
}
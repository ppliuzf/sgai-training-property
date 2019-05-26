package com.sgai.property.wf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.wf.entity.WfFlowDefine;

/**
 * 
    * ClassName: WfFlowDefineDao  
    * com.sgai.property.commonService.vo;(事件流程定义Dao)
    * @author yangyz  
    * Date 2017年12月5日  
    * Company 首自信--智慧城市创新中心
 */
@Mapper
public interface WfFlowDefineDao extends CrudDao<WfFlowDefine> {
	
	/**
	 * 
	 * findFlowList:(根据事件类型查询事件定义列表).
	 * @param flowCode
	 * @return :List<WfFlowDefine> 
	 * @since JDK 1.8
	 * @author yangyz
	 */
    List<WfFlowDefine> findFlowList(WfFlowDefine wfFlowDefine);
}
package com.sgai.property.wf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.common.persistence.Page;
import com.sgai.property.wf.entity.WfFlowDefine;
import com.sgai.property.wf.entity.WfUserRight;

/**
 * 
    * ClassName: WfUserRightDao  
    * com.sgai.property.commonService.vo;(流程权限DAO)
    * @author 王天尧  
    * Date 2017年12月5日  
    * Company 首自信--智慧城市创新中心
 */
@Mapper
public interface WfUserRightDao extends CrudDao<WfUserRight> {
	List<WfFlowDefine> getFlowListByUR(Map<String, String> param);
	List<WfFlowDefine> getAllFlowList(String comCode);
	void deleteStepTree(Map<String, String> param);
	List<WfFlowDefine> getFlowListUnOwn(Map<String, String> param);
	List<WfUserRight> findLike(Map<String, String> param);
	List<WfUserRight> findLoginUserAuthority(WfUserRight wfUserRight);
	
}
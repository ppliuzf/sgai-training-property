package com.sgai.property.em.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.em.dto.EmRepairListVo;
import com.sgai.property.em.entity.EmRepairList;

/**
 * 修理事件维护DAO接口
 * @author guanze
 * @version 2017-12-05
 */
@Mapper
public interface EmRepairListDao extends CrudDao<EmRepairList> {

	  
	    /**  
	    * @Title: findNextCodeEmRepairList  
	    * @com.sgai.property.commonService.vo;(这里用一句话描述这个方法的作用)
	    * @param @return    参数  
	    * @return EmRepairList    返回类型  
	    * @throws  
	    */  
	    
	EmRepairList findNextCodeEmRepairList();
	/**
	 * 
	 * getByCode:(根据事件编码查找事件).
	 * @return :EmRepairList 
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	EmRepairListVo getByCode(EmRepairListVo emRepairList);
	
	EmRepairList getRepairDetail(EmRepairList emRepairList);
	
	List<EmRepairList> findRepairList(EmRepairList emRepairList);
	
}
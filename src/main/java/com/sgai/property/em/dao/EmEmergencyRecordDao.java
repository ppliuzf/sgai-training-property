package com.sgai.property.em.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.em.entity.EmEmergencyRecord;

/**
 * 应急事件列表维护DAO接口
 * @author guanze
 * @version 2017-12-05
 */
@Mapper
public interface EmEmergencyRecordDao extends CrudDao<EmEmergencyRecord> {

	  
	    /**  
	    * @Title: findNextCodeEmEmergencyRecord  
	    * @com.sgai.property.commonService.vo;(这里用一句话描述这个方法的作用)
	    * @param @return    参数  
	    * @return EmEmergencyRecord    返回类型  
	    * @throws  
	    */  
	    
	EmEmergencyRecord findNextCodeEmEmergencyRecord();
	
	/**
	 * 
	    * @Title: getEmEmergencyRecord  
	    * @com.sgai.property.commonService.vo;(多条件查询一条记录)
	    * @param @param emEmergencyRecord
	    * @param @return    参数  
	    * @return EmEmergencyRecord    返回类型  
	    * @throws
	 */
    EmEmergencyRecord getEmEmergencyRecord(EmEmergencyRecord emEmergencyRecord);
	
	/**
	 * 
	    * @Title: findSkanList  
	    * @com.sgai.property.commonService.vo;(查看页面)
	    * @param @param emEmergencyRecord
	    * @param @return    参数  
	    * @return EmEmergencyRecord    返回类型  
	    * @throws
	 */
    List<EmEmergencyRecord> findSkanList(EmEmergencyRecord emEmergencyRecord);
	
	/**
	 * 
	    * @Title: findMainList  
	    * @com.sgai.property.commonService.vo;(查看创建页面)
	    * @param @param emEmergencyRecord
	    * @param @return    参数  
	    * @return EmEmergencyRecord    返回类型  
	    * @throws
	 */
    List<EmEmergencyRecord> findMainList(EmEmergencyRecord emEmergencyRecord);
	
	
	
	/**
	 * 图表 获得按照分类获得各个分类的数量
	 * @param emEmergencyRecord
	 * @return :List<Map<String,String>> 
	 * @since JDK 1.8
	 * @author admin
	 */
    List<Map<String,String>>  getEmergencyCountByTime(EmEmergencyRecord emEmergencyRecord);
	
	
}
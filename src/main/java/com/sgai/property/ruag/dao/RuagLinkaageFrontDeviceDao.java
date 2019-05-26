package com.sgai.property.ruag.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ruag.entity.RuagDeviceCalendarInstction;
import com.sgai.property.ruag.entity.RuagLinkaageFrontDevice;

/**
 * 联动前置设备DAO接口
 * @author yangyz
 * @version 2018-01-02
 */
@Mapper
public interface RuagLinkaageFrontDeviceDao extends CrudDao<RuagLinkaageFrontDevice> {
	List<RuagLinkaageFrontDevice> findAllOfList(RuagLinkaageFrontDevice ruagLinkaageFrontDevice);
	/**
	 * 
	    * @Title: findRules  
	    * @com.sgai.property.commonService.vo;(查询是否有符合的联动规则)
	    * @param @param path
	    * @param @return    参数  
	    * @return List<Map<String,String>>    返回类型  
	    * @throws
	 */
	List<Map<String,String>> findRules(String path);
	
	String getPath(Map<String, String> params);
}
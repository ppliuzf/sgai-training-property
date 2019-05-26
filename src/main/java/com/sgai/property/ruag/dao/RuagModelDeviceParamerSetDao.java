package com.sgai.property.ruag.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ruag.entity.RuagModelCalendar;
import com.sgai.property.ruag.entity.RuagModelDeviceParamerSet;

/**
 * 
    * @ClassName: RuagModelDeviceParamerSetDao  
    * @com.sgai.property.commonService.vo;(模式设备参数设置DAO接口)
    * @author 王天尧  
    * @date 2018年1月3日  
    * @Company 首自信--智慧城市创新中心
 */
@Mapper
public interface RuagModelDeviceParamerSetDao extends CrudDao<RuagModelDeviceParamerSet> {
	/**
	 * 
	    * @Title: deleteByModelId  
	    * @com.sgai.property.commonService.vo;(通过运行策略设置id删除参数设置)
	    * @param @param ruagModelDeviceParamerSet    参数  
	    * @return void    返回类型  
	    * @throws
	 */
	void deleteByModelId(RuagModelDeviceParamerSet ruagModelDeviceParamerSet);
	
	List<RuagModelDeviceParamerSet> findAllOfList(RuagModelDeviceParamerSet ruagModelDeviceParamerSet);
	
	void updateParamter(RuagModelDeviceParamerSet ruagModelDeviceParamerSet);
	
	List<RuagModelDeviceParamerSet> findDevicesById(String id);
}
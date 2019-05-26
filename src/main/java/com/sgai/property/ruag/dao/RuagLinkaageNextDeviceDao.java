package com.sgai.property.ruag.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ruag.entity.RuagLinkaageFrontDevice;
import com.sgai.property.ruag.entity.RuagLinkaageNextDevice;

/**
 * 联动后置设备DAO接口
 * @author yangyz
 * @version 2018-01-02
 */
@Mapper
public interface RuagLinkaageNextDeviceDao extends CrudDao<RuagLinkaageNextDevice> {
	/**
	 * 
	    * @Title: findJoinFrontList  
	    * @com.sgai.property.commonService.vo;(查询前置设备和后置设备的设备编号)
	    * @param @param ruagLinkaageNextDevice
	    * @param @return    参数  
	    * @return List<RuagLinkaageNextDevice>    返回类型  
	    * @throws
	 */
	List<RuagLinkaageNextDevice> findJoinFrontList(RuagLinkaageNextDevice ruagLinkaageNextDevice);
	List<RuagLinkaageNextDevice> findAllOfList(RuagLinkaageNextDevice ruagLinkaageNextDevice);
}
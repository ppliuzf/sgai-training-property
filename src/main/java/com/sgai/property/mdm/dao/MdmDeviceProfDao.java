package com.sgai.property.mdm.dao;

import org.apache.ibatis.annotations.Mapper;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.mdm.entity.MdmDeviceProf;

/**
 * 
    * ClassName: MdmDeviceProfDao  
    * com.sgai.property.commonService.vo;(设备专业Dao接口)
    * @author yangyz  
    * Date 2017年11月24日  
    * Company 首自信--智慧城市创新中心
 */
@Mapper
public interface MdmDeviceProfDao extends CrudDao<MdmDeviceProf> {
	
}
package com.sgai.property.mdm.dao;

import org.apache.ibatis.annotations.Mapper;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.mdm.entity.MdmDeviceParameter;

/**
 * 
    * ClassName: MdmDeviceParameterDao  
    * com.sgai.property.commonService.vo;(设备运行参数)
    * @author yangyz  
    * Date 2017年12月30日  
    * Company 首自信--智慧城市创新中心
 */
@Mapper
public interface MdmDeviceParameterDao extends CrudDao<MdmDeviceParameter> {
	
}
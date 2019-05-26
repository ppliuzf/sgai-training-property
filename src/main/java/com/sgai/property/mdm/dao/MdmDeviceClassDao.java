package com.sgai.property.mdm.dao;

import org.apache.ibatis.annotations.Mapper;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.mdm.entity.MdmDeviceClass;

/**
 * 
    * ClassName: MdmDeviceClassDao  
    * com.sgai.property.commonService.vo;(设备类型Dao接口)
    * @author yangyz  
    * Date 2017年11月24日  
    * Company 首自信--智慧城市创新中心
 */
@Mapper
public interface MdmDeviceClassDao extends CrudDao<MdmDeviceClass> {
	
	/**
	 * 
	 * getDeviceClass:(根据实体查询设备分类，可传参数).
	 * @param mdmDeviceClass
	 * @return :MdmDeviceClass 
	 * @since JDK 1.8
	 * @author yangyz
	 */
    MdmDeviceClass getDeviceClass(MdmDeviceClass mdmDeviceClass);
}
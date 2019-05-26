package com.sgai.property.mdm.dao;

import org.apache.ibatis.annotations.Mapper;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.mdm.dto.MdmDevicesModelVo;
import com.sgai.property.mdm.entity.MdmDevicesModel;

/**
 * 
    * ClassName: MdmDevicesModelDao  
    * com.sgai.property.commonService.vo;(设备型号Dao接口)
    * @author yangyz  
    * Date 2017年11月24日  
    * Company 首自信--智慧城市创新中心
 */
@Mapper
public interface MdmDevicesModelDao extends CrudDao<MdmDevicesModel> {
	
	/**
	 * 
	 * getDevicesModel:(根据实体查询设备型号，可传参数).
	 * @param mdmDevicesModel
	 * @return :MdmDevicesModel 
	 * @since JDK 1.8
	 * @author yangyz
	 */
    MdmDevicesModel getDevicesModel(MdmDevicesModel mdmDevicesModel);
	/**
	 * 
	 * getDevicesModel:(根据id查询设备型号（修改），可传参数).
	 * @param mdmDevicesModel
	 * @return :MdmDevicesModel 
	 * @since JDK 1.8
	 * @author maronglu
	 */
    MdmDevicesModelVo getDevicesModelVo(String id);

	
}
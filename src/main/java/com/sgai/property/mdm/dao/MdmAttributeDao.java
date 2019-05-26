package com.sgai.property.mdm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.mdm.dto.MdmDevicesUseInfoVo;
import com.sgai.property.mdm.entity.MdmAttribute;
import com.sgai.property.mdm.entity.MdmDeviceProf;
import com.sgai.property.mdm.entity.MdmDevicesUseInfo;


@Mapper
public interface MdmAttributeDao extends CrudDao<MdmAttribute> {
	

	List<MdmDevicesUseInfoVo> getALLAttrName(MdmDevicesUseInfoVo mdmDevicesUseInfoVo);
	
	List<MdmDevicesUseInfoVo> findAttrListVoByDevices(MdmDevicesUseInfoVo mdmDevicesUseInfoVo);
	
	MdmDevicesUseInfoVo getAttribute(String id);
	
}




package com.sgai.property.mdm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.mdm.dto.MdmDevicesUseInfoVo;
import com.sgai.property.mdm.entity.MdmDeviceProf;
import com.sgai.property.mdm.entity.MdmDevicesUseInfo;
import com.sgai.property.mdm.entity.MdmSpaceTree;

/**
 * 
 * ClassName: MdmDevicesUseInfoDao com.sgai.property.commonService.vo;(设备主数据Dao接口)
 * 
 * @author yangyz Date 2017年11月24日 Company 首自信--智慧城市创新中心
 */
@Mapper
public interface MdmDevicesUseInfoDao extends CrudDao<MdmDevicesUseInfo> {

	/**
	 * 
	 * findListVo:(查询设备主数据列表重新组装Vo).
	 * 
	 * @param mdmDevicesUseInfoVo
	 * @return :List<MdmDevicesUseInfoVo>
	 * @since JDK 1.8
	 * @author yangyz
	 */
    List<MdmDevicesUseInfoVo> findListVo(MdmDevicesUseInfoVo mdmDevicesUseInfoVo);

	/**
	 * 
	 * @Title: findListVo @com.sgai.property.commonService.vo;(查询除了id集合外的设备主数据列表重新组装Vo) @param @param
	 * mdmDevicesUseInfoVo @param @return 参数 @return List<MdmDevicesUseInfoVo>
	 * 返回类型 @throws
	 */
    List<MdmDevicesUseInfoVo> findNotByIdList(MdmDevicesUseInfoVo mdmDevicesUseInfoVo);
	
	MdmDevicesUseInfoVo getMdmDevicesUseInfoVo(String id);
	
	List<MdmDeviceProf> findProBySpace(Map<String, String> param);
	
	List<MdmDevicesUseInfoVo> findListByProf(MdmDevicesUseInfoVo mdmDevicesUseInfoVo);
	
	List<String> findDeviceCodes(Map<String, String> param);
	
	List<MdmDevicesUseInfoVo> getALLAttrName(MdmDevicesUseInfoVo mdmDevicesUseInfoVo);
	
    List<MdmDevicesUseInfoVo> findAttrListVoByDevices(MdmDevicesUseInfoVo mdmDevicesUseInfoVo);
    List<MdmDevicesUseInfo> findAttrList(MdmDevicesUseInfo mdmDevicesUseInfo);
	List<MdmSpaceTree> findSpaceInfo(Map<String, String> params);
    List<Map<String,String>> getAttr(Map<String, String> params);

    MdmDevicesUseInfo getDevicesByCode(MdmDevicesUseInfo mdmDevicesUseInfo);
}





package com.sgai.property.mdm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.mdm.entity.MdmSuppDeviceClassRelation;

/**
 * 供应商设备型号关联表DAO接口
 * @author liushang
 * @version 2017-11-27
 */
@Mapper
public interface MdmSuppDeviceClassRelationDao extends CrudDao<MdmSuppDeviceClassRelation> {
	List<MdmSuppDeviceClassRelation> findRestPage(MdmSuppDeviceClassRelation mdmSuppDeviceClassRelation);
	
	/**
	 * 
	 * findListByModelCode:(根据设备型号查询供应商).
	 * @param mdmSuppDeviceClassRelation
	 * @return :List<MdmSuppDeviceClassRelation> 
	 * @since JDK 1.8
	 * @author yangyz
	 */
    List<MdmSuppDeviceClassRelation> findListByModelCode(MdmSuppDeviceClassRelation mdmSuppDeviceClassRelation);
	
}
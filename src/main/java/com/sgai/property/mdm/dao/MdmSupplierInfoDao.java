package com.sgai.property.mdm.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.mdm.entity.MdmSupplierInfo;

/**
 * 供应商数据DAO接口
 * @author liushang
 * @version 2017-11-24
 */
@Mapper
public interface MdmSupplierInfoDao extends CrudDao<MdmSupplierInfo> {
	
	/**
	 * 
	 * getSuppByCode:(根据供应商编码查询供应商信息).
	 * @param mdmSupplierInfo
	 * @return :MdmSupplierInfo 
	 * @since JDK 1.8
	 * @author yangyz
	 */
    MdmSupplierInfo getSuppByCode(MdmSupplierInfo mdmSupplierInfo);
}
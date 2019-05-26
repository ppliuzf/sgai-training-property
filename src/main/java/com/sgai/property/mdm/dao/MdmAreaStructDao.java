package com.sgai.property.mdm.dao;

import org.apache.ibatis.annotations.Mapper;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.mdm.entity.MdmAreaStruct;

/**
 * 区域描述 ---空间DAO接口
 * @author zhb
 * @version 2017-11-24
 */
@Mapper
public interface MdmAreaStructDao extends CrudDao<MdmAreaStruct> {
	
	MdmAreaStruct getByCode(String arg0, String arg1);
	
}
package com.sgai.property.mdm.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.mdm.entity.MdmEmType;

/**
 * 事件类别维护DAO接口
 * @author liushang
 * @version 2017-12-05
 */
@Mapper
public interface MdmEmTypeDao extends CrudDao<MdmEmType> {
	
}
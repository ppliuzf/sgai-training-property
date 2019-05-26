package com.sgai.property.mdm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.mdm.entity.MdmMatClass;

/**
 * 物料分类表DAO接口
 * @author liushang
 * @version 2017-11-24
 */
@Mapper
public interface MdmMatClassDao extends CrudDao<MdmMatClass> {
	List<MdmMatClass> findRepeatList(MdmMatClass mdmMatClass);
	
}
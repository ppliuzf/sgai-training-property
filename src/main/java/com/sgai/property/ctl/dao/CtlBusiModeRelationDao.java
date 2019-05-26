package com.sgai.property.ctl.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlBusiModeRelation;

/**
 * 业务模块打包DAO接口
 * @author 王天尧
 * @version 2017-11-21
 */
@Mapper
public interface CtlBusiModeRelationDao extends CrudDao<CtlBusiModeRelation> {
	
}
package com.sgai.property.ctl.dao;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlUserExtRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户关系表DAO接口
 * @author 李伟
 * @version 2018-02-07
 */
@Mapper
public interface CtlUserExtRelationDao extends CrudDao<CtlUserExtRelation> {
	
}
package com.sgai.property.ctl.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlBusiUser;

/**
 * 外部系统用户维护DAO接口
 * @author 李伟
 * @version 2018-02-07
 */
@Mapper
public interface CtlBusiUserDao extends CrudDao<CtlBusiUser> {

}
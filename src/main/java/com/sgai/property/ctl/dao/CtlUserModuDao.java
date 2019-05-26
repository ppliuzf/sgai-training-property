package com.sgai.property.ctl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlModu;
import com.sgai.property.ctl.entity.CtlUserModu;

/**
 * 用户模块关系DAO接口
 * @author admin
 * @version 2018-03-28
 */
@Mapper
public interface CtlUserModuDao extends CrudDao<CtlUserModu> {
	List<CtlModu> findLackList(CtlModu ctlModu);
}
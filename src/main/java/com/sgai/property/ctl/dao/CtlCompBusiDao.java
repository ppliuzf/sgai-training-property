package com.sgai.property.ctl.dao;

import org.apache.ibatis.annotations.Mapper;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlCompBusi;

/**
 * 机构子系统关系DAO接口
 * @author admin
 * @version 2018-03-28
 */
@Mapper
public interface CtlCompBusiDao extends CrudDao<CtlCompBusi> {
	
	void deleteByCompCode(String comCode);
}
package com.sgai.property.ctl.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlRoleChart;

/**
 * 角色和图表关系DAO接口
 * @author admin
 * @version 2018-01-04
 */
@Mapper
public interface CtlRoleChartDao extends CrudDao<CtlRoleChart> {
	
	void deleteByRoleCode(String roleCode);
}
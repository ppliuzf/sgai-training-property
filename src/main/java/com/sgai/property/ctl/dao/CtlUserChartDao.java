package com.sgai.property.ctl.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlUserChart;

/**
 * 用户图表关系表DAO接口
 * @author admin
 * @version 2018-01-04
 */
@Mapper
public interface CtlUserChartDao extends CrudDao<CtlUserChart> {
	
	void deleteByUserCode(String userCode);
}
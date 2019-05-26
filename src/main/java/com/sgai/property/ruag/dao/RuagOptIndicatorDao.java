package com.sgai.property.ruag.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ruag.entity.RuagOptIndicator;

/**
 * 优化指标DAO接口
 * @author admin
 * @version 2018-08-17
 */
@Mapper
public interface RuagOptIndicatorDao extends CrudDao<RuagOptIndicator> {
	
}
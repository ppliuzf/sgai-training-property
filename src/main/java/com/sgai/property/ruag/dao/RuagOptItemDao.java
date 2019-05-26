package com.sgai.property.ruag.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ruag.entity.RuagOptItem;

/**
 * 优化项DAO接口
 * @author admin
 * @version 2018-08-17
 */
@Mapper
public interface RuagOptItemDao extends CrudDao<RuagOptItem> {
	
}
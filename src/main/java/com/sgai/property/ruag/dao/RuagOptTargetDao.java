package com.sgai.property.ruag.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ruag.entity.RuagOptTarget;

/**
 * 优化目标DAO接口
 * @author admin
 * @version 2018-08-17
 */
@Mapper
public interface RuagOptTargetDao extends CrudDao<RuagOptTarget> {
	
}
package com.sgai.property.ruag.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ruag.entity.RuagOptimizedRecord;

/**
 * 优化记录DAO接口
 * @author admin
 * @version 2018-08-17
 */
@Mapper
public interface RuagOptimizedRecordDao extends CrudDao<RuagOptimizedRecord> {
	
}
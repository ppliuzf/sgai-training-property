package com.sgai.property.ruag.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ruag.entity.RuagOptimizationParameterSet;

/**
 * 优化参数配置DAO接口
 * @author admin
 * @version 2018-08-17
 */
@Mapper
public interface RuagOptimizationParameterSetDao extends CrudDao<RuagOptimizationParameterSet> {
	
	void updateRuagWorkModelDetail(RuagOptimizationParameterSet ruagOptimizationParameterSet);
	
	
	List<RuagOptimizationParameterSet> findParameter(RuagOptimizationParameterSet ruagOptimizationParameterSet);
	
	
	void deleteAll();
	
}
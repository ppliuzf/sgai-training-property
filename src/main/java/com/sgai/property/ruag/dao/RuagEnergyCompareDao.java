package com.sgai.property.ruag.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ruag.entity.RuagEnergyCompare;

/**
 * 能耗对比DAO接口
 * @author admin
 * @version 2018-08-17
 */
@Mapper
public interface RuagEnergyCompareDao extends CrudDao<RuagEnergyCompare> {
	
	List<RuagEnergyCompare> findEnergyListById(RuagEnergyCompare ruagEnergyCompare);
	
}
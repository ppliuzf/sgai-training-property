package com.sgai.property.depot.dao;

import java.util.List;

import com.sgai.property.commonService.dao.MoreDataSourceDao;
import org.apache.ibatis.annotations.Mapper;

import com.sgai.property.depot.entity.WarehouseInOut;

@Mapper
public interface IWarehouseInOutExtDao extends MoreDataSourceDao<WarehouseInOut> {
	List<WarehouseInOut> findInOutList(WarehouseInOut inOut);
}

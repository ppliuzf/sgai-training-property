package com.sgai.property.depot.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.depot.entity.WarehouseAllot;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface IWarehouseAllotDao extends MoreDataSourceDao<WarehouseAllot> {
}
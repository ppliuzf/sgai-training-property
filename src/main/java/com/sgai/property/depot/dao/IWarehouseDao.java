package com.sgai.property.depot.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.depot.entity.Warehouse;
import com.sgai.property.depot.vo.InventoryManageVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IWarehouseDao extends MoreDataSourceDao<Warehouse> {

    List<InventoryManageVo> inventoryManages(InventoryManageVo manageVo);
}
package com.sgai.property.purchase.dao;

import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.purchase.entity.WarehouseMat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IWarehouseMatDao extends MoreDataSourceDao<WarehouseMat> {
    /**
     * 查询仓库表中物料信息
     *
     * @param mattypeId
     * @return
     */
//    List<WarehouseMat> getDepotWareHouse(String mattypeId);
    Long getDepotWareHouse(String mattypeId);
 
}
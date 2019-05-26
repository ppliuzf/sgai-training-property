package com.sgai.property.depot.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.depot.entity.InventoriesMat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IInventoriesMatDao extends MoreDataSourceDao<InventoriesMat> {

    /**
     * 根据盘点单号获取物料列表
     * @param inventoriesMat
     * @return
     */
    List<InventoriesMat> findMatListByivtNo(InventoriesMat inventoriesMat);

    /**
     * 根据盘点单获取物料列表
     * @param inventoriesMat
     * @return
     */
    List<InventoriesMat> findListByIvtNo(InventoriesMat inventoriesMat);

    /**
     * 根据盘点单号获取物料的个数
     * @param inventoriesMat
     * @return
     */
    Integer getRecordCount1(InventoriesMat inventoriesMat);

    /**
     * 根据条件查询条数
     * @param inventoriesMat
     * @return
     */
    Integer getRecordCount(InventoriesMat inventoriesMat);
}
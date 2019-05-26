package com.sgai.property.depot.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.depot.entity.Inventories;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IInventoriesDao extends MoreDataSourceDao<Inventories> {


    /**
     * 根据盘点单号查询仓库名称
     * @param inventories
     * @return
     */
    List<Inventories> findAllBhByivtNo(Inventories inventories);


    /**
     * 根据盘点单号查询id
     * @param inventories
     * @return
     */
    List<Inventories> findListByIvtNo(Inventories inventories);
}
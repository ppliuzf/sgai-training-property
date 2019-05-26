package com.sgai.property.depot.dao;

import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.depot.entity.Inventories;
import com.sgai.property.depot.entity.WarehouseAllot;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by 145811 on 2018/1/29.
 */
@Mapper
public interface IWarehouseAllotExtDao extends MoreDataSourceDao<Inventories> {
    List<WarehouseAllot> find4InOut(WarehouseAllot allot);
}

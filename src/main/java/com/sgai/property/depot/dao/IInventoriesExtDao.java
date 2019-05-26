package com.sgai.property.depot.dao;

import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.depot.entity.Inventories;
import com.sgai.property.depot.vo.InventoriesExt;
import com.sgai.property.depot.vo.InventoriesVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * Created by 145811 on 2018/1/25.
 */
@Mapper
public interface IInventoriesExtDao extends MoreDataSourceDao<Inventories> {
    List<Inventories> searchList(InventoriesExt inventoriesExt);
    Integer syncMat(Inventories inventories);
    int getcountUnIvt(Inventories ivt);
    int getcountMore(Inventories ivt);
    int getcountLess(Inventories ivt);
}

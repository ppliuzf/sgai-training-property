package com.sgai.property.depot.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.depot.entity.WarehousRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IWarehousRecordDao extends MoreDataSourceDao<WarehousRecord> {

    /**
     * 去重查询
     * @param record
     * @return
     */
    List<WarehousRecord> findListByIvtNo(WarehousRecord record);

    /**
     * 去重统计
     * @param warehousRecord
     * @return
     */
    Integer getRecordCount(WarehousRecord warehousRecord);
}
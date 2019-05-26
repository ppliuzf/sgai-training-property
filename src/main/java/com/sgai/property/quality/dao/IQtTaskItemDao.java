package com.sgai.property.quality.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.quality.entity.QtTaskItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IQtTaskItemDao extends MoreDataSourceDao<QtTaskItem>{
    /**
     * 批量插入检查项
     * @param taskItemList
     */
    void insertByBatch(List<QtTaskItem> taskItemList);
}
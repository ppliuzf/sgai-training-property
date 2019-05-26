package com.sgai.property.quality.dao;
import com.sgai.property.quality.entity.QtPlanItem;
import com.sgai.property.commonService.dao.MoreDataSourceDao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface IQtPlanItemDao extends MoreDataSourceDao<QtPlanItem>{
    /**
     * 获取组中最大的排序
     * @param sortQuery
     * @return
     */
    Integer getMaxSort(QtPlanItem sortQuery);
    
    List<QtPlanItem> getByTiIds(@Param(value = "tpId") String tpId, @Param(value = "tiIds") String[] tiIds);
    
    void insertBatch(List<QtPlanItem> qtPlanItem);

    /**
     * 根据模板ID 查询关联的任务项
     * @param entity
     * @return
     */
    List<QtPlanItem> findListByTemplateId(QtPlanItem entity);

    
}
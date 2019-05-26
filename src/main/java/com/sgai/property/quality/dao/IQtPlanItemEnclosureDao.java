package com.sgai.property.quality.dao;
import com.sgai.property.quality.entity.QtPlanItemEnclosure;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
@Mapper
public interface IQtPlanItemEnclosureDao extends MoreDataSourceDao<QtPlanItemEnclosure>{
    int batchInsert(List<QtPlanItemEnclosure> enclosures);
}
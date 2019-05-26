package com.sgai.property.quality.dao;
import com.sgai.property.quality.entity.QtDefectLabel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
@Mapper
public interface IQtDefectLabelDao extends MoreDataSourceDao<QtDefectLabel>{
    List<Map<String, Object>> getUserLabels(String feedId);

    int updateLabel(Map<String, Object> map);
}
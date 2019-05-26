package com.sgai.property.quality.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.quality.entity.JhDay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IJhDayDao extends MoreDataSourceDao<JhDay>{

    List<JhDay> findListByTestId(List<String> ids);
 
}
package com.sgai.property.quality.dao;

import com.sgai.property.quality.entity.JhWeek;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IJhWeekDao extends MoreDataSourceDao<JhWeek>{

    List<JhWeek> findListByTestId(List<String> ids);
 
}
package com.sgai.property.quality.dao;

import com.sgai.property.quality.entity.JhMonth;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IJhMonthDao extends MoreDataSourceDao<JhMonth>{

    List<JhMonth> findListByTestId(List<String> ids);
 
}
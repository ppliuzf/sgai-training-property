package com.sgai.property.customer.dao;

import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.customer.entity.CustomLevelInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ICustomLevelInfoDao extends MoreDataSourceDao<CustomLevelInfo> {


    List<CustomLevelInfo> findListTo(CustomLevelInfo customLevelInfo);
}
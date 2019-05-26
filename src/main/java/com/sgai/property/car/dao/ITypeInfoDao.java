package com.sgai.property.car.dao;
import com.sgai.property.car.entity.TypeInfo;
import com.sgai.property.commonService.dao.MoreDataSourceDao;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface ITypeInfoDao extends MoreDataSourceDao<TypeInfo>{
 
}
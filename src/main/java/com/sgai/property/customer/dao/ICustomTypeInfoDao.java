package com.sgai.property.customer.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.customer.entity.CustomTypeInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ICustomTypeInfoDao extends MoreDataSourceDao<CustomTypeInfo> {

    List<CustomTypeInfo> findAllListByTypeClass(CustomTypeInfo customTypeInfo);
 
}
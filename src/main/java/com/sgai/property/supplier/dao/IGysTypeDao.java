package com.sgai.property.supplier.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.supplier.entity.GysType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IGysTypeDao extends MoreDataSourceDao<GysType> {
    //获取供应商类型集合
    List<GysType> findGysTypeList(GysType gysType);
    //根据条件获取供应商类型数量
    int getListCount(GysType gysType);
}
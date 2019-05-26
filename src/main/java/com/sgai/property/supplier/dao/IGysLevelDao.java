package com.sgai.property.supplier.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.supplier.entity.GysContent;
import com.sgai.property.supplier.entity.GysLevel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IGysLevelDao extends MoreDataSourceDao<GysLevel> {
    //获取供应商等级集合
    List<GysLevel> findGysLevelList(GysLevel gysLevel);
    //根据条件获取供应商等级数量
    int getListCount(GysLevel gysLevel);
}
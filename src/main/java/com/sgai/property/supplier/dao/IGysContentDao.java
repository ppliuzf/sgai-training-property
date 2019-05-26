package com.sgai.property.supplier.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.supplier.entity.GysContent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IGysContentDao extends MoreDataSourceDao<GysContent> {
    //获取供应商内容集合
    List<GysContent> findGysContentList(GysContent gysContent);
    //根据条件获取供应商内容数量
    int getListCount(GysContent gysContent);
}
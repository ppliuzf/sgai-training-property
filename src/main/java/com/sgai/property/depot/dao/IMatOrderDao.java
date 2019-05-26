package com.sgai.property.depot.dao;

import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.depot.entity.MatOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IMatOrderDao extends MoreDataSourceDao<MatOrder> {

    List<MatOrder> findListInfo(MatOrder matOrder);
}
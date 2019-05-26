package com.sgai.property.customer.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.customer.entity.CustomCardInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ICustomCardInfoDao extends MoreDataSourceDao<CustomCardInfo> {

    List<CustomCardInfo> getByPrId(@Param("prId") String prId);

    int deleteByPrId(@Param("prId") String prId);
}
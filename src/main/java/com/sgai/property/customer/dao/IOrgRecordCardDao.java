package com.sgai.property.customer.dao;

import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.customer.entity.OrgRecordCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IOrgRecordCardDao extends MoreDataSourceDao<OrgRecordCard> {


    List<OrgRecordCard> findAllOrgRecordCardByOrId(@Param("orId") String orId);

    int deleteByOrId(@Param("orId") String orId);
 
}
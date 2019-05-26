package com.sgai.property.customer.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.customer.entity.OrgRecordInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IOrgRecordInfoDao extends MoreDataSourceDao<OrgRecordInfo> {
 
}
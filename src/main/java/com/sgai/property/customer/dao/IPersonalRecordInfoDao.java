package com.sgai.property.customer.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.customer.entity.PersonalRecordInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IPersonalRecordInfoDao extends MoreDataSourceDao<PersonalRecordInfo> {
 
}
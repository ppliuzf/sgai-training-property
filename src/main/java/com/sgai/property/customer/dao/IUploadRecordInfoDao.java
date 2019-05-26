package com.sgai.property.customer.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.customer.entity.UploadRecordInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUploadRecordInfoDao extends MoreDataSourceDao<UploadRecordInfo> {
 
}
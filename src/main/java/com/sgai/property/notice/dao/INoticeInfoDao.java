package com.sgai.property.notice.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.notice.entity.NoticeInfo;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface INoticeInfoDao extends MoreDataSourceDao<NoticeInfo> {
 
}
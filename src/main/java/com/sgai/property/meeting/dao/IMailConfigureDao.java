package com.sgai.property.meeting.dao;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.meeting.entity.MailConfigure;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IMailConfigureDao extends MoreDataSourceDao<MailConfigure> {
 
}
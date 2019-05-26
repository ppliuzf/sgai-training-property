package com.sgai.property.notice.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgai.property.notice.dao.INoticeInfoScopeDao;
import com.sgai.property.notice.entity.NoticeInfoScope;

@Service
public class NoticeInfoScopeServiceImpl extends MoreDataSourceCrudServiceImpl<INoticeInfoScopeDao,NoticeInfoScope>{
	@Autowired
	private INoticeInfoScopeDao noticeInfoScopeDao;

}
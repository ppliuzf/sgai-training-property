package com.sgai.property.notice.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgai.property.notice.dao.INoticeInfoTimePublishDao;
import com.sgai.property.notice.entity.NoticeInfoTimePublish;

@Service
public class NoticeInfoTimePublishServiceImpl extends MoreDataSourceCrudServiceImpl<INoticeInfoTimePublishDao,NoticeInfoTimePublish>{
	@Autowired
	private INoticeInfoTimePublishDao noticeInfoTimePublishDao;

}
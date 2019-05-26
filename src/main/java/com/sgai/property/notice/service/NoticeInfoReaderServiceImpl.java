package com.sgai.property.notice.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgai.property.notice.dao.INoticeInfoReaderDao;
import com.sgai.property.notice.entity.NoticeInfoReader;

@Service
public class NoticeInfoReaderServiceImpl extends MoreDataSourceCrudServiceImpl<INoticeInfoReaderDao,NoticeInfoReader>{
	@Autowired
	private INoticeInfoReaderDao noticeInfoReaderDao;

}
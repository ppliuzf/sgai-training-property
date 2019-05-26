package com.sgai.property.notice.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgai.property.notice.dao.INoticeInfoDao;
import com.sgai.property.notice.entity.NoticeInfo;

@Service
public class NoticeInfoServiceImpl extends MoreDataSourceCrudServiceImpl<INoticeInfoDao,NoticeInfo>{
	@Autowired
	private INoticeInfoDao noticeInfoDao;

}
package com.sgai.property.notice.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgai.property.notice.dao.INoticeInfoReceiptDao;
import com.sgai.property.notice.entity.NoticeInfoReceipt;

@Service
public class NoticeInfoReceiptServiceImpl extends MoreDataSourceCrudServiceImpl<INoticeInfoReceiptDao,NoticeInfoReceipt>{
	@Autowired
	private INoticeInfoReceiptDao noticeInfoReceiptDao;

}
package com.sgai.property.notice.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgai.property.notice.dao.INoticeInfoScopeEmpDao;
import com.sgai.property.notice.entity.NoticeInfoScopeEmp;

@Service
public class NoticeInfoScopeEmpServiceImpl extends MoreDataSourceCrudServiceImpl<INoticeInfoScopeEmpDao,NoticeInfoScopeEmp>{
	@Autowired
	private INoticeInfoScopeEmpDao noticeInfoScopeEmpDao;

}
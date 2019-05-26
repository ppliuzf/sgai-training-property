package com.sgai.property.meeting.service;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.meeting.dao.ISummaryPicDao;
import com.sgai.property.meeting.entity.SummaryPic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SummaryPicServiceImpl extends MoreDataSourceCrudServiceImpl<ISummaryPicDao,SummaryPic> {
	@Autowired
	private ISummaryPicDao summaryPicDao;

}
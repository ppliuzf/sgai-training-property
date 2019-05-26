package com.sgai.property.meeting.service;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.meeting.dao.ISummaryDao;
import com.sgai.property.meeting.entity.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SummaryServiceImpl extends MoreDataSourceCrudServiceImpl<ISummaryDao,Summary> {
	@Autowired
	private ISummaryDao summaryDao;

}
package com.sgai.property.customer.service;

import com.sgai.property.customer.dao.IOrgRecordCardDao;
import com.sgai.property.customer.entity.OrgRecordCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrgRecordCardServiceImpl extends MoreDataSourceCrudServiceImpl<IOrgRecordCardDao,OrgRecordCard>{
	@Autowired
	private IOrgRecordCardDao orgRecordCardDao;

}
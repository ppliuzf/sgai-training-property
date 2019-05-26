package com.sgai.property.customer.service;

import com.sgai.property.customer.dao.IOrgRecordLinkmanDao;
import com.sgai.property.customer.entity.OrgRecordLinkman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrgRecordLinkmanServiceImpl extends MoreDataSourceCrudServiceImpl<IOrgRecordLinkmanDao,OrgRecordLinkman>{
	@Autowired
	private IOrgRecordLinkmanDao orgRecordLinkmanDao;

}
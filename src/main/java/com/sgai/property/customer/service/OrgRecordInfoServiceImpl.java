package com.sgai.property.customer.service;

import com.sgai.property.customer.dao.IOrgRecordInfoDao;
import com.sgai.property.customer.entity.OrgRecordInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrgRecordInfoServiceImpl extends MoreDataSourceCrudServiceImpl<IOrgRecordInfoDao,OrgRecordInfo>{
	@Autowired
	private IOrgRecordInfoDao orgRecordInfoDao;

}
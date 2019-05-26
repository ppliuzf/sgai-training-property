package com.sgai.property.customer.service;

import com.sgai.property.customer.dao.IPersonalRecordInfoDao;
import com.sgai.property.customer.entity.PersonalRecordInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalRecordInfoServiceImpl extends MoreDataSourceCrudServiceImpl<IPersonalRecordInfoDao,PersonalRecordInfo>{
	@Autowired
	private IPersonalRecordInfoDao personalRecordInfoDao;

}
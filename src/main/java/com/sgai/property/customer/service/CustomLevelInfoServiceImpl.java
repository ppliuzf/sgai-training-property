package com.sgai.property.customer.service;

import com.sgai.property.customer.dao.ICustomLevelInfoDao;
import com.sgai.property.customer.entity.CustomLevelInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomLevelInfoServiceImpl extends MoreDataSourceCrudServiceImpl<ICustomLevelInfoDao,CustomLevelInfo>{
	@Autowired
	private ICustomLevelInfoDao customLevelInfoDao;

}
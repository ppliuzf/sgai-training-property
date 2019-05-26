package com.sgai.property.customer.service;

import com.sgai.property.customer.dao.ICustomTypeInfoDao;
import com.sgai.property.customer.entity.CustomTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomTypeInfoServiceImpl extends MoreDataSourceCrudServiceImpl<ICustomTypeInfoDao,CustomTypeInfo>{
	@Autowired
	private ICustomTypeInfoDao customTypeInfoDao;

}
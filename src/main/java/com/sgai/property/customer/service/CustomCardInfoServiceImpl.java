package com.sgai.property.customer.service;

import com.sgai.property.customer.dao.ICustomCardInfoDao;
import com.sgai.property.customer.entity.CustomCardInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomCardInfoServiceImpl extends MoreDataSourceCrudServiceImpl<ICustomCardInfoDao,CustomCardInfo>{
	@Autowired
	private ICustomCardInfoDao customCardInfoDao;

}
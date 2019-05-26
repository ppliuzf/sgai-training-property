package com.sgai.property.contract.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgai.property.contract.dao.ICHtTypeDao;
import com.sgai.property.contract.entity.HtType;

@Service
public class HtTypeServiceImpl extends MoreDataSourceCrudServiceImpl<ICHtTypeDao,HtType>{
	@Autowired
	private ICHtTypeDao htTypeDao;

}
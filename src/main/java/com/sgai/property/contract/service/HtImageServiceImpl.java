package com.sgai.property.contract.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgai.property.contract.dao.IHtImageDao;
import com.sgai.property.contract.entity.HtImage;

@Service
public class HtImageServiceImpl extends MoreDataSourceCrudServiceImpl<IHtImageDao,HtImage>{
	@Autowired
	private IHtImageDao htImageDao;

}
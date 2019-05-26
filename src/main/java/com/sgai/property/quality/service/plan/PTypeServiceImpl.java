package com.sgai.property.quality.service.plan;


import com.sgai.property.quality.dao.plan.ITypeDao;
import com.sgai.property.quality.entity.plan.Type;
import com.sgai.property.quality.service.MoreDataSourceCrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PTypeServiceImpl extends MoreDataSourceCrudServiceImpl<ITypeDao,Type> {
	@Autowired
	private ITypeDao typeDao;

}
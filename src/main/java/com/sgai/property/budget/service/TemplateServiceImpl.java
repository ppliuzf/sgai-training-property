package com.sgai.property.budget.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgai.property.budget.dao.ITemplateDao;
import com.sgai.property.budget.entity.Template;

@Service
public class TemplateServiceImpl extends MoreDataSourceCrudServiceImpl<ITemplateDao,Template>{
	@Autowired
	private ITemplateDao templateDao;

}
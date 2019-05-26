package com.sgai.property.budget.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgai.property.budget.dao.ITemplateSubjectItemDao;
import com.sgai.property.budget.entity.TemplateSubjectItem;

@Service
public class TemplateSubjectItemServiceImpl extends MoreDataSourceCrudServiceImpl<ITemplateSubjectItemDao,TemplateSubjectItem>{
	@Autowired
	private ITemplateSubjectItemDao templateSubjectItemDao;

}
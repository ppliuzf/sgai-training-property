package com.sgai.property.budget.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgai.property.budget.dao.IRecordTemplateDao;
import com.sgai.property.budget.entity.RecordTemplate;

@Service
public class RecordTemplateServiceImpl extends MoreDataSourceCrudServiceImpl<IRecordTemplateDao,RecordTemplate>{
	@Autowired
	private IRecordTemplateDao recordTemplateDao;

}
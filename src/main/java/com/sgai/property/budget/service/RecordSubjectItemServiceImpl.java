package com.sgai.property.budget.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgai.property.budget.dao.IRecordSubjectItemDao;
import com.sgai.property.budget.entity.RecordSubjectItem;

@Service
public class RecordSubjectItemServiceImpl extends MoreDataSourceCrudServiceImpl<IRecordSubjectItemDao,RecordSubjectItem>{
	@Autowired
	private IRecordSubjectItemDao recordSubjectItemDao;

}
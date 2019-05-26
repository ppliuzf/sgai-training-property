package com.sgai.property.budget.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgai.property.budget.dao.ISubjectDao;
import com.sgai.property.budget.entity.Subject;

@Service
public class SubjectServiceImpl extends MoreDataSourceCrudServiceImpl<ISubjectDao,Subject>{
	@Autowired
	private ISubjectDao subjectDao;

}
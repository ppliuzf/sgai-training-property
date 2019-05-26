package com.sgai.property.budget.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgai.property.budget.dao.IInputDataDao;
import com.sgai.property.budget.entity.InputData;

@Service
public class InputDataServiceImpl extends MoreDataSourceCrudServiceImpl<IInputDataDao,InputData>{
	@Autowired
	private IInputDataDao inputDataDao;

}
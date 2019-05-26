package com.sgai.property.budget.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgai.property.budget.dao.IInputDao;
import com.sgai.property.budget.entity.Input;

@Service
public class InputServiceImpl extends MoreDataSourceCrudServiceImpl<IInputDao,Input>{
	@Autowired
	private IInputDao inputDao;

}
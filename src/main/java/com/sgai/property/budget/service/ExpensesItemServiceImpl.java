package com.sgai.property.budget.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgai.property.budget.dao.IExpensesItemDao;
import com.sgai.property.budget.entity.ExpensesItem;

@Service
public class ExpensesItemServiceImpl extends MoreDataSourceCrudServiceImpl<IExpensesItemDao,ExpensesItem>{
	@Autowired
	private IExpensesItemDao expensesItemDao;

}
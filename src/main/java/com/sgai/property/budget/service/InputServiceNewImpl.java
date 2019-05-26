package com.sgai.property.budget.service;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.budget.dao.IInputDao;
import com.sgai.property.budget.entity.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InputServiceNewImpl extends MoreDataSourceCrudServiceImpl<IInputDao,Input>{
	@Autowired
	private IInputDao inputDao;

	public Page<Input> getListPage(Input input, Integer pageNum,
								   Integer pageSize) {
		Page<Input> page =new Page<Input>(pageNum,pageSize);
		page.setOrderBy("create_time DESC");
		input.setPage(page);
		
		input.setComCode(UserServletContext.getUserInfo().getComCode());
		input.setModuCode(UserServletContext.getUserInfo().getModuCode());
		page.setList(inputDao.findList(input));
		return page;
	}

}
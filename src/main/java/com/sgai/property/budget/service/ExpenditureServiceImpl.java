package com.sgai.property.budget.service;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.budget.dao.IExpensesItemDao;
import com.sgai.property.budget.entity.ExpensesItem;
import com.sgai.property.budget.entity.TemplateSubjectItem;
import com.sgai.property.budget.vo.ExpensesItemParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class ExpenditureServiceImpl extends MoreDataSourceCrudServiceImpl<IExpensesItemDao,ExpensesItem>{
	@Autowired
	private IExpensesItemDao expensesItemDao;
	@Autowired
	private TemplateSubjectItemServiceImpl templateSubjectItemService;

	public Page<ExpensesItem> getListPage(ExpensesItem expensesItem,
										  int pageNum, int pageSize) {
		Page<ExpensesItem> page = new Page<ExpensesItem>(pageNum, pageSize);
		page.setOrderBy("create_time");
		
		expensesItem.setIsDelete(0L);
		expensesItem.setPage(page);
		
		expensesItem.setComCode(UserServletContext.getUserInfo().getComCode());
		expensesItem.setModuCode(UserServletContext.getUserInfo().getModuCode());
		
		page.setList(expensesItemDao.findList(expensesItem));
		return page;
	}

	public ExpensesItem saveExpensesItem(ExpensesItemParam param) {
		
		ExpensesItem item =new ExpensesItem();
		BeanUtils.copyProperties(param, item);
		
		if (StringUtils.isEmpty(item.getId())) {
			String hexStr = String.format("%08X", System.currentTimeMillis()/1000);
			item.setItemCode(hexStr);
			item.setOrgId(UserServletContext.getUserInfo().getComCode());
			item.setState(0L);
			item.setIsDelete(0L);
			item.setCreateTime(System.currentTimeMillis());

			item.setComCode(UserServletContext.getUserInfo().getComCode());
			item.setModuCode(UserServletContext.getUserInfo().getModuCode());
			save(item);
		}else {
			item.setUpdateTime(System.currentTimeMillis());
			expensesItemDao.updateById(item);
		}
		
		return item;
	}

	public Boolean deleteExpensesItem(String id) throws Exception {
		
		ExpensesItem item =getById(id);
		if (item==null) {
			throw new Exception("费项不存在！");
		}
		
		//判断费项是否已关联
		TemplateSubjectItem templateSubjectItem =new TemplateSubjectItem();
		templateSubjectItem.setItemId(id);
		List<TemplateSubjectItem> templateSubjectItems=templateSubjectItemService.findList(templateSubjectItem);
		if (templateSubjectItems.size()>0) {
			throw new Exception("费项已经关联数据不能删除！");
		}
		
		item.setUpdateTime(System.currentTimeMillis());
		item.setIsDelete(1L);
		expensesItemDao.updateById(item);
		
		return true;
	}

	public Boolean updateState(String id, long state) throws Exception {
		
		ExpensesItem item =getById(id);
		if (item==null) {
			throw new Exception();
		}
		item.setUpdateTime(System.currentTimeMillis());
		item.setState(state);
		
		return updateById(item);
	}

	public List<ExpensesItem> getList() {
		ExpensesItem item =new ExpensesItem();
		item.setIsDelete(0L);
		item.setState(0L);

		item.setComCode(UserServletContext.getUserInfo().getComCode());
		item.setModuCode(UserServletContext.getUserInfo().getModuCode());
		return expensesItemDao.findList(item);
	}

}
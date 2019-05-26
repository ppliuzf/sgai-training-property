package com.sgai.property.budget.web;

import com.sgai.common.persistence.Page;
import com.sgai.property.budget.entity.ExpensesItem;
import com.sgai.property.budget.service.ExpenditureServiceImpl;
import com.sgai.property.budget.vo.ExpensesItemParam;
import com.sgai.property.common.web.BaseController;
import com.sgai.property.commonService.vo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *@author 严浩淼
 *@date 2018年1月17日--下午2:08:47
 */
@Api(description="费项管理")
@RestController
@RequestMapping(value="/expensesItem")
public class ExpensesItemController extends BaseController {

	@Autowired
	private ExpenditureServiceImpl expenditureService;
	
	@ApiOperation(value="分页获取费项列表",httpMethod="POST",notes="分页获取费项列表")
	@PostMapping(value="getPageList")
	public Response<Page<ExpensesItem>> getPageList(
			int pageNum,int pageSize) {
		Response<Page<ExpensesItem>> result =new Response<Page<ExpensesItem>>();
		try {

			Page<ExpensesItem> page =expenditureService.getListPage(new ExpensesItem(), pageNum, pageSize);
			result.setData(page);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("-100");
			result.setMessage("操作数据库异常！");
		}
		return result;
	}
	
	@ApiOperation(value="获取费项列表",httpMethod="POST",notes="获取费项列表")
	@PostMapping(value="getList")
	public Response<List<ExpensesItem>> getList() {
		Response<List<ExpensesItem>> result =new Response<List<ExpensesItem>>();
		try {

			List<ExpensesItem> page =expenditureService.getList();
			result.setData(page);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("-100");
			result.setMessage("操作数据库异常！");
		}
		return result;
	}
	
	@ApiOperation(value="获取费项详情",httpMethod="POST",notes="获取费项详情")
	@PostMapping(value="getById")
	public Response<ExpensesItem> getById(String id) {
		Response<ExpensesItem> result =new Response<ExpensesItem>();
		try {
			ExpensesItem expensesItem =expenditureService.getById(id);
			result.setData(expensesItem);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("-100");
			result.setMessage("操作数据库异常！");
		}
		return result;
	}
	
	@ApiOperation(value="保存费项",httpMethod="POST",notes="保存费项")
	@PostMapping(value="save")
	public Response<ExpensesItem> save(@RequestBody ExpensesItemParam param) {
		Response<ExpensesItem> result =new Response<ExpensesItem>();
		try {
			 if (StringUtils.isEmpty(param.getItemName())) {
				throw new Exception("费项名称不能为空");
			}

			ExpensesItem expensesItem =expenditureService.saveExpensesItem(param);
			result.setData(expensesItem);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode("-100");
			result.setMessage("操作数据库异常！");
		}
		return result;
	}
	
	@ApiOperation(value="删除费项",httpMethod="POST",notes="删除费项")
	@PostMapping(value="deleteById")
	public Response<Boolean> deleteById(String id) {
		Response<Boolean> result = new Response<Boolean>();
		try {

			result.setData(expenditureService.deleteExpensesItem(id));
		} catch (Exception e) {
			e.printStackTrace();
			result.setData(false);
			result.setCode("-100");
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
}

package com.sgai.property.budget.service;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.budget.dao.ISubjectDao;
import com.sgai.property.budget.dao.ITemplateDao;
import com.sgai.property.budget.entity.ExpensesItem;
import com.sgai.property.budget.entity.Subject;
import com.sgai.property.budget.entity.Template;
import com.sgai.property.budget.entity.TemplateSubjectItem;
import com.sgai.property.budget.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class TemplateNewServiceImpl extends MoreDataSourceCrudServiceImpl<ITemplateDao,Template>{
	@Autowired
	private ITemplateDao templateDao;
	@Autowired
	private ISubjectDao subjectDao;
	@Autowired
	private TemplateSubjectItemServiceImpl templateSubjectItemService;

	public Page<Template> getPageList(Integer pageNum, Integer pageSize) {
		
		Page<Template> page = new Page<Template>(pageNum,pageSize);
		page.setOrderBy("create_time");
		
		Template template =new Template();
		template.setIsDelete(0L);
		template.setPage(page);
		

		template.setComCode(UserServletContext.getUserInfo().getComCode());
		template.setModuCode(UserServletContext.getUserInfo().getModuCode());
		
		page.setList(templateDao.findList(template));
		return page;
	}

	public Template saveTemplate(TemplateParam param) {
		Template template =new Template();
		BeanUtils.copyProperties(param, template);
		template.setOrgId(UserServletContext.getUserInfo().getComCode());
		template.setCreatorEiId(UserServletContext.getUserInfo().getUserNo());
		template.setCreatorEiEmpName(UserServletContext.getUserInfo().getUserName());
		template.setState(0L);
		template.setIsDelete(0L);
		template.setCreateTime(System.currentTimeMillis());
		

		template.setComCode(UserServletContext.getUserInfo().getComCode());
		template.setModuCode(UserServletContext.getUserInfo().getModuCode());
		save(template);
		return template;
	}

	public TemplateVo getDetailById(String id) {
		TemplateVo templateVo =new TemplateVo();
		
		Template template =templateDao.getById(id);
		if (template!=null) {
			BeanUtils.copyProperties(template, templateVo);
			TemplateSubjectItem templateSubjectItem =new TemplateSubjectItem();
			templateSubjectItem.setTemplateId(template.getId());
			Page<TemplateSubjectItem> page=new Page<TemplateSubjectItem>();
			page.setOrderBy("item_order asc");
			templateVo.setSubjectItemList(templateSubjectItemService.findList(templateSubjectItem));
		}
		
		return templateVo;
	}

	public Boolean updateState(long state, String id) throws Exception{
		
		Template template =templateDao.getById(id);
		if (template==null) {
			throw new Exception("模版id不存在！");
		}
		template.setState(state);
		template.setUpdateTime(System.currentTimeMillis());

		return updateById(template);
	}

	public List<SubjectItemsVo> getSubjectItemList(String templateId) {
		List<SubjectItemsVo> subjectItemsVos =new ArrayList<SubjectItemsVo>();
		
		//获取所有科目费项关联信息
		TemplateSubjectItem templateSubjectItem =new TemplateSubjectItem();
		templateSubjectItem.setTemplateId(templateId);
		List<TemplateSubjectItem> templateSubjectItems =templateSubjectItemService.findList(templateSubjectItem);
		
		//获取所有科目列表
		Subject subject =new Subject();
		subject.setIsDelete(0L);
		Page<Subject> page =new Page<Subject>();
		page.setOrderBy("create_time DESC");
		subject.setPage(page);
		
		subject.setComCode(UserServletContext.getUserInfo().getComCode());
		subject.setModuCode(UserServletContext.getUserInfo().getModuCode());
		List<Subject> subjects =subjectDao.findList(subject);
		List<SubjectItemsVo> allSubjectItemList =new ArrayList<SubjectItemsVo>();
		for (Subject sub : subjects) {
			SubjectItemsVo vo =new SubjectItemsVo();
			BeanUtils.copyProperties(sub, vo);
			allSubjectItemList.add(vo);
		}

		//获取一级科目
		for (SubjectItemsVo rootSubjectVo : allSubjectItemList) {
			if (!StringUtils.isEmpty(rootSubjectVo.getParentId()) && rootSubjectVo.getParentId().equals("-1")) {
				//获取费项
				List<ExpensesItem> itemList =getExpensesItemList(rootSubjectVo,templateSubjectItems);
				rootSubjectVo.setItemList(itemList);
				
				subjectItemsVos.add(rootSubjectVo);
			}
		}
		//为一级科目设置子节点
		for (SubjectItemsVo subjectItemsVo : subjectItemsVos) {
			subjectItemsVo.setChildSubjectList(getChildSubjects(subjectItemsVo.getId(),allSubjectItemList,templateSubjectItems));
		}
		
		return subjectItemsVos;
	}
	
	/*
	 * 递归获取子科目
	 */
	private List<SubjectItemsVo> getChildSubjects(String id,List<SubjectItemsVo> allSubjectList,List<TemplateSubjectItem> templateSubjectItems) {
		// 子科目
		List<SubjectItemsVo> childSubjectList =new ArrayList<SubjectItemsVo>();
		for (SubjectItemsVo subjectItemsVo : allSubjectList) {
			if (id.equals(subjectItemsVo.getParentId())) {
				//获取费项
				List<ExpensesItem> itemList =getExpensesItemList(subjectItemsVo,templateSubjectItems);
				subjectItemsVo.setItemList(itemList);
				
				childSubjectList.add(subjectItemsVo);
			}
		}
		// 把子科目的子科目再循环一遍
		for (SubjectItemsVo subjectItemsVo : childSubjectList) {
			subjectItemsVo.setChildSubjectList(getChildSubjects(subjectItemsVo.getId(),allSubjectList,templateSubjectItems));
		}
		if (childSubjectList.size()==0) {
			return new ArrayList<SubjectItemsVo>() ;
		}
		return childSubjectList;
	}
	
	/*
	 * 获取费项列表
	 */
	public List<ExpensesItem> getExpensesItemList(SubjectItemsVo subjectItemsVo,List<TemplateSubjectItem> templateSubjectItems) {
		List<ExpensesItem> itemList =new ArrayList<ExpensesItem>();
		for (TemplateSubjectItem item : templateSubjectItems) {
			if (subjectItemsVo.getId().equals(item.getSubId())) {
				ExpensesItem expensesItem =new ExpensesItem();
				expensesItem.setId(item.getItemId());
				expensesItem.setItemName(item.getItemName());
				itemList.add(expensesItem);
			}
		}
		return itemList;
	}

	public Boolean saveRelationData(RelationDataParam param) throws Exception{
		
		Template template =templateDao.getById(param.getTemplateId());
		if (template==null) {
			throw new Exception("模版信息不存在！");
		}
		
		//删除模版旧的关联数据
		TemplateSubjectItem templateSubjectItem =new TemplateSubjectItem();
		templateSubjectItem.setTemplateId(param.getTemplateId());
		templateSubjectItemService.delete(templateSubjectItem);

		//保存关联数据
		List<SubjectItemsRelationVo> subjectItemVos=param.getSubjectItems();
		int order=1;
		for (SubjectItemsRelationVo subjectItemVo : subjectItemVos) {
			Subject subject = subjectDao.getById(subjectItemVo.getSubId());
			
			TemplateSubjectItem relation =new TemplateSubjectItem();
			BeanUtils.copyProperties(subjectItemVo, relation);

			relation.setSubLongName(subject.getLongName());
			relation.setTemplateId(template.getId());
			relation.setTemplateName(template.getTemplateName());
			relation.setComCode(UserServletContext.getUserInfo().getComCode());
			relation.setModuCode(UserServletContext.getUserInfo().getModuCode());
			
			//遍历科目下的费项
			if (subjectItemVo.getItemList()!=null  && subjectItemVo.getItemList().size()>0) {
				for (ExpensesItemVo expensesItemVo : subjectItemVo.getItemList()) {
					relation.setItemId(expensesItemVo.getItemId());
					relation.setItemName(expensesItemVo.getItemName());
					relation.setItemOrder(Long.valueOf(order));
					relation.setCreateTime(System.currentTimeMillis());
					templateSubjectItemService.save(relation);
					relation.setId("");
					order++;
				}
			}
		}
		return true;
	}
	
}
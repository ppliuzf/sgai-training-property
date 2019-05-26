package com.sgai.property.budget.dao;
import java.util.List;

import com.sgai.property.commonService.dao.MoreDataSourceDao;
import org.apache.ibatis.annotations.Mapper;

import com.sgai.property.budget.entity.TemplateSubjectItem;
@Mapper
public interface ITemplateSubjectItemDaoVo extends MoreDataSourceDao<TemplateSubjectItem> {

	List<String> getTemplateIds();
}
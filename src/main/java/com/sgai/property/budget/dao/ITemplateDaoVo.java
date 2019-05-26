package com.sgai.property.budget.dao;
import java.util.List;
import java.util.Map;

import com.sgai.property.commonService.dao.MoreDataSourceDao;
import org.apache.ibatis.annotations.Mapper;

import com.sgai.property.budget.entity.Template;
@Mapper
public interface ITemplateDaoVo extends MoreDataSourceDao<Template> {

	List<Template> getTemplateByCycle(Map<String, Object> map);
}
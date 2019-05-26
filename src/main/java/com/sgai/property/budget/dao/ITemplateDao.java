package com.sgai.property.budget.dao;
import com.sgai.property.budget.entity.Template;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface ITemplateDao extends MoreDataSourceDao<Template> {
 
}
package com.sgai.property.ctl.dao;

import com.sgai.common.persistence.CrudDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.sgai.property.ctl.entity.CtlComRule;

/**
 * user关联rule队列DAO接口
 * @author chenxing
 * @version 2017-11-20
 */
@Mapper
public interface CtlComRuleDao extends CrudDao<CtlComRule> {
	void batchDelete(List<String> idList);
	List<Map<String,Object>> getComList();
	List<Map<String,Object>> getRuleList();
	CtlComRule getComRule(CtlComRule param);
}
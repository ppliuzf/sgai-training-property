package com.sgai.property.ctl.dao;

import com.sgai.common.persistence.CrudDao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.sgai.property.ctl.entity.CtlRuleItem;

/**
 * 编码规则DAO接口
 * @author chenxing
 * @version 2017-11-20
 */
@Mapper
public interface CtlRuleItemDao extends CrudDao<CtlRuleItem> {
	List<CtlRuleItem> getRuleItemList(CtlRuleItem ctlRuleItem);
	void batchDelete(List<String> ruleInfoIdList);
}
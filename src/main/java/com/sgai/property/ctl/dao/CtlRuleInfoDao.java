package com.sgai.property.ctl.dao;

import com.sgai.common.persistence.CrudDao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.sgai.property.ctl.entity.CtlRuleInfo;

/**
 * 编码规则DAO接口
 * @author chenxing
 * @version 2017-11-20
 */
@Mapper
public interface CtlRuleInfoDao extends CrudDao<CtlRuleInfo> {
	CtlRuleInfo getRuleInfo(CtlRuleInfo param);
	void batchDelete(List<String> idList);
	Integer countSequCodeExceptSelf(CtlRuleInfo entity);
}
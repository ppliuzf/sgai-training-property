package com.sgai.property.ruag.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ruag.entity.RuagLinkageDeviceParamSet;
import com.sgai.property.ruag.entity.RuagLinkageRule;

/**
 * 联动规则定义DAO接口
 * @author yangyz
 * @version 2018-01-02
 */
@Mapper
public interface RuagLinkageRuleDao extends CrudDao<RuagLinkageRule> {
	
	RuagLinkageRule getLinkageCode(RuagLinkageRule ruagLinkageRule);
	
	List<RuagLinkageRule>findByName(RuagLinkageRule ruagLinkageRule);
	
	List<RuagLinkageRule> findAllOfList(RuagLinkageRule ruagLinkageRule);
	
}
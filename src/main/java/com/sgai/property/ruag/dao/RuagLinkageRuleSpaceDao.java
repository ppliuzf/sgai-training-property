package com.sgai.property.ruag.dao;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ruag.entity.RuagLinkageRule;
import com.sgai.property.ruag.entity.RuagLinkageRuleSpace;

/**
 * 
    * @ClassName: RuagLinkageRuleSpaceDao  
    * @com.sgai.property.commonService.vo;(联动规则与位置的关系DAO接口)
    * @author 王天尧  
    * @date 2018年4月4日  
    * @Company 首自信--智慧城市创新中心
 */
@Mapper
public interface RuagLinkageRuleSpaceDao extends CrudDao<RuagLinkageRuleSpace> {
	
	List<RuagLinkageRuleSpace> findAllOfList(RuagLinkageRuleSpace ruagLinkageRuleSpace);
}
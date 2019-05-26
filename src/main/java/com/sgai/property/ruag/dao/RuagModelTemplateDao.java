package com.sgai.property.ruag.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ruag.entity.RuagModelDeviceParamerSet;
import com.sgai.property.ruag.entity.RuagModelTemplate;

/**
 * 
    * @ClassName: RuagModelTemplateDao  
    * @com.sgai.property.commonService.vo;(运行模式定义DAO接口)
    * @author 王天尧
    * @date 2018年1月2日  
    * @Company 首自信--智慧城市创新中心
 */
@Mapper
public interface RuagModelTemplateDao extends CrudDao<RuagModelTemplate> {
	/**
	 * 
	    * @Title: findByName  
	    * @com.sgai.property.commonService.vo;(根据名称查询运行策略)
	    * @param @param ruagModelTemplate
	    * @param @return    参数  
	    * @return RuagModelTemplate    返回类型  
	    * @throws
	 */
	RuagModelTemplate findByName(RuagModelTemplate ruagModelTemplate);
	
	List<RuagModelTemplate> findAllOfList(RuagModelTemplate ruagModelTemplate);
}
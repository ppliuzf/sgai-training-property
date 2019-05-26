package com.sgai.property.alm.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.alm.entity.AlmAlermClass;

/**
 * 
    * ClassName: AlmAlermClassDao  
    * com.sgai.property.commonService.vo;(报警分类DAO接口)
    * @author 王天尧 
    * Date 2017年11月26日  
    * Company 首自信--智慧城市创新中心
 */
@Mapper
public interface AlmAlermClassDao extends CrudDao<AlmAlermClass> {
	
}
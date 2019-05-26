package com.sgai.property.alm.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.alm.entity.AlmDeviceLevelRelation;

/**
 * 
    * ClassName: AlmDeviceLevelRelationDao  
    * com.sgai.property.commonService.vo;(设备与报警等级关系DAO接口)
    * @author 王天尧 
    * Date 2017年11月26日  
    * Company 首自信--智慧城市创新中心
 */
@Mapper
public interface AlmDeviceLevelRelationDao extends CrudDao<AlmDeviceLevelRelation> {
	
}
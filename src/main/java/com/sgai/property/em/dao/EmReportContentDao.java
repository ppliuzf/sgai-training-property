package com.sgai.property.em.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.em.entity.EmReportContent;


/**
 * 
    * @ClassName: EmReportContentDao  
    * @com.sgai.property.commonService.vo;(报案内容DAO接口)
    * @author LiuYang  
    * @date 2017年12月5日  
    * @Company 首自信--智慧城市创新中心
 */
@Mapper
public interface EmReportContentDao extends CrudDao<EmReportContent> {
	
}
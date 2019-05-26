package com.sgai.property.em.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.em.entity.EmEnded;


/**
 * 
    * @ClassName: EmEndedDao  
    * @com.sgai.property.commonService.vo;(事件终止DAO接口)
    * @author LiuYang  
    * @date 2017年12月5日  
    * @Company 首自信--智慧城市创新中心
 */
@Mapper
public interface EmEndedDao extends CrudDao<EmEnded> {
	/**
	 * 
	 * getByCode:(根据编码查找事件).
	 * @param emEnded
	 * @return :EmEnded 
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	EmEnded getByCode(EmEnded emEnded); 
}
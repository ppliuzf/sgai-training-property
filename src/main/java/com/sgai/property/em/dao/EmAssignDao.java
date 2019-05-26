package com.sgai.property.em.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.em.entity.EmAssign;

/**
 * 
    * @ClassName: EmAssignDao  
    * @com.sgai.property.commonService.vo;(事件指派DAO接口)
    * @author LiuYang  
    * @date 2017年12月5日  
    * @Company 首自信--智慧城市创新中心
 */
@Mapper
public interface EmAssignDao extends CrudDao<EmAssign> {
	
	/**
	 * 
	 * getAssignByCode:(查询指派数据).
	 * @param emAssign
	 * @return :EmAssign 
	 * @since JDK 1.8
	 * @author yangyz
	 */
    EmAssign getAssignByCode(EmAssign emAssign);
}
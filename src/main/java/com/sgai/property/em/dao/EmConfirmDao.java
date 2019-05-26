package com.sgai.property.em.dao;

import org.apache.ibatis.annotations.Mapper;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.em.entity.EmConfirm;


/**
 * 
    * @ClassName: EmConfirmDao  
    * @com.sgai.property.commonService.vo;(事件核实DAO接口)
    * @author LiuYang  
    * @date 2017年12月5日  
    * @Company 首自信--智慧城市创新中心
 */
@Mapper
public interface EmConfirmDao extends CrudDao<EmConfirm> {
	/**
	 * 
	 * getByCode:(根据事件编码查找事件).
	 * @param emConfirm
	 * @return :EmConfirm 
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	EmConfirm getByCode(EmConfirm emConfirm);
}
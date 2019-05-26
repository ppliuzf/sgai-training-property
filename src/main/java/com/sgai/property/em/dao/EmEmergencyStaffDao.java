package com.sgai.property.em.dao;

import org.apache.ibatis.annotations.Mapper;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.em.entity.EmEmergencyStaff;

/**
 * 
    * ClassName: EmEmergencyStaffDao  
    * com.sgai.property.commonService.vo;(应急人员表DAO接口)
    * @author yangyz  
    * Date 2017年12月14日  
    * Company 首自信--智慧城市创新中心
 */
@Mapper
public interface EmEmergencyStaffDao extends CrudDao<EmEmergencyStaff> {
	
}
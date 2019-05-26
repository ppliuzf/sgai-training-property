package com.sgai.property.ctl.dao;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlRole;

/**
 * 功能维护DAO接口
 * @author wangty
 * @version 2017-11-09
 */
@Mapper
public interface CtlRoleDao extends CrudDao<CtlRole> {

	/**
	 * 
	 * findListAdd:(添加时验证唯一性).
	 * @param ctlRole
	 * @return :List<CtlUser> 
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	List<CtlRole> findListAdd(CtlRole ctlRole);
	
	List<CtlRole>  findRoleForEventByDeptCode(CtlRole role);
	
}
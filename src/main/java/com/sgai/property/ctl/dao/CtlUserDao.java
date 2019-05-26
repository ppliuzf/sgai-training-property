package com.sgai.property.ctl.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.entity.CtlRole;
import com.sgai.property.ctl.entity.CtlUser;


/**
 * 定义系统的登录用户DAO接口
 * @author admin
 * @version 2017-11-11
 */
@Mapper
public interface CtlUserDao extends CrudDao<CtlUser> {
	CtlUser  getUserByLoginName(String userCode);
	List<CtlUser> getUserBytype();
	//public List<Map<String,String>>  getIndexMenus(String userCode,String userType);
	
	/**
	 * 
	 * findNextNodeUserList:(根据事件类型、节点级别查询User).
	 * @param flowCode
	 * @param stepPos
	 * @return :List<CtlUser> 
	 * @since JDK 1.8
	 * @author yangyz
	 */
    List<CtlUser> findNextNodeUserList(Map<String, String> map);
	
	/**
	 * findUserForEventByDeptCode:(事件页面 根据机构编码获得本机构一下所有的用户).
	 * @param deptCode
	 * @return :CtlUser 
	 * @since JDK 1.8
	 * @author admin
	 */
    List<CtlUser>  findUserForEventByDeptCode(CtlUser user);
	
	List<CtlUser> findMList(CtlUser user);
	
	List<CtlUser> findGTList(CtlUser user);
	
	
	
}
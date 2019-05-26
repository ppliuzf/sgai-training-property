package com.sgai.property.ctl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.dao.CtlRoleDao;
import com.sgai.property.ctl.dao.CtlUserDao;
import com.sgai.property.ctl.dao.CtlUserRoleDao;
import com.sgai.property.ctl.entity.CtlRole;
import com.sgai.property.ctl.entity.CtlUser;
import com.sgai.property.ctl.entity.CtlUserRole;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
/**
 *
    * ClassName: CtlRoleService
    * Description: (角色管理业务层)
    * @author 王天尧
    * Date 2017年11月18日
    * Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class CtlRoleService extends CrudServiceExt<CtlRoleDao, CtlRole> {
	@Autowired
	private CtlUserRoleDao ctlUserRoleDao;
	@Autowired
	private CtlUserDao ctlUserDao;
	@Autowired
	private DeleteRulesUtils deleteRulesUtils;
	public CtlRole get(String id) {
		return super.get(id);
	}

	public List<CtlRole> findList(CtlRole ctlRole) {
		return super.findList(ctlRole);
	}

	public Page<CtlRole> findPage(Page<CtlRole> page, CtlRole ctlRole) {
		return super.findPage(page, ctlRole);
	}

	@Transactional(readOnly = false)
	public void save(CtlRole ctlRole) {
		super.save(ctlRole);
	}

	@Transactional(readOnly = false)
	public void delete(CtlRole ctlRole) {
		super.delete(ctlRole);
	}

	@Transactional(readOnly = false)
	public List<CtlRole>  findRoleForEventByDeptCode(CtlRole role) {
		return dao.findRoleForEventByDeptCode(role);
	}

	/**
	 *
	 * saveRole:(保存角色，包括插入和更新).
	 * @param map
	 * @param roleCode
	 * @param ctlRole
	 * @return :Map<String,String>
	 * @since JDK 1.8
	 * @author ASUS
	 */
	@Transactional(readOnly = false)
	public Map<String,String> saveRole(Map<String,String> map,String roleCode,CtlRole ctlRole){
		//获取当前登录用户
		LoginUser userInfo = UserServletContext.getUserInfo();
		ctlRole.setEnabledFlag("Y");
		//通过判断对象id是否为空判断是执行插入方法还是更新方法
		if(ctlRole.getId().equals("")) {
			//通过机构代码和角色代码判断唯一性
			CtlRole ctlRoleNew=new CtlRole();
			ctlRoleNew.setComCode(userInfo.getComCode());
			ctlRoleNew.setRoleCode(roleCode);
			List<CtlRole> roleList = dao.findListAdd(ctlRoleNew);
			if(roleList.size()==0) {
					ctlRole.setComCode(userInfo.getComCode());
					save(ctlRole);
					map.put("msg", "success");
				}else {
					map.put("msg", "repeat");
				}
			}else {
				ctlRole.setComCode(userInfo.getComCode());
				save(ctlRole);
				map.put("msg", "success");
		}
		return map;
	}
	/**
	 *
	 * deleteRole:(删除角色).
	 * @param roleIds : 角色代码集合
	 * @since JDK 1.8
	 * @author 王天尧
	 * @return
	 */
	@Transactional(readOnly = false)
	public Map<String, String> deleteRole(String roleIds) {
		//将字符串解析为数组
		Map<String,String> result=new HashMap<String,String>();
		String ids[]=roleIds.split(",");
		for(String id:ids){
			if(id!=null&&!id.equals("")){
				Map<String, String> checkBeforeDelete = deleteRulesUtils.checkBeforeDelete(CtlRole.class, id);
				CtlRole ctlRole = get(id);
				String value = checkBeforeDelete.get("value");
				String desc = checkBeforeDelete.get("description");
				if(value=="true") {
					delete(ctlRole);
					result.put("value", value);
				}else {
					result.put("value", value);
					result.put("desc", desc);
				}
			}
		}
		return result;
	}
	/**
	 *
	 * getListRole:(获取角色列表，带分页).
	 * @param ctlRole
	 * @param roleCode 角色代码
	 * @param enabledFlag 可用标识
	 * @param page 分页对象
	 * @return :Page<CtlRole>
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	public Page<CtlRole> getListRole(CtlRole ctlRole,String roleCode,Page<CtlRole> page){
		ctlRole.setRoleCode(roleCode);
		ctlRole.setEnabledFlag("Y");
		ctlRole.getPage().setOrderBy("createdDt");
		Page<CtlRole> pageRole = findPage(page, ctlRole);
		return pageRole;
	}
	/**
	 *
	 * getRoleUser:(获取角色的用户信息).
	 * @param roleCode 角色代码
	 * @param ctlUserRole
	 * @param page 分页对象
	 * @return :Page<CtlUser>
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	public Page<CtlUser> getRoleUser(String roleCode,CtlUserRole ctlUserRole,Page<CtlUser> page ) {
		//通过角色id查询关联的用户
		ctlUserRole.setRoleCode(roleCode);
		List<CtlUserRole> userRoleList = ctlUserRoleDao.findList(ctlUserRole);
		//建立一个集合盛放user对象
		List<CtlUser> users=new ArrayList<CtlUser>();
		//遍历userRoleList获取userCode
		for (CtlUserRole userRole : userRoleList) {
			CtlUser ctlUserNew=new CtlUser();
			ctlUserNew.setUserCode(userRole.getUserCode());
			List<CtlUser> findList = ctlUserDao.findList(ctlUserNew);
			users.addAll(findList);
		}
	    page.setList(users);
		page.setCount(users.size());
		return page;
	}

	/**
	 *
	 * getUnUser:(获取该角色未拥有的用户).
	 * @param roleCode 角色代码
	 * @param ctlUserRole
	 * @param page 	  分页对象
	 * @return :Page<CtlUser>
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	public Page<CtlUser> getUnUser(String roleCode,CtlUserRole ctlUserRole,Page<CtlUser> page) {
		//通过角色id查询关联的用户
		ctlUserRole.setRoleCode(roleCode);
		List<CtlUserRole> userRoleList = ctlUserRoleDao.findList(ctlUserRole);
		//建立一个集合盛放user对象
		List<CtlUser> users=new ArrayList<CtlUser>();
		//获取当前机构下所有的可用用户
		//获取当前登录用户
		LoginUser userInfo = UserServletContext.getUserInfo();
		CtlUser ctlUser=new CtlUser();
		ctlUser.setEnabledFlag("Y");
		ctlUser.setUserType("I");
		ctlUser.setComCode(userInfo.getComCode());
		List<CtlUser> allUsers = ctlUserDao.findList(ctlUser);
		//遍历userRoleList获取userCode，获取角色拥有的用户
		for (CtlUserRole userRole : userRoleList) {
			CtlUser ctlUserNew=new CtlUser();
			ctlUserNew.setUserCode(userRole.getUserCode());
			List<CtlUser> findList = ctlUserDao.findList(ctlUserNew);
			users.addAll(findList);
		}
		//获取该角色未拥有的用户
		allUsers.removeAll(users);
		page.setList(allUsers);
		page.setCount(allUsers.size());
		return page;
	}

	/**
	    * @Title: getRoleUserList
	    * @Description: (获取角色的所有用户)
	    * @param @param roleCode
	    * @param @param ctlUserRole
	    * @param @return    参数
	    * @return List<CtlUser>  用户集合
	    * @throws
	 */
	public List<CtlUser> getRoleUserList(String roleCode,CtlUserRole ctlUserRole) {
		//通过角色id查询关联的用户
		ctlUserRole.setRoleCode(roleCode);
		List<CtlUserRole> userRoleList = ctlUserRoleDao.findList(ctlUserRole);
		//建立一个集合盛放user对象
		List<CtlUser> users=new ArrayList<CtlUser>();
		//遍历userRoleList获取userCode
		for (CtlUserRole userRole : userRoleList) {
			CtlUser ctlUserNew=new CtlUser();
			ctlUserNew.setUserCode(userRole.getUserCode());
			List<CtlUser> findList = ctlUserDao.findList(ctlUserNew);
			users.addAll(findList);
		}
		return users;
	}
}

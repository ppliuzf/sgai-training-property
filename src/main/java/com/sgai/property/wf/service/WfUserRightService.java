package com.sgai.property.wf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.dao.CtlRoleDao;
import com.sgai.property.ctl.dao.CtlUserDao;
import com.sgai.property.ctl.entity.CtlBusinessDefine;
import com.sgai.property.ctl.entity.CtlCodeDet;
import com.sgai.property.ctl.entity.CtlComBusiRelation;
import com.sgai.property.ctl.entity.CtlComp;
import com.sgai.property.ctl.entity.CtlCompMenu;
import com.sgai.property.ctl.entity.CtlRole;
import com.sgai.property.ctl.entity.CtlUser;
import com.sgai.property.ctl.service.CtlCodeDetService;
import com.sgai.property.ctl.service.CtlRoleService;
import com.sgai.property.ctl.service.CtlUserService;
import com.sgai.property.em.entity.EmRepairList;
import com.sgai.property.wf.dao.WfUserRightDao;
import com.sgai.property.wf.entity.WfFlowDefine;
import com.sgai.property.wf.entity.WfUserRight;

/**
 *
    * ClassName: WfUserRightService
    * com.sgai.property.commonService.vo;(流程权限Service)
    * @author 王天尧
    * Date 2017年12月5日
    * Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class WfUserRightService extends CrudServiceExt<WfUserRightDao, WfUserRight> {
	@Autowired
	private CtlUserDao ctlUserDao;
	@Autowired
	private CtlRoleDao ctlRoleDao;
	@Autowired
	private CtlUserService   ctlUserService;
	@Autowired
	private CtlRoleService  ctlRoleService;

	@Autowired
	private CtlCodeDetService ctlCodeDetService;
	@Autowired
	private WfInstanceRecordService wfInstanceRecordService;
	public WfUserRight get(String id) {
		return super.get(id);
	}

	public List<WfUserRight> findList(WfUserRight wfUserRight) {
		return super.findList(wfUserRight);
	}

	public List<WfUserRight> findLoginUserAuthority(WfUserRight wfUserRight){
		wfUserRight.preGet();
		return dao.findLoginUserAuthority(wfUserRight);
	}

	public Page<WfUserRight> findPage(Page<WfUserRight> page, WfUserRight wfUserRight) {
		return super.findPage(page, wfUserRight);
	}
	@Transactional(readOnly = false)
	public void save(WfUserRight wfUserRight) {
		super.save(wfUserRight);
	}

	@Transactional(readOnly = false)
	public void delete(WfUserRight wfUserRight) {
		super.delete(wfUserRight);
	}
	/**
	 *
	 * getFlowiListLack:(获取用户没有的流程).
	 * @param param 传递所需要的参数
	 * @return :List<Map<String,String>>
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	 public List<WfFlowDefine> getFlowListLack(Map<String,String> param,String comCode){
		// 存放所有流程的集合
		List<WfFlowDefine> allList = dao.getAllFlowList(comCode);
		// 存放所拥有的流程集合
		param.put("comCode", comCode);
		List<WfFlowDefine> ownList = dao.getFlowListByUR(param);
		allList.removeAll(ownList);
		return allList;
	}
	 /**
	  *
	  * getFlowListLackPage:(分页查询).
	  * @param param
	  * @return :List<WfFlowDefine>
	  * @since JDK 1.8
	  * @author 王天尧
	  */
	public List<WfFlowDefine> getFlowListLackPage(Map<String,String> param){
		// 存放所拥有的流程集合
		List<WfFlowDefine> list = dao.getFlowListUnOwn(param);
		return list;
	}
	/**
	 *
	 * getFlowListOwn:(得到该用户已拥有的流程).
	 * @param param
	 * @return :List<Map<String,String>>
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	public List<WfFlowDefine> getFlowListOwn(Map<String,String> param){
		List<WfFlowDefine> list = dao.getFlowListByUR(param);
		return list;
	}
	/**
	 *
	 * deleteStepTree:(删除该角色或用户的关联关系).
	 * @param param :void
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	@Transactional(readOnly = false)
	public void deleteStepTree(Map<String,String> param) {
		dao.deleteStepTree(param);
	}
	/**
	 *
	 * saveStepTree:(保存该用户或角色的流程).
	 * @param stepCodeList 步骤代码集合
	 * @param corrCode :void  用户或角色代码
	 * @since JDK 1.8
	 * @author ASUS
	 * @return
	 */
	@Transactional(readOnly = false)
	public Map<String, String> saveStepTree(List<String> stepCodeList, String corrCode,String category,String comCode) {
		Map<String,String> result = new HashMap<String,String>();
		result.put("msg", "success");
		for (String stepCode : stepCodeList) {
			//验证唯一性，一个步骤节点只对应一个人
			String substring = stepCode.substring(0, 4);
			Map<String,String> param = new HashMap<String,String>();
			param.put("stepCode", substring);
			param.put("corrCode", corrCode);
			param.put("comCode", comCode);
			List<WfUserRight> findLike = dao.findLike(param);
			if(findLike.size()==0) {
				WfUserRight entity = new WfUserRight();
				entity.setCategory(category);
				entity.setCorrCode(corrCode);
				entity.setStepCode(stepCode);
				entity.setEnabledFlag("Y");
				super.save(entity);
			}else {
				result.put("msg", "repeat");
				result.put("step",substring );
				break;
			}

		}
		return result;
	}
	/**
	 *
	 * getUserRoleTree:(构建角色用户的树结构).
	 * @return :List<Map<String,String>>
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	@SuppressWarnings("deprecation")
	public List<Map<String,String>> getUserRoleTree(String deptCode,String comCode,String moduCode){
		List<Map<String, String>> result = Lists.newArrayList();
		CtlUser ctlUser = new CtlUser();
		ctlUser.setComCode(comCode);
		ctlUser.setModuCode(moduCode);
		CtlRole ctlRole = new CtlRole();
		ctlRole.setComCode(comCode);
		ctlRole.setModuCode(moduCode);
		List<CtlUser>  cUserList = ctlUserService.findUserForEventByDeptCode(ctlUser);
		List<CtlRole> roleList = ctlRoleService.findRoleForEventByDeptCode(ctlRole);
		// 这里需要修改
		/*List<CtlUser> cUserList = ctlUserDao.getUserBytype();
		List<CtlRole> roleList = ctlRoleDao.findAllList();*/
		Map<String, String> newMap = Maps.newHashMap();
		newMap.put("id","U");
		newMap.put("pId", "0");
		newMap.put("name", "用户");
		Map<String, String> newMap2 = Maps.newHashMap();
		newMap2.put("id","R");
		newMap2.put("pId", "0");
		newMap2.put("name", "角色");
		result.add(newMap);
		result.add(newMap2);
		for (CtlUser user : cUserList) {
			Map<String, String> newMap3 = Maps.newHashMap();
			newMap3.put("id", user.getUserCode());
			newMap3.put("pId", "U");
			newMap3.put("name", user.getUserName());
			result.add(newMap3);
		}
		for (CtlRole role : roleList) {
			Map<String, String> newMap3 = Maps.newHashMap();
			newMap3.put("id", role.getRoleCode());
			newMap3.put("pId", "R");
			newMap3.put("name", role.getRoleDesc());
			result.add(newMap3);
		}
		return result;
	}
	/**
	 *
	 * getListByUser:(根据用户过滤流程实例).
	 * @param param
	 * @return :Page<EmRepairList>
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	@Transactional
	public Map<String, String> getListByUser(Map<String, String> param){

		List<WfFlowDefine>wfFlowDefines = wfInstanceRecordService.getStepByCode(param);
		String state = "";
		String userCode=param.get("userCode");
		String codeType=param.get("flowCode");
		CtlCodeDet ctlCodeDet = new CtlCodeDet();
		for (int i=0;i<wfFlowDefines.size();i++) {
			if(i<wfFlowDefines.size()-1) {
				if (!"".equals(codeType)) {
					ctlCodeDet.setCodeType(codeType);
				}
				ctlCodeDet.setCodeName("待"+wfFlowDefines.get(i).getStepName());
				CtlCodeDet codeCode = ctlCodeDetService.getCodeDet(ctlCodeDet);
				state+="'"+codeCode.getCodeCode()+"'"+",";
			}else {
				if (!"".equals(codeType)) {
					ctlCodeDet.setCodeType(codeType);
				}
				ctlCodeDet.setCodeName("待"+wfFlowDefines.get(i).getStepName());
				CtlCodeDet codeCode = ctlCodeDetService.getCodeDet(ctlCodeDet);
				state+="'"+codeCode.getCodeCode()+"'";
			}

		}
		Map<String, String> sqlMap=new HashMap<String, String>();
		if(wfFlowDefines.size()!=0) {
			sqlMap.put("sqlMap","allrecord.\"emState\" IN"+ "("+state+")"+"AND"+"(USER_CODE IS NULL OR  USER_CODE="+"'"+userCode+"')");
		}
		return sqlMap;
	}

	/**
	 *
	 * getListByUser:(根据用户过滤流程实例).
	 * @param param
	 * @return :Page<EmRepairList>
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	@Transactional
	public Map<String, String> getListsByUser(Map<String, String> param){

		List<WfFlowDefine>wfFlowDefines = wfInstanceRecordService.getStepByCode(param);
		String state = "";
		String userCode=param.get("userCode");
		String codeType=param.get("flowCode");
		CtlCodeDet ctlCodeDet = new CtlCodeDet();
		for (int i=0;i<wfFlowDefines.size();i++) {
			if(i<wfFlowDefines.size()-1) {
				ctlCodeDet.setCodeType(codeType);
				ctlCodeDet.setCodeName("待"+wfFlowDefines.get(i).getStepName());
				CtlCodeDet codeCode = ctlCodeDetService.getCodeDet(ctlCodeDet);
				state+="'"+codeCode.getCodeCode()+"'"+",";
			}else {
				ctlCodeDet.setCodeType(codeType);
				ctlCodeDet.setCodeName("待"+wfFlowDefines.get(i).getStepName());
				CtlCodeDet codeCode = ctlCodeDetService.getCodeDet(ctlCodeDet);
				state+="'"+codeCode.getCodeCode()+"'";
			}

		}

		Map<String, String> sqlMap=new HashMap<String, String>();
		if(wfFlowDefines.size()!=0) {
			sqlMap.put("sqlMap","and states IN"+ "("+state+")"+"AND"+"(USER_CODE IS NULL OR  USER_CODE="+"'"+userCode+"')");
		}else {
			sqlMap.put("sqlMap", "and states IN"+"('')");
		}
		return sqlMap;
	}

}

package com.sgai.property.commonService.service;

import com.alibaba.fastjson.JSON;
import com.sgai.property.commonService.client.TobApi;
import com.sgai.property.commonService.dao.IEmpInfoCompareDao;
import com.sgai.property.commonService.dao.IEmpInfoDao;
import com.sgai.property.commonService.entity.EmpInfo;
import com.sgai.property.common.util.CommonUtil;
import com.sgai.property.commonService.constants.Constants;
import com.sgai.property.common.util.JSONUtil;
import com.sgai.property.commonService.vo.organ.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//@Service
public class SyncEmployeesService {
	
	private static Logger logger = LoggerFactory.getLogger(SyncEmployeesService.class);
	
	private Lock lock = new ReentrantLock();
	
	@Value("${accessToken.accessId}")
	private String accessId;
	@Value("${accessToken.accessSecret}")
	private String accessSecret;
	@Value("${accessToken.orgId}")
	private String orgId;
	@Value("${orgTree.defaultEiPicUrl}")
	private String defaultEiPicUrl;
	@Autowired
	private TobApi tobApi;
	@Autowired
	private IEmpInfoCompareDao empInfoCompareDao;
	@Autowired
	private IEmpInfoDao empInfoDao;
	@Autowired
	private OrganServiceImpl organService;
	@Async
	public void sync() {
		try {
			lock.lock();
//			List<String> idList = empInfoCompareDao.getAllIds();
//			if(!idList.isEmpty()) {
			empInfoCompareDao.deleteAll();
//			}
			logger.info("同步处理组织：{}数据>>>>>>>>>>>>>>开始", orgId);

			//获取连接平台用户数据
			logger.info("同步处理组织：{}的用户数据>>>>开始", orgId);
			List<User> userList = getUserList(Long.parseLong(orgId));
			logger.info("同步处理组织：{}的用户数据<<<<结束", orgId);
			
			//获取连接平台角色数据
			logger.info("同步处理组织：{}的角色数据>>>>开始", orgId);
			Map<String, Role> roleMap = getRoleMap(Long.parseLong(orgId));
			logger.info("同步处理组织：{}的角色数据<<<<结束", orgId);
			
			//获取连接平台组织单元数据
			logger.info("同步处理组织：{}的组织单元数据>>>>开始", orgId);
			List<OrganUnit> organUnitList = getOrganUnitList(Long.parseLong(orgId));
			Map<String, OrganUnit> organUnitMap = getOrganUnitMap(organUnitList);
			logger.info("同步处理组织：{}的组织单元数据<<<<结束", orgId);
			
			//根据对应关系处理连接平台数据
			logger.info("根据对应关系处理组织：{}的数据>>>>开始", orgId);
			List<EmpInfo> empInfoList = empInfoHandle(Long.parseLong(orgId), userList, roleMap, organUnitMap);
			logger.info("根据对应关系处理组织：{}的数据<<<<结束", orgId);
			
			//处理用户toon_user_id
			logger.info("处理组织：{}的用户对应的toon user id>>>>开始", orgId);
			toonUserIdHandle(Long.parseLong(orgId), empInfoList);
			logger.info("处理组织：{}的用户对应的toon user id<<<<结束", orgId);
			
			//保存处理后的连接平台用户数据
			logger.info("保存处理后的组织：{}数据>>>>开始", orgId);
			empInfoCompareDao.insertEmpInfoBatch(empInfoList);
			logger.info("保存处理后的组织：{}数据<<<<结束", orgId);
			
			logger.info("同步处理组织：{}数据<<<<<<<<<<<<<<结束", orgId);
			
			//数据比对
			List<EmpInfo> addEmpInfoList = null;
			List<EmpInfo> deleteEmpInfoList = null;
			List<EmpInfo> updateEmpInfoList = null;
			try {
				logger.info("数据比对>>>>开始");
				addEmpInfoList = empInfoCompareDao.getAddEmpInfo();
				logger.info("数据比对，得到需要添加的用户{}条", addEmpInfoList.size());
				deleteEmpInfoList = empInfoCompareDao.getDeleteEmpInfo();
				logger.info("数据比对，得到需要删除的用户{}条", deleteEmpInfoList.size());
				updateEmpInfoList = empInfoCompareDao.getUpdateEmpInfo();
				logger.info("数据比对，得到需要修改的用户{}条", updateEmpInfoList.size());
				logger.info("数据比对<<<<结束");
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("数据比对出错!");
			}
			
			//更新用户表
			try {
				if(addEmpInfoList != null && addEmpInfoList.size() > 0) {				
					empInfoDao.insertEmpInfoBatch(addEmpInfoList);
				}
				if(updateEmpInfoList != null && updateEmpInfoList.size() > 0) {	
					for(EmpInfo emp : updateEmpInfoList) {							
						empInfoDao.updateEmpInfo(emp);
					}
				}
				if(deleteEmpInfoList != null && deleteEmpInfoList.size() > 0) {
					for(EmpInfo emp : deleteEmpInfoList) {
						empInfoDao.delete(emp);
					}
//					empInfoDao.deleteByListId(ids);
				}
			} catch (Exception e) {
				throw new Exception("更新EmpInfo数据表出错!");
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {	
			lock.unlock();
			SyncService.canStart = true;
		}
	}
	
	/**
	 * 获取连接平台accessToken
	 * @return
     */
	public String getAccessToken(Long orgId) {
		String accessToken = null;
		try {
//			Map<String, Object> paramsMap = new HashMap<String, Object>();
//			paramsMap.put("access_id", accessId);
//			paramsMap.put("access_secret", accessSecret);
//			String json = tobApi.getAccessToken(paramsMap);
//			if (json != null) {
//				Map<Object, String> result = JSON.parseObject(json, Map.class);
//				accessToken = result.get("accessToken");
//			}
			return organService.getAccessToken1(orgId.toString(),accessId,accessSecret);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken; 
	}
	
	/**
	 * 从连接平台获取用户
	 * @return
	 * @throws Exception
	 */
	public List<User> getUserList(Long orgId) throws Exception {
		List<User> result = null; 
		try {
			String accessToken = getAccessToken(orgId);
			tobApi.setAccess_token(accessToken);
			String json = tobApi.getAllUsers();
			CommonResult<User> commonResult = JSONUtil.parseObject(json, User.class);
			if(commonResult != null && commonResult.getData() != null && !commonResult.getData().isEmpty()) {
				result = commonResult.getData();
				logger.info("从连接平台获取组织:{}的用户数据:{}条", orgId, result.size());
			} else {
				throw new Exception("从连接平台获取组织:" + orgId + "的用户数据为空！");
			}			
			return result;
		} catch (Exception e) {
			throw new Exception("从连接平台获取组织:" + orgId + "的用户数据出错！");
		}
	}
	
	/**
	 * 从连接平台获取角色
	 * @return
	 * @throws Exception
	 */
	public Map<String, Role> getRoleMap(Long orgId) throws Exception {
		Map<String, Role> map = null;
		try {
			map = new HashMap<String, Role>();
			String accessToken = getAccessToken(orgId);
			tobApi.setAccess_token(accessToken);
			String json = tobApi.getAllRoles();
			CommonResult<Role> commonResult = JSONUtil.parseObject(json, Role.class);
			if(commonResult != null && commonResult.getData() != null && !commonResult.getData().isEmpty()) {
				List<Role> roleList = commonResult.getData();
				logger.info("从连接平台获取组织:{}的角色数据:{}条", orgId, roleList.size());				
				if(roleList != null && roleList.size() > 0) {
					for(Role role : roleList) {
						map.put(role.getId(), role);
					}
				}
			}
			return map;
		} catch (Exception e) {
			throw new Exception("从连接平台获取组织:" + orgId + "的角色数据出错！");
		}
	}
	
	/**
	 * 从连接平台获取组织单元
	 * @return
	 * @throws Exception
	 */
	public List<OrganUnit> getOrganUnitList(Long orgId) throws Exception {
		List<OrganUnit> organUnitList = null; 
		try {
			String accessToken = getAccessToken(orgId);
			tobApi.setAccess_token(accessToken);
			String json = tobApi.getAllOrganUnit();
			CommonResult<OrganUnit> commonResult = JSONUtil.parseObject(json, OrganUnit.class);
			if(commonResult != null && commonResult.getData() != null && !commonResult.getData().isEmpty()) {
				organUnitList = commonResult.getData();
				logger.info("从连接平台获取组织:{}的组织单元数据:{}条", orgId,organUnitList.size());	
			}			
			return organUnitList;
		} catch (Exception e) {
			throw new Exception("从连接平台获取组织:" + orgId + "的组织单元数据出错！");
		}
	}
	
	/**
	 * 从连接平台获取组织单元
	 * @return
     */
	public Map<String, OrganUnit> getOrganUnitMap(List<OrganUnit> organUnitList) {
		Map<String, OrganUnit> map = null;	
		if(organUnitList != null && organUnitList.size() > 0) {
			map = new HashMap<String, OrganUnit>();
			for(OrganUnit organ : organUnitList) {
				map.put(organ.getId(), organ);
			}
		}	
		return map;
	}

	/**
	 * 处理连接平台用户数据
	 * @param userList
	 * @param roleMap
	 * @param organUnitMap
	 * @return
	 * @throws Exception
	 */
	public List<EmpInfo> empInfoHandle(Long orgId, List<User> userList, 
			Map<String, Role> roleMap, Map<String, OrganUnit> organUnitMap) throws Exception {
		List<EmpInfo> empInfoList = null;
		try {
			String orgName = null;
			//处理得到根部门（组织）名称
			Iterator<Map.Entry<String, OrganUnit>> it = organUnitMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, OrganUnit> entry = it.next();
				OrganUnit organ = entry.getValue();
				if(Constants.Sync.ROOT_PARENT_ID.equals(organ.getParentId())) {
					orgName = organ.getOrganUnitName();
					break;
				}
			}
			
			if(userList != null && userList.size() > 0) {
				empInfoList = new ArrayList<EmpInfo>();
				for(User user : userList) {
					EmpInfo emp = new EmpInfo();
					/**
					 * 处理用户基本信息
					 */
					emp.setId(user.getId());
					emp.setEiEmpName(user.getUserName());	//用户名称
					emp.setEiEmpNo(user.getUserCode());	//用户工号
                    if(StringUtils.isNotEmpty(user.getPhoto())){
                        emp.setEiHeadPicture(user.getPhoto());
                    }else{
                        emp.setEiHeadPicture(defaultEiPicUrl);
                    }
					emp.setEiEmpPhone(user.getMobilePhone());
					emp.setEiEmail(user.getEmail());
					emp.setOriginEmId(Long.parseLong(user.getId()));	//连接平台的用户id
					if(user.getFeedIds() != null && user.getFeedIds().size() > 0) {						
						emp.setFeedId(user.getFeedIds().get(0));	//名片id（名片只一张）
					} else {
						emp.setFeedId("");
					}
					emp.setOrgId(orgId);
					emp.setOrgName(orgName);
					emp.setUpdateTime(System.currentTimeMillis());
					emp.setCreateTime(System.currentTimeMillis());
					/**
					 * 处理用户岗位（角色）
					 */
					List<UserRole> userRoleList = user.getRoles();
					if(userRoleList != null && userRoleList.size() > 0) {
						//用户可能会有多个岗位
						for(UserRole userRole : userRoleList) {
							//只拿主岗,从用户角色关系中取角色id
							if("0".equals(userRole.getRelationType())) {
								String roleId = userRole.getId();
								//从角色Map中取对应的角色
								if(roleMap.get(roleId) != null) {
									Role role = roleMap.get(roleId);
									emp.setPositionName(role.getRoleName());
									emp.setPositionId(Long.parseLong(role.getId()));
								
									/**
									 * 处理部门（组织单元）
									 */
									List<RoleOrganUnit> roleOrganUnitList = role.getDepts();
									if(roleOrganUnitList != null && roleOrganUnitList.size() > 0) {
										//其实一个角色只对应一个部门
										RoleOrganUnit roleOrganUnit = roleOrganUnitList.get(0);
										//通过角色部门关系拿到部门id
										String organUnitId = roleOrganUnit.getId();
										if(organUnitMap.get(organUnitId) != null) {
											OrganUnit organUnit = organUnitMap.get(organUnitId);
											emp.setDeptName(organUnit.getOrganUnitName());
											emp.setDeptId(Long.parseLong(organUnit.getId()));
										}
									}
								}
								break;
							}
						}
					}
					empInfoList.add(emp);
				}
			}
		} catch (Exception e) {
			throw new Exception("处理组织:" + orgId + "的数据得到用户数据出错！");
		}
		return empInfoList;
	}
	
	/**
	 * 处理用户对应的toon user id
	 * @param empInfoList
	 * @throws Exception
	 */
	public void toonUserIdHandle(Long orgId, List<EmpInfo> empInfoList) throws Exception {
		try {	
			if(empInfoList != null && empInfoList.size() > 0) {
				//获取所有名片id
				List<String> feedIds = new ArrayList<>();
				for(EmpInfo emp : empInfoList) {
					String feedId = emp.getFeedId();
					if(feedId != null && !"".equals(feedId)) {
						feedIds.add(feedId);
					}
				}
				if(feedIds.size() > 0) {
					Map<String, ToonStaffCard> toonStaffCardMap = getToonUserIdMap(orgId, feedIds);
					for(EmpInfo emp : empInfoList) {
						if(emp.getFeedId() != null && !"".equals(emp.getFeedId()) && toonStaffCardMap.get(emp.getFeedId())!= null) {
							if(toonStaffCardMap.get(emp.getFeedId()).getUserId() != null) {								
								emp.setToonUserId(Long.parseLong(toonStaffCardMap.get(emp.getFeedId()).getUserId()));
							} else {
								emp.setToonUserId(0L);
							}
						} else {
							emp.setToonUserId(0L);
						}
					}
				}
			}
		} catch (Exception e) {
			throw new Exception("处理组织:" + orgId + "的用户对应的toon user id出错！");
		}
	}
	
	/**
	 * 通过名片id从连接平台获取相关userid
	 * @param feedIds
	 * @return
	 * @throws Exception
	 */
	public Map<String, ToonStaffCard> getToonUserIdMap(Long orgId, List<String> feedIds) throws Exception {
		Map<String, ToonStaffCard> map = new HashMap<String, ToonStaffCard>();
		try {
			String accessToken = getAccessToken(orgId);
			tobApi.setAccess_token(accessToken);
			if(feedIds.size() > 50) {
				List<List<String>> list = CommonUtil.splitList(feedIds, 50);
				for(int i=0; i<list.size(); i++) {
					List<String> l = list.get(i);
					String json = tobApi.getToonUserId(l);
					CommonResult<ToonStaffCard> commonResult = JSONUtil.parseObject(json, ToonStaffCard.class);
					if(commonResult != null && commonResult.getData() != null && !commonResult.getData().isEmpty()) {
						List<ToonStaffCard> toonStaffCardList = commonResult.getData();			
						if(toonStaffCardList != null && toonStaffCardList.size() > 0) {
							for(ToonStaffCard toonStaffCard : toonStaffCardList) {
								map.put(toonStaffCard.getFeedId(), toonStaffCard);
							}
						}
					}
				}
			} else {
				String json = tobApi.getToonUserId(feedIds);
				CommonResult<ToonStaffCard> commonResult = JSONUtil.parseObject(json, ToonStaffCard.class);
				if(commonResult != null && commonResult.getData() != null && !commonResult.getData().isEmpty()) {
					List<ToonStaffCard> toonStaffCardList = commonResult.getData();			
					if(toonStaffCardList != null && toonStaffCardList.size() > 0) {
						for(ToonStaffCard toonStaffCard : toonStaffCardList) {
							map.put(toonStaffCard.getFeedId(), toonStaffCard);
						}
					}
				}
			}
			return map;
		} catch (Exception e) {
			throw new Exception("从连接平台获取组织:" + orgId + "的toon user Id出错！");
		}
	}
	
	
}

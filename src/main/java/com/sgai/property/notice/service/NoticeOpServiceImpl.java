package com.sgai.property.notice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.service.BaseDepartmentService;
import com.sgai.property.commonService.dto.SgaiEmpDto;
import com.sgai.property.commonService.vo.DeptVo;
import com.sgai.property.commonService.vo.OrgSgaiTreeNode;
import com.sgai.property.ctl.entity.CtlDept;
import com.sgai.property.notice.constants.Constants;
import com.sgai.property.notice.dao.INoticeInfoDao;
import com.sgai.property.notice.dao.INoticeInfoReceiptDao;
import com.sgai.property.notice.dao.INoticeInfoScopeDao;
import com.sgai.property.notice.dao.INoticeInfoScopeEmpDao;
import com.sgai.property.notice.dao.INoticeInfoTimePublishDao;
import com.sgai.property.notice.dao.InfoReceiptVoDao;
import com.sgai.property.notice.dao.InfoScopeEmpVoDao;
import com.sgai.property.notice.entity.NoticeInfo;
import com.sgai.property.notice.entity.NoticeInfoReceipt;
import com.sgai.property.notice.entity.NoticeInfoScope;
import com.sgai.property.notice.entity.NoticeInfoScopeEmp;
import com.sgai.property.notice.entity.NoticeInfoTimePublish;
import com.sgai.property.notice.job.PublishJob;
import com.sgai.property.notice.param.InfoCommitParam;
import com.sgai.property.notice.vo.NoticeInfoVo;

@Service
public class NoticeOpServiceImpl {
	@Autowired
	private INoticeInfoDao infoDao;
	@Autowired
	private INoticeInfoTimePublishDao infoTimePublishDao;

	@Autowired
	private INoticeInfoScopeDao infoScopeDao;
	@Autowired
	private INoticeInfoScopeEmpDao infoScopeEmpDao;
	@Autowired
	private DeptInfoServiceImpl deptInfoService;
	@Autowired
	private NoticeEmpInfoServiceImpl empInfoService;
	@Autowired
	private INoticeInfoTimePublishDao timePublishDao;

	@Autowired
	private InfoScopeEmpVoDao infoScopeEmpVoDao;
	@Autowired
	private InfoReceiptVoDao infoReceiptVoDao;

	@Autowired
	private INoticeInfoReceiptDao infoReceiptDao;
	@Autowired
	private BaseDepartmentService baseDepartmentService;

	@Autowired
	// @Qualifier("Scheduler")
	private Scheduler scheduler;

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
	public String submit(InfoCommitParam infoCommitParam) {
		Date dt = new Date();
		NoticeInfo info = new NoticeInfo();
		if (StringUtils.isBlank(infoCommitParam.getVisibilityScope())) {
			throw new BusinessException(ReturnType.Success, "可见范围不能为空！");
		}
		BeanCopier copier = BeanCopier.create(infoCommitParam.getClass(), info.getClass(), false);
		copier.copy(infoCommitParam, info, null);
		info.setInfoOrgId(UserServletContext.getUserInfo().getComCode());
		info.setInfoOrgName(UserServletContext.getUserInfo().getComName());
		info.setModuCode(UserServletContext.getUserInfo().getModuCode());
		info.setComCode(UserServletContext.getUserInfo().getComCode());
		if (UserServletContext.getUserInfo().getUserType().equals("user")) {
			info.setInfoCreatorType(Constants.Notice.CREATE_TYPE_EMP);
			info.setInfoCreatorId(UserServletContext.getUserInfo().getUserId());
			info.setInfoCreatorName(UserServletContext.getUserInfo().getUserName());
		} else {
			info.setInfoCreatorType(Constants.Notice.CREATE_TYPE_ORG);
			LoginUser userInfo = UserServletContext.getUserInfo();
			info.setInfoCreatorId(userInfo.getUserId());
			info.setInfoCreatorName(UserServletContext.getUserInfo().getUserName());
		}
		info.setId(UUID.randomUUID().toString());
		info.setCreateTime(dt.getTime());
		info.setUpdateTime(dt.getTime());
		info.setUpdatedDt(dt);
		info.setCreatedDt(dt);
		info.setInfoTimePublish(infoCommitParam.getInfoTimePublish().toString());
		// 需要审核的设置发起审核时间
		if (info.getInfoApprovalFlag() != null && info.getInfoApprovalFlag() == 1) {
			info.setInitApprovalTime(dt.getTime());
			info.setInfoStatus(Constants.Notice.DSH.longValue());
		}
		// 立即发布
		if (info.getInfoApprovalFlag() != null && info.getInfoApprovalFlag() == 0) {
			info.setInfoStatus(Constants.Notice.YFB.longValue());
			info.setPublishTime(dt.getTime());
		}
		// 是否定时发布
		if (info.getInfoTimePublish() != null && info.getInfoTimePublish().equals("1")) {
			info.setInfoStatus(Constants.Notice.YTG.longValue());
		}

		infoDao.insert(info);
		insertInfoAttr(info, infoCommitParam);
		return info.getId();
	}

	private void insertInfoAttr(NoticeInfo info, InfoCommitParam infoCommitParam) {
		Date date = new Date();
		// 是否定时发布
		if (info.getInfoTimePublish() != null && info.getInfoTimePublish().equals("1")) {
			cronPublic(info);
		}

		// 如果发布范围不是全部可见
		if (info.getInfoScopeIsAll().intValue() == Constants.Notice.INFO_SCOPE_IS_NOT_ALL.intValue()) {
			// 所选部门信息
			List<DeptVo> deptVoList = null;
			// 所选人员信息
			List<SgaiEmpDto> empInfoList = new ArrayList<>();
			// 发布范围内所有人员信息
			List<OrgSgaiTreeNode> empInfoListAll = new ArrayList<>();
			List<OrgSgaiTreeNode> empIdsByTree = new ArrayList<>();
			// 调用公告服务去掉Token中PC_前缀
			String sgaiToken = "";

			// 发布范围总的人员
			List<String> allEmp = new ArrayList<>();
			try {
				if (infoCommitParam.getInfoScopeDeparts() != null && infoCommitParam.getInfoScopeDeparts().size() > 0) {
					List<String> infoScopeslist = infoCommitParam.getInfoScopeDeparts();

					for (int i = 0; i < infoScopeslist.size(); i++) {
						List<OrgSgaiTreeNode> nodes = null;// commonsRomeotService.getSgaiTreeNode(sgaiToken,
															// infoScopeslist.get(i)).getData();
						for (OrgSgaiTreeNode ns : nodes) {
							Integer nodeType = ns.getNodeType();
							if (nodeType.intValue() == 1) {
								empIdsByTree.add(ns);
							} else {
								nodeTree(sgaiToken, empIdsByTree, ns);
							}
						}
					}
					List<String> deptEmp = new ArrayList<>();
					if (empIdsByTree != null && empIdsByTree.size() > 0) {
						for (OrgSgaiTreeNode emp : empIdsByTree) {
							deptEmp.add(emp.getNodeId() + "");
						}
						allEmp.addAll(deptEmp);
					}
				}

				if (infoCommitParam.getInfoScope() != null && infoCommitParam.getInfoScope().size() > 0) {
					allEmp.addAll(infoCommitParam.getInfoScope());
					// 查所选人员信息
//					empInfoList = empInfoService.getEmpInfoByEiIds(UserServletContext.getUserInfo().getComCode(), infoCommitParam.getInfoScope());
					List<String> scopeList = infoCommitParam.getInfoScope();
					for (int i = 0; i < scopeList.size(); i++) {
						// empInfoList.add(commonsRomeotService.getSgaiEmpById(sgaiToken,
						// scopeList.get(i)).getData());
					}
				}

//				empInfoListAll.add(empIdsByTree);//人员信息
				// 发布范围内的人员去重
				allEmp = getValidList(allEmp);
//				if(allEmp != null && allEmp.size() > 0) {
//					//查所有人员信息
////					empInfoListAll = empInfoService.getEmpInfoByEiIds(UserServletContext.getUserInfo().getComCode(), allEmp);
//					for (int i = 0; i < allEmp.size(); i++){
////						empInfoListAll.add(commonsRomeotService.getSgaiEmpById(sgaiToken, allEmp.get(i)).getData());
//					}
//				}
			} catch (Exception e) {
				throw new BusinessException(ReturnType.Error, "调公共服务出错！");
			}

			/**
			 * 处理发布范围
			 */
			NoticeInfoScope infoScope = new NoticeInfoScope();
			infoScope.setId(UUID.randomUUID().toString());
			infoScope.setInfoId(info.getId());
			infoScope.setInfoScope(StringUtils.join(infoCommitParam.getInfoScope().toArray(), ","));
			infoScope.setInfoScopeDeparts(StringUtils.join(infoCommitParam.getInfoScopeDeparts().toArray(), ","));
			infoScope.setInfoScopeEmpNum(infoCommitParam.getInfoScopeEmpNum());
			infoScope.setInfoScopeObject(infoCommitParam.getInfoScopeObject());
			infoScope.setCreateTime(date.getTime());
			infoScope.setUpdateTime(date.getTime());
			StringBuilder sb = new StringBuilder();
//			if(deptVoList != null && deptVoList.size() > 0) {
			// 人员ID与部门ID拼接
			String linkInfo = linkString(sgaiToken, infoCommitParam.getInfoScopeDeparts());
			sb.append(linkInfo);
//				for(DeptVo dept : deptVoList) {
//					sb.append(dept.getDeptName());
//					sb.append("（");
//					sb.append(dept.getDeptNum() + "人");
//					sb.append("）");
//					sb.append("、");
//				}
//			}
			if (empInfoList != null && empInfoList.size() > 0) {
				for (SgaiEmpDto emp : empInfoList) {
					sb.append(emp.getLastname());
					sb.append("、");
				}
			}
			String infoScopeStr = sb.toString();
			if (infoScopeStr.length() > 0) {
				infoScopeStr = infoScopeStr.substring(0, infoScopeStr.length() - 1);
			}
			infoScope.setInfoScopeStr(infoScopeStr);

			/**
			 * 处理发布范围人员、处理回执
			 */
			List<NoticeInfoScopeEmp> infoScopeEmpList = new ArrayList<>();
			List<NoticeInfoReceipt> infoReceiptList = new ArrayList<>();

			if (allEmp != null && allEmp.size() > 0) {
				for (int i = 0; i < allEmp.size(); i++) {
					if (allEmp.get(i) == null) {
						continue;
					}
					NoticeInfoScopeEmp infoScopeEmp = new NoticeInfoScopeEmp();
					infoScopeEmp.setId(UUID.randomUUID().toString());
					infoScopeEmp.setInfoId(info.getId());
					infoScopeEmp.setEmpId(allEmp.get(i));
					infoScopeEmp.setCreateTime(date.getTime());
					infoScopeEmp.setUpdateTime(date.getTime());
					infoScopeEmp.setCreatedDt(date);
					infoScopeEmp.setUpdatedDt(date);
					infoScopeEmpList.add(infoScopeEmp);

					NoticeInfoReceipt infoReceipt = new NoticeInfoReceipt();
					infoReceipt.setId(UUID.randomUUID().toString());
					infoReceipt.setInfoId(info.getId());
					infoReceipt.setReceiptEmpId(allEmp.get(i));
					infoReceipt.setReceiptEmpName(UserServletContext.getUserInfo().getUserName());
					infoReceipt.setReceiptStatus(0L);
					infoReceipt.setReceiptTime(0L);
					infoReceipt.setCreateTime(date.getTime());
					infoReceipt.setUpdateTime(date.getTime());
					infoReceipt.setCreatedDt(date);
					infoReceipt.setUpdatedDt(date);
					infoReceiptList.add(infoReceipt);
				}
			}
			infoScopeDao.insert(infoScope);
			if (infoScopeEmpList != null && infoScopeEmpList.size() > 0) {
				infoScopeEmpVoDao.batchInsert(infoScopeEmpList);
			}
			if (infoReceiptList != null && infoReceiptList.size() > 0) {
				infoReceiptVoDao.batchInsert(infoReceiptList);
			}

		}
	}

	private void nodeTree(String sgaiToken, List<OrgSgaiTreeNode> empIdsByTree, OrgSgaiTreeNode ns) {
		List<OrgSgaiTreeNode> node = null;// commonsRomeotService.getSgaiTreeNode(sgaiToken, ns.getNodeId()).getData();
		for (OrgSgaiTreeNode nsa : node) {
			Integer nodeTyp = nsa.getNodeType();
			if (nodeTyp.intValue() == 1) {
				empIdsByTree.add(nsa);
			} else {
				nodeTree(sgaiToken, empIdsByTree, nsa);
			}
		}
	}

	/**
	 * 定时发布
	 *
	 * @param info
	 */
	private void cronPublic(NoticeInfo info) {
		// Auto-generated method stub
		Date dt = new Date();
		NoticeInfoTimePublish timePublish = new NoticeInfoTimePublish();
		timePublish.setId(info.getId());
		timePublish.setCreateTime(dt.getTime());
		timePublish.setUpdateTime(dt.getTime());
		timePublish.setInfoPublishPlanTime(info.getPublishTime());
		timePublish.setCreatedDt(dt);
		timePublish.setUpdatedDt(dt);
		timePublish.setInfoIsPublished(0L);
		timePublishDao.insert(timePublish);

		try {
			// 启动调度器
			scheduler.start();
			// 构建job信息
			JobDetail jobDetail = JobBuilder.newJob(PublishJob.class).withIdentity(info.getId(), "publishNoticeTime")
					.build();

			SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
			SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity(info.getId(), "publishNoticeTime")
					.startAt(new Date(info.getPublishTime())).withSchedule(scheduleBuilder).build();
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}

	}

	private List<String> getValidList(List<String> list) {
		List<String> newList = new ArrayList<String>();
		HashSet<String> set = new HashSet<>(list);
		newList.clear();
		newList.addAll(set);
		return newList;
	}

	public NoticeInfoVo getDetail4Edit(String id) {
		NoticeInfo info = infoDao.getById(id);
		if (info == null) {
			throw new BusinessException(ReturnType.DB, "没有数据");
		}
		NoticeInfoVo vo = new NoticeInfoVo();
		BeanCopier copier = BeanCopier.create(info.getClass(), vo.getClass(), false);
		copier.copy(info, vo, null);
		NoticeInfoScope scope = new NoticeInfoScope();
		scope.setInfoId(id);
		scope = infoScopeDao.get(scope);
		if (scope != null) {
			vo.setInfoScopeEmp(scope.getInfoScope());
			vo.setInfoScopeDept(scope.getInfoScopeDeparts());
			vo.setInfoScopeEmpNum(scope.getInfoScopeEmpNum());
			vo.setInfoScopeObject(scope.getInfoScopeObject());
		}

		return vo;
	}

	public String update(InfoCommitParam infoCommitParam) {
		Date dt = new Date();
		NoticeInfo info = new NoticeInfo();
		if (StringUtils.isBlank(infoCommitParam.getVisibilityScope())) {
			throw new BusinessException(ReturnType.Success, "可见范围不能为空！");
		}
		BeanCopier copier = BeanCopier.create(infoCommitParam.getClass(), info.getClass(), false);
		copier.copy(infoCommitParam, info, null);
		info.setInfoTimePublish(infoCommitParam.getInfoTimePublish() + "");
		if (UserServletContext.getUserInfo().getUserType().equals("user")) {
			info.setInfoCreatorType(Constants.Notice.CREATE_TYPE_EMP);
			info.setInfoCreatorId(UserServletContext.getUserInfo().getUserId());
			info.setInfoCreatorName(UserServletContext.getUserInfo().getUserName());
		} else {
			info.setInfoCreatorType(Constants.Notice.CREATE_TYPE_ORG);
			info.setInfoCreatorId(UserServletContext.getUserInfo().getUserId());
			info.setInfoCreatorName(UserServletContext.getUserInfo().getUserName());
		}
		info.setUpdateTime(dt.getTime());
		info.setUpdatedDt(dt);
		// 需要审核的设置发起审核时间
		if (info.getInfoApprovalFlag() != null && info.getInfoApprovalFlag() == 1) {
			info.setInfoStatus(Constants.Notice.DSH.longValue());
			info.setInitApprovalTime(dt.getTime());
		}
		// 立即发布
		if (info.getInfoApprovalFlag() != null && info.getInfoApprovalFlag() == 0) {
			info.setInfoStatus(Constants.Notice.YFB.longValue());
			info.setPublishTime(dt.getTime());
		}
		// 是否定时发布
		if (info.getInfoTimePublish() != null && info.getInfoTimePublish().equals("1")) {
			info.setInfoStatus(Constants.Notice.YTG.longValue());
		}
		infoDao.updateById(info);

		//
		NoticeInfoScope scope = new NoticeInfoScope();
		scope.setInfoId(info.getId());
		infoScopeDao.delete(scope);

		NoticeInfoScopeEmp scopeEmp = new NoticeInfoScopeEmp();
		scopeEmp.setInfoId(info.getId());
		infoScopeEmpDao.delete(scopeEmp);

		NoticeInfoReceipt receipt = new NoticeInfoReceipt();
		receipt.setInfoId(info.getId());
		infoReceiptDao.delete(receipt);

		NoticeInfoTimePublish timePublish = new NoticeInfoTimePublish();
		timePublish.setId(info.getId());
		timePublishDao.delete(timePublish);

		insertInfoAttr(info, infoCommitParam);
		return info.getId();
	}

	/**
	 * 信息拼接
	 */
	private String linkString(String sgaiToken, List<String> scopeDeparts) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < scopeDeparts.size(); i++) {
//			if (scopeDeparts.size() > 0){
//				sb.append(scopeDeparts.get(i));
//			}
//			sb.append("（");
//			if (scope.size() > 0){
//				sb.append(scope.get(i) + "人");
//			}
//			sb.append("）");
			List<CtlDept> data = baseDepartmentService.findDeptsByCodes(scopeDeparts.get(i));
			for (CtlDept dts : data) {
				sb.append(dts.getDeptName());
				sb.append("、");
			}
		}
		return sb.toString();
	}
}

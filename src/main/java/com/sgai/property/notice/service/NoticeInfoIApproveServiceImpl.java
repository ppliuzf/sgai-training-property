package com.sgai.property.notice.service;
import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.notice.dao.INoticeInfoScopeDao;
import com.sgai.property.notice.dao.INoticeInfoVoDao;
import com.sgai.property.notice.entity.*;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.commonService.vo.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class NoticeInfoIApproveServiceImpl {

	@Autowired
	INoticeInfoVoDao iNoticeInfoVoDao;
	@Autowired
	INoticeInfoScopeDao iNoticeInfoScopeDao;

	//我审批的列表（搜索）
	public Response<Page<NoticeResponseInfo>> cminfoPageList(NoticeInfoParam noticeInfoParam, int pageNum, int pageSize) {

		Response<Page<NoticeResponseInfo>> result = new Response<Page<NoticeResponseInfo>>();

		//判断权限登录人
		String userNo = UserServletContext.getUserInfo().getUserType();
		String creatorType;
		String empId;
		//组织
		if (userNo.equals("admin")) {
			empId = UserServletContext.getUserInfo().getComCode();
			creatorType = "1";
		} else {
			//发起人Id.
//			empId = UserServletContext.getUserInfo().getUserNo();
			empId = UserServletContext.getUserInfo().getEmCode();
			creatorType = "0";
		}

		NoticeISendParam approveParam = new NoticeISendParam();
		approveParam.setEmpId(empId);
		String keyword = noticeInfoParam.getKeyword();
		if(keyword != null && !keyword.equals("")){
			keyword = "'%"+keyword+"%'";
			approveParam.setKeyword(keyword);
		}
		approveParam.setStartCreateTime(noticeInfoParam.getStartCreateTime());
		approveParam.setEndCreateTime(noticeInfoParam.getEndCreateTime());
		approveParam.setStartPublishTime(noticeInfoParam.getStartPublishTime());
		approveParam.setEndPublishTime(noticeInfoParam.getEndPublishTime());
		approveParam.setComCode(UserServletContext.getUserInfo().getComCode());
		approveParam.setModuCode(UserServletContext.getUserInfo().getModuCode());
		approveParam.setCreatorType(creatorType);

		if (noticeInfoParam.getStatus().equals("DSH")) {
			approveParam.setDsh("1");
			//检索待审核的数据
			Page<NoticeInfo> pageInfo = new Page<NoticeInfo>(pageNum, pageSize);
			approveParam.setPage(pageInfo);
			pageInfo.setList(iNoticeInfoVoDao.cmFindDshList(approveParam));
			//copy 数据
			if (pageInfo.getList().size() > 0) {
				Page<NoticeResponseInfo> informationList = new Page<>();
				BeanUtils.copyProperties(pageInfo, informationList);

				result.setData(informationList);
			}

		} else if(noticeInfoParam.getStatus().equals("YSH")) {
			approveParam.setNoPass("2");
			approveParam.setPass("4");
			approveParam.setPublish("8");
			//检索已审核的数据
			Page<NoticeInfo> pageInfo = new Page<NoticeInfo>(pageNum,pageSize);
			approveParam.setPage(pageInfo);
			pageInfo.setList(iNoticeInfoVoDao.cmFindYshList(approveParam));
			//copy 数据
			if (pageInfo.getList().size() > 0) {
				Page<NoticeResponseInfo> informationList = new Page<>();
				BeanUtils.copyProperties(pageInfo, informationList);

				result.setData(informationList);
			}

		} else if(noticeInfoParam.getStatus().equals("YJJ")) {
			approveParam.setInfoStatus("5");
			//检索已审核的数据
			Page<NoticeInfo> pageInfo = new Page<NoticeInfo>(pageNum,pageSize);
			approveParam.setPage(pageInfo);
			pageInfo.setList(iNoticeInfoVoDao.cmFindInfo(approveParam));
			//copy 数据
			if (pageInfo.getList().size() > 0) {
				Page<NoticeResponseInfo> informationList = new Page<>();
				BeanUtils.copyProperties(pageInfo, informationList);

				result.setData(informationList);
			}

		} else if (noticeInfoParam.getStatus() == null || noticeInfoParam.getStatus().equals("")) {
			approveParam.setNoPass("16");
			//检索已审核的数据
			Page<NoticeInfo> pageInfo = new Page<NoticeInfo>(pageNum,pageSize);
			approveParam.setPage(pageInfo);
			pageInfo.setList(iNoticeInfoVoDao.cmFindList(approveParam));
			//copy 数据
			if (pageInfo.getList().size() > 0) {
				Page<NoticeResponseInfo> informationList = new Page<>();
				BeanUtils.copyProperties(pageInfo, informationList);

				result.setData(informationList);
			}

		}
			return result;
		}
	//web我审批的详情
    public Response<InfoDetail> cminfoDetail(String id) {

		InfoDetail noticeResponseInfo = new InfoDetail();
		Response<InfoDetail> result = new Response<>();

		//创建公告检索参数对象
		NoticeInfo noticeInfo = new NoticeInfo();
		noticeInfo.setId(id);

		//检索出数据
		NoticeInfo infoData = iNoticeInfoVoDao.get(noticeInfo);

		//拷贝数据到Response对象中
		if (infoData == null) {
			throw new BusinessException(ReturnType.Success, "暂无该资讯信息~");
		}
		BeanUtils.copyProperties(infoData,noticeResponseInfo);

		//发布范围处理
		if (noticeResponseInfo.getInfoScopeIsAll().intValue() == 0) {
			NoticeInfoScope infoScope = new NoticeInfoScope();
			infoScope.setInfoId(id);
			NoticeInfoScope scope = iNoticeInfoScopeDao.get(infoScope);
			noticeResponseInfo.setInfoScope(scope.getInfoScopeStr());
		}
		noticeResponseInfo.setPresentTime(new Date().getTime());
		result.setData(noticeResponseInfo);
		return result;

    }
	}
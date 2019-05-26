package com.sgai.property.notice.service;

import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.notice.dao.INoticeAppVoDao;
import com.sgai.property.notice.dao.INoticeInfoScopeDao;
import com.sgai.property.notice.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class NoticeInfoAppIApproveServiceImpl {

	@Autowired
	INoticeAppVoDao iNoticeAppVoDao;
	@Autowired
	INoticeInfoScopeDao iNoticeInfoScopeDao;

	//我审批的列表（搜索）
	public Response<List<NoticeResponseInfo>> appInfoPageList(NoticeInfoParam noticeInfoParam, int pageNum, int pagesize) {

		Response<List<NoticeResponseInfo>> result = new Response<List<NoticeResponseInfo>>();
		String empId = UserServletContext.getUserInfo().getUserNo();


		NoticeISendParam approveParam = new NoticeISendParam();
		approveParam.setEmpId(empId);
		approveParam.setPageNum((pageNum - 1)* pagesize);
		approveParam.setPageSize(pageNum * pagesize);

		if (noticeInfoParam.getStatus().equals("DSH")) {
			approveParam.setDsh("1");
			//检索待审核的数据
			List<NoticeInfo> noticeInfos = iNoticeAppVoDao.appFindDshList(approveParam);
			//copy 数据
			if (noticeInfos.size() > 0) {
				List<NoticeResponseInfo> noticeResponseInfos = getNoticeResponseInfos(noticeInfos);
				//前端必须参数处理
				List<NoticeResponseInfo> infoListData = flagOrStatus(noticeResponseInfos, noticeInfoParam);
				result.setData(infoListData);
			}

		} else if(noticeInfoParam.getStatus().equals("YSH")) {
			approveParam.setNoPass("2");
			approveParam.setPass("4");
			approveParam.setPublish("8");
			//检索已审核的数据
			List<NoticeInfo> noticeInfos = iNoticeAppVoDao.appFindYshList(approveParam);
			//copy 数据
			if (noticeInfos.size() > 0) {
				List<NoticeResponseInfo> noticeResponseInfos = getNoticeResponseInfos(noticeInfos);
				//前端必须参数处理
				List<NoticeResponseInfo> infoListData = flagOrStatus(noticeResponseInfos, noticeInfoParam);
				result.setData(infoListData);
			}

		} else if (noticeInfoParam.getStatus() == null || noticeInfoParam.getStatus().equals("")) {
			approveParam.setNoPass("16");
			//检索已审核的数据
			List<NoticeInfo> noticeInfos = iNoticeAppVoDao.appFindList(approveParam);
			//copy 数据
			if (noticeInfos.size() > 0) {
				List<NoticeResponseInfo> noticeResponseInfos = getNoticeResponseInfos(noticeInfos);
				//前端必须参数处理
				List<NoticeResponseInfo> infoListData = flagOrStatus(noticeResponseInfos, noticeInfoParam);
				result.setData(infoListData);
			}

		}
			return result;
		}

	//App我审批的详情
    public Response<InfoDetail> appInfoDetail(String id) {

		InfoDetail noticeResponseInfo = new InfoDetail();
		Response<InfoDetail> result = new Response<>();

		//创建公告检索参数对象
		NoticeInfo noticeInfo = new NoticeInfo();
		noticeInfo.setId(id);

		//检索出数据
		NoticeInfo infoData = iNoticeAppVoDao.get(noticeInfo);

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


	//拷贝数据到response对象中
	private List<NoticeResponseInfo> getNoticeResponseInfos(List<NoticeInfo> infoList) {
		List<NoticeResponseInfo> noticeList = new ArrayList<>();
		if (infoList != null && infoList.size() > 0){
			for (NoticeInfo info:infoList) {
				NoticeResponseInfo responseInfo = new NoticeResponseInfo();
				BeanUtils.copyProperties(info,responseInfo);
				noticeList.add(responseInfo);
			}
		}
		return noticeList;
	}
	//前端必须参数
	private List<NoticeResponseInfo> flagOrStatus(List<NoticeResponseInfo> noticeList, NoticeInfoParam noticeInfoParam) {

		for (int i = 0; i < noticeList.size() ; i++) {
			noticeList.get(i).setFlag(noticeInfoParam.getFlag());
			noticeList.get(i).setStatus(noticeInfoParam.getStatus());
		}
		return noticeList;
	}

	}
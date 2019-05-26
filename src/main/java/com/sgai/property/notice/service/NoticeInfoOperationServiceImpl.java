package com.sgai.property.notice.service;
import com.sgai.property.notice.dao.INoticeAppVoDao;
import com.sgai.property.notice.dao.INoticeInfoDao;
import com.sgai.property.notice.dao.INoticeInfoScopeDao;
import com.sgai.property.notice.dao.INoticeInfoScopeEmpDao;
import com.sgai.property.notice.entity.*;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.notice.constants.Constants;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.notice.vo.SendDetailVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class NoticeInfoOperationServiceImpl{

	@Autowired
	INoticeInfoDao iNoticeInfoDao;
	@Autowired
	INoticeInfoScopeEmpDao noticeInfoScopeEmpDao;
	@Autowired
	INoticeAppVoDao iNoticeAppVoDao;
	@Autowired
	INoticeInfoScopeDao infoScopeDao;

	public SendDetailVo sendDetail(String infoId) {

		SendDetailVo sendVo = new SendDetailVo();
		//查公告信息
		NoticeInfo info = new NoticeInfo();
		info.setId(infoId);
		info = iNoticeAppVoDao.get(info);
		if(info != null) {
			sendVo.setInfoScopeIsAll(info.getInfoScopeIsAll());
//			sendVo.setInfoReceiptFlag(info.getInfoReceiptFlag());
			sendVo.setInfoApprovalFlag(info.getInfoApprovalFlag());
			sendVo.setApprovalEmpId(info.getApprovalEmpId());
			sendVo.setApprovalEmpName(info.getApprovalEmpName());
			sendVo.setInfoCreatorId(info.getInfoCreatorId());
			sendVo.setInfoCreatorName(info.getInfoCreatorName());

			String scopeStr = "";
			//如果发布范围不是公开，全部可见
			if(info.getInfoScopeIsAll().intValue() == Constants.Notice.INFO_SCOPE_IS_NOT_ALL.intValue()) {
				NoticeInfoScope scope = new NoticeInfoScope();
				scope.setInfoId(info.getId());
				scope = infoScopeDao.get(scope);
				if(scope != null) {
					scopeStr = scope.getInfoScopeStr();
				}
			} else {
				scopeStr = "公开，全部可见";
			}
			sendVo.setInfoScope(scopeStr);
		} else {
			throw new BusinessException(ReturnType.DB, "没有查到公告信息");
		}
		return sendVo;
	}

	/**
	*@Author 杨鹏伟 【syswin.600111】
	*@Date 2017/12/28 9:27
	*@ClassName NoticeInfoOperationServiceImpl
	*@MethodName 审批公告
	*@MethodParameters
	*/
    public Boolean infoApproval(ApprovalParam approvalParam) {

    	//查审核状态
    	NoticeInfo info = new NoticeInfo();
    	info.setId(approvalParam.getId());
    	info = iNoticeInfoDao.get(info);


    	if(info != null) {
    		//如果不是待审核状态
        	if(info.getInfoStatus().intValue() != Constants.Notice.DSH.intValue()) {
        		throw new BusinessException(ReturnType.DB, "公告不是待审核状态");
        	} else {
        		//更新审核状态
        		NoticeInfo infoUpdate = new NoticeInfo();
        		infoUpdate.setId(info.getId());
        		if(approvalParam.getApprovalStatus().equals("Y")) {
        			infoUpdate.setInfoStatus(Constants.Notice.YTG.longValue());
        		} else {
        			infoUpdate.setInfoStatus(Constants.Notice.WTG.longValue());
        		}
        		infoUpdate.setApprovalOpinition(approvalParam.getApprovalOpinition());
        		Date date = new Date();
        		infoUpdate.setApprovalTime(date.getTime());
				infoUpdate.setUpdateTime(date.getTime());
        		int count = iNoticeInfoDao.updateById(infoUpdate);
                return count == 1;
        	}
    	} else {
    		throw new BusinessException(ReturnType.DB, "没有查到公告信息");
    	}
    }

	/**
	*@Author 杨鹏伟 【syswin.600111】
	*@Date 2017/12/28 9:27
	*@ClassName NoticeInfoOperationServiceImpl
	*@MethodName 发布公告
	*@MethodParameters
	*/
    public String infoPublish(String id) {

    	//查发布状态
		NoticeInfo info = new NoticeInfo();
		info.setId(id);
		info = iNoticeInfoDao.get(info);

    	if(info != null) {
    		//如果不是待发布(审核通过)状态
    		if(info.getInfoStatus().intValue() != Constants.Notice.YTG.intValue()) {
    			throw new BusinessException(ReturnType.DB, "公告不是待发布状态");
    		} else {
    			//更新发布状态
    			NoticeInfo infoUpdate = new NoticeInfo();
    			Date date = new Date();
    			infoUpdate.setId(info.getId());
    			infoUpdate.setInfoStatus(Constants.Notice.YFB.longValue());
    			infoUpdate.setPublishTime(date.getTime());
    			infoUpdate.setUpdateTime(date.getTime());
    			int count = iNoticeInfoDao.updateById(infoUpdate);
    			if(count == 1){
    				//返回发布时间
    				return date.getTime() + "";
    			}
    		}
    	} else {
    		throw new BusinessException(ReturnType.DB, "没有查到公告信息");
    	}
		return "false";
    }

    /**
    *@Author 杨鹏伟 【syswin.600111】
    *@Date 2017/12/28 13:57
    *@ClassName NoticeInfoOperationServiceImpl
    *@MethodName 撤回公告
    *@MethodParameters
    */
	public Boolean infoRetract(String id) {

		//查撤回状态
		NoticeInfo info = new NoticeInfo();
		info.setId(id);
		info = iNoticeInfoDao.get(info);

		if(info != null) {
			//如果已撤回
			if(info.getInfoStatus().intValue() == Constants.Notice.YCH.intValue()) {
				throw new BusinessException(ReturnType.DB, "公告已撤回");
			} else {
				//更新撤回状态
				NoticeInfo infoUpdate = new NoticeInfo();
				Date date = new Date();
				infoUpdate.setId(info.getId());
				infoUpdate.setInfoStatus(Constants.Notice.YCH.longValue());
				infoUpdate.setRetractTime(date.getTime());
				infoUpdate.setInfoIsTop(Constants.Notice.INFO_IS_TOP_N);
				infoUpdate.setInfoTopTime(0L);
				int count = iNoticeInfoDao.updateById(infoUpdate);
                return count == 1;
			}
		} else {
			throw new BusinessException(ReturnType.DB, "没有查到公告信息");
		}
    }
	/**
	*@Author 杨鹏伟 【syswin.600111】
	*@Date 2017/12/28 10:43
	*@ClassName NoticeInfoOperationServiceImpl
	*@MethodName 公告置顶
	*@MethodParameters
	*/
	public Boolean infoIsTop(ApprovalParam approvalParam) {

		//查撤回状态
		NoticeInfo info = new NoticeInfo();
		info.setId(approvalParam.getId());
		info = iNoticeInfoDao.get(info);

		Date date = new Date();

		if(info != null) {
			//更新置顶状态
			NoticeInfo infoUpdate = new NoticeInfo();
			infoUpdate.setId(info.getId());
			//Y置顶
			if(approvalParam.getApprovalStatus().equals("Y")) {
				infoUpdate.setInfoIsTop(Constants.Notice.INFO_IS_TOP_Y);
				infoUpdate.setInfoTopTime(date.getTime());
			} else {
				infoUpdate.setInfoIsTop(Constants.Notice.INFO_IS_TOP_N);
				infoUpdate.setInfoTopTime(0L);
			}

			int count = iNoticeInfoDao.updateById(infoUpdate);
            return count == 1;
		} else {
			throw new BusinessException(ReturnType.DB, "没有查到公告信息");
		}
    }


	/**
	*@Author 杨鹏伟 【syswin.600111】
	*@Date 2018/1/3 9:18
	*@ClassName NoticeInfoOperationServiceImpl
	*@MethodName App 搜索接口
	*@MethodParameters
	*/
	public Response<List<NoticeResponseInfo>> selectInfo(NoticeInfoParam noticeInfoParam, String empId, int pageNum, int pageSize) {

		Response<List<NoticeResponseInfo>> result = new Response<List<NoticeResponseInfo>>();

		NoticeISendParam sendParam = new NoticeISendParam();
		sendParam.setEmpId(empId);
		sendParam.setPageNum((pageNum-1) * pageSize);
		sendParam.setPageSize(pageNum * pageSize);

		String keyword = noticeInfoParam.getKeyword();
		if(keyword != null && !keyword.equals("")){
			keyword = "'%"+keyword+"%'";
			sendParam.setKeyword(keyword);
		}

		if(noticeInfoParam.getFlag().equals("WSD")){

			//创建参数对象
			NoticeInfoScopeEmp infoScopeEmp = new NoticeInfoScopeEmp();
			infoScopeEmp.setEmpId(empId);
			//发布范围表检索公告ID
			List<NoticeInfoScopeEmp> infoScopeEmps = noticeInfoScopeEmpDao.findList(infoScopeEmp);

			//公告表检索出 全部可见的 公告Id
			NoticeInfo noticeInfo = new NoticeInfo();
			noticeInfo.setInfoScopeIsAll(1L);
			//检索出 全部可见数据
			List<NoticeInfo> scopeIsAlls = iNoticeAppVoDao.findList(noticeInfo);

			//创建数组参数，容纳 部分可见的 公告Id
			String [] scopeEmpstArry = new String[infoScopeEmps.size()];
			//创建数组参数，容纳 全部可见的 公告Id
			String [] scopeIsAllArry = new String[scopeIsAlls.size()];

			if(infoScopeEmps.size() > 0){
				//循环赋值资讯ID给数组；
				for (int i = 0; i < infoScopeEmps.size(); i++) {
					scopeEmpstArry[i] = infoScopeEmps.get(i).getInfoId();
				}
			}

			if (scopeIsAlls != null && scopeIsAlls.size() > 0) {
				//循环赋值公告Id给数组；
				for (int i = 0; i < scopeIsAlls.size(); i++) {
					scopeIsAllArry[i] = scopeIsAlls.get(i).getId();
				}

			}

			//求出两个数组的并集，用于检索公告数据
			String[] allArray = union(scopeEmpstArry, scopeIsAllArry);
			if (allArray.length ==0) {
				throw new BusinessException(ReturnType.Success,"暂无数据~");
			}
			//检索公告表，查出相应数据；
			sendParam.setReceiptArry(allArray);
			sendParam.setInfoStatus("8");
			List<NoticeInfo> infoData = iNoticeAppVoDao.findNoticeList(sendParam);
			if (infoData.size() == 0) {
				throw new BusinessException(ReturnType.Success,"暂无数据~");
			}
			//拷贝数据到response对象中
			List<NoticeResponseInfo> noticeList = getNoticeResponseInfos(infoData);

			//前端需要返回的参数
			for (int i = 0; i < noticeList.size(); i++) {
				noticeList.get(i).setFlag(noticeInfoParam.getFlag());
			}
			result.setData(noticeList);

		} else if(noticeInfoParam.getFlag().equals("WSH") && noticeInfoParam.getStatus().equals("DSH")){
			sendParam.setDsh("1");
			//检索待审核公告
			List<NoticeInfo> infoList =  iNoticeAppVoDao.findMeDshData(sendParam);
			if (infoList.size() == 0) {
				throw new BusinessException(ReturnType.Success,"暂无数据~");
			}

			//拷贝数据到response对象中
			List<NoticeResponseInfo> noticeList = getNoticeResponseInfos(infoList);

			//前端必须参数处理
			List<NoticeResponseInfo> infoListData = flagOrStatus(noticeList, noticeInfoParam);
			result.setData(infoListData);

		}else if(noticeInfoParam.getFlag().equals("WSH") && noticeInfoParam.getStatus().equals("YSH")){
			sendParam.setNoPass("2");
			sendParam.setPass("4");
			sendParam.setPublish("8");
			//检索待发布公告
			List<NoticeInfo> infoList =  iNoticeAppVoDao.findMeYshData(sendParam);
			if (infoList.size() == 0) {
				throw new BusinessException(ReturnType.Success,"暂无数据~");
			}

			//拷贝数据到response对象中
			List<NoticeResponseInfo> noticeList = getNoticeResponseInfos(infoList);

			//前端必须参数处理
			List<NoticeResponseInfo> infoListData = flagOrStatus(noticeList, noticeInfoParam);
			result.setData(infoListData);

		}else if(noticeInfoParam.getFlag().equals("WFQ") && noticeInfoParam.getStatus().equals("DFB")){
			sendParam.setInfoStatus("4");
			//检索待发布公告
			List<NoticeInfo> infoList =  iNoticeAppVoDao.appSelectFindInfo(sendParam);
			if (infoList.size() == 0) {
				throw new BusinessException(ReturnType.Success,"暂无数据~");
			}

			//拷贝数据到response对象中
			List<NoticeResponseInfo> noticeList = getNoticeResponseInfos(infoList);

			//前端必须参数处理
			List<NoticeResponseInfo> infoListData = flagOrStatus(noticeList, noticeInfoParam);
			result.setData(infoListData);

		}else if(noticeInfoParam.getFlag().equals("WFQ") && noticeInfoParam.getStatus().equals("DSH")){
			sendParam.setInfoStatus("1");
			//检索待审核公告
			List<NoticeInfo> infoList =  iNoticeAppVoDao.appSelectFindInfo(sendParam);
			if (infoList.size() == 0) {
				throw new BusinessException(ReturnType.Success,"暂无数据~");
			}

			//拷贝数据到response对象中
			List<NoticeResponseInfo> noticeList = getNoticeResponseInfos(infoList);

			//前端必须参数处理
			List<NoticeResponseInfo> infoListData = flagOrStatus(noticeList, noticeInfoParam);
			result.setData(infoListData);

		}else if(noticeInfoParam.getFlag().equals("WFQ") && noticeInfoParam.getStatus().equals("YFB")){
			sendParam.setInfoStatus("8");
			//检索已发布公告
			List<NoticeInfo> infoList =  iNoticeAppVoDao.appSelectFindInfo(sendParam);
			if (infoList.size() == 0) {
				throw new BusinessException(ReturnType.Success,"暂无数据~");
			}

			//拷贝数据到response对象中
			List<NoticeResponseInfo> noticeList = getNoticeResponseInfos(infoList);

			//前端必须参数处理
			List<NoticeResponseInfo> infoListData = flagOrStatus(noticeList, noticeInfoParam);
			result.setData(infoListData);

		}else if(noticeInfoParam.getFlag().equals("WFQ") && noticeInfoParam.getStatus().equals("YCH")){
			sendParam.setNoPass("2");
			sendParam.setRevocation("16");
			//检索以撤回公告
			List<NoticeInfo> infoList =  iNoticeAppVoDao.findYchData(sendParam);
			if (infoList.size() == 0) {
				throw new BusinessException(ReturnType.Success,"暂无数据~");
			}

			//拷贝数据到response对象中
			List<NoticeResponseInfo> noticeList = getNoticeResponseInfos(infoList);

			//前端必须参数处理
			List<NoticeResponseInfo> infoListData = flagOrStatus(noticeList, noticeInfoParam);
			result.setData(infoListData);
		}
		return result;
	}
	//求出数组的并集
	public static String[] union (String[] arr1, String[] arr2){
		Set<String> hs = new HashSet<String>();
		if(arr1.length > 0){
			for(String str:arr1){
				hs.add(str);
			}
		}
		if(arr2.length > 0){
			for(String str:arr2){
				hs.add(str);
			}
		}
		String[] result={};
		return hs.toArray(result);
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
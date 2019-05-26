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
public class NoticeInfoAppISendServiceImpl {

    @Autowired
    INoticeAppVoDao iNoticeAppVoDao;
    @Autowired
    INoticeInfoScopeDao iNoticeInfoScopeDao;

    //web我发起的列表（搜索）
    public Response<List<NoticeResponseInfo>> appInfoPageList(NoticeInfoParam noticeInfoParam, int pageNum, int pagesize) {

        Response<List<NoticeResponseInfo>> result = new Response<List<NoticeResponseInfo>>();

            //判断权限登录人
            String empId = UserServletContext.getUserInfo().getUserNo();

            NoticeISendParam sendParam = new NoticeISendParam();
            sendParam.setEmpId(empId);
            sendParam.setPageNum((pageNum - 1)* pagesize);
            sendParam.setPageSize(pageNum * pagesize);

        if (noticeInfoParam.getStatus().equals("DFB")) {
            sendParam.setInfoStatus("4");
            List<NoticeInfo> noticeInfos = iNoticeAppVoDao.appFindInfo(sendParam);
            //copy 数据
            if (noticeInfos.size() > 0) {
                List<NoticeResponseInfo> noticeResponseInfos = getNoticeResponseInfos(noticeInfos);
                //前端必须参数处理
                List<NoticeResponseInfo> infoListData = flagOrStatus(noticeResponseInfos, noticeInfoParam);
                result.setData(infoListData);
            }
        } else if(noticeInfoParam.getStatus().equals("DSH")) {
            sendParam.setInfoStatus("1");
            //检索待审核公告
            List<NoticeInfo> noticeInfos = iNoticeAppVoDao.appFindInfo(sendParam);
            //copy 数据
            if (noticeInfos.size() > 0) {
                List<NoticeResponseInfo> noticeResponseInfos = getNoticeResponseInfos(noticeInfos);
                //前端必须参数处理
                List<NoticeResponseInfo> infoListData = flagOrStatus(noticeResponseInfos, noticeInfoParam);
                result.setData(infoListData);
            }
        } else if(noticeInfoParam.getStatus().equals("YFB")){

            sendParam.setInfoStatus("8");
            //检索已发布公告
            List<NoticeInfo> noticeInfos = iNoticeAppVoDao.appFindInfo(sendParam);
            //copy 数据
            if (noticeInfos.size() > 0) {
                List<NoticeResponseInfo> noticeResponseInfos = getNoticeResponseInfos(noticeInfos);
                //前端必须参数处理
                List<NoticeResponseInfo> infoListData = flagOrStatus(noticeResponseInfos, noticeInfoParam);
                result.setData(infoListData);
            }
        }else if(noticeInfoParam.getStatus().equals("YCH")){
            sendParam.setNoPass("2");
            sendParam.setRevocation("16");
            //检索已撤回公告
            List<NoticeInfo> noticeInfos = iNoticeAppVoDao.appFindNotice(sendParam);
            //copy 数据
            if (noticeInfos.size() > 0) {
                List<NoticeResponseInfo> noticeResponseInfos = getNoticeResponseInfos(noticeInfos);
                //前端必须参数处理
                List<NoticeResponseInfo> infoListData = flagOrStatus(noticeResponseInfos, noticeInfoParam);
                result.setData(infoListData);
            }
        } else if (noticeInfoParam.getStatus() == null || noticeInfoParam.getStatus().equals("")) {

            //检索全部公告
            List<NoticeInfo> noticeInfos = iNoticeAppVoDao.appFindInfo(sendParam);
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

    //web我发起的详情
    public Response<InfoDetail> appInfoDetail(String id) {

        Response<InfoDetail> result = new Response<>();

        //查询当前公告信息
        NoticeInfo noticeInfo = new NoticeInfo();
        noticeInfo.setId(id);

        NoticeInfo responseInfo = iNoticeAppVoDao.get(noticeInfo);
        InfoDetail infoDetail = new InfoDetail();

        if (responseInfo != null) {
            BeanUtils.copyProperties(responseInfo,infoDetail);
        }else {
            throw new BusinessException(ReturnType.Success,"暂无该数据信息~");
        }
        //发布范围处理
        if (infoDetail.getInfoScopeIsAll().intValue() == 0) {
            NoticeInfoScope infoScope = new NoticeInfoScope();
            infoScope.setInfoId(id);
            NoticeInfoScope scope = iNoticeInfoScopeDao.get(infoScope);
            infoDetail.setInfoScope(scope.getInfoScopeStr());
        }

        infoDetail.setPresentTime(new Date().getTime());
        result.setData(infoDetail);

        return result ;

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
package com.sgai.property.notice.service;


import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.notice.dao.INoticeInfoScopeDao;
import com.sgai.property.notice.dao.INoticeInfoVoDao;
import com.sgai.property.notice.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class NoticeInfoISendServiceImpl {

    @Autowired
    INoticeInfoVoDao iNoticeInfoVoDao;
    @Autowired
    INoticeInfoScopeDao iNoticeInfoScopeDao;

    //web我发起的列表（搜索）
    public Response<Page<NoticeResponseInfo>> cmInfoPageList(NoticeInfoParam noticeInfoParam, int pageNum, int pageSize) {

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
//            empId = UserServletContext.getUserInfo().getUserNo();
            empId = UserServletContext.getUserInfo().getUserNo();
            creatorType = "0";
        }
        NoticeISendParam sendParam = new NoticeISendParam();
        sendParam.setEmpId(empId);
        String keyword = noticeInfoParam.getKeyword();
        if (keyword != null && !keyword.equals("")) {
            keyword = "'%" + keyword + "%'";
            sendParam.setKeyword(keyword);
        }
        sendParam.setStartCreateTime(noticeInfoParam.getStartCreateTime());
        sendParam.setEndCreateTime(noticeInfoParam.getEndCreateTime());
        sendParam.setStartPublishTime(noticeInfoParam.getStartPublishTime());
        sendParam.setEndPublishTime(noticeInfoParam.getEndPublishTime());
        sendParam.setComCode(UserServletContext.getUserInfo().getComCode());
        sendParam.setModuCode(UserServletContext.getUserInfo().getModuCode());
        sendParam.setCreatorType(creatorType);
        if (noticeInfoParam.getStatus().equals("DFB")) {
            sendParam.setInfoStatus("4");
            Page<NoticeInfo> pageInfo = new Page<NoticeInfo>(pageNum, pageSize);
            sendParam.setPage(pageInfo);
            //检索待发布公告
            pageInfo.setList(iNoticeInfoVoDao.cmFindInfo(sendParam));
            //copy 数据
            if (pageInfo.getList().size() > 0) {
                Page<NoticeResponseInfo> informationList = new Page<>();
                BeanUtils.copyProperties(pageInfo, informationList);

                result.setData(informationList);
            }
        } else if (noticeInfoParam.getStatus().equals("DSH")) {
            sendParam.setInfoStatus("1");
            //检索待审核公告
            Page<NoticeInfo> pageInfo = new Page<NoticeInfo>(pageNum, pageSize);
            sendParam.setPage(pageInfo);
            pageInfo.setList(iNoticeInfoVoDao.cmFindInfo(sendParam));
            //copy 数据
            if (pageInfo.getList().size() > 0) {
                Page<NoticeResponseInfo> informationList = new Page<>();
                BeanUtils.copyProperties(pageInfo, informationList);

                result.setData(informationList);
            }
        } else if (noticeInfoParam.getStatus().equals("YFB")) {

            sendParam.setInfoStatus("8");
            //检索已发布公告
            Page<NoticeInfo> pageInfo = new Page<NoticeInfo>(pageNum, pageSize);
            sendParam.setPage(pageInfo);
            pageInfo.setList(iNoticeInfoVoDao.cmFindInfo(sendParam));
            //copy 数据
            if (pageInfo.getList().size() > 0) {
                Page<NoticeResponseInfo> informationList = new Page<>();
                BeanUtils.copyProperties(pageInfo, informationList);

                result.setData(informationList);
            }
        } else if (noticeInfoParam.getStatus().equals("YCH")) {
            sendParam.setNoPass("200");
            sendParam.setRevocation("16");
            //检索已撤回公告
            Page<NoticeInfo> pageInfo = new Page<NoticeInfo>(pageNum, pageSize);
            sendParam.setPage(pageInfo);
            pageInfo.setList(iNoticeInfoVoDao.cmFindNotice(sendParam));
            //copy 数据
            if (pageInfo.getList().size() > 0) {
                Page<NoticeResponseInfo> informationList = new Page<>();
                BeanUtils.copyProperties(pageInfo, informationList);

                result.setData(informationList);
            }
        } else if (noticeInfoParam.getStatus().equals("YJJ")) {
            sendParam.setInfoStatus("2");
            //检索已撤回公告
            Page<NoticeInfo> pageInfo = new Page<NoticeInfo>(pageNum, pageSize);
            sendParam.setPage(pageInfo);
            pageInfo.setList(iNoticeInfoVoDao.cmFindInfo(sendParam));
            //copy 数据
            if (pageInfo.getList().size() > 0) {
                Page<NoticeResponseInfo> informationList = new Page<>();
                BeanUtils.copyProperties(pageInfo, informationList);

                result.setData(informationList);
            }
        } else if (noticeInfoParam.getStatus() == null || noticeInfoParam.getStatus().equals("")) {

            //检索全部公告
            Page<NoticeInfo> pageInfo = new Page<NoticeInfo>(pageNum, pageSize);
            sendParam.setPage(pageInfo);
            pageInfo.setList(iNoticeInfoVoDao.cmFindInfo(sendParam));
            //copy 数据
            if (pageInfo.getList().size() > 0) {
                Page<NoticeResponseInfo> informationList = new Page<>();
                BeanUtils.copyProperties(pageInfo, informationList);

                result.setData(informationList);
            }

        }

        return result;
    }

    //web我发起的详情
    public Response<InfoDetail> cminfoDetail(String id) {

        Response<InfoDetail> result = new Response<>();

        //查询当前公告信息
        NoticeInfo noticeInfo = new NoticeInfo();
        noticeInfo.setId(id);

        NoticeInfo responseInfo = iNoticeInfoVoDao.get(noticeInfo);
        InfoDetail infoDetail = new InfoDetail();

        if (responseInfo != null) {
            BeanUtils.copyProperties(responseInfo, infoDetail);
        } else {
            throw new BusinessException(ReturnType.Success, "暂无该数据信息~");
        }
        //发布范围处理
        if (infoDetail.getInfoScopeIsAll().intValue() == 0) {
            NoticeInfoScope infoScope = new NoticeInfoScope();
            infoScope.setInfoId(id);
            NoticeInfoScope scope = iNoticeInfoScopeDao.get(infoScope);
            infoDetail.setInfoScope(scope.getInfoScopeStr());
        }

        infoDetail.setPresentTime(new Date().getTime());
        infoDetail.setInfoTimePublish(Long.parseLong(responseInfo.getInfoTimePublish()));
        result.setData(infoDetail);

        return result;

    }
}
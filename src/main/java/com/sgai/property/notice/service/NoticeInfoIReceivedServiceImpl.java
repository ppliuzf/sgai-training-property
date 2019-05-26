package com.sgai.property.notice.service;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.notice.dao.INoticeInfoScopeDao;
import com.sgai.property.notice.dao.INoticeInfoScopeEmpDao;
import com.sgai.property.notice.dao.INoticeInfoVoDao;
import com.sgai.property.notice.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class NoticeInfoIReceivedServiceImpl {

    @Autowired
    INoticeInfoScopeEmpDao noticeInfoScopeEmpDao;
    @Autowired
    INoticeInfoVoDao iNoticeInfoVoDao;
    @Autowired
    INoticeInfoScopeDao iNoticeInfoScopeDao;
    //我收到的列表
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
            empId = UserServletContext.getUserInfo().getEmCode();
            creatorType = "0";
        }

        //创建参数对象
        NoticeInfoScopeEmp infoScopeEmp = new NoticeInfoScopeEmp();
        infoScopeEmp.setEmpId(empId);
        //发布范围表检索公告ID
        List<NoticeInfoScopeEmp> infoScopeEmps = noticeInfoScopeEmpDao.findList(infoScopeEmp);

        //公告表检索出 全部可见的 公告Id
        NoticeInfo noticeInfo = new NoticeInfo();
        noticeInfo.setInfoScopeIsAll(1L);
        //检索出 全部可见数据
        List<NoticeInfo> scopeIsAlls = iNoticeInfoVoDao.findList(noticeInfo);

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

        if (scopeIsAlls.size() > 0) {
            //循环赋值公告Id给数组；
            for (int i = 0; i < scopeIsAlls.size(); i++) {
                scopeIsAllArry[i] = scopeIsAlls.get(i).getId();
            }

        }

        //求出两个数组的并集，用于检索公告数据
        String[] allArray = union(scopeEmpstArry, scopeIsAllArry);
        if (allArray.length == 0 || allArray == null) {
            throw new BusinessException(ReturnType.Success,"暂无数据~");
        }
        //检索公告表，查出相应数据；
        NoticeISendParam sendParam = new NoticeISendParam();
        sendParam.setReceiptArry(allArray);
        sendParam.setInfoStatus("8");
        sendParam.setInfoUrgency(noticeInfoParam.getInfoUrgency());
        String keyword = noticeInfoParam.getKeyword();
        if(keyword != null && !keyword.equals("")){
            keyword = "'%"+keyword+"%'";
            sendParam.setKeyword(keyword);
        }
        sendParam.setComCode(UserServletContext.getUserInfo().getComCode());
        sendParam.setModuCode(UserServletContext.getUserInfo().getModuCode());
        Page<NoticeInfo> pageInfo = new Page<NoticeInfo>(pageNum, pageSize);
        sendParam.setPage(pageInfo);
        pageInfo.setList(iNoticeInfoVoDao.cmInfoPageList(sendParam));
        //拷贝数据到response对象中
        if (pageInfo.getList().size() > 0) {
            Page<NoticeResponseInfo> informationList = new Page<>();
            BeanUtils.copyProperties(pageInfo, informationList);
            result.setData(informationList);
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

}
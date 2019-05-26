package com.sgai.property.notice.dao;

import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.notice.entity.NoticeISendParam;
import com.sgai.property.notice.entity.NoticeInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface INoticeAppVoDao extends MoreDataSourceDao<NoticeInfo> {

    /**
    *@Author 杨鹏伟 【syswin.600111】
    *@Date 2017/12/26 14:11
    *@ClassName INoticeInfoVoDao
    *@MethodName App发起的列表
    *@MethodParameters 
    */
    List<NoticeInfo> appFindInfo(NoticeISendParam sendParam);

    List<NoticeInfo> appFindNotice(NoticeISendParam sendParam);
    /**
    *@Author 杨鹏伟 【syswin.600111】
    *@Date 2017/12/26 14:12
    *@ClassName INoticeInfoVoDao
    *@MethodName App审核的待审核
    *@MethodParameters
    */
    List<NoticeInfo> appFindDshList(NoticeISendParam sendParam);
    
    /**
    *@Author 杨鹏伟 【syswin.600111】
    *@Date 2017/12/26 14:33
    *@ClassName INoticeInfoVoDao
    *@MethodName App审核的已审核
    *@MethodParameters 
    */
    List<NoticeInfo> appFindYshList(NoticeISendParam sendParam);
    
    /**
    *@Author 杨鹏伟 【syswin.600111】
    *@Date 2017/12/26 14:33
    *@ClassName INoticeInfoVoDao
    *@MethodName App审核的全部数据
    *@MethodParameters 
    */
    List<NoticeInfo> appFindList(NoticeISendParam sendParam);

    /**
     *@Author 杨鹏伟 【syswin.600111】
     *@Date 2017/12/26 14:33
     *@ClassName INoticeInfoVoDao
     *@MethodName App收到的全部数据
     *@MethodParameters
     */
    List<NoticeInfo> appInfoPageList(NoticeISendParam sendParam);
    /**
    *@Author 杨鹏伟 【syswin.600111】
    *@Date 2018/1/3 9:33
    *@ClassName INoticeAppVoDao
    *@MethodName App搜索我收到的
    *@MethodParameters
    */
    List<NoticeInfo> findNoticeList(NoticeISendParam sendParam);
    /**
    *@Author 杨鹏伟 【syswin.600111】
    *@Date 2018/1/3 10:10
    *@ClassName INoticeAppVoDao
    *@MethodName App搜索我审核的待审核
    *@MethodParameters 
    */
    List<NoticeInfo> findMeDshData(NoticeISendParam sendParam);
    /**
    *@Author 杨鹏伟 【syswin.600111】
    *@Date 2018/1/3 10:17
    *@ClassName INoticeAppVoDao
    *@MethodName App搜索我审核的已审核
    *@MethodParameters 
    */
    List<NoticeInfo> findMeYshData(NoticeISendParam sendParam);
    /**
    *@Author 杨鹏伟 【syswin.600111】
    *@Date 2018/1/3 10:31
    *@ClassName INoticeAppVoDao
    *@MethodName App搜索我发起的待发布,待审核,已发布
    *@MethodParameters 
    */
    List<NoticeInfo> appSelectFindInfo(NoticeISendParam sendParam);

    /**
    *@Author 杨鹏伟 【syswin.600111】
    *@Date 2018/1/3 10:33
    *@ClassName INoticeAppVoDao
    *@MethodName App 搜索我发起的已撤回
    *@MethodParameters
    */
    List<NoticeInfo> findYchData(NoticeISendParam sendParam);
}
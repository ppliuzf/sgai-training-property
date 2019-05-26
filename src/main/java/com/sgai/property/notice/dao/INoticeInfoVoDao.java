package com.sgai.property.notice.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.notice.entity.NoticeISendParam;
import com.sgai.property.notice.entity.NoticeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;


@Mapper
public interface INoticeInfoVoDao extends MoreDataSourceDao<NoticeInfo> {

    /**
    *@Author 杨鹏伟 【syswin.600111】
    *@Date 2017/12/26 14:11
    *@ClassName INoticeInfoVoDao
    *@MethodName 我发起的列表
    *@MethodParameters 
    */
    List<NoticeInfo> cmFindInfo(NoticeISendParam sendParam);

    List<NoticeInfo> cmFindNotice(NoticeISendParam sendParam);
    /**
    *@Author 杨鹏伟 【syswin.600111】
    *@Date 2017/12/26 14:12
    *@ClassName INoticeInfoVoDao
    *@MethodName 我审核的待审核
    *@MethodParameters
    */
    List<NoticeInfo> cmFindDshList(NoticeISendParam sendParam);
    
    /**
    *@Author 杨鹏伟 【syswin.600111】
    *@Date 2017/12/26 14:33
    *@ClassName INoticeInfoVoDao
    *@MethodName 我审核的已审核
    *@MethodParameters 
    */
    List<NoticeInfo> cmFindYshList(NoticeISendParam sendParam);
    
    /**
    *@Author 杨鹏伟 【syswin.600111】
    *@Date 2017/12/26 14:33
    *@ClassName INoticeInfoVoDao
    *@MethodName 我审核的全部数据
    *@MethodParameters 
    */
    List<NoticeInfo> cmFindList(NoticeISendParam sendParam);

    /**
     *@Author 杨鹏伟 【syswin.600111】
     *@Date 2017/12/26 14:33
     *@ClassName INoticeInfoVoDao
     *@MethodName 我收到的全部数据
     *@MethodParameters
     */
    List<NoticeInfo> cmInfoPageList(NoticeISendParam sendParam);
}
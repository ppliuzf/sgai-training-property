package com.sgai.property.customer.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.customer.entity.SurveyUserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ISurveyUserInfoDao extends MoreDataSourceDao<SurveyUserInfo> {

    void batchInsert(List<SurveyUserInfo> list);
}
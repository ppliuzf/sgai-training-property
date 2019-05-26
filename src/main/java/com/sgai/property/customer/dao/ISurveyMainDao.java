package com.sgai.property.customer.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.customer.entity.SurveyMain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ISurveyMainDao extends MoreDataSourceDao<SurveyMain> {

    List<SurveyMain> getNotFinishSurvey();

    void updateSurveyStatusToEnd(String id);
}
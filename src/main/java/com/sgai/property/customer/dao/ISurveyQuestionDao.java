package com.sgai.property.customer.dao;

import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.customer.entity.SurveyQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ISurveyQuestionDao extends MoreDataSourceDao<SurveyQuestion> {

    /**
     * 批量插入问卷问题
     * @param surveyQuestionList
     * @return
     */
    void insertSurveyQuestionList(List<SurveyQuestion> surveyQuestionList);

	List<SurveyQuestion> getSurveyQuestionsBySmId(@Param("smId") String smId);
}
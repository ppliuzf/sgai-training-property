package com.sgai.property.customer.dao;

import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.customer.entity.SurveyUserAnswer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ISurveyUserAnswerDao extends MoreDataSourceDao<SurveyUserAnswer> {
 
	List<SurveyUserAnswer> getSurveyUserAnswersBySmId(@Param("smId") String smId);

	List<SurveyUserAnswer> getSurveyUserAnswersBySmIdAndSqId(@Param("smId") String smId, @Param("sqId") String sqId);
}
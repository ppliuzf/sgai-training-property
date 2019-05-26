package com.sgai.property.customer.service;

import com.sgai.property.customer.dao.ISurveyQuestionDao;
import com.sgai.property.customer.entity.SurveyQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyQuestionServiceImpl extends MoreDataSourceCrudServiceImpl<ISurveyQuestionDao,SurveyQuestion>{
	@Autowired
	private ISurveyQuestionDao surveyQuestionDao;

}
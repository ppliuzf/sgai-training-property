package com.sgai.property.customer.service;

import com.sgai.property.customer.dao.ISurveyUserAnswerDao;
import com.sgai.property.customer.entity.SurveyUserAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyUserAnswerServiceImpl extends MoreDataSourceCrudServiceImpl<ISurveyUserAnswerDao,SurveyUserAnswer>{
	@Autowired
	private ISurveyUserAnswerDao surveyUserAnswerDao;

}
package com.sgai.property.customer.service;

import com.sgai.property.customer.dao.ISurveyUserInfoDao;
import com.sgai.property.customer.entity.SurveyUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyUserInfoServiceImpl extends MoreDataSourceCrudServiceImpl<ISurveyUserInfoDao,SurveyUserInfo>{
	@Autowired
	private ISurveyUserInfoDao surveyUserInfoDao;

}
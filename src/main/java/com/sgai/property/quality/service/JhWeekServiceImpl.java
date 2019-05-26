package com.sgai.property.quality.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgai.property.quality.dao.IJhWeekDao;
import com.sgai.property.quality.entity.JhWeek;

@Service
public class JhWeekServiceImpl extends MoreDataSourceCrudServiceImpl<IJhWeekDao,JhWeek>{
	@Autowired
	private IJhWeekDao jhWeekDao;

}
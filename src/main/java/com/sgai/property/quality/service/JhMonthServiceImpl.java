package com.sgai.property.quality.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgai.property.quality.dao.IJhMonthDao;
import com.sgai.property.quality.entity.JhMonth;

@Service
public class JhMonthServiceImpl extends MoreDataSourceCrudServiceImpl<IJhMonthDao,JhMonth>{
	@Autowired
	private IJhMonthDao jhMonthDao;

}
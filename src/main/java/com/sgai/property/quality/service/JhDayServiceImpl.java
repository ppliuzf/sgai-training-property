package com.sgai.property.quality.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgai.property.quality.dao.IJhDayDao;
import com.sgai.property.quality.entity.JhDay;

@Service
public class JhDayServiceImpl extends MoreDataSourceCrudServiceImpl<IJhDayDao,JhDay>{
	@Autowired
	private IJhDayDao jhDayDao;

}
package com.sgai.property.quality.service.plan;


import com.sgai.property.quality.dao.plan.IPeriodDao;
import com.sgai.property.quality.entity.plan.Period;
import com.sgai.property.quality.service.MoreDataSourceCrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeriodServiceImpl extends MoreDataSourceCrudServiceImpl<IPeriodDao,Period> {
	@Autowired
	private IPeriodDao periodDao;

}
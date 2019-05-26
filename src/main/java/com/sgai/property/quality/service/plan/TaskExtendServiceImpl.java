package com.sgai.property.quality.service.plan;


import com.sgai.property.quality.dao.plan.ITaskExtendDao;
import com.sgai.property.quality.entity.plan.TaskExtend;
import com.sgai.property.quality.service.MoreDataSourceCrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskExtendServiceImpl extends MoreDataSourceCrudServiceImpl<ITaskExtendDao,TaskExtend> {
	@Autowired
	private ITaskExtendDao taskExtendDao;

}
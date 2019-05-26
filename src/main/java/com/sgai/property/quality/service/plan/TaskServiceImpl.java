package com.sgai.property.quality.service.plan;


import com.sgai.property.quality.dao.plan.ITaskDao;
import com.sgai.property.quality.entity.plan.Task;
import com.sgai.property.quality.service.MoreDataSourceCrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl extends MoreDataSourceCrudServiceImpl<ITaskDao,Task> {
	@Autowired
	private ITaskDao taskDao;

}
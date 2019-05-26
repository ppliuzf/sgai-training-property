package com.sgai.property.quality.service.plan;


import com.sgai.property.quality.dao.plan.ITaskPersonDao;
import com.sgai.property.quality.entity.plan.TaskPerson;
import com.sgai.property.quality.service.MoreDataSourceCrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskPersonServiceImpl extends MoreDataSourceCrudServiceImpl<ITaskPersonDao,TaskPerson> {
	@Autowired
	private ITaskPersonDao taskPersonDao;

}
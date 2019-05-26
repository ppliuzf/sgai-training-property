package com.sgai.property.quality.service.plan;


import com.sgai.property.quality.dao.plan.ITaskImgDao;
import com.sgai.property.quality.entity.plan.TaskImg;
import com.sgai.property.quality.service.MoreDataSourceCrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskImgServiceImpl extends MoreDataSourceCrudServiceImpl<ITaskImgDao,TaskImg> {
	@Autowired
	private ITaskImgDao taskImgDao;

}
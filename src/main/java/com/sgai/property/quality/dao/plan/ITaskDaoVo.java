package com.sgai.property.quality.dao.plan;

import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.quality.entity.plan.Task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ITaskDaoVo extends MoreDataSourceDao<Task> {

	List<Task> taskStateMonth(Map<String, Object> map);
	
	List<String> getRecordIds(Map<String, Object> map);
	
	List<Task> getTaskList(Map<String, Object> map);
}
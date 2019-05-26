package com.sgai.property.quality.dao.plan;

import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.quality.entity.plan.TaskPerson;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ITaskPersonDaoVo extends MoreDataSourceDao<TaskPerson> {

	List<String> getTaskIdsByEiId(TaskPerson taskPerson);
}
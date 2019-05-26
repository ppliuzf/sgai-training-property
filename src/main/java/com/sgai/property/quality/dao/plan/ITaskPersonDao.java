package com.sgai.property.quality.dao.plan;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.quality.entity.plan.TaskPerson;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ITaskPersonDao extends MoreDataSourceDao<TaskPerson> {

    List<TaskPerson> findListById(TaskPerson entity);
    
    List<TaskPerson> getByTaskIds(List<String> taskIds); 
}
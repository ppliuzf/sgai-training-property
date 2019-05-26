package com.sgai.property.purchase.dao;
import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.purchase.entity.PlanTask;
import com.sgai.property.purchase.param.TaskParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IPlanTaskDao extends MoreDataSourceDao<PlanTask> {

    List<PlanTask> findLists(TaskParam planTask);
}
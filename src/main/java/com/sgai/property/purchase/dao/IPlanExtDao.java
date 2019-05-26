package com.sgai.property.purchase.dao;

import com.sgai.property.commonService.dao.MoreDataSourceDao;
import com.sgai.property.purchase.entity.MatSpuulyCom;
import com.sgai.property.purchase.entity.Plan;
import com.sgai.property.purchase.entity.PlanTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IPlanExtDao extends MoreDataSourceDao<Plan> {
	
	List<MatSpuulyCom> getComListByTaskId(String taskId);
	
	int insertOrderMat(@Param("taskId") String taskId, @Param("comId") String comId, @Param("applyNo") String applyNo);
	
	List<PlanTask> getPlanTaskByStageId(@Param("stageId") String stageId);

}

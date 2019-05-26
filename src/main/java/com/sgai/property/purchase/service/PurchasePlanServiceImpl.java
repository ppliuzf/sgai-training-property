package com.sgai.property.purchase.service;

import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.util.CopyHelper;
import com.sgai.property.purchase.dao.IPlanDao;
import com.sgai.property.purchase.dao.IPlanStageDao;
import com.sgai.property.purchase.entity.Plan;
import com.sgai.property.purchase.param.PlanParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class PurchasePlanServiceImpl {
	
	@Autowired
	IPlanDao planDao;
	
	@Autowired
	IPlanStageDao stageDao;

	public String saveOrUpdatePlan(PlanParam planParam) {
		Plan plan  = new Plan();
		if(planParam == null){
			throw new BusinessException(ReturnType.ParamIllegal, "参数为空");
		}
		Date dt = new Date();
		CopyHelper.copyObj(planParam, plan);
		
		plan.setUpdatedDt(dt);
		plan.setPlanEmpId(UserServletContext.getUserInfo().getEmCode());
		plan.setPlanEmpName(UserServletContext.getUserInfo().getUserName());
		plan.setPlanStat(1L); //待提交
		
		if(plan.getId() != null){
			planDao.updateById(plan);
			
		}else{
			plan.setCreatedDt(dt);
			plan.setId(UUID.randomUUID().toString());
			plan.setComCode(UserServletContext.getUserInfo().getComCode());
			plan.setModuCode(UserServletContext.getUserInfo().getModuCode());
			planDao.insert(plan);
		}
		
		
		return plan.getId();
	}

}

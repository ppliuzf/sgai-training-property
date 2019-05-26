package com.sgai.property.purchase.service;

import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.util.CopyHelper;
import com.sgai.property.purchase.dao.IPlanDao;
import com.sgai.property.purchase.dao.IPlanStageDao;
import com.sgai.property.purchase.entity.PlanStage;
import com.sgai.property.purchase.param.PlanStageParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PurchasePlanStageServiceImpl {
	
	@Autowired
	IPlanStageDao stageDao;
	
	@Autowired
	IPlanDao planDao;

	public Boolean saveOrUpdate(List<PlanStageParam> stageParamList) {
		List<PlanStage> stageList = new ArrayList<>();
		if(stageParamList == null || stageParamList.size() == 0){
			throw new BusinessException(ReturnType.ParamIllegal, "参数为空");
		}
		
		cleanStage(stageParamList);
		
		CopyHelper.copyList(stageParamList, stageList, PlanStage.class);
		
		saveOrUpdateStage(stageList);
		
		return true;
	}

	private void saveOrUpdateStage(List<PlanStage> stageList) {
		Date dt = new Date();
		for(int i = 0; i < stageList.size(); i ++){
			PlanStage stage = stageList.get(i);
			stage.setSort(i + 1L);
			stage.setUpdatedDt(dt);
			if(StringUtils.isBlank(stage.getId())){
				stage.setCreatedDt(dt);
				stage.setId(UUID.randomUUID().toString());
				stageDao.insert(stage);
			}else{
				stageDao.updateById(stage);
			}
		}
	}

	private boolean cleanStage(List<PlanStageParam> stageParamList) {
		String planId = stageParamList.get(0).getPlanId();
		Set<String> stageIdSet = new HashSet<String>();
		for(PlanStageParam stage : stageParamList){
			if(StringUtils.isNotBlank(stage.getPlanId())){
				stageIdSet.add(stage.getId());
			}
		}
		
		PlanStage stage = new PlanStage();
		stage.setPlanId(planId);
		List<PlanStage> stageList = stageDao.findList(stage);
		if(stageList != null){
			for(PlanStage one : stageList){
				if(!stageIdSet.contains(one.getId())){
					stageDao.deleteById(one.getId());
				}
			}
		}
		return true;
	}

}

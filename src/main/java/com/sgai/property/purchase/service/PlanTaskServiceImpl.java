package com.sgai.property.purchase.service;

import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.constants.CodeType;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.service.BaseCodeService;
import com.sgai.property.common.util.CopyHelper;
import com.sgai.property.depot.dao.IMatOrderDao;
import com.sgai.property.depot.entity.MatOrder;
import com.sgai.property.purchase.dao.*;
import com.sgai.property.purchase.entity.*;
import com.sgai.property.purchase.param.PlanDetailCompanyParam;
import com.sgai.property.purchase.param.PlanTaskParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PlanTaskServiceImpl {
	
	@Autowired
	IPlanTaskDao taskDao;
	
	@Autowired
	IPlanDetailDao planDetailDao;
	
	@Autowired
	IMatApplyDetailExtDao matApplyDetailExtDao;
	
	@Autowired
	IPlanDetailCompanyDao detailCompanyDao;
	
	@Autowired
    IPlanExtDao planExtDao;
	
    @Autowired
    IMatOrderExtDao matOrderExtDao;
    
    @Autowired
    IMatOrderDao matOrderDao;
    
    @Autowired
    IPlanStageDao stageDao;
    
    @Autowired
    IPlanDao planDao;
	@Autowired
	private BaseCodeService baseCodeService;

	/**
	 * 1、新增
	 * 	1.1、新增task
	 *  1.2、新增物料
	 * 2、更新（？已处理的就不要更新了）
	 * 	2.1、清理物料
	 *  2.2、更新task
	 *  2.3、新增物料
	 * @param planTaskParam
	 * @return
	 */
	public Boolean saveOrUpdate(PlanTaskParam planTaskParam) {
		if(planTaskParam == null){
			throw new BusinessException(ReturnType.ParamIllegal, "参数为空！");
		}
		PlanTask task = new PlanTask();
		task.setPlanId(planTaskParam.getPlanId());
		task.setStageId(planTaskParam.getStageId());
		//todo 暂时设置为2
		task.setTaskPlanMat(2L);
		int taskCount = taskDao.getCount(task);
		taskCount ++;
		
		Date dt = new Date();
		PlanTask planTask = new PlanTask();
		CopyHelper.copyObj(planTaskParam, planTask);
		planTask.setUpdatedDt(dt);
		planTask.setTaskNeedStatus(2L);
		planTask.setTaskEmpId(UserServletContext.getUserInfo().getEmCode());
		planTask.setTaskEmpName(UserServletContext.getUserInfo().getUserName());
		if(StringUtils.isBlank(planTaskParam.getId())){
			//新增
			planTask.setId(UUID.randomUUID().toString());
			planTask.setCreatedDt(dt);
			planTask.setTaskDate(dt);
			planTask.setSort(new Long(taskCount));
			planTask.setTaskNo(genNo());
			planTask.setModuCode(UserServletContext.getUserInfo().getModuCode());
			planTask.setComCode(UserServletContext.getUserInfo().getComCode());
			taskDao.insert(planTask);
		}else{
			//更新
			taskDao.updateById(planTask);
			//清理物料
			PlanDetail detail = new PlanDetail();
			detail.setTaskId(planTask.getId());
			planDetailDao.delete(detail);
		}
		//新增物料
		if(planTaskParam.getPlanDetailList() != null && planTaskParam.getPlanDetailList().size() > 0){
			List<PlanDetail> detailList = new ArrayList<PlanDetail>();
			CopyHelper.copyList(planTaskParam.getPlanDetailList(), detailList, PlanDetail.class);
			for(PlanDetail one : detailList){
				one.setId(UUID.randomUUID().toString());
				one.setCreatedDt(dt);
				one.setUpdatedDt(dt);
				one.setTaskId(planTask.getId());
				planDetailDao.insert(one);
			}
		}
		
		return true;
	}

	
    private String genNo(){
        Long seq = baseCodeService.getseq(CodeType.CODE_TYPE_iMatApplyDetailExt);
        String seqStr = Long.toString(seq);
        while(seqStr.length() < 8){
            seqStr = "0" + seqStr;
        }
        return "CL-" + seqStr;
    }
    

	public Boolean complateTask(List<PlanDetailCompanyParam> paramList) {
		if(paramList == null || paramList.size() == 0){
			throw new BusinessException(ReturnType.ParamIllegal, "参数异常");
		}
		String taskId = paramList.get(0).getTaskId();
		for(PlanDetailCompanyParam param : paramList){
			if(!taskId.equals(param.getTaskId())){
				throw new BusinessException(ReturnType.ParamIllegal, "参数异常");	
			}
		}
		Date dt = new Date();
		
		List<PlanDetailCompany> entityList = new ArrayList<>();
		CopyHelper.copyList(paramList, entityList, PlanDetailCompany.class);
		
		for(PlanDetailCompany entity : entityList){
			entity.setCreatedDt(dt);
			entity.setUpdatedDt(dt);
			entity.setId(UUID.randomUUID().toString());
			detailCompanyDao.insert(entity);
		}
		PlanTask planTask = taskDao.getById(taskId);
		planTask.setTaskNeedStatus(1L);
		planTask.setId(taskId);
		taskDao.updateById(planTask);
		Plan plan = planDao.getById(planTask.getPlanId());
		//生成任务订单 ：
		/**
		 * 1、获取任务下的供应商列表
		 * 2、新建订单记录
		 * 3、同步物料数据
		 */
		List<MatSpuulyCom> comList = planExtDao.getComListByTaskId(taskId);
		if(comList != null && comList.size() > 0){
			for(MatSpuulyCom one : comList){
				
				//新建一条订单记录
				MatOrder matOrder = new MatOrder();
				matOrder.setId(UUID.randomUUID().toString());
				matOrder.setOrderNo(genMatOrderNo());
				matOrder.setApplyNo(planTask.getTaskNo());
				if (plan != null ){
					matOrder.setDeptJson(plan.getPlanDept());
				}
				matOrder.setOrderDate(dt);
				//制单人
				matOrder.setOrderEmpId(UserServletContext.getUserInfo().getEmCode());
				matOrder.setOrderEmpName(UserServletContext.getUserInfo().getUserName());
				matOrder.setOrderType(2L);
				matOrder.setOrderStat(1L);
				matOrder.setTaskId(taskId);
				matOrder.setCreatedDt(dt);
				matOrder.setUpdatedDt(dt);
				//供应商，发票
				matOrder.setSupplyComId(one.getId());
				matOrder.setSupplyComName(one.getComName());
				matOrder.setInvoiceState("1");
				matOrder.setModuCode(UserServletContext.getUserInfo().getModuCode());
				matOrder.setComCode(UserServletContext.getUserInfo().getComCode());
				matOrderDao.insert(matOrder);
				//同步物料数据
				planExtDao.insertOrderMat(taskId, one.getId(), matOrder.getOrderNo());
			}
		}
		
		return true;
	}
	
	/**
	 * 订单编号
	 * @return
	 */
	private String genMatOrderNo(){
        Long seq = baseCodeService.getseq(CodeType.CODE_TYPE_matOrderExt);
        String seqStr = Long.toString(seq);
        for(int i = seqStr.length(); i < 8; i ++){
            seqStr = "0" + seqStr;
        }
        return "CA-" + seqStr;
    }


	/**
	 * 
	 * @param taskId
	 * @param upDown 1:上移，2:下移
	 * @return
	 */
	public Boolean taskMove(String taskId, Integer upDown) {
		PlanTask task = taskDao.getById(taskId);
		if(task == null){
			throw new BusinessException(ReturnType.ParamIllegal, "taskId参数异常");
		}
		if(upDown == 1){
			PlanTask entityUpOne = new PlanTask();	
			Long entityUpOneSort = task.getSort() - 1;
			entityUpOne.setSort(entityUpOneSort);
			entityUpOne.setStageId(task.getStageId());
			List<PlanTask> taskList = taskDao.findList(entityUpOne);
			if(taskList != null && taskList.size() > 0){
				entityUpOne = taskList.get(0);
				entityUpOne.setSort(task.getSort());
				taskDao.updateById(entityUpOne);
				task.setSort(entityUpOneSort);
				taskDao.updateById(task);
			}else{
				throw new BusinessException(ReturnType.ParamIllegal, "已经到最顶端");
			}
			
		}else if(upDown == 2){
			PlanTask entityDownOne = new PlanTask();
			Long entityDownOneSort = task.getSort() + 1;
			entityDownOne.setSort(entityDownOneSort);
			entityDownOne.setStageId(task.getStageId());
			List<PlanTask> taskList = taskDao.findList(entityDownOne);
			if(taskList != null && taskList.size() > 0){
				entityDownOne = taskList.get(0);
				entityDownOne.setSort(task.getSort());
				taskDao.updateById(entityDownOne);
				task.setSort(entityDownOneSort);
				taskDao.updateById(task);
			}else{
				throw new BusinessException(ReturnType.ParamIllegal, "已经到最底部");
			}
		}else{
			throw new BusinessException(ReturnType.ParamIllegal, "upDown参数异常");
		}
		
		return true;
	}


	public Boolean taskMoveTo(String taskId, String stageId) {
		Long taskSort = 1L;
		PlanTask task = new PlanTask();
		task.setStageId(stageId);
		List<PlanTask> taskList = taskDao.findList(task);
		if(taskList != null && taskList.size() > 0){
			taskSort = taskList.size() + 1L;
		}
		task = taskDao.getById(taskId);
		if(task == null){
			throw new BusinessException(ReturnType.ParamIllegal, "任务不存在");
		}
		if(stageId == task.getId()){
			throw new BusinessException(ReturnType.ParamIllegal, "已经在此阶段了");
		}
		String oldStageId = task.getStageId();
		
		task.setStageId(stageId);
		task.setSort(taskSort);
		taskDao.updateById(task);
		//更新原有任务排序值
		List<PlanTask> oldTaskList= planExtDao.getPlanTaskByStageId(oldStageId);
		if(oldTaskList != null){
			for(int i = 0; i < oldTaskList.size(); i ++){
				PlanTask one = oldTaskList.get(i);
				one.setSort(i + 1L);
				taskDao.updateById(one);
			}
		}
		
		return true;
	}


	public Boolean taskDelete(String taskId) {
		taskDao.deleteById(taskId);
		return true;
	}


	public Boolean clearStage(String planId) {
		PlanStage stage = new PlanStage();
		stage.setPlanId(planId);
		List<PlanStage> stageList = stageDao.findList(stage);
		for(PlanStage one : stageList){
			stageDao.deleteById(one.getId());
		}
		return true;
	}
}

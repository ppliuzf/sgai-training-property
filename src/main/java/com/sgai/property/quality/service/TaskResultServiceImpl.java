package com.sgai.property.quality.service;

import com.google.common.collect.Lists;
import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.util.TaskUtil;
import com.sgai.property.quality.constants.Constants;
import com.sgai.property.quality.dao.*;
import com.sgai.property.quality.dao.plan.IRecordActorDao;
import com.sgai.property.quality.dao.plan.IRecordPCDao;
import com.sgai.property.quality.dao.plan.ITaskDao;
import com.sgai.property.quality.entity.*;
import com.sgai.property.quality.entity.plan.Record;
import com.sgai.property.quality.entity.plan.RecordActor;
import com.sgai.property.quality.entity.plan.Task;
import com.sgai.property.quality.vo.TaskResultDetailVo;
import com.sgai.property.quality.vo.TaskResultVo;
import com.sgai.property.quality.vo.plan.ExPlanItemlVo;
import com.sgai.property.quality.vo.plan.ExecuteTaskDetailVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskResultServiceImpl{

	@Autowired
	QCommonServicelmpl commonService;
	@Autowired
    IQtTestTaskDao testTaskDao;
	@Autowired
    private IQtTaskItemGroupDao taskItemGroupDao;
	@Autowired
    private IQtTaskItemDao taskItemDao;
	@Autowired
	private ITaskDao taskDao;

	@Autowired
	private IRecordActorDao recordActorDao;
	@Autowired
	private IQtPlanItemDao qtPlanItemDao;
	@Autowired
	private IQtTestItemDao qtTestItemDao;

	@Autowired
	private IRecordPCDao recordPCDao;
	@Autowired
	private IQtTaskResultDao taskResultDao;

	
	public Page<TaskResultVo> listTaskResult(String ttName, String pcId, Long startDate, Long endDate, Integer pageNum, Integer pageSize) {

		Page<TaskResultVo> pageInfo=new Page<>(pageNum, pageSize);

		Long comId = UserServletContext.getUserInfo().getCompanyId();
		if(startDate == null){
			startDate = 0L;
		}
		TaskResultVo taskResultVo=new TaskResultVo();
		taskResultVo.setComId(comId);
		if(StringUtils.isNotEmpty(pcId)) {
			taskResultVo.setPcId(pcId);
		}
		taskResultVo.setValid(Constants.VALID_YES);//只显示未删除的
		if(StringUtils.isNotEmpty(ttName)) {
			taskResultVo.setTtName(ttName);
		}
		if(startDate!=null) {
			taskResultVo.setStartDate(startDate);
		}
		if(endDate!=null) {
			taskResultVo.setEndDate(endDate+60*60*24*1000-1);
		}
		taskResultVo.setPage(pageInfo);
		pageInfo.setList(testTaskDao.listTaskResult(taskResultVo));
		return pageInfo;
	}

	public void detailTaskResult(String accessToken, Long ttId) {
		
	}

	public TaskResultDetailVo resultDetail(String taskId) {
		TaskResultDetailVo vo=new TaskResultDetailVo();//
		//查询任务信息
		QtTestTask task = testTaskDao.getById(taskId);
		if(task==null){
			throw new BusinessException(ReturnType.ParamIllegal, "不存在的任务id");
		}
		//设置任务相关信息
		vo.setId(taskId);
		vo.setName(task.getTtName());
		vo.setObjName(task.getTtObjName());
		vo.setCheckTime(task.getTtStartTime());
		vo.setCreateName(task.getCreateName());
		vo.setStatus(TaskUtil.getTaskStatusDesc(task.getTtStatus()));
		vo.setResultDesc("无");
		QtTaskItemGroup groupQuery=new QtTaskItemGroup();
		groupQuery.setTtId(taskId);
		Page<QtTaskItemGroup> taskItemGroupPage=new Page<>(1,Integer.MAX_VALUE);
		taskItemGroupPage.setOrderBy("tig_sort desc");
		groupQuery.setPage(taskItemGroupPage);
		List<QtTaskItemGroup> groupList = taskItemGroupDao.findList(groupQuery);
		List<TaskResultDetailVo.ResultItem> resultItemList=new ArrayList<>();
		for (QtTaskItemGroup group : groupList) {
			QtTaskItem itemQuery=new QtTaskItem();
			itemQuery.setTtId(taskId);
			itemQuery.setGroupId(group.getId());
			Page<QtTaskItem> taskItemPage=new Page<>(1,Integer.MAX_VALUE);
			taskItemPage.setOrderBy("group_sort desc");
			itemQuery.setPage(taskItemPage);
			List<QtTaskItem> itemList = taskItemDao.findList(itemQuery);
			for (QtTaskItem item : itemList) {
				TaskResultDetailVo.ResultItem newItem=new TaskResultDetailVo.ResultItem();
				newItem.setId(item.getId());
				newItem.setName(item.getTiName());
				newItem.setGroupName(group.getTigName());
				newItem.setAnswerType(TaskUtil.getItemTypeDesc(item.getTiIsInput()));
				newItem.setCheckName(item.getTiCheckName());
				newItem.setCheckTime(item.getTiCheckTime());
				newItem.setStatus(TaskUtil.getItemIsSubmitDesc(item.getTiIsSubmit()));
				newItem.setResult(TaskUtil.getTaskItemStatusDesc(item.getTiStatus()));
				newItem.setRemark(item.getTiRemark());
				resultItemList.add(newItem);
			}
		}
		vo.setList(resultItemList);
		return vo;
	}



	/**
	 * 执行的任务详情
	 .getUserInfo().
	 * @param recordId 计划ID
	 * @param templateId 模板ID
	 * @return
	 */
		public ExecuteTaskDetailVo ExecuteTaskDetail(String recordId,String templateId,Long dateTime) {
		Record record = recordPCDao.getById(recordId);
		ExecuteTaskDetailVo executeTaskDetailVo = new ExecuteTaskDetailVo();

		Task entity = new Task();
		entity.setIsDelete(0L);
		entity.setRecordId(recordId);
		entity.setTemplateId(templateId);
		Task task = taskDao.getByRecordIdAndTemplateId(entity);
		if (task != null) {
			executeTaskDetailVo.setTaskId(task.getId());
			//任务范围类型（1关联的空间主数据，2物料主数据，3设备主数据，4供应商主数据）
			executeTaskDetailVo.setTaskScopeType(task.getTaskScopeType());
			//空间数据
			executeTaskDetailVo.setSpaceData(task.getSpaceData());
			//物料数据
			executeTaskDetailVo.setMaterielData(task.getMaterielData());
			//设备数据
			executeTaskDetailVo.setEquipmentData(task.getEquipmentData());
			//供应商数据
			executeTaskDetailVo.setSupplierData(task.getSupplierData());
			executeTaskDetailVo.setTemplateName(task.getTemplateName());
			executeTaskDetailVo.setTaskBeginTime(task.getTaskBeginTime());
			executeTaskDetailVo.setTaskEndTime(task.getTaskEndTime());
			executeTaskDetailVo.setDesc(task.getTaskDesc());
			executeTaskDetailVo.setRecordId(task.getRecordId());
			executeTaskDetailVo.setRecordName(task.getRecordName());
			if (record!=null) {
				executeTaskDetailVo.setRecordName(record.getRecordName());
			}

			RecordActor recordActor = new RecordActor();
			recordActor.setRecordId(recordId);
			List<RecordActor> recordActors = recordActorDao.findListByRecordId(recordActor);
			//责任岗位
			StringBuffer dutyPerson = new StringBuffer();
			if (recordActors.size()>0) {
				for (RecordActor ra : recordActors) {
					dutyPerson.append(ra.getEiEmpName()).append(",");
				}
			}
			executeTaskDetailVo.setDutyPerson(dutyPerson.toString());
			QtPlanItem qtPlanItem = new QtPlanItem();
			qtPlanItem.setTpId(templateId);
			qtPlanItem.setValid(Constants.VALID_YES);
			List<QtPlanItem> planItems = qtPlanItemDao.findListByTemplateId(qtPlanItem);
			List<ExPlanItemlVo> exPlanItemls = Lists.newArrayList();
			if (planItems.size()>0) {
				for (QtPlanItem planItem : planItems) {
					ExPlanItemlVo planItemlVo = new ExPlanItemlVo();
					planItemlVo.setId(planItem.getId());
					planItemlVo.setName(planItem.getPiName());
					planItemlVo.setTiId(planItem.getTiId());
					planItemlVo.setPiIsInput(planItem.getPiIsInput());

					//根据tiid 查询执行结果 qt_test_item
					/*QtTestItem qtTestItem = new QtTestItem();
					qtTestItem.setId(planItem.getTiId());
					QtTestItem testItem = qtTestItemDao.getByObj(qtTestItem);*/
					QtTaskResult result = new QtTaskResult();
					result.setTiId(planItem.getTiId());
					result.setDateTime(dateTime);
					result.setTpId(planItem.getTpId());
					result.setRecordId(recordId);
					QtTaskResult taskResult = taskResultDao.getDisEntity(result);
					//查询检验项表
					/*QtTestItem qtTestItem = new QtTestItem();
					qtTestItem.setId(planItem.getTiId());
					QtTestItem testItem = qtTestItemDao.get(qtTestItem);
					if (testItem!=null) {
						planItemlVo.setRemark("");
					}*/

					if (taskResult != null) {
						planItemlVo.setExecutor(taskResult.getTiExecutorName());
//						planItemlVo.setExecutoStatus(taskResult.getTiStatus());//状态（0:未检查,1:合格,2:缺陷）
						if (taskResult.getTiStatus()==0) {
							planItemlVo.setExecuteResult(0L);
						}else if (taskResult.getTiStatus()==1) {
							planItemlVo.setExecuteResult(1L);
						}else if (taskResult.getTiStatus()==2) {
							planItemlVo.setExecuteResult(2L);
						}
						planItemlVo.setExecutoStatus("已完成");
						planItemlVo.setExecutoTime(taskResult.getTiCheckTime()+"");
						planItemlVo.setRemark(taskResult.getTiInputResult());

					}else {
						planItemlVo.setExecutoStatus("未完成");
//						planItemlVo.setExecuteResult(0L);
					}
					exPlanItemls.add(planItemlVo);

				}
			}
			executeTaskDetailVo.setExPlanItemls(exPlanItemls);
		}
		return executeTaskDetailVo;
	}



}

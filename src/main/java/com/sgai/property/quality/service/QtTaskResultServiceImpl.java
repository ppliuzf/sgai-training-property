package com.sgai.property.quality.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import com.sgai.property.common.util.*;
import com.sgai.property.quality.constants.Constants;
import com.sgai.property.quality.dao.plan.IRecordPCDao;
import com.sgai.property.quality.entity.plan.Record;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgai.property.quality.dao.IQtPlanItemDao;
import com.sgai.property.quality.dao.IQtPlanItemEnclosureDao;
import com.sgai.property.quality.dao.IQtPlanItemGroupDao;
import com.sgai.property.quality.dao.IQtProfessionalCategoryDao;
import com.sgai.property.quality.dao.IQtTaskResultDao;
import com.sgai.property.quality.dao.IQtTestItemDao;
import com.sgai.property.quality.dao.IQtTestPlanDao;
import com.sgai.property.quality.dao.IQtTestTaskDao;
import com.sgai.property.quality.dao.plan.ITaskDao;
import com.sgai.property.quality.dao.plan.ITaskDaoVo;
import com.sgai.property.quality.dto.SgaiEmpDto;
import com.sgai.property.quality.entity.QtPlanItem;
import com.sgai.property.quality.entity.QtPlanItemEnclosure;
import com.sgai.property.quality.entity.QtTaskResult;
import com.sgai.property.quality.entity.QtTestItem;
import com.sgai.property.quality.entity.QtTestPlan;
import com.sgai.property.quality.entity.plan.Task;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.quality.vo.TaskStatusUncheckedVo;
import com.sgai.property.quality.vo.TaskStatusVo;
import com.sgai.property.quality.vo.TestItemDetailVo;

@Service
public class QtTaskResultServiceImpl extends MoreDataSourceCrudServiceImpl<IQtTaskResultDao,QtTaskResult>{
	@Autowired
	private IQtTaskResultDao qtTaskResultDao;
	@Autowired
	private QCommonServicelmpl commonService;
	@Autowired
	private IQtTestPlanDao testPlanDao;
	@Autowired
	private IQtTestItemDao testItemDao;
	@Autowired
	private IQtPlanItemGroupDao planItemGroupDao;
	@Autowired
	private IQtPlanItemDao planItemDao;
	@Autowired
	private IQtProfessionalCategoryDao professionalCategoryDao;
	@Autowired
	private IQtTestTaskDao testTaskDao;
	@Autowired
	private ITaskDaoVo taskDaoVo;
	@Autowired
	private ITaskDao taskDao;

	@Autowired
	private IRecordPCDao recordPCDao;

	@Autowired
    IQtPlanItemEnclosureDao enclosureDao;
	public TaskStatusUncheckedVo listByRecordIdAndTpId(String tpId, String recordId, Long dateTimeL) {
        Date dateTime = new Date(dateTimeL);
        TimeZone zone = TimeZone.getTimeZone("GMT+8"); 
        String dateStr = DateFormatUtils.format(dateTime,"yyyy-MM-dd", zone);
        try {
            dateTime = DateUtils.parseDate(dateStr, Locale.CHINA, "yyyy-MM-dd");
        } catch (Exception e) {
            throw new BusinessException(ReturnType.ParamIllegal, "时间参数异常");
        }

		QtTestPlan testPlan = testPlanDao.getById(tpId);
		if (testPlan == null) {
			throw new BusinessException(ReturnType.ParamIllegal, "不存在的模板id");
		}
		Task taskQuery = new Task();
		taskQuery.setRecordId(recordId);
		List<Task> task =  taskDao.findListByRecordId(taskQuery);
		if (task ==null || task.size()==0) {
			throw new BusinessException(ReturnType.ParamIllegal, "不存在的计划id");
		}
		// 先查出已经关联的检验项id
		QtPlanItem qtPlanItem = new QtPlanItem();
		qtPlanItem.setTpId(tpId);
		qtPlanItem.setValid(Constants.VALID_YES);
		List<String> relIds = testPlanDao.findRelIds(qtPlanItem);
		if (relIds.isEmpty()) {
			throw new BusinessException(ReturnType.ParamIllegal, "模板中没有关联的检验项");
		}
		
		QtTaskResult taskResultQuery = new QtTaskResult();
		taskResultQuery.setRecordId(recordId);
		taskResultQuery.setTpId(tpId);
		/**todo时间需要转换*/
		taskResultQuery.setDateTime(dateTimeL);
//		taskResultQuery.setDateTime(1529596800000L);
		
		
		
//		List<QtTaskResult> list_QtTaskResult = qtTaskResultDao.findList(taskResultQuery);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("recordId", recordId);
		map.put("tpId", tpId);
		map.put("dateTime", dateTimeL);
		List<QtTaskResult> list_QtTaskResult = qtTaskResultDao.findListByRecordId_tpId_dateTime(map);
		List<TaskStatusVo> list_taskStatusVo = new ArrayList<TaskStatusVo>();
		String uncheckedID =  "";
	
		TaskStatusUncheckedVo uncheckedVo = new TaskStatusUncheckedVo();
		/**list_QtTaskResult = 0 表示该当天计划的模板中的所有的任务项，一项都没执行*/
		if (list_QtTaskResult != null ) {
			for(String id :relIds){
				TaskStatusVo taskStatusVo = new TaskStatusVo();
				QtTestItem  testItem =  testItemDao.getById(id);
				taskStatusVo.setTaskId(testItem.getId());
				taskStatusVo.setTaskName(testItem.getTiName());
				taskStatusVo.setCreateTime(testItem.getCreateTime());
				taskStatusVo.setStatus(testItem.getTiStatus().intValue());
				taskStatusVo.setDesc(TaskUtil.getTaskItemStatusDesc(0));
				/**覆盖同样任务项Id 对应的状态。以QtTaskResult表的状态为准*/
				for(QtTaskResult qtTaskResult :list_QtTaskResult){
					String tiId = qtTaskResult.getTiId();
					if (tiId.equals(id)) {
						taskStatusVo.setStatus(qtTaskResult.getTiStatus().intValue());
						taskStatusVo.setDesc(TaskUtil.getTaskItemStatusDesc(qtTaskResult.getTiStatus().intValue()));
					}
				}
				if ("".equals(uncheckedID)) {
					if (taskStatusVo.getStatus().intValue() ==0) {
						uncheckedID = taskStatusVo.getTaskId();
					}
				}
				list_taskStatusVo.add(taskStatusVo);
			}
			uncheckedVo.setUncheckedTaskId(uncheckedID);
			uncheckedVo.setLists(list_taskStatusVo);
		}
		
//		J
//		
//		QtTaskResult qtTaskResult = new QtTaskResult();
//		qtTaskResult.setRecordId(recordId);
//		qtTaskResult.setTpId(tpId);
//		List<QtTaskResult> list_qtTaskResult = qtTaskResultDao.findList(qtTaskResult);
		return uncheckedVo;
	}
	public TestItemDetailVo detailTestItem(String itemId,String sgaiToken,String recordId,Long dateTime,String tpId) {

		Record record = recordPCDao.getById(recordId);
		
		QtTestItem testItem = testItemDao.getById(itemId);
		String dutyEmpIds = testItem.getTiDutyEmpIds();
		List<String> list_dutyEmpNames = new ArrayList<String>();
		if (StringUtils.isNotBlank(dutyEmpIds)) {
			if (dutyEmpIds.indexOf(",")!= -1) {
				String [] array_dutyEmpIds = dutyEmpIds.split(",");
				for(int i =0 ;i <array_dutyEmpIds.length;i++){
					SgaiEmpDto sgaiEmp = null;//planCommonsRomeotService.getSgaiEmpById(sgaiToken, array_dutyEmpIds[i]).getData();;
					String dutyEmpName = sgaiEmp.getLastname();
					if (!StringUtils.isNotBlank(dutyEmpName)) {
						throw new BusinessException(ReturnType.ParamIllegal, array_dutyEmpIds[i]+"该责任人名称不可为空！");
					}
					list_dutyEmpNames.add(dutyEmpName);
				}
            }else{
//				SgaiEmpDto sgaiEmp = null;//planCommonsRomeotService.getSgaiEmpById(sgaiToken, dutyEmpIds).getData();
//				String dutyEmpName = sgaiEmp.getLastname();
//				if (!StringUtils.isNotBlank(dutyEmpName)) {
//					throw new BusinessException(ReturnType.ParamIllegal, dutyEmpIds+"该责任人名称不可为空！");
//				}
				list_dutyEmpNames.add("设计公司");
			}
		}
		

		
		TestItemDetailVo testItemDetailVo = new TestItemDetailVo();
		QtBeanMapper.copy(testItem, testItemDetailVo);

		if(record != null) {
			testItemDetailVo.setRecordId(recordId);
			testItemDetailVo.setRecordName(record.getRecordName());
		}

		//testItemDetailVo 没有 任务模板的名称？？？以前是存在哪里了？
//		testItemDetailVo.setPcName(pcName);
//		testItemDetailVo.setCreateEiName(createEiName);
//		testItemDetailVo.setCreateTime(createTime);
//		testItemDetailVo.setTiName(tiName);
//		testItemDetailVo.setTiStandard(tiStandard);
//		testItemDetailVo.setTiLimitTime(tiLimitTime);
//		testItemDetailVo.setTiFinshUnit(tiFinshUnit);
//		testItemDetailVo.setTiRectificationRequirements(tiRectificationRequirements);
		
		QtTaskResult qtTaskResultQuery = new QtTaskResult();
		qtTaskResultQuery.setRecordId(recordId);
		qtTaskResultQuery.setTiId(itemId);
		qtTaskResultQuery.setTpId(tpId);
		qtTaskResultQuery.setDateTime(dateTime);
		List<QtTaskResult> list_qtTaskResult = qtTaskResultDao.findList(qtTaskResultQuery);
		if (list_qtTaskResult.size()!=0) {
			QtTaskResult qtTaskResult= list_qtTaskResult.get(0);
			/**没有qtTaskResult中没有存，所以用testItem赋值*/
			qtTaskResult.setCreateTime(testItem.getCreateTime());
			qtTaskResult.setTiName(testItem.getTiName());
			QtBeanMapper.copy(qtTaskResult, testItemDetailVo);
		}
		
		testItemDetailVo.setTiId(testItem.getId());
		testItemDetailVo.setTiMin(Double.valueOf(testItem.getTiMin())/100 + "");
		testItemDetailVo.setTiMax(Double.valueOf(testItem.getTiMax())/100 + "");
		testItemDetailVo.setDutyEmpNames(list_dutyEmpNames);
		//获取附件
		QtPlanItemEnclosure enclosure = new QtPlanItemEnclosure();
		enclosure.setTId(testItem.getId());
		enclosure.setTType(QtPlanItemEnclosure.TEST_ITEM);
		List<QtPlanItemEnclosure> enclosureList = enclosureDao.findList(enclosure);
		if(CollectionUtils.isNotEmpty(enclosureList)) {
			testItemDetailVo.setEnclosures(enclosureList.get(0).getEnclosure());
		}
		return testItemDetailVo;
	}	
}
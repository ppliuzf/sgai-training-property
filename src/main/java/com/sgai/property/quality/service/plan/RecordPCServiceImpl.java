package com.sgai.property.quality.service.plan;


import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.service.BaseSgaiOrgTreeService;
import com.sgai.property.ctl.entity.CtlEmpDto;
import com.sgai.property.ctl.service.CtlEmpDtoService;
import com.sgai.property.quality.dao.IJhDayDao;
import com.sgai.property.quality.dao.IJhMonthDao;
import com.sgai.property.quality.dao.IJhWeekDao;
import com.sgai.property.quality.dao.plan.IPeriodDao;
import com.sgai.property.quality.dao.plan.IRecordActorDao;
import com.sgai.property.quality.dao.plan.IRecordPCDao;
import com.sgai.property.quality.dao.plan.ITaskDao;
import com.sgai.property.quality.entity.plan.Period;
import com.sgai.property.quality.entity.plan.Record;
import com.sgai.property.quality.entity.plan.RecordActor;
import com.sgai.property.quality.entity.plan.Task;
import com.sgai.property.quality.service.MoreDataSourceCrudServiceImpl;
import com.sgai.property.quality.vo.plan.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 严浩淼
 * @date 2018年1月6日--上午10:59:45
 */
@Service
public class RecordPCServiceImpl extends MoreDataSourceCrudServiceImpl<IRecordPCDao, Record> {

    @Autowired
    private IRecordPCDao recordPCDao;
    @Autowired
    private RecordActorServiceImpl recordActorService;
    @Autowired
    private IRecordActorDao recordActorDao;
    @Autowired
    private TaskServiceImpl taskService;
    @Autowired
    private ITaskDao taskDao;
    @Autowired
    private RecordImgServiceImpl recordImgService;
    @Autowired
    private PeriodServiceImpl periodService;
    @Autowired
    private IPeriodDao periodDao;
    @Autowired
    private IJhDayDao dayDao;
    @Autowired
    private IJhWeekDao weekDao;
    @Autowired
    private IJhMonthDao monthDao;
    @Autowired
    private BaseSgaiOrgTreeService baseSgaiOrgTreeService;
    @Autowired
    private CtlEmpDtoService ctlEmpDtoService;

    public Page<RecordVo> getListPageByParam(RecordParam param, int pageNum,
                                             int pageSize) {
        Record record = new Record();
        BeanUtils.copyProperties(param, record);
        record.setIsDelete(0L);
        record.setComCode(UserServletContext.getUserInfo().getComCode());
        record.setTypeFlag(param.getType());

        Page<Record> page = new Page<Record>(pageNum, pageSize);
        record.setPage(page).setOrderBy("create_time DESC");

        List<Record> records = recordPCDao.getListByParam(record);
        page.setList(records);

        Page<RecordVo> pageVo = new Page<RecordVo>();
        BeanUtils.copyProperties(page, pageVo);

        List<RecordVo> recordVos = new ArrayList<RecordVo>();
        if (records.size() > 0) {
            for (Record ty : records) {
                RecordVo typeVo = new RecordVo();
                BeanUtils.copyProperties(ty, typeVo);
                recordVos.add(typeVo);
            }
        }
        pageVo.setList(recordVos);

        return pageVo;
    }

    public Record saveRecord(RecordParamAdd param) {
        Record record = new Record();
        BeanUtils.copyProperties(param, record);
        record.setTypeId("0");//默认值
        record.setTypeName("0");
        record.setOrgId(UserServletContext.getUserInfo().getComCode());
        record.setIsDelete(0L);
        record.setCreateTime(System.currentTimeMillis());
        record.setUpdateTime(System.currentTimeMillis());
        record.setCreatorEiId(UserServletContext.getUserInfo().getUserId());
        record.setCreatorEiEmpName(UserServletContext.getUserInfo().getUserName());
        record.setComCode(UserServletContext.getUserInfo().getComCode());
        record.setModuCode(UserServletContext.getUserInfo().getModuCode());
        record.setTypeFlag(param.getType());

        save(record);

        if (record.getId() != null) {
            //获取参与者信息
            if (param.getIdList().size() > 0) {
                for (int i = 0; i < param.getIdList().size(); i++) {
                    String empId = param.getIdList().get(i);
                    CtlEmpDto ctlEmp = ctlEmpDtoService.selectByPrimaryKey(Long.valueOf(empId));
                    RecordActor recordActor = new RecordActor();
                    recordActor.setRecordId(record.getId());
                    recordActor.setRecordName(record.getRecordName());
                    recordActor.setToonUserId("");
                    recordActor.setFeedId("");
                    recordActor.setEiId(ctlEmp.getId()+"");
                    recordActor.setEiEmpName(ctlEmp.getLastname());
                    recordActor.setIsDelete(0L);
                    recordActor.setCreateTime(System.currentTimeMillis());
                    recordActorService.save(recordActor);
                }
            }
        }
        return record;
    }

    public RecordVo getRecordDetails(String id) {
        Record record = recordPCDao.getById(id);
        RecordVo recordVo = new RecordVo();
        if (record != null) {
            BeanUtils.copyProperties(record, recordVo);
            //获取参与者信息
            RecordActor recordActor = new RecordActor();
            recordActor.setRecordId(id);
            List<RecordActor> recordActors = recordActorDao.findList(recordActor);
            List<String> actorNames = new ArrayList<String>();
            for (RecordActor actor : recordActors) {
                actorNames.add(actor.getEiEmpName());
            }
            recordVo.setActorNames(actorNames);
            //添加无阶段数据
            Period notPerio = new Period();
            notPerio.setId("-1");
            notPerio.setPeriodSort(0L);
            notPerio.setPeriodName("无");
            //获取计划的阶段列表
            Period period = new Period();
            period.setRecordId(id);
            period.setIsDelete(0L);
            Page<Period> page = new Page<Period>();
            page.setOrderBy("period_sort");
            period.setPage(page);
            List<Period> periods = periodDao.findList(period);
            periods.add(notPerio);
            //获取阶段下的任务列表
			/*List<PeriodTaskVo> periodTaskVos =new ArrayList<PeriodTaskVo>();
			for (Period per : periods) {
				PeriodTaskVo periodTaskVo =new PeriodTaskVo();
				periodTaskVo.setPeriodId(per.getId());
				periodTaskVo.setPeriodName(per.getPeriodName());
				periodTaskVo.setPeriodSort(per.getPeriodSort());
				List<PlanTaskVo> taskVos =new ArrayList<PlanTaskVo>();
				Task spring =new Task();
				spring.setRecordId(id);
				spring.setPeriodId(per.getId());
				spring.setIsDelete(0L);
				spring.setPage(new Page<Task>()).setOrderBy("task_sort");
				List<Task> taskList=taskDao.findList(spring);
				for (Task ta : taskList) {
					PlanTaskVo taskVo =new PlanTaskVo();
					BeanUtils.copyProperties(ta, taskVo);
					updateTaskViewState(taskVo,false);
					taskVos.add(taskVo);
				}
				periodTaskVo.setTaskList(taskVos);
				periodTaskVos.add(periodTaskVo);
			}
			recordVo.setPeriodTaskList(periodTaskVos);*/
            //获取计划中关联的模板
            Task task = new Task();
            task.setRecordId(id);
            task.setIsDelete(0L);
            List<Task> taskList = taskDao.findListByRecordId(task);

            List<TaskTemplateVo> templateVos = new ArrayList<>();//计划关联的模板
            if (taskList.size() > 0) {
                for (Task taskl : taskList) {
                    TaskTemplateVo taskTemplateVo = new TaskTemplateVo();
                    taskTemplateVo.setId(taskl.getId());
                    taskTemplateVo.setRecordId(taskl.getRecordId());
                    taskTemplateVo.setRecordName(taskl.getRecordName());
                    taskTemplateVo.setTaskBeginTime(taskl.getTaskBeginTime());
                    taskTemplateVo.setTaskEndTime(taskl.getTaskEndTime());
                    taskTemplateVo.setTemplateId(taskl.getTemplateId());
                    taskTemplateVo.setTemplateName(taskl.getTemplateName());
                    taskTemplateVo.setTaskFlag(taskl.getTaskFlag());
                    taskTemplateVo.setOptionFlag(taskl.getOptionFlag());
                    taskTemplateVo.setTaskDay(taskl.getTaskDay());
                    taskTemplateVo.setTaskYear(taskl.getTaskYear());
                    taskTemplateVo.setBeginDate(taskl.getBeginDate());
                    taskTemplateVo.setEndDate(taskl.getEndDate());
                    taskTemplateVo.setBeginTime(taskl.getBeginTime());
                    taskTemplateVo.setEndTime(taskl.getEndTime());
                    taskTemplateVo.setBeginMonth(taskl.getBeginMonth());
                    taskTemplateVo.setEndMonth(taskl.getEndMonth());

                    //判断状态
                    Date date = new Date();
                    if (taskl.getTaskIsComplete() != null && taskl.getTaskIsComplete() == 1) {
                        taskTemplateVo.setStatus(RecordTaskServiceImpl.TaskStat.task_stat_complete);
                    } else if (date.getTime() < taskl.getTaskBeginTime()) {
                        taskTemplateVo.setStatus(RecordTaskServiceImpl.TaskStat.task_stat_before_begin);
                    } else if (date.getTime() < taskl.getTaskEndTime()) {
                        taskTemplateVo.setStatus(RecordTaskServiceImpl.TaskStat.task_stat_running);
                    } else {
                        taskTemplateVo.setStatus(RecordTaskServiceImpl.TaskStat.task_stat_after_time);
                    }

					/*if (taskl.getTaskFlag() ==1) {
						//按天重复执行时间
						JhDay jhDay = new JhDay();
						jhDay.setTestId(taskl.getId());
						JhDay day = dayDao.get(jhDay);
						JhDayVo dayVo = new JhDayVo();
						if (day !=null) {
							BeanUtils.copyProperties(day,dayVo);
							taskTemplateVo.setDayVo(dayVo);
						}

						//按周重复执行时间
						JhWeekVo weekVo = new JhWeekVo();
						JhWeek jhWeek = new JhWeek();
						jhWeek.setTestId(taskl.getId());
						JhWeek week = weekDao.get(jhWeek);
						if (week !=null) {
							BeanUtils.copyProperties(week,weekVo);
							taskTemplateVo.setWeekVo(weekVo);
						}

						//按月重复执行时间
						JhMonth jhMonth = new JhMonth();
						JhMonthVo monthVo = new JhMonthVo();
						jhMonth.setTestId(taskl.getId());
						JhMonth month = monthDao.get(jhMonth);
						if (month !=null) {
							BeanUtils.copyProperties(month,monthVo);
							taskTemplateVo.setMonthVo(monthVo);
						}
					}*/
                    templateVos.add(taskTemplateVo);
                }
            }


            recordVo.setTaskTemplateVos(templateVos);
        }
        return recordVo;
    }

    /**
     * 更新显示状态
     *
     * @param taskVo
     */
    public static void updateTaskViewState(PlanTaskVo taskVo, boolean isAppror) {
        Date date = new Date();
        if (taskVo.getTaskIsComplete() != null && taskVo.getTaskIsComplete() == 1) {
            taskVo.setViewState(RecordTaskServiceImpl.TaskStat.task_stat_complete);
            return;
        }
        if (isAppror) {
            if (taskVo.getTaskIsComplete() != null && taskVo.getTaskIsComplete() == 0) {
                taskVo.setViewState(RecordTaskServiceImpl.TaskStat.task_stat_appr);
            }
        } else {
            if (date.getTime() < taskVo.getTaskBeginTime()) {
                taskVo.setViewState(RecordTaskServiceImpl.TaskStat.task_stat_before_begin);
            } else if (date.getTime() < taskVo.getTaskEndTime()) {
                taskVo.setViewState(RecordTaskServiceImpl.TaskStat.task_stat_running);
            } else {
                taskVo.setViewState(RecordTaskServiceImpl.TaskStat.task_stat_after_time);
            }
        }
    }

}

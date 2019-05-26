package com.sgai.property.quality.service.plan;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.service.BaseDepartmentService;
import com.sgai.property.common.service.BaseEmployeeService;
import com.sgai.property.common.util.DateTools;
import com.sgai.property.common.util.plan.PlanConstants;
import com.sgai.property.commonService.dto.SpaceDto;
import com.sgai.property.commonService.service.EmpInfoServiceImpl;
import com.sgai.property.commonService.vo.DeptInfos;
import com.sgai.property.commonService.vo.DeptVo;
import com.sgai.property.commonService.vo.EmpInfoVo;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.ctl.entity.CtlEmp;
import com.sgai.property.mdm.dto.MdmDevicesUseInfoVo;
import com.sgai.property.mdm.entity.MdmDeviceClass;
import com.sgai.property.mdm.entity.MdmMatClass;
import com.sgai.property.mdm.entity.MdmMatInfo;
import com.sgai.property.mdm.service.MdmDeviceClassService;
import com.sgai.property.mdm.service.MdmDevicesUseInfoService;
import com.sgai.property.mdm.service.MdmMatClassService;
import com.sgai.property.mdm.service.MdmMatInfoService;
import com.sgai.property.quality.constants.Constants;
import com.sgai.property.quality.dao.IQtProfessionalCategoryDao;
import com.sgai.property.quality.dao.IQtTaskResultDao;
import com.sgai.property.quality.dao.IQtTestPlanDao;
import com.sgai.property.quality.dao.plan.*;
import com.sgai.property.quality.dto.SgaiEmpDto;
import com.sgai.property.quality.entity.QtPlanItem;
import com.sgai.property.quality.entity.QtProfessionalCategory;
import com.sgai.property.quality.entity.QtTaskResult;
import com.sgai.property.quality.entity.QtTestPlan;
import com.sgai.property.quality.entity.plan.*;
import com.sgai.property.quality.service.QApprovalServiceImpl;
import com.sgai.property.quality.service.TaskResultServiceImpl;
import com.sgai.property.quality.vo.CategoryTypeVo;
import com.sgai.property.quality.vo.PlanVo;
import com.sgai.property.quality.vo.TaskImgVo;
import com.sgai.property.quality.vo.TaskParam;
import com.sgai.property.quality.vo.plan.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.*;

@Service
public class RecordTaskServiceImpl {
    @Autowired
    private ITaskDao taskDao;
    @Autowired
    private ITaskImgDao taskImgDao;
    @Autowired
    private ITaskExtendDao taskExtendDao;
    @Autowired
    private ITaskPersonDao taskPersonDao;
    @Autowired
    private ITaskPersonDaoVo taskPersonDaoVo;
    @Autowired
    private ITaskDaoVo taskDaoVo;
    @Autowired
    private TaskServiceImpl taskService;
    @Autowired
    private TaskExtendServiceImpl taskExtendService;
    @Autowired
    private TaskImgServiceImpl taskImgService;
    @Autowired
    private TaskPersonServiceImpl taskPersonService;
    @Autowired
    private PlanSendMessageServiceImpl sendMessageService;
    @Autowired
    private PIRecordDaoVo recordDaoVo;

    @Autowired
    private IQtTaskResultDao taskResultDao;
    @Autowired
    private IQtTestPlanDao testPlanDao;
    @Autowired
    private TaskResultServiceImpl taskResultService;
    @Autowired
    private IQtProfessionalCategoryDao categoryDao;
    @Autowired
    private BaseDepartmentService baseDepartmentService;
    @Autowired
    private BaseEmployeeService baseEmployeeService;
    @Autowired
    private RecordTaskServiceImpl recordTaskService;
    @Autowired
    private QApprovalServiceImpl approvalService;
    @Autowired
    private MdmMatInfoService mdmMatInfoService;
    @Autowired
    private MdmMatClassService mdmMatClassService;
    @Autowired
    private MdmDeviceClassService mdmDeviceClassService;
    @Autowired
    private MdmDevicesUseInfoService mdmDevicesUseInfoService;
    @Autowired
    private EmpInfoServiceImpl empInfoService;


    public PlanTaskVo getDetail(String tId) {
        PlanTaskVo taskVo = null;

        //根据任务id查任务基础信息
        Task task = taskDao.getById(tId);
        if (task == null) {
            return taskVo;
        }
        taskVo = new PlanTaskVo();
        BeanUtils.copyProperties(task, taskVo);

        //根据任务id查询扩展信息
        TaskExtend taskExtend = new TaskExtend();
        taskExtend.setTaskId(task.getId());
        taskExtend = taskExtendDao.get(taskExtend);
        BeanUtils.copyProperties(taskExtend, taskVo,
                "id", "isDelete", "createTime", "updateTime", "remarks", "createdBy", "createdDt", "updatedBy", "updatedDt", "delFlag", "version");

        //根据任务id查询责任人列表
        TaskPerson taskPerson = new TaskPerson();
        taskPerson.setTaskId(task.getId());
        taskPerson.setPersonType(1L);
        List<TaskPerson> dutyPersonList = taskPersonDao.findList(taskPerson);
        taskVo.setDutyPersonList(dutyPersonList);

        //根据任务id查询审核人列表
        taskPerson.setPersonType(2L);
        List<TaskPerson> approverList = taskPersonDao.findList(taskPerson);
        taskVo.setApproverList(approverList);

        //判断当前人是否是审核人
        if (approverList != null) {
            String eiId = UserServletContext.getUserInfo().getEmCode();
            for (TaskPerson person : approverList) {
                if (person.getUserNo() != null && person.getUserNo().equals(eiId + "")) {
                    taskVo.setIsApprover(1L);
                    break;
                }
            }
        }
        if (taskVo.getIsApprover() == null) {
            taskVo.setIsApprover(0L);
        }

        //根据任务id查询任务图片
        Page<TaskImg> page = new Page<>();
        page.setOrderBy(" create_time asc ");
        TaskImg taskImg = new TaskImg();
        taskImg.setTaskId(task.getId());
        taskImg.setIsDelete(0L);
        taskImg.setPage(page);
        List<TaskImg> taskImgList = taskImgDao.findList(taskImg);
        if (taskImgList != null && taskImgList.size() > 0) {
            List<TaskImgVo> imgVoList = new ArrayList<>();
            for (int i = 0; i < taskImgList.size(); i++) {
                TaskImgVo vo = new TaskImgVo();
                BeanUtils.copyProperties(taskImgList.get(i), vo);
                imgVoList.add(vo);
            }
            taskVo.setImgList(imgVoList);
        }

        updateTaskViewState(taskVo, false);
        return taskVo;
    }

    /**
     * 分解重复任务
     */
    private List<Task> resolveRepeatedTask(Long startTime, Long endTime, Task task) {
        List<Task> fullList = new ArrayList<Task>();
        Long l = task.getTaskFlag();
        Date dateStartTime = new Date(startTime);
        Date dateEndTime = new Date(endTime);
        /**是重复执行的*/
        if (l.intValue() == 1) {
            Long option = task.getOptionFlag();
            Long taskBeginTime = task.getTaskBeginTime();
            Long taskEndTime = task.getTaskEndTime();
            /**任务的结束时间小于开始时间 直接返回
             * 任务的开始时间大于endTime 直接返回
             * */
            if (new Date(taskEndTime).before(new Date(startTime)) ||
                    new Date(taskBeginTime).after(new Date(endTime))
            ) {
                return fullList;
            }
            Long finalBeginTime = 0L;
            Long finalEndTime = 0L;
            if (taskBeginTime >= startTime) {
                finalBeginTime = taskBeginTime;
            } else {
                finalBeginTime = startTime;
//				 finalBeginTime =  DateTools.getHourMinuteTime(taskBeginTime,startTime);
            }
            if (taskEndTime >= endTime) {
                finalEndTime = endTime;
//				 finalEndTime  = DateTools.getHourMinuteTime(taskEndTime,endEnd);
            } else {
                finalEndTime = taskEndTime;
            }
//   		 选择重复标示：1 选择天  2 选择周  3 选择月
            if (option.intValue() == 1) {
//   			 startTime
//   			 endEnd
                List<Date> list_date = DateTools.getDates(new Date(finalBeginTime), new Date(finalEndTime));
                for (Date date : list_date) {
//   				 spring.setTaskBeginTime(date.getTime());
                    /**设置任务开始时间的小时和分钟的时间为任务开始时间的小时和分钟*/
                    task.setTaskBeginTime(DateTools.getHourMinuteFromTime1(taskBeginTime, date.getTime()));
                    /**设置任务截止时间小时和分钟的时间为任务截止时间的小时和分钟*/
                    task.setTaskEndTime(DateTools.getHourMinuteFromTime1(taskEndTime, date.getTime()));
                    try {
                        Task clone_task = (Task) task.clone();
                        fullList.add(clone_task);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }
            } else if (option.intValue() == 2) {
                String str = task.getTaskDay();
                int[] weeks = DateTools.strToWeeks(str);
                if (weeks != null) {
                    List<Date> list_date = DateTools.getDatesOfWeek(new Date(finalBeginTime), new Date(finalEndTime), weeks);
                    for (Date date : list_date) {
                        /**设置任务开始时间的小时和分钟的时间为任务开始时间的小时和分钟*/
                        task.setTaskBeginTime(DateTools.getHourMinuteFromTime1(taskBeginTime, date.getTime()));
                        /**设置任务截止时间小时和分钟的时间为任务截止时间的小时和分钟*/
                        task.setTaskEndTime(DateTools.getHourMinuteFromTime1(taskEndTime, date.getTime()));
                        try {
                            Task clone_task = (Task) task.clone();
                            fullList.add(clone_task);
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                /**重复执行月接口*/
            } else if (option.intValue() == 3) {
                String taskYear = task.getTaskYear();

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateStartTime);
                int year1 = calendar.get(Calendar.YEAR);
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(dateEndTime);
                int year2 = calendar2.get(Calendar.YEAR);

                String beginDateStr = task.getBeginDate();
                String endDateStr = task.getEndDate();
                if (!StringUtils.isBlank(taskYear)) {
                    if (year1 == year2) {
                        if (year1 == new Integer(taskYear)) {
                            List<Date> list_date = DateTools.getDates(dateStartTime, dateEndTime);
                            List<Date> list_temp = DateTools.getDatesOfMonth(list_date, beginDateStr, endDateStr);
                            for (Date date_temp : list_temp) {
//				        		spring.setTaskBeginTime(date_temp.getTime());
                                /**设置任务开始时间的小时和分钟的时间为任务开始时间的小时和分钟*/
                                task.setTaskBeginTime(DateTools.getHourMinuteFromTime1(taskBeginTime, date_temp.getTime()));
                                /**设置任务截止时间小时和分钟的时间为任务截止时间的小时和分钟*/
                                task.setTaskEndTime(DateTools.getHourMinuteFromTime1(taskEndTime, date_temp.getTime()));
                                try {
                                    Task clone_task = (Task) task.clone();
                                    fullList.add(clone_task);
                                } catch (CloneNotSupportedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        /***/
                    } else {
                        /**前端传值跨年份的情况*/
                        /**taskYear= 2018 表示任务只在2018年执行 */
                        if (year1 == new Integer(taskYear)) {
                            /**任务执行的年底最后一天*/
                            String endDayStr = taskYear + "-12-" + task.getEndDate();
                            long endDay = 0;
                            try {
                                endDay = DateUtils.parseDate(endDayStr, Locale.CHINA, "yyyy-MM-dd").getTime();
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                            }
                            Date date_endDay = new Date(endDay);
                            List<Date> list_date = DateTools.getDates(dateStartTime, date_endDay);
                            List<Date> list_temp = DateTools.getDatesOfMonth(list_date, beginDateStr, endDateStr);
                            for (Date date_temp : list_temp) {
//				        		spring.setTaskBeginTime(date_temp.getTime());
                                /**设置任务开始时间的小时和分钟的时间为任务开始时间的小时和分钟*/
                                task.setTaskBeginTime(DateTools.getHourMinuteFromTime1(taskBeginTime, date_temp.getTime()));
                                /**设置任务截止时间小时和分钟的时间为任务截止时间的小时和分钟*/
                                task.setTaskEndTime(DateTools.getHourMinuteFromTime1(taskEndTime, date_temp.getTime()));
                                try {
                                    Task clone_task = (Task) task.clone();
                                    fullList.add(clone_task);
                                } catch (CloneNotSupportedException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else if (year2 == new Integer(taskYear)) {
                            /**任务执行的年前第一天*/
                            String beginDayStr = taskYear + "-1-" + task.getBeginDate();
                            long beginDay = 0;
                            try {
                                beginDay = DateUtils.parseDate(beginDayStr, Locale.CHINA, "yyyy-MM-dd").getTime();
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                            }
                            Date date_beginDay = new Date(beginDay);
                            List<Date> list_date = DateTools.getDates(date_beginDay, dateEndTime);
                            List<Date> list_temp = DateTools.getDatesOfMonth(list_date, beginDateStr, endDateStr);
                            for (Date date_temp : list_temp) {
//				        		spring.setTaskBeginTime(date_temp.getTime());
                                /**设置任务开始时间的小时和分钟的时间为任务开始时间的小时和分钟*/
                                task.setTaskBeginTime(DateTools.getHourMinuteFromTime1(taskBeginTime, date_temp.getTime()));
                                /**设置任务截止时间小时和分钟的时间为任务截止时间的小时和分钟*/
                                task.setTaskEndTime(DateTools.getHourMinuteFromTime1(taskEndTime, date_temp.getTime()));
                                try {
                                    Task clone_task = (Task) task.clone();
                                    fullList.add(clone_task);
                                } catch (CloneNotSupportedException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            return fullList;
                        }
                    }


                }
            }
        }
        return fullList;
    }

    /** 查任务计划 */
    public List<TaskTPStatVo> taskStateMonth(String eiId, Long startTime, Long endEnd, Integer type) {
        if (startTime > endEnd) {
            throw new BusinessException(ReturnType.ParamIllegal, "时间参数异常");
        }
        List<TaskTPStatVo> result = new ArrayList<TaskTPStatVo>();
//        Date dateStartTime = new Date(startTime);
//        Date dateEndTime = new Date(endEnd);
//        if (dateStartTime) {
//
//		}
//        TimeZone zone = TimeZone.getTimeZone("GMT+8");
//        String dateStartStr = DateFormatUtils.format(dateStartTime,"yyyy-MM-dd", zone);
//        String dateEndStr = DateFormatUtils.format(dateEndTime,"yyyy-MM-dd", zone);
//
//        try {
//            dateStartTime = DateUtils.parseDate(dateStartStr, Locale.CHINA, "yyyy-MM-dd");
//            dateEndTime = DateUtils.parseDate(dateEndStr, Locale.CHINA, "yyyy-MM-dd");
//        } catch (Exception e) {
//            throw new BusinessException(ReturnType.ParamIllegal, "时间参数异常");
//        }
        /**EiId人员Id*/

        //查我负责的任务
        Map<String, Object> map = new HashMap<>();
        map.put("startTime", startTime);
        map.put("endTime", endEnd);
        map.put("taskIds", null);
        map.put("isDelete", 1L);
//        /**查不重复任务*/
        map.put("taskFlag", 0L);
        if (type == null) {
            map.put("typeFlag", 0);
        } else {
            map.put("typeFlag", type);
        }
        map.put("comCode", UserServletContext.getUserInfo().getComCode());
        List<Task> taskLists = null;
        taskLists = taskDaoVo.taskStateMonth(map);
        Map<String, List<TaskTPStat>> taskStatMap = new HashMap<String, List<TaskTPStat>>();
        Task task = null;
        List<Task> fullList = new ArrayList<Task>();
        fullList.addAll(taskLists);
        /**添加重复执行任务Start*/
        map.put("taskIds", null);
        map.put("isDelete", 1L);
        map.put("startTime", null);
        map.put("endTime", null);
        /**是重复任务*/
        map.put("taskFlag", 1L);
        if (type == null) {
            map.put("typeFlag", 0);
        } else {
            map.put("typeFlag", type);
        }
        map.put("comCode", UserServletContext.getUserInfo().getComCode());
        taskLists = taskDaoVo.taskStateMonth(map);
        if (taskLists.size() > 0) {
            for (Task task_temp : taskLists) {
                List<Task> List_temp = resolveRepeatedTask(startTime, endEnd, task_temp);
                fullList.addAll(List_temp);
            }
        }

        /**根据taskId获取执行人信息Start*/
        List<String> list_taskIds = new ArrayList<String>();
        if (fullList.size() > 0) {
            for (int i = 0; i < fullList.size(); i++) {
                task = fullList.get(i);
                if (!list_taskIds.contains(task.getId())) {
                    list_taskIds.add(task.getId());
                }
            }
        }

        if (list_taskIds.size() > 0) {
            List<TaskPerson> list_taskPerson = taskPersonDao.getByTaskIds(list_taskIds);
            /**根据taskId获取执行人信息end*/
            for (int i = 0; i < fullList.size(); i++) {
                List<String> list_eiEmpName = new ArrayList<String>();
                List<String> list_eiEmpId = new ArrayList<String>();
                task = fullList.get(i);
                /**添加执行人信息start*/
                String taskId = task.getId();
                for (int j = 0; j < list_taskPerson.size(); j++) {
                    TaskPerson tmp_taskPerson = list_taskPerson.get(j);
                    if (taskId.equals(tmp_taskPerson.getTaskId())) {
                        list_eiEmpName.add(tmp_taskPerson.getEiEmpName());
                        list_eiEmpId.add(tmp_taskPerson.getUserNo());
                    }
                }
                /**添加执行人信息end*/
                //将task状态放到日状态map中
                fillTaskStatMap(task, taskStatMap, list_eiEmpName, list_eiEmpId);
            }
            /**组装数据回显*/
            Iterator<String> it = taskStatMap.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                List<TaskTPStat> lists = taskStatMap.get(key);
                TaskTPStatVo vo = new TaskTPStatVo();
                try {
                    vo.setDt(DateUtils.parseDate(key, Locale.CHINA, "yyyy-MM-dd").getTime());
                } catch (ParseException e) {
                    throw new BusinessException(ReturnType.ParamIllegal, "");
                }
                vo.setTaskTPList(lists);
                result.add(vo);

            }
        }
        return result;
    }


    /** 查任务计划 */
    public List<TaskTPStatVo> taskStateMonth1(Long eiId, Long startTime, Long endEnd, Integer type) {
        if (startTime > endEnd) {
            throw new BusinessException(ReturnType.ParamIllegal, "时间参数异常");
        }
        List<TaskTPStatVo> result = new ArrayList<TaskTPStatVo>();
//        Date dateStartTime = new Date(startTime);
//        Date dateEndTime = new Date(endEnd);
//        if (dateStartTime) {
//
//		}
//        TimeZone zone = TimeZone.getTimeZone("GMT+8");
//        String dateStartStr = DateFormatUtils.format(dateStartTime,"yyyy-MM-dd", zone);
//        String dateEndStr = DateFormatUtils.format(dateEndTime,"yyyy-MM-dd", zone);
//
//        try {
//            dateStartTime = DateUtils.parseDate(dateStartStr, Locale.CHINA, "yyyy-MM-dd");
//            dateEndTime = DateUtils.parseDate(dateEndStr, Locale.CHINA, "yyyy-MM-dd");
//        } catch (Exception e) {
//            throw new BusinessException(ReturnType.ParamIllegal, "时间参数异常");
//        }
        /**EiId人员Id*/

        //查我负责的任务
        Map<String, Object> map = new HashMap<>();
        map.put("startTime", startTime);
        map.put("endTime", endEnd);
        map.put("taskIds", null);
        map.put("isDelete", 1L);
//        /**查不重复任务*/
        map.put("taskFlag", 0L);
        if (type == null) {
            map.put("typeFlag", 0);
        } else {
            map.put("typeFlag", type);
        }

        List<Task> taskLists = null;
        taskLists = taskDaoVo.taskStateMonth(map);
        Map<String, List<TaskTPStat>> taskStatMap = new HashMap<String, List<TaskTPStat>>();
        Task task = null;
        List<Task> fullList = new ArrayList<Task>();
        fullList.addAll(taskLists);
        /**添加重复执行任务Start*/
        map.put("taskIds", null);
        map.put("isDelete", 1L);
        map.put("startTime", null);
        map.put("endTime", null);
        /**是重复任务*/
        map.put("taskFlag", 1L);
        if (type == null) {
            map.put("typeFlag", 0);
        } else {
            map.put("typeFlag", type);
        }
        taskLists = taskDaoVo.taskStateMonth(map);
        if (taskLists.size() > 0) {
            for (Task task_temp : taskLists) {
                List<Task> List_temp = resolveRepeatedTask(startTime, endEnd, task_temp);
                fullList.addAll(List_temp);
            }
        }

        /**根据taskId获取执行人信息Start*/
        List<String> list_taskIds = new ArrayList<String>();
        if (fullList.size() > 0) {
            for (int i = 0; i < fullList.size(); i++) {
                task = fullList.get(i);
                if (!list_taskIds.contains(task.getId())) {
                    list_taskIds.add(task.getId());
                }
            }
        }

        if (list_taskIds.size() > 0) {
            List<TaskPerson> list_taskPerson = taskPersonDao.getByTaskIds(list_taskIds);
            /**根据taskId获取执行人信息end*/
            for (int i = 0; i < fullList.size(); i++) {
                List<String> list_eiEmpName = new ArrayList<String>();
                List<String> list_eiEmpId = new ArrayList<String>();
                task = fullList.get(i);
                /**添加执行人信息start*/
                String taskId = task.getId();
                for (int j = 0; j < list_taskPerson.size(); j++) {
                    TaskPerson tmp_taskPerson = list_taskPerson.get(j);
                    if (taskId.equals(tmp_taskPerson.getTaskId())) {
                        list_eiEmpName.add(tmp_taskPerson.getEiEmpName());
                        list_eiEmpId.add(tmp_taskPerson.getUserNo());
                    }
                }
                /**添加执行人信息end*/
                //将task状态放到日状态map中
                fillTaskStatMap(task, taskStatMap, list_eiEmpName, list_eiEmpId);
            }
            /**组装数据回显*/
            Iterator<String> it = taskStatMap.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                List<TaskTPStat> lists = taskStatMap.get(key);
                TaskTPStatVo vo = new TaskTPStatVo();
                try {
                    vo.setDt(DateUtils.parseDate(key, Locale.CHINA, "yyyy-MM-dd").getTime());
                } catch (ParseException e) {
                    throw new BusinessException(ReturnType.ParamIllegal, "");
                }
                vo.setTaskTPList(lists);
                result.add(vo);

            }
        }
        return result;
    }


    public List<TaskTPStatVo> taskStateMonthByExecutor(String eiId, Long startTime, Long endTime, Integer type) {
        if (startTime > endTime) {
            throw new BusinessException(ReturnType.ParamIllegal, "时间参数异常");
        }
        if (StringUtils.isEmpty(eiId)) {
            throw new BusinessException(ReturnType.ParamIllegal, "员工ID不能为空");
        }
        /**查询该ID下所有计划*/
        TaskPerson taskPerson = new TaskPerson();
        taskPerson.setEiId(eiId);
        List<TaskPerson> list_taskPerson = taskPersonDao.findListById(taskPerson);
        if (list_taskPerson == null || list_taskPerson.isEmpty()) {
            throw new BusinessException(ReturnType.ParamIllegal, eiId + "下没有执行的计划");
        }
        List<TaskTPStatVo> result = new ArrayList<TaskTPStatVo>();
        /**查询这段时间内的所有计划*/
        /**EiId人员Id*/
        List<Task> taskLists = Lists.newArrayList();

        //查我负责的任务
        Map<String, Object> map = new HashMap<>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("taskIds", null);
        map.put("isDelete", 1L);
//      /**查不重复任务*/
        map.put("taskFlag", 0L);
        if (type == null) {
            map.put("typeFlag", 0);
        } else {
            map.put("typeFlag", type);
        }
        map.put("comCode", UserServletContext.getUserInfo().getComCode());
        taskLists = taskDaoVo.taskStateMonth(map);
        /**这段时间内我负责的计划 不重复任务*/
        List<String> list_eiEmpName = new ArrayList<String>();
        List<Task> taskList1 = new ArrayList<Task>();
        for (int i = 0; i < list_taskPerson.size(); i++) {
            TaskPerson temp_taskPerson = list_taskPerson.get(i);
            /**得到JH_task id列*/
            String taskId = temp_taskPerson.getTaskId();
            if (taskId == null || "".equals(taskId)) {
                throw new BusinessException(ReturnType.ParamIllegal, "taskId不能为空");
            }
            for (int j = 0; j < taskLists.size(); j++) {
                Task task = taskLists.get(j);
                String id = task.getId();
                if (taskId.equals(id)) {
//        			String eiEmpName = temp_taskPerson.getEiEmpName();
                    taskList1.add(task);
//        			list_eiEmpName.add(eiEmpName);
                    break;
                }
            }
        }
        /**我负责的重复任务*/
        List<Task> taskList2 = new ArrayList<Task>();
        map.put("startTime", null);
        map.put("endTime", null);
        map.put("taskFlag", 1L);
        if (type == null) {
            map.put("typeFlag", 0);
        } else {
            map.put("typeFlag", type);
        }
        map.put("comCode", UserServletContext.getUserInfo().getComCode());
        taskLists = taskDaoVo.taskStateMonth(map);
        for (int i = 0; i < list_taskPerson.size(); i++) {
            TaskPerson temp_taskPerson = list_taskPerson.get(i);
            /**得到JH_task id列*/
            String taskId = temp_taskPerson.getTaskId();
            if (taskId == null || "".equals(taskId)) {
                throw new BusinessException(ReturnType.ParamIllegal, "taskId不能为空");
            }
            for (int j = 0; j < taskLists.size(); j++) {
                Task task = taskLists.get(j);
                String id = task.getId();
                if (taskId.equals(id)) {
                    taskList2.add(task);
                    break;
                }
            }
        }
        List<Task> finalList = new ArrayList<Task>();
        finalList.addAll(taskList1);
        for (Task task : taskList2) {
            List<Task> List_temp = resolveRepeatedTask(startTime, endTime, task);
            finalList.addAll(List_temp);
        }
        /***/

        list_eiEmpName.add(list_taskPerson.get(0).getEiEmpName());
        Map<String, List<TaskTPStat>> taskStatMap = new HashMap<String, List<TaskTPStat>>();
        List<String> list_eiEmpId = new ArrayList<String>();
        list_eiEmpId.add(list_taskPerson.get(0).getUserNo());
        /**把所有任务状态存入map*/
        if (finalList.size() > 0) {
            for (int i = 0; i < finalList.size(); i++) {
                Task task = finalList.get(i);
                fillTaskStatMap(task, taskStatMap, list_eiEmpName, list_eiEmpId);
            }
        }


        /**数据回显*/
        Iterator<String> it = taskStatMap.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            List<TaskTPStat> lists = taskStatMap.get(key);
            TaskTPStatVo vo = new TaskTPStatVo();
            try {
                vo.setDt(DateUtils.parseDate(key, Locale.CHINA, "yyyy-MM-dd").getTime());
            } catch (ParseException e) {
                throw new BusinessException(ReturnType.ParamIllegal, "");
            }

            vo.setTaskTPList(lists);
            result.add(vo);

        }

        return result;
    }

    public List<String> getValidList(List<String> list) {
        List<String> newList = new ArrayList<String>();
        HashSet<String> set = new HashSet<>(list);
        newList.clear();
        newList.addAll(set);
        return newList;
    }

    public List<DayTaskVo> taskDay(Long eiId, Long dateTime) {
        List<DayTaskVo> result = new ArrayList<DayTaskVo>();
        Date date = new Date(dateTime);
        String dateStr = DateFormatUtils.format(date, "yyyy-MM-dd");
        try {
            date = DateUtils.parseDate(dateStr, "yyyy-MM-dd");
        } catch (ParseException e) {
            throw new BusinessException(ReturnType.ParamIllegal, "", e);
        }
        Date tomorrow = DateUtils.addDays(date, 1);

        //查我审核的TaskId列表
        TaskPerson taskPerson = new TaskPerson();
        taskPerson.setEiId(eiId + "");
        taskPerson.setPersonType(2L);
        List<String> approvalTaskIds = taskPersonDaoVo.getTaskIdsByEiId(taskPerson);
        //查我负责的TaskId列表
        taskPerson.setPersonType(1L);
        List<String> dutyTaskIds = taskPersonDaoVo.getTaskIdsByEiId(taskPerson);

        //查当天我审核的和我负责的RecordId列表
        List<String> taskIds = new ArrayList<>();
        taskIds.addAll(dutyTaskIds);
        taskIds.addAll(approvalTaskIds);
        taskIds = getValidList(taskIds);
        if (taskIds == null || taskIds.size() == 0) {
            return result;
        }
        //查当天我审核的RecordID列表
        Map<String, Object> map = new HashMap<>();
        map.put("startTime", date.getTime());
        map.put("endTime", tomorrow.getTime());
        map.put("taskIds", dutyTaskIds);
        map.put("isDelete", 1L);
        List<String> dutyRecordIdList = null;
        if (!CollectionUtils.isEmpty(dutyTaskIds)) {
            dutyRecordIdList = taskDaoVo.getRecordIds(map);
        }

        //查当天我负责的RecordId列表
        List<String> approvalRecordIdList = null;
        if (!CollectionUtils.isEmpty(approvalTaskIds)) {
            map.put("taskIds", approvalTaskIds);
            map.put("byAppr", 1L);
            approvalRecordIdList = taskDaoVo.getRecordIds(map);
        }
        List<String> recordIdList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(approvalRecordIdList)) {
            recordIdList.addAll(approvalRecordIdList);
        }
        if (!CollectionUtils.isEmpty(dutyRecordIdList)) {
            recordIdList.addAll(dutyRecordIdList);
        }
        recordIdList = getValidList(recordIdList);
        if (recordIdList == null || recordIdList.size() == 0) {
            return result;
        }
        //查当天我审核的和我负责的计划列表
        Map<String, Object> mapParam = new HashMap<>();
        mapParam.put("ids", recordIdList);
        mapParam.put("isDelete", 1L);
        List<Record> recordList = recordDaoVo.taskDay(mapParam);
        for (int i = 0; i < recordList.size(); i++) {
            Record record = recordList.get(i);
            fillDayTaskVo(result, record, eiId, date, approvalTaskIds, dutyTaskIds);
        }
        return result;
    }

    //填充计划的任务列表
    private void fillDayTaskVo(List<DayTaskVo> result,
                               Record record, Long eiId, Date dateTime,
                               List<String> approvalTaskIds, List<String> dutyTaskIds) {
        Date tomorow = DateUtils.addDays(dateTime, 1);
        //查当天我负责的task列表
        Map<String, Object> map = new HashMap<>();
        map.put("startTime", dateTime.getTime());
        map.put("endTime", tomorow.getTime());
        map.put("taskIds", dutyTaskIds);
        map.put("isDelete", 1L);
        map.put("recordId", record.getId());
        List<Task> taskListExec = new ArrayList<Task>();
        List<PlanTaskVo> taskListExecVo = new ArrayList<PlanTaskVo>();
        if (dutyTaskIds != null && dutyTaskIds.size() > 0) {
            taskListExec.addAll(taskDaoVo.getTaskList(map));
            if (taskListExec != null && taskListExec.size() > 0) {
                for (int i = 0; i < taskListExec.size(); i++) {
                    PlanTaskVo vo = new PlanTaskVo();
                    BeanUtils.copyProperties(taskListExec.get(i), vo);
                    TaskPerson taskPerson = new TaskPerson();
                    taskPerson.setTaskId(taskListExec.get(i).getId());
                    taskPerson.setPersonType(1L);
                    List<TaskPerson> dutyPersonList = taskPersonDao.findList(taskPerson);
                    vo.setDutyPersonList(dutyPersonList);
                    taskListExecVo.add(vo);
                }
            }
            for (PlanTaskVo vo : taskListExecVo) {
                updateTaskViewState(vo, false);
            }
        }


        //查当天我审核的task列表
        map.put("taskIds", approvalTaskIds);
        map.put("isByAppr", 1);
        List<Task> taskListAppr = new ArrayList<Task>();
        List<PlanTaskVo> taskListApprVo = new ArrayList<PlanTaskVo>();
        if (approvalTaskIds != null && approvalTaskIds.size() > 0) {
            taskListAppr.addAll(taskDaoVo.getTaskList(map));
            if (taskListAppr != null && taskListAppr.size() > 0) {
                for (int i = 0; i < taskListAppr.size(); i++) {
                    PlanTaskVo vo = new PlanTaskVo();
                    BeanUtils.copyProperties(taskListAppr.get(i), vo);
                    TaskPerson taskPerson = new TaskPerson();
                    taskPerson.setTaskId(taskListAppr.get(i).getId());
                    taskPerson.setPersonType(2L);
                    List<TaskPerson> approverList = taskPersonDao.findList(taskPerson);
                    vo.setApproverList(approverList);
                    taskListApprVo.add(vo);
                }
            }

            for (PlanTaskVo vo : taskListApprVo) {
                updateTaskViewState(vo, true);
            }
        }

        DayTaskVo dayVo = new DayTaskVo();
        dayVo.setRecordName(record.getRecordName());
        dayVo.setRecordId(record.getId());
        dayVo.setTaskList(new ArrayList<PlanTaskVo>());
        dayVo.getTaskList().addAll(taskListExecVo);
        dayVo.getTaskList().addAll(taskListApprVo);
        result.add(dayVo);
    }

    @Transactional
    public boolean saveOrUpdate(TaskParam taskParam) {

        if (taskParam == null) {
            throw new BusinessException(ReturnType.ParamIllegal, "");
        }
        //判断当前计划下任务名称是否重复
        Task tempTask = new Task();
        tempTask.setRecordId(taskParam.getRecordId());
        tempTask.setTaskName(taskParam.getTaskName());
        tempTask.setIsDelete(0L);
        int num = taskDao.getCount(tempTask);
        if (num > 0) {
            throw new BusinessException(ReturnType.ParamIllegal, "任务名称不能重复");
        }

        //处理任务排序
        Task task = new Task();
        task.setRecordId(taskParam.getRecordId());
        task.setIsDelete(0L);
        int count = taskDao.getCount(task);

        //处理任务基础信息
        task = new Task();
        task.setTaskSort(count + 1L);
        BeanUtils.copyProperties(taskParam, task);
        Date date = new Date();
        task.setCreateTime(date.getTime());
        task.setUpdateTime(date.getTime());
        try {
            //处理任务创建者信息
//        	SgaiEmpDto sgaiEmp = commonsRomeotService.getSgaiEmpById(sgaiToken, UserServletContext.getUserInfo().getEmCode()).getData();
            task.setTaskCreatorEiId(UserServletContext.getUserInfo().getEmCode());
            task.setTaskCreatorEiEmpName(UserServletContext.getUserInfo().getUserName());
            task.setTaskCreatorEiEmpImg(null);
        } catch (Exception ex) {
            throw new BusinessException(ReturnType.ThirdParty, "", ex);
        }
        //保存任务基础信息
        taskService.save(task);

        if (task.getId() != null) {
            TaskExtend extend = new TaskExtend();
            BeanUtils.copyProperties(taskParam, extend);
            extend.setTaskId(task.getId());
            extend.setCreateTime(date.getTime());
            extend.setUpdateTime(date.getTime());
            taskExtendService.save(extend);

            //处理任务图片
            if (taskParam.getImgList() != null) {
                TaskImg img;
                for (TaskImgVo imgVo : taskParam.getImgList()) {
                    img = new TaskImg();
                    BeanUtils.copyProperties(imgVo, img);
                    img.setCreateTime(date.getTime());
                    img.setUpdateTime(date.getTime());
                    img.setTaskId(task.getId());
                    taskImgService.save(img);
                }
            }

            //处理任务责任人
            List<TaskPerson> dutyPersonList = null;
            String dutyPersonNameStr = "";
            if (taskParam.getDutyPersonIdList() != null && taskParam.getDutyPersonIdList().size() > 0) {
                dutyPersonList = new ArrayList<>();
                for (String eiId : taskParam.getDutyPersonIdList()) {
                    TaskPerson person = new TaskPerson();
                    //处理任务责任人信息
                    SgaiEmpDto sgaiEmp = null;//commonsRomeotService.getSgaiEmpById(sgaiToken, eiId).getData();
                    person.setTaskId(task.getId());
                    person.setPersonType(1L);
                    person.setEiId(eiId);
                    person.setEiEmpName(sgaiEmp.getLastname());
                    person.setFeedId(null);
                    person.setToonUserId(null);
                    person.setPersonIcon(null);
                    person.setCreateTime(date.getTime());
                    person.setUpdateTime(date.getTime());
                    taskPersonService.save(person);
                    dutyPersonNameStr += sgaiEmp.getLastname() + ",";
                    dutyPersonList.add(person);
//                    EmpInfoVo empInfoVo = commonsRomeotService.getEmpInfoById(UserServletContext.getUserInfo().getCompanyId(), Long.parseLong(eiId)).getData();
//                    person.setTaskId(spring.getId());
//                    person.setPersonType(1L);
//                    person.setEiId(eiId);
//                    person.setEiEmpName(empInfoVo.getEiEmpName());
//                    person.setFeedId(empInfoVo.getFeedId());
//                    person.setToonUserId(empInfoVo.getToonUserId() + "");
//                    person.setPersonIcon(empInfoVo.getEiHeadPicture());
//                    person.setCreateTime(date.getTime());
//                    person.setUpdateTime(date.getTime());
//                    taskPersonService.save(person);
//                    dutyPersonNameStr += empInfoVo.getEiEmpName() + ",";
//                    dutyPersonList.add(person);
                }
            }

            //处理任务审核人
            if (taskParam.getApproverList() != null && taskParam.getApproverList().size() > 0) {
                for (String eiId : taskParam.getApproverList()) {
                    TaskPerson person = new TaskPerson();
                    //处理任务审核人信息
                    DeptInfos sgaidEmp = null;// baseEmployeeService.get(sgaiToken, eiId).getData();
                    person.setTaskId(task.getId());
                    person.setPersonType(2L);
                    person.setEiId(eiId);
                    // person.setEiEmpName(sgaiEmp.getEmpCode());
                    person.setFeedId(null);
                    person.setToonUserId(null);
                    person.setPersonIcon(null);
                    person.setCreateTime(date.getTime());
                    person.setUpdateTime(date.getTime());
                    taskPersonService.save(person);
                }
            }

            //给责任人发通知
            if (dutyPersonList != null) {
                Date dateStart = new Date(task.getTaskBeginTime());
                Date dateEnd = new Date(task.getTaskEndTime());
                dutyPersonNameStr = dutyPersonNameStr.substring(0, dutyPersonNameStr.length() - 1);
                for (int i = 0; i < dutyPersonList.size(); i++) {
                    TaskPerson person = dutyPersonList.get(i);
                    String subCatalog = "任务通知";
                    String message = "任务名称：";
                    message += task.getTaskName().length() > 14 ? task.getTaskName().substring(0, 14) + "..." : task.getTaskName();
                    message += "\n开始时间：" + DateFormatUtils.format(dateStart, "yyyy-MM-dd HH:mm");
                    message += "\n截止时间：" + DateFormatUtils.format(dateEnd, "yyyy-MM-dd HH:mm");
                    message += "\n责任人：" + dutyPersonNameStr;
//            		sendMessageService.sendMessage(UserServletContext.getUserInfo().getCompanyId(), Long.parseLong(person.getUserNo()), message, subCatalog, spring.getId(), 0);
                }
            }
            return true;
        }
        return false;
    }


    @Transactional
    public String saveTaskQuality(TaskParam taskParam) {

        if (taskParam == null) {
            throw new BusinessException(ReturnType.ParamIllegal, "");
        }

        //判断当前计划下任务名称是否重复
        Task tempTask = new Task();
        tempTask.setRecordId(taskParam.getRecordId());
        tempTask.setTemplateId(taskParam.getTemplateId());

        tempTask.setIsDelete(0L);
        int num = taskDao.getCount(tempTask);
        if (num > 0) {
            throw new BusinessException(ReturnType.ParamIllegal, "模板在同一个计划中不能重复关联!");
        }

        //处理任务排序
        Task task = new Task();
        task.setRecordId(taskParam.getRecordId());
        task.setIsDelete(0L);
        int count = taskDao.getCount(task);

        //处理任务基础信息
        task = new Task();
        task.setTaskSort(count + 1L);
        BeanUtils.copyProperties(taskParam, task);
        task.setCreateTime(System.currentTimeMillis());
        task.setUpdateTime(System.currentTimeMillis());
        task.setCreatedBy(UserServletContext.getUserInfo().getUserName());
        task.setTaskResultTime(taskParam.getTaskEndTime());
        task.setTaskCreatorEiEmpName(UserServletContext.getUserInfo().getUserName());
        task.setCreatedDt(new Date());
        task.setUpdatedDt(new Date());
        task.setTaskName(UserServletContext.getUserInfo().getUserName());
        task.setTaskDay(taskParam.getTaskDay());
        task.setTaskYear(taskParam.getMonthVo().getJhYear());
        task.setBeginDate(taskParam.getMonthVo().getBeginDate());
        task.setEndDate(taskParam.getMonthVo().getEndDate());
        task.setBeginTime(taskParam.getMonthVo().getBeginTime());
        task.setEndTime(taskParam.getMonthVo().getEndTime());
        task.setBeginMonth(taskParam.getMonthVo().getBeginMonth());
        task.setEndMonth(taskParam.getMonthVo().getEndMonth());
        task.setTypeFlag(taskParam.getType());
        task.setComCode(UserServletContext.getUserInfo().getComCode());
        task.setModuCode(UserServletContext.getUserInfo().getModuCode());

        /*try{
            //处理任务创建者信息
            EmpInfoVo empInfoVo = commonsRomeotService.getEmpInfoById(UserServletContext.getUserInfo().getCompanyId(), UserServletContext.getUserInfo().getEmCode()).getData();
            spring.setTaskCreatorEiId(empInfoVo.getUserNo() + "");
            spring.setTaskCreatorEiEmpName(empInfoVo.getEiEmpName());
            spring.setTaskCreatorEiEmpImg(empInfoVo.getEiHeadPicture());
        }catch (Exception ex){
            throw new BusinessException(ReturnType.ThirdParty, "", ex);
        }*/
        System.out.println("=================保存任务基础信息开始");
        //保存任务基础信息
        taskService.save(task);
        System.out.println("=================保存任务基础信息结束");

        if (task.getId() != null) {
           /* TaskExtend extend = new TaskExtend();
            BeanUtils.copyProperties(taskParam, extend);
            extend.setTaskId(spring.getId());
            extend.setCreateTime(date.getTime());
            extend.setUpdateTime(date.getTime());
            taskExtendService.save(extend);*/

            //处理任务图片
            /*if(taskParam.getImgList() != null){
                TaskImg img ;
                for(TaskImgVo imgVo : taskParam.getImgList()){
                    img = new TaskImg();
                    BeanUtils.copyProperties(imgVo, img);
                    img.setCreateTime(date.getTime());
                    img.setUpdateTime(date.getTime());
                    img.setTaskId(spring.getId());
                    taskImgService.save(img);
                }
            }*/

            //处理执行人
            List<TaskPerson> dutyPersonList = null;
            String dutyPersonNameStr = "";
            if (taskParam.getDutyPersonIdList() != null && taskParam.getDutyPersonIdList().size() > 0) {
                dutyPersonList = new ArrayList<>();
                for (String eiId : taskParam.getDutyPersonIdList()) {
                    TaskPerson person = new TaskPerson();
                    //处理任务责任人信息
                    CtlEmp sgaiEmp = empInfoService.getByCode(eiId);
                    if (sgaiEmp != null) {
                        person.setTaskId(task.getId());
//                    person.setPersonType(code.getUserType());
                        person.setEiId(eiId);
                        person.setEiEmpName(sgaiEmp.getLastname());
                        person.setCreateTime(System.currentTimeMillis());
                        person.setUpdateTime(System.currentTimeMillis());
                        taskPersonService.save(person);
                        dutyPersonNameStr += UserServletContext.getUserInfo().getUserName() + ",";
                        dutyPersonList.add(person);
                    }

                }
            }

            //初始化模板关联的任务项的状态  QT_TASK_RESULT

            //根据模板ID 查询关联的任务项
            /*QtPlanItem planItem = new QtPlanItem();
            planItem.setValid(0);
            planItem.setTpId(taskParam.getTemplateId());
            List<QtPlanItem> plList = qtPlanItemDao.findListByTemplateId(planItem);
            if (plList.size()>0) {
                for (QtPlanItem pl : plList) {
                    QtTaskResult taskResult = new QtTaskResult();
                    taskResult.setId(IdGen.uuid());
                    taskResult.setTpId(pl.getTpId());
                    taskResult.setTpName(taskParam.getTemplateName());
                    taskResult.setRecordId(taskParam.getRecordId());
                    taskResult.setTiId(pl.getTiId());
                    taskResult.setTiStatus(0L);
                    taskResult.setCreatedDt(new Date());
                    taskResultDao.insert(taskResult);
                }


            }*/
            //处理任务审核人
            /*if(taskParam.getApproverList() != null && taskParam.getApproverList().size() > 0){
                for(String eiId : taskParam.getApproverList()){
                    TaskPerson person = new TaskPerson();
                    //处理任务审核人信息
                    EmpInfoVo empInfoVo = commonsRomeotService.getEmpInfoById(UserServletContext.getUserInfo().getCompanyId(), Long.parseLong(eiId)).getData();
                    person.setTaskId(spring.getId());
                    person.setPersonType(2L);
                    person.setEiId(eiId);
                    person.setEiEmpName(empInfoVo.getEiEmpName());
                    person.setFeedId(empInfoVo.getFeedId());
                    person.setToonUserId(empInfoVo.getToonUserId() + "");
                    person.setPersonIcon(empInfoVo.getEiHeadPicture());
                    person.setCreateTime(date.getTime());
                    person.setUpdateTime(date.getTime());
                    taskPersonService.save(person);
                }
            }*/

            //给责任人发通知
            /*if(dutyPersonList != null) {
                try {
                    Date dateStart = new Date(spring.getTaskBeginTime());
                    Date dateEnd = new Date(spring.getTaskEndTime());
                    dutyPersonNameStr = dutyPersonNameStr.substring(0, dutyPersonNameStr.length() - 1);
                    for (int i = 0; i < dutyPersonList.size(); i++) {
                        TaskPerson person = dutyPersonList.get(i);
                        String subCatalog = "任务通知";
                        String message = "任务名称：";
                        message += spring.getTaskName().length() > 14 ? spring.getTaskName().substring(0, 14) + "...": spring.getTaskName();
                        message += "\n开始时间：" + DateFormatUtils.format(dateStart, "yyyy-MM-dd HH:mm");
                        message += "\n截止时间：" + DateFormatUtils.format(dateEnd, "yyyy-MM-dd HH:mm");
                        message += "\n责任人：" + dutyPersonNameStr;
                        sendMessageService.sendMessage(UserServletContext.getUserInfo().getCompanyId(), Long.parseLong(person.getUserNo()), message, subCatalog, spring.getId(), 0);
                    }
                } catch (Exception e) {
                }
            }*/
            return task.getId();
        }
        return task.getId();
    }

    //任务新增审核功能
    @Transactional
    public boolean addTaskQualityApproval(String taskId, Long comId, List<String> eiIds) {
        try {
            //处理任务审核人
            long currentTime = System.currentTimeMillis();
            for (String eiId : eiIds) {
                TaskPerson person = new TaskPerson();
                //处理任务审核人信息
                EmpInfoVo empInfoVo = baseEmployeeService.getEmpInfoById(comId, eiId);
                person.setTaskId(taskId);
                person.setPersonType(2L);
                person.setEiId(eiId);
                person.setEiEmpName(empInfoVo.getEiEmpName());
                person.setFeedId(empInfoVo.getFeedId());
                person.setToonUserId(empInfoVo.getToonUserId() + "");
                person.setPersonIcon(empInfoVo.getEiHeadPicture());
                person.setCreateTime(currentTime);
                person.setUpdateTime(currentTime);
                taskPersonService.save(person);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isInclude(Long eiId, List<TaskPerson> personList) {
        if (personList != null && personList.size() > 0) {
            for (int i = 0; i < personList.size(); i++) {
                if (personList.get(i).getUserNo().equals(eiId + "")) {
                    return true;
                }
            }
        }
        return false;
    }

    @Transactional
    public boolean completeTask(String taskId, String taskResult) {

        Long eiId = Long.valueOf(UserServletContext.getUserInfo().getEmCode());

        Task task = taskDao.getById(taskId);
        if (task == null) {
            throw new BusinessException(ReturnType.DB, "查询的数据不存在");
        } else if (task.getTaskResultTime() > 0) {
            throw new BusinessException(ReturnType.CheckError, "审核已经提交，不能重复操作！");
        } else if (task.getIsDelete() > 0) {
            throw new BusinessException(ReturnType.Error, "此任务已经被删除！");
        }

        //验证当前人是否是责任人
        TaskPerson taskPerson = new TaskPerson();
        taskPerson.setTaskId(taskId);
        taskPerson.setPersonType(1L);
        //获取责任人列表
        List<TaskPerson> dutyPersonList = taskPersonDao.findList(taskPerson);
        if (!isInclude(eiId, dutyPersonList)) {
            throw new BusinessException(ReturnType.Unauthorized, "");
        }
        //获取审核人列表
        taskPerson.setPersonType(2L);
        List<TaskPerson> approverList = taskPersonDao.findList(taskPerson);

        TaskExtend taskExtend = new TaskExtend();
        taskExtend.setTaskId(task.getId());
        taskExtend = taskExtendDao.get(taskExtend);
        Date date = new Date();
        task.setTaskResultTime(date.getTime());
        taskExtend.setTaskResult(taskResult);
        if (0 == task.getTaskNeedAppr()) {
            task.setTaskIsComplete(1L);
        } else {

            JSONArray apprDetailArr = new JSONArray();
            JSONObject dutyJsonObj = new JSONObject();
            dutyJsonObj.put("icon", UserServletContext.getUserInfo().getUserIcon());
            dutyJsonObj.put("name", UserServletContext.getUserInfo().getUserName());
            try {
                DeptVo deptVo = null;//commonsRomeotService.getDeptInfoById(UserServletContext.getUserInfo().getCompanyId(), UserServletContext.getUserInfo().getDeptId()).getData();
                dutyJsonObj.put("dept", deptVo.getPathDeptName());
            } catch (Exception ex) {
                throw new BusinessException(ReturnType.ThirdParty, "", ex);
            }
            String desStr = "";
            JSONObject taskResultJson = JSONObject.parseObject(taskResult);
            if (!taskResultJson.containsKey("des")) {
            } else {
                desStr += taskResultJson.getString("des") == null ? "" : taskResultJson.getString("des");
            }
            dutyJsonObj.put("des", "完成情况：" + desStr);
            dutyJsonObj.put("time", date.getTime());
            dutyJsonObj.put("state", "发起审核");
            apprDetailArr.add(dutyJsonObj);

            task.setTaskApprState(1L);
            task.setUpdateTime(date.getTime());
            taskExtend.setTaskApprDetail(apprDetailArr.toJSONString());
            taskExtend.setUpdateTime(date.getTime());
            //给审核人发通知
            if (approverList != null) {
                //责任人字符串
                String dutyPersonStr = "";
                if (dutyPersonList != null && dutyPersonList.size() > 0) {
                    for (int i = 0; i < dutyPersonList.size(); i++) {
                        dutyPersonStr += dutyPersonList.get(i).getEiEmpName() + ",";
                    }
                    dutyPersonStr = dutyPersonStr.substring(0, dutyPersonStr.length() - 1);
                }
                //审核人字符串
                String approverStr = "";
                if (approverList != null && approverList.size() > 0) {
                    for (int i = 0; i < approverList.size(); i++) {
                        approverStr += approverList.get(i).getEiEmpName() + ",";
                    }
                    approverStr = approverStr.substring(0, approverStr.length() - 1);
                }

                for (int i = 0; i < approverList.size(); i++) {
                    TaskPerson person = approverList.get(i);
                    String subCatalog = "审核通知";
                    String message = "任务名称：";
                    message += task.getTaskName().length() > 14 ? task.getTaskName().substring(0, 14) + "..." : task.getTaskName();
                    message += "\n提交时间：" + DateFormatUtils.format(date, "yyyy-MM-dd HH:mm");
                    message += "\n审核人：" + approverStr;
                    message += "\n责任人：" + dutyPersonStr;
                    sendMessageService.sendMessage(UserServletContext.getUserInfo().getCompanyId(), Long.parseLong(person.getUserNo()), message, subCatalog, task.getId(), 1);
                }
            }
        }
        return taskDao.updateById(task) > 0 && taskExtendDao.updateById(taskExtend) > 0;
    }

    public boolean apprTask(String taskId, Long apprState, String reason) {

        if (StringUtils.isBlank(reason)) {
            reason = "审核意见：";
        } else {
            reason = "审核意见：" + reason;
        }

        Task task = taskDao.getById(taskId);
        if (task == null) {
            throw new BusinessException(ReturnType.DB, "查询的数据不存在");
        } else if (task.getTaskApprState() > 1) {
            throw new BusinessException(ReturnType.CheckError, "审核已经处理，不能重复操作！");
        } else if (task.getIsDelete() > 0) {
            throw new BusinessException(ReturnType.Error, "此任务已经被删除！");
        }

        //验证当前人是否是审核人
        Long eiId = Long.valueOf(UserServletContext.getUserInfo().getEmCode());
        TaskPerson taskPerson = new TaskPerson();
        taskPerson.setTaskId(taskId);
        taskPerson.setPersonType(2L);
        //获审核人列表
        List<TaskPerson> approverList = taskPersonDao.findList(taskPerson);
        if (!isInclude(eiId, approverList)) {
            throw new BusinessException(ReturnType.Unauthorized, "");
        }
        //获取责任人列表
        taskPerson.setPersonType(1L);
        List<TaskPerson> dutyPersonList = taskPersonDao.findList(taskPerson);

        Date date = new Date();
        JSONArray apprDetailArr;
        TaskExtend taskExtend = new TaskExtend();
        taskExtend.setTaskId(taskId);
        taskExtend = taskExtendDao.get(taskExtend);
        //审核流程详情
        String apprDetail = taskExtend.getTaskApprDetail();
        if (StringUtils.isNotBlank(apprDetail)) {
            apprDetailArr = JSONArray.parseArray(apprDetail);
        } else {
            apprDetailArr = new JSONArray();
        }
        //审核节点
        JSONObject apprObject = new JSONObject();
        apprObject.put("icon", UserServletContext.getUserInfo().getUserIcon());
        apprObject.put("name", UserServletContext.getUserInfo().getUserName());
        try {
            DeptVo deptVo = baseDepartmentService.getDeptInfoById(UserServletContext.getUserInfo().getCompanyId(), UserServletContext.getUserInfo().getDeptId());
            apprObject.put("dept", deptVo.getPathDeptName());
        } catch (Exception ex) {
            throw new BusinessException(ReturnType.ThirdParty, "", ex);
        }
        apprObject.put("des", reason);
        apprObject.put("time", date.getTime());
        if (apprState == 1) {
            apprObject.put("state", TaskStat.task_appr_aggre);
            task.setTaskApprState(3L);
            task.setTaskIsComplete(1L);
        } else {
            apprObject.put("state", TaskStat.task_appr_refuse);
            task.setTaskApprState(2L);
        }
        JSONObject completeObject = new JSONObject();
        if (apprDetailArr.size() > 0) {
            completeObject = apprDetailArr.getJSONObject(0);
        } else {
            completeObject = new JSONObject();
        }

        apprDetailArr = new JSONArray();
        apprDetailArr.add(apprObject);
        apprDetailArr.add(completeObject);

        taskExtend.setTaskApprDetail(apprDetailArr.toJSONString());
        task.setTaskApprTime(date.getTime());
        if (apprState == 1) {
            approvalService.taskPlanApproval(taskId, apprState.intValue(), reason);
        } else {
            approvalService.taskPlanApproval(taskId, 2, reason);
        }
        if (taskDao.updateById(task) > 0 && taskExtendDao.updateById(taskExtend) > 0) {
            //给责任人发通知
            if (dutyPersonList != null) {
                //责任人字符串
                String dutyPersonStr = "";
                if (dutyPersonList != null && dutyPersonList.size() > 0) {
                    for (int i = 0; i < dutyPersonList.size(); i++) {
                        dutyPersonStr += dutyPersonList.get(i).getEiEmpName() + ",";
                    }
                    dutyPersonStr = dutyPersonStr.substring(0, dutyPersonStr.length() - 1);
                }
                //审核人字符串
                String approverStr = "";
                if (approverList != null && approverList.size() > 0) {
                    for (int i = 0; i < approverList.size(); i++) {
                        approverStr += approverList.get(i).getEiEmpName() + ",";
                    }
                    approverStr = approverStr.substring(0, approverStr.length() - 1);
                }

                for (int i = 0; i < dutyPersonList.size(); i++) {
                    TaskPerson person = dutyPersonList.get(i);
                    String subCatalog = "";
                    if (apprState == 1) {
                        subCatalog = "审核通过通知";
                    } else {
                        subCatalog = "审核未通过通知";
                    }
                    String message = "任务名称：";
                    message += task.getTaskName().length() > 14 ? task.getTaskName().substring(0, 14) + "..." : task.getTaskName();
                    message += "\n审核时间：" + DateFormatUtils.format(date, "yyyy-MM-dd HH:mm");
                    message += "\n审核人：" + approverStr;
                    message += "\n责任人：" + dutyPersonStr;
                    sendMessageService.sendMessage(UserServletContext.getUserInfo().getCompanyId(), Long.parseLong(person.getUserNo()), message, subCatalog, task.getId(), 0);
                }
            }
            return true;
        }
        return false;
    }

    public boolean taskDelete(String taId) {
        Task task = taskDao.getById(taId);
        if (task == null) {
            throw new BusinessException(ReturnType.DB, "没有数据");
        }
        if (task.getTaskIsComplete() != null && task.getTaskIsComplete() == 1) {
            throw new BusinessException(ReturnType.DB, "已完成的任务不可删除");
        }
        if (task.getTaskApprState() != null && task.getTaskApprState() == 1) {
            throw new BusinessException(ReturnType.DB, "审核中的任务不可删除");
        }
        task.setIsDelete(1L);
        task.setUpdateTime(new Date().getTime());
        return taskDao.updateById(task) > 0;
    }

    /**
     * @param task
     * @param taskStatMap
     */
    private void fillTaskStatMapOld(Task task, Map<String, String> taskStatMap) {
        Date date = new Date(task.getTaskBeginTime());
        String dateStr = DateFormatUtils.format(date, "yyyy-MM-dd");
        String stat = taskStatMap.get(dateStr);
        /**有*/
        if (StringUtils.isNotBlank(stat) && PlanConstants.taskDayState.STAT_W.equalsIgnoreCase(stat)) {
            return;
        }

        if (1 == task.getTaskIsComplete()) {
            taskStatMap.put(dateStr, PlanConstants.taskDayState.STAT_P);
        } else {
            taskStatMap.put(dateStr, PlanConstants.taskDayState.STAT_W);
        }
    }


    /**
     * @param task
     * @param taskStatMap
     * @param list_eiEmpName
     * @param
     * @param
     * @param
     */
    private void fillTaskStatMap(Task task, Map<String, List<TaskTPStat>> taskStatMap, List<String> list_eiEmpName, List<String> list_eiEmpId) {

        Date date = new Date(task.getTaskBeginTime());
        TaskTPStat tpS = new TaskTPStat();

        updateTaskViewStateNew(task, tpS);


        tpS.setList_eiEmpName(list_eiEmpName);
        tpS.setList_eiEmpId(list_eiEmpId);
//        tpS.setStat(spring.getTaskIsComplete()==1?PlanConstants.taskDayState.STAT_P:PlanConstants.taskDayState.STAT_W);
        /**现在是任务项名词，以后要修改为模板名称*/
        tpS.setTaskName(task.getTemplateName());
        tpS.setTp_id(task.getTemplateId());
        tpS.setRecord_id(task.getRecordId());
        tpS.setTaskBeginTime(task.getTaskBeginTime());
        tpS.setTaskEndTime(task.getTaskEndTime());
        tpS.setTaskFlag(task.getTaskFlag());
        tpS.setOptionFlag(task.getOptionFlag());
        tpS.setTaskDay(task.getTaskDay());


        String dateStr = DateFormatUtils.format(date, "yyyy-MM-dd");
        if (!taskStatMap.containsKey(dateStr)) {
            List<TaskTPStat> list = new ArrayList<TaskTPStat>();
            list.add(tpS);
            taskStatMap.put(dateStr, list);
        } else {
            List<TaskTPStat> list = taskStatMap.get(dateStr);
            list.add(tpS);
            taskStatMap.put(dateStr, list);
        }

		/*if (spring.getTaskFlag() ==1) {
            if (spring.getOptionFlag()==1) {
                if (days.size()>0) {
                    for (JhDay day : days) {
                        if (day.getTestId().equals(spring.getId())) {
                            JhDayVo dayVo = new JhDayVo();
                            BeanUtils.copyProperties(day, dayVo);
                            tpS.setTaskFlag(1L);
                            tpS.setOptionFlag(1L);
                            tpS.setDayVo(dayVo);

                        }
                    }
                }

            }else if (spring.getOptionFlag()==2) {
                if (weeks.size()>0) {
                    for (JhWeek week : weeks) {
                        if (week.getTestId().equals(spring.getId())) {
                            JhWeekVo weekVo = new JhWeekVo();
                            BeanUtils.copyProperties(week, weekVo);
                            tpS.setTaskFlag(1L);
                            tpS.setOptionFlag(2L);
                            tpS.setWeekVo(weekVo);
                        }
                    }
                }
            }else if (spring.getOptionFlag()==3) {
                if (months.size()>0) {
                    for (JhMonth month : months) {
                        if (month.getTestId().equals(spring.getId())) {
                            JhMonthVo monthVo = new JhMonthVo();
                            BeanUtils.copyProperties(month, monthVo);
                            tpS.setTaskFlag(1L);
                            tpS.setOptionFlag(3L);
                            tpS.setMonthVo(monthVo);
                        }
                    }
                }
            }

        }else {
            tpS.setTaskFlag(0L);
            tpS.setTaskBeginTime(spring.getTaskBeginTime());
            tpS.setTaskEndTime(spring.getTaskEndTime());
        }*/


    }

    /**
     * 更新显示状态
     *
     * @param
     */
    public void updateTaskViewStateNew(Task task, TaskTPStat tpS) {
        Date date = new Date();
        /** 是不重复任务 0 没有 1 有 */
        if (task.getTaskFlag() == 0) {
            if (task.getTaskIsComplete() != null && task.getTaskIsComplete() == 1) {
                tpS.setStat(TaskStat.task_stat_complete);
                return;
            }
            if (date.getTime() < task.getTaskBeginTime()) {
                tpS.setStat(TaskStat.task_stat_before_begin);
            } else if (date.getTime() < task.getTaskEndTime()) {
                tpS.setStat(TaskStat.task_stat_running);
            } else {
                tpS.setStat(TaskStat.task_stat_after_time);
            }
            return;
        } else {
            /** 查询条数，该计划当天下的所有关联任务项是否执行 */
            QtTaskResult qtTaskResult2 = new QtTaskResult();
            qtTaskResult2.setRecordId(task.getRecordId());
            qtTaskResult2.setTpId(task.getTemplateId());
            /** 日期转换Start */
            Date date_taskBegin = new Date(task.getTaskBeginTime());
            String dateStr = DateFormatUtils.format(date_taskBegin, "yyyy-MM-dd");
            long date_temp = 0L;
            try {
                date_temp = DateUtils.parseDate(dateStr, Locale.CHINA, "yyyy-MM-dd").getTime();
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            qtTaskResult2.setDateTime(date_temp);
            /** 日期转换end */
            List<QtTaskResult> list_qtTaskResult = taskResultDao.findList(qtTaskResult2);
            List<String> list_tiId = new ArrayList<String>();
            for (QtTaskResult taskResult_temp : list_qtTaskResult) {
                list_tiId.add(taskResult_temp.getTiId());
            }
            // 先查出已经关联的检验项id
            QtPlanItem qtPlanItem = new QtPlanItem();
            qtPlanItem.setTpId(task.getTemplateId());
            qtPlanItem.setValid(Constants.VALID_YES);
            List<String> relIds = testPlanDao.findRelIds(qtPlanItem);
            /** 任务项是否全部完成 */
            boolean isComplate = false;
            if (list_tiId.size() > 0) {
                isComplate = list_tiId.containsAll(relIds) && relIds.containsAll(list_tiId);
            }
            if (isComplate) {
                tpS.setStat(TaskStat.task_stat_complete);
                return;
            }
            if (date.getTime() < task.getTaskBeginTime()) {
                tpS.setStat(TaskStat.task_stat_before_begin);
            } else if (date.getTime() < task.getTaskEndTime()) {
                tpS.setStat(TaskStat.task_stat_running);
            } else {
                tpS.setStat(TaskStat.task_stat_after_time);
            }
        }
    }

    /**
     * 更新显示状态
     *
     * @param taskVo
     */
    public static void updateTaskViewState(PlanTaskVo taskVo, boolean isAppror) {
        Date date = new Date();
        if (taskVo.getTaskIsComplete() != null && taskVo.getTaskIsComplete() == 1) {
            taskVo.setViewState(TaskStat.task_stat_complete);
            return;
        }
        if (isAppror) {
            if (taskVo.getTaskIsComplete() != null && taskVo.getTaskIsComplete() == 0) {
                taskVo.setViewState(TaskStat.task_stat_appr);
            }
        } else {
            if (date.getTime() < taskVo.getTaskBeginTime()) {
                taskVo.setViewState(TaskStat.task_stat_before_begin);
            } else if (date.getTime() < taskVo.getTaskEndTime()) {
                taskVo.setViewState(TaskStat.task_stat_running);
            } else {
                taskVo.setViewState(TaskStat.task_stat_after_time);
            }
        }
    }

    class TaskStat {
        public static final String task_stat_before_begin = "未开始";
        public static final String task_stat_running = "执行中";
        public static final String task_stat_after_time = "已逾期";
        public static final String task_stat_complete = "已完成";
        public static final String task_stat_appr = "待审核";

        public static final String task_appr_aggre = "审核通过";
        public static final String task_appr_refuse = "打回";

    }

    public Response<String> taskMove(String sId, String gId) {
        Response<String> response = new Response<String>();
        response.setData("false");
        try {
            Task stask = taskDao.getById(sId);
            Task gtask = taskDao.getById(gId);
            if (stask != null && gtask != null) {
                Long sTaSort = stask.getTaskSort();
                Long gTaSort = gtask.getTaskSort();
                //交换排序号
                stask.setTaskSort(gTaSort);
                gtask.setTaskSort(sTaSort);
                taskDao.updateById(stask);
                taskDao.updateById(gtask);
                response.setData("true");
                return response;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public Response<String> taskMoveTo(String taskId, String periodId, String periodName) {
        Response<String> response = new Response<String>();
        response.setData("false");
        try {
            Task task = taskDao.getById(taskId);
            Task query = new Task();
            query.setPeriodId(periodId);
            List<Task> taskList = taskDao.findList(query);
            if (task != null) {
                task.setPeriodId(periodId);
                task.setPeriodName(periodName);
                //移动后，排序放在第一
                task.setTaskSort(1L);
                taskDao.updateById(task);

                //调整此阶段下的顺序
                for (Task t : taskList) {
                    t.setTaskSort(t.getTaskSort() + 1);
                    taskDao.updateById(t);
                }
                response.setData("true");
                return response;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public List<Map<String, Object>> getAllCategory() {
        List<Map<String, Object>> list = recordTaskService.getAllCategory();
        return list;
    }

    public List<CategoryTypeVo> getCategoryTypeByCategoryId(String categoryId) {
        List<CategoryTypeVo> list = recordTaskService.getCategoryTypeByCategoryId(categoryId);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                CategoryTypeVo vo = list.get(i);
                if (vo.getAsName().equals("无")) {
                    list.remove(vo);
                }
            }
        }
        return list;
    }

    public List<PlanVo> getPlanList(String categoryId) {
        return recordTaskService.getPlanList(categoryId);
    }

    public List<TreeNodeVo> getSpaceData(String id) {
        List<TreeNodeVo> nodeList = new ArrayList<>();
        List<SpaceDto> spaceList = null;//commonsRomeotService.registAndCallBack(sgaiToken).getData();
        if (spaceList != null && spaceList.size() > 0) {
            for (int i = 0; i < spaceList.size(); i++) {
                SpaceDto spaceDto = spaceList.get(i);
                TreeNodeVo vo = new TreeNodeVo();
                if (id == null) {
                    if (spaceDto.getpId() == null) {
                        vo.setId(spaceDto.getId());
                        vo.setParentId(spaceDto.getpId());
                        vo.setName(spaceDto.getName());
                        nodeList.add(vo);
                    }
                } else {
                    if (spaceDto.getpId() != null && spaceDto.getpId().equals(id)) {
                        vo.setId(spaceDto.getId());
                        vo.setParentId(spaceDto.getpId());
                        vo.setName(spaceDto.getName());
                        nodeList.add(vo);
                    }
                }
            }
        }
        return nodeList;
    }

    public List<TreeNodeVo> getMaterielData(String id) {
        List<TreeNodeVo> nodeList = new ArrayList<>();
        if (id == null) {
            MdmMatClass mdmMatClass = new MdmMatClass();
            List<MdmMatClass> dataList = mdmMatClassService.findList(mdmMatClass);
            if (dataList != null && dataList.size() > 0) {
                for (int i = 0; i < dataList.size(); i++) {
                    MdmMatClass mat = dataList.get(i);
                    TreeNodeVo vo = new TreeNodeVo();
                    vo.setId(mat.getMatTypeCode());
                    vo.setParentId(null);
                    vo.setName(mat.getMatTypeName());
                    nodeList.add(vo);
                }
            }
        } else {
            MdmMatInfo mdmMatInfo = new MdmMatInfo();
            mdmMatInfo.setMatTypeCode(id);
            mdmMatInfo.setMatName(null);
            Page<MdmMatInfo> data = mdmMatInfoService.findPage(new Page<MdmMatInfo>(1, Integer.MAX_VALUE), mdmMatInfo);
            List<MdmMatInfo> dataList = data.getList();
            if (dataList != null && dataList.size() > 0) {
                for (int i = 0; i < dataList.size(); i++) {
                    MdmMatInfo mat = dataList.get(i);
                    TreeNodeVo vo = new TreeNodeVo();
                    vo.setId(mat.getId());
                    vo.setParentId(id);
                    vo.setName(mat.getMatName());
                    nodeList.add(vo);
                }
            }
        }
        return nodeList;
    }

    public List<TreeNodeVo> getDevicesData(String id, String className) {

        List<TreeNodeVo> nodeList = new ArrayList<>();
        if (StringUtils.isEmpty(id)) {
            List<MdmDeviceClass> dataList = null;
            try {
                MdmDeviceClass mdmDeviceClass = new MdmDeviceClass();
                String sql = "";
                Map<String, String> sqlMap = new HashMap<String, String>();
                if (com.sgai.common.utils.StringUtils.isBlank(UserServletContext.getUserInfo().getModuCode())) {
                    sql = "NOT EXISTS(select * FROM ALM_DEVICE_LEVEL_RELATION b WHERE a.CLASS_CODE=b.CLASS_CODE AND b.COM_CODE='" + UserServletContext.getUserInfo().getComCode() + "' AND b.MODU_CODE IS NULL)";
                } else {
                    sql = "NOT EXISTS(select * FROM ALM_DEVICE_LEVEL_RELATION b WHERE a.CLASS_CODE=b.CLASS_CODE AND b.COM_CODE='" + UserServletContext.getUserInfo().getComCode() + "' AND b.MODU_CODE=" + UserServletContext.getUserInfo().getModuCode() + ")";
                }
                sqlMap.put("sql", sql);
                mdmDeviceClass.setSqlMap(sqlMap);
                dataList = mdmDeviceClassService.findList(mdmDeviceClass);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (dataList != null && dataList.size() > 0) {
                for (int i = 0; i < dataList.size(); i++) {
                    MdmDeviceClass mat = dataList.get(i);
                    TreeNodeVo vo = new TreeNodeVo();
                    vo.setId(mat.getId());
                    vo.setParentId(null);
                    vo.setName(mat.getClassName());
                    nodeList.add(vo);
                }
            }
        } else {
            MdmDevicesUseInfoVo mdmDevicesUseInfoVo = new MdmDevicesUseInfoVo();
            mdmDevicesUseInfoVo.setClassCode(className);
            List<MdmDevicesUseInfoVo> dataList = mdmDevicesUseInfoService.findPageVoByModel(new Page<MdmDevicesUseInfoVo>(1, Integer.MAX_VALUE), mdmDevicesUseInfoVo).getList();
            if (dataList != null && dataList.size() > 0) {
                for (int i = 0; i < dataList.size(); i++) {
                    MdmDevicesUseInfoVo dev = dataList.get(i);
                    TreeNodeVo vo = new TreeNodeVo();
                    vo.setId(dev.getId());
                    vo.setParentId(id);
                    vo.setName(dev.getDevicesName());
                    nodeList.add(vo);
                }
            }
        }
        return nodeList;
    }

    public List<ExecuteTaskDetailVo> taskStateMonthToday() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(new Date());
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        long time1 = calendar1.getTime().getTime();
        System.out.println("time1:" + time1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date());
        calendar2.set(Calendar.HOUR_OF_DAY, 23);
        calendar2.set(Calendar.MINUTE, 59);
        calendar2.set(Calendar.SECOND, 59);
        long time2 = calendar2.getTime().getTime();
        System.out.println("time2:" + time2);
        List<TaskTPStatVo> list = taskStateMonth1(0L, time1, time2, 0);
        List<ExecuteTaskDetailVo> list_result = new ArrayList<ExecuteTaskDetailVo>();
        if (list.size() > 0) {
            List<TaskTPStat> list_TaskTPStat = list.get(0).getTaskTPList();
            for (TaskTPStat taskTPStat : list_TaskTPStat) {
                String templateId = taskTPStat.getTp_id();
                QtTestPlan qtTestPlan = testPlanDao.getById(templateId);
                String tiId = qtTestPlan.getTiId();//得到任务专业id
                QtProfessionalCategory category = categoryDao.getById(tiId);
                String pcName = category.getPcName();//任务专业名称
                String recordId = taskTPStat.getRecord_id();
                long dateTimeTmp = calendar1.getTime().getTime();
                String status = taskTPStat.getStat();
                ExecuteTaskDetailVo detailVo = taskResultService.ExecuteTaskDetail(recordId, templateId, dateTimeTmp);
                detailVo.setStatus(status);
                detailVo.setPcName(pcName);
                list_result.add(detailVo);
            }
        }
        return list_result;
    }


}

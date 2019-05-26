package com.sgai.property.quality.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.exception.BusinessException;
import com.sgai.property.common.exception.ReturnType;
import com.sgai.property.common.service.BaseDepartmentService;
import com.sgai.property.common.service.BaseEmployeeService;
import com.sgai.property.common.service.BaseTaskStatusUpdateService;
import com.sgai.property.common.util.ApprovalOperatingeEnum;
import com.sgai.property.common.util.QtBeanMapper;
import com.sgai.property.commonService.vo.DeptVo;
import com.sgai.property.quality.constants.Constants;
import com.sgai.property.quality.dao.IQtApprovalRecordsDao;
import com.sgai.property.quality.dao.IQtTaskAppovalDao;
import com.sgai.property.quality.dao.IQtTestTaskDao;
import com.sgai.property.quality.entity.QtApprovalRecords;
import com.sgai.property.quality.entity.QtTaskAppoval;
import com.sgai.property.quality.entity.QtTestTask;
import com.sgai.property.quality.vo.dto.ApprovalDto;
import com.sgai.property.quality.vo.dto.TaskApprovalDto;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class QApprovalServiceImpl {

    private static final Logger logger = LogManager.getLogger(QApprovalServiceImpl.class);

    @Autowired
    IQtApprovalRecordsDao approvalRecordsDao;
    @Autowired
    IQtTaskAppovalDao taskApprovalDao;
    @Autowired
    IQtTestTaskDao testTaskDao;
    @Autowired
    QCommonServicelmpl commonService;
    @Autowired
    SendMessageServiceImpl sendMessageService;
    @Autowired
    private BaseEmployeeService baseEmployeeService;
    @Autowired
    private BaseDepartmentService baseDepartmentService;
    @Value("${sendMessage.taskUrl}")
    private String taskUrl;
    @Value("${wytSendMessage.accessId}")
    private String accessId;
    @Value("${wytSendMessage.accessSecret}")
    private String accessSecret;
    @Autowired
    private BaseTaskStatusUpdateService baseTaskStatusUpdateService;

    private static final String NEW_APPROVAL_TITLE = "审批请求";
    private static final String AGREE_APPROVAL_TITLE = "审批通过";
    private static final String DISAGREE_APPROVAL_TITLE = "审批未通过通知";

    private String NEW_APPROVAL_MODEL = "检验内容：%s\n检验时间：%s\n发起人：%s";
    private String APPROVAL_MODEL = "检验内容：%s\n检验时间：%s\n审核人：%s";


    @Transactional
    public boolean createApproval(TaskApprovalDto taskApprovalDto) {

        if (taskApprovalDto.getTaskId() == null) {
            throw new BusinessException(ReturnType.ParamIllegal, "任务ID不可为空！");
        }

        if (CollectionUtils.isEmpty(taskApprovalDto.getApprovalDtos())) {
            throw new BusinessException(ReturnType.ParamIllegal, "审批人不可为空！");
        }


        Long comId = UserServletContext.getUserInfo().getCompanyId();
        String creatorFeedId = UserServletContext.getUserInfo().getFeeId();
        String creatorName = UserServletContext.getUserInfo().getUserName();
        Long creatorEiid = Long.valueOf(UserServletContext.getUserInfo().getEmCode());
        String icon = UserServletContext.getUserInfo().getUserIcon();
        String fadepartment = UserServletContext.getUserInfo().getComName() == null ? "" : UserServletContext.getUserInfo().getComName();
        DeptVo deptVo = baseDepartmentService.getDeptInfoById(comId, UserServletContext.getUserInfo().getDeptId());
        if (deptVo != null) {
            fadepartment = deptVo.getPathDeptName();
        }
        Long currentTimeMillis = System.currentTimeMillis();

        //插入审批主表
        QtApprovalRecords approvalRecord = new QtApprovalRecords();
        approvalRecord.setTtId(taskApprovalDto.getTaskId());
        approvalRecord.setCreateTime(currentTimeMillis);
        approvalRecord.setUpdateTime(currentTimeMillis);
        approvalRecord.setValid(Constants.VALID_YES);
        approvalRecord.preInsert();
        approvalRecordsDao.insert(approvalRecord);

        //修改任务表
        QtTestTask testTask = new QtTestTask();
        testTask.setId(taskApprovalDto.getTaskId());
        testTask.setArId(approvalRecord.getId());
        testTask.setTtSubmitId(creatorEiid);
        testTask.setTtSubmitName(creatorName);
        testTask.setTtSubmitTime(currentTimeMillis);
        testTask.setTtStatus(QtTestTask.AUDITING);

        updateByStatus(QtTestTask.STARTING, testTask);

        List<QtTaskAppoval> qtTaskAppovals = new ArrayList<>();
        //插入审批明细表
        List<String> approvalEiIds = Lists.newArrayList();
        for (ApprovalDto approvalDto : taskApprovalDto.getApprovalDtos()) {
            QtTaskAppoval qtTaskAppoval = new QtTaskAppoval();
            QtBeanMapper.copy(approvalDto, qtTaskAppoval);

            qtTaskAppoval.setTtId(testTask.getId());
            qtTaskAppoval.setArId(approvalRecord.getId());

            qtTaskAppoval.setTaStatus(ApprovalOperatingeEnum.NO_CHECK.getStatus());
            qtTaskAppoval.setCreateTime(currentTimeMillis);
            qtTaskAppoval.setUpdateTime(currentTimeMillis);
            qtTaskAppoval.setValid(Constants.VALID_YES);
            //查询审核d
            qtTaskAppoval.preInsert();
            qtTaskAppovals.add(qtTaskAppoval);
            approvalEiIds.add(approvalDto.getApprovalId());
        }
        //将发起人也放进去
        QtTaskAppoval createApproval = new QtTaskAppoval();

        createApproval.setFeedId(creatorFeedId);
        createApproval.setApprovalId(creatorEiid);
        createApproval.setApprovalName(creatorName);
        createApproval.setIcon(icon);
        createApproval.setDepartment(fadepartment);


        createApproval.setTtId(testTask.getId());
        createApproval.setArId(approvalRecord.getId());

        createApproval.setTaStatus(ApprovalOperatingeEnum.START.getStatus());
        createApproval.setCreateTime(currentTimeMillis);
        createApproval.setUpdateTime(currentTimeMillis);
        createApproval.setValid(Constants.VALID_YES);
        createApproval.preInsert();
        qtTaskAppovals.add(createApproval);

        taskApprovalDao.batchInsertTaskApproval(qtTaskAppovals);
        //给计划发起状态变化通知,计划审核中的状态是1
        baseTaskStatusUpdateService.updateTaskQualityStatus(taskApprovalDto.getTaskId(), 1L, JSON.toJSONString(approvalEiIds));

        //给审批人发通知
        new Thread(new Runnable() {
            @Override
            public void run() {
                String message = String.format(NEW_APPROVAL_MODEL,
                        taskApprovalDto.getTaskName(),
                        getCurrentTime(),
                        creatorName);
                for (int i = 0; i < qtTaskAppovals.size() - 1; i++) {
                    String code = commonService.getNewCode(comId, qtTaskAppovals.get(i).getApprovalId(), accessId, accessSecret);
                    String toonUrl = taskUrl + "?code=" + code + "&taskId=" + taskApprovalDto.getTaskId();
                    sendMessageService.sendMessage(comId, qtTaskAppovals.get(i).getApprovalId(), message, NEW_APPROVAL_TITLE, taskApprovalDto.getTaskId(), toonUrl);
                    logger.info("发送消息成功！\n" + message);
                }
            }
        }).start();

        return true;
    }

    @Transactional
    public Integer taskApproval(String taskId, int status, String opinion) {
        QtTestTask testTask = testTaskDao.getById(taskId);
        if (testTask == null) {
            throw new BusinessException(ReturnType.ParamIllegal, "找不到该任务！taskId:" + taskId);
        }
        String creatorFeedId = UserServletContext.getUserInfo().getFeeId();
        Long creatorEiId = Long.valueOf(UserServletContext.getUserInfo().getEmCode());
        String creatorName = UserServletContext.getUserInfo().getUserName();
        long comId = UserServletContext.getUserInfo().getCompanyId();
        Long currentTimeMillis = System.currentTimeMillis();

        QtTaskAppoval taskAppoval = new QtTaskAppoval();
        taskAppoval.setTtId(taskId);
        taskAppoval.setArId(testTask.getArId());

        if (status == ApprovalOperatingeEnum.NO_PASS.getStatus()) {
            //更新任务状态为处理中
            testTask.setTtStatus(QtTestTask.STARTING);

            updateByStatus(QtTestTask.AUDITING, testTask);
            logger.info("--------------拒绝-------------------将任务状态设置为处理中！taskId：" + taskId);

            //同时将该任务批次表的该条记录设置为无效
            QtApprovalRecords approvalRecord = new QtApprovalRecords();
            approvalRecord.setId(testTask.getArId());
            approvalRecord.setValid(Constants.VALID_NO);
            approvalRecordsDao.updateById(approvalRecord);

            //给计划发起状态变化通知,对应计划管理是已打回的状态2
            baseTaskStatusUpdateService.updateTaskQualityStatus(taskId, 2L, null);
        }

        if (status == ApprovalOperatingeEnum.PASS.getStatus()) {
            //判断其他的是否都通过了，如果其他都通过了，就代表该任务全部审批通过了，就要更新审批状态和审批人
            // 查询该任务ID、该审批ID对应的明细表中未审批的记录，如果只有一条且ID等于该审批人的feedId，则就代表他是最后一个审批人
            taskAppoval.setTaStatus(ApprovalOperatingeEnum.NO_CHECK.getStatus());
            List<QtTaskAppoval> appovals = taskApprovalDao.findList(taskAppoval);
            if (appovals != null && appovals.size() == 1) {

                testTask.setTtStatus(QtTestTask.FINISH);
                testTask.setTtCheckId(creatorEiId);
                testTask.setTtCheckName(creatorName);
                testTask.setTtCheckTime(System.currentTimeMillis());
                //给计划发起状态变化通知
                baseTaskStatusUpdateService.updateTaskQualityStatus(taskId, QtTestTask.FINISH.longValue(), null);

                updateByStatus(QtTestTask.AUDITING, testTask);
                logger.info("---------------全部通过------------------将任务状态设置为已完成！taskId：" + taskId);
            }

        }

        //更新审批明细表
        taskAppoval.setFeedId(creatorFeedId);
        taskAppoval.setTaStatus(status);
        if (StringUtils.isNotEmpty(opinion)) {
            taskAppoval.setTaOpinion(opinion);
        }
        String department = UserServletContext.getUserInfo().getComName() == null ? "" : UserServletContext.getUserInfo().getComName();
        DeptVo deptVo = baseDepartmentService.getDeptInfoById(comId, UserServletContext.getUserInfo().getDeptId());
        if (deptVo != null) {
            department = deptVo.getPathDeptName();
        }
        taskAppoval.setUpdateTime(currentTimeMillis);
        taskAppoval.setDepartment(department);
        taskApprovalDao.updateApprovalStatus(taskAppoval);

        //给发起人发送消息

        new Thread(new Runnable() {
            @Override
            public void run() {

                String message = String.format(APPROVAL_MODEL,
                        testTask.getTtName(),
                        getCurrentTime(),
                        creatorName);
                String code = commonService.getNewCode(comId, testTask.getTtSubmitId(), accessId, accessSecret);
                String toonUrl = taskUrl + "?code=" + code + "&taskId=" + taskId + "&categoryId=" + testTask.getPcId();
                sendMessageService.sendMessage(comId,
                        testTask.getTtSubmitId(),
                        message,
                        status == ApprovalOperatingeEnum.PASS.getStatus() ? AGREE_APPROVAL_TITLE : DISAGREE_APPROVAL_TITLE,
                        taskId, toonUrl);
                logger.info("发送消息成功！\n" + message);

            }
        }).start();
        return testTask.getTtStatus();
    }


    @Transactional
    public boolean taskPlanApproval(String taskId, int status, String opinion) {
        QtTestTask testTask = testTaskDao.getById(taskId);
        if (testTask == null) {
            throw new BusinessException(ReturnType.ParamIllegal, "找不到该任务！taskId:" + taskId);
        }
        try {
            String creatorFeedId = UserServletContext.getUserInfo().getFeeId();
            Long creatorEiId = Long.valueOf(UserServletContext.getUserInfo().getEmCode());
            String creatorName = UserServletContext.getUserInfo().getUserName();
            long comId = UserServletContext.getUserInfo().getCompanyId();
            Long currentTimeMillis = System.currentTimeMillis();

            QtTaskAppoval taskAppoval = new QtTaskAppoval();
            taskAppoval.setTtId(taskId);
            taskAppoval.setArId(testTask.getArId());

            if (status == ApprovalOperatingeEnum.NO_PASS.getStatus()) {
                //更新任务状态为处理中
                testTask.setTtStatus(QtTestTask.STARTING);
                updateByStatus(QtTestTask.AUDITING, testTask);
                logger.info("--------------拒绝-------------------将任务状态设置为处理中！taskId：" + taskId);

                //同时将该任务批次表的该条记录设置为无效
                QtApprovalRecords approvalRecord = new QtApprovalRecords();
                approvalRecord.setId(testTask.getArId());
                approvalRecord.setValid(Constants.VALID_NO);
                approvalRecordsDao.updateById(approvalRecord);
            }

            if (status == ApprovalOperatingeEnum.PASS.getStatus()) {
                //备注:计划只要一个人审核通过或者拒绝，代表审批已完成
                taskAppoval.setTaStatus(ApprovalOperatingeEnum.NO_CHECK.getStatus());
                testTask.setTtStatus(QtTestTask.FINISH);
                testTask.setTtCheckId(creatorEiId);
                testTask.setTtCheckName(creatorName);
                testTask.setTtCheckTime(System.currentTimeMillis());
                updateByStatus(QtTestTask.AUDITING, testTask);
                logger.info("---------------全部通过------------------将任务状态设置为已完成！taskId：" + taskId);
            }

            //更新审批明细表
            taskAppoval.setFeedId(creatorFeedId);
            taskAppoval.setTaStatus(status);
            if (StringUtils.isNotEmpty(opinion)) {
                taskAppoval.setTaOpinion(opinion);
            }
            String department = UserServletContext.getUserInfo().getComName() == null ? "" : UserServletContext.getUserInfo().getComName();
            DeptVo deptVo = baseDepartmentService.getDeptInfoById(comId, UserServletContext.getUserInfo().getDeptId());
            if (deptVo != null) {
                department = deptVo.getPathDeptName();
            }
            taskAppoval.setUpdateTime(currentTimeMillis);
            taskAppoval.setDepartment(department);
            taskApprovalDao.updateApprovalStatus(taskAppoval);
            return true;
        } catch (BusinessException e) {
            return false;
        }
    }

    /**
     * 获取当前时间 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * 根据任务ID和任务上一状态来更新任务
     *
     * @param oldStatus 任务上一状态
     *                  处理中的任务才可以变为审批中
     *                  审批中的任务拒绝后才可以变成处理中
     *                  审批中的任务全部通过后才可以变成已完成
     * @param testTask
     */
    void updateByStatus(Integer oldStatus, QtTestTask testTask) {
        JSONObject jsonObject = (JSONObject) JSON.toJSON(testTask);
        jsonObject.put("oldStatus", oldStatus);
        Map<String, Object> paramMap = JSONObject.toJavaObject(jsonObject, Map.class);
        int count = testTaskDao.updateByStatus(paramMap);
        if (count == 0) {
            throw new BusinessException(ReturnType.ParamIllegal, "找不到该任务或任务状态已发生变化！ID=" + testTask.getId());
        }
    }

}

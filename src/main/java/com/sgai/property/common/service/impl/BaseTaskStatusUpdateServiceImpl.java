package com.sgai.property.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.service.BaseTaskStatusUpdateService;
import com.sgai.property.common.util.plan.PlanConstants;
import com.sgai.property.quality.entity.plan.Task;
import com.sgai.property.quality.service.plan.RecordTaskServiceImpl;
import com.sgai.property.quality.service.plan.TaskServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ppliu
 * created in 2018/12/25 16:06
 */
@Service
public class BaseTaskStatusUpdateServiceImpl implements BaseTaskStatusUpdateService {
    @Autowired
    private TaskServiceImpl taskService;
    @Autowired
    private RecordTaskServiceImpl recordTaskService;

    @Override
    public Boolean updateTaskQualityStatus(String taskId, Long status, String eiIds) {
        {
            Task task = taskService.getById(taskId);
            if (task == null) {
                //非计划的任务直接返回成功
                return true;
            }
            task.setId(taskId);
            task.setTaskApprState(status);
            if (status == PlanConstants.MT_STATUS_3.longValue()) {
                task.setTaskIsComplete(1L); //已完成
            }
            try {

                Long comId = UserServletContext.getUserInfo().getCompanyId();
                //出错不能影响主体流程
                if (StringUtils.isNotEmpty(eiIds)) {
                    List<String> eiIdList = JSON.parseArray(eiIds, String.class);
                    recordTaskService.addTaskQualityApproval(taskId, comId, eiIdList);
                }
            } catch (Exception e) {
            }
            return taskService.updateById(task);
        }
    }
}

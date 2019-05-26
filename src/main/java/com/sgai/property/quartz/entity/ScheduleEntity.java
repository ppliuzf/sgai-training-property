package com.sgai.property.quartz.entity;
import com.sgai.common.persistence.BoEntity;


  
/**  
* @ClassName: ScheduleEntity  
* @Description: 任务调度的展现类
* @author admin  
* @date 2017年12月19日  
* @Company 首自信--智慧城市创新中心  
*/  
public class ScheduleEntity extends BoEntity<ScheduleEntity>{

    private static final long serialVersionUID = 1L;
    private String jobName; // 任务名

    private String jobGroup; // 任务组

    private String cronExpression; // cron表达式

    private String status; // 状态  :STATE_BLOCKED 4 阻塞;STATE_COMPLETE 2 完成  ;STATE_ERROR 3 错误  ;STATE_NONE -1 不存在  ;STATE_NORMAL 0 正常;STATE_PAUSED 1 暂停

    private String description; // 描述

    private String className; // 执行任务的类(完整路径  包含包名)

    private String methodName;//执行任务的方法名


    public ScheduleEntity() {
        super();
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }


    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

}
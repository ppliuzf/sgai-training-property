package com.sgai.property.quality.vo.plan;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class TaskTemplateVo {

    private String id;

    @ApiModelProperty(value = "计划id")
    private String recordId; //计划id

    @ApiModelProperty(value = "任务结束时间")
    private Long taskEndTime; //任务结束时间

    @ApiModelProperty(value = "任务类型(1:执行;2:检验)")
    private Long taskType; //任务类型(1:执行;2:检验)

    @ApiModelProperty(value = "计划名称")
    private String recordName; //计划名称

    @ApiModelProperty(value = "任务开始时间")
    private Long taskBeginTime; //任务开始时间

    @ApiModelProperty(value = "关联模板id")
    private String templateId;
    @ApiModelProperty(value = "关联模板名称")
    private String templateName;
    @ApiModelProperty(value = "模板状态")
    private String status;//模板状态

    @ApiModelProperty(value = "是否有重复执行 0 没有  1 有")
    private Long taskFlag;//是否有重复执行 0 没有  1 有
    @ApiModelProperty(value = "选择重复标示：1 选择天  2 选择周  3 选择月")
    private Long optionFlag;

    @ApiModelProperty(value = "0：周一，1：周二:2：周三:3：周四:4：周五:5：周六:6：周日")
    private String taskDay; //0：周一，1：周二:2：周三:3：周四:4：周五:5：周六:6：周日

    @ApiModelProperty(value = "按天重复执行")
    private JhDayVo dayVo;//按天重复执行

    @ApiModelProperty(value = "按周重复执行")
    private JhWeekVo weekVo;//按周重复执行

    @ApiModelProperty(value = "按月重复执行")
    private JhMonthVo monthVo;//按月重复执行

    @ApiModelProperty(value = "选择年时，存放年月")
    private String taskYear;
    @ApiModelProperty(value = "开始日")
    private String beginDate;
    @ApiModelProperty(value = "截止日")
    private String endDate;
    @ApiModelProperty(value = "开始时间")
    private String beginTime;
    @ApiModelProperty(value = "截止时间")
    private String endTime;

    @ApiModelProperty(value = "开始月份")
    private String beginMonth;
    @ApiModelProperty(value = "截止月份")
    private String endMonth;

    public String getBeginMonth() {
        return beginMonth;
    }

    public void setBeginMonth(String beginMonth) {
        this.beginMonth = beginMonth;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public String getTaskYear() {
        return taskYear;
    }

    public void setTaskYear(String taskYear) {
        this.taskYear = taskYear;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public Long getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(Long taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public Long getTaskType() {
        return taskType;
    }

    public void setTaskType(Long taskType) {
        this.taskType = taskType;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public Long getTaskBeginTime() {
        return taskBeginTime;
    }

    public void setTaskBeginTime(Long taskBeginTime) {
        this.taskBeginTime = taskBeginTime;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Long getTaskFlag() {
        return taskFlag;
    }

    public void setTaskFlag(Long taskFlag) {
        this.taskFlag = taskFlag;
    }

    public JhDayVo getDayVo() {
        return dayVo;
    }

    public void setDayVo(JhDayVo dayVo) {
        this.dayVo = dayVo;
    }

    public JhWeekVo getWeekVo() {
        return weekVo;
    }

    public void setWeekVo(JhWeekVo weekVo) {
        this.weekVo = weekVo;
    }

    public JhMonthVo getMonthVo() {
        return monthVo;
    }

    public void setMonthVo(JhMonthVo monthVo) {
        this.monthVo = monthVo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getOptionFlag() {
        return optionFlag;
    }

    public void setOptionFlag(Long optionFlag) {
        this.optionFlag = optionFlag;
    }

    public String getTaskDay() {
        return taskDay;
    }

    public void setTaskDay(String taskDay) {
        this.taskDay = taskDay;
    }
}

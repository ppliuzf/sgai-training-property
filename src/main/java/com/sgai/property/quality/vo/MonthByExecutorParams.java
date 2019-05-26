package com.sgai.property.quality.vo;


import io.swagger.annotations.ApiModelProperty;

/**
 * 判读是否有重复执行的入参
 */
public class MonthByExecutorParams {

    @ApiModelProperty(value = "关联模板id")
    private String templateId;
    @ApiModelProperty(value = "计划id")
    private String recordId; //计划id
    @ApiModelProperty(value = "是否有重复执行 0 没有  1 有")
    private Long taskFlag;//是否有重复执行 0 没有  1 有
    @ApiModelProperty(value = "选择重复标示：1 选择天  2 选择周  3 选择月")
    private Long optionFlag;

    @ApiModelProperty(value = "任务开始时间")
    private Long taskBeginTime; //任务开始时间
    @ApiModelProperty(value = "任务结束时间")
    private Long taskEndTime; //任务结束时间

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Long getTaskFlag() {
        return taskFlag;
    }

    public void setTaskFlag(Long taskFlag) {
        this.taskFlag = taskFlag;
    }

    public Long getOptionFlag() {
        return optionFlag;
    }

    public void setOptionFlag(Long optionFlag) {
        this.optionFlag = optionFlag;
    }

    public Long getTaskBeginTime() {
        return taskBeginTime;
    }

    public void setTaskBeginTime(Long taskBeginTime) {
        this.taskBeginTime = taskBeginTime;
    }

    public Long getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(Long taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }
}

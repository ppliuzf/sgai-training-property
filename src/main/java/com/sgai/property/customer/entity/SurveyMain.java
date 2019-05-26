package com.sgai.property.customer.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class SurveyMain extends BoEntity<SurveyMain> {

    @ApiModelProperty(value = "问卷发起人ID")
    private String smCreatorId;
    @ApiModelProperty(value = "问卷发起人Feed_ID")
    private String smCreatorFeedId;
    @ApiModelProperty(value = "问卷发起人名称")
    private String smCreatorName;
    @ApiModelProperty(value = "问卷状态(0,未开始;1进行中,2已结束)")
    private Long smStatus;
    @ApiModelProperty(value = "完成问卷人数")
    private Long smFinishCount;
    @ApiModelProperty(value = "问卷题目总数量")
    private Long smCount;
    @ApiModelProperty(value = "问卷类型")
    private Long smType;
    @ApiModelProperty(value = "参与问卷人数")
    private Long smJoinCount;
    @ApiModelProperty(value = "问卷开始时间")
    private Date smStartTime;
    @ApiModelProperty(value = "问卷名称")
    private String smName;
    @ApiModelProperty(value = "问卷编号")
    private String surveyNo;
    @ApiModelProperty(value = "问卷简介")
    private String smDesc;
    @ApiModelProperty(value = "问卷结束时间")
    private Date smEndTime;
    @ApiModelProperty(value = "是否删除(0,否:1.是)")
    private Long isDelete;

    public String getSmCreatorId() {
        return smCreatorId;
    }

    public void setSmCreatorId(String smCreatorId) {
        this.smCreatorId = smCreatorId;
    }

    public String getSmCreatorFeedId() {
        return smCreatorFeedId;
    }

    public void setSmCreatorFeedId(String smCreatorFeedId) {
        this.smCreatorFeedId = smCreatorFeedId;
    }

    public String getSmCreatorName() {
        return smCreatorName;
    }

    public void setSmCreatorName(String smCreatorName) {
        this.smCreatorName = smCreatorName;
    }

    public Long getSmStatus() {
        return smStatus;
    }

    public void setSmStatus(Long smStatus) {
        this.smStatus = smStatus;
    }

    public Long getSmFinishCount() {
        return smFinishCount;
    }

    public void setSmFinishCount(Long smFinishCount) {
        this.smFinishCount = smFinishCount;
    }

    public Long getSmCount() {
        return smCount;
    }

    public void setSmCount(Long smCount) {
        this.smCount = smCount;
    }

    public Long getSmType() {
        return smType;
    }

    public void setSmType(Long smType) {
        this.smType = smType;
    }

    public Long getSmJoinCount() {
        return smJoinCount;
    }

    public void setSmJoinCount(Long smJoinCount) {
        this.smJoinCount = smJoinCount;
    }

    public Date getSmStartTime() {
        return smStartTime;
    }

    public void setSmStartTime(Date smStartTime) {
        this.smStartTime = smStartTime;
    }

    public String getSmName() {
        return smName;
    }

    public void setSmName(String smName) {
        this.smName = smName;
    }

    public String getSurveyNo() {
        return surveyNo;
    }

    public void setSurveyNo(String surveyNo) {
        this.surveyNo = surveyNo;
    }

    public String getSmDesc() {
        return smDesc;
    }

    public void setSmDesc(String smDesc) {
        this.smDesc = smDesc;
    }

    public Date getSmEndTime() {
        return smEndTime;
    }

    public void setSmEndTime(Date smEndTime) {
        this.smEndTime = smEndTime;
    }

    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Long isDelete) {
        this.isDelete = isDelete;
    }
}
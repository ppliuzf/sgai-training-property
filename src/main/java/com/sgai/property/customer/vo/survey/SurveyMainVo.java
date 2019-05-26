package com.sgai.property.customer.vo.survey;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 问卷主信息
 *
 * @author Hou
 * @create 2018-03-01 14:31
 */
public class SurveyMainVo implements Serializable {

    @ApiModelProperty(value = "问卷ID")
    private String smId;
    @ApiModelProperty(value = "问卷发起人ID")
    private String smCreatorId;
    @ApiModelProperty(value = "问卷发起人名称")
    private String smCreatorName;
    @ApiModelProperty(value = "问卷状态(0,未开始;1进行中,2已结束)")
    private Long smStatus;
    @ApiModelProperty(value = "问卷题目总数量")
    private Long smCount;
    @ApiModelProperty(value = "问卷开始时间")
    private String smStartTime;
    @ApiModelProperty(value = "问卷名称")
    private String smName;
    @ApiModelProperty(value = "问卷编号")
    private String surveyNo;
    @ApiModelProperty(value = "问卷结束时间")
    private String smEndTime;
    @ApiModelProperty(value = "创建日期")
    private String createdDt;
    @ApiModelProperty(value = "是否完成(0,未完成,1,已完成)")
    private Long suIsFinish;

    public Long getSuIsFinish() {
        return suIsFinish;
    }

    public SurveyMainVo setSuIsFinish(Long suIsFinish) {
        this.suIsFinish = suIsFinish;
        return this;
    }

    public String getCreatedDt() {
        return createdDt;
    }

    public SurveyMainVo setCreatedDt(String createdDt) {
        this.createdDt = createdDt;
        return this;
    }

    public String getSmId() {
        return smId;
    }

    public SurveyMainVo setSmId(String smId) {
        this.smId = smId;
        return this;
    }

    public String getSmCreatorId() {
        return smCreatorId;
    }

    public SurveyMainVo setSmCreatorId(String smCreatorId) {
        this.smCreatorId = smCreatorId;
        return this;
    }


    public String getSmCreatorName() {
        return smCreatorName;
    }

    public SurveyMainVo setSmCreatorName(String smCreatorName) {
        this.smCreatorName = smCreatorName;
        return this;
    }

    public Long getSmStatus() {
        return smStatus;
    }

    public SurveyMainVo setSmStatus(Long smStatus) {
        this.smStatus = smStatus;
        return this;
    }

    public Long getSmCount() {
        return smCount;
    }

    public SurveyMainVo setSmCount(Long smCount) {
        this.smCount = smCount;
        return this;
    }

    public String getSmStartTime() {
        return smStartTime;
    }

    public SurveyMainVo setSmStartTime(String smStartTime) {
        this.smStartTime = smStartTime;
        return this;
    }

    public String getSmName() {
        return smName;
    }

    public SurveyMainVo setSmName(String smName) {
        this.smName = smName;
        return this;
    }

    public String getSurveyNo() {
        return surveyNo;
    }

    public SurveyMainVo setSurveyNo(String surveyNo) {
        this.surveyNo = surveyNo;
        return this;
    }

    public String getSmEndTime() {
        return smEndTime;
    }

    public SurveyMainVo setSmEndTime(String smEndTime) {
        this.smEndTime = smEndTime;
        return this;
    }
}

package com.sgai.property.customer.vo.survey;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 问卷主信息入参
 *
 * @author Hou
 * @create 2018-03-01 15:34
 */
public class SurveyMainParam implements Serializable {

    @ApiModelProperty(value = "问卷ID")
    private String smId;
    @ApiModelProperty(value = "问卷题目总数量")
    private Long smCount;
    @ApiModelProperty(value = "问卷名称")
    private String smName;
    @ApiModelProperty(value = "问卷状态(0,未开始;1进行中,2已结束")
    private Long smStatus;
    @ApiModelProperty(value = "问卷开始时间")
    private String smStartTime;
    @ApiModelProperty(value = "问卷结束时间")
    private String smEndTime;
    private String smDesc;

    public Long getSmCount() {
        return smCount;
    }

    public SurveyMainParam setSmCount(Long smCount) {
        this.smCount = smCount;
        return this;
    }

    public String getSmId() {
        return smId;
    }

    public SurveyMainParam setSmId(String smId) {
        this.smId = smId;
        return this;
    }

    public String getSmName() {
        return smName;
    }

    public SurveyMainParam setSmName(String smName) {
        this.smName = smName;
        return this;
    }

    public Long getSmStatus() {
        return smStatus;
    }

    public SurveyMainParam setSmStatus(Long smStatus) {
        this.smStatus = smStatus;
        return this;
    }

    public String getSmStartTime() {
        return smStartTime;
    }

    public SurveyMainParam setSmStartTime(String smStartTime) {
        this.smStartTime = smStartTime;
        return this;
    }

    public String getSmEndTime() {
        return smEndTime;
    }

    public SurveyMainParam setSmEndTime(String smEndTime) {
        this.smEndTime = smEndTime;
        return this;
    }

	public String getSmDesc() {
		return smDesc;
	}

	public void setSmDesc(String smDesc) {
		this.smDesc = smDesc;
	}
}

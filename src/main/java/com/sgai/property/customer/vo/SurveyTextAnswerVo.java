package com.sgai.property.customer.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


/**
 * 问卷问题统计结果
 *
 * @author Hou
 * @create 2018-04-12 11:20
 */
public class SurveyTextAnswerVo implements Serializable{

    @ApiModelProperty(value = "主键ID")
    private String userAnswerId;
    @ApiModelProperty(value = "关联的问卷表ID")
    private String smId;
    @ApiModelProperty(value = "关联的问题表ID")
    private String sqId;
    @ApiModelProperty(value = "答题时间")
    private String saAnswerTime;
    @ApiModelProperty(value = "答题者id")
    private String userId;
    @ApiModelProperty(value = "信息来源:安卓,ios,pc")
    private String source;
    @ApiModelProperty(value = "ip")
    private String ip;
    @ApiModelProperty(value = "所填答案内容")
    private String saContent;
    @ApiModelProperty(value = "答题者姓名")
    private String userName;
    @ApiModelProperty(value = "答题者手机号")
    private String userPhone;
    
    
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getSaContent() {
        return saContent;
    }

    public SurveyTextAnswerVo setSaContent(String saContent) {
        this.saContent = saContent;
        return this;
    }

    public String getUserAnswerId() {
        return userAnswerId;
    }

    public SurveyTextAnswerVo setUserAnswerId(String userAnswerId) {
        this.userAnswerId = userAnswerId;
        return this;
    }

    public String getSmId() {
        return smId;
    }

    public SurveyTextAnswerVo setSmId(String smId) {
        this.smId = smId;
        return this;
    }

    public String getSqId() {
        return sqId;
    }

    public SurveyTextAnswerVo setSqId(String sqId) {
        this.sqId = sqId;
        return this;
    }

    public String getSaAnswerTime() {
        return saAnswerTime;
    }

    public SurveyTextAnswerVo setSaAnswerTime(String saAnswerTime) {
        this.saAnswerTime = saAnswerTime;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public SurveyTextAnswerVo setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getSource() {
        return source;
    }

    public SurveyTextAnswerVo setSource(String source) {
        this.source = source;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public SurveyTextAnswerVo setIp(String ip) {
        this.ip = ip;
        return this;
    }
}

package com.sgai.property.wy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 投诉.
 * Created by ppliu on 2018/02/01.
 */
public class Complain extends BoEntity<Complain> {
    /**外键.*/
    private String sourceKey;
    /**投诉人.*/
    private String complainerName;
    /**投诉标题.*/
    private String complainTitle;
    /**投诉内容.*/
    private String complainContent;
    /**投诉者电话.*/
    private String complainerPhone;
    /**回复.*/
    private String complainReply;
    /**回复人.*/
    private String replyerName;
    /**回复时间.*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date replyTime;
    /**投诉时间.*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date complainTime;
    /**投诉处理状态.*/
    private String complainStatus;//0:未回复 1：已回复
    private String replyType;
    private  String sourceNum;
    private String incidentSource; // 事件来源 1:电话 2:微信 
    private Date startTime;
	private Date endTime;

    public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getIncidentSource() {
		return incidentSource;
	}

	public void setIncidentSource(String incidentSource) {
		this.incidentSource = incidentSource;
	}

	public String getSourceKey() {
        return sourceKey;
    }

    public void setSourceKey(String sourceKey) {
        this.sourceKey = sourceKey;
    }

    public String getComplainerName() {
        return complainerName;
    }

    public void setComplainerName(String complainerName) {
        this.complainerName = complainerName;
    }

    public String getComplainTitle() {
        return complainTitle;
    }

    public void setComplainTitle(String complainTitle) {
        this.complainTitle = complainTitle;
    }

    public String getComplainContent() {
        return complainContent;
    }

    public void setComplainContent(String complainContent) {
        this.complainContent = complainContent;
    }

    public String getComplainerPhone() {
        return complainerPhone;
    }

    public void setComplainerPhone(String complainerPhone) {
        this.complainerPhone = complainerPhone;
    }

    public String getComplainReply() {
        return complainReply;
    }

    public void setComplainReply(String complainReply) {
        this.complainReply = complainReply;
    }

    public String getReplyerName() {
        return replyerName;
    }

    public void setReplyerName(String replyerName) {
        this.replyerName = replyerName;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Date getComplainTime() {
        return complainTime;
    }

    public void setComplainTime(Date complainTime) {
        this.complainTime = complainTime;
    }

    public String getComplainStatus() {
        return complainStatus;
    }

    public void setComplainStatus(String complainStatus) {
        this.complainStatus = complainStatus;
    }

    public String getReplyType() {
        return replyType;
    }

    public void setReplyType(String replyType) {
        this.replyType = replyType;
    }

    public String getSourceNum() {
        return sourceNum;
    }

    public void setSourceNum(String sourceNum) {
        this.sourceNum = sourceNum;
    }
}

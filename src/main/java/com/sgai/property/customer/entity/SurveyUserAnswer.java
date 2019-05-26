package com.sgai.property.customer.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class SurveyUserAnswer extends BoEntity<SurveyUserAnswer> {

    @ApiModelProperty(value = "答案类型(0,单选,1,多选,2,文本)")
    private Long saType;
    @ApiModelProperty(value = "所填答案内容")
    private String saContent;
    @ApiModelProperty(value = "关联的问卷表ID")
    private String smId;
    @ApiModelProperty(value = "答题时间")
    private Date saAnswerTime;
    @ApiModelProperty(value = "答题者姓名")
    private String userName;
    @ApiModelProperty(value = "答题者手机号")
    private String userPhone;
    @ApiModelProperty(value = "关联的问题表ID")
    private String sqId;
    @ApiModelProperty(value = "答题者id")
    private String userId;
    @ApiModelProperty(value = "是否删除(0,否:1.是)")
    private Long isDelete;
    @ApiModelProperty(value = "feedId")
    private String feedId;
    @ApiModelProperty(value = "信息来源:安卓,ios,pc")
    private String source;
    @ApiModelProperty(value = "ip")
    private String ip;
    
    

    public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Long getSaType() {
        return saType;
    }

    public void setSaType(Long saType) {
        this.saType = saType;
    }

    public String getSaContent() {
        return saContent;
    }

    public void setSaContent(String saContent) {
        this.saContent = saContent;
    }

    public String getSmId() {
        return smId;
    }

    public void setSmId(String smId) {
        this.smId = smId;
    }

    public Date getSaAnswerTime() {
        return saAnswerTime;
    }

    public void setSaAnswerTime(Date saAnswerTime) {
        this.saAnswerTime = saAnswerTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSqId() {
        return sqId;
    }

    public void setSqId(String sqId) {
        this.sqId = sqId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Long isDelete) {
        this.isDelete = isDelete;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }
}
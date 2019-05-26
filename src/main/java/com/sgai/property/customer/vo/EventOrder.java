package com.sgai.property.customer.vo;


import com.sgai.property.customer.constants.Constants;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Arrays;

public class EventOrder implements Serializable {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "客户主键")
    private String prId;
    @ApiModelProperty(value = "编号")
    private String eventCode;
    @ApiModelProperty(value = "事件类别")
    private String eventClass;
    @ApiModelProperty(value = "事件类型")
    private String eventType;
    @ApiModelProperty(value = "事件联系人")
    private String contactPerson;
    @ApiModelProperty(value = "联系人电话")
    private String telephone;
    @ApiModelProperty(value = "事件状态")
    private String status;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "开始时间")
    private String beginTime;
    @ApiModelProperty(value = "结束时间")
    private String endTime;
    @ApiModelProperty(value = "投诉地址")
    private String address;
    @ApiModelProperty(value = "投诉内容")
    private String complContent;
    @ApiModelProperty(value = "备注")
    private String desc;
    @ApiModelProperty(value = "附件",required = false)
    private String repairPhoto;

    
    public String getPrId() {
		return prId;
	}

	public void setPrId(String prId) {
		this.prId = prId;
	}

	public String getRepairPhoto() {
        return repairPhoto;
    }

    public void setRepairPhoto(String repairPhoto) {
        this.repairPhoto = repairPhoto;
    }

    public String getComplContent() {
        return complContent;
    }

    public void setComplContent(String complContent) {
        this.complContent = complContent;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEventClass() {
        return eventClass;
    }

    public EventOrder setEventClass(String eventClass) {
        this.eventClass = eventClass;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getStatus() {
        return status;
    }



    public void setStatus(String status) {
        if(null != this.getEventType() && StringUtils.isNotBlank(status)){
            Integer statusInt = Integer.parseInt(status);
            String eventType = this.getEventType();
            if(Constants.EventType.Complaints.equals(eventType)){
                this.status = Constants.getComplaintsStatus(statusInt).getName();
            }else if(Constants.EventType.Security.equals(eventType)){
                this.status = Constants.getSecurityStatus(statusInt).getName();
            }else if(Constants.EventType.Emergency.equals(eventType)){
                this.status = Constants.getEmergencyStatus(statusInt).getName();
            }else {
                this.status = Constants.getRepairStatus(statusInt).getName();
            }
        }
    }

    public String getCreateTime() {
        return createTime;
    }


    public void setCreateTime(String createTime) {
        this.createTime = Arrays.asList(createTime.split("\\.")).get(0);
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


}
package com.sgai.property.customer.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 事件列表
 * @author guanze
 * @version 2017-12-05
 */
public class EventListVo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "编号")
	private String code;		// 编号
	@ApiModelProperty(value = "类别")
	private String category;	// 类别
	@ApiModelProperty(value = "类型")
	private String type;		//类型
	@ApiModelProperty(value = "联系人")
	private String person;		// 投诉人
	@ApiModelProperty(value = "联系电话")
	private String telphone;		// 联系电话
	@ApiModelProperty(value = "投诉地址")
	private String address;		// 投诉地址
	@ApiModelProperty(value = "投诉时间")
	private String time;		// 投诉时间
	@ApiModelProperty(value = "处理时间")
	private String procTime;		// 处理时间
	@ApiModelProperty(value = "事件状态")
	private String states;		// 事件状态：待指派:0,待处理:1,待回访:2,已完成:3,已终止:4
	@ApiModelProperty(value = "可用标识")
	private String enabledFlag;		// 可用标识：1.选项为:'Y':是'N':否默认为'Y'
	@ApiModelProperty(value = "用户代码")
	private String userCode;		// 用户代码
	@ApiModelProperty(value = "节点执行人")
	private String userName;		// 节点执行人
	@ApiModelProperty(value = "处理人")
	private String procPerson;	//处理人
	@ApiModelProperty(value = "事件类别")
	private String codeType;	//处理人
	
	
	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public EventListVo() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getProcTime() {
		return procTime;
	}

	public void setProcTime(String procTime) {
		this.procTime = procTime;
	}

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}

	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProcPerson() {
		return procPerson;
	}

	public void setProcPerson(String procPerson) {
		this.procPerson = procPerson;
	}

	
	
}
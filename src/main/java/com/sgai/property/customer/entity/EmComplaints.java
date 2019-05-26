package com.sgai.property.customer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 投诉事件维护Entity
 * @author guanze
 * @version 2017-12-05
 */
public class EmComplaints extends BoEntity<EmComplaints> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "投诉编号")
	private String complCode;		// 投诉编号
	@ApiModelProperty(value = "投诉类别")
	private String compCategory;	// 投诉类别：设备服务其他
	@ApiModelProperty(value = "投诉类型")
	private String complType;		//投诉类型 TS
	@ApiModelProperty(value = "投诉来源")
	private String complFrom;		// 投诉来源：0:app 1:电话
	@ApiModelProperty(value = "投诉人")
	private String complPerson;		// 投诉人
	@ApiModelProperty(value = "联系电话")
	private String telphone;		// 联系电话
	@ApiModelProperty(value = "投诉地址")
	private String address;		// 投诉地址
	@ApiModelProperty(value = "投诉事件")
	private String complTime;		// 投诉时间
	@ApiModelProperty(value = "投诉内容")
	private String complContent;		// 投诉内容
	@ApiModelProperty(value = "说明")
	private String desc;		// 说明
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

	@ApiModelProperty(value = "投诉时间")
	private Date complTimes;		// 投诉时间


	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getComplTimes() {
		return complTimes;
	}


	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setComplTimes(Date complTimes) {
		this.complTimes = complTimes;
	}

	public EmComplaints() {
		super();
	}

	public EmComplaints(String id){
		super(id);
	}
	
	@Length(min=0, max=60, message="投诉编号长度必须介于 0 和 60 之间")
	public String getComplCode() {
		return complCode;
	}

	public void setComplCode(String complCode) {
		this.complCode = complCode;
	}
	
	@Length(min=0, max=60, message="投诉类别：设备服务其他长度必须介于 0 和 60 之间")
	public String getComplType() {
		return complType;
	}

	public void setComplType(String complType) {
		this.complType = complType;
	}
	
	@Length(min=0, max=60, message="投诉来源：app电话长度必须介于 0 和 60 之间")
	public String getComplFrom() {
		return complFrom;
	}


	public void setComplFrom(String complFrom) {
		this.complFrom = complFrom;
	}
	
	@Length(min=0, max=60, message="投诉人长度必须介于 0 和 60 之间")
	public String getComplPerson() {
		return complPerson;
	}

	public void setComplPerson(String complPerson) {
		this.complPerson = complPerson;
	}
	
	@Length(min=0, max=60, message="联系电话长度必须介于 0 和 60 之间")
	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	
	@Length(min=0, max=128, message="投诉地址长度必须介于 0 和 128 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=60, message="投诉时间长度必须介于 0 和 60 之间")
	public String getComplTime() {
		return complTime;
	}

	public void setComplTime(String complTime) {
		this.complTime = complTime;
	}
	
	@Length(min=0, max=512, message="投诉内容长度必须介于 0 和 512 之间")
	public String getComplContent() {
		return complContent;
	}

	public void setComplContent(String complContent) {
		this.complContent = complContent;
	}
	
	@Length(min=0, max=60, message="说明长度必须介于 0 和 60 之间")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Length(min=0, max=60, message="事件状态：待指派待处理待回访已完成已终止长度必须介于 0 和 60 之间")
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

	public String getProcPerson() {
		return procPerson;
	}

	public void setProcPerson(String procPerson) {
		this.procPerson = procPerson;
	}

	/**
	 * userCode.
	 *
	 * @return  the userCode
	 * @since   JDK 1.8
	 */
	public String getUserCode() {
		return userCode;
	}

	/**
	 * userCode.
	 *
	 * @param   userCode    the userCode to set
	 * @since   JDK 1.8
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/**
	 * userName.
	 *
	 * @return  the userName
	 * @since   JDK 1.8
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * userName.
	 *
	 * @param   userName    the userName to set
	 * @since   JDK 1.8
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * compCategory.
	 *
	 * @return  the compCategory
	 * @since   JDK 1.8
	 */
	public String getCompCategory() {
		return compCategory;
	}

	/**
	 * compCategory.
	 *
	 * @param   compCategory    the compCategory to set
	 * @since   JDK 1.8
	 */
	public void setCompCategory(String compCategory) {
		this.compCategory = compCategory;
	}
	
}
package com.sgai.property.customer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 安保事件维护Entity
 * @author guanze
 * @version 2017-12-05
 */
public class EmSecuRecord extends BoEntity<EmSecuRecord> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "事件编号")
	private String emCode;		// 事件编号
	@ApiModelProperty(value = "事件类型")
	private String emType;     //类型  AB
	@ApiModelProperty(value = "事件类别")
	private String emCategory;//事件类别
	@ApiModelProperty(value = "报案人")
	private String reportPerson;		// 报案人
	@ApiModelProperty(value = "联系电话")
	private String telphone;		// 联系电话
	@ApiModelProperty(value = "报案地址")
	private String address;		// 报案地址
	@ApiModelProperty(value = "报案时间")
	private Date reportDatetime;		// 报案时间
	@ApiModelProperty(value = "报案内容")
	private String reportContent;		// 报案内容：文字图片视频
	@ApiModelProperty(value = "事件状态")
	private String states;		// 事件状态：待核实：0，待指派:1，待处理：2已完成：3，已终止：4
	@ApiModelProperty(value = "是否可用")
	private String enabledFlag;		// 可用标识：1.选项为:'Y':是'N':否默认为'Y'
	@ApiModelProperty(value = "用户代码")
	private String userCode;		// 用户代码
	@ApiModelProperty(value = "节点执行人")
	private String userName;		// 节点执行人
	@ApiModelProperty(value = "处理人")
	private String procPerson;	//处理人
	
	public EmSecuRecord() {
		super();
	}

	public EmSecuRecord(String id){
		super(id);
	}
	
	@Length(min=0, max=60, message="事件编号长度必须介于 0 和 60 之间")
	public String getEmCode() {
		return emCode;
	}

	public void setEmCode(String emCode) {
		this.emCode = emCode;
	}
	
	@Length(min=0, max=60, message="报案人长度必须介于 0 和 60 之间")
	public String getReportPerson() {
		return reportPerson;
	}

	public void setReportPerson(String reportPerson) {
		this.reportPerson = reportPerson;
	}
	
	@Length(min=0, max=60, message="联系电话长度必须介于 0 和 60 之间")
	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	
	@Length(min=0, max=60, message="报案地址长度必须介于 0 和 60 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReportDatetime() {
		return reportDatetime;
	}

	public void setReportDatetime(Date reportDatetime) {
		this.reportDatetime = reportDatetime;
	}
	
	@Length(min=0, max=256, message="报案内容：文字图片视频长度必须介于 0 和 256 之间")
	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	
	@Length(min=0, max=20, message="事件状态：待指派已接单待处理已完成已终止长度必须介于 0 和 20 之间")
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

	public String getEmType() {
		return emType;
	}

	public void setEmType(String emType) {
		this.emType = emType;
	}

	/**
	 * emCategory.
	 *
	 * @return  the emCategory
	 * @since   JDK 1.8
	 */
	public String getEmCategory() {
		return emCategory;
	}

	/**
	 * emCategory.
	 *
	 * @param   emCategory    the emCategory to set
	 * @since   JDK 1.8
	 */
	public void setEmCategory(String emCategory) {
		this.emCategory = emCategory;
	}
	
}
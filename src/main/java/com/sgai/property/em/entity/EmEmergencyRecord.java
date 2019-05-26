package com.sgai.property.em.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

import io.swagger.annotations.ApiModelProperty;

/**
 * 应急事件列表维护Entity
 * @author guanze
 * @version 2017-12-05
 */
public class EmEmergencyRecord extends BoEntity<EmEmergencyRecord> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "机构编码")
	private String comCode;		// 外键，机构编码
	@ApiModelProperty(value = "事件编号")
	private String emCode;		// 事件编号：对应于业务事件的编码，例如：应急事件、保修事件、投诉事件等
	@ApiModelProperty(value = "事件类别")
	private String emType;		// 事件类别
	@ApiModelProperty(value = "事件级别")
	private String emGrade;		// 事件级别
	@ApiModelProperty(value = "报案人")
	private String reportPerson;		// 报案人
	@ApiModelProperty(value = "联系电话")
	private String telphone;		// 联系电话
	@ApiModelProperty(value = "报案地址")
	private String address;		// 报案地址
	@ApiModelProperty(value = "报案时间")
	private String reportDatetime;		// 报案时间
	@ApiModelProperty(value = "报案内容")
	private String reportContent;		// 报案内容：文字图片视频
	@ApiModelProperty(value = "事件状态")
	private String states;		// 事件状态：0:待核实 1:待处理  2:已完成  3:已终止
	@ApiModelProperty(value = "操作人")
	private String operator;		// 操作人
	@ApiModelProperty(value = "可用标识")
	private String enabledFlag;		// 可用标识：1.选项为:'Y':是'N':否默认为'Y'
	@ApiModelProperty(value = "版本号")
	private Integer version;		// 版本号
//	private Date updatedDt;		// 修改时间
//	private String updatedBy;		// 修改人
//	private Date createdDt;		// 创建日期
//	private String createdBy;		// 创建者
	
	public EmEmergencyRecord() {
		super();
	}

	public EmEmergencyRecord(String id){
		super(id);
	}

	@Length(min=0, max=10, message="外键，机构编码长度必须介于 0 和 10 之间")
	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	
	@Length(min=0, max=32, message="事件编号：对应于业务事件的编码，例如：应急事件、保修事件、投诉事件等长度必须介于 0 和 32 之间")
	public String getEmCode() {
		return emCode;
	}

	public void setEmCode(String emCode) {
		this.emCode = emCode;
	}
	
	@Length(min=0, max=60, message="事件类别长度必须介于 0 和 60 之间")
	public String getEmType() {
		return emType;
	}

	public void setEmType(String emType) {
		this.emType = emType;
	}
	
	@Length(min=0, max=60, message="事件级别长度必须介于 0 和 60 之间")
	public String getEmGrade() {
		return emGrade;
	}

	public void setEmGrade(String emGrade) {
		this.emGrade = emGrade;
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
	
	@Length(min=0, max=60, message="报案时间长度必须介于 0 和 60 之间")
	public String getReportDatetime() {
		return reportDatetime;
	}

	public void setReportDatetime(String reportDatetime) {
		this.reportDatetime = reportDatetime;
	}
	
	@Length(min=0, max=512, message="报案内容：文字图片视频长度必须介于 0 和 512 之间")
	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	
	@Length(min=0, max=60, message="事件状态：待核实处理中已完成已终止长度必须介于 0 和 60 之间")
	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}
	
	@Length(min=0, max=60, message="操作人长度必须介于 0 和 60 之间")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}
	
	@Length(min=0, max=32, message="修改人长度必须介于 0 和 32 之间")
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}
	
	@Length(min=0, max=32, message="创建者长度必须介于 0 和 32 之间")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
}
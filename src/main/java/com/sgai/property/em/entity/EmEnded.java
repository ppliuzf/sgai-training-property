package com.sgai.property.em.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

import io.swagger.annotations.ApiModelProperty;


/**
 * 
 * @ClassName: EmEnded  
 * @com.sgai.property.commonService.vo;(事件终止Entity)
 * @author LiuYang  
 * @date 2017年12月5日  
 * @Company 首自信--智慧城市创新中心
*/
public class EmEnded extends BoEntity<EmEnded> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "流程实例id")
	private String instanceId;		// instance_id
	@ApiModelProperty(value = "事件编码")
	private String emCode;		// 对应于业务事件的编码，例如：应急事件、保修事件、投诉事件等
	@ApiModelProperty(value = "事件类型")
	private String emType;		// 维修事件投诉事件报警事件
	@ApiModelProperty(value = "终止原因")
	private String endReason;		// end_reason
	@ApiModelProperty(value = "终止时间")
	private String endTime;		// 终止时间
	@ApiModelProperty(value = "是否完成")
	private String endFlag;		// 未完成：N完成：Y
	@ApiModelProperty(value = "操作人")
	private String operator;		// 操作人
	@ApiModelProperty(value = "是否可用")
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	@ApiModelProperty(value = "版本号")
	private Integer version;		// 版本号
	@ApiModelProperty(value = "修改时间")
	private Date updatedDt;		// 修改时间
	@ApiModelProperty(value = "修改人")
	private String updatedBy;		// 修改人
	@ApiModelProperty(value = "创建日期")
	private Date createdDt;		// 创建日期
	@ApiModelProperty(value = "创建人")
	private String createdBy;		// 创建者
	@ApiModelProperty(value = "流程实例状态")
	private String states;		// 流程状态
	@ApiModelProperty(value = "流程id")
	private String complId;		// 流程id
	public EmEnded() {
		super();
	}

	public EmEnded(String id){
		super(id);
	}

	@Length(min=0, max=64, message="instance_id长度必须介于 0 和 64 之间")
	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	
	@Length(min=0, max=32, message="对应于业务事件的编码，例如：应急事件、保修事件、投诉事件等长度必须介于 0 和 32 之间")
	public String getEmCode() {
		return emCode;
	}

	public void setEmCode(String emCode) {
		this.emCode = emCode;
	}
	
	@Length(min=0, max=32, message="维修事件投诉事件报警事件长度必须介于 0 和 32 之间")
	public String getEmType() {
		return emType;
	}

	public void setEmType(String emType) {
		this.emType = emType;
	}
	
	@Length(min=0, max=60, message="end_reason长度必须介于 0 和 60 之间")
	public String getEndReason() {
		return endReason;
	}

	public void setEndReason(String endReason) {
		this.endReason = endReason;
	}
	
	@Length(min=0, max=60, message="终止时间长度必须介于 0 和 60 之间")
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	@Length(min=0, max=1, message="未完成：N完成：Y长度必须介于 0 和 1 之间")
	public String getEndFlag() {
		return endFlag;
	}

	public void setEndFlag(String endFlag) {
		this.endFlag = endFlag;
	}
	
	@Length(min=0, max=60, message="操作人长度必须介于 0 和 60 之间")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	@Length(min=0, max=60, message="选项为:'Y':是'N':否默认为'Y'长度必须介于 1 和 1 之间")
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

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}

	public String getComplId() {
		return complId;
	}

	public void setComplId(String complId) {
		this.complId = complId;
	}
	
	
	
}
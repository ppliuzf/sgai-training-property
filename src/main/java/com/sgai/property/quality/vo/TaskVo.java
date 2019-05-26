package com.sgai.property.quality.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 任务列表
 * @author wuzhihui
 *
 */
public class TaskVo {
	@ApiModelProperty(value = "任务id")
	private String id;
	@ApiModelProperty(value = "任务名称")
	private String name;
	@ApiModelProperty(value = "任务图片url")
	private String icon;
	@ApiModelProperty(value = "专业范畴id")
	private Long pcId;
	@ApiModelProperty(value = "任务状态")
	private Integer status;
	@ApiModelProperty(value = "任务状态描述")
	private String statusDesc;
	
	@ApiModelProperty(value = "开始时间")
	private Long startTime;
	@ApiModelProperty(value = "计划完成时间")
	private Long planCompleteTime;
	@ApiModelProperty(value = "提交人")
	private String submiterName;
	@ApiModelProperty(value = "提交时间(检查时间)")
	private Long submitTime;
	@ApiModelProperty(value = "审核人")
	private String checkerName;
	@ApiModelProperty(value = "审核时间")
	private Long checkTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getPlanCompleteTime() {
		return planCompleteTime;
	}
	public void setPlanCompleteTime(Long planCompleteTime) {
		this.planCompleteTime = planCompleteTime;
	}
	public String getSubmiterName() {
		return submiterName;
	}
	public void setSubmiterName(String submiterName) {
		this.submiterName = submiterName;
	}
	public Long getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Long submitTime) {
		this.submitTime = submitTime;
	}
	public String getCheckerName() {
		return checkerName;
	}
	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}
	public Long getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Long checkTime) {
		this.checkTime = checkTime;
	}
	public Long getPcId() {
		return pcId;
	}
	public void setPcId(Long pcId) {
		this.pcId = pcId;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
}

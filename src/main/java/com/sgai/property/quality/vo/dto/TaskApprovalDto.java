package com.sgai.property.quality.vo.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class TaskApprovalDto {

	@ApiModelProperty(value = "任务ID")
	private String taskId; // 任务ID
	
	@ApiModelProperty(value = "任务名称")
	private String taskName; // 任务名称

	@ApiModelProperty(value = "审批人列表")
	private List<ApprovalDto> approvalDtos; // 审批人列表

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public List<ApprovalDto> getApprovalDtos() {
		return approvalDtos;
	}

	public void setApprovalDtos(List<ApprovalDto> approvalDtos) {
		this.approvalDtos = approvalDtos;
	}

}

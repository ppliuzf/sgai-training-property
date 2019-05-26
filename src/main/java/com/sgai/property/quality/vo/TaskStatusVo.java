package com.sgai.property.quality.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 任务状态
 * @author wuzhihui
 *
 */
public class TaskStatusVo {
	
	@ApiModelProperty(value = "任务项id")
	private String taskId;
	@ApiModelProperty(value = "任务项名称")
	private String taskName;
	@ApiModelProperty(value = "创建时间")
	private Long createTime;
	@ApiModelProperty(value = "状态")
	private Integer status;
	@ApiModelProperty(value = "状态文字描述")
	private String desc;
	public Long getCreateTime() {
		return createTime;
	}
	public String getDesc() {
		return desc;
	}
	public Integer getStatus() {
		return status;
	}
	public String getTaskId() {
		return taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	

}

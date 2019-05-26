package com.sgai.property.quality.vo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class TaskStatusUncheckedVo {
	@ApiModelProperty(value = "未检查任务项id")
	private String uncheckedTaskId;
	public String getUncheckedTaskId() {
		return uncheckedTaskId;
	}
	public void setUncheckedTaskId(String uncheckedTaskId) {
		this.uncheckedTaskId = uncheckedTaskId;
	}
	public List<TaskStatusVo> getLists() {
		return lists;
	}
	public void setLists(List<TaskStatusVo> lists) {
		this.lists = lists;
	}
	@ApiModelProperty(value = "任务项状态列表")
	private List<TaskStatusVo> lists;	
}

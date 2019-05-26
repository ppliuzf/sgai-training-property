package com.sgai.property.quality.vo.plan;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class DayTaskVo {
    private String recordId;
    @ApiModelProperty(value = "计划名称")
    private String recordName ;
    @ApiModelProperty(value = "任务列表")
    private List<PlanTaskVo> taskList;

    public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getRecordName() {
		return recordName;
	}

	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}

	public List<PlanTaskVo> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<PlanTaskVo> taskList) {
        this.taskList = taskList;
    }
}

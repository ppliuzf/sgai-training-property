package com.sgai.property.quality.vo.plan;

import io.swagger.annotations.ApiModelProperty;


public class RecordParam {
     
    @ApiModelProperty(value = "开始时间")
    private Long startTime; //创建时间
    @ApiModelProperty(value = "结束时间")
    private Long endTime; //创建时间
    @ApiModelProperty(value = "计划类型id")
    private String typeId; //计划类型id
    @ApiModelProperty(value = "计划名称")
    private String recordName; //计划名称
	@ApiModelProperty(value = "任务类型标示 0：检验类 1：安保类")
	private Integer type;

	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getRecordName() {
		return recordName;
	}
	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
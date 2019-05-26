package com.sgai.property.quality.vo.dto;

import io.swagger.annotations.ApiModelProperty;

public class CategoryDto {

	@ApiModelProperty(value = "专业范畴ID")
	private String pcId; // 专业范畴ID
	@ApiModelProperty(value = "专业范畴名称")
	private String pcName; // 专业范畴名称
	@ApiModelProperty(value = "描述")
	private String pcDesc; // 描述
	@ApiModelProperty(value = "显示图标")
	private String pcIcon; // 显示图标
	@ApiModelProperty(value = "关联类型")
	private String asType; // 关联类型
	@ApiModelProperty(value = "任务类型")
	private String taskType;
	@ApiModelProperty(value = "任务类型标示 0：检验类 1：安保类")
	private Integer type;

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	public String getPcName() {
		return pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

	public String getPcDesc() {
		return pcDesc;
	}

	public void setPcDesc(String pcDesc) {
		this.pcDesc = pcDesc;
	}

	public String getPcIcon() {
		return pcIcon;
	}

	public void setPcIcon(String pcIcon) {
		this.pcIcon = pcIcon;
	}

	public String getAsType() {
		return asType;
	}

	public void setAsType(String asType) {
		this.asType = asType;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
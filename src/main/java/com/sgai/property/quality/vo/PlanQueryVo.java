package com.sgai.property.quality.vo;

import io.swagger.annotations.ApiModelProperty;

public class PlanQueryVo {
	@ApiModelProperty(value = "方案id")
	private String id;
	@ApiModelProperty(value = "方案名称")
	private String name;
	@ApiModelProperty(value = "专业范畴id")
	private String pcId;
	@ApiModelProperty(value = "图片url")
	private String icon;
	@ApiModelProperty(value = "说明")
	private String description;
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
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}

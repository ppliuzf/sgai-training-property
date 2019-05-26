package com.sgai.property.quality.vo;

import io.swagger.annotations.ApiModelProperty;

public class InspectionProfile {
	@ApiModelProperty(value = "概况项描述")
	private String name;
	@ApiModelProperty(value = "数字")
	private Integer count ;
	@ApiModelProperty(value = "状态(0:正常,1:异常用于前端字体标红)")
	private Integer status;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}

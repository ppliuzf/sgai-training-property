package com.sgai.property.quality.vo;

import io.swagger.annotations.ApiModelProperty;

public class OptionVo {
	@ApiModelProperty(value = "id")
	private String id;
	@ApiModelProperty(value = "名称")
	private String name;
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
	
	
}

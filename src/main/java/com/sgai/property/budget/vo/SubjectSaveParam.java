package com.sgai.property.budget.vo;

import io.swagger.annotations.ApiModelProperty;

public class SubjectSaveParam {

	@ApiModelProperty(value = "父科目id,如果没有父节点(一级科目)传\"-1\"")
    private String parentId; //父科目id
    @ApiModelProperty(value = "科目短名称")
    private String shortName; //科目短名称
    @ApiModelProperty(value = "科目描述")
    private String description; //科目描述
	private String subName;
    
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}
}

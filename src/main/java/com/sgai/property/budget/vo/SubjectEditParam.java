package com.sgai.property.budget.vo;

import io.swagger.annotations.ApiModelProperty;

public class SubjectEditParam {

	@ApiModelProperty(value = "科目id")
	private String id;
    @ApiModelProperty(value = "科目短名称")
    private String shortName; //科目短名称
    @ApiModelProperty(value = "科目描述")
    private String description; //科目描述
    private String parentId;
    private String subName;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
    
}

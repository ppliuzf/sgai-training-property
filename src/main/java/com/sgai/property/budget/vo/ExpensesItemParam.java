package com.sgai.property.budget.vo;

import io.swagger.annotations.ApiModelProperty;

public class ExpensesItemParam{
     
	@ApiModelProperty(value = "费项id")
    private String id; //费项id
    @ApiModelProperty(value = "费项名称")
    private String itemName; //费项名称
    @ApiModelProperty(value = "描述")
    private String description; //描述
    
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
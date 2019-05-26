package com.sgai.property.budget.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 *@author 严浩淼
 *@date 2018年1月18日--下午3:20:27
 */
public class ExpensesItemVo {
	
    @ApiModelProperty(value = "费项id")
    private String itemId; //费项id
    @ApiModelProperty(value = "费项名称")
    private String itemName; //费项名称
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
    
    
}

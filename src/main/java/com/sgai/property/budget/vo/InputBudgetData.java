package com.sgai.property.budget.vo;

import io.swagger.annotations.ApiModelProperty;

public class InputBudgetData {

	@ApiModelProperty(value = "记录id（预算表格中每一行数据的id）")
	private String rsiId;
	@ApiModelProperty(value = "预算金额")
	private String budget;
	public String getRsiId() {
		return rsiId;
	}
	public void setRsiId(String rsiId) {
		this.rsiId = rsiId;
	}
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	
}

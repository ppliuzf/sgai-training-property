package com.sgai.property.budget.vo;

import io.swagger.annotations.ApiModelProperty;

public class InputExpendData {

	@ApiModelProperty(value = "记录id（预算表格中每一行数据的id）")
	private String rsiId;
	@ApiModelProperty(value = "预算金额")
	private String budget;
	@ApiModelProperty(value = "预算结余")
	private String surplus;
	@ApiModelProperty(value = "支出（将这行记录每个月的支出用逗号隔开拼成一个字符串）")
	private String expend;
	
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
	public String getSurplus() {
		return surplus;
	}
	public void setSurplus(String surplus) {
		this.surplus = surplus;
	}
	public String getExpend() {
		return expend;
	}
	public void setExpend(String expend) {
		this.expend = expend;
	}
	
	
}

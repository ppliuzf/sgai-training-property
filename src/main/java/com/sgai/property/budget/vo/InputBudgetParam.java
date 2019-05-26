package com.sgai.property.budget.vo;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
public class InputBudgetParam {
	
	@ApiModelProperty(value = "计划id")
	private String recordId;
	@ApiModelProperty(value = "每条预算数据")
	private List<InputBudgetData> entryList;
	
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public List<InputBudgetData> getEntryList() {
		return entryList;
	}
	public void setEntryList(List<InputBudgetData> entryList) {
		this.entryList = entryList;
	}
	
}
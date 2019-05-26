package com.sgai.property.budget.vo;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
public class InputExpendParam {
	
	@ApiModelProperty(value = "计划id")
	private String recordId;
	@ApiModelProperty(value = "每条预算支出数据")
	private List<InputExpendData> entryList;
	
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public List<InputExpendData> getEntryList() {
		return entryList;
	}
	public void setEntryList(List<InputExpendData> entryList) {
		this.entryList = entryList;
	}
	
	
}
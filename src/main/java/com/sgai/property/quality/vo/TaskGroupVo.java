package com.sgai.property.quality.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskGroupVo {
	@ApiModelProperty(value = "组id")
	private String groupId;
	@ApiModelProperty(value = "组名称")
	private String groupName;
	@ApiModelProperty(value = "组中检查项总数")
	private Integer itemTotal;
	@ApiModelProperty(value = "组中已检查项(用于计算组中的进度)")
	private Integer checkedCount;
	@ApiModelProperty(value = "组中检查项")
	private List<TaskItemVo> list;
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Integer getItemTotal() {
		return itemTotal;
	}
	public void setItemTotal(Integer itemTotal) {
		this.itemTotal = itemTotal;
	}
	public Integer getCheckedCount() {
		return checkedCount;
	}
	public void setCheckedCount(Integer checkedCount) {
		this.checkedCount = checkedCount;
	}
	public List<TaskItemVo> getList() {
		return list;
	}
	public void setList(List<TaskItemVo> list) {
		this.list = list;
	}
	
}

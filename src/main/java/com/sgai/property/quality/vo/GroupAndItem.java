package com.sgai.property.quality.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 选题分组
 * @author wuzhihui
 *
 */
public class GroupAndItem {
	@ApiModelProperty(value = "组id")
	private String groupId;
	@ApiModelProperty(value = "组名称")
	private String groupName;
	@ApiModelProperty(value = "组中检查项总数")
	private Integer itemTotal;
	@ApiModelProperty(value = "组中检查项")
	private List<Item> list;
	public class Item{
		@ApiModelProperty(value = "检查项id")
		private String id;
		@ApiModelProperty(value = "检查项名称")
		private String name;
		@ApiModelProperty(value = "在组中第几项")
		private Integer sort;
		@ApiModelProperty(value = "检查项状态")
		private Integer status;
		@ApiModelProperty(value = "检查项状态描述")
		private String statusDesc;
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
		public Integer getSort() {
			return sort;
		}
		public void setSort(Integer sort) {
			this.sort = sort;
		}
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		public String getStatusDesc() {
			return statusDesc;
		}
		public void setStatusDesc(String statusDesc) {
			this.statusDesc = statusDesc;
		}
		public String getGroupId() {
			return groupId;
		}
		
	}
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
	public List<Item> getList() {
		return list;
	}
	public void setList(List<Item> list) {
		this.list = list;
	}
	
}

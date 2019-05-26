package com.sgai.property.quality.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 检验项分组
 * @author wuzhihui
 *
 */
public class GroupingVo {
	@ApiModelProperty(value = "组id")
	private String id;
	@ApiModelProperty(value = "组名称")
	private String name;
	@ApiModelProperty(value = "关联的检查项")
	private List<GroupItem> list;
	public 	static class GroupItem{
		@ApiModelProperty(value = "检查项id")
		private String id;
		@ApiModelProperty(value = "检查项名称")
		private String name;
		@ApiModelProperty(value = "标准")
		private String standard;
		@ApiModelProperty(value = "答案类型")
		private String answerTypeDesc;
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
		public String getStandard() {
			return standard;
		}
		public void setStandard(String standard) {
			this.standard = standard;
		}
		public String getAnswerTypeDesc() {
			return answerTypeDesc;
		}
		public void setAnswerTypeDesc(String answerTypeDesc) {
			this.answerTypeDesc = answerTypeDesc;
		}
		
	}
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
	public List<GroupItem> getList() {
		return list;
	}
	public void setList(List<GroupItem> list) {
		this.list = list;
	}
	
}

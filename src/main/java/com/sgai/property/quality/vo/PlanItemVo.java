package com.sgai.property.quality.vo;

import com.sgai.property.quality.entity.QtPlanItem;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 方案明细
 * @author wuzhihui
 *
 */
public class PlanItemVo {
	@ApiModelProperty(value = "方案id")
	private String id;
	@ApiModelProperty(value = "方案名称")
	private String name;
	@ApiModelProperty(value = "专业范畴id")
	private String pcId;
	@ApiModelProperty(value = "专业范畴名称")
	private String pcName;
	@ApiModelProperty(value = "方案说明")
	private String description;
//	@ApiModelProperty(value = "方案说明")
//	private List<PlanItem> list;

	private List<QtPlanItem> list;
	
	public static class PlanItem{
		@ApiModelProperty(value = "检查项id")
		private String id;
		@ApiModelProperty(value = "检查项原始id")
		private String htiId;
		@ApiModelProperty(value = "检查项名称")
		private String name;
		@ApiModelProperty(value = "组id")
		private String groupId;
		@ApiModelProperty(value = "组名称")
		private String groupName;
		@ApiModelProperty(value = "标准")
		private String standard;
		@ApiModelProperty(value = "答案类型")
		private String answerTypeDesc;
		@ApiModelProperty(value = "创建人")
		private String createName;
		@ApiModelProperty(value = "创建时间")
		private Long createTime;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
		public String getHtiId() {
			return htiId;
		}
		public void setHtiId(String htiId) {
			this.htiId = htiId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
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
		public String getCreateName() {
			return createName;
		}
		public void setCreateName(String createName) {
			this.createName = createName;
		}
		public Long getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Long createTime) {
			this.createTime = createTime;
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

	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	public String getPcName() {
		return pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*public List<PlanItem> getList() {
		return list;
	}

	public void setList(List<PlanItem> list) {
		this.list = list;
	}*/

	public List<QtPlanItem> getList() {
		return list;
	}

	public void setList(List<QtPlanItem> list) {
		this.list = list;
	}
}

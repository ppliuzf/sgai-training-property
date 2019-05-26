package com.sgai.property.quality.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class QtPlanItem  extends BoEntity<QtPlanItem> {
	@ApiModelProperty(value = "任务项名称")
	private String piName; // 检验项名称
	@ApiModelProperty(value = "专业范畴id")
	private String tpId; // 所属方案id
	@ApiModelProperty(value = "专业范畴id")
	private String pcId; // 专业范畴id
	@ApiModelProperty(value = "专业范畴名称")
	private String pcName; // 专业范畴名称
	@ApiModelProperty(value = "原始任务项id")
	private String tiId; //原始检验项id
	@ApiModelProperty(value = "专业范畴名称")
	private String piStandard; // 检查标准
	@ApiModelProperty(value = "待选项(多个选项拼接)")
	private String piOptions; // 待选项(多个选项拼接)
	@ApiModelProperty(value = "合格选项")
	private String piStandardOption; // 合格选项
	@ApiModelProperty(value = "上限")
	private Long piMax; // 上限
	@ApiModelProperty(value = "下限")
	private Long piMin; // 下限
	@ApiModelProperty(value = "单位")
	private String piUnit; // 单位
	@ApiModelProperty(value = "是否允许手工录入(0是，1否)  答题类型(0:数字型,1:选择型)")
	private Integer piIsInput; // 是否允许手工录入(0是，1否)
	@ApiModelProperty(value = "完成时限")
	private Integer piLimitTime; // 完成时限
	@ApiModelProperty(value = "完成时限单位(1:天,2:小时,3:分钟)")
	private Integer piFinshUnit; // 完成时限单位(1:天,2:小时,3:分钟)
	@ApiModelProperty(value = "缺陷责任岗位id")
	private String postId; // 缺陷责任岗位id
	@ApiModelProperty(value = "缺陷责任岗位名称")
	private String postName; // 缺陷责任岗位名称
	@ApiModelProperty(value = "整改要求")
	private String piRectificationRequirements; // 整改要求
	@ApiModelProperty(value = "创建人id")
	private String createEiId; // 创建人id
	@ApiModelProperty(value = "创建人名称")
	private String createName; // 创建人名称
	@ApiModelProperty(value = "所在分组id")
	private String groupId; // 所在分组id
	@ApiModelProperty(value = "在组中的排序")
	private Integer groupSort; // 在组中的排序
	@ApiModelProperty(value = "在方案中的排序")
	private Integer planSort; // 在方案中的排序
	@ApiModelProperty(value = "创建时间")
	private Long createTime; // 创建时间
	@ApiModelProperty(value = "更新时间")
	private Long updateTime; // 更新时间
	@ApiModelProperty(value = "是否删除(0未删除，1已删除)")
	private Integer valid; // 是否删除(0未删除，1已删除)

	public String getPiName() {
		return piName;
	}

	public void setPiName(String piName) {
		this.piName = piName;
	}
	public String getTpId() {
		return tpId;
	}

	public void setTpId(String tpId) {
		this.tpId = tpId;
	}

	public Integer getPlanSort() {
		return planSort;
	}

	public void setPlanSort(Integer planSort) {
		this.planSort = planSort;
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

	public String getPiStandard() {
		return piStandard;
	}

	public void setPiStandard(String piStandard) {
		this.piStandard = piStandard;
	}

	public String getPiOptions() {
		return piOptions;
	}

	public void setPiOptions(String piOptions) {
		this.piOptions = piOptions;
	}

	public String getPiStandardOption() {
		return piStandardOption;
	}

	public void setPiStandardOption(String piStandardOption) {
		this.piStandardOption = piStandardOption;
	}

	public Long getPiMax() {
		return piMax;
	}

	public void setPiMax(Long piMax) {
		this.piMax = piMax;
	}

	public Long getPiMin() {
		return piMin;
	}

	public void setPiMin(Long piMin) {
		this.piMin = piMin;
	}

	public String getPiUnit() {
		return piUnit;
	}

	public void setPiUnit(String piUnit) {
		this.piUnit = piUnit;
	}

	public Integer getPiIsInput() {
		return piIsInput;
	}

	public void setPiIsInput(Integer piIsInput) {
		this.piIsInput = piIsInput;
	}

	public Integer getPiLimitTime() {
		return piLimitTime;
	}

	public void setPiLimitTime(Integer piLimitTime) {
		this.piLimitTime = piLimitTime;
	}

	public Integer getPiFinshUnit() {
		return piFinshUnit;
	}

	public void setPiFinshUnit(Integer piFinshUnit) {
		this.piFinshUnit = piFinshUnit;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getPiRectificationRequirements() {
		return piRectificationRequirements;
	}

	public void setPiRectificationRequirements(String piRectificationRequirements) {
		this.piRectificationRequirements = piRectificationRequirements;
	}

	public String getCreateEiId() {
		return createEiId;
	}

	public void setCreateEiId(String createEiId) {
		this.createEiId = createEiId;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Integer getGroupSort() {
		return groupSort;
	}

	public void setGroupSort(Integer groupSort) {
		this.groupSort = groupSort;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getTiId() {
		return tiId;
	}

	public void setTiId(String tiId) {
		this.tiId = tiId;
	}

}
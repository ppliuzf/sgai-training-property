package com.sgai.property.quality.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class QtTestPlan  extends BoEntity<QtTestPlan> {
	// 未关联检验项
	public static final Integer NO_REL_ITEM = 0;
	// 已关联检验项
	public static final Integer HAS_REL_ITEM = 1;
	@ApiModelProperty(value = "方案名称")
	private String tpName; // 方案名称
	@ApiModelProperty(value = "所属专业范畴id")
	private String tiId; // 所属专业范畴id
	@ApiModelProperty(value = "专业范畴名称")
	private String tiName; // 专业范畴名称
	@ApiModelProperty(value = "方案说明")
	private String tiDescription; // 方案说明
	@ApiModelProperty(value = "创建人id")
	private String createEiId; // 创建人id
	@ApiModelProperty(value = "创建人名称")
	private String createEiName; // 创建人名称
	@ApiModelProperty(value = "组织ID")
	private Long comId; // 组织ID
	@ApiModelProperty(value = "是否有关联检验项(0未关联,1已关联)")
	private Integer hasRelItem; // 是否有关联检验项(0未关联,1已关联)
	@ApiModelProperty(value = "创建时间")
	private Long createTime; // 创建时间
	@ApiModelProperty(value = "更新时间")
	private Long updateTime; // 更新时间
	@ApiModelProperty(value = "是否删除(0未删除,1已删除)")
	private Integer valid; // 是否删除(0未删除,1已删除)
	private String icon;

	@ApiModelProperty(value = "任务类型标示 0：检验类 1：安保类")
	private Integer typeFlag;

	public String getTpName() {
		return tpName;
	}

	public void setTpName(String tpName) {
		this.tpName = tpName;
	}

	public String getTiId() {
		return tiId;
	}

	public void setTiId(String tiId) {
		this.tiId = tiId;
	}

	public String getTiName() {
		return tiName;
	}

	public void setTiName(String tiName) {
		this.tiName = tiName;
	}

	public String getTiDescription() {
		return tiDescription;
	}

	public void setTiDescription(String tiDescription) {
		this.tiDescription = tiDescription;
	}

	public String getCreateEiId() {
		return createEiId;
	}

	public void setCreateEiId(String createEiId) {
		this.createEiId = createEiId;
	}

	public String getCreateEiName() {
		return createEiName;
	}

	public void setCreateEiName(String createEiName) {
		this.createEiName = createEiName;
	}

	public Long getComId() {
		return comId;
	}

	public void setComId(Long comId) {
		this.comId = comId;
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

	public Integer getHasRelItem() {
		return hasRelItem;
	}

	public void setHasRelItem(Integer hasRelItem) {
		this.hasRelItem = hasRelItem;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public static Integer getNoRelItem() {
		return NO_REL_ITEM;
	}

	public Integer getTypeFlag() {
		return typeFlag;
	}

	public void setTypeFlag(Integer typeFlag) {
		this.typeFlag = typeFlag;
	}
}
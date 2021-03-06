package com.sgai.property.quality.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class QtProfessionalCategory  extends BoEntity<QtProfessionalCategory> {
	@ApiModelProperty(value = "任务专业名称")
	private String pcName; // 专业范畴名称
	@ApiModelProperty(value = "描述")
	private String pcDesc; // 描述
	@ApiModelProperty(value = "创建人")
	private String createEiId; // 创建人
	@ApiModelProperty(value = "创建人姓名")
	private String createName; // 创建人姓名
	@ApiModelProperty(value = "组织ID")
	private Long comId; // 组织ID
	@ApiModelProperty(value = "显示图标")
	private String pcIcon; // 显示图标
	@ApiModelProperty(value = "关联类型")
	private String asType; // 关联类型
	@ApiModelProperty(value = "创建时间 ")
	private Long createTime; // 创建时间
	@ApiModelProperty(value = "更新时间")
	private Long updateTime; // 更新时间
	@ApiModelProperty(value = "是否删除(0未删除，1已删除)")
	private Integer valid; // 是否删除(0未删除，1已删除)
	@ApiModelProperty(value = "任务类型标示 0：检验类 1：安保类")
	private Integer typeFlag;

	
	@ApiModelProperty(value = "任务类型")
	private String taskType; // 任务类型
	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getPcName() {
		return pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

	public String getPcDesc() {
		return pcDesc;
	}

	public void setPcDesc(String pcDesc) {
		this.pcDesc = pcDesc;
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

	public Long getComId() {
		return comId;
	}

	public void setComId(Long comId) {
		this.comId = comId;
	}

	public String getPcIcon() {
		return pcIcon;
	}

	public void setPcIcon(String pcIcon) {
		this.pcIcon = pcIcon;
	}

	public String getAsType() {
		return asType;
	}

	public void setAsType(String asType) {
		this.asType = asType;
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

	public Integer getTypeFlag() {
		return typeFlag;
	}

	public void setTypeFlag(Integer typeFlag) {
		this.typeFlag = typeFlag;
	}
}
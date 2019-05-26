package com.sgai.property.quality.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sgai.common.persistence.Page;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlTransient;

/**
 * 方案列表 vo
 */
public class PlanVo {
	@ApiModelProperty(value = "方案id")
	private String id;
	@ApiModelProperty(value = "专业范畴id")
	private String pcId;
	@ApiModelProperty(value = "方案名称")
	private String name;
	@ApiModelProperty(value = "方案说明")
	private String description;
	@ApiModelProperty(value = "专业范畴")
	private String professionalCategory;
	@ApiModelProperty(value = "操作人")
	private String operator;
	@ApiModelProperty(value = "更新时间")
	private Long updateTime;
	//冗余字段，禁止序列化
	protected Long startTime;
	protected Long endTime;
	protected Integer valid;

	private Page page;

	private long comId;

	private String comCode; //机构代码
	private String moduCode; //模块代码

	@ApiModelProperty(value = "任务类型标示 0：检验类 1：安保类")
	private Integer typeFlag;

	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public String getModuCode() {
		return moduCode;
	}

	public void setModuCode(String moduCode) {
		this.moduCode = moduCode;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public long getComId() {
		return comId;
	}

	public void setComId(long comId) {
		this.comId = comId;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProfessionalCategory() {
		return professionalCategory;
	}
	public void setProfessionalCategory(String professionalCategory) {
		this.professionalCategory = professionalCategory;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	@JsonIgnore
	@XmlTransient
	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	@JsonIgnore
	@XmlTransient
	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	@JsonIgnore
	@XmlTransient
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

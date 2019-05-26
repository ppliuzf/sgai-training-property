package com.sgai.property.quality.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 *@author 严浩淼
 *@date 2018年1月9日--下午6:14:02
 */
public class DeviceDto {
	@ApiModelProperty(value = "设备分类ID")
	private String id;//": "97042bd14ae04da798048e5940d65d24",
	private String remarks;//": "功能测试备注",
	private String createdDt;//": null,
	private String updatedDt;//": null,
	private String version;//": null,
	private String classCode;//": "1",
	@ApiModelProperty(value = "设备分类名称")
	private String className;//": "灯具开关",
	private String profCode;//": "1",
	private String profName;//": "照明专业",
	private String classDesc;//": "功能测试描述",
	private String enabledFlag;//": "Y"
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCreatedDt() {
		return createdDt;
	}
	public void setCreatedDt(String createdDt) {
		this.createdDt = createdDt;
	}
	public String getUpdatedDt() {
		return updatedDt;
	}
	public void setUpdatedDt(String updatedDt) {
		this.updatedDt = updatedDt;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getProfCode() {
		return profCode;
	}
	public void setProfCode(String profCode) {
		this.profCode = profCode;
	}
	public String getProfName() {
		return profName;
	}
	public void setProfName(String profName) {
		this.profName = profName;
	}
	public String getClassDesc() {
		return classDesc;
	}
	public void setClassDesc(String classDesc) {
		this.classDesc = classDesc;
	}
	public String getEnabledFlag() {
		return enabledFlag;
	}
	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
    
    
}

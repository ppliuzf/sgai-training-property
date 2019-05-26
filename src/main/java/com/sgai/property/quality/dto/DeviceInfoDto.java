package com.sgai.property.quality.dto;

import io.swagger.annotations.ApiModelProperty;

public class DeviceInfoDto {
	@ApiModelProperty(value = "设备专业ID")
	private String id;//": "f66a5e59964a4ba983c499f9531d07c1",
	private String remarks;//": null,
	private String createdDt;//": null,
	private String updatedDt;//": null,
	private String version;//": null,
	@ApiModelProperty(value = "设备分类编码")
	private String devicesCode;//": "SB0031",
    @ApiModelProperty(value = "设备分类名称")
	private String devicesName;//": "风机盘管",
	private String profName;//": "空调专业",
	private String className;//": "盘管",
	private String brandName;//": "西门子",
	private String devicesModel;//": "空调压缩机",
	private String supplierName;//": "供应商Name1",
	private String telPhone;//": "8829011",
	private String spaceName;//": "7楼",
	private String outDate;//": "2018-01-17 00:00:00",
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
	public String getDevicesCode() {
		return devicesCode;
	}
	public void setDevicesCode(String devicesCode) {
		this.devicesCode = devicesCode;
	}
	public String getDevicesName() {
		return devicesName;
	}
	public void setDevicesName(String devicesName) {
		this.devicesName = devicesName;
	}
	public String getProfName() {
		return profName;
	}
	public void setProfName(String profName) {
		this.profName = profName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getDevicesModel() {
		return devicesModel;
	}
	public void setDevicesModel(String devicesModel) {
		this.devicesModel = devicesModel;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getTelPhone() {
		return telPhone;
	}
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	public String getSpaceName() {
		return spaceName;
	}
	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}
	public String getOutDate() {
		return outDate;
	}
	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}
	public String getEnabledFlag() {
		return enabledFlag;
	}
	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
    
}

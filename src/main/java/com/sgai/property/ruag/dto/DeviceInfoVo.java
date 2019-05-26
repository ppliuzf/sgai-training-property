package com.sgai.property.ruag.dto;

import java.util.List;

/**
 * @ClassName: DeviceInfo
 * @com.sgai.property.commonService.vo;(这里用一句话描述这个类的作用)
 * @author 王天尧
 * @date 2018年7月11日
 * @Company 首自信--智慧城市创新中心
 */

public class DeviceInfoVo {

	private String deviceID; // 设备唯一编号

	private String deviceName; // 设备完整名称

	private String path; // 设备标签

	private String type; // 表示数据类型，list或者是item

	private String property; // 标识是单个设备还是kpi
	
    private String remarks;
    
	private List<ParameterVo> valueList;

	private String x; // x轴坐标

	private String y; // y轴坐标
	
	private String parentCode;//父类设备编码
	
	private String parentSpace;//父类设备位置

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<ParameterVo> getValueList() {
		return valueList;
	}

	public void setValueList(List<ParameterVo> valueList) {
		this.valueList = valueList;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getParentSpace() {
		return parentSpace;
	}

	public void setParentSpace(String parentSpace) {
		this.parentSpace = parentSpace;
	}

}

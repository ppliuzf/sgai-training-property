  
    /**    
    * @Title: MdmDevicesModelVo.java  
    * @Package com.sgai.modules.mdm.dto  
    * @com.sgai.property.commonService.vo;(用一句话描述该文件做什么)
    * @author maronglu  
    * @date 2018年5月23日  
    * @Company 首自信--智慧城市创新中心
    * @version V1.0    
    */  
    
package com.sgai.property.mdm.dto;

import com.sgai.common.persistence.BoEntity;

import io.swagger.annotations.ApiModelProperty;

/**  
    * @ClassName: MdmDevicesModelVo  
    * @com.sgai.property.commonService.vo;(这里用一句话描述这个类的作用)
    * @author maronglu  
    * @date 2018年5月23日  
    * @Company 首自信--智慧城市创新中心  
    */

public class MdmDevicesModelVo extends BoEntity<MdmDevicesModelVo>{
	private String id;				//主键
	private static final long serialVersionUID = 1L;
	private String brandCode;		// 品牌编码
	private String brandName;		// 品牌名称
	private String modelCode;		// 型号编码
	private String devicesModel;		// 设备型号
	private String powerRequ;		// 供电要求
	private String voltage;		// 额定电压
	private String power;		// 功率
	private String consumption;		// 功耗
	private String runTime;		// 运行时长
	private String serviceLife;		// 使用寿命
	private String classCode;		// 类型编码
	private String className;		// 类型名称
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	private String profName;//专业类型
	private String profCode;//类型编码
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getModelCode() {
		return modelCode;
	}
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	public String getDevicesModel() {
		return devicesModel;
	}
	public void setDevicesModel(String devicesModel) {
		this.devicesModel = devicesModel;
	}
	public String getPowerRequ() {
		return powerRequ;
	}
	public void setPowerRequ(String powerRequ) {
		this.powerRequ = powerRequ;
	}
	public String getVoltage() {
		return voltage;
	}
	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public String getConsumption() {
		return consumption;
	}
	public void setConsumption(String consumption) {
		this.consumption = consumption;
	}
	public String getRunTime() {
		return runTime;
	}
	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}
	public String getServiceLife() {
		return serviceLife;
	}
	public void setServiceLife(String serviceLife) {
		this.serviceLife = serviceLife;
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
	public String getEnabledFlag() {
		return enabledFlag;
	}
	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getProfName() {
		return profName;
	}
	public void setProfName(String profName) {
		this.profName = profName;
	}
	public String getProfCode() {
		return profCode;
	}
	public void setProfCode(String profCode) {
		this.profCode = profCode;
	}
	
	
	
}

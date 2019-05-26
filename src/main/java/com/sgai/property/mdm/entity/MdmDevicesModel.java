package com.sgai.property.mdm.entity;

import org.hibernate.validator.constraints.Length;
import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
    * ClassName: MdmDevicesModel  
    * com.sgai.property.commonService.vo;(设备型号实体类)
    * @author yangyz  
    * Date 2017年11月24日  
    * Company 首自信--智慧城市创新中心
 */
public class MdmDevicesModel extends BoEntity<MdmDevicesModel> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "品牌编码")
	private String brandCode;		// 品牌编码
	@ApiModelProperty(value = "品牌名称")
	private String brandName;		// 品牌名称
	@ApiModelProperty(value = "型号编码")
	private String modelCode;		// 型号编码
	@ApiModelProperty(value = "供电要求")
	private String devicesModel;		// 设备型号
	@ApiModelProperty(value = "供电要求")
	private String powerRequ;		// 供电要求
	@ApiModelProperty(value = "额定电压")
	private String voltage;		// 额定电压
	@ApiModelProperty(value = "功率")
	private String power;		// 功率
	@ApiModelProperty(value = "功耗")
	private String consumption;		// 功耗
	@ApiModelProperty(value = "运行时长")
	private String runTime;		// 运行时长
	@ApiModelProperty(value = "使用寿命")
	private String serviceLife;		// 使用寿命
	@ApiModelProperty(value = "类型编码")
	private String classCode;		// 类型编码
	@ApiModelProperty(value = "类型名称")
	private String className;		// 类型名称
	@ApiModelProperty(value = "可用标识")
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	
	public MdmDevicesModel() {
		super();
	}

	public MdmDevicesModel(String id){
		super(id);
	}

	@Length(min=0, max=60, message="品牌编码长度必须介于 0 和 60 之间")
	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	
	@Length(min=0, max=60, message="品牌名称长度必须介于 0 和 60 之间")
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	@Length(min=0, max=60, message="型号编码长度必须介于 0 和 60 之间")
	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	
	@Length(min=0, max=60, message="设备型号长度必须介于 0 和 60 之间")
	public String getDevicesModel() {
		return devicesModel;
	}

	public void setDevicesModel(String devicesModel) {
		this.devicesModel = devicesModel;
	}
	
	@Length(min=0, max=128, message="供电要求长度必须介于 0 和 128 之间")
	public String getPowerRequ() {
		return powerRequ;
	}

	public void setPowerRequ(String powerRequ) {
		this.powerRequ = powerRequ;
	}
	
	@Length(min=0, max=60, message="额定电压长度必须介于 0 和 60 之间")
	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
	
	@Length(min=0, max=60, message="功率长度必须介于 0 和 60 之间")
	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}
	
	@Length(min=0, max=60, message="功耗长度必须介于 0 和 60 之间")
	public String getConsumption() {
		return consumption;
	}

	public void setConsumption(String consumption) {
		this.consumption = consumption;
	}
	
	@Length(min=0, max=60, message="运行时长长度必须介于 0 和 60 之间")
	public String getRunTime() {
		return runTime;
	}

	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}
	
	@Length(min=0, max=60, message="使用寿命长度必须介于 0 和 60 之间")
	public String getServiceLife() {
		return serviceLife;
	}

	public void setServiceLife(String serviceLife) {
		this.serviceLife = serviceLife;
	}
	
	@Length(min=0, max=60, message="类型编码长度必须介于 0 和 60 之间")
	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	
	@Length(min=0, max=60, message="类型名称长度必须介于 0 和 60 之间")
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	//@选项为:'Y':是'N':否默认为'Y'长度必须介于 1 和 1 之间")
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
}
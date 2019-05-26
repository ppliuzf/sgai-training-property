package com.sgai.property.mdm.entity;

import org.hibernate.validator.constraints.Length;

import com.sgai.common.persistence.BoEntity;

/**
 * 供应商设备型号关联表Entity
 * @author liushang
 * @version 2017-11-27
 */
public class MdmSuppDeviceClassRelation extends BoEntity<MdmSuppDeviceClassRelation> {
	
	private static final long serialVersionUID = 1L;
	private String supplierNo;		// 供应商编号
	private String deviceModel;			// 设备型号名
	private String brandName;	
	private String modelCode; //设备型号代码
	private String powerRequ;	
	private String voltage;	
	private String power;	
	private String consumption;	
	private String runTime;	
	private String serviceLife;
	private String className;
	public MdmSuppDeviceClassRelation() {
		super();
	}

	public MdmSuppDeviceClassRelation(String id){
		super(id);
	}

	@Length(min=0, max=64, message="供应商编号长度必须介于 0 和 64 之间")
	public String getSupplierNo() {
		return supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}
	

	public String getPowerRequ() {
		return powerRequ;
	}

	public void setPowerRequ(String powerRequ) {
		this.powerRequ = powerRequ;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
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

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	
}
package com.sgai.property.ruag.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;


/**
 * 联动后置设备Entity
 * @author yangyz
 * @version 2018-01-02
 */
public class RuagLinkaageNextDevice extends BoEntity<RuagLinkaageNextDevice> {
	
	private static final long serialVersionUID = 1L;
	private String frontId;		// 前置ID
	private String linkageCode;		// 联动代码
	private String linkageName;		// 联动名称
	private String profCode;		// 专业代码
	private String profName;		// 专业名称
	private String deviceCode;		// 设备代码
	private String deviceName;		// 设备名称
	private String spaceCode;		// 空间编码
	private String spaceName;		// 空间名称引用节点类型的数据的名称
	private String ruleSet;		// rule_set
	private String status;		// N:未执行Y:已执行
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	private String classCode;   //设备类型编码
	public RuagLinkaageNextDevice() {
		super();
	}

	public RuagLinkaageNextDevice(String id){
		super(id);
	}

	@Length(min=0, max=60, message="前置ID长度必须介于 0 和 60 之间")
	public String getFrontId() {
		return frontId;
	}

	public void setFrontId(String frontId) {
		this.frontId = frontId;
	}
	
	@Length(min=0, max=60, message="联动代码长度必须介于 0 和 60 之间")
	public String getLinkageCode() {
		return linkageCode;
	}

	public void setLinkageCode(String linkageCode) {
		this.linkageCode = linkageCode;
	}
	
	@Length(min=0, max=60, message="联动名称长度必须介于 0 和 60 之间")
	public String getLinkageName() {
		return linkageName;
	}

	public void setLinkageName(String linkageName) {
		this.linkageName = linkageName;
	}
	
	@Length(min=0, max=60, message="专业代码长度必须介于 0 和 60 之间")
	public String getProfCode() {
		return profCode;
	}

	public void setProfCode(String profCode) {
		this.profCode = profCode;
	}
	
	@Length(min=0, max=60, message="专业名称长度必须介于 0 和 60 之间")
	public String getProfName() {
		return profName;
	}

	public void setProfName(String profName) {
		this.profName = profName;
	}
	
	@Length(min=0, max=60, message="设备代码长度必须介于 0 和 60 之间")
	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	
	@Length(min=0, max=60, message="设备名称长度必须介于 0 和 60 之间")
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	@Length(min=0, max=60, message="空间编码长度必须介于 0 和 60 之间")
	public String getSpaceCode() {
		return spaceCode;
	}

	public void setSpaceCode(String spaceCode) {
		this.spaceCode = spaceCode;
	}
	
	@Length(min=0, max=60, message="空间名称引用节点类型的数据的名称长度必须介于 0 和 60 之间")
	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}
	
	@Length(min=0, max=512, message="rule_set长度必须介于 0 和 512 之间")
	public String getRuleSet() {
		return ruleSet;
	}

	public void setRuleSet(String ruleSet) {
		this.ruleSet = ruleSet;
	}
	
	@Length(min=0, max=2, message="N:未执行Y:已执行长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	
}
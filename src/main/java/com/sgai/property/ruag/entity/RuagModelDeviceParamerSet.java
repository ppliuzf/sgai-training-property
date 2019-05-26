package com.sgai.property.ruag.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;
import com.sgai.property.mdm.entity.MdmDeviceParameter;

/**
 * 模式设备参数设置Entity
 * @author yangyz
 * @version 2018-01-02
 */
public class RuagModelDeviceParamerSet extends BoEntity<RuagModelDeviceParamerSet> {
	
	private static final long serialVersionUID = 1L;
	private String workModelId;		// 运行策略ID
	private String deviceCode;		// 设备代码
	private String parameterId;		// 参数ID
	private String parameterValue;		// 参数值
	private String timePoint;		// 时间点
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	private String parameterName;		// 参数名称
	public RuagModelDeviceParamerSet() {
		super();
	}

	public RuagModelDeviceParamerSet(String id){
		super(id);
	}

	@Length(min=0, max=60, message="运行策略ID长度必须介于 0 和 60 之间")
	public String getWorkModelId() {
		return workModelId;
	}

	public void setWorkModelId(String workModelId) {
		this.workModelId = workModelId;
	}
	
	@Length(min=0, max=60, message="设备代码长度必须介于 0 和 60 之间")
	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	
	@Length(min=0, max=60, message="参数ID长度必须介于 0 和 60 之间")
	public String getParameterId() {
		return parameterId;
	}

	public void setParameterId(String parameterId) {
		this.parameterId = parameterId;
	}
	
	@Length(min=0, max=60, message="参数值长度必须介于 0 和 60 之间")
	public String getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}
	
	@Length(min=0, max=60, message="时间点长度必须介于 0 和 60 之间")
	public String getTimePoint() {
		return timePoint;
	}

	public void setTimePoint(String timePoint) {
		this.timePoint = timePoint;
	}
	
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	
}
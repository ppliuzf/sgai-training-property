package com.sgai.property.ruag.dto;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;


/**
 * 联动规则设备参数vo类
 * @author 王天尧
 * @version 2018-02-26
 */
public class RuagLinkageDeviceParamSetVo {
	
	private String parameterName;		// 参数ID
	private String parameterValue;		// 参数值
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public String getParameterValue() {
		return parameterValue;
	}
	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}
	
}
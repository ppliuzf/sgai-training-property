package com.sgai.property.ruag.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;


/**
 * 优化参数配置Entity
 * @author admin
 * @version 2018-08-17
 */
public class RuagOptimizationParameterSet extends BoEntity<RuagOptimizationParameterSet> {
	
	private static final long serialVersionUID = 1L;
	private String workModelId;		// 运行策略具体设备id
	private String deviceCode;		// 设备代码
	private String parameterId;		// 参数ID
	private String parameterValue;		// 参数值
	private String timePoint;		// 时间点
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	private Integer version;		// 版本号
	private Date updatedDt;		// 修改时间
	private String updatedBy;		// 修改人
	private Date createdDt;		// 创建日期
	private String createdBy;		// 创建者
	private String parameterName;		// 参数名称
	private String comCode;		// 机构代码
	private String moduCode;		// 模块代码
	private String profCode;		// 专业代码
	private String oldId;          //原参数表id
	private String targets;        //目标选择
	// 非数据库字段
	private  String controlCode;   //date  or  time
	
	
	
	
	
	public String getControlCode() {
		return controlCode;
	}

	public void setControlCode(String controlCode) {
		this.controlCode = controlCode;
	}

	public String getOldId() {
		return oldId;
	}

	public void setOldId(String oldId) {
		this.oldId = oldId;
	}



	public String getTargets() {
		return targets;
	}

	public void setTargets(String targets) {
		this.targets = targets;
	}

	public String getProfCode() {
		return profCode;
	}

	public void setProfCode(String profCode) {
		this.profCode = profCode;
	}

	public RuagOptimizationParameterSet() {
		super();
	}

	public RuagOptimizationParameterSet(String id){
		super(id);
	}

	@Length(min=0, max=60, message="运行策略具体设备id长度必须介于 0 和 60 之间")
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
	
	//@选项为:'Y':是'N':否默认为'Y'长度必须介于 1 和 1 之间")
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}
	
	@Length(min=0, max=32, message="修改人长度必须介于 0 和 32 之间")
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}
	
	@Length(min=0, max=32, message="创建者长度必须介于 0 和 32 之间")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Length(min=0, max=60, message="参数名称长度必须介于 0 和 60 之间")
	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	
	@Length(min=1, max=60, message="机构代码长度必须介于 1 和 60 之间")
	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	
	@Length(min=0, max=60, message="模块代码长度必须介于 0 和 60 之间")
	public String getModuCode() {
		return moduCode;
	}

	public void setModuCode(String moduCode) {
		this.moduCode = moduCode;
	}
	
}
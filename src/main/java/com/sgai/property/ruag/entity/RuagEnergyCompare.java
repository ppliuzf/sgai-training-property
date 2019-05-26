package com.sgai.property.ruag.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;


/**
 * 能耗对比Entity
 * @author admin
 * @version 2018-08-17
 */
public class RuagEnergyCompare extends BoEntity<RuagEnergyCompare> {
	
	private static final long serialVersionUID = 1L;
	private String modelTemplateId;		// 运行策略id
	private String energyBefore;		// 优化前能耗
	private String energyAftter;		// 优化后能耗
	private Date dateTime;		// 时间
	private String energyType;		// 能耗类型（单日能耗或是总能耗）
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	private Integer version;		// 版本号
	private Date updatedDt;		// 修改时间
	private String updatedBy;		// 修改人
	private Date createdDt;		// 创建日期
	private String createdBy;		// 创建者
	private String parameterName;		// 参数名称
	private String comCode;		// 机构代码
	private String moduCode;		// 模块代码
	private String recordId;		// 记录id
	// 非数据库字段
	private  String controlCode;
	
	
	
	
	
	
	public String getControlCode() {
		return controlCode;
	}

	public void setControlCode(String controlCode) {
		this.controlCode = controlCode;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public RuagEnergyCompare() {
		super();
	}

	public RuagEnergyCompare(String id){
		super(id);
	}

	@Length(min=0, max=60, message="运行策略id长度必须介于 0 和 60 之间")
	public String getModelTemplateId() {
		return modelTemplateId;
	}

	public void setModelTemplateId(String modelTemplateId) {
		this.modelTemplateId = modelTemplateId;
	}
	
	@Length(min=0, max=60, message="优化前能耗长度必须介于 0 和 60 之间")
	public String getEnergyBefore() {
		return energyBefore;
	}

	public void setEnergyBefore(String energyBefore) {
		this.energyBefore = energyBefore;
	}
	
	@Length(min=0, max=60, message="优化后能耗长度必须介于 0 和 60 之间")
	public String getEnergyAftter() {
		return energyAftter;
	}

	public void setEnergyAftter(String energyAftter) {
		this.energyAftter = energyAftter;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	
	@Length(min=0, max=60, message="能耗类型（单日能耗或是总能耗）长度必须介于 0 和 60 之间")
	public String getEnergyType() {
		return energyType;
	}

	public void setEnergyType(String energyType) {
		this.energyType = energyType;
	}
	
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
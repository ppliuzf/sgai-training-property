package com.sgai.property.ruag.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;


/**
 * 优化指标Entity
 * @author admin
 * @version 2018-08-17
 */
public class RuagOptIndicator extends BoEntity<RuagOptIndicator> {
	
	private static final long serialVersionUID = 1L;
	private String modelTemplateId;		// 运行策略id
	private String optIndicator;		// 优化指标
	private String parameterId;		// 参数id
	private String arg1;		// 扩展字段1
	private String arg2;		// 扩展字段2
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	private Integer version;		// 版本号
	private Date updatedDt;		// 修改时间
	private String updatedBy;		// 修改人
	private Date createdDt;		// 创建日期
	private String createdBy;		// 创建者
	private String parameterName;		// 参数名称
	private String comCode;		// 机构代码
	private String moduCode;		// 模块代码
	
	public RuagOptIndicator() {
		super();
	}

	public RuagOptIndicator(String id){
		super(id);
	}

	@Length(min=0, max=60, message="运行策略id长度必须介于 0 和 60 之间")
	public String getModelTemplateId() {
		return modelTemplateId;
	}

	public void setModelTemplateId(String modelTemplateId) {
		this.modelTemplateId = modelTemplateId;
	}
	
	@Length(min=0, max=60, message="优化指标长度必须介于 0 和 60 之间")
	public String getOptIndicator() {
		return optIndicator;
	}

	public void setOptIndicator(String optIndicator) {
		this.optIndicator = optIndicator;
	}
	
	@Length(min=0, max=60, message="参数id长度必须介于 0 和 60 之间")
	public String getParameterId() {
		return parameterId;
	}

	public void setParameterId(String parameterId) {
		this.parameterId = parameterId;
	}
	
	@Length(min=0, max=60, message="扩展字段1长度必须介于 0 和 60 之间")
	public String getArg1() {
		return arg1;
	}

	public void setArg1(String arg1) {
		this.arg1 = arg1;
	}
	
	@Length(min=0, max=255, message="扩展字段2长度必须介于 0 和 255 之间")
	public String getArg2() {
		return arg2;
	}

	public void setArg2(String arg2) {
		this.arg2 = arg2;
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
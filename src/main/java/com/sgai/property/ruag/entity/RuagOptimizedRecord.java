package com.sgai.property.ruag.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;


/**
 * 优化记录Entity
 * @author admin
 * @version 2018-08-17
 */
public class RuagOptimizedRecord extends BoEntity<RuagOptimizedRecord> {
	
	private static final long serialVersionUID = 1L;
	private String mdoelTemplateId;		// 运行策略id
	private String modelTemplateName;		// 运行策略名称
	private Date optimizationTime;		// 优化时间
	private String userCode;		// 操作人编码
	private String userName;		// 操作人名称
	private String optimizationMethod;		// 优化方式
	private String arg1;		// 扩展字段1
	private String arg2;		// 扩展字段2
	private Integer version;		// 版本号
	private Date updatedDt;		// 修改时间
	private String updatedBy;		// 修改人
	private Date createdDt;		// 创建日期
	private String createdBy;		// 创建者
	private String comCode;		// 机构代码
	private String moduCode;		// 模块代码
	
	public RuagOptimizedRecord() {
		super();
	}

	public RuagOptimizedRecord(String id){
		super(id);
	}

	@Length(min=1, max=60, message="运行策略id长度必须介于 1 和 60 之间")
	public String getMdoelTemplateId() {
		return mdoelTemplateId;
	}

	public void setMdoelTemplateId(String mdoelTemplateId) {
		this.mdoelTemplateId = mdoelTemplateId;
	}
	
	@Length(min=0, max=60, message="运行策略名称长度必须介于 0 和 60 之间")
	public String getModelTemplateName() {
		return modelTemplateName;
	}

	public void setModelTemplateName(String modelTemplateName) {
		this.modelTemplateName = modelTemplateName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOptimizationTime() {
		return optimizationTime;
	}

	public void setOptimizationTime(Date optimizationTime) {
		this.optimizationTime = optimizationTime;
	}
	
	@Length(min=0, max=60, message="操作人编码长度必须介于 0 和 60 之间")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	@Length(min=0, max=60, message="操作人名称长度必须介于 0 和 60 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=60, message="优化方式长度必须介于 0 和 60 之间")
	public String getOptimizationMethod() {
		return optimizationMethod;
	}

	public void setOptimizationMethod(String optimizationMethod) {
		this.optimizationMethod = optimizationMethod;
	}
	
	@Length(min=0, max=60, message="扩展字段1长度必须介于 0 和 60 之间")
	public String getArg1() {
		return arg1;
	}

	public void setArg1(String arg1) {
		this.arg1 = arg1;
	}
	
	@Length(min=0, max=60, message="扩展字段2长度必须介于 0 和 60 之间")
	public String getArg2() {
		return arg2;
	}

	public void setArg2(String arg2) {
		this.arg2 = arg2;
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
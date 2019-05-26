package com.sgai.property.ruag.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;


/**
 * 优化项Entity
 * @author admin
 * @version 2018-08-17
 */
public class RuagOptItem extends BoEntity<RuagOptItem> {
	
	private static final long serialVersionUID = 1L;
	private String optimizationItem;		// 优化项
	private String optimizationItemDesc;		// 优化项说明
	private String profCode;		// 专业代码
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
	
	public RuagOptItem() {
		super();
	}

	public RuagOptItem(String id){
		super(id);
	}

	@Length(min=0, max=60, message="优化项长度必须介于 0 和 60 之间")
	public String getOptimizationItem() {
		return optimizationItem;
	}

	public void setOptimizationItem(String optimizationItem) {
		this.optimizationItem = optimizationItem;
	}
	
	@Length(min=0, max=255, message="优化项说明长度必须介于 0 和 255 之间")
	public String getOptimizationItemDesc() {
		return optimizationItemDesc;
	}

	public void setOptimizationItemDesc(String optimizationItemDesc) {
		this.optimizationItemDesc = optimizationItemDesc;
	}
	
	@Length(min=0, max=60, message="专业代码长度必须介于 0 和 60 之间")
	public String getProfCode() {
		return profCode;
	}

	public void setProfCode(String profCode) {
		this.profCode = profCode;
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
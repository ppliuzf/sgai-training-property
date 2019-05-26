package com.sgai.property.mdm.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

/**
 * 区域描述 ---空间Entity
 * @author zhb
 * @version 2017-11-24
 */
public class MdmAreaStruct extends BoEntity<MdmAreaStruct> {
	
	private static final long serialVersionUID = 1L;
	private String areaCode;		// 自动生成
	private String areaName;		// 区域名称
	private String areaUse;		// 区域用途
	private String areaType;		// 属性类别
	private String areaLevel;		// 级别行政级别：区级、县级、街道
	private String planChar;		// 规划章程
	private String planYear;		// 规划年份
	private Date startDate;		// 开工时间
	private Date endDate;		// 竣工时间
	private char enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	private Integer version;		// 版本号
	private Date updatedDt;		// 修改时间
	private String updatedBy;		// 修改人
	private Date createdDt;		// 创建日期
	private String createdBy;		// 创建者
	private String areaProperty;  // 空间属性
	
	public MdmAreaStruct() {
		super();
	}

	public MdmAreaStruct(String id){
		super(id);
	}

	@Length(min=0, max=60, message="自动生成长度必须介于 0 和 60 之间")
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	@Length(min=0, max=60, message="区域名称长度必须介于 0 和 60 之间")
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	@Length(min=0, max=60, message="区域用途长度必须介于 0 和 60 之间")
	public String getAreaUse() {
		return areaUse;
	}

	public void setAreaUse(String areaUse) {
		this.areaUse = areaUse;
	}
	
	@Length(min=0, max=60, message="属性类别长度必须介于 0 和 60 之间")
	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	
	@Length(min=0, max=60, message="级别行政级别：区级、县级、街道长度必须介于 0 和 60 之间")
	public String getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(String areaLevel) {
		this.areaLevel = areaLevel;
	}
	
	@Length(min=0, max=1024, message="规划章程长度必须介于 0 和 1024 之间")
	public String getPlanChar() {
		return planChar;
	}

	public void setPlanChar(String planChar) {
		this.planChar = planChar;
	}
	
	@Length(min=0, max=60, message="规划年份长度必须介于 0 和 60 之间")
	public String getPlanYear() {
		return planYear;
	}

	public void setPlanYear(String planYear) {
		this.planYear = planYear;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public char getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(char enabledFlag) {
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


	public String getAreaProperty() {
		return areaProperty;
	}

	public void setAreaProperty(String areaProperty) {
		this.areaProperty = areaProperty;
	}
	
}
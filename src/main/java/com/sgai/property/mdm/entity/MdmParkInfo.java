package com.sgai.property.mdm.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

/**
 * 园区描述 ---空间Entity
 * @author zhb
 * @version 2017-11-24
 */
public class MdmParkInfo extends BoEntity<MdmParkInfo> {
	
	private static final long serialVersionUID = 1L;
	private String parkCode;		// 园区编号;系统自动生成
	private String parkName;		// 园区名称
	private String parkDesc;		// 园区介绍
	private String viewImge;		// 概览图
	private String planChar;		// plan_char
	private String planYear;		// plan_year
	private Date startDate;		// start_date
	private Date endDate;		// end_date
	private Double longiTude;		// longi_tude
	private Double latiTude;		// lati_tude
	private char enabledFlag;		// enabled_flag
	private Integer version;		// version
	private Date updatedDt;		// updated_dt
	private String updatedBy;		// updated_by
	private Date createdDt;		// created_dt
	private String createdBy;		// created_by
	private String parkProperty; //空间属性
	
	
	public MdmParkInfo() {
		super();
	}

	public MdmParkInfo(String id){
		super(id);
	}

	@Length(min=0, max=60, message="园区编号;系统自动生成长度必须介于 0 和 60 之间")
	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	
	@Length(min=0, max=60, message="园区名称长度必须介于 0 和 60 之间")
	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	
	@Length(min=0, max=512, message="园区介绍长度必须介于 0 和 512 之间")
	public String getParkDesc() {
		return parkDesc;
	}

	public void setParkDesc(String parkDesc) {
		this.parkDesc = parkDesc;
	}
	
	@Length(min=0, max=100, message="概览图长度必须介于 0 和 100 之间")
	public String getViewImge() {
		return viewImge;
	}

	public void setViewImge(String viewImge) {
		this.viewImge = viewImge;
	}
	
	@Length(min=0, max=1024, message="plan_char长度必须介于 0 和 1024 之间")
	public String getPlanChar() {
		return planChar;
	}

	public void setPlanChar(String planChar) {
		this.planChar = planChar;
	}
	
	@Length(min=0, max=60, message="plan_year长度必须介于 0 和 60 之间")
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
	
	public Double getLongiTude() {
		return longiTude;
	}

	public void setLongiTude(Double longiTude) {
		this.longiTude = longiTude;
	}
	
	public Double getLatiTude() {
		return latiTude;
	}

	public void setLatiTude(Double latiTude) {
		this.latiTude = latiTude;
	}
	
	@Length(min=1, max=1, message="enabled_flag长度必须介于 1 和 1 之间")
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
	
	@Length(min=0, max=32, message="updated_by长度必须介于 0 和 32 之间")
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
	
	@Length(min=0, max=32, message="created_by长度必须介于 0 和 32 之间")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getParkProperty() {
		return parkProperty;
	}

	public void setParkProperty(String parkProperty) {
		this.parkProperty = parkProperty;
	}
	
}
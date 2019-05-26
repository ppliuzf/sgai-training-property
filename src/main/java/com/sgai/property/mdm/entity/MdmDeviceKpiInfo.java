
package com.sgai.property.mdm.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;


/**
 * 标准值Entity
 * @author admin
 * @version 2018-07-27
 */
public class MdmDeviceKpiInfo extends BoEntity<MdmDeviceKpiInfo> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private String path;		// path
	private String profCode;		// prof_code
	private String spaceCode;		// space_code
	private String classCode;		// class_code
	private Integer version;		// 版本号
	private Date updatedDt;		// 修改时间
	private String updatedBy;		// 修改人
	private Date createdDt;		// 创建日期
	private String createdBy;		// 创建者
	private String comCode;		// com_code
	private String moduCode;		// modu_code
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	
	public MdmDeviceKpiInfo() {
		super();
	}

	public MdmDeviceKpiInfo(String id){
		super(id);
	}

	@Length(min=0, max=255, message="name长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="path长度必须介于 0 和 255 之间")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@Length(min=0, max=255, message="prof_code长度必须介于 0 和 255 之间")
	public String getProfCode() {
		return profCode;
	}

	public void setProfCode(String profCode) {
		this.profCode = profCode;
	}
	
	@Length(min=0, max=255, message="space_code长度必须介于 0 和 255 之间")
	public String getSpaceCode() {
		return spaceCode;
	}

	public void setSpaceCode(String spaceCode) {
		this.spaceCode = spaceCode;
	}
	
	@Length(min=0, max=255, message="class_code长度必须介于 0 和 255 之间")
	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
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
	
	@Length(min=0, max=60, message="com_code长度必须介于 0 和 60 之间")
	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	
	@Length(min=0, max=60, message="modu_code长度必须介于 0 和 60 之间")
	public String getModuCode() {
		return moduCode;
	}

	public void setModuCode(String moduCode) {
		this.moduCode = moduCode;
	}
	
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
}
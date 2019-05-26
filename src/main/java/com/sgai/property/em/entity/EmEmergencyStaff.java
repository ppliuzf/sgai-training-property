package com.sgai.property.em.entity;

import java.util.Date;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
    * ClassName: EmEmergencyStaff  
    * com.sgai.property.commonService.vo;(应急人员表Entity)
    * @author yangyz  
    * Date 2017年12月14日  
    * Company 首自信--智慧城市创新中心
 */
public class EmEmergencyStaff extends BoEntity<EmEmergencyStaff> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "是否可用")
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	@ApiModelProperty(value = "版本号")
	private Integer version;		// 版本号
	@ApiModelProperty(value = "修改时间")
	private Date updatedDt;		// 修改时间
	@ApiModelProperty(value = "修改人")
	private String updatedBy;		// 修改人
	@ApiModelProperty(value = "创建日期")
	private Date createdDt;		// 创建日期
	@ApiModelProperty(value = "创建者")
	private String createdBy;		// 创建者
	@ApiModelProperty(value = "人员编码")
	private String staffCode;		// 人员编码
	@ApiModelProperty(value = "人员名称")
	private String staffName;		// 人员名称
	@ApiModelProperty(value = "类型编号")
	private String typeCode;		//类型编号    （来自字典表）
	@ApiModelProperty(value = "人员类型 ")
	private String staffType;		// 人员类型        专家：0 应急人员：1
	@ApiModelProperty(value = "联系电话")
	private String contactNumber;		// 联系电话
	@ApiModelProperty(value = "毕业院校")
	private String graduateSchool;		// 毕业院校
	@ApiModelProperty(value = "工作经历")
	private String workExperience;		// 工作经历
	@ApiModelProperty(value = "机构代码")
	private String comCode;             //机构代码
	
	public EmEmergencyStaff() {
		super();
	}

	public EmEmergencyStaff(String id){
		super(id);
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
	
	@Length(min=0, max=32, message="人员编码长度必须介于 0 和 32 之间")
	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
	
	@Length(min=0, max=64, message="人员名称长度必须介于 0 和 64 之间")
	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	
	@Length(min=0, max=32, message="类型长度必须介于 0 和 32 之间")
	public String getStaffType() {
		return staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}
	
	@Length(min=0, max=32, message="联系电话长度必须介于 0 和 32 之间")
	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	@Length(min=0, max=128, message="毕业院校长度必须介于 0 和 128 之间")
	public String getGraduateSchool() {
		return graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}
	
	@Length(min=0, max=512, message="工作经历长度必须介于 0 和 512 之间")
	public String getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}

	/**
	 * typeCode.
	 *
	 * @return  the typeCode
	 * @since   JDK 1.8
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * typeCode.
	 *
	 * @param   typeCode    the typeCode to set
	 * @since   JDK 1.8
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	/**
	 * comCode.
	 *
	 * @return  the comCode
	 * @since   JDK 1.8
	 */
	public String getComCode() {
		return comCode;
	}

	/**
	 * comCode.
	 *
	 * @param   comCode    the comCode to set
	 * @since   JDK 1.8
	 */
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	
}
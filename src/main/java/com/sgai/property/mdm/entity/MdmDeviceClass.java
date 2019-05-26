package com.sgai.property.mdm.entity;

import org.hibernate.validator.constraints.Length;
import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
    * ClassName: MdmDeviceClass  
    * com.sgai.property.commonService.vo;(设备类型实体类)
    * @author yangyz  
    * Date 2017年11月24日  
    * Company 首自信--智慧城市创新中心
 */
public class MdmDeviceClass extends BoEntity<MdmDeviceClass> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "类型编码")
	private String classCode;		// 类型编码
	@ApiModelProperty(value = "类型名称")
	private String className;		// 类型名称
	@ApiModelProperty(value = "专业代码")
	private String profCode;		// 专业代码
	@ApiModelProperty(value = "专业名称")
	private String profName;		// 专业名称
	@ApiModelProperty(value = "说明")
	private String classDesc;		// 说明
	@ApiModelProperty(value = "可用标识")
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	
	public MdmDeviceClass() {
		super();
	}

	public MdmDeviceClass(String id){
		super(id);
	}

	@Length(min=0, max=60, message="类型编码长度必须介于 0 和 60 之间")
	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	
	@Length(min=0, max=60, message="类型名称长度必须介于 0 和 60 之间")
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	@Length(min=0, max=60, message="专业代码长度必须介于 0 和 60 之间")
	public String getProfCode() {
		return profCode;
	}

	public void setProfCode(String profCode) {
		this.profCode = profCode;
	}
	
	@Length(min=0, max=60, message="专业名称长度必须介于 0 和 60 之间")
	public String getProfName() {
		return profName;
	}

	public void setProfName(String profName) {
		this.profName = profName;
	}
	
	@Length(min=0, max=60, message="说明长度必须介于 0 和 60 之间")
	public String getClassDesc() {
		return classDesc;
	}

	public void setClassDesc(String classDesc) {
		this.classDesc = classDesc;
	}
	
	//@选项为:'Y':是'N':否默认为'Y'长度必须介于 1 和 1 之间")
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
}
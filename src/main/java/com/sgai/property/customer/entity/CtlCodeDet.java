
package com.sgai.property.customer.entity;

import com.sgai.common.persistence.BoEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 统一基础代码表Entity
 * @author liushang
 * @version 2017-11-13
 */
public class CtlCodeDet extends BoEntity<CtlCodeDet> {
	
	private static final long serialVersionUID = 1L;
	private String codeType;		// 基础代码类别
	private String comCode;		// 机构代码
	private String codeCode;		// 基础代码ID
	private String codeName;		// 基础代码名称
	private Long displayOrder;		// 显示顺序
	private String sysFlag;		// 是否系统内置
	private String enabledFlag;		// 是否开启
	private String baseDesc;		// 基础代码注释
	
	public CtlCodeDet() {
		super();
	}

	public CtlCodeDet(String id){
		super(id);
	}

	@Length(min=1, max=20, message="code_type长度必须介于 1 和 20 之间")
	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	
	@Length(min=1, max=10, message="com_code长度必须介于 1 和 10 之间")
	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	
	@Length(min=0, max=20, message="code_code长度必须介于 0 和 20 之间")
	public String getCodeCode() {
		return codeCode;
	}

	public void setCodeCode(String codeCode) {
		this.codeCode = codeCode;
	}
	
	@Length(min=0, max=60, message="code_name长度必须介于 0 和 60 之间")
	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	
	public Long getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Long displayOrder) {
		this.displayOrder = displayOrder;
	}
	
	@Length(min=1, max=1, message="sys_flag长度必须介于 1 和 1 之间")
	public String getSysFlag() {
		return sysFlag;
	}

	public void setSysFlag(String sysFlag) {
		this.sysFlag = sysFlag;
	}
	
	@Length(min=1, max=1, message="enabled_flag长度必须介于 1 和 1 之间")
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
	@Length(min=0, max=255, message="base_desc长度必须介于 0 和 255 之间")
	public String getBaseDesc() {
		return baseDesc;
	}

	public void setBaseDesc(String baseDesc) {
		this.baseDesc = baseDesc;
	}

	
}
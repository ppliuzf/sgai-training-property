
package com.sgai.property.ctl.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.sgai.common.persistence.BoEntity;

/**
 * 系统基础代码类别表Entity
 * @author liushang
 * @version 2017-11-13
 */
public class CtlCodeType extends BoEntity<CtlCodeType> {
	
	private static final long serialVersionUID = 1L;
	private String codeType;		// 基础代码类别
	private String codeTypeName;		// 基础代码类名
	private String gcFlag;		// 'G':表示集团'C':表示机构
	private String enabledFlag;		// 是否开启
	private String sysFlag;		// 是否系统内置
	private String remark;		// 备注
	private String pageSize;
	private String pageNo;
	
	public CtlCodeType() {
		super();
	}

	public CtlCodeType(String id){
		super(id);
	}

	@Length(min=1, max=20, message="code_type长度必须介于 1 和 20 之间")
	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	
	@Length(min=1, max=60, message="code_type_name长度必须介于 1 和 60 之间")
	public String getCodeTypeName() {
		return codeTypeName;
	}

	public void setCodeTypeName(String codeTypeName) {
		this.codeTypeName = codeTypeName;
	}
	
	@Length(min=1, max=1, message="'G':表示集团'C':表示机构长度必须介于 1 和 1 之间")
	public String getGcFlag() {
		return gcFlag;
	}

	public void setGcFlag(String gcFlag) {
		this.gcFlag = gcFlag;
	}
	
	@Length(min=1, max=1, message="enabled_flag长度必须介于 1 和 1 之间")
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
	@Length(min=1, max=1, message="sys_flag长度必须介于 1 和 1 之间")
	public String getSysFlag() {
		return sysFlag;
	}

	public void setSysFlag(String sysFlag) {
		this.sysFlag = sysFlag;
	}
	
	@Length(min=0, max=250, message="remark长度必须介于 0 和 250 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	
}
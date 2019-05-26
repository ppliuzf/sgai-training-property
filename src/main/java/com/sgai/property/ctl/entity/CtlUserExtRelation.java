package com.sgai.property.ctl.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

/**
 * 用户关系表Entity
 * 
 * @author 李伟
 * @version 2018-02-07
 */
public class CtlUserExtRelation extends BoEntity<CtlUserExtRelation> {

	private static final long serialVersionUID = 1L;
	private String userCode; // 用户名
	private String userName; // 业务用户名
	private String busiCode; // 系统代码
	private String ctlUserCode; // 用户代码
	private String ext1; // 扩展字段
	private String enabledFlag; // 1.选项为:'Y':是'N':否默认为'Y'

	public CtlUserExtRelation() {
		super();
	}

	public CtlUserExtRelation(String id) {
		super(id);
	}

	@Length(min = 0, max = 60, message = "用户名长度必须介于 0 和 60 之间")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Length(min = 0, max = 64, message = "业务用户名长度必须介于 0 和 64 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Length(min = 0, max = 60, message = "系统代码长度必须介于 0 和 60 之间")
	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}

	@Length(min = 0, max = 60, message = "用户代码长度必须介于 0 和 60 之间")
	public String getCtlUserCode() {
		return ctlUserCode;
	}

	public void setCtlUserCode(String ctlUserCode) {
		this.ctlUserCode = ctlUserCode;
	}

	@Length(min = 0, max = 64, message = "扩展字段长度必须介于 0 和 64 之间")
	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

}
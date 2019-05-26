package com.sgai.property.ctl.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

/**
 * 外部系统用户维护Entity
 * @author 李伟
 * @version 2018-02-07
 */
public class CtlBusiUser extends BoEntity<CtlBusiUser> {
	
	private static final long serialVersionUID = 1L;
	private String busiCode;		// 系统代码
	private String busiName;		// 系统名称
	private String userCode;		// 用户代码
	private String userName;		// 用户名
	private String userPass;		// 用户密码
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'

	
	public CtlBusiUser() {
		super();
	}

	public CtlBusiUser(String id){
		super(id);
	}

	@Length(min=0, max=60, message="系统代码长度必须介于 0 和 60 之间")
	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}
	
	@Length(min=0, max=128, message="系统名称长度必须介于 0 和 128 之间")
	public String getBusiName() {
		return busiName;
	}

	public void setBusiName(String busiName) {
		this.busiName = busiName;
	}
	
	@Length(min=0, max=60, message="用户代码长度必须介于 0 和 60 之间")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	@Length(min=0, max=64, message="用户名长度必须介于 0 和 64 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=64, message="用户密码长度必须介于 0 和 64 之间")
	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	

}
package com.sgai.property.ctl.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

/**
 * 用户模块关系Entity
 * @author admin
 * @version 2018-03-28
 */
public class CtlUserModu extends BoEntity<CtlUserModu> {
	
	private static final long serialVersionUID = 1L;
	private String userCode;		// user_code
	
	public CtlUserModu() {
		super();
	}

	public CtlUserModu(String id){
		super(id);
	}

	@Length(min=1, max=30, message="user_code长度必须介于 1 和 30 之间")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
}
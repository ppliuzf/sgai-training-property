package com.sgai.property.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.szx.core.jwt.bean.UserSubject;

public class AdminUserSubject implements UserSubject {

	private Long userId;

	private String sid;

	private String userName;

	private String userType;

	private Long employeeId;

	@JSONField(serialize=false)
	private String token;

	public Long getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserType() {
		return userType;
	}

	public String toJsonString() {
		return JSON.toJSONString(this);
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSid() {
		return sid;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

}

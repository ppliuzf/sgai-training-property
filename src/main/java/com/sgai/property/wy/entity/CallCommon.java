
package com.sgai.property.wy.entity;

import com.sgai.common.persistence.BoEntity;

/**
 * @ClassName: callCommon
 * (指定人信息)
 * @author cui
 * @date 2018年1月31日
 * @Company 首自信--智慧城市创新中心
 */

public class CallCommon extends BoEntity<CallCommon> {
	private static final long serialVersionUID = 1L;
	private String userName; // 用户名
	private String realName; // 真实姓名
	private String phone; // 电话
	private String userCode;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
}


package com.sgai.property.ctl.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.sgai.common.persistence.BoEntity;

/**
 * 用户IP管理Entity
 * @author liushang
 * @version 2017-11-09
 */
public class CtlUserIp extends BoEntity<CtlUserIp> {
	
	private static final long serialVersionUID = 1L;
	private String userCode;		// 用户代码
	private String ipAddress;		// IP地址
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	private String remark;		// 备注
	
	/*自定义属性*/
	private String comCode; // 用户所在机构代码 
	private String userName; //联合查询获取的用户名
	public CtlUserIp() {
		super();
	}

	public CtlUserIp(String id){
		super(id);
	}

	@Length(min=1, max=60, message="user_code长度必须介于 1 和 60 之间")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	@Length(min=1, max=15, message="ip_address长度必须介于 1 和 15 之间")
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	//@选项为:'Y':是'N':否默认为'Y'长度必须介于 1 和 1 之间")
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
	@Length(min=0, max=60, message="remark长度必须介于 0 和 60 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
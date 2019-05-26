package com.sgai.property.ctl.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

/**
 * 在线用户管理Entity
 * @author guanze
 * @version 2017-11-09
 */
public class CtlLog extends BoEntity<CtlLog> {
	
	private static final long serialVersionUID = 1L;
	private String sessionId;		// 会话代码session_id
	private String comCode;		// 登陆机构代码com_code
	private String comName;		// 机构名称com_name
	private String userCode;		// 登陆用户代码user_code
	private String userName;		// 用户名称user_name
	private String userType;		// 用户类型user_type
	private String corrCode;		// 用户|角色对应代码corr_code
	private String corrName;		// 用户|角色对应名称corr_name
	private Date loginTime;		// 登陆时间login_time
	private String loginIp;		// 登陆IP login_ip
	private String onlineFlag;		// 在线标识online_flag
	private Long onlineTime;		// 在线时间（按分钟存）
	private Date logoutTime;		// 登出时间logout_time
	private Integer pageNo = 0;
	private Integer pageSize = 10;
	
	public CtlLog() {
		super();
	}

	public CtlLog(String id){
		super(id);
	}

	@Length(min=1, max=60, message="会话代码session_id长度必须介于 1 和 60 之间")
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	@Length(min=1, max=10, message="登陆机构代码com_code长度必须介于 1 和 10 之间")
	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	
	@Length(min=0, max=60, message="机构名称com_name长度必须介于 0 和 60 之间")
	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}
	
	@Length(min=1, max=30, message="登陆用户代码user_code长度必须介于 1 和 30 之间")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	@Length(min=0, max=60, message="用户名称user_name长度必须介于 0 和 60 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=1, max=1, message="用户类型user_type长度必须介于 1 和 1 之间")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	@Length(min=0, max=60, message="&lsquo;用户|角色&rsquo;对应代码corr_code长度必须介于 0 和 60 之间")
	public String getCorrCode() {
		return corrCode;
	}

	public void setCorrCode(String corrCode) {
		this.corrCode = corrCode;
	}
	
	@Length(min=0, max=60, message="&lsquo;用户|角色&rsquo;对应名称corr_name长度必须介于 0 和 60 之间")
	public String getCorrName() {
		return corrName;
	}

	public void setCorrName(String corrName) {
		this.corrName = corrName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="登陆时间login_time不能为空")
	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	@Length(min=1, max=15, message="登陆IP login_ip长度必须介于 1 和 15 之间")
	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	
	@Length(min=1, max=1, message="在线标识online_flag长度必须介于 1 和 1 之间")
	public String getOnlineFlag() {
		return onlineFlag;
	}

	public void setOnlineFlag(String onlineFlag) {
		this.onlineFlag = onlineFlag;
	}
	
	public Long getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Long onlineTime) {
		this.onlineTime = onlineTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}
	

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
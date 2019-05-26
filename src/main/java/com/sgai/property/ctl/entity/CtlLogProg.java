package com.sgai.property.ctl.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;
import com.sgai.common.utils.excel.annotation.ExcelField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/** 
* ClassName: CtlLogProg  
* Description: (这里用一句话描述这个类的作用)
* @author admin  
* Date 2017年11月18日  
* Company 首自信--智慧城市创新中心
*/  
@ApiModel(value="操作日志" , description="操作日志对象")
public class CtlLogProg extends BoEntity<CtlLogProg> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "主键")
	private String sessionId;		// session_id
	@ApiModelProperty(value = "机构编码")
	private String comCode;		// com_id
	@ApiModelProperty(value = "机构名称")
	private String comName;		// com_name
	@ApiModelProperty(value = "用户编码")
	private String userCode;		// user_id
	@ApiModelProperty(value = "用户名称")
	private String userName;		// user_name
	@ApiModelProperty(value = "用户类型")
	private String userType;		// user_type
	@ApiModelProperty(value = "请求ip")
	private String remoteAddr;		// ip地址
	@ApiModelProperty(value = "请求url")
	private String requestUrl;   //请求url
	@ApiModelProperty(value = "请求方式")
	private String requestType;  //请求方式
	@ApiModelProperty(value = "处理方法")
	private String classMethod ;  //处理方法
	@ApiModelProperty(value = "runTime")
	private String runTime;		// run_time
	@ApiModelProperty(value = "corrName")
	private String beginCreatedDt;		// 开始 创建日期
	private String endCreatedDt;		// 结束 创建日期
	
	
	
	public CtlLogProg() {
		super();
	}

	public CtlLogProg(String id){
		super(id);
	}

	@ExcelField(title="会话编码", type=1, align=2, sort=1)
	@Length(min=1, max=60, message="session_id长度必须介于 1 和 60 之间")
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	@ExcelField(title="机构编码", type=1, align=2, sort=2)
	@Length(min=1, max=10, message="com_id长度必须介于 1 和 10 之间")
	public String getComCode() {
		return comCode;
	}

	public void setComId(String comCode) {
		this.comCode = comCode;
	}
	
	
	@Length(min=0, max=60, message="com_name长度必须介于 0 和 60 之间")
	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}
	
	@ExcelField(title="登录账户", type=1, align=2, sort=3)
	@Length(min=0, max=30, message="user_id长度必须介于 0 和 30 之间")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	@ExcelField(title="用户名称", type=1, align=2, sort=4)
	@Length(min=0, max=60, message="user_name长度必须介于 0 和 60 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=1, max=1, message="user_type长度必须介于 1 和 1 之间")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	@ExcelField(title="请求ip", type=1, align=2, sort=5)
	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	@ExcelField(title="请求路径", type=1, align=2, sort=6)
	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	
	@ExcelField(title="请求方式", type=1, align=2, sort=7)
	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	@ExcelField(title="处理方法", type=1, align=2, sort=8)
	public String getClassMethod() {
		return classMethod;
	}

	public void setClassMethod(String classMethod) {
		this.classMethod = classMethod;
	}

	@ExcelField(title="运行时间", type=1, align=2, sort=9)
	public String getRunTime() {
		return runTime;
	}

	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	
	public String getBeginCreatedDt() {
		return beginCreatedDt;
	}

	public void setBeginCreatedDt(String beginCreatedDt) {
		this.beginCreatedDt = beginCreatedDt;
	}
	
	public String getEndCreatedDt() {
		return endCreatedDt;
	}

	public void setEndCreatedDt(String endCreatedDt) {
		this.endCreatedDt = endCreatedDt;
	}
		
}
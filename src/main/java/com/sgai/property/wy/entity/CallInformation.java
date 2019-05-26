
/**    
* @Title: CallInformation.java  
* @Package com.sgai.property.wy.entity
* (用一句话描述该文件做什么)
* @author cui  
* @date 2018年1月29日  
* @Company 首自信--智慧城市创新中心
* @version V1.0    
*/

package com.sgai.property.wy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName: CallInformation
 * @Description: 来电登记相关信息
 * @author cui
 * @date 2018年1月29日
 * @Company 首自信--智慧城市创新中心
 */

public class CallInformation extends BoEntity<CallInformation> {

	private static final long serialVersionUID = 1L;

	private String caller; // 来电人

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date callDate; // 录入日期

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date appointDate; // 指定日期

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date dealDate; // 处理日期

	private String callPhone; // 电话

	private String callAddress; // 报修地址

	private String department; // 部门

	private String operatorId; // 处理人

	private String operatorName;

	private String loginId; // 录入人

	private String callDescription; // 来电描述

	private String dealDescription; // 处理描述

	private String appointStatus; // 0:未指定 1已指定

	private String dealStatus; // 0 未处理 1已处理

	private String areaId; // 区域

	private Date beginTime;

	private Date endTime;
	private Date beginTimeDeal;

	private Date endTimeDeal;

	private String callNowToday;// 首页跳转查询标识

	private String type; // deal 处理请求

	private String repairAddress; // 区域地址

	private String repairAddressCode; // 区域地址

	private String serialNumber; // 流水号

	private String comCode; // 机构代码

	private String deptCode; // 部门代码

	private String cho;// 1是从首页跳转过来

	public Date getBeginTimeDeal() {
		return beginTimeDeal;
	}

	public void setBeginTimeDeal(Date beginTimeDeal) {
		this.beginTimeDeal = beginTimeDeal;
	}

	public Date getEndTimeDeal() {
		return endTimeDeal;
	}

	public void setEndTimeDeal(Date endTimeDeal) {
		this.endTimeDeal = endTimeDeal;
	}

	public String getCho() {
		return cho;
	}

	public void setCho(String cho) {
		this.cho = cho;
	}

	public String getCallNowToday() {
		return callNowToday;
	}

	public void setCallNowToday(String callNowToday) {
		this.callNowToday = callNowToday;
	}

	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getRepairAddress() {
		return repairAddress;
	}

	public void setRepairAddress(String repairAddress) {
		this.repairAddress = repairAddress;
	}

	public String getRepairAddressCode() {
		return repairAddressCode;
	}

	public void setRepairAddressCode(String repairAddressCode) {
		this.repairAddressCode = repairAddressCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public Date getAppointDate() {
		return appointDate;
	}

	public void setAppointDate(Date appointDate) {
		this.appointDate = appointDate;
	}

	public Date getDealDate() {
		return dealDate;
	}

	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(String dealStatus) {
		this.dealStatus = dealStatus;
	}

	public String getAppointStatus() {
		return appointStatus;
	}

	public void setAppointStatus(String appointStatus) {
		this.appointStatus = appointStatus;
	}

	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}

	public Date getCallDate() {
		return callDate;
	}

	public void setCallDate(Date callDate) {
		this.callDate = callDate;
	}

	public String getCallPhone() {
		return callPhone;
	}

	public void setCallPhone(String callPhone) {
		this.callPhone = callPhone;
	}

	public String getCallAddress() {
		return callAddress;
	}

	public void setCallAddress(String callAddress) {
		this.callAddress = callAddress;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getCallDescription() {
		return callDescription;
	}

	public void setCallDescription(String callDescription) {
		this.callDescription = callDescription;
	}

	public String getDealDescription() {
		return dealDescription;
	}

	public void setDealDescription(String dealDescription) {
		this.dealDescription = dealDescription;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
}

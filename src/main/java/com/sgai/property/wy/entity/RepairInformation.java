
/**
 * @Title: RepairInformation.java
 * @Package com.sgai.modules.repair.entity
 * (用一句话描述该文件做什么)
 * @author XJ9001
 * @date 2018年1月20日
 * @Company 首自信--智慧城市创新中心
 * @version V1.0
 */

package com.sgai.property.wy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;
import com.sgai.modules.login.support.UserServletContext;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: RepairInformation
 * @Description: 报修信息
 * @author XJ9001
 * @date 2018年1月20日
 * @Company 首自信--智慧城市创新中心
 */

public class RepairInformation extends BoEntity<RepairInformation> {

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	private static final long serialVersionUID = 1L;

	private String orderNumber; // 订单号

	private String repairPeopleId; // 报修人ID

	private String repairPeopleName; // 报修人姓名

	private String repairPhone; // 报修电话

	private String repairAddress; // 报修地址

	private String detailAddress; // 详细地址

	private String incidentRank; // 事件级别

	private String emergenciesType; // 紧急事件分类

	private String repairType; // 报修类型

	private String repairTypeCode; // 报修类型code

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date appointmentTime; // 预约时间从
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date appointmentTimeGo; // 预约时间到

	private String repairEquipment; // 保修设备

	private String faultDescription; // 故障描述

	private String repairStatus; // 报修状态

	private String repairDetailAddress; // 报修详细地址

	private String maintainPersonId; // 维修人ID

	private String maintainPerson; // 维修人姓名

	private String maintainPersonTelepno; // 维修人电话
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date maintainShowUpDate; // 到场时间

	private String acceptPerson; // 受理人

	private Double consumableMoney; // 耗材费用

	private Double maintainMoney; // 维修费用

	private String repairAddressCode; // 报修地址Code

	private String remark; // 备注

	private String cause; // 原因(无)

	private String appraiseNorm; // 评价标准(无)
	private String complainId;// 是否投诉，投诉

	private String repairEquipmentIds; // 报修设备(无)
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date planMaintainDate; // 计划维修时间

	private Date appointDate; // 指派时间

	private String incidentSource; // 事件来源 1:电话报修

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date repairNowToday; // 今日报修 （只做查询用）

	private List<RepairRecord> repairRecords;
	private String loginUserCode;
	private String count;
	private String pictNames; // 图片名称
	private String buildName; // 楼宇名称

	private Date startTime;
	private Date endTime;

	public String getBuildName() {
		return buildName;
	}

	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	public String getPictNames() {
		return pictNames;
	}

	public void setPictNames(String pictNames) {
		this.pictNames = pictNames;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getRepairPeopleId() {
		return repairPeopleId;
	}

	public void setRepairPeopleId(String repairPeopleId) {
		this.repairPeopleId = repairPeopleId;
	}

	public String getRepairPeopleName() {
		return repairPeopleName;
	}

	public void setRepairPeopleName(String repairPeopleName) {
		this.repairPeopleName = repairPeopleName;
	}

	public String getRepairPhone() {
		return repairPhone;
	}

	public void setRepairPhone(String repairPhone) {
		this.repairPhone = repairPhone;
	}

	public String getRepairAddress() {
		return repairAddress;
	}

	public void setRepairAddress(String repairAddress) {
		this.repairAddress = repairAddress;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getIncidentRank() {
		return incidentRank;
	}

	public void setIncidentRank(String incidentRank) {
		this.incidentRank = incidentRank;
	}

	public String getEmergenciesType() {
		return emergenciesType;
	}

	public void setEmergenciesType(String emergenciesType) {
		this.emergenciesType = emergenciesType;
	}

	public String getRepairType() {
		return repairType;
	}

	public void setRepairType(String repairType) {
		this.repairType = repairType;
	}

	public Date getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getRepairEquipment() {
		return repairEquipment;
	}

	public void setRepairEquipment(String repairEquipment) {
		this.repairEquipment = repairEquipment;
	}

	public String getFaultDescription() {
		return faultDescription;
	}

	public void setFaultDescription(String faultDescription) {
		this.faultDescription = faultDescription;
	}

	public String getRepairStatus() {
		return repairStatus;
	}

	public void setRepairStatus(String repairStatus) {
		this.repairStatus = repairStatus;
	}

	public String getRepairDetailAddress() {
		return repairDetailAddress;
	}

	public void setRepairDetailAddress(String repairDetailAddress) {
		this.repairDetailAddress = repairDetailAddress;
	}

	public String getMaintainPerson() {
		return maintainPerson;
	}

	public void setMaintainPerson(String maintainPerson) {
		this.maintainPerson = maintainPerson;
	}

	public Double getConsumableMoney() {
		return consumableMoney;
	}

	public void setConsumableMoney(Double consumableMoney) {
		this.consumableMoney = consumableMoney;
	}

	public Double getMaintainMoney() {
		return maintainMoney;
	}

	public void setMaintainMoney(Double maintainMoney) {
		this.maintainMoney = maintainMoney;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getAppraiseNorm() {
		return appraiseNorm;
	}

	public void setAppraiseNorm(String appraiseNorm) {
		this.appraiseNorm = appraiseNorm;
	}

	public String getAcceptPerson() {
		return acceptPerson;
	}

	public void setAcceptPerson(String acceptPerson) {
		this.acceptPerson = acceptPerson;
	}

	public String getRepairEquipmentIds() {
		return repairEquipmentIds;
	}

	public void setRepairEquipmentIds(String repairEquipmentIds) {
		this.repairEquipmentIds = repairEquipmentIds;
	}

	public String getRepairAddressCode() {
		return repairAddressCode;
	}

	public void setRepairAddressCode(String repairAddressCode) {
		this.repairAddressCode = repairAddressCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMaintainPersonId() {
		return maintainPersonId;
	}

	public void setMaintainPersonId(String maintainPersonId) {
		this.maintainPersonId = maintainPersonId;
	}

	public Date getPlanMaintainDate() {
		return planMaintainDate;
	}

	public void setPlanMaintainDate(Date planMaintainDate) {
		this.planMaintainDate = planMaintainDate;
	}

	public Date getAppointDate() {
		return appointDate;
	}

	public void setAppointDate(Date appointDate) {
		this.appointDate = appointDate;
	}

	public String getIncidentSource() {
		return incidentSource;
	}

	public void setIncidentSource(String incidentSource) {
		this.incidentSource = incidentSource;
	}

	public Date getAppointmentTimeGo() {
		return appointmentTimeGo;
	}

	public void setAppointmentTimeGo(Date appointmentTimeGo) {
		this.appointmentTimeGo = appointmentTimeGo;
	}

	public Date getRepairNowToday() {
		return repairNowToday;
	}

	public void setRepairNowToday(Date repairNowToday) {
		this.repairNowToday = repairNowToday;
	}

	public String getComplainId() {
		return complainId;
	}

	public void setComplainId(String complainId) {
		this.complainId = complainId;
	}

	public String getRepairTypeCode() {
		return repairTypeCode;
	}

	public void setRepairTypeCode(String repairTypeCode) {
		this.repairTypeCode = repairTypeCode;
	}

	public String getMaintainPersonTelepno() {
		return maintainPersonTelepno;
	}

	public void setMaintainPersonTelepno(String maintainPersonTelepno) {
		this.maintainPersonTelepno = maintainPersonTelepno;
	}

	public Date getMaintainShowUpDate() {
		return maintainShowUpDate;
	}

	public void setMaintainShowUpDate(Date maintainShowUpDate) {
		this.maintainShowUpDate = maintainShowUpDate;
	}

	public List<RepairRecord> getRepairRecords() {
		return repairRecords;
	}

	public void setRepairRecords(List<RepairRecord> repairRecords) {
		this.repairRecords = repairRecords;
	}

	public String getLoginUserCode() {
		return loginUserCode;
	}

	public void setLoginUserCode(String loginUserCode) {
		this.loginUserCode = loginUserCode;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
}

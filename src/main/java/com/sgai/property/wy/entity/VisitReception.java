package com.sgai.property.wy.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/*
    * @ClassName: VisitReception  
    *
    * @author LiuXiaobing  
    * @date 2018年2月1日  
    * @Company 首自信--智慧城市创新中心
*/

public class VisitReception extends BoEntity<VisitReception> {
	  
	private static final long serialVersionUID = 1L;
	
	// 参观接待模块Entity对应的数据库字段信息
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date appTime;// 预约时间
	
	private String appPerson;// 预约人
	
	private String appPhone;// 预约电话
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date receptionTime;// 接待时间
	
	private String receptionAddress;// 接待地点
	
	private String visitUnit;// 参观单位
	
	private String visitNumber;// 参观人数
	
	private String leaderPhone;// 领队电话
	
	private String visitLevel;// 参观级别
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date appVisitTime;// 预约参观时间
	
	private String visitArea;// 参观区域
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date overTime;// 结束时间
	
	private String visitingTime;// 参观用时
	
	private String display; // 逻辑删除(Y/N)
	
	private String remarks;// 备注

	public Date getAppTime() {
		return appTime;
	}

	public void setAppTime(Date appTime) {
		this.appTime = appTime;
	}

	public String getAppPerson() {
		return appPerson;
	}

	public void setAppPerson(String appPerson) {
		this.appPerson = appPerson;
	}

	public String getAppPhone() {
		return appPhone;
	}

	public void setAppPhone(String appPhone) {
		this.appPhone = appPhone;
	}

	public Date getReceptionTime() {
		return receptionTime;
	}

	public void setReceptionTime(Date receptionTime) {
		this.receptionTime = receptionTime;
	}

	public String getReceptionAddress() {
		return receptionAddress;
	}

	public void setReceptionAddress(String receptionAddress) {
		this.receptionAddress = receptionAddress;
	}

	public String getVisitUnit() {
		return visitUnit;
	}

	public void setVisitUnit(String visitUnit) {
		this.visitUnit = visitUnit;
	}

	public String getVisitNumber() {
		return visitNumber;
	}

	public void setVisitNumber(String visitNumber) {
		this.visitNumber = visitNumber;
	}

	public String getLeaderPhone() {
		return leaderPhone;
	}

	public void setLeaderPhone(String leaderPhone) {
		this.leaderPhone = leaderPhone;
	}

	public String getVisitLevel() {
		return visitLevel;
	}

	public void setVisitLevel(String visitLevel) {
		this.visitLevel = visitLevel;
	}

	public Date getAppVisitTime() {
		return appVisitTime;
	}

	public void setAppVisitTime(Date appVisitTime) {
		this.appVisitTime = appVisitTime;
	}

	public String getVisitArea() {
		return visitArea;
	}

	public void setVisitArea(String visitArea) {
		this.visitArea = visitArea;
	}

	public Date getOverTime() {
		return overTime;
	}

	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}

	public String getVisitingTime() {
		return visitingTime;
	}

	public void setVisitingTime(String visitingTime) {
		this.visitingTime = visitingTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

}

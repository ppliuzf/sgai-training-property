package com.sgai.property.wy.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/*
    * @ClassName: Visitor  
    *
    * @author LiuXiaobing  
    * @date 2018年1月20日  
    * @Company 首自信--智慧城市创新中心
*/

public class Visitor extends BoEntity<Visitor> {
	  
	private static final long serialVersionUID = 1L;
	
	// 访客成员对应的数据库访客表信息
	
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date visitorDate;//当天访问日期
	
	private String visitorName;//访客姓名
	
	private String visitorType;//访客分类
	
	private String idCard;//证件号
	
	private String comName;//单位
	
	private String phone;//联系电话
	
	private String personNum;//访问人数
	
	private String visitorMeet;//是否有预约
	
	private String deptName;//访问部门
	
	private String toerPersons;//被访问人姓名
	
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date beginTime;//进入时间
	
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date endTime;//离开时间
	
	private String display;// 逻辑删除(Y/N)
	
	private String comCode; // 机构代码
	
	private String deptCode; // 部门代码
	
	private String remarks;//备注

	public Date getVisitorDate() {
		return visitorDate;
	}

	public void setVisitorDate(Date visitorDate) {
		this.visitorDate = visitorDate;
	}

	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	public String getVisitorType() {
		return visitorType;
	}

	public void setVisitorType(String visitorType) {
		this.visitorType = visitorType;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPersonNum() {
		return personNum;
	}

	public void setPersonNum(String personNum) {
		this.personNum = personNum;
	}

	public String getVisitorMeet() {
		return visitorMeet;
	}

	public void setVisitorMeet(String visitorMeet) {
		this.visitorMeet = visitorMeet;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getToerPersons() {
		return toerPersons;
	}

	public void setToerPersons(String toerPersons) {
		this.toerPersons = toerPersons;
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

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	
}

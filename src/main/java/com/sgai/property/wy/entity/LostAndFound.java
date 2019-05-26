package com.sgai.property.wy.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/*
    * @ClassName: LostAndFound  
    *
    * @author LiuXiaobing  
    * @date 2018年1月28日  
    * @Company 首自信--智慧城市创新中心
*/

public class LostAndFound extends BoEntity<LostAndFound> {
	  
	private static final long serialVersionUID = 1L;
	
	// 数据库表对应的属性成员字段
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date pickupTime; // 拾物时间
	
	private String pickupPerson; // 拾取人
	
	private String articels; // 物品名称
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date drawTime; // 领取时间
	
	private String handOver; // 交接人姓名
	
	private String leadPerson; // 代领人姓名
	
	private String phone; // 电话
	
	private String loserName; // 失主姓名
	
	private String display; // 逻辑删除(Y/N)
	
	private String remarks;	// 备注信息


	public Date getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(Date pickupTime) {
		this.pickupTime = pickupTime;
	}

	public String getPickupPerson() {
		return pickupPerson;
	}

	public void setPickupPerson(String pickupPerson) {
		this.pickupPerson = pickupPerson;
	}

	public String getArticels() {
		return articels;
	}

	public void setArticels(String articels) {
		this.articels = articels;
	}

	public Date getDrawTime() {
		return drawTime;
	}

	public void setDrawTime(Date drawTime) {
		this.drawTime = drawTime;
	}

	public String getHandOver() {
		return handOver;
	}

	public void setHandOver(String handOver) {
		this.handOver = handOver;
	}

	public String getLeadPerson() {
		return leadPerson;
	}

	public void setLeadPerson(String leadPerson) {
		this.leadPerson = leadPerson;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLoserName() {
		return loserName;
	}

	public void setLoserName(String loserName) {
		this.loserName = loserName;
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

}

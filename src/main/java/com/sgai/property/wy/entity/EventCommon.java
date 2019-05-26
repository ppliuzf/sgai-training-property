
package com.sgai.property.wy.entity;

import com.sgai.common.persistence.BoEntity;

import java.util.Date;

/**
 * @ClassName: EventCommon
 * (首页待处理事件实体)
 * @author cui
 * @date 2018年2月6日
 * @Company 首自信--智慧城市创新中心
 */

public class EventCommon extends BoEntity<EventCommon> {

	private static final long serialVersionUID = 1L;
	private Integer sum; // 事件总数
	private String type; // 事件类型
	private String repairStatus; // 事件类型
	private String publishNotice; // 发布公告
	private String notcieWait; // 待审核
	private String ownOtice; // 收到的公告
	private Date date;// 今日时间
	private String user;
	private String spaceCode;		// node_code
	private String spaceName;		// 楼宇
	private String status;		//状态
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSpaceCode() {
		return spaceCode;
	}
	public void setSpaceCode(String spaceCode) {
		this.spaceCode = spaceCode;
	}
	public String getSpaceName() {
		return spaceName;
	}
	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}
	public String getPublishNotice() {
		return publishNotice;
	}
	public void setPublishNotice(String publishNotice) {
		this.publishNotice = publishNotice;
	}
	public String getNotcieWait() {
		return notcieWait;
	}
	public void setNotcieWait(String notcieWait) {
		this.notcieWait = notcieWait;
	}
	public String getOwnOtice() {
		return ownOtice;
	}
	public void setOwnOtice(String ownOtice) {
		this.ownOtice = ownOtice;
	}
	public Integer getSum() {
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getRepairStatus() {
		return repairStatus;
	}
	public void setRepairStatus(String repairStatus) {
		this.repairStatus = repairStatus;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

}

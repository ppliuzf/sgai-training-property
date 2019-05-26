package com.sgai.property.meeting.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class MeetingsListDto implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 8185898469047380193L;
	@ApiModelProperty(value = "主键")
    private String miId; //主键
	@ApiModelProperty(value = "会议主题")
	private String miMtSubject; // 会议主题
	@ApiModelProperty(value = "会议开始时间")
	private Integer itrMtBeginTime; // 会议开始时间
	@ApiModelProperty(value = "会议结束时间")
	private Integer itrMtEndTime; // 会议结束时间
	@ApiModelProperty(value = "主持人")
	private String compereEiName; // 主持人
	@ApiModelProperty(value = "1 未开始 2执行中 3已结束 4已逾期5已取消")
	private Integer miStatus; // 1 未开始 2执行中 3已结束 4已逾期5已取消
	@ApiModelProperty(value = "1 未开始 2执行中 3已结束 4已逾期5已取消")
	private String miStatusCn; // 1 未开始 2执行中 3已结束 4已逾期5已取消
	@ApiModelProperty(value = "创建人ID")
	private Long createEiId; // 创建人ID
	@ApiModelProperty(value = "创建人")
	private String createEiName; // 创建人
	@ApiModelProperty(value = "位置名称")
	private String rpPositionName; // 位置名称
	@ApiModelProperty(value = "会议室名称")
	private String roomName; // 会议室名称
	@ApiModelProperty(value = "当前人是否是创建人")
	private Integer isCreateUser; // 当前人是否是创建人
	
	
	public Integer getIsCreateUser() {
		return isCreateUser;
	}

	public void setIsCreateUser(Integer isCreateUser) {
		this.isCreateUser = isCreateUser;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getMiId() {
		return miId;
	}

	public void setMiId(String miId) {
		this.miId = miId;
	}

	public String getMiMtSubject() {
		return miMtSubject;
	}

	public void setMiMtSubject(String miMtSubject) {
		this.miMtSubject = miMtSubject;
	}

	public Integer getItrMtBeginTime() {
		return itrMtBeginTime;
	}

	public void setItrMtBeginTime(Integer itrMtBeginTime) {
		this.itrMtBeginTime = itrMtBeginTime;
	}

	public Integer getItrMtEndTime() {
		return itrMtEndTime;
	}

	public void setItrMtEndTime(Integer itrMtEndTime) {
		this.itrMtEndTime = itrMtEndTime;
	}

	public String getCompereEiName() {
		return compereEiName;
	}

	public void setCompereEiName(String compereEiName) {
		this.compereEiName = compereEiName;
	}

	public Integer getMiStatus() {
		return miStatus;
	}

	public void setMiStatus(Integer miStatus) {
		this.miStatus = miStatus;
	}

	public String getMiStatusCn() {
		return miStatusCn;
	}

	public void setMiStatusCn(String miStatusCn) {
		this.miStatusCn = miStatusCn;
	}

	public Long getCreateEiId() {
		return createEiId;
	}

	public void setCreateEiId(Long createEiId) {
		this.createEiId = createEiId;
	}

	public String getCreateEiName() {
		return createEiName;
	}

	public void setCreateEiName(String createEiName) {
		this.createEiName = createEiName;
	}

	public String getRpPositionName() {
		return rpPositionName;
	}

	public void setRpPositionName(String rpPositionName) {
		this.rpPositionName = rpPositionName;
	}

	/**
	 *  简单描述该方法的实现功能（可选）.
	 * @see Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((miId == null) ? 0 : miId.hashCode());
		return result;
	}

	/**
	 *  简单描述该方法的实现功能（可选）.
	 * @see Object#equals(Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeetingsListDto other = (MeetingsListDto) obj;
		if (miId == null) {
            return other.miId == null;
		} else return miId.equals(other.miId);
    }
	
	

}

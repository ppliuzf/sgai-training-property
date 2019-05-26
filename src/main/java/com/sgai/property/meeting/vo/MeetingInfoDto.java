package com.sgai.property.meeting.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class MeetingInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4455942648007313050L;
	@ApiModelProperty(value = "主键")
    private String id; //主键
	@ApiModelProperty(value = "主键")
    private String miId; //主键
	@ApiModelProperty(value = "会议主题")
    private String miMtSubject; //会议主题
    @ApiModelProperty(value = "会议图片")
    private List<PicDto> picDtos; //会议图片
    @ApiModelProperty(value = "会议室ID")
    private String rrId; //会议室ID
    @ApiModelProperty(value = "会议室位置")
    private String rpId; //会议室位置ID
    @ApiModelProperty(value = "会议室名称")
    private String roomName; //会议室位置
    @ApiModelProperty(value = "会议室位置")
    private String rpPositionName; //会议室位置
    @ApiModelProperty(value = "会议日期")
    private Long miMtDate; //会议日期
    @ApiModelProperty(value = "会议时间段")
    private String miMtTimeSeg; //会议时间段
    @ApiModelProperty(value = "会议简要")
    private String miMtContent; //会议简要
    @ApiModelProperty(value = "1 未开始 2执行中 3已结束 4已逾期5已取消")
	private Integer miStatus; // 1 未开始 2执行中 3已结束 4已逾期5已取消
    @ApiModelProperty(value = "是否周例会1 是 0否")
    private Integer miIsWeekMeeting; //是否周例会1 是 0否
    @ApiModelProperty(value = "重复次数")
    private Integer miRepeatNum; //重复次数
    @ApiModelProperty(value = "参与人")
    private List<InviterDto> inviterDtos; //参与人
    @ApiModelProperty(value = "参会部门")
    private List<InviteDeptVo> inviteDeptVos; //参会部门
    
    @ApiModelProperty(value = "物料")
	private List<MaterielVo> materielDtoList;
    
    @ApiModelProperty(value = "是否提醒1 是 0 否")
    private Integer miIsRemind;
    @ApiModelProperty(value = "提前分钟数")
    private Integer miRemindMin;
    
    
    
	/**
	 * id.
	 *
	 * @return  the id
	 * @since   JDK 1.8
	 */
	public String getId() {
		return id;
	}
	/**
	 * id.
	 *
	 * @param   id    the id to set
	 * @since   JDK 1.8
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * miIsRemind.
	 *
	 * @return  the miIsRemind
	 * @since   JDK 1.8
	 */
	public Integer getMiIsRemind() {
		return miIsRemind;
	}
	/**
	 * miIsRemind.
	 *
	 * @param   miIsRemind    the miIsRemind to set
	 * @since   JDK 1.8
	 */
	public void setMiIsRemind(Integer miIsRemind) {
		this.miIsRemind = miIsRemind;
	}
	/**
	 * miRemindMin.
	 *
	 * @return  the miRemindMin
	 * @since   JDK 1.8
	 */
	public Integer getMiRemindMin() {
		return miRemindMin;
	}
	/**
	 * miRemindMin.
	 *
	 * @param   miRemindMin    the miRemindMin to set
	 * @since   JDK 1.8
	 */
	public void setMiRemindMin(Integer miRemindMin) {
		this.miRemindMin = miRemindMin;
	}
	/**
	 * materielDtoList.
	 *
	 * @return  the materielDtoList
	 * @since   JDK 1.8
	 */
	public List<MaterielVo> getMaterielDtoList() {
		return materielDtoList;
	}
	/**
	 * materielDtoList.
	 *
	 * @param   materielDtoList    the materielDtoList to set
	 * @since   JDK 1.8
	 */
	public void setMaterielDtoList(List<MaterielVo> materielDtoList) {
		this.materielDtoList = materielDtoList;
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
	
	public List<PicDto> getPicDtos() {
		return picDtos;
	}
	public void setPicDtos(List<PicDto> picDtos) {
		this.picDtos = picDtos;
	}
	public String getRpId() {
		return rpId;
	}
	public void setRpId(String rpId) {
		this.rpId = rpId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public List<InviterDto> getInviterDtos() {
		return inviterDtos;
	}
	public void setInviterDtos(List<InviterDto> inviterDtos) {
		this.inviterDtos = inviterDtos;
	}
	public String getRrId() {
		return rrId;
	}
	public void setRrId(String rrId) {
		this.rrId = rrId;
	}
	public String getRpPositionName() {
		return rpPositionName;
	}
	public void setRpPositionName(String rpPositionName) {
		this.rpPositionName = rpPositionName;
	}
	public Long getMiMtDate() {
		return miMtDate;
	}
	public void setMiMtDate(Long miMtDate) {
		this.miMtDate = miMtDate;
	}
	public String getMiMtTimeSeg() {
		return miMtTimeSeg;
	}
	public void setMiMtTimeSeg(String miMtTimeSeg) {
		this.miMtTimeSeg = miMtTimeSeg;
	}
	public String getMiMtContent() {
		return miMtContent;
	}
	public void setMiMtContent(String miMtContent) {
		this.miMtContent = miMtContent;
	}
	public Integer getMiIsWeekMeeting() {
		return miIsWeekMeeting;
	}
	public void setMiIsWeekMeeting(Integer miIsWeekMeeting) {
		this.miIsWeekMeeting = miIsWeekMeeting;
	}
	public Integer getMiRepeatNum() {
		return miRepeatNum;
	}
	public void setMiRepeatNum(Integer miRepeatNum) {
		this.miRepeatNum = miRepeatNum;
	}
	
	public Integer getMiStatus() {
		return miStatus;
	}
	public void setMiStatus(Integer miStatus) {
		this.miStatus = miStatus;
	}
	 
	public List<InviteDeptVo> getInviteDeptVos() {
		return inviteDeptVos;
	}
	public void setInviteDeptVos(List<InviteDeptVo> inviteDeptVos) {
		this.inviteDeptVos = inviteDeptVos;
	}
    
    
}

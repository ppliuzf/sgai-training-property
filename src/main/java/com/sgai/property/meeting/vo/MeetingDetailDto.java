package com.sgai.property.meeting.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class MeetingDetailDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4455942648007313050L;

	@ApiModelProperty(value = "主键")
    private String miId; //主键
	@ApiModelProperty(value = "会议主题")
    private String miMtSubject; //会议主题
    @ApiModelProperty(value = "会议图片")
    private List<PicDto> picDtos; //会议图片
    @ApiModelProperty(value = "会议室名称")
    private String roomName; //会议室名称
    @ApiModelProperty(value = "会议室位置")
    private String rpPositionName; //会议室位置
    @ApiModelProperty(value = "会议日期")
    private Long miMtDate; //会议日期
    @ApiModelProperty(value = "会议时间段")
    private String miMtTimeSeg; //会议时间段
    @ApiModelProperty(value = "会议简要")
    private String miMtContent; //会议简要
	@ApiModelProperty(value = "会议室设备")
	private String rdRoomDevice;
	@ApiModelProperty(value = "会议室容纳人数范围")
	private String rrRoomPeoples; //会议室容纳人数范围
    @ApiModelProperty(value = "1 未开始 2执行中 3已结束 4已逾期5已取消")
	private Integer miStatus; // 1 未开始 2执行中 3已结束 4已逾期5已取消
    @ApiModelProperty(value = "主持人")
    private String compereEiName; //主持人
    @ApiModelProperty(value = "主持人头像")
    private String compereUrl; //主持人头像
    @ApiModelProperty(value = "主持人公司职位")
    private String compereComDept; //主持人公司职位
    @ApiModelProperty(value = "创建人")
    private String createEiName; //创建人
    @ApiModelProperty(value = "创建人是否参加")
    private Integer isInvite; //创建人是否参加
    @ApiModelProperty(value = "当前用户是否是创建人")
    private Integer isCreateUser; //当前用户是否是创建人
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "参与人总数")
    private Long invitersSum; //参与人总数
    @ApiModelProperty(value = "参与人")
    private List<InviterDto> inviters; //创建时间
    @ApiModelProperty(value = "会议纪要")
    private List<SummaryDto> summaryDtos; //会议纪要
	@ApiModelProperty(value = "物料")
	private List<MaterielVo> materielDtoList;
	
	@ApiModelProperty(value = "是否提醒1 是 0 否")
    private Integer miIsRemind;
    @ApiModelProperty(value = "提前分钟数")
    private Integer miRemindMin;

    @ApiModelProperty(value = "是否是管理员 1 是 0 否")
    private Integer isAdmin;
    @ApiModelProperty(value = "是否是参会人 1 是 0 否")
    private Integer isInviter;
    
    @ApiModelProperty(value = "会议室设备详情Vo")
    private List<RoomDeviceDetailVo> deviceDetailVoList;
    
    
    
	/**
	 * @return the deviceDetailVoList
	 */
	public List<RoomDeviceDetailVo> getDeviceDetailVoList() {
		return deviceDetailVoList;
	}

	/**
	 * @param deviceDetailVoList the deviceDetailVoList to set
	 */
	public void setDeviceDetailVoList(List<RoomDeviceDetailVo> deviceDetailVoList) {
		this.deviceDetailVoList = deviceDetailVoList;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * isInviter.
	 *
	 * @return  the isInviter
	 * @since   JDK 1.8
	 */
	public Integer getIsInviter() {
		return isInviter;
	}

	/**
	 * isInviter.
	 *
	 * @param   isInviter    the isInviter to set
	 * @since   JDK 1.8
	 */
	public void setIsInviter(Integer isInviter) {
		this.isInviter = isInviter;
	}

	/**
	 * isAdmin.
	 *
	 * @return  the isAdmin
	 * @since   JDK 1.8
	 */
	public Integer getIsAdmin() {
		return isAdmin;
	}

	/**
	 * isAdmin.
	 *
	 * @param   isAdmin    the isAdmin to set
	 * @since   JDK 1.8
	 */
	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
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

	public List<MaterielVo> getMaterielDtoList() {
		return materielDtoList;
	}

	public MeetingDetailDto setMaterielDtoList(List<MaterielVo> materielDtoList) {
		this.materielDtoList = materielDtoList;
		return this;
	}

	public String getRdRoomDevice() {
		return rdRoomDevice;
	}

	public MeetingDetailDto setRdRoomDevice(String rdRoomDevice) {
		this.rdRoomDevice = rdRoomDevice;
		return this;
	}

	public String getRrRoomPeoples() {
		return rrRoomPeoples;
	}

	public MeetingDetailDto setRrRoomPeoples(String rrRoomPeoples) {
		this.rrRoomPeoples = rrRoomPeoples;
		return this;
	}

	public Integer getIsCreateUser() {
		return isCreateUser;
	}
	public void setIsCreateUser(Integer isCreateUser) {
		this.isCreateUser = isCreateUser;
	}
	public Integer getIsInvite() {
		return isInvite;
	}
	public void setIsInvite(Integer isInvite) {
		this.isInvite = isInvite;
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
	 
	public String getCompereEiName() {
		return compereEiName;
	}
	public void setCompereEiName(String compereEiName) {
		this.compereEiName = compereEiName;
	}
	public String getCompereUrl() {
		return compereUrl;
	}
	public void setCompereUrl(String compereUrl) {
		this.compereUrl = compereUrl;
	}
	public String getCompereComDept() {
		return compereComDept;
	}
	public void setCompereComDept(String compereComDept) {
		this.compereComDept = compereComDept;
	}
	public String getCreateEiName() {
		return createEiName;
	}
	public void setCreateEiName(String createEiName) {
		this.createEiName = createEiName;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Long getInvitersSum() {
		return invitersSum;
	}
	public void setInvitersSum(Long invitersSum) {
		this.invitersSum = invitersSum;
	}
	public List<InviterDto> getInviters() {
		return inviters;
	}
	public void setInviters(List<InviterDto> inviters) {
		this.inviters = inviters;
	}
	
	public List<SummaryDto> getSummaryDtos() {
		return summaryDtos;
	}
	public void setSummaryDtos(List<SummaryDto> summaryDtos) {
		this.summaryDtos = summaryDtos;
	}
	public Integer getMiStatus() {
		return miStatus;
	}
	public void setMiStatus(Integer miStatus) {
		this.miStatus = miStatus;
	}
	 
	public List<PicDto> getPicDtos() {
		return picDtos;
	}
	public void setPicDtos(List<PicDto> picDtos) {
		this.picDtos = picDtos;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
    
    
}

package com.sgai.property.meeting.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 会议室管理Vo
 *
 * @author 146584
 * @create 2017-11-02 15:12
 */
public class RoomResourceVo implements Serializable {


    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "主键")
    private String rrId;

    @ApiModelProperty(value = "会议室类型ID")
    private String rtId; //会议室类型ID

    @ApiModelProperty(value = "会议室类型名称")
    private String rrRoomType; //会议室类型
    @ApiModelProperty(value = "会议室名称")
    private String rrRoomName;

    @ApiModelProperty(value = "会议室描述")
    private String rrRoomDesc; //会议室描述
    @ApiModelProperty(value = "会议室位置名称")
    private String rrRoomPosition;

    @ApiModelProperty(value = "会议室容纳人数范围")
    private String rrRoomPeoples;

    @ApiModelProperty(value = "会议室状态 1 启用 0 禁用")
    private Integer rrRoomState;

    @ApiModelProperty(value = "默认图片URL")
    private String rrRoomPicMain;

    @ApiModelProperty(value = "会议室设备详情Vo")
    private List<RoomDeviceDetailVo> deviceDetailVoList;

    @ApiModelProperty(value = "管理员id")
    private String rrAdminId;
    @ApiModelProperty(value = "管理员名称")
    private String rrAdminName;
    @ApiModelProperty(value = "管理员类型 0员工 1部门")
    private Long rrAdminType;
    @ApiModelProperty(value = "当前登录人")
    private String loginUserName;

    public String getRtId() {
        return rtId;
    }

    public void setRtId(String rtId) {
        this.rtId = rtId;
    }

    public String getRrRoomDesc() {
        return rrRoomDesc;
    }

    public void setRrRoomDesc(String rrRoomDesc) {
        this.rrRoomDesc = rrRoomDesc;
    }

    public String getRrRoomType() {
        return rrRoomType;
    }

    public void setRrRoomType(String rrRoomType) {
        this.rrRoomType = rrRoomType;
    }

    /**
	 * @return the loginUserName
	 */
	public String getLoginUserName() {
		return loginUserName;
	}

	/**
	 * @param loginUserName the loginUserName to set
	 */
	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}

	public String getRrId() {
        return rrId;
    }

    public RoomResourceVo setRrId(String rrId) {
        this.rrId = rrId;
        return this;
    }

    public String getId() {
        return id;
    }

    public RoomResourceVo setId(String id) {
        this.id = id;
        return this;
    }

    public String getRrAdminId() {
        return rrAdminId;
    }

    public RoomResourceVo setRrAdminId(String rrAdminId) {
        this.rrAdminId = rrAdminId;
        return this;
    }

    public String getRrAdminName() {
        return rrAdminName;
    }

    public RoomResourceVo setRrAdminName(String rrAdminName) {
        this.rrAdminName = rrAdminName;
        return this;
    }

    public Long getRrAdminType() {
        return rrAdminType;
    }

    public void setRrAdminType(Long rrAdminType) {
        this.rrAdminType = rrAdminType;
    }

    public String getRrRoomName() {
        return rrRoomName;
    }

    public RoomResourceVo setRrRoomName(String rrRoomName) {
        this.rrRoomName = rrRoomName;
        return this;
    }

    public String getRrRoomPosition() {
        return rrRoomPosition;
    }

    public RoomResourceVo setRrRoomPosition(String rrRoomPosition) {
        this.rrRoomPosition = rrRoomPosition;
        return this;
    }

    public String getRrRoomPeoples() {
        return rrRoomPeoples;
    }

    public RoomResourceVo setRrRoomPeoples(String rrRoomPeoples) {
        this.rrRoomPeoples = rrRoomPeoples;
        return this;
    }

    public Integer getRrRoomState() {
        return rrRoomState;
    }

    public RoomResourceVo setRrRoomState(Integer rrRoomState) {
        this.rrRoomState = rrRoomState;
        return this;
    }

    public String getRrRoomPicMain() {
        return rrRoomPicMain;
    }

    public RoomResourceVo setRrRoomPicMain(String rrRoomPicMain) {
        this.rrRoomPicMain = rrRoomPicMain;
        return this;
    }

    public List<RoomDeviceDetailVo> getDeviceDetailVoList() {
        return deviceDetailVoList;
    }

    public RoomResourceVo setDeviceDetailVoList(List<RoomDeviceDetailVo> deviceDetailVoList) {
        this.deviceDetailVoList = deviceDetailVoList;
        return this;
    }

    @Override
    public String toString() {
        return "RoomResourceVo{" +
                "rrId=" + id +
                ", rrRoomName='" + rrRoomName + '\'' +
                ", rrRoomPosition='" + rrRoomPosition + '\'' +
                ", rrRoomPeoples='" + rrRoomPeoples + '\'' +
                ", rrRoomState=" + rrRoomState +
                ", rrRoomPicMain='" + rrRoomPicMain + '\'' +
                ", rdRoomDevice='" + deviceDetailVoList + '\'' +
                '}';
    }
}

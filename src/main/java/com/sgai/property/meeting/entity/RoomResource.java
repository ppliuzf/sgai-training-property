package com.sgai.property.meeting.entity;

import com.sgai.common.persistence.BoEntity;

import io.swagger.annotations.ApiModelProperty;

public class RoomResource extends BoEntity<RoomResource>{

    @ApiModelProperty(value = "会议室名称")
    private String rrRoomName; //会议室名称
    @ApiModelProperty(value = "会议室类型ID")
    private String rtId; //会议室类型ID
    @ApiModelProperty(value = "会议室类型")
    private String rrRoomType; //会议室类型
    @ApiModelProperty(value = "会议室位置ID")
    private String rpId; //会议室位置ID
    @ApiModelProperty(value = "会议室位置名称")
    private String rrRoomPosition; //会议室位置名称
    @ApiModelProperty(value = "会议室容纳人数范围")
    private String rrRoomPeoples; //会议室容纳人数范围
    @ApiModelProperty(value = "会议室描述")
    private String rrRoomDesc; //会议室描述
    @ApiModelProperty(value = "会议室状态 1 启用 0 禁用")
    private Integer rrRoomState; //会议室状态 1 启用 0 禁用
    @ApiModelProperty(value = "默认图片URL")
    private String rrRoomPicMain; //默认图片URL
    @ApiModelProperty(value = "会议室设备")
    private String rdRoomDevice; //会议室设备
    @ApiModelProperty(value = "会议室设备ID")
    private String rdDeviceId; //会议室设备ID
    @ApiModelProperty(value = "公司ID")
    private String comId; //公司ID
    @ApiModelProperty(value = "创建时间")
    private Long createTime; //创建时间
    @ApiModelProperty(value = "更新时间")
    private Long updateTime; //更新时间
    @ApiModelProperty(value = "是否删除 1 是 0 否")
    private Integer isDelete; //是否删除 1 是 0 否
    @ApiModelProperty(value = "创建人ID")
    private String createUserId; //创建人ID
    @ApiModelProperty(value = "创建人")
    private String createUser; //创建人

    @ApiModelProperty(value = "管理员id")
    private String rrAdminId;
    @ApiModelProperty(value = "管理员名称")
    private String rrAdminName;
    @ApiModelProperty(value = "管理员类型 0员工 1部门")
    private Long rrAdminType;
    public String getRrRoomName(){
        return rrRoomName;
    }

    public String getRrAdminId() {
        return rrAdminId;
    }

    public void setRrAdminId(String rrAdminId) {
        this.rrAdminId = rrAdminId;
    }

    public String getRrAdminName() {
        return rrAdminName;
    }

    public void setRrAdminName(String rrAdminName) {
        this.rrAdminName = rrAdminName;
    }

    public Long getRrAdminType() {
        return rrAdminType;
    }

    public void setRrAdminType(Long rrAdminType) {
        this.rrAdminType = rrAdminType;
    }

    public void setRrRoomName(String rrRoomName){
        this.rrRoomName = rrRoomName;
    }
    public String getRtId(){
        return rtId;
    }

    public void setRtId(String rtId){
        this.rtId = rtId;
    }
    public String getRrRoomType(){
        return rrRoomType;
    }

    public void setRrRoomType(String rrRoomType){
        this.rrRoomType = rrRoomType;
    }
    public String getRpId(){
        return rpId;
    }

    public void setRpId(String rpId){
        this.rpId = rpId;
    }
    public String getRrRoomPosition(){
        return rrRoomPosition;
    }

    public void setRrRoomPosition(String rrRoomPosition){
        this.rrRoomPosition = rrRoomPosition;
    }
    public String getRrRoomPeoples(){
        return rrRoomPeoples;
    }

    public void setRrRoomPeoples(String rrRoomPeoples){
        this.rrRoomPeoples = rrRoomPeoples;
    }
    public String getRrRoomDesc(){
        return rrRoomDesc;
    }

    public void setRrRoomDesc(String rrRoomDesc){
        this.rrRoomDesc = rrRoomDesc;
    }
    public Integer getRrRoomState(){
        return rrRoomState;
    }

    public void setRrRoomState(Integer rrRoomState){
        this.rrRoomState = rrRoomState;
    }
    public String getRrRoomPicMain(){
        return rrRoomPicMain;
    }

    public void setRrRoomPicMain(String rrRoomPicMain){
        this.rrRoomPicMain = rrRoomPicMain;
    }
    public String getRdRoomDevice(){
        return rdRoomDevice;
    }

    public void setRdRoomDevice(String rdRoomDevice){
        this.rdRoomDevice = rdRoomDevice;
    }
    public String getRdDeviceId(){
        return rdDeviceId;
    }

    public void setRdDeviceId(String rdDeviceId){
        this.rdDeviceId = rdDeviceId;
    }
    public String getComId(){
        return comId;
    }

    public void setComId(String comId){
        this.comId = comId;
    }
    public Long getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Long createTime){
        this.createTime = createTime;
    }
    public Long getUpdateTime(){
        return updateTime;
    }

    public void setUpdateTime(Long updateTime){
        this.updateTime = updateTime;
    }
    public Integer getIsDelete(){
        return isDelete;
    }

    public void setIsDelete(Integer isDelete){
        this.isDelete = isDelete;
    }
    public String getCreateUserId(){
        return createUserId;
    }

    public void setCreateUserId(String createUserId){
        this.createUserId = createUserId;
    }
    public String getCreateUser(){
        return createUser;
    }

    public void setCreateUser(String createUser){
        this.createUser = createUser;
    }
}
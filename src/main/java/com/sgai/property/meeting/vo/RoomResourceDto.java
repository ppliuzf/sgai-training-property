package com.sgai.property.meeting.vo;

import com.sgai.property.meeting.entity.RoomResource;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 会议室dto
 *
 * @author 146584
 * @create 2017-11-02 15:29
 */
public class RoomResourceDto  implements Serializable  {

    @ApiModelProperty(value = "会议室ID")
    private String rrId; //会议室ID
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

    @ApiModelProperty(value = "设备详情入参List")
    private List<RoomDeviceDetailParam> deviceDetailParamList;


    @ApiModelProperty(value = "默认图片URL")
    private String rrRoomPicMain; //默认图片URL

    @ApiModelProperty(value = "管理员id")
    private String rrAdminId;
    @ApiModelProperty(value = "管理员名称")
    private String rrAdminName;
    @ApiModelProperty(value = "管理员类型 0员工 1部门")
    private Long rrAdminType;

    public String getRrId() {
        return rrId;
    }

    public void setRrId(String rrId) {
        this.rrId = rrId;
    }

    public List<RoomDeviceDetailParam> getDeviceDetailParamList() {
        return deviceDetailParamList;
    }

    public RoomResourceDto setDeviceDetailParamList(List<RoomDeviceDetailParam> deviceDetailParamList) {
        this.deviceDetailParamList = deviceDetailParamList;
        return this;
    }

    public String getRrRoomPicMain() {
        return rrRoomPicMain;
    }

    public RoomResourceDto setRrRoomPicMain(String rrRoomPicMain) {
        this.rrRoomPicMain = rrRoomPicMain;
        return this;
    }

    public String getRrAdminId() {
        return rrAdminId;
    }

    public RoomResourceDto setRrAdminId(String rrAdminId) {
        this.rrAdminId = rrAdminId;
        return this;
    }

    public String getRrAdminName() {
        return rrAdminName;
    }

    public RoomResourceDto setRrAdminName(String rrAdminName) {
        this.rrAdminName = rrAdminName;
        return this;
    }

    public Long getRrAdminType() {
        return rrAdminType;
    }

    public RoomResourceDto setRrAdminType(Long rrAdminType) {
        this.rrAdminType = rrAdminType;
        return this;
    }

    public String getRrRoomName() {
        return rrRoomName;
    }

    public String getRtId() {
        return rtId;
    }

    public RoomResourceDto setRtId(String rtId) {
        this.rtId = rtId;
        return this;
    }

    public RoomResourceDto setRrRoomName(String rrRoomName) {
        this.rrRoomName = rrRoomName;
        return this;
    }

    public String getRrRoomType() {
        return rrRoomType;
    }

    public RoomResourceDto setRrRoomType(String rrRoomType) {
        this.rrRoomType = rrRoomType;
        return this;
    }

    public String getRrRoomPosition() {
        return rrRoomPosition;
    }

    public RoomResourceDto setRrRoomPosition(String rrRoomPosition) {
        this.rrRoomPosition = rrRoomPosition;
        return this;
    }

    public String getRrRoomPeoples() {
        return rrRoomPeoples;
    }

    public RoomResourceDto setRrRoomPeoples(String rrRoomPeoples) {
        this.rrRoomPeoples = rrRoomPeoples;
        return this;
    }

    public String getRpId() {
        return rpId;
    }

    public RoomResourceDto setRpId(String rpId) {
        this.rpId = rpId;
        return this;
    }


    public String getRrRoomDesc() {
        return rrRoomDesc;
    }

    public RoomResourceDto setRrRoomDesc(String rrRoomDesc) {
        this.rrRoomDesc = rrRoomDesc;
        return this;
    }

}

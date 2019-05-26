package com.sgai.property.meeting.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Hou
 * @create 2018-04-18 11:32
 */
public class RoomDeviceDetailParam implements Serializable {
    @ApiModelProperty(value = "设备Id")
    private String rdId;
    @ApiModelProperty(value = "会议室设备状态(0启用,1禁用)")
    private Long rdsState;
    @ApiModelProperty(value = "会议室设备名称")
    private String rdRoomDevice;

    public String getRdId() {
        return rdId;
    }

    public RoomDeviceDetailParam setRdId(String rdId) {
        this.rdId = rdId;
        return this;
    }

    public Long getRdsState() {
        return rdsState;
    }

    public RoomDeviceDetailParam setRdsState(Long rdsState) {
        this.rdsState = rdsState;
        return this;
    }

    public String getRdRoomDevice() {
        return rdRoomDevice;
    }

    public RoomDeviceDetailParam setRdRoomDevice(String rdRoomDevice) {
        this.rdRoomDevice = rdRoomDevice;
        return this;
    }
}

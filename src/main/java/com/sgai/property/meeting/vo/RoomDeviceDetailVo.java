package com.sgai.property.meeting.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Hou
 * @create 2018-04-18 10:55
 */
public class RoomDeviceDetailVo implements Serializable {

    @ApiModelProperty(value = "设备状态Id")
    private String rdsId;
    @ApiModelProperty(value = "会议室设备状态(0启用,1禁用)")
    private Long rdsState;
    @ApiModelProperty(value = "会议室设备名称")
    private String rdRoomDevice;

    public String getRdsId() {
        return rdsId;
    }

    public RoomDeviceDetailVo setRdsId(String rdsId) {
        this.rdsId = rdsId;
        return this;
    }

    public Long getRdsState() {
        return rdsState;
    }

    public RoomDeviceDetailVo setRdsState(Long rdsState) {
        this.rdsState = rdsState;
        return this;
    }

    public String getRdRoomDevice() {
        return rdRoomDevice;
    }

    public RoomDeviceDetailVo setRdRoomDevice(String rdRoomDevice) {
        this.rdRoomDevice = rdRoomDevice;
        return this;
    }
}

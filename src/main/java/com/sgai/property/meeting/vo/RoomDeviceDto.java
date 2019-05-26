package com.sgai.property.meeting.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 设备Dto
 *
 * @author 146584
 * @date 2017-11-08 18:51
 */
public class RoomDeviceDto implements Serializable {
    @ApiModelProperty(value = "主键")
    private String rdId; //主键
    @ApiModelProperty(value = "会议室设备名称")
    private String rdRoomDevice; //会议室设备名称

    public String getRdId() {
        return rdId;
    }

    public RoomDeviceDto setRdId(String rdId) {
        this.rdId = rdId;
        return this;
    }

    public String getRdRoomDevice() {
        return rdRoomDevice;
    }

    public RoomDeviceDto setRdRoomDevice(String rdRoomDevice) {
        this.rdRoomDevice = rdRoomDevice;
        return this;
    }
}

package com.sgai.property.meeting.entity;


import io.swagger.annotations.ApiModelProperty;
import com.sgai.common.persistence.BoEntity;

public class RoomDeviceStatus extends BoEntity<RoomDeviceStatus> {

    @ApiModelProperty(value = "是否删除")
    private Long isDelete;
    @ApiModelProperty(value = "关联会议室id")
    private String rrId;
    @ApiModelProperty(value = "会议室设备ID")
    private String rdId;
    @ApiModelProperty(value = "会议室设备状态(0启用,1禁用)")
    private Long rdsState;
    @ApiModelProperty(value = "会议室设备名称")
    private String rdRoomDevice;

    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Long isDelete) {
        this.isDelete = isDelete;
    }

    public String getRrId() {
        return rrId;
    }

    public void setRrId(String rrId) {
        this.rrId = rrId;
    }

    public String getRdId() {
        return rdId;
    }

    public void setRdId(String rdId) {
        this.rdId = rdId;
    }

    public Long getRdsState() {
        return rdsState;
    }

    public void setRdsState(Long rdsState) {
        this.rdsState = rdsState;
    }

    public String getRdRoomDevice() {
        return rdRoomDevice;
    }

    public void setRdRoomDevice(String rdRoomDevice) {
        this.rdRoomDevice = rdRoomDevice;
    }
}
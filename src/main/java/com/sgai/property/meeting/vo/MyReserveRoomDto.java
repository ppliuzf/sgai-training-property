package com.sgai.property.meeting.vo;

import com.sgai.common.persistence.BoEntity;
import com.sgai.property.meeting.entity.InviteDept;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 我预定的会议室入参封装dto
 *
 * @author hou
 * @date 2017-12-26 14:27
 */
public class MyReserveRoomDto  extends BoEntity<MyReserveRoomDto> {

    @ApiModelProperty(value = "会议室id")
    private String rrId;

    @ApiModelProperty(value = "会议室名称")
    private String rrRoomName;

    @ApiModelProperty(value = "1 未开始 2进行中 3已结束 4已逾期5已取消")
    private Integer miStatus;

    @ApiModelProperty(value = "会议起始时间")
    private Long startTime;

    @ApiModelProperty(value = "会议结束时间")
    private Long endTime;

    @ApiModelProperty(value = "创建者ID")
    private String createBy;

    @ApiModelProperty(value = "创建者名称")
    private String createEiName;

    public String getCreateEiName() {
        return createEiName;
    }

    public void setCreateEiName(String createEiName) {
        this.createEiName = createEiName;
    }

    public String getCreateBy() {
        return createBy;
    }

    public MyReserveRoomDto setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public String getRrId() {
        return rrId;
    }

    public MyReserveRoomDto setRrId(String rrId) {
        this.rrId = rrId;
        return this;
    }

    public String getRrRoomName() {
        return rrRoomName;
    }

    public MyReserveRoomDto setRrRoomName(String rrRoomName) {
        this.rrRoomName = rrRoomName;
        return this;
    }

    public Integer getMiStatus() {
        return miStatus;
    }

    public MyReserveRoomDto setMiStatus(Integer miStatus) {
        this.miStatus = miStatus;
        return this;
    }

    public Long getStartTime() {
        return startTime;
    }

    public MyReserveRoomDto setStartTime(Long startTime) {
        this.startTime = startTime;
        return this;
    }

    public Long getEndTime() {
        return endTime;
    }

    public MyReserveRoomDto setEndTime(Long endTime) {
        this.endTime = endTime;
        return this;
    }
}

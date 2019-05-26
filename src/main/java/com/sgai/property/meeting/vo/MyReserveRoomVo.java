package com.sgai.property.meeting.vo;

import com.sgai.common.persistence.BoEntity;
import com.sgai.property.meeting.entity.Maininfo;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


/**
 * 我预定的会议返回Vo
 *
 * @author hou
 * @date 2017-12-26 14:34
 */
public class MyReserveRoomVo extends BoEntity<MyReserveRoomVo> {

    @ApiModelProperty(value = "会议室名称")
    private String rrRoomName; //会议室名称

    @ApiModelProperty(value = "会议室位置名称")
    private String rrRoomPosition; //会议室位置名称

    @ApiModelProperty(value = "会议室容纳人数范围")
    private String rrRoomPeoples; //会议室容纳人数范围

    @ApiModelProperty(value = "默认图片URL")
    private String rrRoomPicMain; //默认图片URL
    @ApiModelProperty(value = "会议室设备详情Vo")
    private List<RoomDeviceDetailVo> deviceDetailVoList;

    @ApiModelProperty(value = "会议简要")
    private String miMtContent; //会议简要

    @ApiModelProperty(value = "会议主题")
    private String miMtSubject; //会议主题

    @ApiModelProperty(value = "会议日期")
    private Long miMtDate; //会议日期

    @ApiModelProperty(value = "会议时间段")
    private String miMtTimeSeg; //会议时间段

    @ApiModelProperty(value = "2未开始 1执行中 4已结束 5已逾期3已取消")
    private Integer miStatus; //1 未开始 2进行中 3已结束 4已逾期5已取消

    @ApiModelProperty(value = "会议室Id")
    private String rrId;

    @ApiModelProperty(value = "创建者名称")
    private String createEiName;

    public String getCreateEiName() {
        return createEiName;
    }

    public void setCreateEiName(String createEiName) {
        this.createEiName = createEiName;
    }

    public String getRrId() {
        return rrId;
    }



    public MyReserveRoomVo setRrId(String rrId) {
        this.rrId = rrId;
        return this;
    }

    public List<RoomDeviceDetailVo> getDeviceDetailVoList() {
        return deviceDetailVoList;
    }

    public MyReserveRoomVo setDeviceDetailVoList(List<RoomDeviceDetailVo> deviceDetailVoList) {
        this.deviceDetailVoList = deviceDetailVoList;
        return this;
    }

    public String getRrRoomName() {
        return rrRoomName;
    }

    public MyReserveRoomVo setRrRoomName(String rrRoomName) {
        this.rrRoomName = rrRoomName;
        return this;
    }

    public String getRrRoomPosition() {
        return rrRoomPosition;
    }

    public MyReserveRoomVo setRrRoomPosition(String rrRoomPosition) {
        this.rrRoomPosition = rrRoomPosition;
        return this;
    }

    public String getRrRoomPeoples() {
        return rrRoomPeoples;
    }

    public MyReserveRoomVo setRrRoomPeoples(String rrRoomPeoples) {
        this.rrRoomPeoples = rrRoomPeoples;
        return this;
    }

    public String getRrRoomPicMain() {
        return rrRoomPicMain;
    }

    public MyReserveRoomVo setRrRoomPicMain(String rrRoomPicMain) {
        this.rrRoomPicMain = rrRoomPicMain;
        return this;
    }

    public Long getMiMtDate() {
        return miMtDate;
    }

    public MyReserveRoomVo setMiMtDate(Long miMtDate) {
        this.miMtDate = miMtDate;
        return this;
    }

    public String getMiMtTimeSeg() {
        return miMtTimeSeg;
    }

    public MyReserveRoomVo setMiMtTimeSeg(String miMtTimeSeg) {
        this.miMtTimeSeg = miMtTimeSeg;
        return this;
    }

    public Integer getMiStatus() {
        return miStatus;
    }

    public MyReserveRoomVo setMiStatus(Integer miStatus) {
        this.miStatus = miStatus;
        return this;
    }

    public String getMiMtContent() {
        return miMtContent;
    }

    public void setMiMtContent(String miMtContent) {
        this.miMtContent = miMtContent;
    }

    public String getMiMtSubject() {
        return miMtSubject;
    }

    public void setMiMtSubject(String miMtSubject) {
        this.miMtSubject = miMtSubject;
    }
}

package com.sgai.property.meeting.vo;

import com.sgai.property.commonService.vo.UserInfoVo;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 会议详情页
 *
 * @author hou
 * @date 2017-12-27 17:34
 */
public class MeetingDetailsVo implements Serializable {

    @ApiModelProperty(value = "会议室名称")
    private String rrRoomName;

    @ApiModelProperty(value = "会议室位置名称")
    private String rrRoomPosition;

    @ApiModelProperty(value = "会议室容纳人数范围")
    private String rrRoomPeoples;

    @ApiModelProperty(value = "会议室设备")
    private String rdRoomDevice;

    @ApiModelProperty(value = "会议日期")
    private Long miMtDate;

    @ApiModelProperty(value = "会议时间段")
    private String miMtTimeSeg;

    @ApiModelProperty(value = "会议创建人")
    private String createEiName;

    @ApiModelProperty(value = "会议简要")
    private String miMtContent;

    @ApiModelProperty(value = "1 未开始 2进行中 3已结束 4已逾期5已取消")
    private Integer miStatus;

    @ApiModelProperty(value = "会议主题")
    private String miMtSubject;

    @ApiModelProperty(value = "是否提醒1 是 0 否")
    private Integer miIsRemind;

    @ApiModelProperty(value = "提前分钟数")
    private Integer miRemindMin;

    @ApiModelProperty(value = "物料")
    private List<MaterielDto> materielDtoList;

    @ApiModelProperty(value = "参会人")
    private List<UserInfoVo> userInfoVoList;

    public List<MaterielDto> getMaterielDtoList() {
        return materielDtoList;
    }

    public MeetingDetailsVo setMaterielDtoList(List<MaterielDto> materielDtoList) {
        this.materielDtoList = materielDtoList;
        return this;
    }

    public String getRrRoomName() {
        return rrRoomName;
    }

    public MeetingDetailsVo setRrRoomName(String rrRoomName) {
        this.rrRoomName = rrRoomName;
        return this;
    }

    public Integer getMiStatus() {
        return miStatus;
    }

    public Integer getMiIsRemind() {
        return miIsRemind;
    }

    public MeetingDetailsVo setMiIsRemind(Integer miIsRemind) {
        this.miIsRemind = miIsRemind;
        return this;
    }

    public Integer getMiRemindMin() {
        return miRemindMin;
    }

    public MeetingDetailsVo setMiRemindMin(Integer miRemindMin) {
        this.miRemindMin = miRemindMin;
        return this;
    }

    public MeetingDetailsVo setMiStatus(Integer miStatus) {
        this.miStatus = miStatus;
        return this;
    }

    public String getRrRoomPosition() {
        return rrRoomPosition;
    }

    public MeetingDetailsVo setRrRoomPosition(String rrRoomPosition) {
        this.rrRoomPosition = rrRoomPosition;
        return this;
    }

    public String getRrRoomPeoples() {
        return rrRoomPeoples;
    }

    public MeetingDetailsVo setRrRoomPeoples(String rrRoomPeoples) {
        this.rrRoomPeoples = rrRoomPeoples;
        return this;
    }

    public String getRdRoomDevice() {
        return rdRoomDevice;
    }

    public MeetingDetailsVo setRdRoomDevice(String rdRoomDevice) {
        this.rdRoomDevice = rdRoomDevice;
        return this;
    }

    public Long getMiMtDate() {
        return miMtDate;
    }

    public MeetingDetailsVo setMiMtDate(Long miMtDate) {
        this.miMtDate = miMtDate;
        return this;
    }

    public String getMiMtTimeSeg() {
        return miMtTimeSeg;
    }

    public MeetingDetailsVo setMiMtTimeSeg(String miMtTimeSeg) {
        this.miMtTimeSeg = miMtTimeSeg;
        return this;
    }

    public String getCreateEiName() {
        return createEiName;
    }

    public MeetingDetailsVo setCreateEiName(String createEiName) {
        this.createEiName = createEiName;
        return this;
    }

    public String getMiMtContent() {
        return miMtContent;
    }

    public MeetingDetailsVo setMiMtContent(String miMtContent) {
        this.miMtContent = miMtContent;
        return this;
    }

    public String getMiMtSubject() {
        return miMtSubject;
    }

    public MeetingDetailsVo setMiMtSubject(String miMtSubject) {
        this.miMtSubject = miMtSubject;
        return this;
    }

    public List<UserInfoVo> getUserInfoVoList() {
        return userInfoVoList;
    }

    public MeetingDetailsVo setUserInfoVoList(List<UserInfoVo> userInfoVoList) {
        this.userInfoVoList = userInfoVoList;
        return this;
    }
}

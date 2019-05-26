package com.sgai.property.wy.entity;

import com.sgai.common.persistence.BoEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by ppliu on 2018/1/23.
 * 会议室管理.
 *
 */
public class MeetingRoom extends BoEntity<MeetingRoom> {
    /**楼Id.*/
    private String floorId;
    /**会议室名称.*/
    private String meetingName;
    /**会议室地址.*/
    private String meetingAddress;
    /**会议类型.*/
    private String meetingType;
    /**会议服务类型.*/
    private String meetingServiceType;
    private List<MeetingOrder> meetings;
    private Date searchTime;

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getMeetingAddress() {
        return meetingAddress;
    }

    public void setMeetingAddress(String meetingAddress) {
        this.meetingAddress = meetingAddress;
    }

    public String getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType;
    }

    public String getMeetingServiceType() {
        return meetingServiceType;
    }

    public void setMeetingServiceType(String meetingServiceType) {
        this.meetingServiceType = meetingServiceType;
    }

    public List<MeetingOrder> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<MeetingOrder> meetings) {
        this.meetings = meetings;
    }

    public Date getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(Date searchTime) {
        this.searchTime = searchTime;
    }
}

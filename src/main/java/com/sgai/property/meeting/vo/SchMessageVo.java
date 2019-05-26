package com.sgai.property.meeting.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gaojianqun on 2018/7/13.
 */
public class SchMessageVo implements Serializable {

    private String roomName;

    private List<MyReserveRoomVo> reserveRoomVoList;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public List<MyReserveRoomVo> getReserveRoomVoList() {
        return reserveRoomVoList;
    }

    public void setReserveRoomVoList(List<MyReserveRoomVo> reserveRoomVoList) {
        this.reserveRoomVoList = reserveRoomVoList;
    }
}

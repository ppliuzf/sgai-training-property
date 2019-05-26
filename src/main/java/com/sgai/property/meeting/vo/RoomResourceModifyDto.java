package com.sgai.property.meeting.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 会议室修改字段
 *
 * @author 146584
 * @create 2017-11-03 11:31
 */
public class RoomResourceModifyDto implements Serializable {

    @ApiModelProperty(value = "主键")
    private String rrId; //主键
    @ApiModelProperty(value = "会议室状态 1 启用 0 禁用")
    private Integer rrRoomState; //会议室状态 1 启用 0 禁用
    @ApiModelProperty(value = "是否删除 1 是 0 否")
    private Integer isDelete; //是否删除 1 是 0 否

    public String getRrId() {
        return rrId;
    }

    public RoomResourceModifyDto setRrId(String rrId) {
        this.rrId = rrId;
        return this;
    }

    public Integer getRrRoomState() {
        return rrRoomState;
    }

    public RoomResourceModifyDto setRrRoomState(Integer rrRoomState) {
        this.rrRoomState = rrRoomState;
        return this;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public RoomResourceModifyDto setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
        return this;
    }
}

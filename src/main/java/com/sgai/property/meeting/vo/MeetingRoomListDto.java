package com.sgai.property.meeting.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class MeetingRoomListDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -317177286075915794L;

	@ApiModelProperty(value = "主键")
	private String Id; //主键
    @ApiModelProperty(value = "位置名称")
    private String rpPositionName; //位置名称
    @ApiModelProperty(value = "描述")
    private String rpPositionDesc; //描述
    @ApiModelProperty(value = "会议室列表")
    private List<RoomResourceVo> roomResourceVos;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getRpPositionName() {
		return rpPositionName;
	}
	public void setRpPositionName(String rpPositionName) {
		this.rpPositionName = rpPositionName;
	}
	public String getRpPositionDesc() {
		return rpPositionDesc;
	}
	public void setRpPositionDesc(String rpPositionDesc) {
		this.rpPositionDesc = rpPositionDesc;
	}
	public List<RoomResourceVo> getRoomResourceVos() {
		return roomResourceVos;
	}
	public void setRoomResourceVos(List<RoomResourceVo> roomResourceVos) {
		this.roomResourceVos = roomResourceVos;
	}
	
}

package com.sgai.property.meeting.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 会议室位置Dto
 *
 * @author 146584
 * @create 2017-11-02 15:54
 */
public class RoomPositionDto implements Serializable {

	
	@ApiModelProperty(value = "主键")
	private String rpId; //主键
    @ApiModelProperty(value = "位置名称")
    private String rpPositionName; //位置名称
    @ApiModelProperty(value = "描述")
    private String rpPositionDesc; //描述

	public String getRpId() {
		return rpId;
	}

	public void setRpId(String rpId) {
		this.rpId = rpId;
	}

	public String getRpPositionName() {
        return rpPositionName;
    }

    public RoomPositionDto setRpPositionName(String rpPositionName) {
        this.rpPositionName = rpPositionName;
        return this;
    }

    public String getRpPositionDesc() {
        return rpPositionDesc;
    }

    public RoomPositionDto setRpPositionDesc(String rpPositionDesc) {
        this.rpPositionDesc = rpPositionDesc;
        return this;
    }
}

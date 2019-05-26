package com.sgai.property.meeting.vo;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author Hou
 * @create 2018-04-18 17:51
 */
public class MyReserveRoomDetailVo extends BoEntity<MyReserveRoomDetailVo> {

    @ApiModelProperty(value = "会议室名称")
    private String rrRoomName; //会议室名称

    @ApiModelProperty(value = "会议室位置名称")
    private String rrRoomPosition; //会议室位置名称

    @ApiModelProperty(value = "会议室容纳人数范围")
    private String rrRoomPeoples; //会议室容纳人数范围

    @ApiModelProperty(value = "默认图片URL")
    private String rrRoomPicMain; //默认图片URL
    @ApiModelProperty(value = "会议室设备详情Vo")
    private String rdRoomDevice;

    @ApiModelProperty(value = "会议日期")
    private Long miMtDate; //会议日期

    @ApiModelProperty(value = "会议时间段")
    private String miMtTimeSeg; //会议时间段

    @ApiModelProperty(value = "1 未开始 2进行中 3已结束 4已逾期5已取消")
    private Integer miStatus; //1 未开始 2进行中 3已结束 4已逾期5已取消

    @ApiModelProperty(value = "会议室Id")
    private String rrId;



}

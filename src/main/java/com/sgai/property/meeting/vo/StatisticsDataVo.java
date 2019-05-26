package com.sgai.property.meeting.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 会议统计数据返回的vo
 *
 * @author hou
 * @date 2017-12-20 17:57
 */
public class StatisticsDataVo implements Serializable {

    @ApiModelProperty(value = "发起会议的数量")
    private Integer startMeetingsCount;
    @ApiModelProperty(value = "结束会议的数量")
    private Integer entMeetingCount;
    @ApiModelProperty(value = "会议时间(小时)")
    private Long meetingTime;
    @ApiModelProperty(value = "剩余可用时间(小时)")
    private Long surplusTime;

    @ApiModelProperty(value = "员工预定排行榜")
    private List<EmpRankVo> empRank;

}

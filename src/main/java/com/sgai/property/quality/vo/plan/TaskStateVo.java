package com.sgai.property.quality.vo.plan;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 145811 on 2017/11/17.
 */
public class TaskStateVo {

    @ApiModelProperty(value = "日期")
    private Long dt;
    @ApiModelProperty(value = "状态，待处理：W，已处理：P")
    private String stat;

    private String taskName;

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}

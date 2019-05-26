package com.sgai.property.quality.vo.plan;

import java.util.List;

import com.sgai.property.quality.entity.plan.TaskTPStat;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 145811 on 2017/11/17.
 */
public class TaskTPStatVo {

    @ApiModelProperty(value = "日期")
    private Long dt;
    @ApiModelProperty(value = "任务模板列表")
    private List<TaskTPStat> taskTPList;

	public List<TaskTPStat> getTaskTPList() {
		return taskTPList;
	}
	public void setTaskTPList(List<TaskTPStat> taskTPList) {
		this.taskTPList = taskTPList;
	}

	public Long getDt() {
		return dt;
	}

	public void setDt(Long dt) {
		this.dt = dt;
	}
}

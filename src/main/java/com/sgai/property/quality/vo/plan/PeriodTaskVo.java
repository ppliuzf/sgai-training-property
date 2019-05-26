package com.sgai.property.quality.vo.plan;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 *@author 严浩淼
 *@date 2018年1月10日--下午2:43:43
 */
public class PeriodTaskVo {
	@ApiModelProperty(value = "阶段id")
    private String periodId; //阶段id
    @ApiModelProperty(value = "阶段名称")
    private String periodName; //阶段名称
    @ApiModelProperty(value = "阶段排序")
    private Long periodSort; //阶段排序
    
    @ApiModelProperty(value = "任务列表")
    private List<PlanTaskVo> taskList;
    
	public String getPeriodName() {
		return periodName;
	}
	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}
	public Long getPeriodSort() {
		return periodSort;
	}
	public void setPeriodSort(Long periodSort) {
		this.periodSort = periodSort;
	}
	public List<PlanTaskVo> getTaskList() {
		return taskList;
	}
	public void setTaskList(List<PlanTaskVo> taskList) {
		this.taskList = taskList;
	}
	public String getPeriodId() {
		return periodId;
	}
	public void setPeriodId(String periodId) {
		this.periodId = periodId;
	}
    
    
}

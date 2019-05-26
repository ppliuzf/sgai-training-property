package com.sgai.property.purchase.param;

import io.swagger.annotations.ApiModelProperty;

public class PlanStageParam {
	
	@ApiModelProperty(value = "id")
    private String id; //id
	@ApiModelProperty(value = "上下移")
    private Long sort; //上下移
    @ApiModelProperty(value = "计划Id")
    private String planId; //计划Id
    @ApiModelProperty(value = "阶段名称")
    private String stageName; //阶段名称
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getSort() {
		return sort;
	}
	public void setSort(Long sort) {
		this.sort = sort;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
    
}

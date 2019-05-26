package com.sgai.property.quality.vo.plan;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 145811 on 2017/11/23.
 */
public class PeriodParam {
	
    @ApiModelProperty(value = "阶段id")
    private String id; //阶段id
    @ApiModelProperty(value = "阶段名称")
    private String periodName; //阶段名称
    @ApiModelProperty(value = "计划id")
    private String recordId; //计划id
    @ApiModelProperty(value = "计划名称")
    private String recordName; //计划名称
    @ApiModelProperty(value = "阶段排序")
    private Long periodSort; //阶段排序
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public Long getPeriodSort() {
		return periodSort;
	}
	public void setPeriodSort(Long periodSort) {
		this.periodSort = periodSort;
	}
	public String getRecordName() {
		return recordName;
	}
	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}
	public String getPeriodName() {
		return periodName;
	}
	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}

    
}

package com.sgai.property.budget.vo;
import io.swagger.annotations.ApiModelProperty;
public class RecordSearchParam {
	
	@ApiModelProperty(value = "计划名称")
	private String recordName; //计划名称
	@ApiModelProperty(value = "申报人名称")
	private String creatorEiEmpName; //申报人名称
    @ApiModelProperty(value = "预算年份")
    private String year; //预算年份
    @ApiModelProperty(value = "预算周期（1:全年2:半年3季度4:月）")
    private Long cycle; //预算周期（1:全年2:半年3季度4:月）
    @ApiModelProperty(value = "计划状态(0:未提交1:已提交2:已通过3:已拒绝4:已撤销)")
    private Long state; //计划状态(0:未提交1:已提交2:已通过3:已拒绝4:已撤销)
	public String getRecordName() {
		return recordName;
	}
	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}
	public String getCreatorEiEmpName() {
		return creatorEiEmpName;
	}
	public void setCreatorEiEmpName(String creatorEiEmpName) {
		this.creatorEiEmpName = creatorEiEmpName;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Long getCycle() {
		return cycle;
	}
	public void setCycle(Long cycle) {
		this.cycle = cycle;
	}
	public Long getState() {
		return state;
	}
	public void setState(Long state) {
		this.state = state;
	}
	
}
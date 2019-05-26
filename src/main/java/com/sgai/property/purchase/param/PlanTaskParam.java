package com.sgai.property.purchase.param;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class PlanTaskParam {
	@ApiModelProperty(value = "id")
    private String id; //id
	@ApiModelProperty(value = "所属部门")
    private String taskDept; //所属部门
    @ApiModelProperty(value = "需求状态(1.已处理 2.未处理)")
    private Long taskNeedStatus; //需求状态(1.已处理 2.未处理)
    @ApiModelProperty(value = "申请编号")
    private String taskNo; //申请编号
//    @ApiModelProperty(value = "申请人姓名")
//    private String taskEmpName; //申请人姓名
    @ApiModelProperty(value = "计划Id")
    private String planId; //计划Id
    @ApiModelProperty(value = "采购名称")
    private String taskPuchaseName; //采购名称
//    @ApiModelProperty(value = "申请日期")
//    private Date taskDate; //申请日期
    @ApiModelProperty(value = "排序字段")
    private Long sort; //排序字段
    @ApiModelProperty(value = "阶段Id")
    private String stageId; //阶段Id
//    @ApiModelProperty(value = "申请人id")
//    private String taskEmpId; //申请人id
    @ApiModelProperty(value = "采购日期")
    private Date taskPurchaseDate; //采购日期
    @ApiModelProperty(value = "结束日期")
    private Date taskEndDate; //结束日期
    @ApiModelProperty(value = "申请理由")
    private String taskOpinion; //申请理由
    
    @ApiModelProperty(value = "任务物料列表")
    private List<PlanDetailParam> planDetailList;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTaskDept() {
		return taskDept;
	}
	public void setTaskDept(String taskDept) {
		this.taskDept = taskDept;
	}
	public Long getTaskNeedStatus() {
		return taskNeedStatus;
	}
	public void setTaskNeedStatus(Long taskNeedStatus) {
		this.taskNeedStatus = taskNeedStatus;
	}
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getTaskPuchaseName() {
		return taskPuchaseName;
	}
	public void setTaskPuchaseName(String taskPuchaseName) {
		this.taskPuchaseName = taskPuchaseName;
	}
//	public Date getTaskDate() {
//		return taskDate;
//	}
//	public void setTaskDate(Date taskDate) {
//		this.taskDate = taskDate;
//	}
	public Long getSort() {
		return sort;
	}
	public void setSort(Long sort) {
		this.sort = sort;
	}
	public String getStageId() {
		return stageId;
	}
	public void setStageId(String stageId) {
		this.stageId = stageId;
	}
	public Date getTaskPurchaseDate() {
		return taskPurchaseDate;
	}
	public void setTaskPurchaseDate(Date taskPurchaseDate) {
		this.taskPurchaseDate = taskPurchaseDate;
	}
	public Date getTaskEndDate() {
		return taskEndDate;
	}
	public void setTaskEndDate(Date taskEndDate) {
		this.taskEndDate = taskEndDate;
	}
	public String getTaskOpinion() {
		return taskOpinion;
	}
	public void setTaskOpinion(String taskOpinion) {
		this.taskOpinion = taskOpinion;
	}
	public List<PlanDetailParam> getPlanDetailList() {
		return planDetailList;
	}
	public void setPlanDetailList(List<PlanDetailParam> planDetailList) {
		this.planDetailList = planDetailList;
	}
    
}

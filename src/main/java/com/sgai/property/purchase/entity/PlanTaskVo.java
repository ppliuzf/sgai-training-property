package com.sgai.property.purchase.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by Administrator on 2018/2/28.
 */
public class PlanTaskVo {

    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "所属部门")
    private String taskDept; //所属部门
    @ApiModelProperty(value = "需求状态(1.已处理 2.未处理)")
    private Long taskNeedStatus; //需求状态(1.已处理 2.未处理)
    @ApiModelProperty(value = "申请编号")
    private String taskNo; //申请编号
    @ApiModelProperty(value = "申请人姓名")
    private String taskEmpName; //申请人姓名
    @ApiModelProperty(value = "采购名称")
    private String taskPuchaseName; //采购名称
    @ApiModelProperty(value = "申请日期")
    private Date taskDate; //申请日期
    @ApiModelProperty(value = "申请人id")
    private String taskEmpId; //申请人id
    @ApiModelProperty(value = "采购日期")
    private Date taskPurchaseDate; //采购日期
    @ApiModelProperty(value = "结束日期")
    private Date taskEndDate; //结束日期
    @ApiModelProperty(value = "申请理由")
    private String taskOpinion; //申请理由
    @ApiModelProperty(value = "排序字段")
    private Long sort; //排序字段

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

    public String getTaskEmpName() {
        return taskEmpName;
    }

    public void setTaskEmpName(String taskEmpName) {
        this.taskEmpName = taskEmpName;
    }

    public String getTaskPuchaseName() {
        return taskPuchaseName;
    }

    public void setTaskPuchaseName(String taskPuchaseName) {
        this.taskPuchaseName = taskPuchaseName;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public String getTaskEmpId() {
        return taskEmpId;
    }

    public void setTaskEmpId(String taskEmpId) {
        this.taskEmpId = taskEmpId;
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

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }
}

package com.sgai.property.purchase.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/2/28.
 */
public class PlanTaskDetailVo {

    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "采购名称")
    private String taskPuchaseName; //采购名称
    @ApiModelProperty(value = "申请日期")
    private Date taskDate; //申请日期
    @ApiModelProperty(value = "申请理由")
    private String taskOpinion; //申请理由
    @ApiModelProperty(value = "采购日期")
    private Date taskPurchaseDate; //采购日期
    @ApiModelProperty(value = "结束日期")
    private Date taskEndDate; //结束日期
    @ApiModelProperty(value = "计划创建人名称")
    private String planEmpName; //计划创建人名称
    @ApiModelProperty(value = "所属部门")
    private String taskDept; //所属部门
    @ApiModelProperty(value = "物料详情列表")
    private List<PlanDetail> planDetailList;
    @ApiModelProperty(value = "阶段名称")
    private String stageName; //阶段名称
    @ApiModelProperty(value = "阶段Id")
    private String stageId; //阶段Id
    @ApiModelProperty(value = "图片数组")
    private List<MatApplyDetailImg> imgUrls; //阶段Id


    public List<PlanDetail> getPlanDetailList() {
        return planDetailList;
    }

    public void setPlanDetailList(List<PlanDetail> planDetailList) {
        this.planDetailList = planDetailList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTaskOpinion() {
        return taskOpinion;
    }

    public void setTaskOpinion(String taskOpinion) {
        this.taskOpinion = taskOpinion;
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

    public String getPlanEmpName() {
        return planEmpName;
    }

    public void setPlanEmpName(String planEmpName) {
        this.planEmpName = planEmpName;
    }

    public String getTaskDept() {
        return taskDept;
    }

    public void setTaskDept(String taskDept) {
        this.taskDept = taskDept;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public List<MatApplyDetailImg> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<MatApplyDetailImg> imgUrls) {
        this.imgUrls = imgUrls;
    }
}

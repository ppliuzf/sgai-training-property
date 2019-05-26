package com.sgai.property.purchase.vo;

import com.sgai.property.purchase.entity.PlanDetailCompany;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * 申请已处理详情 on 2018/3/1.
 */
public class TetailProcessedVo {
    /*matOrder*/
    @ApiModelProperty(value = "制单人名称")
    private String orderEmpName; //制单人名称
    @ApiModelProperty(value = "需求单号")
    private String applyNo; //需求单号
    @ApiModelProperty(value = "制单日期")
    private Date orderDate; //制单日期
    @ApiModelProperty(value = "审批时间")
    private Date approveDate; //审批时间
    @ApiModelProperty(value = "需求订单?1:需求订单;2:主动采购")
    private Long orderType; //需求订单?1:需求订单;2:主动采购
    /*spring*/
    @ApiModelProperty(value = "申请理由")
    private String taskOpinion; //申请理由
    @ApiModelProperty(value = "申请日期")
    private Date taskDate; //申请日期
    @ApiModelProperty(value = "申请人id")
    private String taskEmpId; //申请人id
    @ApiModelProperty(value = "申请人姓名")
    private String taskEmpName; //申请人姓名
    /*plan*/
    @ApiModelProperty(value = "采购审核人名称")
    private String buyerEmpName; //采购审核人名称
    @ApiModelProperty(value = "采购计划部门")
    private String planDept; //采购计划部门

    @ApiModelProperty(value = "物料已提交数据")
    private List<PlanDetailCompany> pdcList;

    public String getOrderEmpName() {
        return orderEmpName;
    }

    public void setOrderEmpName(String orderEmpName) {
        this.orderEmpName = orderEmpName;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public Long getOrderType() {
        return orderType;
    }

    public void setOrderType(Long orderType) {
        this.orderType = orderType;
    }

    public String getTaskOpinion() {
        return taskOpinion;
    }

    public void setTaskOpinion(String taskOpinion) {
        this.taskOpinion = taskOpinion;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public String getPlanDept() {
        return planDept;
    }

    public void setPlanDept(String planDept) {
        this.planDept = planDept;
    }

    public String getTaskEmpId() {
        return taskEmpId;
    }

    public void setTaskEmpId(String taskEmpId) {
        this.taskEmpId = taskEmpId;
    }

    public String getTaskEmpName() {
        return taskEmpName;
    }

    public void setTaskEmpName(String taskEmpName) {
        this.taskEmpName = taskEmpName;
    }

    public String getBuyerEmpName() {
        return buyerEmpName;
    }

    public void setBuyerEmpName(String buyerEmpName) {
        this.buyerEmpName = buyerEmpName;
    }

    public List<PlanDetailCompany> getPdcList() {
        return pdcList;
    }

    public void setPdcList(List<PlanDetailCompany> pdcList) {
        this.pdcList = pdcList;
    }
}

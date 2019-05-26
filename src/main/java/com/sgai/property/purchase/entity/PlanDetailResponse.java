package com.sgai.property.purchase.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/2/28.
 */
public class PlanDetailResponse extends BoEntity<PlanDetailResponse> {

    @ApiModelProperty(value = "计划名称")
    private String planName; //计划名称
    @ApiModelProperty(value = "计划类型")
    private Long planType; //计划类型
    @ApiModelProperty(value = "采购计划部门ID")
    private String planDeptId; //采购计划部门ID
    @ApiModelProperty(value = "采购计划部门")
    private String planDept; //采购计划部门
    @ApiModelProperty(value = "描述")
    private String planDescribe; //描述
    @ApiModelProperty(value = "状态? 1:待提交；2::待审核；3:已拒绝；4:已通过")
    private Long planStat; //状态? 1:待提交；2::待审核；3:已拒绝；4:已通过
    @ApiModelProperty(value = "审批时间")
    private Date approveDate; //审批时间
    @ApiModelProperty(value = "审批意见")
    private String approveOption; //审批意见
    @ApiModelProperty(value = "阶段数据")
    private List<PlanStageVo> planStageVoList;

    public List<PlanStageVo> getPlanStageVoList() {
        return planStageVoList;
    }

    public void setPlanStageVoList(List<PlanStageVo> planStageVoList) {
        this.planStageVoList = planStageVoList;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Long getPlanType() {
        return planType;
    }

    public void setPlanType(Long planType) {
        this.planType = planType;
    }

    public String getPlanDept() {
        return planDept;
    }

    public void setPlanDept(String planDept) {
        this.planDept = planDept;
    }

    public String getPlanDescribe() {
        return planDescribe;
    }

    public void setPlanDescribe(String planDescribe) {
        this.planDescribe = planDescribe;
    }

    public Long getPlanStat() {
        return planStat;
    }

    public void setPlanStat(Long planStat) {
        this.planStat = planStat;
    }

    public String getPlanDeptId() {
        return planDeptId;
    }

    public void setPlanDeptId(String planDeptId) {
        this.planDeptId = planDeptId;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public String getApproveOption() {
        return approveOption;
    }

    public void setApproveOption(String approveOption) {
        this.approveOption = approveOption;
    }
}

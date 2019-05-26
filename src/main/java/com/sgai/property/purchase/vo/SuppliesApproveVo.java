package com.sgai.property.purchase.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by Administrator on 2018/1/15.
 */
public class SuppliesApproveVo {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "申请理由")
    private String applyReason; //申请理由
    @ApiModelProperty(value = "申请日期")
    private Date applyDate; //申请日期
    @ApiModelProperty(value = "申请人名称")
    private String applyEmpName; //申请人名称
    @ApiModelProperty(value = "所属部门名称")
    private String applyDeptName; //所属部门名称
    @ApiModelProperty(value = "申请编号")
    private String applyNo; //申请编号
    @ApiModelProperty(value = "1:已提交；2:已通过；3:已拒绝；4:已撤回")
    private Long matStat; //1:已提交；2:已通过；3:已拒绝；4:已撤回

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getApplyEmpName() {
        return applyEmpName;
    }

    public void setApplyEmpName(String applyEmpName) {
        this.applyEmpName = applyEmpName;
    }

    public String getApplyDeptName() {
        return applyDeptName;
    }

    public void setApplyDeptName(String applyDeptName) {
        this.applyDeptName = applyDeptName;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public Long getMatStat() {
        return matStat;
    }

    public void setMatStat(Long matStat) {
        this.matStat = matStat;
    }
}

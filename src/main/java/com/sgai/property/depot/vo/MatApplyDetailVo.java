package com.sgai.property.depot.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

import com.sgai.property.purchase.entity.MatDetail;

/**
 * Created by 145811 on 2018/1/12.
 */
public class MatApplyDetailVo {
    @ApiModelProperty(value = "id")
    private String id; //id
    @ApiModelProperty(value = "申请理由")
    private String applyReason; //申请理由
    @ApiModelProperty(value = "申请人id")
    private Long applyEmpId; //申请人id
    @ApiModelProperty(value = "申请日期")
    private Date applyDate; //申请日期
    @ApiModelProperty(value = "审批意见")
    private String approveOption; //审批意见
    @ApiModelProperty(value = "审批时间")
    private Date approveDate; //审批时间
    @ApiModelProperty(value = "申请人名称")
    private String applyEmpName; //申请人名称
    @ApiModelProperty(value = "所属部门名称")
    private String applyDeptName; //所属部门名称
    @ApiModelProperty(value = "1:已提交；2:已通过；3:已拒绝；4:已撤回")
    private Long matStat; //1:已提交；2:已通过；3:已拒绝；4:已撤回
    @ApiModelProperty(value = "要求领取日期")
    private Date supplyDate; //要求领取日期
    @ApiModelProperty(value = "申请编号")
    private String applyNo; //申请编号
    @ApiModelProperty(value = "用料审批人id")
    private String approveEmpId; //用料审批人id
    @ApiModelProperty(value = "用料审批人名称")
    private String approveEmpName; //用料审批人名称
    @ApiModelProperty(value = "物料明细")
    private List<MatDetail> matDetail; //物料明细


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

    public Long getApplyEmpId() {
        return applyEmpId;
    }

    public void setApplyEmpId(Long applyEmpId) {
        this.applyEmpId = applyEmpId;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getApproveOption() {
        return approveOption;
    }

    public void setApproveOption(String approveOption) {
        this.approveOption = approveOption;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
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

    public Long getMatStat() {
        return matStat;
    }

    public void setMatStat(Long matStat) {
        this.matStat = matStat;
    }

    public Date getSupplyDate() {
        return supplyDate;
    }

    public void setSupplyDate(Date supplyDate) {
        this.supplyDate = supplyDate;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getApproveEmpId() {
        return approveEmpId;
    }

    public void setApproveEmpId(String approveEmpId) {
        this.approveEmpId = approveEmpId;
    }

    public String getApproveEmpName() {
        return approveEmpName;
    }

    public void setApproveEmpName(String approveEmpName) {
        this.approveEmpName = approveEmpName;
    }

    public List<MatDetail> getMatDetail() {
        return matDetail;
    }

    public void setMatDetail(List<MatDetail> matDetail) {
        this.matDetail = matDetail;
    }
}

package com.sgai.property.purchase.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/1/15.
 */
public class SuppliesApproveParam {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "申请人名称")
    private String applyEmpName; //申请人名称
    @ApiModelProperty(value = "1:已提交；2:已通过；3:已拒绝；4:已撤回")
    private Long matStat; //1:已提交；2:已通过；3:已拒绝；4:已撤回

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplyEmpName() {
        return applyEmpName;
    }

    public void setApplyEmpName(String applyEmpName) {
        this.applyEmpName = applyEmpName;
    }

    public Long getMatStat() {
        return matStat;
    }

    public void setMatStat(Long matStat) {
        this.matStat = matStat;
    }
}

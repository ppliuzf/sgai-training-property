package com.sgai.property.car.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 我预约的入参
 *
 * @author Hou
 * @create 2018-05-23 11:18
 */
public class CarRelationInfoParam implements Serializable {

    @ApiModelProperty(value = "预约者姓名")
    private String riUserName;
    @ApiModelProperty(value = "审核状态 0 未提交审核 1 审核中 2 审核通过 3审核不通过 4已归还 5归还中")
    private Long riAuditStatus;
    @ApiModelProperty(value = "预约者预约开始时间")
    private Long riUseStart;

    public String getRiUserName() {
        return riUserName;
    }

    public CarRelationInfoParam setRiUserName(String riUserName) {
        this.riUserName = riUserName;
        return this;
    }

    public Long getRiAuditStatus() {
        return riAuditStatus;
    }

    public CarRelationInfoParam setRiAuditStatus(Long riAuditStatus) {
        if((riAuditStatus+"").equals("-1")){
            this.riAuditStatus = null;
        }else {
            this.riAuditStatus = riAuditStatus;
        }
        return this;
    }

    public Long getRiUseStart() {
        return riUseStart;
    }

    public CarRelationInfoParam setRiUseStart(Long riUseStart) {
        if((riUseStart+"").equals("-1")){
            this.riUseStart = null;
        }else {
            this.riUseStart = riUseStart;
        }
        return this;
    }
}

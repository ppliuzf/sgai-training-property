package com.sgai.property.depot.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/1/25.
 */
public class WhInParam {
    @ApiModelProperty(value = "入库单号")
    private String whInNo; //入库单号
    @ApiModelProperty(value = "入库状态?1:未入库;2:部分入库;3:全部入库")
    private Long whInStat; //入库状态?1:未入库;2:部分入库;3:全部入库
    @ApiModelProperty(value = "入库类型？1：调拨入库；2：采购入库；3：归还入库；4：直接入库")
    private Long whInType; //入库类型？1：调拨入库；2：采购入库；3：归还入库；4：直接入库

    public String getWhInNo() {
        return whInNo;
    }

    public void setWhInNo(String whInNo) {
        this.whInNo = whInNo;
    }

    public Long getWhInStat() {
        return whInStat;
    }

    public void setWhInStat(Long whInStat) {
        this.whInStat = whInStat;
    }

    public Long getWhInType() {
        return whInType;
    }

    public void setWhInType(Long whInType) {
        this.whInType = whInType;
    }
}

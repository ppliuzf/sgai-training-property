package com.sgai.property.depot.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/1/24.
 */
public class WhOutParam {
    @ApiModelProperty(value = "出库类型？1：调拨出库；2：用料出库；3：借出出库；4：直接出库；5：销售出库")
    private Long whOutType; //出库类型？1：调拨出库；2：用料出库；3：借出出库；4：直接出库；5：销售出库
    @ApiModelProperty(value = "出库单号")
    private String whOutNo; //出库单号
    @ApiModelProperty(value = "出库状态?1:未出库;2:部分出库;3:全部出库")
    private Long whStat; //出库状态?1:未出库;2:部分出库;3:全部出库

    public Long getWhOutType() {
        return whOutType;
    }

    public void setWhOutType(Long whOutType) {
        this.whOutType = whOutType;
    }

    public String getWhOutNo() {
        return whOutNo;
    }

    public void setWhOutNo(String whOutNo) {
        this.whOutNo = whOutNo;
    }

    public Long getWhStat() {
        return whStat;
    }

    public void setWhStat(Long whStat) {
        this.whStat = whStat;
    }
}

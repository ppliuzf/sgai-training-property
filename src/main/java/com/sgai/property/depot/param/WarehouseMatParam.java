package com.sgai.property.depot.param;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gaojianqun on 2018/6/9.
 */
public class WarehouseMatParam {

    @ApiModelProperty(value = "盘点单号")
    private String orderNumber; //盘点单号
    @ApiModelProperty(value = "仓库id")
    private String whId; //仓库id
    @ApiModelProperty(value = "物料id")
    private String matTypeId; //物料id
    @ApiModelProperty(value = "实际数量")
    private Long matRealNum; //实际数量
    @ApiModelProperty(value = "差异量")
    private Long matDiffNum; //差异量

    public String getWhId() {
        return whId;
    }

    public void setWhId(String whId) {
        this.whId = whId;
    }

    public String getMatTypeId() {
        return matTypeId;
    }

    public void setMatTypeId(String matTypeId) {
        this.matTypeId = matTypeId;
    }

    public Long getMatRealNum() {
        return matRealNum;
    }

    public void setMatRealNum(Long matRealNum) {
        this.matRealNum = matRealNum;
    }

    public Long getMatDiffNum() {
        return matDiffNum;
    }

    public void setMatDiffNum(Long matDiffNum) {
        this.matDiffNum = matDiffNum;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}

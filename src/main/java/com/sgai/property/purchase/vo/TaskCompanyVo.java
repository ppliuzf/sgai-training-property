package com.sgai.property.purchase.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/6/23.
 */
public class TaskCompanyVo {
    @ApiModelProperty(value = "采购数量")
    private Long buyCount; //采购数量
    @ApiModelProperty(value = "供应商名称")
    private String supplyComName; //供应商名称
    @ApiModelProperty(value = "供应商id")
    private String supplyComId; //供应商id

    public Long getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Long buyCount) {
        this.buyCount = buyCount;
    }

    public String getSupplyComName() {
        return supplyComName;
    }

    public void setSupplyComName(String supplyComName) {
        this.supplyComName = supplyComName;
    }

    public String getSupplyComId() {
        return supplyComId;
    }

    public void setSupplyComId(String supplyComId) {
        this.supplyComId = supplyComId;
    }
}

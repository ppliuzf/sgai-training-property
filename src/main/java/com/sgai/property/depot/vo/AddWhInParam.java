package com.sgai.property.depot.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by Administrator on 2018/1/25.
 */
public class AddWhInParam {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "仓库名称")
    private String whName; //仓库名称
    @ApiModelProperty(value = "仓库类型 1：实仓，2：虚仓")
    private Long whType; //仓库类型 1：实仓，2：虚仓
    @ApiModelProperty(value = "采购单id")
    private String orderId; //采购单id
    @ApiModelProperty(value = "入库单号")
    private String whInNo; //入库单号
    @ApiModelProperty(value = "仓库id")
    private String whId; //仓库id
    @ApiModelProperty(value = "调拨单id")
    private String allotId; //调拨单id
    @ApiModelProperty(value = "入库状态?1:未入库;2:部分入库;3:全部入库")
    private Long whInStat; //入库状态?1:未入库;2:部分入库;3:全部入库
    @ApiModelProperty(value = "采购单名称")
    private String orderName; //采购单名称
    @ApiModelProperty(value = "调拨单名称")
    private String allotName; //调拨单名称
    @ApiModelProperty(value = "入库类型？1：调拨入库；2：采购入库；3：归还入库；4：直接入库")
    private Long whInType; //入库类型？1：调拨入库；2：采购入库；3：归还入库；4：直接入库
    @ApiModelProperty(value = "物料明细")
    private String suppliesDetailsId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWhName() {
        return whName;
    }

    public void setWhName(String whName) {
        this.whName = whName;
    }

    public Long getWhType() {
        return whType;
    }

    public void setWhType(Long whType) {
        this.whType = whType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getWhInNo() {
        return whInNo;
    }

    public void setWhInNo(String whInNo) {
        this.whInNo = whInNo;
    }

    public String getWhId() {
        return whId;
    }

    public void setWhId(String whId) {
        this.whId = whId;
    }

    public String getAllotId() {
        return allotId;
    }

    public void setAllotId(String allotId) {
        this.allotId = allotId;
    }

    public Long getWhInStat() {
        return whInStat;
    }

    public void setWhInStat(Long whInStat) {
        this.whInStat = whInStat;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getAllotName() {
        return allotName;
    }

    public void setAllotName(String allotName) {
        this.allotName = allotName;
    }

    public Long getWhInType() {
        return whInType;
    }

    public void setWhInType(Long whInType) {
        this.whInType = whInType;
    }

    public String getSuppliesDetailsId() {
        return suppliesDetailsId;
    }

    public void setSuppliesDetailsId(String suppliesDetailsId) {
        this.suppliesDetailsId = suppliesDetailsId;
    }
}

package com.sgai.property.depot.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Transient;
import java.util.List;

/**
 * Created by Administrator on 2018/1/24.
 */
public class AddWhOutParam {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "仓库类型 1：实仓，2：虚仓")
    private Long whType; //仓库类型 1：实仓，2：虚仓
    @ApiModelProperty(value = "用料申请单id")
    private String matApplyId; //用料申请单id
    @ApiModelProperty(value = "调拨单id")
    private String allotId; //调拨单id
    @ApiModelProperty(value = "调拨单名称")
    private String allotName; //调拨单名称
    @ApiModelProperty(value = "仓库id")
    private String whId; //仓库id
    @ApiModelProperty(value = "用料申请单名称")
    private String matApplyName; //用料申请单名称
    @ApiModelProperty(value = "出库类型？1：调拨出库；2：用料出库；3：借出出库；4：直接出库；5：销售出库")
    private Long whOutType; //出库类型？1：调拨出库；2：用料出库；3：借出出库；4：直接出库；5：销售出库
    @ApiModelProperty(value = "出库状态?1:未出库;2:部分出库;3:全部出库")
    private Long whStat; //出库状态?1:未出库;2:部分出库;3:全部出库
    @ApiModelProperty(value = "仓库名称")
    private String whName; //仓库名称
    @Transient
    @ApiModelProperty(value = "物料明细")
    private List<SuppliesDetail> suppliesDetails;
    @ApiModelProperty(value = "虚拟ID")
    String orderId;
    private String orderNumber;

    public Long getWhType() {
        return whType;
    }

    public void setWhType(Long whType) {
        this.whType = whType;
    }

    public String getMatApplyId() {
        return matApplyId;
    }

    public void setMatApplyId(String matApplyId) {
        this.matApplyId = matApplyId;
    }

    public String getWhId() {
        return whId;
    }

    public void setWhId(String whId) {
        this.whId = whId;
    }

    public String getMatApplyName() {
        return matApplyName;
    }

    public void setMatApplyName(String matApplyName) {
        this.matApplyName = matApplyName;
    }

    public Long getWhOutType() {
        return whOutType;
    }

    public void setWhOutType(Long whOutType) {
        this.whOutType = whOutType;
    }

    public String getAllotName() {
        return allotName;
    }

    public void setAllotName(String allotName) {
        this.allotName = allotName;
    }

    public String getWhName() {
        return whName;
    }

    public void setWhName(String whName) {
        this.whName = whName;
    }

    public List<SuppliesDetail> getSuppliesDetails() {
        return suppliesDetails;
    }

    public void setSuppliesDetails(List<SuppliesDetail> suppliesDetails) {
        this.suppliesDetails = suppliesDetails;
    }

    public String getAllotId() {
        return allotId;
    }

    public void setAllotId(String allotId) {
        this.allotId = allotId;
    }

    public Long getWhStat() {
        return whStat;
    }

    public void setWhStat(Long whStat) {
        this.whStat = whStat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}

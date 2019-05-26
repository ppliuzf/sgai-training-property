package com.sgai.property.depot.param;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/6/9.
 */
public class RecordParam {

    @ApiModelProperty(value = "虚拟ID")
    String orderId;
    @ApiModelProperty(value = "订单号")
    String orderNumber;
    @ApiModelProperty(value = "0:调拨；1:用料")
    int isAllot;
    @ApiModelProperty(value = "仓库Id")
    String whId;

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

    public int getIsAllot() {
        return isAllot;
    }

    public void setIsAllot(int isAllot) {
        this.isAllot = isAllot;
    }

    public String getWhId() {
        return whId;
    }

    public void setWhId(String whId) {
        this.whId = whId;
    }
}

package com.sgai.property.depot.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 145811 on 2018/1/24.
 */
public class MatDetailVo {

    @ApiModelProperty(value = "id")
    private String id; //id
    @ApiModelProperty(value = "物料名称")
    private String matName; //物料名称
    @ApiModelProperty(value = "物料规格")
    private String matSpec; //物料规格
    @ApiModelProperty(value = "物料型号")
    private String matTypeCode; //物料型号
    @ApiModelProperty(value = "采购数量")
    private Long buyCount; //采购数量
    @ApiModelProperty(value = "需求编号，如：CL-00000001")
    private String applyNo; //需求编号，如：CL-00000001
    @ApiModelProperty(value = "供应商id")
    private String supplyComId; //供应商id
    @ApiModelProperty(value = "需求数量")
    private Long applyCount; //需求数量
    @ApiModelProperty(value = "订单编号，如：CA-00000001")
    private String orderNo; //订单编号，如：CA-00000001
    @ApiModelProperty(value = "供应商名称")
    private String supplyComName; //供应商名称
    @ApiModelProperty(value = "物料种类id")
    private String matTypeId; //

    public String getMatTypeId() {
        return matTypeId;
    }

    public void setMatTypeId(String matTypeId) {
        this.matTypeId = matTypeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getMatSpec() {
        return matSpec;
    }

    public void setMatSpec(String matSpec) {
        this.matSpec = matSpec;
    }

    public String getMatTypeCode() {
        return matTypeCode;
    }

    public void setMatTypeCode(String matTypeCode) {
        this.matTypeCode = matTypeCode;
    }

    public Long getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Long buyCount) {
        this.buyCount = buyCount;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getSupplyComId() {
        return supplyComId;
    }

    public void setSupplyComId(String supplyComId) {
        this.supplyComId = supplyComId;
    }

    public Long getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(Long applyCount) {
        this.applyCount = applyCount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSupplyComName() {
        return supplyComName;
    }

    public void setSupplyComName(String supplyComName) {
        this.supplyComName = supplyComName;
    }
}

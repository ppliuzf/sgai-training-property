package com.sgai.property.depot.vo;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class WarehousRecordVo{

    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "物料名称")
    private String matName; //物料名称
    @ApiModelProperty(value = "")
    private String matTypeId; //
    @ApiModelProperty(value = "物料规格")
    private String matSpec; //物料规格
    @ApiModelProperty(value = "物料型号")
    private String matTypeCode; //物料型号
    @ApiModelProperty(value = "需求数量")
    private Long matNeetNum; //需求数量
    @ApiModelProperty(value = "订单号虚拟Id")
    private String orderId; //订单号虚拟Id
    @ApiModelProperty(value = "仓库id")
    private String whId; //仓库id
    @ApiModelProperty(value = "仓库名称")
    private String whName; //仓库名称
    @ApiModelProperty(value = "仓库类型")
    private String whType; //仓库类型
    @ApiModelProperty(value = "实际数量")
    private Long matRealNum; //实际数量



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

    public String getMatTypeId() {
        return matTypeId;
    }

    public void setMatTypeId(String matTypeId) {
        this.matTypeId = matTypeId;
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

    public Long getMatNeetNum() {
        return matNeetNum;
    }

    public void setMatNeetNum(Long matNeetNum) {
        this.matNeetNum = matNeetNum;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getWhId() {
        return whId;
    }

    public void setWhId(String whId) {
        this.whId = whId;
    }

    public String getWhName() {
        return whName;
    }

    public void setWhName(String whName) {
        this.whName = whName;
    }

    public String getWhType() {
        return whType;
    }

    public void setWhType(String whType) {
        this.whType = whType;
    }

    public Long getMatRealNum() {
        return matRealNum;
    }

    public void setMatRealNum(Long matRealNum) {
        this.matRealNum = matRealNum;
    }
}
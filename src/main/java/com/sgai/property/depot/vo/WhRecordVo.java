package com.sgai.property.depot.vo;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * 出库分页返回值 on 2018/6/9.
 */
public class WhRecordVo extends BoEntity<WhRecordVo> {

    @ApiModelProperty(value = "物料id")
    private String matTypeId; //物料id
    @ApiModelProperty(value = "物料规格")
    private String matSpec; //物料规格
    @ApiModelProperty(value = "物料编号")
    private String matNo; //物料编号
    @ApiModelProperty(value = "物料型号（无）")
    private String matTypeCode; //物料型号（无）
    @ApiModelProperty(value = "订单号虚拟Id")
    private String orderId; //订单号虚拟Id
    @ApiModelProperty(value = "所需数量")
    private Long matNeetNum; //所需数量
    @ApiModelProperty(value = "物料名称")
    private String matName; //物料名称
    @ApiModelProperty(value = "实际（出库）数量")
    private Long matRealNum; //实际（出库）数量
    @ApiModelProperty(value = "仓库id")
    private String whId; //仓库id
    @ApiModelProperty(value = "库存数量（账存数量）")
    private Long matNum; //库存数量（账存数量）

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

    public String getMatNo() {
        return matNo;
    }

    public void setMatNo(String matNo) {
        this.matNo = matNo;
    }

    public String getMatTypeCode() {
        return matTypeCode;
    }

    public void setMatTypeCode(String matTypeCode) {
        this.matTypeCode = matTypeCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getMatNeetNum() {
        return matNeetNum;
    }

    public void setMatNeetNum(Long matNeetNum) {
        this.matNeetNum = matNeetNum;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public Long getMatRealNum() {
        return matRealNum;
    }

    public void setMatRealNum(Long matRealNum) {
        this.matRealNum = matRealNum;
    }

    public String getWhId() {

        return whId;
    }

    public void setWhId(String whId) {
        this.whId = whId;
    }

    public Long getMatNum() {
        return matNum;
    }

    public void setMatNum(Long matNum) {
        this.matNum = matNum;
    }
}

package com.sgai.property.depot.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gaojianqun on 2018/6/8.
 */
public class InventoiresMatVoNew {

    @ApiModelProperty(value = "盘点单号")
    private String orderNumber; //盘点单号
    @ApiModelProperty(value = "仓库id")
    private String whId; //仓库id
    @ApiModelProperty(value = "仓库名称")
    private String whName; //仓库名称
    @ApiModelProperty(value = "物料id")
    private String matTypeId; //物料id
    @ApiModelProperty(value = "物料名称")
    private String matName; //物料名称
    @ApiModelProperty(value = "账存数量")
    private Long matNum; //账存数量
    @ApiModelProperty(value = "实存数量")
    private Long matRealNum; //实存数量
    @ApiModelProperty(value = "差异量")
    private Long matDiffNum; //差异量
    @ApiModelProperty(value = "物料编号")
    private String matNo; //物料编号

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

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public Long getMatNum() {
        return matNum;
    }

    public void setMatNum(Long matNum) {
        this.matNum = matNum;
    }

    public Long getMatDiffNum() {
        return matDiffNum;
    }

    public void setMatDiffNum(Long matDiffNum) {
        this.matDiffNum = matDiffNum;
    }

    public Long getMatRealNum() {
        return matRealNum;
    }

    public void setMatRealNum(Long matRealNum) {
        this.matRealNum = matRealNum;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getWhName() {
        return whName;
    }

    public void setWhName(String whName) {
        this.whName = whName;
    }

    public String getMatNo() {
        return matNo;
    }

    public void setMatNo(String matNo) {
        this.matNo = matNo;
    }
}

package com.sgai.property.quality.dto;

import io.swagger.annotations.ApiModelProperty;

public class MaterielInfoDto {
    @ApiModelProperty(value = "物料ID")
    private String id;
    @ApiModelProperty(value = "物料分类编码")
    private String matTypeCode;
    @ApiModelProperty(value = "物料分类名称")
    private String matTypeName;
    @ApiModelProperty(value = "物料名称")
    private String matName;
    @ApiModelProperty(value = "物料规格")
    private String spec;
    @ApiModelProperty(value = "计量单位")
    private String unitName;
    @ApiModelProperty(value = "用途")
    private String matUseName;
    @ApiModelProperty(value = "存量上限")
    private String stockLimit;
    @ApiModelProperty(value = "存量下限")
    private String stockOffline;
    @ApiModelProperty(value = "备注")
    private String remarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatTypeCode() {
        return matTypeCode;
    }

    public void setMatTypeCode(String matTypeCode) {
        this.matTypeCode = matTypeCode;
    }

    public String getMatTypeName() {
        return matTypeName;
    }

    public void setMatTypeName(String matTypeName) {
        this.matTypeName = matTypeName;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getMatUseName() {
        return matUseName;
    }

    public void setMatUseName(String matUseName) {
        this.matUseName = matUseName;
    }

    public String getStockLimit() {
        return stockLimit;
    }

    public void setStockLimit(String stockLimit) {
        this.stockLimit = stockLimit;
    }

    public String getStockOffline() {
        return stockOffline;
    }

    public void setStockOffline(String stockOffline) {
        this.stockOffline = stockOffline;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}

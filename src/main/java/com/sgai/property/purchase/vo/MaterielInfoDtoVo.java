package com.sgai.property.purchase.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/1/12.
 */
public class MaterielInfoDtoVo {
    @ApiModelProperty(value = "物料ID")
    private String id; //id
    @ApiModelProperty(value = "物料名称")
    private String matName; //物料名称
    @ApiModelProperty(value = "物料规格")
    private String spec; //物料规格
    @ApiModelProperty(value = "物料分类编码")
    private String matTypeCode; //物料型号
   @ApiModelProperty(value = "物料分类名称")
    private String getMatName;
   @ApiModelProperty(value = "用途")
    private String matUseName;
   @ApiModelProperty(value = "备注")
    private String remarks;
   @ApiModelProperty(value = "存量上限")
   private String stockLimit;
   @ApiModelProperty(value = "存量下限")
    private String stockOffline;
   @ApiModelProperty(value = "计量单位")
    private String unitName;

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

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getMatTypeCode() {
        return matTypeCode;
    }

    public void setMatTypeCode(String matTypeCode) {
        this.matTypeCode = matTypeCode;
    }

    public String getGetMatName() {
        return getMatName;
    }

    public void setGetMatName(String getMatName) {
        this.getMatName = getMatName;
    }

    public String getMatUseName() {
        return matUseName;
    }

    public void setMatUseName(String matUseName) {
        this.matUseName = matUseName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}

package com.sgai.property.meeting.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 物料dto
 *
 * @author hou
 * @date 2017-12-27 17:47
 */
public class MaterielDto implements Serializable {

    @ApiModelProperty(value = "物料Id")
    private String id;
    @ApiModelProperty(value = "物料Id")
    private String maId;
    @ApiModelProperty(value = "物料数量")
    private Long maCount;
    @ApiModelProperty(value = "物料名称")
    private String matName;
    @ApiModelProperty(value = "类型code")
    private String matTypeCode;
    @ApiModelProperty(value = "类型名称")
    private String matTypeName;
    @ApiModelProperty(value = "单位")
    private String spec;
    @ApiModelProperty(value = "评价")
    private String remarks;
    @ApiModelProperty(value = "限制")
    private Integer stockLimit;
    private Integer stockOffline;
    private String unit;
    @ApiModelProperty(value = "品类名称")
    private String unitName;
    private String matUseName;

    public String getMaId() {
        return maId;
    }

    public MaterielDto setMaId(String maId) {
        this.maId = maId;
        return this;
    }

    public Long getMaCount() {
        return maCount;
    }

    public String getId() {
        return id;
    }

    public MaterielDto setId(String id) {
        this.id = id;
        return this;
    }

    public MaterielDto setMaCount(Long maCount) {
        this.maCount = maCount;
        return this;
    }

    public String getMatName() {
        return matName;
    }

    public MaterielDto setMatName(String matName) {
        this.matName = matName;
        return this;
    }

    public String getMatTypeCode() {
        return matTypeCode;
    }

    public MaterielDto setMatTypeCode(String matTypeCode) {
        this.matTypeCode = matTypeCode;
        return this;
    }

    public String getMatTypeName() {
        return matTypeName;
    }

    public MaterielDto setMatTypeName(String matTypeName) {
        this.matTypeName = matTypeName;
        return this;
    }

    public String getSpec() {
        return spec;
    }

    public MaterielDto setSpec(String spec) {
        this.spec = spec;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public MaterielDto setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public Integer getStockLimit() {
        return stockLimit;
    }

    public MaterielDto setStockLimit(Integer stockLimit) {
        this.stockLimit = stockLimit;
        return this;
    }

    public Integer getStockOffline() {
        return stockOffline;
    }

    public MaterielDto setStockOffline(Integer stockOffline) {
        this.stockOffline = stockOffline;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public MaterielDto setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public String getUnitName() {
        return unitName;
    }

    public MaterielDto setUnitName(String unitName) {
        this.unitName = unitName;
        return this;
    }

    public String getMatUseName() {
        return matUseName;
    }

    public MaterielDto setMatUseName(String matUseName) {
        this.matUseName = matUseName;
        return this;
    }
}

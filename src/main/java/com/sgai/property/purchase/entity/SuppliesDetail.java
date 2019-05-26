package com.sgai.property.purchase.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/1/12.
 */
public class SuppliesDetail {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "物料类型ID")
    private String matTypeId;
    @ApiModelProperty(value = "物料名称")
    private String matName; //物料名称
    @ApiModelProperty(value = "物料规格")
    private String matSpec; //物料规格
    @ApiModelProperty(value = "物料型号")
    private String matTypeCode; //物料型号
    @ApiModelProperty(value = "需求数量")
    private Long applyCount; //需求数量
    @ApiModelProperty(value = "采购数量")
    private Long buyCount; //采购数量
    @ApiModelProperty(value = "物料分类名称")
    private String matTypeName;

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

    public Long getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(Long applyCount) {
        this.applyCount = applyCount;
    }

    public String getMatTypeName() {
        return matTypeName;
    }

    public void setMatTypeName(String matTypeName) {
        this.matTypeName = matTypeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Long buyCount) {
        this.buyCount = buyCount;
    }

    public String getMatTypeId() {
        return matTypeId;
    }

    public void setMatTypeId(String matTypeId) {
        this.matTypeId = matTypeId;
    }
}

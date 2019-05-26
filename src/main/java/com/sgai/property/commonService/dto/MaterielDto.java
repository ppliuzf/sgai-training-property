package com.sgai.property.commonService.dto;

import io.swagger.annotations.ApiModelProperty;

public class MaterielDto {
    @ApiModelProperty(value = "物料分类ID")
    private String id;
    @ApiModelProperty(value = "物料分类编码")
    private String matTypeCode;
    @ApiModelProperty(value = "物料分类名称")
    private String matTypeName;

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
}

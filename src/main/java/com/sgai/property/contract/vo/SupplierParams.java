package com.sgai.property.contract.vo;

import io.swagger.annotations.ApiModelProperty;

public class SupplierParams {

    @ApiModelProperty(value = "供应商名称")
    private String name;//供应商名称

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

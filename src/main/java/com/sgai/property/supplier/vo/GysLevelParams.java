package com.sgai.property.supplier.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gaojianqun on 2018/2/5.
 */
public class GysLevelParams {

    @ApiModelProperty(value = "描述")
    private String description; //描述
    @ApiModelProperty(value = "等级名称")
    private String name; //等级名称

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

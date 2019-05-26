package com.sgai.property.supplier.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gaojianqun on 2018/1/16.
 * 添加供应商类型输入实体
 */
public class GysTypeParams {

    @ApiModelProperty(value = "类型描述")
    private String description; //类型描述
    @ApiModelProperty(value = "类型名称")
    private String name; //类型名称

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

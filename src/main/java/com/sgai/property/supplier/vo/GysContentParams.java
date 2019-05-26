package com.sgai.property.supplier.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gaojianqun on 2018/1/17.
 */
public class GysContentParams {

    @ApiModelProperty(value = "内容描述")
    private String description; //内容描述
    @ApiModelProperty(value = "内容名称")
    private String name; //内容名称

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

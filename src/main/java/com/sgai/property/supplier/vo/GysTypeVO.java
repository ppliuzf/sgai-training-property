package com.sgai.property.supplier.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gaojianqun on 2018/1/17.
 * 供应商类型实体输出
 */
public class GysTypeVO {

    @ApiModelProperty(value = "类型描述")
    private String id; //类型描述
    @ApiModelProperty(value = "类型描述")
    private String description; //类型描述
    @ApiModelProperty(value = "类型名称")
    private String name; //类型名称
    @ApiModelProperty(value = "关联供应商数量")
    private Integer count; //关联供应商数量
    @ApiModelProperty(value = "是否删除标识")
    private Long isDelete; //是否删除标识

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Long isDelete) {
        this.isDelete = isDelete;
    }
}

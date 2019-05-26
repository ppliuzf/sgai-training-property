package com.sgai.property.supplier.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by gaojianqun on 2018/2/5.
 */
public class GysLevelVO {

    @ApiModelProperty(value = "唯一标识")
    private String id; //唯一标识
    @ApiModelProperty(value = "等级描述")
    private String description; //等级描述
    @ApiModelProperty(value = "等级名称")
    private String name; //等级名称
    @ApiModelProperty(value = "关联供应商数量")
    private Integer levelCount; //关联供应商数量
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

    public Integer getLevelCount() {
        return levelCount;
    }

    public void setLevelCount(Integer levelCount) {
        this.levelCount = levelCount;
    }
}

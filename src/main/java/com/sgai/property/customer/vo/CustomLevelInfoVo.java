package com.sgai.property.customer.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 客户级别vo
 *
 * @author hou
 * @date 2017-12-25 9:06
 */
public class CustomLevelInfoVo implements Serializable {

    @ApiModelProperty(value = "id")
    private String clId; //描述

    @ApiModelProperty(value = "级别名称")
    private String levelName; //级别名称

    @ApiModelProperty(value = "是否删除")
    private Long clIsDelete; //是否删除

    @ApiModelProperty(value = "描述")
    private String levelDesc; //描述

    @ApiModelProperty(value = "级别状态(0:默认,1:自定义)")
    private Long levelType; //级别状态(0:默认,1:自定义)

    public Long getLevelType() {
        return levelType;
    }

    public CustomLevelInfoVo setLevelType(Long levelType) {
        this.levelType = levelType;
        return this;
    }

    public String getClId() {
        return clId;
    }

    public CustomLevelInfoVo setClId(String clId) {
        this.clId = clId;
        return this;
    }

    public String getLevelName() {
        return levelName;
    }

    public CustomLevelInfoVo setLevelName(String levelName) {
        this.levelName = levelName;
        return this;
    }

    public Long getClIsDelete() {
        return clIsDelete;
    }

    public CustomLevelInfoVo setClIsDelete(Long clIsDelete) {
        this.clIsDelete = clIsDelete;
        return this;
    }

    public String getLevelDesc() {
        return levelDesc;
    }

    public CustomLevelInfoVo setLevelDesc(String levelDesc) {
        this.levelDesc = levelDesc;
        return this;
    }
}

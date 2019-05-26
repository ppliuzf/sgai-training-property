package com.sgai.property.customer.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 客户类型Vo
 *
 * @author hou
 * @date 2017-12-22 18:28
 */
public class CustomTypeInfoVo implements Serializable {
    private String ctId;
    @ApiModelProperty(value = "类型名称")
    private String typeName; //类型名称
    @ApiModelProperty(value = "类型分类(1 个人 2 机构)")
    private Long typeClass; //类型分类(1 个人 2 机构)
    @ApiModelProperty(value = "公司ID")
    private String comId; //公司ID
    @ApiModelProperty(value = "类型描述")
    private String typeDesc; //类型描述
    @ApiModelProperty(value = "状态(0:默认,1:自定义)")
    private Long typeStatus; //状态(0:默认,1:自定义)

    public Long getTypeStatus() {
        return typeStatus;
    }

    public CustomTypeInfoVo setTypeStatus(Long typeStatus) {
        this.typeStatus = typeStatus;
        return this;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getCtId() {
        return ctId;
    }

    public CustomTypeInfoVo setCtId(String ctId) {
        this.ctId = ctId;
        return this;
    }

    public CustomTypeInfoVo setTypeName(String typeName) {
        this.typeName = typeName;
        return this;
    }

    public Long getTypeClass() {
        return typeClass;
    }

    public CustomTypeInfoVo setTypeClass(Long typeClass) {
        this.typeClass = typeClass;
        return this;
    }

    public String getComId() {
        return comId;
    }

    public CustomTypeInfoVo setComId(String comId) {
        this.comId = comId;
        return this;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public CustomTypeInfoVo setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
        return this;
    }
}

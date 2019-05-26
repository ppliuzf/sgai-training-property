package com.sgai.property.meeting.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 物料类型信息
 *
 * @author hou
 * @date 2018-01-05 9:32
 */
public class MaterielTypeInfo implements Serializable {

    @ApiModelProperty(value = "物料类型id")
    private String matTypeId;

    @ApiModelProperty(value = "物料类型code")
    private String matTypeCode;

    @ApiModelProperty(value = "物料类型名称")
    private String matTypeName;

    public String getMatTypeCode() {
        return matTypeCode;
    }

    public MaterielTypeInfo setMatTypeCode(String matTypeCode) {
        this.matTypeCode = matTypeCode;
        return this;
    }

    public String getMatTypeName() {
        return matTypeName;
    }

    public MaterielTypeInfo setMatTypeName(String matTypeName) {
        this.matTypeName = matTypeName;
        return this;
    }

    public String getMatTypeId() {
        return matTypeId;
    }

    public MaterielTypeInfo setMatTypeId(String matTypeId) {
        this.matTypeId = matTypeId;
        return this;
    }
}

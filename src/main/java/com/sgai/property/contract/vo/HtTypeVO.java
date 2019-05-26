package com.sgai.property.contract.vo;

import io.swagger.annotations.ApiModelProperty;

public class HtTypeVO{
    @ApiModelProperty(value = "类型id")
     private String id;
    @ApiModelProperty(value = "类型名称")
    private String typeName; //类型名称
    @ApiModelProperty(value = "类型描述")
    private String typeDescription; //类型描述
    @ApiModelProperty(value = "是否删除；1:是;0:否,3:不能删除，只能修改")
    private Long isDelete; //是否删除；1:是;0:否,3:不能删除，只能修改
    @ApiModelProperty(value = "关联合同数量")
    private int total;
    @ApiModelProperty(value = "合同规约值限制")
    private Double limitValue; //合同规约值限制

    public Double getLimitValue() {
        return limitValue;
    }

    public void setLimitValue(Double limitValue) {
        this.limitValue = limitValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Long isDelete) {
        this.isDelete = isDelete;
    }
}
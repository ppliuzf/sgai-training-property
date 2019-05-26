package com.sgai.property.quality.vo.plan;

import io.swagger.annotations.ApiModelProperty;

public class TypeParam{
     
    @ApiModelProperty(value = "计划类型名称")
    private String typeName; //计划类型名称
    @ApiModelProperty(value = "计划类型描述")
    private String typeDesc; //计划类型描述
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
    
}
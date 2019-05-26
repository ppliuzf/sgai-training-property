package com.sgai.property.purchase.vo;

import io.swagger.annotations.ApiModelProperty;

public class MaterielInfo {
	@ApiModelProperty(value = "物料ID")
	private String id;
	@ApiModelProperty(value = "物料名称 ")
	private String matName;  //物料名称 ,
	@ApiModelProperty(value = "物料规格")
	private String spec;	//物料规格 ,
	@ApiModelProperty(value = "物料分类名称")
	private String matTypeName; //物料分类名称 
	@ApiModelProperty(value = "物料分类编码 ")
	private String matTypeCode;  //物料分类编码 ,
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMatName() {
		return matName;
	}
	public void setMatName(String matName) {
		this.matName = matName;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getMatTypeCode() {
		return matTypeCode;
	}
	public void setMatTypeCode(String matTypeCode) {
		this.matTypeCode = matTypeCode;
	}
	public String getMatTypeName() {
		return matTypeName;
	}
	public void setMatTypeName(String matTypeName) {
		this.matTypeName = matTypeName;
	}
	
}

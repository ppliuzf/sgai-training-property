package com.sgai.property.quality.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 品质检验
 * @author wuzhihui
 *
 */
public class QualityInspectionVo {
	
	//类型 0：专业范畴
	public static final int PROFESSIONAL_CATEGORY=0;
	//类型1：缺陷整改
	public static final int DEFACT_RECTIFICATION=1;
	@ApiModelProperty(value = "图片")
	private String icon;
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "专业范畴id")
	private String id;
	@ApiModelProperty(value = "类型(0:专业范畴,1:缺陷整改)")
	private Integer type;
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}

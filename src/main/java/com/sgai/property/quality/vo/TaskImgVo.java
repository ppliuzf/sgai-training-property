package com.sgai.property.quality.vo;

import io.swagger.annotations.ApiModelProperty;

public class TaskImgVo {
    
	@ApiModelProperty(value = "图片地址")
	private String imgUrl; //图片地址
    @ApiModelProperty(value = "是否是默认图片(1:是;0:否)")
    private Long isDefault; //是否是默认图片(1:是;0:否)
    
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Long getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Long isDefault) {
		this.isDefault = isDefault;
	}
    
}
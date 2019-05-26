package com.sgai.property.quality.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 备注信息
 * @author wuzhihui
 *
 */
public class RemarkVo {
	@ApiModelProperty(value = "检查项id")
	private String id;
	@ApiModelProperty(value = "文字备注")
	private String remark;
	@ApiModelProperty(value = "图片url")
	private String url;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}

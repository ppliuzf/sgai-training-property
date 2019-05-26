package com.sgai.property.quality.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 检查项标准信息
 * @author wuzhihui
 *
 */
public class StandardInfoVo {
	@ApiModelProperty(value = "检查项名称")
	private String name;
	@ApiModelProperty(value = "专业范畴名称")
	private String professionalCategory;
	@ApiModelProperty(value = "创建人名称")
	private String createName;
	@ApiModelProperty(value = "创建时间")
	private Long createTime;
	@ApiModelProperty(value = "图片")
	private String url;
	@ApiModelProperty(value = "内容")
	private String content;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfessionalCategory() {
		return professionalCategory;
	}
	public void setProfessionalCategory(String professionalCategory) {
		this.professionalCategory = professionalCategory;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}

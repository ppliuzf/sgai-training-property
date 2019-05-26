package com.sgai.property.budget.vo;

import io.swagger.annotations.ApiModelProperty;

public class TemplateParam {
     

    @ApiModelProperty(value = "模板名称")
    private String templateName; //模板名称
    @ApiModelProperty(value = "模板内容（json串）")
    private String content; //模板内容（json串）
    @ApiModelProperty(value = "描述")
    private String description; //描述
    @ApiModelProperty(value = "周期类型（1:全年2:半年3季度4:月）")
    private Long cycle; //周期类型（1:全年2:半年3季度4:月）
    
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getCycle() {
		return cycle;
	}
	public void setCycle(Long cycle) {
		this.cycle = cycle;
	}
	
}
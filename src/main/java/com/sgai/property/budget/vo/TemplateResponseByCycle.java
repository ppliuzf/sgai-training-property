package com.sgai.property.budget.vo;
import io.swagger.annotations.ApiModelProperty;
public class TemplateResponseByCycle {
	
	@ApiModelProperty(value = "模板id")
	private String id;
    @ApiModelProperty(value = "模板名称")
    private String templateName; //模板名称
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
    
}
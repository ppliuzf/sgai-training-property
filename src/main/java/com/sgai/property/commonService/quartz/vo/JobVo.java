package com.sgai.property.commonService.quartz.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class JobVo implements Serializable,Cloneable {
	 @ApiModelProperty(value = "任务名，建议采用‘项目名_任务名’",required =true)
     private String name;
	 @ApiModelProperty(value = "任务组,默认“DEFAULT”")
     private String group;
	 @ApiModelProperty(value = "时间表达式,例如:0 0 1 * * ?",required =true)
     private String expression;
//	 @ApiModelProperty(value = "部门ID")
//     private String targetClass;
//	 @ApiModelProperty(value = "部门ID")
//     private String targetMehtod;
	 @ApiModelProperty(value = "服务功能描述",required =true)
     private String desc;

	@ApiModelProperty(value = "回调接口,必须是get请求接口，只适合springcloud的服务方法,格式如:http://注册的服务名/类服务名/方法名",required =true)
	private String callBackUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCallBackUrl() {
		return callBackUrl;
	}

	public void setCallBackUrl(String callBackUrl) {
		this.callBackUrl = callBackUrl;
	}
}

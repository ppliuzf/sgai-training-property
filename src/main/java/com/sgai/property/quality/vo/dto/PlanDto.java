package com.sgai.property.quality.vo.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 新增方案dto
 * @author wuzhihui
 *
 */
public class PlanDto {
	  @ApiModelProperty(value = "方案名称")
	  private String name;//方案名称',
	  @ApiModelProperty(value = "专业范畴id")
	  private Long pcId;//专业范畴id
	  @ApiModelProperty(value = "专业范畴名称")
	  private String pcName;//专业范畴名称
	  @ApiModelProperty(value = "方案说明")
	  private String description;//方案说明

}

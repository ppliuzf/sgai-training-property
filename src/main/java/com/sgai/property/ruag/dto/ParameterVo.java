
package com.sgai.property.ruag.dto;

/**
 * @ClassName: parameterVo
 * @com.sgai.property.commonService.vo;(参数变量VO类)
 * @author 王天尧
 * @date 2018年7月13日
 * @Company 首自信--智慧城市创新中心
 */

public class ParameterVo {

	private String tag;// 变量标识

	private String name;// 变量名称

	private String time;// 时间

	private String value;// 值

	private String unit;// 单位

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}

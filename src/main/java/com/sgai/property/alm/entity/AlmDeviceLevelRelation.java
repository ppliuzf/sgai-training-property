package com.sgai.property.alm.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

import io.swagger.annotations.ApiModelProperty;


/**
 * 
    * ClassName: AlmDeviceLevelRelation  
    * com.sgai.property.commonService.vo;(设备与报警等级关系Entity)
    * @author ASUS  
    * Date 2017年11月26日  
    * Company 首自信--智慧城市创新中心
 */
public class AlmDeviceLevelRelation extends BoEntity<AlmDeviceLevelRelation> {
	@ApiModelProperty(value = "主键")
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "等级编码")
	private String levelCode;		// 等级编码
	@ApiModelProperty(value = "等级名称")
	private String levelName;		// 等级名称
	@ApiModelProperty(value = "专业代码")
	private String profCode;		// 专业代码
	@ApiModelProperty(value = "专业名称")
	private String profName;		// 专业名称
	@ApiModelProperty(value = "类型编码")
	private String classCode;		// 类型编码
	@ApiModelProperty(value = "类型名称")
	private String className;		// 类型名称
	@ApiModelProperty(value = "报警分类编码")
	private String alermTypeCode;		// 报警分类编码
	@ApiModelProperty(value = "报警分类名称")
	private String alermTypeName;		// 报警分类名称
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	
	public AlmDeviceLevelRelation() {
		super();
	}

	public AlmDeviceLevelRelation(String id){
		super(id);
	}

	@Length(min=0, max=60, message="等级编码长度必须介于 0 和 60 之间")
	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	
	@Length(min=0, max=60, message="等级名称长度必须介于 0 和 60 之间")
	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	@Length(min=0, max=60, message="专业代码长度必须介于 0 和 60 之间")
	public String getProfCode() {
		return profCode;
	}

	public void setProfCode(String profCode) {
		this.profCode = profCode;
	}
	
	@Length(min=0, max=60, message="专业名称长度必须介于 0 和 60 之间")
	public String getProfName() {
		return profName;
	}

	public void setProfName(String profName) {
		this.profName = profName;
	}
	
	@Length(min=0, max=60, message="类型编码长度必须介于 0 和 60 之间")
	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	
	@Length(min=0, max=60, message="类型名称长度必须介于 0 和 60 之间")
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	@Length(min=0, max=60, message="报警分类编码长度必须介于 0 和 60 之间")
	public String getAlermTypeCode() {
		return alermTypeCode;
	}

	public void setAlermTypeCode(String alermTypeCode) {
		this.alermTypeCode = alermTypeCode;
	}
	
	@Length(min=0, max=60, message="报警分类名称长度必须介于 0 和 60 之间")
	public String getAlermTypeName() {
		return alermTypeName;
	}

	public void setAlermTypeName(String alermTypeName) {
		this.alermTypeName = alermTypeName;
	}
	
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
	
}
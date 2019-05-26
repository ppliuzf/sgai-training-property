package com.sgai.property.alm.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
 * 
    * ClassName: AlmAlermClass  
    * com.sgai.property.commonService.vo;(报警分类Entity)
    * @author 王天尧  
    * Date 2017年11月26日  
    * Company 首自信--智慧城市创新中心
 */
public class AlmAlermClass extends BoEntity<AlmAlermClass> {
	@ApiModelProperty(value = "主键")
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "分类编号")
	private String alermTypeCode;		// 分类编码
	@ApiModelProperty(value = "分类名称")
	private String alermTypeName;		// 分类名称
	@ApiModelProperty(value = "分类说明")
	private String alermTypeDesc;		// 分类说明
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	
	public AlmAlermClass() {
		super();
	}

	public AlmAlermClass(String id){
		super(id);
	}

	@Length(min=0, max=60, message="分类编码长度必须介于 0 和 60 之间")
	public String getAlermTypeCode() {
		return alermTypeCode;
	}

	public void setAlermTypeCode(String alermTypeCode) {
		this.alermTypeCode = alermTypeCode;
	}
	
	@Length(min=0, max=60, message="分类名称长度必须介于 0 和 60 之间")
	public String getAlermTypeName() {
		return alermTypeName;
	}

	public void setAlermTypeName(String alermTypeName) {
		this.alermTypeName = alermTypeName;
	}
	
	@Length(min=0, max=60, message="分类说明长度必须介于 0 和 60 之间")
	public String getAlermTypeDesc() {
		return alermTypeDesc;
	}

	public void setAlermTypeDesc(String alermTypeDesc) {
		this.alermTypeDesc = alermTypeDesc;
	}
	
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
}
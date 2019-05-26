package com.sgai.property.ctl.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
    * ClassName: CtlBusiModeRelation  
    * Description: (业务模块打包实体类)
    * @author 王天尧 
    * Date 2017年11月21日  
    * Company 首自信--智慧城市创新中心
 */
public class CtlBusiModeRelation extends BoEntity<CtlBusiModeRelation> {
	@ApiModelProperty(value = "主键")
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "系统代码")
	private String busiCode;		// 系统代码
	@ApiModelProperty(value = "系统代码")
	private String busiName;		// 系统名称
	@ApiModelProperty(value = "模块代码")
	private String sbsCode;		// 子系统代码
	@ApiModelProperty(value = "模块名称")
	private String sbsName;		// 子系统名称
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	
	public CtlBusiModeRelation() {
		super();
	}

	public CtlBusiModeRelation(String id){
		super(id);
	}

	@Length(min=1, max=10, message="系统代码长度必须介于 1 和 10 之间")
	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}
	
	@Length(min=1, max=60, message="系统名称长度必须介于 1 和 60 之间")
	public String getBusiName() {
		return busiName;
	}

	public void setBusiName(String busiName) {
		this.busiName = busiName;
	}
	
	@Length(min=1, max=10, message="子系统代码长度必须介于 1 和 10 之间")
	public String getSbsCode() {
		return sbsCode;
	}

	public void setSbsCode(String sbsCode) {
		this.sbsCode = sbsCode;
	}
	
	@Length(min=1, max=60, message="子系统名称长度必须介于 1 和 60 之间")
	public String getSbsName() {
		return sbsName;
	}

	public void setSbsName(String sbsName) {
		this.sbsName = sbsName;
	}
	
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
}
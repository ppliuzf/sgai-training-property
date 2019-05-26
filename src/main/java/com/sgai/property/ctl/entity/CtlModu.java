package com.sgai.property.ctl.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;

/**
 * 模块维护Entity
 * @author guanze
 * @version 2017-11-07
 */
public class CtlModu extends BoEntity<CtlModu> {
	
	private static final long serialVersionUID = 1L;
	private String sbsCode;		// 模块编码
	private String sbsName;		// 模块名字
	private String sbsUrl;		// 模块路径
	private Long displayOrder;		// 排序编号
	private String enabledFlag;		// 生效标志：1.选项为:'Y':是'N':否默认为'Y'
	private String sbsDesc;		// 模块描述
	
	public CtlModu() {
		super();
	}

	public CtlModu(String id){
		super(id);
	}

	@Length(min=1, max=10, message="sbs_code长度必须介于 1 和 10 之间")
	public String getSbsCode() {
		return sbsCode;
	}

	public void setSbsCode(String sbsCode) {
		this.sbsCode = sbsCode;
	}
	
	@Length(min=1, max=60, message="sbs_name长度必须介于 1 和 60 之间")
	public String getSbsName() {
		return sbsName;
	}

	public void setSbsName(String sbsName) {
		this.sbsName = sbsName;
	}
	
	@Length(min=0, max=255, message="sbs_url长度必须介于 0 和 255 之间")
	public String getSbsUrl() {
		return sbsUrl;
	}

	public void setSbsUrl(String sbsUrl) {
		this.sbsUrl = sbsUrl;
	}
	
	@NotNull(message="display_order不能为空")
	public Long getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Long displayOrder) {
		this.displayOrder = displayOrder;
	}
	
	@NotNull(message="选项为:\'Y\':是\'N\':否默认为\'Y\'长度必须介于 1 和 1 之间")
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
	@Length(min=0, max=60, message="sbs_desc长度必须介于 0 和 60 之间")
	public String getSbsDesc() {
		return sbsDesc;
	}

	public void setSbsDesc(String sbsDesc) {
		this.sbsDesc = sbsDesc;
	}
}
package com.sgai.property.ruag.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;


/**
 * 联动规则定义Entity
 * @author yangyz
 * @version 2018-01-02
 */
public class RuagLinkageRule extends BoEntity<RuagLinkageRule> {
	
	private static final long serialVersionUID = 1L;
	private String spaceCode;		// 区域编码
	private String spaceName;		// 区域名称引用节点类型的数据的名称
	private String linkageCode;		// 联动代码
	private String linkageName;		// 联动名称
	private String linkageDesc;		// 说明
	private String status;		// 是否启用Y：是N：否
	private String enabledFlag;		// 1.选项为:'Y':是'N':否默认为'Y'
	private String ruleFlag;//"Y"规则符合，指令已发送，"N",规则不符合，指令待发送
	public RuagLinkageRule() {
		super();
	}

	public RuagLinkageRule(String id){
		super(id);
	}

	@Length(min=0, max=60, message="空间编码长度必须介于 0 和 60 之间")
	public String getSpaceCode() {
		return spaceCode;
	}

	public void setSpaceCode(String spaceCode) {
		this.spaceCode = spaceCode;
	}
	@Length(min=0, max=60, message="空间名称引用节点类型的数据的名称长度必须介于 0 和 60 之间")
	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}
	
	@Length(min=0, max=60, message="联动代码长度必须介于 0 和 60 之间")
	public String getLinkageCode() {
		return linkageCode;
	}

	public void setLinkageCode(String linkageCode) {
		this.linkageCode = linkageCode;
	}
	
	@Length(min=0, max=60, message="联动名称长度必须介于 0 和 60 之间")
	public String getLinkageName() {
		return linkageName;
	}

	public void setLinkageName(String linkageName) {
		this.linkageName = linkageName;
	}
	
	@Length(min=0, max=60, message="说明长度必须介于 0 和 60 之间")
	public String getLinkageDesc() {
		return linkageDesc;
	}

	public void setLinkageDesc(String linkageDesc) {
		this.linkageDesc = linkageDesc;
	}
	
	@Length(min=0, max=60, message="是否启用Y：是N：否长度必须介于 0 和 60 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
	public String getRuleFlag() {
		return ruleFlag;
	}

	public void setRuleFlag(String ruleFlag) {
		this.ruleFlag = ruleFlag;
	}
	
}
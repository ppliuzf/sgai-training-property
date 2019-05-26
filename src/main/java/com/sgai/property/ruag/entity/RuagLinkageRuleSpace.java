package com.sgai.property.ruag.entity;

import org.hibernate.validator.constraints.Length;

import com.sgai.common.persistence.BoEntity;


/**
 * 
    * @ClassName: RuagLinkageRuleSpace  
    * @com.sgai.property.commonService.vo;(联动规则与位置的关系Entity)
    * @author 王天尧  
    * @date 2018年4月4日  
    * @Company 首自信--智慧城市创新中心
 */
public class RuagLinkageRuleSpace extends BoEntity<RuagLinkageRuleSpace> {
	
	private static final long serialVersionUID = 1L;
	private String linkageCode;		// linkage_code
	private String spaceCode;		// space_code
	
	public RuagLinkageRuleSpace() {
		super();
	}

	public RuagLinkageRuleSpace(String id){
		super(id);
	}

	@Length(min=1, max=30, message="linkage_code长度必须介于 1 和 30 之间")
	public String getLinkageCode() {
		return linkageCode;
	}

	public void setLinkageCode(String linkageCode) {
		this.linkageCode = linkageCode;
	}
	
	@Length(min=1, max=60, message="space_code长度必须介于 1 和 60 之间")
	public String getSpaceCode() {
		return spaceCode;
	}

	public void setSpaceCode(String spaceCode) {
		this.spaceCode = spaceCode;
	}
}
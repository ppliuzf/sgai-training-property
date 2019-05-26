package com.sgai.property.budget.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 *@author 严浩淼
 *@date 2018年1月19日--上午10:34:24
 */
public class ApprovalParam {

	@ApiModelProperty(value="预算id")
	private String id;
	@ApiModelProperty(value = "计划状态(0:未提交1:已提交2:已通过3:已拒绝4:已撤销)")
    private Long state; //计划状态(0:未提交1:已提交2:已通过3:已拒绝4:已撤销)
	@ApiModelProperty(value = "审核原因")
    private String approvalReason; //审核原因
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getState() {
		return state;
	}
	public void setState(Long state) {
		this.state = state;
	}
	public String getApprovalReason() {
		return approvalReason;
	}
	public void setApprovalReason(String approvalReason) {
		this.approvalReason = approvalReason;
	}
	
	
	
}

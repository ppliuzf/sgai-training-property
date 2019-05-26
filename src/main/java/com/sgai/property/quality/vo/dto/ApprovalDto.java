package com.sgai.property.quality.vo.dto;

import io.swagger.annotations.ApiModelProperty;

public class ApprovalDto {

	@ApiModelProperty(value = "审核人名片id")
	private String feedId; // 审核人名片id
	@ApiModelProperty(value = "审核人eiid")
	private String approvalId; // 审核人eiid
	@ApiModelProperty(value = "审核人名称")
	private String approvalName; // 审核人名称
	@ApiModelProperty(value = "审核人头像")
	private String icon; // 审核人头像
	@ApiModelProperty(value = "审核人部门")
	private String department; // 审核人部门

	public String getFeedId() {
		return feedId;
	}

	public void setFeedId(String feedId) {
		this.feedId = feedId;
	}

	public String getApprovalId() {
		return approvalId;
	}

	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
	}

	public String getApprovalName() {
		return approvalName;
	}

	public void setApprovalName(String approvalName) {
		this.approvalName = approvalName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}

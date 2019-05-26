package com.sgai.property.notice.vo;

import io.swagger.annotations.ApiModelProperty;

public class SendDetailVo {
    
	@ApiModelProperty(value = "发布范围字符串(部门(x人)、xxx、xxx)")
    private String infoScope;//发布范围字符串
	@ApiModelProperty(value = "审批人id")
	private String approvalEmpId;//审批人id
    @ApiModelProperty(value = "审批人名称")
    private String approvalEmpName;//审批人名称
    @ApiModelProperty(value = "发起人id")
	private String infoCreatorId;//发起人id
    @ApiModelProperty(value = "发起人名称")
    private String infoCreatorName;//发起人名称
    @ApiModelProperty(value = "发布范围全部可见？(0:否，1:是)")
    private Long infoScopeIsAll; //发布范围全部可见？(0:否，1:是)
    @ApiModelProperty(value = "是否需要回执(0：不需要回执，1：需要回执)")
    private Long infoReceiptFlag; //是否需要回执(0：不需要回执，1：需要回执)
    @ApiModelProperty(value = "是否需要审核(0：不需要审核，1：需要审核)")
    private Long infoApprovalFlag; //是否需要审核(0：不需要审核，1：需要审核)
	public String getInfoScope() {
		return infoScope;
	}
	public void setInfoScope(String infoScope) {
		this.infoScope = infoScope;
	}
	public void setApprovalEmpId(String approvalEmpId) {
		this.approvalEmpId = approvalEmpId;
	}
	public String getInfoCreatorId() {
		return infoCreatorId;
	}
	public void setInfoCreatorId(String infoCreatorId) {
		this.infoCreatorId = infoCreatorId;
	}
	public String getApprovalEmpName() {
		return approvalEmpName;
	}
	public void setApprovalEmpName(String approvalEmpName) {
		this.approvalEmpName = approvalEmpName;
	}
	public String getInfoCreatorName() {
		return infoCreatorName;
	}
	public void setInfoCreatorName(String infoCreatorName) {
		this.infoCreatorName = infoCreatorName;
	}
	public Long getInfoScopeIsAll() {
		return infoScopeIsAll;
	}
	public void setInfoScopeIsAll(Long infoScopeIsAll) {
		this.infoScopeIsAll = infoScopeIsAll;
	}
	public Long getInfoReceiptFlag() {
		return infoReceiptFlag;
	}
	public void setInfoReceiptFlag(Long infoReceiptFlag) {
		this.infoReceiptFlag = infoReceiptFlag;
	}
	public Long getInfoApprovalFlag() {
		return infoApprovalFlag;
	}
	public void setInfoApprovalFlag(Long infoApprovalFlag) {
		this.infoApprovalFlag = infoApprovalFlag;
	}

	public String getApprovalEmpId() {
		return approvalEmpId;
	}
}

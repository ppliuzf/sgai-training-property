package com.sgai.property.quality.entity;

import com.sgai.common.persistence.BoEntity;
import io.swagger.annotations.ApiModelProperty;

public class QtTaskAppoval  extends BoEntity<QtTaskAppoval> {
	@ApiModelProperty(value = "所属任务id")
	private String ttId; // 所属任务id
	@ApiModelProperty(value = "所属审批批次id")
	private String arId; // 所属审批批次id
	@ApiModelProperty(value = "名片id")
	private String feedId; // 名片id
	@ApiModelProperty(value = "审核人id")
	private Long approvalId; // 审核人id
	@ApiModelProperty(value = "审核人名称")
	private String approvalName; // 审核人名称
	@ApiModelProperty(value = "审核人头像")
	private String icon; // 审核人头像
	@ApiModelProperty(value = "审核人部门")
	private String department; // 审核人部门
	@ApiModelProperty(value = "审核状态(0:发起审核,1审核通过,2:审核未通过,3:未审核)")
	private Integer taStatus; // 审核状态(0:发起审核,1审核通过,2:审核未通过,3:未审核)
	@ApiModelProperty(value = "审核意见")
	private String taOpinion; // 审核意见
	@ApiModelProperty(value = "创建时间")
	private Long createTime; // 创建时间
	@ApiModelProperty(value = "执行时间")
	private Long updateTime; // 执行时间
	@ApiModelProperty(value = "是否有效(0:未删除,1:已删除)")
	private Integer valid; // 是否有效(0:未删除,1:已删除)

	public String getTtId() {
		return ttId;
	}

	public void setTtId(String ttId) {
		this.ttId = ttId;
	}

	public String getArId() {
		return arId;
	}

	public void setArId(String arId) {
		this.arId = arId;
	}

	public String getFeedId() {
		return feedId;
	}

	public void setFeedId(String feedId) {
		this.feedId = feedId;
	}

	public Long getApprovalId() {
		return approvalId;
	}

	public void setApprovalId(Long approvalId) {
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

	public Integer getTaStatus() {
		return taStatus;
	}

	public void setTaStatus(Integer taStatus) {
		this.taStatus = taStatus;
	}

	public String getTaOpinion() {
		return taOpinion;
	}

	public void setTaOpinion(String taOpinion) {
		this.taOpinion = taOpinion;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}
}
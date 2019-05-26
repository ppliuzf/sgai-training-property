package com.sgai.property.quality.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApprovalRecordVo {
	@ApiModelProperty(value = "审核明细id")
	private Long id;
	@ApiModelProperty(value = "审核人名称")
	private String userName;
	@ApiModelProperty(value = "审核人头像")
	private String icon;
	@ApiModelProperty(value = "部门")
	private String deptName;
	@ApiModelProperty(value = "审核时间")
	private Long checkTime;
	@ApiModelProperty(value = "审核意见")
	private String opinion;
	@ApiModelProperty(value = "审核身份(0:发起人,1:审核人)")
	private Integer idType;
	@ApiModelProperty(value = "状态(0:未审核,1审核通过,2:审核未通过)")
	private Integer status;
	@ApiModelProperty(value = "状态([发起,打回,通过])")
	private String statusDesc;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Long getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Long checkTime) {
		this.checkTime = checkTime;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public Integer getIdType() {
		return idType;
	}
	public void setIdType(Integer idType) {
		this.idType = idType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}

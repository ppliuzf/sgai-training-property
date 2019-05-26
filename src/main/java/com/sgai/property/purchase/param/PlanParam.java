package com.sgai.property.purchase.param;

import io.swagger.annotations.ApiModelProperty;

/**
 *  采购计划列表查询 on 2018/2/27.
 */
public class PlanParam {
	
	@ApiModelProperty(value = "计划id")
    private String id; //id
    @ApiModelProperty(value = "计划类型；1：采购")
    private Long planType; //计划类型
    @ApiModelProperty(value = "计划名称")
    private String planName; //计划名称
    @ApiModelProperty(value = "描述")
    private String planDescribe; //描述
    @ApiModelProperty(value = "状态? 1:待提交；2::待审核；3:已拒绝；4:已通过")
    private Long planStat; //状态? 1:待提交；2::待审核；3:已拒绝；4:已通过
    
    @ApiModelProperty(value = "采购计划部门ID")
    private String planDeptId; //采购计划部门ID
    @ApiModelProperty(value = "采购计划部门")
    private String planDept; //采购计划部门
	@ApiModelProperty(value = "是否审批:Y:获取审批列表")
	private String approveIsYup;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getPlanType() {
		return planType;
	}
	public void setPlanType(Long planType) {
		this.planType = planType;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getPlanDescribe() {
		return planDescribe;
	}
	public void setPlanDescribe(String planDescribe) {
		this.planDescribe = planDescribe;
	}
	public Long getPlanStat() {
		return planStat;
	}
	public void setPlanStat(Long planStat) {
		this.planStat = planStat;
	}
	public String getPlanDeptId() {
		return planDeptId;
	}
	public void setPlanDeptId(String planDeptId) {
		this.planDeptId = planDeptId;
	}
	public String getPlanDept() {
		return planDept;
	}
	public void setPlanDept(String planDept) {
		this.planDept = planDept;
	}

	public String getApproveIsYup() {
		return approveIsYup;
	}

	public void setApproveIsYup(String approveIsYup) {
		this.approveIsYup = approveIsYup;
	}
}

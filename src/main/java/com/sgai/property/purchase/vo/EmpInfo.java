package com.sgai.property.purchase.vo;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class EmpInfo implements Serializable {
    private Long eiId; //主键,员工编号
    private Long originEmId; //
	@ApiModelProperty(value = "组织id")
    private Long orgId; //组织id
	@ApiModelProperty(value = "组织名称")
    private String orgName; //组织名称
	@ApiModelProperty(value = "部门编号")
    private Long deptId; //部门编号
	@ApiModelProperty(value = "部门名称")
    private String deptName; //部门名称
	@ApiModelProperty(value = "岗位编号")
    private Long positionId; //岗位编号
	@ApiModelProperty(value = "岗位名称")
    private String positionName; //岗位名称
	@ApiModelProperty(value = "员工工号")
    private String eiEmpNo; //员工工号
	@ApiModelProperty(value = "员工姓名")
    private String eiEmpName; //员工姓名
	@ApiModelProperty(value = "头像")
    private String eiHeadPicture; //头像
	@ApiModelProperty(value = "手机号")
    private String eiEmpPhone; //
	@ApiModelProperty(value = "邮箱")
	private String eiEmail; //邮箱
	@ApiModelProperty(value = "toon用户ID")
    private Long toonUserId; //
	@ApiModelProperty(value = "名片feedId")
    private String feedId; //
    private Long createTime; //创建时间
    private Long updateTime; //更新时间


	public Long getUserNo() {
		return eiId;
	}

	public void setEiId(Long eiId) {
		this.eiId = eiId;
	}

	public Long getOriginEmId() {
		return originEmId;
	}

	public void setOriginEmId(Long originEmId) {
		this.originEmId = originEmId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getEiEmpNo() {
		return eiEmpNo;
	}

	public void setEiEmpNo(String eiEmpNo) {
		this.eiEmpNo = eiEmpNo;
	}

	public String getEiEmpName() {
		return eiEmpName;
	}

	public void setEiEmpName(String eiEmpName) {
		this.eiEmpName = eiEmpName;
	}

	public String getEiHeadPicture() {
		return eiHeadPicture;
	}

	public void setEiHeadPicture(String eiHeadPicture) {
		this.eiHeadPicture = eiHeadPicture;
	}

	public String getEiEmpPhone() {
		return eiEmpPhone;
	}

	public void setEiEmpPhone(String eiEmpPhone) {
		this.eiEmpPhone = eiEmpPhone;
	}

	public Long getToonUserId() {
		return toonUserId;
	}

	public void setToonUserId(Long toonUserId) {
		this.toonUserId = toonUserId;
	}

	public String getFeedId() {
		return feedId;
	}

	public void setFeedId(String feedId) {
		this.feedId = feedId;
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

	public String getEiEmail() {
		return eiEmail;
	}

	public void setEiEmail(String eiEmail) {
		this.eiEmail = eiEmail;
	}

	@Override
	public String toString() {
		return "EmpInfo[" + 
				"eiId:" + this.eiId + 
				",originEmId:" + this.originEmId + 
				",orgId:" + this.orgId + 
				",orgName:" + this.orgName + 
				",deptId:" + this.deptId + 
				",deptName:" + this.deptName + 
				",positionId:" + this.positionId + 
				",positionName:" + this.positionName + 
				",eiEmpNo:" + this.eiEmpNo + 
				",eiEmpName:" + this.eiEmpName + 
				",eiHeadPicture:" + this.eiHeadPicture + 
				",eiEmpPhone:" + this.eiEmpPhone + 
				",toonUserId:" + this.toonUserId + 
				",feedId:" + this.feedId + 
				"]";
	}

}
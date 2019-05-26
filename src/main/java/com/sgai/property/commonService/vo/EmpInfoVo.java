package com.sgai.property.commonService.vo;

import io.swagger.annotations.ApiModelProperty;

public class EmpInfoVo {
    @ApiModelProperty(value = "用户ID")
    private Long eiId;
    @ApiModelProperty(value = "用户名称")
    private String eiEmpName;
    @ApiModelProperty(value = "用户工号")
    private String eiEmpNo;
    @ApiModelProperty(value = "组织id")
    private Long orgId; //组织id
    @ApiModelProperty(value = "组织名称")
    private String orgName; //组织名称
    @ApiModelProperty(value = "用户邮箱")
    private String eiEmail;
    @ApiModelProperty(value = "用户头像")
    private String eiHeadPicture;
    @ApiModelProperty(value = "feedId")
    private String feedId;
    @ApiModelProperty(value = "部门ID")
    private Long deptId;
    @ApiModelProperty(value = "部门名称")
    private String deptName;
    @ApiModelProperty(value = "岗位ID")
    private Long positionId;
    @ApiModelProperty(value = "岗位名称")
    private String positionName;
    @ApiModelProperty(value = "部门id全路径（以'_'区分）")
    private String pathDeptId;
    @ApiModelProperty(value = "部门名称全路径(以'/'区分)")
    private String pathDeptName;
    @ApiModelProperty(value = "toon用户ID")
    private Long toonUserId; //

    public Long getToonUserId() {
        return toonUserId;
    }

    public EmpInfoVo setToonUserId(Long toonUserId) {
        this.toonUserId = toonUserId;
        return this;
    }

    public Long getUserNo() {
        return eiId;
    }

    public void setEiId(Long eiId) {
        this.eiId = eiId;
    }

    public String getEiEmpName() {
        return eiEmpName;
    }

    public void setEiEmpName(String eiEmpName) {
        this.eiEmpName = eiEmpName;
    }

    public String getEiEmpNo() {
        return eiEmpNo;
    }

    public void setEiEmpNo(String eiEmpNo) {
        this.eiEmpNo = eiEmpNo;
    }

    public String getEiEmail() {
        return eiEmail;
    }

    public void setEiEmail(String eiEmail) {
        this.eiEmail = eiEmail;
    }

    public String getEiHeadPicture() {
        return eiHeadPicture;
    }

    public void setEiHeadPicture(String eiHeadPicture) {
        this.eiHeadPicture = eiHeadPicture;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
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

    public String getPathDeptId() {
        return pathDeptId;
    }

    public void setPathDeptId(String pathDeptId) {
        this.pathDeptId = pathDeptId;
    }

    public String getPathDeptName() {
        return pathDeptName;
    }

    public void setPathDeptName(String pathDeptName) {
        this.pathDeptName = pathDeptName;
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
}

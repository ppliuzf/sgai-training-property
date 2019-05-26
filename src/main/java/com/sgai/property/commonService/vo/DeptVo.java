package com.sgai.property.commonService.vo;

import io.swagger.annotations.ApiModelProperty;

public class DeptVo {
    @ApiModelProperty(value = "部门ID")
    private Long deptId;
    @ApiModelProperty(value = "部门名称")
    private String deptName;
    @ApiModelProperty(value = "父级部门id")
    private Long parentId;
    @ApiModelProperty(value = "部门id全路径（以'_'区分）")
    private String pathDeptId;
    @ApiModelProperty(value = "部门名称全路径(以'/'区分)")
    private String pathDeptName;
    @ApiModelProperty(value = "部门人数")
    private Integer deptNum ;
    @ApiModelProperty(value = "部门下的第一个人")
    private String fristUserName ;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public Integer getDeptNum() {
        return deptNum;
    }

    public void setDeptNum(Integer deptNum) {
        this.deptNum = deptNum;
    }

    public String getFristUserName() {
        return fristUserName;
    }

    public void setFristUserName(String fristUserName) {
        this.fristUserName = fristUserName;
    }


}

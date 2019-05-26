package com.sgai.property.commonService.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class DeptEmpVo {
    @ApiModelProperty(value = "部门集合")
    private List<DeptVo> deptVoList;
    @ApiModelProperty(value = "部门下的人员信息")
    private List<EmpInfoVo> empInfoVos;

    public List<DeptVo> getDeptVoList() {
        return deptVoList;
    }

    public void setDeptVoList(List<DeptVo> deptVoList) {
        this.deptVoList = deptVoList;
    }

    public List<EmpInfoVo> getEmpInfoVos() {
        return empInfoVos;
    }

    public void setEmpInfoVos(List<EmpInfoVo> empInfoVos) {
        this.empInfoVos = empInfoVos;
    }
}

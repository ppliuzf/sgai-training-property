package com.sgai.property.common.service;

import com.sgai.common.persistence.Page;
import com.sgai.property.commonService.dto.SgaiDeptDto;
import com.sgai.property.commonService.dto.SgaiTreeNode;
import com.sgai.property.ctl.entity.CtlDept;
import com.sgai.property.ctl.entity.CtlEmp;

import java.util.List;

/**
 * @author ppliu
 * created in 2018/12/21 14:00
 */
public interface BaseSgaiOrgTreeService {
    /**
     * 获取首自信部门数据
     */
    List<SgaiDeptDto> getSgaiDept();

    Page<CtlEmp> getSgaiEmp(Integer pageNo,
                            Integer pageSize, String deptCode, String empCode, String lastname);

    /**
     * 首自信组织树接口
     */
    List<SgaiTreeNode> getSgaiTreeNode(String deptCode, String isIncludeEmp);

    /**
     * 根据部门id获取下级部门数据
     */
    List<CtlDept> getSgaiDeptDtoList(String deptCode);

    /**
     * 获取首自信员工数据
     */
    Page<CtlEmp> getSgaiEmpDtoList(Integer pageNo, Integer pageSize, String deptCode, String empCode, String lastname);

    /**
     * 全局搜索首自信员工数据
     */
    List<SgaiTreeNode> searchSgaiEmp(String lastname);

    /**
     * 根据员工id查员工信息
     */
    CtlEmp getSgaiEmpById(String empCode);

    /**
     * 根据用户名称搜索用户信息
     */
    Page<CtlEmp> searchSgaiEmpInfoByName(String empName, Integer pageNo, Integer pageSize);


    /**
     * 根据用户名称、部门code,用户code搜索用户信息
     */
    Page<CtlEmp> searchSgaiEmpInfoByParams(String empName, String deptCode, String empCode, Integer pageNo, Integer pageSize);


}

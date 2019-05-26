package com.sgai.property.common.service;

import com.sgai.property.commonService.vo.DeptEmpVo;
import com.sgai.property.commonService.vo.DeptVo;
import com.sgai.property.ctl.entity.CtlDept;

import java.util.List;
import java.util.Map;

/**
 * 部门基础数据.
 *
 * @author ppliu
 * created in 2018/12/21 13:32
 */
public interface BaseDepartmentService {
    /**
     * 获取根部门信息.
     */
    DeptVo getRootDeptInfo(Long companyId);

    /**
     * 根据部门Id查询部门信息
     */
    DeptVo getDeptInfoById(Long companyId, Long departmentId);

    /**
     * 根据部门Id集合查询部门信息
     */
    List<DeptVo> getDeptInfoByIds(Long companyId, List<Long> departmentIds);

    /**
     * 根据部门Id集合查询部门人数
     */
    Map<Long, Integer> getDeptEmpNumByIds(Long companyId, List<Long> departmentIds);

    /**
     * 获取部门Id获取子部门集合信息
     */
    DeptEmpVo getDeptListById(Long companyId, Long departmentId);

    /**
     * 多部门代码批量查询部门信息
     */
    List<CtlDept> findDeptsByCodes(String departmentCodes);
}

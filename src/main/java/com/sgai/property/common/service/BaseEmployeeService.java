package com.sgai.property.common.service;

import com.sgai.common.persistence.Page;
import com.sgai.property.commonService.entity.EmpInfo;
import com.sgai.property.commonService.vo.EmpInfoVo;
import com.sgai.property.commonService.vo.EmpSimpleInfoVo;
import com.sgai.property.ctl.entity.CtlEmp;

import java.util.List;

/**
 * 用户基础数据.
 *
 * @author ppliu
 * created in 2018/12/21 13:33
 */
public interface BaseEmployeeService {
    /**
     * 获取人员详细信息
     */
    EmpInfoVo getEmpInfoById(Long comId, String eiId);


    /**
     * 获取人员id集合获取人员详细信息
     */

    List<EmpInfo> getEmpInfoByEiIds(Long comId, List<Long> eiIds);

    /**
     * 根据feedId查询人员详细信息
     */
    EmpInfo getEmpInfoByfeedId(Long comId, String feedId);


    /**
     * 根据用户名称搜索用户信息
     */
    Page<EmpInfo> searchEmpInfoByName(Long comId, String eiEmpName, Integer pageNum, Integer pageSize);


    /**
     * 根据部门id查询用户信息
     */
    List<EmpSimpleInfoVo> findEmpInfoByDeptId(Long comId, List<Long> deptIds, Integer cascade);

    /**
     * 获取组织下所有人的信息
     */
    List<EmpInfo> getOrgEveryOne(String orgId, String appId, String accessId, String accessSecret);

    Page<CtlEmp> getSgaiEmpDtoList(String sgaiToken, Integer pageNo, Integer pageSize, String deptCode, String empCode, String lastname);
}

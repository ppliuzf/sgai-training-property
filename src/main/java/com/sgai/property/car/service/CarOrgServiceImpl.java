package com.sgai.property.car.service;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.service.BaseDepartmentService;
import com.sgai.property.common.service.BaseEmployeeService;
import com.sgai.property.common.service.BaseOrgTreeService;
import com.sgai.property.common.service.BaseSgaiOrgTreeService;
import com.sgai.property.commonService.entity.EmpInfo;
import com.sgai.property.commonService.vo.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取组织树、用户信息
 *
 * @author 147472
 */
@Service
public class CarOrgServiceImpl {
    private static final Logger logger = LogManager.getLogger(CarOrgServiceImpl.class);

    @Autowired
    private BaseEmployeeService baseEmployeeService;
    @Autowired
    private BaseDepartmentService baseDepartmentService;
    @Autowired
    private BaseOrgTreeService baseOrgTreeService;

    public List<OrgTreeNode> getOrgTreeById(Long comId, Long deptId, Integer isIncludeEmp) {
        return baseOrgTreeService.getDeptListById(comId, deptId, isIncludeEmp);
    }

    public List<EmpSimpleInfoVo> findEmpInfoByDeptId(Long comId, List<Long> deptIds, Integer cascade) {
        return baseEmployeeService.findEmpInfoByDeptId(comId, deptIds, cascade);
    }

    public List<OrgTreeNode> searchOrgTree(Long comId, String eiEmpName) {
        return baseOrgTreeService.searchOrgTree(comId, eiEmpName);
    }

    public Response<EmpSearchResult> searchSgaiEmpInfoByName(String accessToken, String keyword, Integer pageNum, Integer pageSize) {
        Response<EmpSearchResult> response = new Response<EmpSearchResult>();
        EmpSearchResult result = null;
        try {
            if ("%".equals(keyword)) {
                return response;
            }
            Page<EmpInfo> pageInfo = baseEmployeeService.searchEmpInfoByName(Long.valueOf(accessToken), keyword, pageNum, pageSize);
            if (pageInfo != null && pageInfo.getList() != null && pageInfo.getList().size() > 0) {
                result = new EmpSearchResult();
                List<EmpInfoVo> list = new ArrayList<>();
                List<EmpInfo> empInfoList = pageInfo.getList();
                if (empInfoList != null) {
                    for (EmpInfo emp : empInfoList) {
                        EmpInfoVo vo = new EmpInfoVo();
                        vo.setEiEmpName(emp.getEiEmpName());
                        vo.setEiEmpNo(emp.getEiEmpNo());
                        vo.setPathDeptName(emp.getDeptName());
                        list.add(vo);
                    }
                    result.setList(list);
                    result.setTotal(pageInfo.getCount());
                }
            }
            response.setData(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


    public Response<EmpSearchResult> searchEmpInfoByName(Long comId, String eiEmpName, Integer pageNum,
                                                         Integer pageSize) {
        Response<EmpSearchResult> response = new Response<EmpSearchResult>();
        EmpSearchResult result = null;
        try {
            if ("%".equals(eiEmpName)) {
                return response;
            }
            Page<EmpInfo> pageInfo = baseEmployeeService.searchEmpInfoByName(comId, eiEmpName, pageNum, pageSize);
            if (pageInfo != null && pageInfo.getList() != null && pageInfo.getList().size() > 0) {
                result = new EmpSearchResult();
                List<EmpInfoVo> list = new ArrayList<>();
                List<EmpInfo> empInfoList = pageInfo.getList();
                if (empInfoList != null) {
                    for (EmpInfo emp : empInfoList) {
                        EmpInfoVo vo = new EmpInfoVo();
                        vo.setEiId(emp.getUserNo());
                        vo.setEiEmpName(emp.getEiEmpName());
                        vo.setDeptId(emp.getDeptId());
                        vo.setDeptName(emp.getDeptName());
                        vo.setEiEmail(emp.getEiEmail());
                        vo.setEiEmpNo(emp.getEiEmpNo());
                        vo.setEiHeadPicture(emp.getEiHeadPicture());
                        vo.setFeedId(emp.getFeedId());
                        vo.setOrgId(emp.getOrgId());
                        vo.setOrgName(emp.getOrgName());
                        vo.setToonUserId(emp.getToonUserId());
                        DeptVo dept = baseDepartmentService.getDeptInfoById(comId, emp.getDeptId());
                        if (dept != null) {
                            vo.setPathDeptId(dept.getPathDeptId());
                            vo.setPathDeptName(dept.getPathDeptName());
                        }
                        list.add(vo);
                    }
                    result.setList(list);
                    result.setTotal(pageInfo.getCount());
                }
            }
            response.setData(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

}

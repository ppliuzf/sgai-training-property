package com.sgai.property.quality.service;


import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.service.BaseEmployeeService;
import com.sgai.property.common.service.BaseOrgTreeService;
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
public class OrgServiceImpl {
    private static final Logger logger = LogManager.getLogger(OrgServiceImpl.class);

    @Autowired
    private BaseOrgTreeService baseOrgTreeService;
    @Autowired
    private BaseEmployeeService baseEmployeeService;

    public Response<List<OrgTreeNode>> getOrgTreeById(Long comId, Long deptId, Integer isIncludeEmp) {
        try {
            return new Response<>(baseOrgTreeService.getDeptListById(comId, deptId, isIncludeEmp));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response<>();
    }

    public Response<List<EmpSimpleInfoVo>> findEmpInfoByDeptId(Long comId, List<Long> deptIds, Integer cascade) {
        try {
            return new Response<>(baseEmployeeService.findEmpInfoByDeptId(comId, deptIds, cascade));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response<>();
    }

    public Response<List<OrgTreeNode>> searchOrgTree(Long comId, String eiEmpName) {
        try {
            return new Response<>(baseOrgTreeService.searchOrgTree(comId, eiEmpName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response<>();
    }

    public Response<EmpSearchResult> searchSgaiEmpInfoByName(String accessToken, String keyword, Integer pageNum, Integer pageSize) {
        Response<EmpSearchResult> response = new Response<EmpSearchResult>();
        EmpSearchResult result = null;
        try {
            if ("%".equals(keyword)) {
                return response;
            }
            Page<EmpInfo> pageInfo = baseEmployeeService.searchEmpInfoByName(UserServletContext.getUserInfo().getCompanyId(),keyword, pageNum, pageSize);
            if (pageInfo != null && pageInfo.getList() != null && pageInfo.getList().size() > 0) {
                result = new EmpSearchResult();
                List<EmpInfoVo> list = new ArrayList<>();
                List<EmpInfo> empInfoList = pageInfo.getList();
                if (empInfoList != null) {
                    for (EmpInfo emp : empInfoList) {
                        EmpInfoVo vo = new EmpInfoVo();
                        vo.setEiEmpNo(emp.getEiEmpNo());
                        vo.setEiEmpName(emp.getEiEmpName());
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
                        vo.setFeedId(emp.getFeedId());
                        vo.setEiHeadPicture(emp.getEiHeadPicture());
                        vo.setOrgId(emp.getOrgId());
                        vo.setOrgName(emp.getOrgName());
                        vo.setToonUserId(emp.getToonUserId());
                        Response<DeptVo> resDept = null;//commonsRomeotService.getDeptInfoById(comId, emp.getDeptId());
                        DeptVo dept = resDept.getData();
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

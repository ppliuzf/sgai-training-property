package com.sgai.property.notice.service;

import java.util.ArrayList;
import java.util.List;

import com.sgai.property.common.service.BaseDepartmentService;
import com.sgai.property.common.service.BaseEmployeeService;
import com.sgai.property.common.service.BaseOrgTreeService;
import com.sgai.property.common.service.BaseSgaiOrgTreeService;
import com.sgai.property.commonService.entity.EmpInfo;
import com.sgai.property.commonService.vo.DeptVo;
import com.sgai.property.commonService.vo.EmpInfoVo;
import com.sgai.property.commonService.vo.EmpSearchResult;
import com.sgai.property.commonService.vo.EmpSimpleInfoVo;
import com.sgai.property.commonService.vo.OrgTreeNode;
import com.sgai.property.commonService.vo.Response;
import com.sgai.property.ctl.entity.CtlEmp;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgai.common.persistence.Page;

/**
 * 获取组织树、用户信息
 *
 * @author 147472
 */
@Service
public class NoticeOrgServiceImpl {
    private static final Logger logger = LogManager.getLogger(NoticeOrgServiceImpl.class);

    @Autowired
    private BaseDepartmentService baseDepartmentService;
    @Autowired
    private BaseEmployeeService baseEmployeeService;
    @Autowired
    private BaseOrgTreeService baseOrgTreeService;
    @Autowired
    private BaseSgaiOrgTreeService baseSgaiOrgTreeService;

    public Response<List<OrgTreeNode>> getOrgTreeById(String comId, Long deptId, Integer isIncludeEmp) {
        Response<List<OrgTreeNode>> response = new Response<List<OrgTreeNode>>();
        try {
            response.setData(baseOrgTreeService.getDeptListById(Long.valueOf(comId), deptId, isIncludeEmp));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public Response<List<EmpSimpleInfoVo>> findEmpInfoByDeptId(String comId, List<Long> deptIds, Integer cascade) {
        Response<List<EmpSimpleInfoVo>> response = new Response<List<EmpSimpleInfoVo>>();
        try {
            response.setData(baseEmployeeService.findEmpInfoByDeptId(Long.valueOf(comId), deptIds, cascade));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public Response<List<OrgTreeNode>> searchOrgTree(String comId, String eiEmpName) {
        Response<List<OrgTreeNode>> response = new Response<List<OrgTreeNode>>();
        try {
            response.setData(baseOrgTreeService.searchOrgTree(Long.valueOf(comId), eiEmpName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public Response<EmpSearchResult> searchEmpInfoByName(String comId, String eiEmpName, Integer pageNum,
                                                         Integer pageSize) {
        Response<EmpSearchResult> response = new Response<EmpSearchResult>();
        EmpSearchResult result = null;
        try {
            if ("%".equals(eiEmpName)) {
                return response;
            }
            Page<EmpInfo> pageInfo = baseEmployeeService.searchEmpInfoByName(Long.valueOf(comId), eiEmpName, pageNum, pageSize);
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
                        DeptVo dept = baseDepartmentService.getDeptInfoById(Long.valueOf(comId), emp.getDeptId());
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

    public Response<EmpSearchResult> searchSgaiEmpInfoByName(String accessToken, String keyword, Integer pageNum, Integer pageSize) {
        Response<EmpSearchResult> response = new Response<EmpSearchResult>();
        EmpSearchResult result = null;
        try {
            if ("%".equals(keyword)) {
                return response;
            }
            Page<CtlEmp> pageInfo = baseSgaiOrgTreeService.searchSgaiEmpInfoByName(keyword, pageNum, pageSize);
            if (pageInfo != null && pageInfo.getList() != null && pageInfo.getList().size() > 0) {
                result = new EmpSearchResult();
                List<EmpInfoVo> list = new ArrayList<>();
                List<CtlEmp> empInfoList = pageInfo.getList();
                if (empInfoList != null) {
                    for (CtlEmp emp : empInfoList) {
                        EmpInfoVo vo = new EmpInfoVo();
                        vo.setEiEmpName(emp.getLastname());
                        vo.setEiEmpNo(emp.getEmpCode());
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

}

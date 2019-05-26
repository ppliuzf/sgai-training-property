package com.sgai.property.meeting.service;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.service.BaseDepartmentService;
import com.sgai.property.common.service.BaseEmployeeService;
import com.sgai.property.common.service.BaseOrgTreeService;
import com.sgai.property.common.service.BaseSgaiOrgTreeService;
import com.sgai.property.commonService.entity.EmpInfo;
import com.sgai.property.commonService.vo.*;
import com.sgai.property.ctl.entity.CtlEmp;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 146584
 * @date 2017-11-8 17:44
 */

@Service
public class MeetingEmpInfoServiceImpl {
    private static final Logger logger = LogManager.getLogger(MeetingEmpInfoServiceImpl.class);

    @Autowired
    private BaseEmployeeService baseEmployeeService;
    @Autowired
    private BaseDepartmentService baseDepartmentService;
    @Autowired
    private BaseOrgTreeService baseOrgTreeService;
    @Autowired
    private BaseSgaiOrgTreeService baseSgaiOrgTreeService;

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
                    result.setTotal(Long.parseLong(empInfoList.size() + ""));
                }
            }
            response.setData(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public EmpInfoVo getEmpInfoByEiId(Long comId, Long eiId) {
        EmpInfoVo empInfoVo = baseEmployeeService.getEmpInfoById(comId, eiId + "");
        return empInfoVo == null ? new EmpInfoVo() : empInfoVo;
    }

    public EmpInfoVo getEmpInfoByEiId(String comId, Long eiId) {
        EmpInfoVo empInfoVo = baseEmployeeService.getEmpInfoById(Long.valueOf(comId), eiId + "");
        return empInfoVo == null ? new EmpInfoVo() : empInfoVo;
    }

    public EmpInfoVo getEmpInfoByEiId(String comId, String eiId) {
        EmpInfoVo empInfoVo = baseEmployeeService.getEmpInfoById(Long.valueOf(comId), eiId + "");
        return empInfoVo == null ? new EmpInfoVo() : empInfoVo;
    }


    public CtlEmp getSgaiEmpById(String sgaiToken, String empCode) {
        return baseSgaiOrgTreeService.getSgaiEmpById(empCode);
    }

    public EmpInfoVo getEmpInfoByFeedId(Long comId, String feedId) {
        EmpInfo empInfo = baseEmployeeService.getEmpInfoByfeedId(comId, feedId);
        EmpInfoVo empInfoVo = new EmpInfoVo();
        if (empInfo != null) {
            BeanUtils.copyProperties(empInfo, empInfoVo);
        }
        return empInfoVo;
    }

    public List<OrgTreeNode> getOrgTreeById(Long comId, Long deptId, Integer isIncludeEmp) {
        return baseOrgTreeService.getDeptListById(comId, deptId, isIncludeEmp);
    }

    /**
     */
    public List<OrgTreeNode> searchOrgTree(Long comId, String eiEmpName) {
        return baseOrgTreeService.searchOrgTree(comId, eiEmpName);
    }

    /**
     */
    public List<EmpSimpleInfoVo> findEmpInfoByDeptId(Long comId, List<Long> deptIds) {
        return baseEmployeeService.findEmpInfoByDeptId(comId, deptIds, 1);
    }

    public List<EmpSimpleInfoVo> findEmpInfoByDeptId(String comId, List<Long> deptIds) {
        return baseEmployeeService.findEmpInfoByDeptId(Long.valueOf(comId), deptIds, 1);
    }

    public Page<CtlEmp> getSgaiEmp(String deptId, String empCode, String lastname) {
        return  baseSgaiOrgTreeService.getSgaiEmp(1, 1000, deptId, empCode, lastname);
    }
    public Response<EmpSearchResult> searchSgaiEmpInfoByName(String keyword, Integer pageNum, Integer pageSize) {
        Response<EmpSearchResult> response = new Response<>();
        EmpSearchResult result = null;
        try {
            if ("%".equals(keyword)) {
                return response;
            }
            Page<EmpInfo> pageInfo = baseEmployeeService.searchEmpInfoByName(null, keyword, pageNum, pageSize);
            if (pageInfo != null && pageInfo.getList() != null && pageInfo.getList().size() > 0) {
                result = new EmpSearchResult();
                List<EmpInfoVo> list = new ArrayList<>();
                if (pageInfo.getList() != null) {
                    for (EmpInfo emp : pageInfo.getList()) {
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
}
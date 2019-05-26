package com.sgai.property.depot.service;

import com.sgai.common.persistence.Page;
import com.sgai.property.common.service.BaseEmployeeService;
import com.sgai.property.common.service.BaseOrgTreeService;
import com.sgai.property.common.service.BaseSgaiOrgTreeService;
import com.sgai.property.commonService.vo.*;
import com.sgai.property.ctl.entity.CtlEmp;
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
public class DepotOrgServiceImpl {
    private static final Logger logger = LogManager.getLogger(DepotOrgServiceImpl.class);

    @Autowired
    private BaseOrgTreeService baseOrgTreeService;
    @Autowired
    private BaseEmployeeService baseEmployeeService;
    @Autowired
    private BaseSgaiOrgTreeService baseSgaiOrgTreeService;

    public Response<List<OrgTreeNode>> getOrgTreeById(Long comId, Long deptId, Integer isIncludeEmp) {
        Response<List<OrgTreeNode>> response = new Response<List<OrgTreeNode>>();
        try {
            response.setData(baseOrgTreeService.getDeptListById(comId, deptId, isIncludeEmp));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public Response<List<EmpSimpleInfoVo>> findEmpInfoByDeptId(Long comId, List<Long> deptIds, Integer cascade) {
        Response<List<EmpSimpleInfoVo>> response = new Response<List<EmpSimpleInfoVo>>();
        try {
            response.setData(baseEmployeeService.findEmpInfoByDeptId(comId, deptIds, cascade));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public Response<List<OrgTreeNode>> searchOrgTree(Long comId, String eiEmpName) {
        Response<List<OrgTreeNode>> response = new Response<List<OrgTreeNode>>();
        try {
            response.setData(baseOrgTreeService.searchOrgTree(comId, eiEmpName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


    public Response<EmpSearchResult> searchSgaiEmpInfoByName(String keyword, Integer pageNum, Integer pageSize) {
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

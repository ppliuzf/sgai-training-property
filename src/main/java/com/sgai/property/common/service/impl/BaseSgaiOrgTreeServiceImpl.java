package com.sgai.property.common.service.impl;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.support.UserServletContext;
import com.sgai.property.common.service.BaseSgaiOrgTreeService;
import com.sgai.property.commonService.dto.SgaiDeptDto;
import com.sgai.property.commonService.dto.SgaiTreeNode;
import com.sgai.property.ctl.entity.CtlDept;
import com.sgai.property.ctl.entity.CtlEmp;
import com.sgai.property.ctl.service.CtlDeptService;
import com.sgai.property.ctl.service.CtlEmpService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author ppliu
 * created in 2018/12/21 14:00
 */
@Service
public class BaseSgaiOrgTreeServiceImpl implements BaseSgaiOrgTreeService {

    @Autowired
    private CtlDeptService ctlDeptService;
    @Autowired
    private CtlEmpService ctlEmpService;

    @Override
    public List<SgaiDeptDto> getSgaiDept() {
        return ctlDeptService.getDeptList();
    }

    @Override
    public Page<CtlEmp> getSgaiEmp(Integer pageNo, Integer pageSize, String deptCode, String empCode, String lastname) {

        Page<CtlEmp> data = getSgaiEmpDtoList(pageNo, pageSize, deptCode, empCode, lastname);
        return data;
    }

    @Override
    public List<SgaiTreeNode> getSgaiTreeNode(String deptCode, String isIncludeEmp) {


        //查部门下的子部门
        List<CtlDept> sgaiDeptList = getSgaiDeptDtoList(deptCode);
        List<SgaiTreeNode> resultList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(sgaiDeptList)) {
            for (int i = 0; i < sgaiDeptList.size(); i++) {
                CtlDept dept = sgaiDeptList.get(i);
                SgaiTreeNode node = new SgaiTreeNode();
                node.setNodeId(dept.getDeptCode());
                node.setNodeName(dept.getDeptName());
                node.setNodeType(0);
                node.setEmpNum(1);
                resultList.add(node);
            }
        }
        if ("1".equals(isIncludeEmp)) {
            //查部门下的员工
            List<CtlEmp> sgaiEmpList = getSgaiEmpDtoList(0, Integer.MAX_VALUE, deptCode, null, null).getList();
            if (!CollectionUtils.isEmpty(sgaiEmpList)) {
                for (int i = 0; i < sgaiEmpList.size(); i++) {
                    CtlEmp emp = sgaiEmpList.get(i);
                    SgaiTreeNode node = new SgaiTreeNode();
                    node.setNodeId(emp.getEmpCode());
                    node.setNodeName(emp.getLastname());
                    node.setNodeType(1);
                    node.setEmpNum(0);
                    resultList.add(node);
                }
            }
        }
        return resultList;
    }

    @Override
    public List<CtlDept> getSgaiDeptDtoList(String deptCode) {
        CtlDept dept = new CtlDept();
        dept.setParentDeptCode(deptCode);
        return ctlDeptService.findList(dept);
    }

    @Override
    public Page<CtlEmp> getSgaiEmpDtoList(Integer pageNo, Integer pageSize, String deptCode, String empCode, String lastname) {
        CtlEmp ctlEmp = new CtlEmp();
        ctlEmp.setDeptCode(deptCode);
        ctlEmp.setEmpCode(empCode);
        ctlEmp.setLastname(lastname);
        ctlEmp.setComCode(UserServletContext.getUserInfo().getComCode());
        Page<CtlEmp> page = ctlEmpService.findPage(new Page<>(pageNo, pageSize), ctlEmp);

        return page;
    }

    @Override
    public List<SgaiTreeNode> searchSgaiEmp(String lastname) {
        //全局查人
        Page<CtlEmp> data = getSgaiEmpDtoList(0, Integer.MAX_VALUE, null, null, lastname);
        List<CtlEmp> sgaiEmpList = data.getList();

        List<SgaiTreeNode> resultList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(sgaiEmpList)) {
            for (int i = 0; i < sgaiEmpList.size(); i++) {
                CtlEmp emp = sgaiEmpList.get(i);
                SgaiTreeNode node = new SgaiTreeNode();
                node.setNodeId(emp.getEmpCode());
                node.setNodeName(emp.getLastname());
                node.setNodeType(1);
                node.setEmpNum(0);
                resultList.add(node);
            }
        }
        return resultList;
    }

    @Override
    public CtlEmp getSgaiEmpById(String empCode) {

        Page<CtlEmp> data = getSgaiEmpDtoList(0, Integer.MAX_VALUE, null, empCode, null);
        List<CtlEmp> sgaiEmpList = data.getList();
        if (!CollectionUtils.isEmpty(sgaiEmpList)) {
            return sgaiEmpList.get(0);
        }
        return null;
    }

    @Override
    public Page<CtlEmp> searchSgaiEmpInfoByName(String empName, Integer pageNo, Integer pageSize) {
        Page<CtlEmp> data = getSgaiEmpDtoList(pageNo, pageSize, null, null, empName);
        return data;
    }

    @Override
    public Page<CtlEmp> searchSgaiEmpInfoByParams(String empName, String deptCode, String empCode, Integer pageNo, Integer pageSize) {
        return getSgaiEmpDtoList(pageNo, pageSize, deptCode, empCode, empName);
    }
}

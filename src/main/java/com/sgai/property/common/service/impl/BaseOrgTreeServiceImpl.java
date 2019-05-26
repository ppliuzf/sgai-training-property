package com.sgai.property.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.sgai.common.persistence.Page;
import com.sgai.property.common.service.BaseOrgTreeService;
import com.sgai.property.commonService.client.RedisClient;
import com.sgai.property.commonService.entity.EmpInfo;
import com.sgai.property.commonService.service.EmpInfoServiceImpl;
import com.sgai.property.common.constants.Constants;
import com.sgai.property.common.util.StringUtil;
import com.sgai.property.commonService.vo.DeptEmpVo;
import com.sgai.property.commonService.vo.DeptVo;
import com.sgai.property.commonService.vo.EmpInfoVo;
import com.sgai.property.commonService.vo.OrgTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * 基础组织树接口.
 *
 * @author ppliu
 * created in 2018/12/21 13:59
 */
@Service
public class BaseOrgTreeServiceImpl implements BaseOrgTreeService {
    @Autowired
    private RedisClient redisClient;
    @Autowired
    EmpInfoServiceImpl empInfoService;

    @Override
    public List<OrgTreeNode> getDeptListById(Long comId, Long deptId, Integer isIncludeEmp) {
        List<OrgTreeNode> orgTreeNodeList = new LinkedList<>();
        OrgTreeNode orgTreeNode = null;
        if (deptId == null || deptId == -1) {
            Object result = redisClient.get(MessageFormat.format(Constants.DEPT_ROOT_KEY, comId.toString()));
            if (result != null) {
                orgTreeNode = new OrgTreeNode();
                DeptVo deptVo = JSON.parseObject((String) result, DeptVo.class);
                orgTreeNode.setNodeId(deptVo.getDeptId()+"");
                orgTreeNode.setNodeName(deptVo.getDeptName());
                orgTreeNode.setNodeType(Constants.ORG_TREE_DEPT_TYPE);
                orgTreeNode.setEmpNum(deptVo.getDeptNum());
                orgTreeNodeList.add(orgTreeNode);
                return orgTreeNodeList;
            }
        } else if (deptId == 0) {  //1:取一级部门
            Object result = redisClient.get(MessageFormat.format(Constants.DEPT_ROOT_KEY, comId.toString()));
            if (result != null) {
                DeptVo rootDept = JSON.parseObject((String) result, DeptVo.class);
                Object result1 = redisClient.get(MessageFormat.format(Constants.DEPT_LIST_KEY, comId.toString()) + rootDept.getDeptId());
                if (result1 != null) {
                    DeptEmpVo deptEmpVo = JSON.parseObject((String) result1, DeptEmpVo.class);
                    setOrgTreeData(orgTreeNodeList, deptEmpVo, isIncludeEmp);
                    return orgTreeNodeList;
                }
            }
        } else {
            Object result = redisClient.get(MessageFormat.format(Constants.DEPT_LIST_KEY, comId.toString()) + deptId);
            if (result != null) {
                DeptEmpVo deptEmpVo = JSON.parseObject((String) result, DeptEmpVo.class);
                setOrgTreeData(orgTreeNodeList, deptEmpVo, isIncludeEmp);
                return orgTreeNodeList;
            }
        }
        return null;
    }

    @Override
    public List<OrgTreeNode> searchOrgTree(Long comId, String eiEmpName) {
        List<OrgTreeNode> orgTreeNodeList = new LinkedList<>();
        EmpInfo empInfo = new EmpInfo();
        empInfo.setEiEmpName(StringUtil.fuzzySearchStr(eiEmpName));
        empInfo.setOrgId(comId);
        Page<EmpInfo> pageInfo = empInfoService.searchEmpInfoList(empInfo, 0, Integer.MAX_VALUE);
        List<EmpInfo> empInfoList = pageInfo.getList();
        if (empInfoList != null && empInfoList.size() > 0) {
            OrgTreeNode orgTreeNode = null;
            for (EmpInfo info : empInfoList) {
                orgTreeNode = new OrgTreeNode();
                orgTreeNode.setNodeId(info.getOriginEmId()+"");
                orgTreeNode.setNodeName(info.getEiEmpName());
                orgTreeNode.setNodeType(Constants.ORG_TREE_EMP_TYPE);
                orgTreeNode.setAvatar(info.getEiHeadPicture());
                orgTreeNode.setFeedId(info.getFeedId());
                orgTreeNode.setPositionId(info.getPositionId());
                orgTreeNode.setPositionName(info.getPositionName());
                orgTreeNodeList.add(orgTreeNode);
            }
        }
        return orgTreeNodeList;
    }

    private void setOrgTreeData(List<OrgTreeNode> orgTreeNodeList, DeptEmpVo deptEmpVo, Integer isIncludeEmp) {
        OrgTreeNode orgTreeNode = null;
        List<DeptVo> deptVoList = deptEmpVo.getDeptVoList();
        if (deptVoList != null && deptVoList.size() > 0) {
            for (DeptVo deptVo : deptVoList) {
                orgTreeNode = new OrgTreeNode();
                orgTreeNode.setNodeId(deptVo.getDeptId()+"");
                orgTreeNode.setNodeName(deptVo.getDeptName());
                orgTreeNode.setNodeType(Constants.ORG_TREE_DEPT_TYPE);
                orgTreeNode.setEmpNum(deptVo.getDeptNum());
                orgTreeNode.setPathNode(deptVo.getPathDeptId());
                orgTreeNode.setPathNodeName(deptVo.getPathDeptName());
                orgTreeNode.setFirstEmpName(deptVo.getFristUserName());
                orgTreeNodeList.add(orgTreeNode);
            }
        }
        if (isIncludeEmp != null && isIncludeEmp == 1) {
            List<EmpInfoVo> empInfoVos = deptEmpVo.getEmpInfoVos();
            if (empInfoVos != null && empInfoVos.size() > 0) {
                for (EmpInfoVo empInfoVo : empInfoVos) {
                    orgTreeNode = new OrgTreeNode();
                    orgTreeNode.setNodeId(empInfoVo.getUserNo()+"");
                    orgTreeNode.setNodeName(empInfoVo.getEiEmpName());
                    orgTreeNode.setNodeType(Constants.ORG_TREE_EMP_TYPE);
                    orgTreeNode.setAvatar(empInfoVo.getEiHeadPicture());
                    orgTreeNode.setFeedId(empInfoVo.getFeedId());
                    orgTreeNode.setPathNodeName(empInfoVo.getPathDeptName());
                    orgTreeNode.setPathNode(empInfoVo.getPathDeptId());
                    orgTreeNodeList.add(orgTreeNode);
                }
            }
        }
    }

}

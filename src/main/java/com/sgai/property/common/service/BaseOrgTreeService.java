package com.sgai.property.common.service;

import com.sgai.property.commonService.vo.OrgTreeNode;

import java.util.List;

/**
 * 基础组织树接口.
 *
 * @author ppliu
 * created in 2018/12/21 13:59
 */
public interface BaseOrgTreeService {
    /**
     * 获取部门Id获取子部门集合信息
     */
    List<OrgTreeNode> getDeptListById(Long comId, Long deptId, Integer isIncludeEmp);


    /**
     * 根据用户名称关键字搜索组织树
     */
    List<OrgTreeNode> searchOrgTree(Long comId, String eiEmpName);

   
}

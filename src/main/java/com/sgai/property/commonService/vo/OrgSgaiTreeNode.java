package com.sgai.property.commonService.vo;

import io.swagger.annotations.ApiModelProperty;

public class OrgSgaiTreeNode {

    @ApiModelProperty(value = "节点id")
    private String nodeId; //节点id

    @ApiModelProperty(value = "节点名称")
    private String nodeName; //节点名称

    @ApiModelProperty(value = "节点类型(0部门，1员工)")
    private Integer nodeType; //节点类型(0部门，1员工)

    @ApiModelProperty(value = "员工人数(包含子部门内的人)")
    private Integer empNum; //员工人数(包含子部门内的人)

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Integer getNodeType() {
        return nodeType;
    }

    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }

    public Integer getEmpNum() {
        return empNum;
    }

    public void setEmpNum(Integer empNum) {
        this.empNum = empNum;
    }
}

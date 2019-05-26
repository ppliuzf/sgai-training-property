package com.sgai.property.commonService.dto;

import io.swagger.annotations.ApiModelProperty;

public class SgaiTreeNode {

	@ApiModelProperty(value = "部门、人员id")
	private String nodeId;
	@ApiModelProperty(value = "部门、人员名称")
	private String nodeName;
	@ApiModelProperty(value = "节点类型(0:部门，1:员工)")
	private Integer nodeType;
	@ApiModelProperty(value = "员工人数(包含子部门内的人)")
	private Integer empNum ;
	
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

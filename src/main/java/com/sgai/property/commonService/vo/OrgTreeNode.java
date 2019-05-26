package com.sgai.property.commonService.vo;

import io.swagger.annotations.ApiModelProperty;

public class OrgTreeNode {
	@ApiModelProperty(value = "节点id")
	private String nodeId; //节点id
	
	@ApiModelProperty(value = "节点名称")
 	private String nodeName; //节点名称

	@ApiModelProperty(value = "岗位id")
	private Long positionId; //岗位名称
	
	@ApiModelProperty(value = "岗位名称")
 	private String positionName; //岗位名称
	
	@ApiModelProperty(value = "节点类型(0部门，1员工)")
	private Integer nodeType; //节点类型(0部门，1员工)
	
	@ApiModelProperty(value = "头像")
	private String avatar; //头像

	@ApiModelProperty(value = "是否选中(0否，1是)")
	private Integer isSelected; //是否选中(0否，1是)

	@ApiModelProperty(value = "节点全路径")
	private String pathNode; //节点路径

	@ApiModelProperty(value = "节点全路径名称")
	private String pathNodeName; //节点路径名称

	@ApiModelProperty(value = "员工人数(包含子部门内的人)")
	private Integer empNum; //员工人数(包含子部门内的人)
	
	@ApiModelProperty(value = "部门第一个员工姓名")
	private String firstEmpName; //部门第一个员工姓名

	@ApiModelProperty(value = "名片ID")
	private String feedId;

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

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public Integer getNodeType() {
		return nodeType;
	}

	public void setNodeType(Integer nodeType) {
		this.nodeType = nodeType;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(Integer isSelected) {
		this.isSelected = isSelected;
	}

	public String getPathNode() {
		return pathNode;
	}

	public void setPathNode(String pathNode) {
		this.pathNode = pathNode;
	}

	public String getPathNodeName() {
		return pathNodeName;
	}

	public void setPathNodeName(String pathNodeName) {
		this.pathNodeName = pathNodeName;
	}

	public Integer getEmpNum() {
		return empNum;
	}

	public void setEmpNum(Integer empNum) {
		this.empNum = empNum;
	}

	public String getFirstEmpName() {
		return firstEmpName;
	}

	public void setFirstEmpName(String firstEmpName) {
		this.firstEmpName = firstEmpName;
	}

	public String getFeedId() {
		return feedId;
	}

	public void setFeedId(String feedId) {
		this.feedId = feedId;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
}

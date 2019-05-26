/**
 * Project Name:smart-framework
 * File Name:MdmSpaceInfo.java
 * Package Name:com.sgai.modules.mdm.entity
 * Date:2017年11月27日上午8:55:38
 * Copyright (c) 2017, 首自信--智慧城市创新中心.
 *
*/
/**    
    * @Title: MdmSpaceInfo.java  
    * @Package com.sgai.modules.mdm.entity  
    * @com.sgai.property.commonService.vo;(用一句话描述该文件做什么)
    * @author admin  
    * @date 2017年11月27日  
    * Company 首自信--智慧城市创新中心
    * version V1.0    
    */  

package com.sgai.property.mdm.entity;

import java.util.Date;

import com.sgai.common.persistence.BoEntity;

/**
 * ClassName:MdmSpaceInfo <br/>
 *  ADD FUNCTION. <br/>
 * Reason:	  REASON. <br/>
 * Date:     2017年11月27日 上午8:55:38 <br/>
 * @author   admin
 * @version  
 * @since    JDK 1.8	 
 */
public class MdmSpaceInfo extends BoEntity<MdmSpaceInfo>{

	private static final long serialVersionUID = 1L;
	private String id;//空间id
	private String spaceNodeCode;		// 结点编码
	private String spaceNodeName;		// 结点名称
	private String spaceParentNodeCode;  //上一级的结点编码
	private String spaceGrantParentNodeCode ; //上上一级的结点编码
	private String spaceNodeUse;		// 结点用途
	private String spaceNodeType;		// 属性类别
	private String spaceNodeLevel;		// 结点 级别行政级别：区级、县级、街道
	private String spaceNodePlanChar;		// 结点 规划章程
	private String spaceNodePlanYear;		// 结点 规划年份
	private Date spaceNodeStartDate;		// 开工时间
	private Date spaceNodeEndDate;		// 竣工时间
	private String spaceNodeBuildArea ; // 建筑面积
	private String  spaceNodeFloorCount ; //楼层数
	private Double longiTude;		// 经度
	private Double latiTude;		// 纬度
	private String viewImge;	  // 图片
	private String spaceNodeDesc; //描述
	private String spaceNodeProperty; //空间属性
	private Integer version;		// 版本号
	private Date updatedDt;		// 修改时间
	private String updatedBy;		// 修改人
	private Date createdDt;		// 创建日期
	private String createdBy;		// 创建者
	
	
	public MdmSpaceInfo() {
		super();
	}

	public MdmSpaceInfo(String id){
		super(id);
	}
	

	public String getSpaceNodeCode() {
		return spaceNodeCode;
	}

	public void setSpaceNodeCode(String spaceNodeCode) {
		this.spaceNodeCode = spaceNodeCode;
	}

	public String getSpaceNodeName() {
		return spaceNodeName;
	}

	public void setSpaceNodeName(String spaceNodeName) {
		this.spaceNodeName = spaceNodeName;
	}

	public String getSpaceNodeUse() {
		return spaceNodeUse;
	}

	public void setSpaceNodeUse(String spaceNodeUse) {
		this.spaceNodeUse = spaceNodeUse;
	}

	public String getSpaceNodeType() {
		return spaceNodeType;
	}

	public void setSpaceNodeType(String spaceNodeType) {
		this.spaceNodeType = spaceNodeType;
	}

	public String getSpaceNodeLevel() {
		return spaceNodeLevel;
	}

	public void setSpaceNodeLevel(String spaceNodeLevel) {
		this.spaceNodeLevel = spaceNodeLevel;
	}

	public String getSpaceNodePlanChar() {
		return spaceNodePlanChar;
	}

	public void setSpaceNodePlanChar(String spaceNodePlanChar) {
		this.spaceNodePlanChar = spaceNodePlanChar;
	}

	public String getSpaceNodePlanYear() {
		return spaceNodePlanYear;
	}

	public void setSpaceNodePlanYear(String spaceNodePlanYear) {
		this.spaceNodePlanYear = spaceNodePlanYear;
	}

	public Date getSpaceNodeStartDate() {
		return spaceNodeStartDate;
	}

	public void setSpaceNodeStartDate(Date spaceNodeStartDate) {
		this.spaceNodeStartDate = spaceNodeStartDate;
	}

	public Date getSpaceNodeEndDate() {
		return spaceNodeEndDate;
	}

	public void setSpaceNodeEndDate(Date spaceNodeEndDate) {
		this.spaceNodeEndDate = spaceNodeEndDate;
	}

	public String getSpaceNodeBuildArea() {
		return spaceNodeBuildArea;
	}

	public void setSpaceNodeBuildArea(String spaceNodeBuildArea) {
		this.spaceNodeBuildArea = spaceNodeBuildArea;
	}

	public String getSpaceNodeFloorCount() {
		return spaceNodeFloorCount;
	}

	public void setSpaceNodeFloorCount(String spaceNodeFloorCount) {
		this.spaceNodeFloorCount = spaceNodeFloorCount;
	}

	public Double getLongiTude() {
		return longiTude;
	}

	public void setLongiTude(Double longiTude) {
		this.longiTude = longiTude;
	}

	public Double getLatiTude() {
		return latiTude;
	}

	public void setLatiTude(Double latiTude) {
		this.latiTude = latiTude;
	}

	public String getViewImge() {
		return viewImge;
	}

	public void setViewImge(String viewImge) {
		this.viewImge = viewImge;
	}


	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}


	public Date getUpdatedDt() {
		return updatedDt;
	}


	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}


	public String getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}


	public Date getCreatedDt() {
		return createdDt;
	}


	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getSpaceNodeDesc() {
		return spaceNodeDesc;
	}


	public void setSpaceNodeDesc(String spaceNodeDesc) {
		this.spaceNodeDesc = spaceNodeDesc;
	}

	public String getSpaceParentNodeCode() {
		return spaceParentNodeCode;
	}


	public void setSpaceParentNodeCode(String spaceParentNodeCode) {
		this.spaceParentNodeCode = spaceParentNodeCode;
	}

	public String getSpaceGrantParentNodeCode() {
		return spaceGrantParentNodeCode;
	}

	public void setSpaceGrantParentNodeCode(String spaceGrantParentNodeCode) {
		this.spaceGrantParentNodeCode = spaceGrantParentNodeCode;
	}

	public String getSpaceNodeProperty() {
		return spaceNodeProperty;
	}

	public void setSpaceNodeProperty(String spaceNodeProperty) {
		this.spaceNodeProperty = spaceNodeProperty;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    
	
}


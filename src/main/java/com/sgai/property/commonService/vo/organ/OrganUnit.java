package com.sgai.property.commonService.vo.organ;

/**
 * 组织单元
 * @author 147693
 *
 */
public class OrganUnit {

	private String id;	//组织单元id
	private int ordinal;	//序号
	private String organUnitName;	//组织单元
	private String parentId;	//上级组织单元id
	private String userCount;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getOrdinal() {
		return ordinal;
	}
	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}
	public String getOrganUnitName() {
		return organUnitName;
	}
	public void setOrganUnitName(String organUnitName) {
		this.organUnitName = organUnitName;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getUserCount() {
		return userCount;
	}
	public void setUserCount(String userCount) {
		this.userCount = userCount;
	}
	
	
}

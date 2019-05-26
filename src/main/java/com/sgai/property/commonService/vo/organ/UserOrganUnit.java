package com.sgai.property.commonService.vo.organ;

/**
 * 用户组织单元关系
 * @author 147693
 *
 */
public class UserOrganUnit {

	private String id;	//组织单元id
	private int ordinal;	//序号
	private String relationType;	//关系类型
	private String userType;	//用户类型
	
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
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
}

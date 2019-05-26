package com.sgai.property.commonService.vo.organ;

/**
 * 角色组织单元关系
 * @author 147693
 *
 */
public class RoleOrganUnit {

	private String id;	//组织单元id
	private int ordinal;	//序号
	private String relationType;	//关系类型
	private String roleType;	
	
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
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	
	
}

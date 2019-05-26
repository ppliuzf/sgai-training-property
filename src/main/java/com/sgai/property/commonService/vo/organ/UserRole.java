package com.sgai.property.commonService.vo.organ;

/**
 * 用户角色关系
 * @author 147693
 *
 */
public class UserRole {

	private String id;	//角色id
	private int ordinal;	//序号
	private String relationType;	//关系类型
	
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
	
	
}

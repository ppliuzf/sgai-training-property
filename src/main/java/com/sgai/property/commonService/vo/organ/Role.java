package com.sgai.property.commonService.vo.organ;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色
 * @author 147693
 *
 */
public class Role {

	private String id;	//角色id
	private String parentId;	//上级角色id
	private String belongId;
	private String roleName;	//角色
	private int ordinal;	//序号
	private List<RoleOrganUnit> depts = new ArrayList<>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getBelongId() {
		return belongId;
	}
	public void setBelongId(String belongId) {
		this.belongId = belongId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getOrdinal() {
		return ordinal;
	}
	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}
	public List<RoleOrganUnit> getDepts() {
		return depts;
	}
	public void setDepts(List<RoleOrganUnit> depts) {
		this.depts = depts;
	}
	
	
}

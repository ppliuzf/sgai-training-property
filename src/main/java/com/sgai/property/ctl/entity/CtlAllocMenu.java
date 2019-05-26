package com.sgai.property.ctl.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.sgai.common.persistence.BoEntity;

/**
 * 机构管理员菜单分配Entity
 * @author chenxing
 * @version 2017-11-10
 */
public class CtlAllocMenu extends BoEntity<CtlAllocMenu> {
	
	private static final long serialVersionUID = 1L;
	private String corrCode;		// 角色/用户对应代码
	private String corrName;		// 角色/用户对应名称
	private String category;		// R:表示角色U:表示用户
	private String comName;		// com_code
	private String menuCode;		// menu_code
	private String menuName;
	private String userType;
	
	public CtlAllocMenu() {
		super();
	}

	public CtlAllocMenu(String id){
		super(id);
	}

	@Length(min=1, max=30, message="角色/用户对应代码长度必须介于 1 和 30 之间")
	public String getCorrCode() {
		return corrCode;
	}

	public void setCorrCode(String corrCode) {
		this.corrCode = corrCode;
	}
	
	@Length(min=1, max=1, message="R:表示角色U:表示用户长度必须介于 1 和 1 之间")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	@Length(min=0, max=30, message="menu_code长度必须介于 0 和 30 之间")
	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	

	public String getCorrName() {
		return corrName;
	}

	public void setCorrName(String corrName) {
		this.corrName = corrName;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
}
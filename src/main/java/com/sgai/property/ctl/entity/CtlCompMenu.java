package com.sgai.property.ctl.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.sgai.common.persistence.BoEntity;

/**
 * 机构菜单分配Entity
 * @author chenxing
 * @version 2017-11-09
 */
public class CtlCompMenu extends BoEntity<CtlCompMenu> {
	
	private static final long serialVersionUID = 1L;
	private String comCode;		// com_code
	private String comName;
	private String menuCode;		// menu_code
	private String menuName;
	
	public CtlCompMenu() {
		super();
	}

	public CtlCompMenu(String id){
		super(id);
	}

	@Length(min=1, max=60, message="com_code长度必须介于 1 和 60 之间")
	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}
	
	@Length(min=1, max=30, message="menu_code长度必须介于 1 和 30 之间")
	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
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
	
}
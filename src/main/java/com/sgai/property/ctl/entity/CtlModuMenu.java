package com.sgai.property.ctl.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;


/**
 * 模块与菜单关系Entity
 * @author admin
 * @version 2018-03-29
 */
public class CtlModuMenu extends BoEntity<CtlModuMenu> {
	
	private static final long serialVersionUID = 1L;
	private String menuCode;		// menu_code
	
	public CtlModuMenu() {
		super();
	}

	public CtlModuMenu(String id){
		super(id);
	}
	@Length(min=1, max=30, message="menu_code长度必须介于 1 和 30 之间")
	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
}
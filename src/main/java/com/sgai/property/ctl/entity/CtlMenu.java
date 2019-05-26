package com.sgai.property.ctl.entity;

import com.sgai.common.persistence.BoEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 菜单Entity
 * @author chenxing
 * @version 2017-11-07
 */
public class CtlMenu extends BoEntity<CtlMenu> {
	
	private static final long serialVersionUID = 1L;
	private String menuCode;		// menu_code
	private String menuName;		// menu_name
	private Long displayOrder;		// display_order
	private String finalLevFlag;		// 是否是叶子节点
	private Long menuLevel;		// menu_level
	private String parentMenuCode;		// parent_menu_code
	private String parentMenuName;
	private String progCode;		// prog_code
	private String progName;
	private String progLevel;
	private String sbsName;
	private String icon;//图标
	private String enabledFlag;		// 是否可用
	
	public CtlMenu() {
		super();
	}

	public CtlMenu(String id){
		super(id);
	}

	@Length(min=1, max=30, message="menu_id长度必须介于 1 和 30 之间")
	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	
	@Length(min=1, max=60, message="menu_name长度必须介于 1 和 60 之间")
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Long getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Long displayOrder) {
		this.displayOrder = displayOrder;
	}

	@NotNull(message="display_order不能为空")


	@Length(min=1, max=1, message="是否可用长度必须介于 1 和 1 之间")
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
	@Length(min=1, max=1, message="是否是叶子节点长度必须介于 1 和 1 之间")
	public String getFinalLevFlag() {
		return finalLevFlag;
	}

	public void setFinalLevFlag(String finalLevFlag) {
		this.finalLevFlag = finalLevFlag;
	}
	
	@NotNull(message="menu_level不能为空")
	public Long getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(Long menuLevel) {
		this.menuLevel = menuLevel;
	}
	
	@Length(min=0, max=60, message="prog_id长度必须介于 0 和 60 之间")
	public String getProgCode() {
		return progCode;
	}

	public void setProgCode(String progCode) {
		this.progCode = progCode;
	}
	

	public String getParentMenuCode() {
		return parentMenuCode;
	}

	public void setParentMenuCode(String parentMenuCode) {
		this.parentMenuCode = parentMenuCode;
	}

	public String getParentMenuName() {
		return parentMenuName;
	}

	public void setParentMenuName(String parentMenuName) {
		this.parentMenuName = parentMenuName;
	}

	public String getProgName() {
		return progName;
	}

	public void setProgName(String progName) {
		this.progName = progName;
	}

	public String getProgLevel() {
		return progLevel;
	}

	public void setProgLevel(String progLevel) {
		this.progLevel = progLevel;
	}

	public String getSbsName() {
		return sbsName;
	}

	public void setSbsName(String sbsName) {
		this.sbsName = sbsName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
}
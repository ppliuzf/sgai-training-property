package com.sgai.property.ctl.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sgai.common.persistence.BoEntity;


/**
 * 子系统和菜单的关联关系Entity
 * @author admin
 * @version 2018-03-27
 */
public class CtlBusiMenu extends BoEntity<CtlBusiMenu> {
	
	private static final long serialVersionUID = 1L;
	private String busiCode;		// busi_code
	private String busiName;        //
	private String menuCode;		// menu_code
	private String menuName;
	private String defineName;      //自定义菜单名称
	private Long defineSort;     //自定义菜单排序
	
	public CtlBusiMenu() {
		super();
	}

	public CtlBusiMenu(String id){
		super(id);
	}

	@Length(min=1, max=60, message="busi_code长度必须介于 1 和 60 之间")
	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}
	
	@Length(min=1, max=30, message="menu_code长度必须介于 1 和 30 之间")
	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	

	public String getBusiName() {
		return busiName;
	}

	public void setBusiName(String busiName) {
		this.busiName = busiName;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	/**
	 * defineName.
	 *
	 * @return  the defineName
	 * @since   JDK 1.8
	 */
	public String getDefineName() {
		return defineName;
	}

	/**
	 * defineName.
	 *
	 * @param   defineName    the defineName to set
	 * @since   JDK 1.8
	 */
	public void setDefineName(String defineName) {
		this.defineName = defineName;
	}

	/**
	 * defineSort.
	 *
	 * @return  the defineSort
	 * @since   JDK 1.8
	 */
	public Long getDefineSort() {
		return defineSort;
	}

	/**
	 * defineSort.
	 *
	 * @param   defineSort    the defineSort to set
	 * @since   JDK 1.8
	 */
	public void setDefineSort(Long defineSort) {
		this.defineSort = defineSort;
	}
	
}
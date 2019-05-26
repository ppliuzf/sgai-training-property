package com.sgai.property.ctl.dao;


import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.dto.FunctionDto;
import com.sgai.property.ctl.entity.CtlBusiMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 子系统和菜单的关联关系DAO接口
 * @author admin
 * @version 2018-03-27
 */
@Mapper
public interface CtlBusiMenuDao extends CrudDao<CtlBusiMenu> {
	
	List<CtlBusiMenu>  getBusiList(CtlBusiMenu param);
	List<FunctionDto> getMenuListAll();
	List<FunctionDto> getMenuListOwn(String busiCode);
	
	void  deleteMenuTreeByBusiCode(String busiCode);
	CtlBusiMenu getBusiMenuByMenuCode(CtlBusiMenu ctlBusiMenu);
	List<String> getEmptyMenu();
}
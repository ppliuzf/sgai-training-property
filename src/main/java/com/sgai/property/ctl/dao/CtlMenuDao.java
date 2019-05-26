package com.sgai.property.ctl.dao;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.dto.FunctionDto;
import com.sgai.property.ctl.entity.CtlMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 菜单DAO接口
 * @author chenxing
 * @version 2017-11-07
 */
@Mapper
public interface CtlMenuDao extends CrudDao<CtlMenu> {
	CtlMenu getCtlMenu(CtlMenu ctlMenu);
	List<FunctionDto> getMenuList();
	List<Map<String,Object>> getProgList();
	Integer countMenuCodeExceptSelf(CtlMenu entity);
	List<FunctionDto> getCompDefineMenuList();
}
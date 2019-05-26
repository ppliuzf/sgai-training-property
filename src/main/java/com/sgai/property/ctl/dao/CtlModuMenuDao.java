package com.sgai.property.ctl.dao;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.dto.FunctionDto;
import com.sgai.property.ctl.entity.CtlModuMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 模块与菜单关系DAO接口
 * @author admin
 * @version 2018-03-29
 */
@Mapper
public interface CtlModuMenuDao extends CrudDao<CtlModuMenu> {
	List<FunctionDto> getMenuListOwn(Map<String, String> param);
	void deleteByCode(CtlModuMenu ctlModuMenu);
}
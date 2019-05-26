package com.sgai.property.ctl.dao;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.dto.FunctionDto;
import com.sgai.property.ctl.entity.CtlCompMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 机构菜单分配DAO接口
 * @author chenxing
 * @version 2017-11-09
 */
@Mapper
public interface CtlCompMenuDao extends CrudDao<CtlCompMenu> {
	List<CtlCompMenu> getCompList(CtlCompMenu param);
	List<FunctionDto> getMenuListAll();
	List<FunctionDto> getMenuListOwn(String comCode);
	void deleteMenuTreeByComCode(String comCode);
}
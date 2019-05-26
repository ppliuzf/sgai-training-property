package com.sgai.property.ctl.dao;

import com.sgai.common.persistence.CrudDao;
import com.sgai.property.ctl.dto.FunctionDto;
import com.sgai.property.ctl.entity.CtlAllocMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 机构管理员菜单分配DAO接口
 * @author chenxing
 * @version 2017-11-10
 */
@Mapper
public interface CtlAllocMenuDao extends CrudDao<CtlAllocMenu> {
	List<CtlAllocMenu> getRolePage(CtlAllocMenu param);
	List<CtlAllocMenu> getUserPage(CtlAllocMenu param);
	List<FunctionDto> getMenuList();
	List<FunctionDto> getMenuListByCom(Map<String, String> param);
	List<FunctionDto> getMenuListOwn(Map<String, String> param);
	void deleteMenuTree(Map<String, String> param);
	void deleteByCode(CtlAllocMenu param);
	List<FunctionDto> getMenuListByModu(Map<String, String> param);
	List<FunctionDto> getModuMenuListOwn(Map<String, String> param);
	List<FunctionDto> getCompDefineMenuList(Map<String, String> param);
	int findMenuByUserCode(Map<String, String> param);
}
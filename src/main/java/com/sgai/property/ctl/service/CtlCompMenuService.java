package com.sgai.property.ctl.service;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.common.util.FunctionDtoConvertUtil;
import com.sgai.property.ctl.dao.CtlCompMenuDao;
import com.sgai.property.ctl.dto.FunctionDto;
import com.sgai.property.ctl.entity.CtlCompMenu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 机构菜单分配Service
 * @author chenxing
 * @version 2017-11-09
 */
@Service
@Transactional
public class CtlCompMenuService extends CrudServiceExt<CtlCompMenuDao, CtlCompMenu> {

	public CtlCompMenu get(String id) {
		return super.get(id);
	}

	public List<CtlCompMenu> findList(CtlCompMenu ctlCompMenu) {
		return super.findList(ctlCompMenu);
	}

	public Page<CtlCompMenu> findPage(Page<CtlCompMenu> page, CtlCompMenu ctlCompMenu) {
		return super.findPage(page, ctlCompMenu);
	}

	@Transactional(readOnly = false)
	public void save(CtlCompMenu ctlCompMenu) {
		super.save(ctlCompMenu);
	}

	@Transactional(readOnly = false)
	public void delete(CtlCompMenu ctlCompMenu) {
		super.delete(ctlCompMenu);
	}

	public Page<CtlCompMenu> getCompPage(Page<CtlCompMenu> page, CtlCompMenu param) {
		param.setPage(page);
		page.setList(dao.getCompList(param));
		return page;
	}

	/**
	  *获取该机构没有的菜单
	 */
	public List<Map<String,String>> getMenuListLack(String comCode){
		List<FunctionDto> allList = dao.getMenuListAll();
		List<FunctionDto> ownList = dao.getMenuListOwn(comCode);
		List<FunctionDto> unOwnedList = allList.stream()
				.filter(all ->ownList.stream()
						.map(FunctionDto::getMenuCode)
						.collect(Collectors.toList())
						.contains(all.getMenuCode()))
				.collect(Collectors.toList());
		return FunctionDtoConvertUtil.convertDtoToMap(unOwnedList);
	}

	/**
	  *获取该机构拥有的菜单
	 */
	public List<Map<String,String>> getMenuListOwn(String comCode){
		List<FunctionDto> list = dao.getMenuListOwn(comCode);
		return FunctionDtoConvertUtil.convertDtoToMap(list);
	}

	@Transactional(readOnly = false)
	public void deleteMenuTreeByComCode(String comCode){
		dao.deleteMenuTreeByComCode(comCode);
	}

	@Transactional(readOnly = false)
	public void saveMenuTree(String comCode, List<String> menuCodeList) {
		for(String menuCode:menuCodeList) {
			CtlCompMenu ctlCompMenu = new CtlCompMenu();
			ctlCompMenu.setComCode(comCode);
			ctlCompMenu.setMenuCode(menuCode);
			save(ctlCompMenu);
		}
	}

}

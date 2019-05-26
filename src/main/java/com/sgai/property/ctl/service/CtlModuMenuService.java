package com.sgai.property.ctl.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.property.ctl.dao.CtlModuMenuDao;
import com.sgai.property.ctl.entity.CtlCompMenu;
import com.sgai.property.ctl.entity.CtlModuMenu;
import com.sgai.modules.login.jwt.bean.LoginUser;

/**
 * 模块与菜单关系Service
 * @author admin
 * @version 2018-03-29
 */
@Service
@Transactional
public class CtlModuMenuService extends CrudServiceExt<CtlModuMenuDao, CtlModuMenu> {

	public CtlModuMenu get(String id) {
		return super.get(id);
	}

	public List<CtlModuMenu> findList(CtlModuMenu ctlModuMenu) {
		return super.findList(ctlModuMenu);
	}

	public Page<CtlModuMenu> findPage(Page<CtlModuMenu> page, CtlModuMenu ctlModuMenu) {
		return super.findPage(page, ctlModuMenu);
	}

	@Transactional(readOnly = false)
	public void save(CtlModuMenu ctlModuMenu) {
		super.save(ctlModuMenu);
	}

	@Transactional(readOnly = false)
	public void delete(CtlModuMenu ctlModuMenu) {
		super.delete(ctlModuMenu);
	}
	@Transactional(readOnly = false)
	public void saveMenuTree(String moduCode, List<String> menuCodeList) {
		for(String menuCode:menuCodeList) {
			CtlModuMenu ctlModuMenu = new CtlModuMenu();
			ctlModuMenu.setModuCode(moduCode);
			ctlModuMenu.setMenuCode(menuCode);
			save(ctlModuMenu);
		}
	}
	public void deleteByCode(CtlModuMenu ctlModuMenu) {
		dao.deleteByCode(ctlModuMenu);
	}
}

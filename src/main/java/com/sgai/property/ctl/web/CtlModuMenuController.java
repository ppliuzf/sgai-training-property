package com.sgai.property.ctl.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.entity.CtlModuMenu;
import com.sgai.property.ctl.service.CtlModuMenuService;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;

/**
 * 模块与菜单关系Controller
 * @author admin
 * @version 2018-03-29
 */
@RestController
@RequestMapping(value = "${adminPath}/ctl/ctlModuMenu")
public class CtlModuMenuController extends BaseController {

	@Autowired
	private CtlModuMenuService ctlModuMenuService;
	
	@RequestMapping(value = "saveMenuTree")
	public Map<String,Object> saveMenuTree(@RequestParam(required=true) String moduCode,
			@RequestParam(required=true) String menuCodes) {
		Map<String,Object> result = Maps.newHashMap();
		LoginUser user = UserServletContext.getUserInfo();
		try {
			CtlModuMenu ctlModuMenu = new CtlModuMenu();
			ctlModuMenu.setModuCode(moduCode);
			ctlModuMenu.setComCode(user.getComCode());
			ctlModuMenuService.deleteByCode(ctlModuMenu);
			List<String> menuCodeList = Lists.newArrayList();
			String[] menuCodeArray = menuCodes.split(",");
			for(String s : menuCodeArray) {
				if(s!=null && !s.equals("")) {
					menuCodeList.add(s);
				}
			}
			ctlModuMenuService.saveMenuTree(moduCode,menuCodeList);
			result.put("state", true);
			result.put("msg", "保存成功!");
		}catch(Exception e) {
			e.printStackTrace();
			result.put("state", false);
			result.put("msg", "保存失败!");
		}
		return result;
	}
}
package com.sgai.property.ctl.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.entity.CtlCompMenu;
import com.sgai.property.ctl.service.CtlAllocMenuService;
import com.sgai.property.ctl.service.CtlCompMenuService;

/**
 * 机构菜单分配Controller
 * @author chenxing
 * @version 2017-11-09
 */
@RestController
@RequestMapping(value = "${adminPath}/compMenu/ctlCompMenu")
public class CtlCompMenuController extends BaseController {

	@Autowired
	private CtlCompMenuService ctlCompMenuService;
	
	@RequestMapping(value = "getCompPage")
	public Page<CtlCompMenu> getCompPage(CtlCompMenu param,HttpServletRequest request, HttpServletResponse response){
		Page<CtlCompMenu> page = ctlCompMenuService.getCompPage(new Page<CtlCompMenu>(request, response), param);
		return page;
	}
	
	@RequestMapping(value = "getMenuList")
	public Map<String,Object> getMenuList(@RequestParam(required=true) String comCode) {
		Map<String,Object> result = Maps.newHashMap();
		try {
			List<Map<String,String>> menuList0 = ctlCompMenuService.getMenuListLack(comCode);
			List<Map<String,String>> menuList1 = ctlCompMenuService.getMenuListOwn(comCode);
			result.put("menuList0", menuList0);
			result.put("menuList1", menuList1);
			result.put("state", true);
			result.put("msg", "查询成功!");
		}catch(Exception e) {
			e.printStackTrace();
			result.put("state", false);
			result.put("msg", "查询失败!");
		}
		return result;
	}
	
	@RequestMapping(value = "saveMenuTree")
	public Map<String,Object> saveMenuTree(@RequestParam(required=true) String comCode,
			@RequestParam(required=true) String menuCodes) {
		Map<String,Object> result = Maps.newHashMap();
		try {
			ctlCompMenuService.deleteMenuTreeByComCode(comCode);
			List<String> menuCodeList = Lists.newArrayList();
			String[] menuCodeArray = menuCodes.split(",");
			for(String s : menuCodeArray) {
				if(s!=null && !s.equals("")) {
					menuCodeList.add(s);
				}
			}
			ctlCompMenuService.saveMenuTree(comCode,menuCodeList);
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
package com.sgai.property.ctl.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sgai.common.config.Global;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.entity.CtlBusiMenu;
import com.sgai.property.ctl.entity.CtlCompMenu;
import com.sgai.property.ctl.service.CtlBusiMenuService;
import com.sgai.modules.login.support.UserServletContext;


/**
 * 子系统和菜单的关联关系Controller
 * @author admin
 * @version 2018-03-27
 */
@RestController
@RequestMapping(value = "${adminPath}/ctl/ctlBusiMenu")
public class CtlBusiMenuController extends BaseController {

	@Autowired
	private CtlBusiMenuService ctlBusiMenuService;
	
	/**
	 * 子系统分配菜单页面 左侧子系统列表
	 * getBusiPage:(这里用一句话描述这个方法的作用).
	 * @param param
	 * @param request
	 * @param response
	 * @return :Page<CtlBusiMenu> 
	 * @since JDK 1.8
	 * @author admin
	 */
	@RequestMapping(value = "getBusiPage")
	public Page<CtlBusiMenu> getBusiPage(CtlBusiMenu param,HttpServletRequest request, HttpServletResponse response){
		Page<CtlBusiMenu> page = ctlBusiMenuService.getBusiPage(new Page<CtlBusiMenu>(request, response), param);
		return page;
	}
	
	/**
	 * 获得 待分配菜单列表和已经分配菜单列表
	 * getMenuList:(这里用一句话描述这个方法的作用).
	 * @param comCode
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author admin
	 */
	@RequestMapping(value = "getMenuList")
	public Map<String,Object> getMenuList(@RequestParam(required=true) String comCode) {
		Map<String,Object> result = Maps.newHashMap();
		try {
			List<Map<String,String>> menuList0 = ctlBusiMenuService.getMenuListLack(comCode);
			List<Map<String,String>> menuList1 = ctlBusiMenuService.getMenuListOwn(comCode);
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
	
	/**
	 * 保存菜单
	 * saveMenuTree:(这里用一句话描述这个方法的作用).
	 * @param busiCode
	 * @param menuCodes
	 * @return :Map<String,Object> 
	 * @since JDK 1.8
	 * @author admin
	 */
	@RequestMapping(value = "saveMenuTree")
	public Map<String,Object> saveMenuTree(@RequestParam(required=true) String busiCode,
			@RequestParam(required=true) String menuCodes) {
		Map<String,Object> result = Maps.newHashMap();
		try {
			ctlBusiMenuService.deleteMenuTreeByBusiCode(busiCode);
			List<String> menuCodeList = Lists.newArrayList();
			String[] menuCodeArray = menuCodes.split(",");
			for(String s : menuCodeArray) {
				if(s!=null && !s.equals("")) {
					menuCodeList.add(s);
				}
			}
			ctlBusiMenuService.saveMenuTree(busiCode, menuCodeList);
			result.put("state", true);
			result.put("msg", "保存成功!");
		}catch(Exception e) {
			e.printStackTrace();
			result.put("state", false);
			result.put("msg", "保存失败!");
		}
		return result;
	}
	
	@RequestMapping(value = "saveBusiMenu")
	public Map<String,Object> saveBusiMenu(
			@RequestParam(required=true) String proId,
			@RequestParam(required=true) String defineName,
			@RequestParam(required=true) String defineSort) {
		Map<String,Object> result = Maps.newHashMap();
		try {
			CtlBusiMenu info = ctlBusiMenuService.get(proId);
			if (StringUtils.isNotBlank(defineName)) {
				info.setDefineName(defineName);
			}else {
				info.setDefineName(null);
			}
			if (StringUtils.isNotBlank(defineSort)) {
				info.setDefineSort(Long.valueOf(defineSort));
			}else {
				info.setDefineSort(null);
			}
			CtlBusiMenu busiMenu = ctlBusiMenuService.get(info.getId());
			if("0000".equals(busiMenu.getComCode())) {
				info.setId("");
				String comCode = UserServletContext.getUserInfo().getComCode();
				info.setComCode(comCode);
			}
			ctlBusiMenuService.save(info);
			result.put("state", true);
			result.put("msg", "保存成功!");
		} catch (Exception e) {
			// : handle exception
			e.printStackTrace();
			result.put("state", false);
			result.put("msg", "保存失败!");
		}
		
		return result;
	}
	
}
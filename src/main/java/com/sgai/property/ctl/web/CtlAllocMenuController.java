package com.sgai.property.ctl.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sgai.common.persistence.Page;
//import com.sgai.common.utils.CacheUtils;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.entity.CtlAllocMenu;
import com.sgai.property.ctl.entity.CtlCompBusi;
import com.sgai.property.ctl.service.CtlAllocMenuService;
import com.sgai.property.ctl.service.CtlCompBusiService;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.modules.login.support.UserServletContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 机构管理员菜单分配Controller
 * @author chenxing
 * @version 2017-11-10
 */  
@RestController
@Api(description = "菜单管理Controller")
@RequestMapping(value = "${adminPath}/allocmenu/ctlAllocMenu")
public class CtlAllocMenuController extends BaseController {

	@Autowired   
	private CtlAllocMenuService ctlAllocMenuService;
	@Autowired
	private CtlCompBusiService ctlCompBusiService;
	
	  
	    /**  
	    * @Title: getRolePage  
	    * @Description: (这里用一句话描述这个方法的作用)
	    * @param param  这是参数
	    * @param  request
	    * @param  response
	    * @param     参数  
	    * @return Page<CtlAllocMenu>    返回类型  
	    * @throws  
	    * @author admin
	    */  
	@RequestMapping(value = "getRolePage")
	public Page<CtlAllocMenu> getRolePage(CtlAllocMenu param,HttpServletRequest request, HttpServletResponse response){
		LoginUser user = UserServletContext.getUserInfo();
		param.setComCode(user.getComCode());
		param.setModuCode(user.getModuCode());
		Page<CtlAllocMenu> page = ctlAllocMenuService.getRolePage(new Page<CtlAllocMenu>(request, response), param);
		return page;
	}
	
	@RequestMapping(value = "getUserPage")
	public Page<CtlAllocMenu> getUserPage(CtlAllocMenu param,HttpServletRequest request, HttpServletResponse response){
		LoginUser user = UserServletContext.getUserInfo();
		param.setUserType(user.getUserType());
		param.setComCode(user.getComCode());
		param.setModuCode(user.getModuCode());
		Page<CtlAllocMenu> page = ctlAllocMenuService.getUserPage(new Page<CtlAllocMenu>(request, response), param);
		return page;
	}
	
	 
	    
	  
	    /**  
	    * @Title: getMenuListOfRole  
	    * @Description: (这里用一句话描述这个方法的作用)
	    * @param  corrCode
	    * @param  comCode
	    * @param  category
	    * @param    参数  
	    * @return Map<String,Object>    返回类型  
	    * @author admin
	    * @throws  
	    */  
	    
	@RequestMapping(value = "getMenuList")
	public Map<String,Object> getMenuListOfRole(@RequestParam(required=true) String corrCode,
			                                    @RequestParam(required=true) String comCode,
			                                    @RequestParam(required=true) String category,
			                                    @RequestParam(required=true) String mark) {
		Map<String,Object> result = Maps.newHashMap();
		try {
			LoginUser user = UserServletContext.getUserInfo();
			Map<String,String> param = new HashMap<String,String>();
			param.put("corrCode", corrCode);
			param.put("category", category); //category  R代表角色
			param.put("comCode", comCode);
			param.put("userType", user.getUserType());
			param.put("mark", mark);
			List<Map<String,String>> menuList0 = ctlAllocMenuService.getMenuListLack(param);//角色没有的权限
			List<Map<String,String>> menuList1 = ctlAllocMenuService.getMenuListOwn(param);//角色拥有的权限
			result.put("menuList0", menuList0);
			result.put("menuList1", menuList1);
			result.put("state", true);
			result.put("msg", "保存成功!");
		}catch(Exception e) {
			e.printStackTrace();
			result.put("state", false);
			result.put("msg", "保存失败!");
		}
		return result;
	}
	@RequestMapping(value = "getModuUserMenuList")
	public Map<String,Object> getModuUserMenu(@RequestParam(required=true) String corrCode,
			                                    @RequestParam(required=true) String moduCode,
			                                    @RequestParam(required=true) String category,
			                                    @RequestParam(required=true) String mark,
			                                    @RequestParam(required=true) String comCode) {
		Map<String,Object> result = Maps.newHashMap();
		try {
			LoginUser user = UserServletContext.getUserInfo();
			Map<String,String> param = new HashMap<String,String>();
			param.put("corrCode", corrCode);
			param.put("category", category);
			param.put("moduCode", moduCode);
			param.put("comCode", comCode);
			param.put("userType", user.getUserType());
			param.put("mark", mark);
			List<Map<String,String>> menuList0 = ctlAllocMenuService.getModuUserMenuListLack(param);
			List<Map<String,String>> menuList1 = ctlAllocMenuService.getMenuListOwn(param);
			result.put("menuList0", menuList0);
			result.put("menuList1", menuList1);
			result.put("state", true);
			result.put("msg", "保存成功!");
		}catch(Exception e) {
			e.printStackTrace();
			result.put("state", false);
			result.put("msg", "保存失败!");
		}
		return result;
	}
	/**
	 * 
	    * @Title: getModuMenuList  
	    * @Description: (得到某一模块已有或是未有的菜单)
	    * @param @param mark
	    * @param @return    参数  
	    * @return Map<String,Object>    返回类型  
	    * @throws
	 */
	@RequestMapping(value = "getModuMenuList")
	public Map<String,Object> getModuMenuList(@RequestParam(required=true) String mark,
										@RequestParam(required=true) String moduCode) {
		Map<String,Object> result = Maps.newHashMap();
		try {
			LoginUser user = UserServletContext.getUserInfo();
			Map<String,String> param = new HashMap<String,String>();
			param.put("moduCode", moduCode);
			param.put("userType", user.getUserType());
			param.put("mark", mark);
			param.put("comCode", user.getComCode());
			CtlCompBusi compBusi = new CtlCompBusi();
			compBusi.setComCode(user.getComCode());
			List<CtlCompBusi> busis = ctlCompBusiService.findList(compBusi);
			String busiCode = busis.get(0).getBusiCode();
			/*if (busis != null && busis.size() > 0) {
				param.put("busiCode", busis.get(0).getBusiCode());
			}*/
			List<Map<String,String>> menuList0 = ctlAllocMenuService.getModuMenuListLack(param,busiCode);
			List<Map<String,String>> menuList1 = ctlAllocMenuService.getModuMenuListOwn(param);
			result.put("menuList0", menuList0);
			result.put("menuList1", menuList1);
			result.put("state", true);
			result.put("msg", "保存成功!");
		}catch(Exception e) {
			e.printStackTrace();
			result.put("state", false);
			result.put("msg", "保存失败!");
		}
		return result;
	}
	@RequestMapping(value = "saveMenuTree")
	public Map<String,Object> saveMenuTree(
			                               @RequestParam(required=true) String corrCode,
			                               @RequestParam(required=true) String menuCodes,
			                               @RequestParam(required=true) String category,
			                               @RequestParam(required=true) String comCode) {
		Map<String,Object> result = Maps.newHashMap();
		try {
			Map<String,String> param = new HashMap<String,String>();
			param.put("corrCode", corrCode);
			param.put("category", category);
			param.put("comCode", comCode);
			ctlAllocMenuService.deleteMenuTree(param);
			List<String> menuCodeList = Lists.newArrayList();
			String[] menuCodeArray = menuCodes.split(",");
			for(String s : menuCodeArray) {
				if(s!=null && !s.equals("")) {
					menuCodeList.add(s);
				}
			}
			ctlAllocMenuService.saveMenuTree(corrCode,menuCodeList,category,comCode);
			//CacheUtils.remove("IndexMenus", corrCode);
			result.put("state", true);
			result.put("msg", "保存成功!");
		}catch(Exception e) {
			e.printStackTrace();
			result.put("state", false);
			result.put("msg", "保存失败!");
		}
		return result;
	}
	/**
	 * @throws JsonProcessingException 
	 * 
	    * @Title: findMenuByUserCode  
	    * @Description: (查看某个用户是否拥有某个菜单)
	    * @param @param user
	    * @param @param menuCode
	    * @param @return    参数  
	    * @return int    返回类型  
	    * @throws
	 */
	@ApiOperation(value = "查看某个用户是否拥有某个菜单", notes = "查看某个用户是否拥有某个菜单")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "menuCode", value = "菜单代码", required = false, dataType = "String"),
     })
	@RequestMapping(value = "findMenuByUserCode",method=RequestMethod.POST)
	public CommonResponse findMenuByUserCode(LoginUser user,String menuCode) throws JsonProcessingException {
		Map<String,String> params = new HashMap<String,String>();
		params.put("userCode", user.getUserId());
		params.put("menuCode", menuCode);
		return ResponseUtil.successResponse(ctlAllocMenuService.findMenuByUserCode(params));
	}
}
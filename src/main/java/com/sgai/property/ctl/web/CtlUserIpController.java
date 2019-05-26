
package com.sgai.property.ctl.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Maps;
import com.sgai.common.config.Global;
import com.sgai.common.persistence.Page;
import com.sgai.common.utils.StringUtils;
import com.sgai.common.web.BaseController;
import com.sgai.property.ctl.entity.CtlUserIp;
import com.sgai.property.ctl.service.CtlUserIpService;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
    * @ClassName: CtlUserIpController  
    * @Description: (用户Ip管理controller)
    * @author shang  
    * @date 2017年11月18日  
    * @Company 首自信--智慧城市创新中心
 */
@RestController
@RequestMapping(value = "${adminPath}/ctlUserIp")
@Api(description = "用户IP信息接口")
public class CtlUserIpController extends BaseController {

	@Autowired
	private CtlUserIpService ctlUserIpService;
	
	/**
	 * 
	    * @Title: get  
	    * @Description: (获取单行)
	    * @param @param id 单行主键id字段
	    * @param @return    参数  携带信息实体
	    * @return CtlUserIp    返回类型  
	    * @throws
	 */
	@ModelAttribute
	public CtlUserIp get(@RequestParam(required=false) String id) {
		CtlUserIp entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ctlUserIpService.get(id);
		}
		if (entity == null){
			entity = new CtlUserIp();
		}
		return entity;
	}
	
	
	@RequestMapping(value = {"list", ""})
	public String list(CtlUserIp ctlUserIp, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(ctlUserIp==null){
			ctlUserIp=new CtlUserIp();
		}
		Page<CtlUserIp> page = ctlUserIpService.findPage(new Page<CtlUserIp>(request, response), ctlUserIp); 
		model.addAttribute("page", page);
		return "ctl/useripdefine/ctlUserIpList";
	}

	
	@RequestMapping(value = "form")
	public String form(CtlUserIp ctlUserIp, Model model) {
		model.addAttribute("ctlUserIp", ctlUserIp);
		return "ctl/useripdefine/ctlUserIpForm";
	}

	
	@RequestMapping(value = "save")
	public String save(CtlUserIp ctlUserIp, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ctlUserIp)){
			return form(ctlUserIp, model);
		}
		ctlUserIpService.save(ctlUserIp);
		addMessage(redirectAttributes, "保存用户IP管理成功");
		return "redirect:"+Global.getAdminPath()+"/useripdefine/ctlUserIp/?repage";
	}
	
	//@RequiresPermissions("useripdefine:ctlUserIp:edit")
	@RequestMapping(value = "delete",method=RequestMethod.POST)
	@ApiOperation(value = "用户IP信息删除", notes = "用户IP信息删除")
	public CommonResponse delete(String ids, RedirectAttributes redirectAttributes) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = ctlUserIpService.deleteUserIp(ids);
		} catch (Exception e) {
			// : handle exception
			e.printStackTrace();
			map.put("msg", "error");
		}
		return ResponseUtil.successResponse(map);
	}
	
	/**
	 * 
	    * @Title: userDelete  
	    * @Description: (根据用户代码和IP地址删除条目)
	    * @param @param ctlUserIp 携带条目信息的实例
	    * @param @param request
	    * @param @param response
	    * @param @throws IOException    参数  
	    * @return void    返回类型  
	    * @throws
	 */
	@RequestMapping(value = "UserDelete")
	public void userDelete(CtlUserIp ctlUserIp,HttpServletRequest request, HttpServletResponse response) {
		ctlUserIp.setUserCode(request.getParameter("user_code"));
		ctlUserIp.setIpAddress(request.getParameter("ip_address"));
		ctlUserIpService.delete(ctlUserIp);
	}
	
	/**
	 * 
	    * @Title: getListUserIp  
	    * @Description: (根据用户代码和Ip地址批量获取条目)
	    * @param @param ctlUserIp 携带查询信息的实例
	    * @param @param request
	    * @param @param response
	    * @param @return 携带信息实体和分页信息的对象
	    * @param @throws IOException    参数  
	    * @return Page<CtlUserIp>    返回类型  
	    * @throws
	 */
	@RequestMapping(value = "/getListUserIp",method=RequestMethod.POST)
	@ApiOperation(value = "获取用户IP信息列表", notes = "获取用户IP信息列表")
	@ApiImplicitParams({
            @ApiImplicitParam(name = "user_code", value = "用户代码", required = false,paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "id", value = "id", required = false,paramType = "query", dataType = "String"),
    })
	public CommonResponse getListUserIp(
			 String user_code,String id,
			 @RequestParam(value = "pageNo", required = true,defaultValue="0") Integer pageNo ,
			 @RequestParam(value = "pageSize", required = true,defaultValue="10") Integer pageSize,

			 HttpServletRequest request, HttpServletResponse response) throws IOException {
		CtlUserIp ctlUserIp = new CtlUserIp();
		ctlUserIp.setUserCode(user_code);
		ctlUserIp.setId(request.getParameter("id"));
		Page<CtlUserIp> page = ctlUserIpService.findPage(new Page<CtlUserIp>(pageNo, pageSize), ctlUserIp);
		
		return ResponseUtil.successResponse(page);
	}
	
	/**
	 * 
	    * @Title: User  
	    * @Description: (插入新条目)
	    * @param @param ctlUserIp 携带查询信息的实例
	    * @param @param request
	    * @param @param response
	    * @param @return 键：msg，成功值为success 失败值为fail
	    * @param @throws IOException    参数  
	    * @return Page<CtlUserIp>    返回类型  
	    * @throws
	 */
	@RequestMapping(value = "UserSave",method=RequestMethod.POST)
	@ApiOperation(value = "用户IP信息保存", notes = "用户IP信息保存")
	public CommonResponse UserSave(CtlUserIp ctlUser,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> result = Maps.newHashMap();
		try {
		ctlUserIpService.save(ctlUser);
		result.put("msg", "success");
		}
		catch(Exception e) {
			e.printStackTrace();
			result.put("msg", "fail");
		}
		return ResponseUtil.successResponse(result);
		
	}
	/**
	 * 
	    * @Title: getListUserIp  
	    * @Description: (更新条目)
	    * @param @param ctlUserIp 携带更新信息的实例
	    * @param @param request
	    * @param @param response
	    * @param @return 键：msg，成功值为success 失败值为fail
	    * @param @throws IOException    参数  
	    * @return Page<CtlUserIp>    返回类型  
	    * @throws
	 */
	@RequestMapping(value = "UserUpdate",method=RequestMethod.POST)
	@ApiOperation(value = "用户IP信息更新", notes = "用户IP信息更新")
	public CommonResponse UserUpdate(CtlUserIp ctlUserIp,HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String,Object> result = Maps.newHashMap();
		try {
		ctlUserIpService.save(ctlUserIp);
		
		result.put("msg", "success");
		}
		catch(Exception e) {
			result.put("msg", "fail");
		}
		
		return ResponseUtil.successResponse(result);
		
	}
	
	/**
	 * 
	    * @Title: getComp  
	    * @Description: (从ctl_comp中获取全部机构代码作为下拉菜单选项)
	    * @param @param request
	    * @param @param response
	    * @param @return 机构代码字符串数组
	    * @param @throws IOException    参数  
	    * @return List<String>    返回类型  
	    * @throws
	 */
	@RequestMapping(value = "getComp",method=RequestMethod.POST)
	@ApiOperation(value = "获取机构代码", notes = "获取机构代码") //获取全部机构的机构代码
	public CommonResponse getComp(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return ResponseUtil.successResponse(ctlUserIpService.getComp());
		
	}
	
	/**
	 * 
	    * @Title: getUser  
	    * @Description: (根据选择的机构代码从ctl_uer表中获取用户代码)
	    * @param @param ctlUserIp 携带查询信息的实例
	    * @param @param request
	    * @param @param response
	    * @param @return 携带查询信息的实体的数组
	    * @param @throws IOException    参数  
	    * @return List<CtlUserIp>    返回类型  
	    * @throws
	 */
	@RequestMapping(value = "getUser",method=RequestMethod.POST)
	@ApiOperation(value = "获取用户列表", notes = "获取用户列表") //根据选择的机构获取用户列表用于前端下拉菜单选项添加
	public CommonResponse getUser(CtlUserIp ctlUserIp,HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(request.getParameter("com_code")!="")
		ctlUserIp.setComCode(request.getParameter("com_code")); //设置entity的机构代码comCode，根据机构代码到ctl_user中查询用户名UserName和用户代码UserCode
		return ResponseUtil.successResponse(ctlUserIpService.getUser(ctlUserIp));
		
	}
}
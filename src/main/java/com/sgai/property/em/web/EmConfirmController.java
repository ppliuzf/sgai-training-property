package com.sgai.property.em.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.property.em.entity.EmConfirm;
import com.sgai.property.em.service.EmConfirmService;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.jwt.util.CommonResponse;
import com.sgai.modules.login.jwt.util.ResponseUtil;
import com.sgai.modules.login.support.UserServletContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 
    * @ClassName: EmConfirmController  
    * @com.sgai.property.commonService.vo;(事件核实Controller)
    * @author LiuYang  
    * @date 2017年12月5日  
    * @Company 首自信--智慧城市创新中心
 */
@Controller
@RequestMapping(value = "${adminPath}/em/emconfirm/emConfirm")
@Api(description = "事件核实接口")
public class EmConfirmController extends BaseController {

	@Autowired
	private EmConfirmService emConfirmService;
	
	@ApiOperation(value = "获得事件核实", notes = "获得事件核实")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "事件核实id", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getConfirm",method=RequestMethod.POST)
	public EmConfirm getConfirm(HttpServletRequest request, String id, @RequestHeader(value="token")String token,HttpServletResponse response) {
		EmConfirm emConfirm = emConfirmService.get(id);
		return emConfirm;
	}
	
	@ApiOperation(value = "保存事件核实", notes = "保存事件核实")
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(LoginUser user, EmConfirm emConfirm, Model model,@RequestHeader(value="token")String token, RedirectAttributes redirectAttributes) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			LoginUser userInfo = UserServletContext.getUserInfo();
			emConfirm.setConfirmPerson(userInfo.getUserId());
			emConfirm.setEnabledFlag("Y");
			emConfirmService.saveConfirm(emConfirm,user);
			map.put("msg", "success");
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "fail");
		}
		return map;
	}
	
	@ApiOperation(value = "获得事件核实列表页面", notes = "获得事件核实列表页面")
	@RequestMapping(value = {"list", ""},method=RequestMethod.GET)
	public String list(EmConfirm emConfirm, 
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		if(emConfirm==null){
			emConfirm=new EmConfirm();
		}
		Page<EmConfirm> page = emConfirmService.findPage(new Page<EmConfirm>(pageNo, pageSize), emConfirm); 
		model.addAttribute("page", page);
		return "/em/emConfirmList";
	}

	@ApiOperation(value = "事件核实form页面", notes = "事件核实form页面")
	@RequestMapping(value = "form",method=RequestMethod.GET)
	public String form(EmConfirm emConfirm, @RequestHeader(value="token")String token, Model model) {
		model.addAttribute("emConfirm", emConfirm);
		return "/em/emConfirmForm";
	}
	
	@ApiOperation(value = "获得角色", notes = "获得角色")
	@RequestMapping(value = "getRole",method=RequestMethod.POST)
	public @ResponseBody LoginUser getRole(EmConfirm emConfirm,@RequestHeader(value="token")String token,  Model model) {
		LoginUser userInfo = UserServletContext.getUserInfo();
		return userInfo;
	}
	
	@ApiOperation(value = "事件核实列表", notes = "事件核实列表")
	@RequestMapping(value = "/getList",method=RequestMethod.POST)
	public @ResponseBody CommonResponse getList(EmConfirm emConfirm,
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Page<EmConfirm> page = emConfirmService.findPage(new Page<EmConfirm>(pageNo, pageSize), emConfirm);
		return ResponseUtil.successResponse(page);
	}
	
	@ApiOperation(value = "保存事件核实并更新流程实例", notes = "保存事件核实并更新流程实例")
	@RequestMapping(value = "saveConfirm",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveComplete(
			@RequestBody EmConfirm emConfirm, 
			@RequestHeader(value="token")String token, 
			HttpServletRequest request
			) {
		Map<String, Object> map = new HashMap<String, Object>();
		LoginUser user = UserServletContext.getUserInfo();
		try {
			emConfirm.setEnabledFlag("Y");
			if (emConfirm.getConfirmDate() == null) {
				emConfirm.setConfirmDate(new Date());
			}
			emConfirmService.saveConfirm(emConfirm,user);
			map.put("msg", "success");
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "fail");
		}
		return map;
	}
	/**
	 * 
	 * getByCode:(根据事件编码获取事件实例).
	 * @param emCode 事件编码
	 * @return :EmConfirm 
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	@ApiOperation(value = "根据事件编码获取事件实例", notes = "根据事件编码获取事件实例")
	@RequestMapping(value = "getByCode",method=RequestMethod.POST)
	@ResponseBody
	public EmConfirm getByCode(
			@RequestBody EmConfirm emConfirm,
			@RequestHeader(value="token")String token,
			HttpServletRequest request,
			HttpServletResponse response
			) {
		return  emConfirmService.getCode(emConfirm);
	}
}
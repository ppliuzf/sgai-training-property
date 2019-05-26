package com.sgai.property.em.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
import com.sgai.property.em.entity.EmProcess;
import com.sgai.property.em.service.EmProcessService;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 
    * @ClassName: EmProcessController  
    * @com.sgai.property.commonService.vo;(事件处理(含更新)Controller)
    * @author LiuYang  
    * @date 2017年12月5日  
    * @Company 首自信--智慧城市创新中心
 */
@Controller
@RequestMapping(value = "${adminPath}/em/emprocess/emProcess")
@Api(description = "事件处理接口")
public class EmProcessController extends BaseController {

	@Autowired
	private EmProcessService emProcessService;

	@ApiOperation(value = "事件处理form页面", notes = "事件处理form页面")
	@RequestMapping(value = "form",method=RequestMethod.GET)
	public String form(EmProcess emProcess,@RequestHeader(value="token")String token, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("emProcess", emProcess);
		return "/em/emProcessForm";
	}
	
	@ApiOperation(value = "事件处理分页", notes = "事件处理分页")
	@RequestMapping(value = "/getList",method=RequestMethod.POST)
	public Page<EmProcess> getList(EmProcess emProcess,
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) {
		Page<EmProcess> page = emProcessService.findPage(new Page<EmProcess>(pageNo, pageSize), emProcess);
		return page;
	}

	@ApiOperation(value = "事件处理list结合", notes = "事件处理list结合")
	@RequestMapping(value = "/getProcess",method=RequestMethod.POST)
	public @ResponseBody List<EmProcess> getProcess(
			@RequestHeader(value="token")String token,
			HttpServletRequest request, 
			@RequestBody EmProcess emProcess, 
			HttpServletResponse response) {
		List<EmProcess> list = emProcessService.findList(emProcess);
		return list;
	}
	
	@ApiOperation(value = "事件处理form页面", notes = "事件处理form页面")
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> save(
			@RequestHeader(value="token")String token,
			@RequestBody EmProcess emProcess, 
			HttpServletRequest request
			) {
		Map<String, Object> map = new HashMap<String, Object>();
		LoginUser userInfo = UserServletContext.getUserInfo();
		try {
			emProcess.setEnabledFlag("Y");
			emProcess.setProcPerson(userInfo.getUserId());
			emProcessService.save(emProcess);
			map.put("msg", "success");
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "fail");
		}
		return map;
	}
	
	@ApiOperation(value = "保存事件处理并跟新流程", notes = "保存事件处理并跟新流程")
	@RequestMapping(value = "saveComplete",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveComplete(
			@RequestHeader(value="token")String token,
			@RequestBody EmProcess emProcess, 
			HttpServletRequest request
			) {
		Map<String, Object> map = new HashMap<String, Object>();
		LoginUser userInfo = UserServletContext.getUserInfo();
		try {
			emProcess.setEnabledFlag("Y");
			emProcess.setProcPerson(userInfo.getUserId());
			emProcessService.saveComplete(emProcess,userInfo);
			map.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return map;
	}
	
	@ApiOperation(value = "事件处理应急form页面", notes = "事件处理应急form页面")
	@RequestMapping(value = "emergencyProcessForm",method=RequestMethod.GET)
	public String emergencyProcessForm(@RequestHeader(value="token")String token,EmProcess emProcess, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("emProcess", emProcess);
		return "/em/emEmergencyRecordProcessForm";
	}
	
	@ApiOperation(value = "事件处理list集合", notes = "事件处理list")
	@RequestMapping(value = "/getEmergencyProcess",method=RequestMethod.POST)
	public @ResponseBody List<EmProcess> getEmergencyProcess(@RequestHeader(value="token")String token,HttpServletRequest request, EmProcess emProcess, HttpServletResponse response) {
		List<EmProcess> list = emProcessService.findList(emProcess);
		return list;
	}

}
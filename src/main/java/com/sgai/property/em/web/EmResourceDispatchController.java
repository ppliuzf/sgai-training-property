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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sgai.common.persistence.Page;
import com.sgai.common.web.BaseController;
import com.sgai.property.em.entity.EmResourceDispatch;
import com.sgai.property.em.service.EmResourceDispatchService;
import com.sgai.modules.login.jwt.bean.LoginUser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
    * @ClassName: EmResourceDispatchController  
    * @com.sgai.property.commonService.vo;(应急资源调度Controller)
    * @author LiuYang  
    * @date 2017年12月5日  
    * @Company 首自信--智慧城市创新中心
 */
@Controller
@RequestMapping(value = "${adminPath}/em/emresourcedispatch/emResourceDispatch")
@Api(description = "应急资源接口")
public class EmResourceDispatchController extends BaseController {

	@Autowired
	private EmResourceDispatchService emResourceDispatchService;
	
	
	@ApiOperation(value = "根据id查询应急资源", notes = "根据id查询应急资源")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "应急资源id", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getAssign",method=RequestMethod.POST)
	public EmResourceDispatch getAssign(HttpServletRequest request, String id, @RequestHeader(value="token")String token,HttpServletResponse response) {
		EmResourceDispatch emResourceDispatch = emResourceDispatchService.get(id);
		return emResourceDispatch;
	}
	
	
	@ApiOperation(value = "应急资源集合", notes = "应急资源集合")
	@RequestMapping(value = "/getResourceDispatch",method=RequestMethod.POST)
	@ResponseBody
	public List<EmResourceDispatch> getResourceDispatch(HttpServletRequest request, @RequestHeader(value="token")String token,EmResourceDispatch emResourceDispatch, HttpServletResponse response) {
		List<EmResourceDispatch> list = emResourceDispatchService.findList(emResourceDispatch);
		return list;
	}
	
	@ApiOperation(value = "应急资源保存", notes = "应急资源保存")
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public Map<String, Object> save(EmResourceDispatch emResourceDispatch,@RequestHeader(value="token")String token, Model model, RedirectAttributes redirectAttributes) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			emResourceDispatchService.save(emResourceDispatch);
			map.put("msg", "success");
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "fail");
		}
		return map;
	}
	
	@ApiOperation(value = "应急资源列表", notes = "应急资源列表")
	@RequestMapping(value = {"list", ""},method=RequestMethod.GET)
	public String list(EmResourceDispatch emResourceDispatch, 
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		if(emResourceDispatch==null){
			emResourceDispatch=new EmResourceDispatch();
		}
		Page<EmResourceDispatch> page = emResourceDispatchService.findPage(new Page<EmResourceDispatch>(pageNo, pageSize), emResourceDispatch); 
		model.addAttribute("page", page);
		return "em/emResourceDispatchList";
	}

	@ApiOperation(value = "应急资源form页面", notes = "应急资源form页面")
	@RequestMapping(value = "form",method=RequestMethod.GET)
	public String form(EmResourceDispatch emResourceDispatch, @RequestHeader(value="token")String token,Model model) {
		model.addAttribute("emResourceDispatch", emResourceDispatch);
		return "em/emResourceDispatchForm";
	}
	
	@ApiOperation(value = "应急资源分页", notes = "应急资源分页")
	@RequestMapping(value = "/getList",method=RequestMethod.POST)
	public Page<EmResourceDispatch> getList(EmResourceDispatch emResourceDispatch,
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) {
		Page<EmResourceDispatch> page = emResourceDispatchService.findPage(new Page<EmResourceDispatch>(pageNo, pageSize), emResourceDispatch);
		return page;
	}
	
	@ApiOperation(value = "应急资源创建页面", notes = "应急资源创建页面")
	@RequestMapping(value = "emEmergencyRecordForm",method=RequestMethod.GET)
	public String emEmergencyRecordForm(EmResourceDispatch emResourceDispatch, @RequestHeader(value="token")String token,String instanceId, String emCode, String emType, Model model) {
		model.addAttribute("emResourceDispatch", emResourceDispatch);
		return "/em/emEmergencyRecordCreateForm";
	}
	
	@ApiOperation(value = "应急资源保存", notes = "应急资源保存")
	@RequestMapping(value = "saveResource",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveResource(LoginUser user,@RequestHeader(value="token")String token,EmResourceDispatch emResourceDispatch, Model model, RedirectAttributes redirectAttributes) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			emResourceDispatchService.saveResource(emResourceDispatch,user);
			map.put("msg", "success");
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "fail");
		}
		return map;
	}
	


}
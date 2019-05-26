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
import com.sgai.property.em.entity.EmAssign;
import com.sgai.property.em.service.EmAssignService;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 
    * @ClassName: EmAssignController  
    * @com.sgai.property.commonService.vo;(事件指派Controller)
    * @author LiuYang  
    * @date 2017年12月5日  
    * @Company 首自信--智慧城市创新中心
 */
@Controller
@RequestMapping(value = "${adminPath}/em/emassign/emAssign")
@Api(description = "事件指派接口")
public class EmAssignController extends BaseController {

	@Autowired
	private EmAssignService emAssignService;
	
	
	@ApiOperation(value = "应急事件处理分页", notes = "应急事件处理分页")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "事件指派id", required = false,paramType = "query", dataType = "String")
	})
	@RequestMapping(value = "/getAssign",method=RequestMethod.POST)
	public EmAssign getAssign(HttpServletRequest request, String id,@RequestHeader(value="token")String token, HttpServletResponse response) {
		EmAssign emAssign = emAssignService.get(id);
		return emAssign;
	}
	
	/**
	 * 
	 * getAssignByCode:(查询指派数据).
	 * @param request
	 * @param emAssign
	 * @param response
	 * @return
	 * @since JDK 1.8
	 * @author lenovo
	 */
	@ApiOperation(value = "根据事件编号查询事件指派", notes = "根据事件编号查询事件指派")
	@RequestMapping(value = "/getAssignByCode",method=RequestMethod.POST)
	public @ResponseBody EmAssign getAssignByCode(
			HttpServletRequest request,
			@RequestHeader(value="token")String token, 
			@RequestBody EmAssign emAssign, 
			HttpServletResponse response) {
		EmAssign assign = emAssignService.getAssignByCode(emAssign);
		return assign;
	}
	
	@ApiOperation(value = "保存事件指派", notes = "保存事件指派")
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public Map<String, Object> save(EmAssign emAssign,@RequestHeader(value="token")String token, Model model, RedirectAttributes redirectAttributes) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			emAssign.setEnabledFlag("Y");
			emAssignService.save(emAssign);
			map.put("msg", "success");
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "fail");
		}
		return map;
	}
	
	@ApiOperation(value = "保存事件指派并跟新流程实例", notes = "保存事件指派并跟新流程实例")
	@RequestMapping(value = "saveAssignAndProcess",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveAssignAndProcess(
			@RequestBody EmAssign emAssign,
			@RequestHeader(value="token")String token, 
			HttpServletRequest request
			) {
		Map<String, Object> map = new HashMap<String, Object>();
		LoginUser user = UserServletContext.getUserInfo();
		try {
			emAssign.setProcPerson(user.getUserId());
			emAssign.setEnabledFlag("Y");
			if (emAssign.getAssignDatetime() == null) {
				emAssign.setAssignDatetime(new Date());
			}
			emAssignService.saveAssignAndProcess(emAssign,user);
			map.put("msg", "success");
		} catch (Exception e) {

			e.printStackTrace();
			map.put("msg", "fail");
		}
		return map;
	}
	
	@ApiOperation(value = "查询事件指派列表", notes = "查询事件指派列表")
	@RequestMapping(value = {"list", ""},method=RequestMethod.GET)
	public String list(EmAssign emAssign, @RequestHeader(value="token")String token,HttpServletRequest request, HttpServletResponse response, Model model) {
		if(emAssign==null){
			emAssign=new EmAssign();
		}
		Page<EmAssign> page = emAssignService.findPage(new Page<EmAssign>(request, response), emAssign); 
		model.addAttribute("page", page);
		return "/em/emAssignList";
	}

	@ApiOperation(value = "事件指派页面", notes = "事件指派页面")
	@RequestMapping(value = "form",method=RequestMethod.GET)
	public String form(EmAssign emAssign,@RequestHeader(value="token")String token, Model model) {
		model.addAttribute("emAssign", emAssign);
		model.addAttribute("emCode",emAssign.getEmCode());
		return "/em/emAssignForm";
	}
	
	@ApiOperation(value = "事件指派分页", notes = "事件指派分页")
	@RequestMapping(value = "/getList",method=RequestMethod.POST)
	public Page<EmAssign> getList(EmAssign emAssign,
			@RequestHeader(value="token")String token,
			@RequestParam(value="pageNo",required = true ,defaultValue = "0" )Integer pageNo,
			@RequestParam(value="pageSize",required = true ,defaultValue = "10" )Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) {
		Page<EmAssign> page = emAssignService.findPage(new Page<EmAssign>(pageNo, pageSize), emAssign);
		return page;
	}
	
}